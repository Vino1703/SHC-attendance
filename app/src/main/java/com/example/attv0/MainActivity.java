package com.example.attv0;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText staffId,staffPass;
    Button loginbtn;
    TextView daytextview,datetextview;
    private DBHelper dbHelper;

    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
    SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
    String date1 = sdf1.format(new Date());
    String day1 = sdf2.format(new Date());


    String uname="";
    String upass="";


    String postID="";
    String postNAME="";
    String postCLASS="";
    String postSUBCODE="";
    String postTOTSTU="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginbtn=findViewById(R.id.loginbtn);
        daytextview=findViewById(R.id.day_textview);
        datetextview=findViewById(R.id.date_textview);

        datetextview.setText(date1);
        daytextview.setText(day1);
        dbHelper = new DBHelper(MainActivity.this);

        staffId=findViewById(R.id.staffid);
        staffPass=findViewById(R.id.staffpass);




        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if ((!staffId.getText().equals("") && staffId.getText() !=null) && (!staffPass.getText().equals("") && staffPass !=null))
                {

                    Cursor cursor=dbHelper.getuserlogincrct(staffId.getText().toString());
                    if (cursor.getCount() == 0) {
                        Toast.makeText(MainActivity.this, "Username OR Password Incorrect...", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (cursor.moveToFirst()) {
                        do {
                            String STAFFID=cursor.getString(0);
                            String STAFFNAME = cursor.getString(1);
                            String STAFFCLASS = cursor.getString(2);
                            String STAFFSUBCODE=cursor.getString(3);
                            String STAFFSTUDENTS=cursor.getString(4);
                            String STAFFPASS=cursor.getString(5);

                            uname=STAFFID;
                            upass=STAFFPASS;

                            postID=STAFFID;
                            postNAME=STAFFNAME;
                            postCLASS=STAFFCLASS;
                            postSUBCODE=STAFFSUBCODE;
                            postTOTSTU=STAFFSTUDENTS;

                            //Toast.makeText(MainActivity.this,""+postID,Toast.LENGTH_SHORT).show();
                           // Toast.makeText(MainActivity.this,""+postNAME,Toast.LENGTH_SHORT).show();
                            //Toast.makeText(MainActivity.this,""+postCLASS,Toast.LENGTH_SHORT).show();
                            //Toast.makeText(MainActivity.this,""+postSUBCODE,Toast.LENGTH_SHORT).show();
                            //Toast.makeText(MainActivity.this,""+postTOTSTU,Toast.LENGTH_SHORT).show();


                        } while (cursor.moveToNext());
                    }

                    if((staffId.getText().toString().equals("ADMIN")) && (staffPass.getText().toString().equals("admin")))
                    {
                        staffId.setText("");
                        staffPass.setText("");
                        calladminpage();
                        }
                    else if((staffId.getText().toString().equals(uname)) && (staffPass.getText().toString().equals(upass)))
                    {
                        staffId.setText("");
                        staffPass.setText("");
                        callstaffpage();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Invalid Login Details...", Toast.LENGTH_SHORT).show();
                    }



                }
                else {
                    Toast.makeText(MainActivity.this, "Please Enter All The Data..!", Toast.LENGTH_SHORT).show();

                }


            }

        });

    }

    public void calladminpage()
    {
        Intent i = new Intent(MainActivity.this, MainActivity5.class);

        startActivity(i);
    }

    public void callstaffpage()
    {
        Intent i = new Intent(MainActivity.this, MainActivity2.class);
        i.putExtra("staffpostID",postID);
        i.putExtra("staffpostNAME",postNAME);
        i.putExtra("staffpostCLASS",postCLASS);
        i.putExtra("staffpostSUBCODE",postSUBCODE);
        i.putExtra("staffpostTOTSTU",postTOTSTU);
        startActivity(i);
    }
}