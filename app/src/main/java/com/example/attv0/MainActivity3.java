package com.example.attv0;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {

    TextView daytextview,datetextview,dayordertextview;
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
    SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
    String date1 = sdf1.format(new Date());
    String day1 = sdf2.format(new Date());



    EditText stid;
    TextView stabsen,stcls,stcode;
    EditText sthr;
    Button attsubbutton;
    private DBHelper dbHelper;


    String postID="";
    String postNAME="";
    String postCLASS="";
    String postSUBCODE="";
    String postTOTSTU="";


    ArrayList<Integer> stulist = new ArrayList<>();
    ArrayList<String> students = new ArrayList<>();
    boolean[] selectedstu;


    ArrayList<Integer> sublist = new ArrayList<>();
    ArrayList<String> subcode = new ArrayList<>();
    boolean[] selectedsub;

    ArrayList<Integer> subcodelist = new ArrayList<>();
    ArrayList<String> Subcode = new ArrayList<>();
    boolean[] selectedsubcode;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        daytextview=findViewById(R.id.day_textview);
        datetextview=findViewById(R.id.date_textview);
        dayordertextview=findViewById(R.id.dayorder_textview);
        dayordertextview.append("1");
        datetextview.setText(date1);
        daytextview.setText(day1);


        stid=findViewById(R.id.stafffid);
        stcls=findViewById(R.id.stafffclass);
        stcode=findViewById(R.id.stafffsubcode);
        sthr=findViewById(R.id.hour);
        stabsen=findViewById(R.id.absentees);






        attsubbutton=findViewById(R.id.attsubbtn);
        dbHelper = new DBHelper(MainActivity3.this);

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
        //Toast.makeText(MainActivity3.this,""+postID,Toast.LENGTH_SHORT).show();
        //Toast.makeText(MainActivity3.this,""+postNAME,Toast.LENGTH_SHORT).show();
        //Toast.makeText(MainActivity3.this,""+postCLASS,Toast.LENGTH_SHORT).show();
        //Toast.makeText(MainActivity3.this,""+postSUBCODE,Toast.LENGTH_SHORT).show();
        //Toast.makeText(MainActivity3.this,""+postTOTSTU,Toast.LENGTH_SHORT).show();
        stid.setText(postID);
        //stcls.setText(postCLASS);
        //stcode.setText(postSUBCODE);


        Cursor cursor=dbHelper.StaffStudents(postID);
        if (cursor.getCount() == 0) {
            Toast.makeText(MainActivity3.this, "No Students Registered...", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cursor.moveToFirst()) {
            do {
                String STUDENTSlist=cursor.getString(0);
                ArrayList<String> stulist = new ArrayList<>(Arrays.asList(STUDENTSlist.split(",")));
                for(String n : stulist){
                    students.add(n);

                }
            } while (cursor.moveToNext());
        }

        String[] strSTU = new String[students.size()];

        for (int i = 0; i < students.size(); i++) {
            strSTU[i] = students.get(i);
        }
        //for (String k : strSTU) {
            //Toast.makeText(MainActivity3.this, ""+k, Toast.LENGTH_SHORT).show();
       // }

        selectedstu = new boolean[strSTU.length];

        stabsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                builder.setTitle("SELECT STUDENTS");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(strSTU, selectedstu, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                        if (b) {
                            stulist.add(i);
                            Collections.sort(stulist);
                        } else {
                            stulist.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < stulist.size(); j++) {
                            stringBuilder.append(strSTU[stulist.get(j)]);
                            if (j != stulist.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                        stabsen.setText(stringBuilder.toString());
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


        Cursor cursor1=dbHelper.StaffClass(postID);
        if (cursor1.getCount() == 0) {
            Toast.makeText(MainActivity3.this, "No Class Registered...", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cursor1.moveToFirst()) {
            do {
                String Classlist=cursor1.getString(0);
                ArrayList<String> sublist = new ArrayList<>(Arrays.asList(Classlist.split(",")));
                for(String n : sublist){
                    subcode.add(n);

                }
            } while (cursor1.moveToNext());
        }

        String[] strCLASS = new String[subcode.size()];

        for (int i = 0; i < subcode.size(); i++) {
            strCLASS[i] = subcode.get(i);
        }
        //for (String k : strSTU) {
        //Toast.makeText(MainActivity3.this, ""+k, Toast.LENGTH_SHORT).show();
        // }

        selectedsub = new boolean[strCLASS.length];

        stcls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                builder.setTitle("SELECT CLASS");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(strCLASS, selectedsub, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                        if (b) {
                            sublist.add(i);
                            Collections.sort(sublist);
                        } else {
                            sublist.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < sublist.size(); j++) {
                            stringBuilder.append(strCLASS[sublist.get(j)]);
                            if (j != sublist.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                        stcls.setText(stringBuilder.toString());
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

        Cursor cursor2=dbHelper.StaffSubcode(postID);
        if (cursor2.getCount() == 0) {
            Toast.makeText(MainActivity3.this, "No Subjects Registered...", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cursor2.moveToFirst()) {
            do {
                String Subcodelist=cursor2.getString(0);
                ArrayList<String> subcodelist = new ArrayList<>(Arrays.asList(Subcodelist.split(",")));
                for(String n : subcodelist){
                    Subcode.add(n);

                }
            } while (cursor2.moveToNext());
        }

        String[] strSUBCODE = new String[Subcode.size()];

        for (int i = 0; i < Subcode.size(); i++) {
            strSUBCODE[i] = Subcode.get(i);
        }
        //for (String k : strSTU) {
        //Toast.makeText(MainActivity3.this, ""+k, Toast.LENGTH_SHORT).show();
        // }

        selectedsubcode = new boolean[strSUBCODE.length];

        stcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                builder.setTitle("SELECT SUBJECT CODE");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(strSUBCODE, selectedsubcode, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                        if (b) {
                            subcodelist.add(i);
                            Collections.sort(subcodelist);
                        } else {
                            subcodelist.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < subcodelist.size(); j++) {
                            stringBuilder.append(strSUBCODE[subcodelist.get(j)]);
                            if (j != subcodelist.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                        stcode.setText(stringBuilder.toString());
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










        attsubbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dayorder=dayordertextview.getText().toString();
                String hour=sthr.getText().toString();
                String subcode=stcode.getText().toString();
                String absentees=stabsen.getText().toString();

                if ((!dayorder.equals("") && dayorder != null) && (!hour.equals("") && hour !=null) && (!subcode.equals("") && subcode !=null) && (!absentees.equals("") && absentees !=null)){
                    validateattendance(dayorder,hour,subcode,absentees);





                } else {
                    Toast.makeText(MainActivity3.this, "Please Enter All The Data..!", Toast.LENGTH_SHORT).show();

                }


            }
        });





    }

    private void validateattendance(String DAYORDER,String HOUR,String SUBCODE,String ABSENTEES)
    {



        ArrayList<String> stulist = new ArrayList<>(Arrays.asList(ABSENTEES.split(",")));
        for(String n : stulist) {
            // students.add(n);

            dbHelper.addabsentee(postID,DAYORDER, HOUR, SUBCODE, n.trim());
            //Toast.makeText(MainActivity3.this, ""+n, Toast.LENGTH_SHORT).show();

        }

        stid.setText("");
        stcls.setText("");
        stcode.setText("");
        sthr.setText("");
        stabsen.setText("");
        Toast.makeText(MainActivity3.this,"ABSENTEES MARKED SUCCESSFLLY!!!",Toast.LENGTH_SHORT).show();
        Intent i=new Intent(MainActivity3.this,MainActivity2.class);
        i.putExtra("staffpostID",postID);
        i.putExtra("staffpostNAME",postNAME);
        i.putExtra("staffpostCLASS",postCLASS);
        i.putExtra("staffpostSUBCODE",postSUBCODE);
        i.putExtra("staffpostTOTSTU",postTOTSTU);
        startActivity(i);

    }




    }