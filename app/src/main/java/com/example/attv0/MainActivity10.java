package com.example.attv0;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity10 extends AppCompatActivity {


    TextView ABclass,Absubcode,ABabsent;
    Button ABsearch,ABdelete;
    private DBHelper dbHelper;


    String postID="";
    String postNAME="";
    String postCLASS="";
    String postSUBCODE="";
    String postTOTSTU="";


    ArrayList<Integer> Asublist = new ArrayList<>();
    ArrayList<String> Asubcode = new ArrayList<>();
    boolean[] Aselectedsub;

    ArrayList<Integer> Asubcodelist = new ArrayList<>();
    ArrayList<String> ASubcode = new ArrayList<>();
    boolean[] Aselectedsubcode;


    ArrayList<Integer> Astulist = new ArrayList<>();
    ArrayList<String> Astudents = new ArrayList<>();
    boolean[] Aselectedstu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);


        Intent intent=getIntent();
        String id= intent.getStringExtra("staffpostID");
        postID=id;
        String name= intent.getStringExtra("staffpostNAME");
        postNAME=name;
        String classs= intent.getStringExtra("staffpostCLASS");
        postCLASS=classs;
        String subcod= intent.getStringExtra("staffpostSUBCODE");
        postSUBCODE=subcod;
        String totstu= intent.getStringExtra("staffpostTOTSTU");
        postTOTSTU=totstu;

        dbHelper = new DBHelper(this);


        /**********************************************************************/

        Cursor cursor1=dbHelper.StaffClass(postID);
        if (cursor1.getCount() == 0) {
            Toast.makeText(MainActivity10.this, "No Class Registered...", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cursor1.moveToFirst()) {
            do {
                String Classlist=cursor1.getString(0);
                ArrayList<String> sublist = new ArrayList<>(Arrays.asList(Classlist.split(",")));
                for(String n : sublist){
                    Asubcode.add(n);

                }
            } while (cursor1.moveToNext());
        }

        String[] strCLASS = new String[Asubcode.size()];

        for (int i = 0; i < Asubcode.size(); i++) {
            strCLASS[i] = Asubcode.get(i);
        }
        //for (String k : strSTU) {
        //Toast.makeText(MainActivity3.this, ""+k, Toast.LENGTH_SHORT).show();
        // }

        ABclass = findViewById(R.id.dclass);

        Aselectedsub = new boolean[strCLASS.length];

        ABclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity10.this);
                builder.setTitle("SELECT CLASS");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(strCLASS, Aselectedsub, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                        if (b) {
                            Asublist.add(i);
                            Collections.sort(Asublist);
                        } else {
                            Asublist.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < Asublist.size(); j++) {
                            stringBuilder.append(strCLASS[Asublist.get(j)]);
                            if (j != Asublist.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                        ABclass.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.show();
            }
        });
        /**********************************************************************/

/**************************************************************/

        Cursor cursor2=dbHelper.StaffSubcode(postID);
        if (cursor2.getCount() == 0) {
            Toast.makeText(MainActivity10.this, "No Subjects Registered...", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cursor2.moveToFirst()) {
            do {
                String Subcodelist=cursor2.getString(0);
                ArrayList<String> subcodelist = new ArrayList<>(Arrays.asList(Subcodelist.split(",")));
                for(String n : subcodelist){
                    ASubcode.add(n);

                }
            } while (cursor2.moveToNext());
        }

        String[] strSUBCODE = new String[ASubcode.size()];

        for (int i = 0; i < ASubcode.size(); i++) {
            strSUBCODE[i] = ASubcode.get(i);
        }
        //for (String k : strSTU) {
        //Toast.makeText(MainActivity3.this, ""+k, Toast.LENGTH_SHORT).show();
        // }
        Absubcode = findViewById(R.id.dsubcode);
        Aselectedsubcode = new boolean[strSUBCODE.length];

        Absubcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity10.this);
                builder.setTitle("SELECT SUBJECT CODE");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(strSUBCODE, Aselectedsubcode, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                        if (b) {
                            Asubcodelist.add(i);
                            Collections.sort(Asubcodelist);
                        } else {
                            Asubcodelist.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < Asubcodelist.size(); j++) {
                            stringBuilder.append(strSUBCODE[Asubcodelist.get(j)]);
                            if (j != Asubcodelist.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                        Absubcode.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.show();
            }
        });



        /**************************************************************/

        ABabsent = findViewById(R.id.delabsentees);
        ABsearch = findViewById(R.id.searchAbsen);

        ABsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                /**************************************************************/

                String sc = Absubcode.getText().toString();

                Cursor cursor=dbHelper.getAbsentDetailDelete(sc);
                if (cursor.getCount() == 0) {
                    Toast.makeText(MainActivity10.this, "No Students Registered...", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (cursor.moveToFirst()) {
                    do {
                        String STUDENTSlist=cursor.getString(0);
                        ArrayList<String> stulist = new ArrayList<>(Arrays.asList(STUDENTSlist.split(",")));
                        for(String n : stulist){
                            Astudents.add(n);

                        }
                    } while (cursor.moveToNext());
                }

                String[] strSTU = new String[Astudents.size()];

                for (int i = 0; i < Astudents.size(); i++) {
                    strSTU[i] = Astudents.get(i);
                }
                //for (String k : strSTU) {
                //Toast.makeText(MainActivity3.this, ""+k, Toast.LENGTH_SHORT).show();
                // }

                Aselectedstu = new boolean[strSTU.length];

                ABabsent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity10.this);
                        builder.setTitle("SELECT STUDENTS");
                        builder.setCancelable(false);
                        builder.setMultiChoiceItems(strSTU, Aselectedstu, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                                if (b) {
                                    Astulist.add(i);
                                    Collections.sort(Astulist);
                                } else {
                                    Astulist.remove(Integer.valueOf(i));
                                }
                            }
                        });

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                StringBuilder stringBuilder = new StringBuilder();
                                for (int j = 0; j < Astulist.size(); j++) {
                                    stringBuilder.append(strSTU[Astulist.get(j)]);
                                    if (j != Astulist.size() - 1) {
                                        stringBuilder.append(", ");
                                    }
                                }
                                ABabsent.setText(stringBuilder.toString());
                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        builder.show();
                    }
                });

                /**************************************************************/

            }
        });



        ABdelete = findViewById(R.id.deleteAbsen);

        ABdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String k = ABabsent.getText().toString();
                String scc = Absubcode.getText().toString();


               /* String[] strSTU = new String[students.size()];

                for (int i = 0; i < students.size(); i++) {
                    strSTU[i] = students.get(i);
                }

                */
                //for (String k : strSTU) {

                // }


                ArrayList<String> stulist = new ArrayList<>(Arrays.asList(k.split(",")));
                for(String n : stulist) {
                    // students.add(n);
                    //dbHelper.addabsentee("T101","DAY ORDER : 1","3","MCA363T",""+n);

                    dbHelper.delAbsentDetail(scc,postID,n.trim());
                    //Toast.makeText(MainActivity10.this, "" + n, Toast.LENGTH_SHORT).show();
                }


                ABclass.setText("");
                Absubcode.setText("");
                Toast.makeText(MainActivity10.this, "Deleted Successfully!!!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity10.this,MainActivity2.class);
                i.putExtra("staffpostID",postID);
                i.putExtra("staffpostNAME",postNAME);
                i.putExtra("staffpostCLASS",postCLASS);
                i.putExtra("staffpostSUBCODE",postSUBCODE);
                i.putExtra("staffpostTOTSTU",postTOTSTU);
                startActivity(i);




            }
        });



    }
}