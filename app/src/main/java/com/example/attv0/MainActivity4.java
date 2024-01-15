package com.example.attv0;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity4 extends AppCompatActivity {

    TextView alreadyuser;
    Button signin;
    EditText Tid,Tname,Tpass,Tcpass;
    TextView Tclass,Tscode,Tstu;
    private DBHelper dbHelper;

    ArrayList<Integer> deptlist = new ArrayList<>();
    String[] depts = {"MCA", "MBA", "BBA", "BCA", "B.Sc Computer Science", "M.Sc Computer Science"};
    boolean[] selecteddept;

    ArrayList<Integer> codelist = new ArrayList<>();
    String[] courses = {"MCA360T", "MCA361T", "MCA362T", "MCA363T", "MCA364T", "MCA365T"};
    boolean[] selectedcourse;

    ArrayList<Integer> stulist = new ArrayList<>();
    String[] students = {"BP211001", "BP211002", "BP211003", "BP211004", "BP211005", "BP211006",
            "BP211007", "BP211008", "BP211009", "BP211010", "BP211011", "BP211012",
            "BP211013", "BP211014", "BP211015", "BP211016", "BP211017", "BP211018",
            "BP211019", "BP211020", "BP211021", "BP211022", "BP211023", "BP211024",
            "BP211025", "BP211026", "BP211027", "BP211028", "BP211029", "BP211030",
            "BP211031", "BP211032", "BP211033", "BP211034", "BP211035", "BP211036",
            "BP211037", "BP211038", "BP211039", "BP211040", "BP211041", "BP211042",
            "BP211043", "BP211044", "BP211045", "BP211046", "BP211047", "BP211048",
            "BP211049", "BP211050", "BP211051", "BP211052", "BP211053", "BP211054",
            "BP211055", "BP211056", "BP211057", "BP211058", "BP211059", "BP211060",};
    boolean[] selectedstu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Tid=findViewById(R.id.teachid);
        Tname=findViewById(R.id.teachname);
        Tclass=findViewById(R.id.teachclass);
        Tscode=findViewById(R.id.teachsubcode);
        Tstu=findViewById(R.id.teachstudents);
        Tpass=findViewById(R.id.teachpassword);
        Tcpass=findViewById(R.id.cteachpassword);
        signin=findViewById(R.id.signbtn);
        dbHelper = new DBHelper(MainActivity4.this);

        selecteddept = new boolean[depts.length];

        Tclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity4.this);
                builder.setTitle("SELECT DEPARTMENT");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(depts, selecteddept, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                        if (b) {
                            deptlist.add(i);
                            Collections.sort(deptlist);
                        } else {
                            deptlist.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < deptlist.size(); j++) {
                            stringBuilder.append(depts[deptlist.get(j)]);
                            if (j != deptlist.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                        Tclass.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int j = 0; j < selecteddept.length; j++) {
                            selecteddept[j] = false;
                            deptlist.clear();
                            Tclass.setText("");
                        }
                    }
                });
                builder.show();
            }
        });


            selectedcourse = new boolean[courses.length];

            Tscode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity4.this);
                    builder.setTitle("SELECT SUBJECT CODES");
                    builder.setCancelable(false);
                    builder.setMultiChoiceItems(courses, selectedcourse, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                            if (b) {
                                codelist.add(i);
                                Collections.sort(codelist);
                            } else {
                                codelist.remove(Integer.valueOf(i));
                            }
                        }
                    });

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int j = 0; j < codelist.size(); j++) {
                                stringBuilder.append(courses[codelist.get(j)]);
                                if (j != codelist.size() - 1) {
                                    stringBuilder.append(", ");
                                }
                            }
                            Tscode.setText(stringBuilder.toString());
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            for (int j = 0; j < selectedcourse.length; j++) {
                                selectedcourse[j] = false;
                                codelist.clear();
                                Tscode.setText("");
                            }
                        }
                    });
                    builder.show();
                }
            });



        selectedstu = new boolean[students.length];

        Tstu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity4.this);
                builder.setTitle("SELECT STUDENTS");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(students, selectedstu, new DialogInterface.OnMultiChoiceClickListener() {
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
                            stringBuilder.append(students[stulist.get(j)]);
                            if (j != stulist.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                        Tstu.setText(stringBuilder.toString());
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



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tid=Tid.getText().toString();
                String tname=Tname.getText().toString();
                String tclass=Tclass.getText().toString();
                String tscode=Tscode.getText().toString();
                String tstu=Tstu.getText().toString();
                String tpass=Tpass.getText().toString();
                String tcpass=Tcpass.getText().toString();

                if((!tid.equals("") && tid != null) && (!tname.equals("") && tname != null) && (!tclass.equals("") && tclass != null) && (!tscode.equals("") && tscode != null) && (!tstu.equals("") && tstu != null) && (!tpass.equals("") && tpass != null) && (!tcpass.equals("") && tcpass != null))
                {
                    validatesignin(tid,tname,tclass,tscode,tstu,tpass,tcpass);
                }else {
                    Toast.makeText(MainActivity4.this, "Please Enter All The Data..!", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

    private void validatesignin(String staffid,String staffname,String staffclass,String staffsubcode,String staffstu,String staffpass,String staffcpass){

        if(staffpass.equals(staffcpass))
        {
            if(validatepassword(staffpass))
            {
                if(dbHelper.checkuser(staffid)){
                    dbHelper.addnewuser(staffid, staffname, staffclass, staffsubcode, staffstu, staffpass);
                   Tid.setText("");
                    Tname.setText("");
                    Tclass.setText("");
                   Tscode.setText("");
                    Tstu.setText("");
                    Tpass.setText("");
                    Tcpass.setText("");
                    Toast.makeText(MainActivity4.this,""+staffid+" , "+staffname+" Added Successfully!!!",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(MainActivity4.this,MainActivity5.class);
                    startActivity(i);

                }}
            else {
                Toast.makeText(MainActivity4.this, "Password must be greater than 1 character..!", Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
            Toast.makeText(MainActivity4.this, "Password doesn't matches..!", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean validatepassword(String Password)
    {
        if(Password.length() < 1){
            return false;

        }
        return true;
    }

}