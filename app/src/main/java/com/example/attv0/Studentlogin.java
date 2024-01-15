package com.example.attv0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Studentlogin extends AppCompatActivity {
    EditText staffId,staffPass;
    Button loginbtn;
    TextView daytextview,datetextview;
    private DBHelper dbHelper;

    String postID="";
    String postNAME="";
    String postCLASS="";

    String uname="";
    String upass="";

    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
    SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
    String date1 = sdf1.format(new Date());
    String day1 = sdf2.format(new Date());



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);


        staffId=findViewById(R.id.studentid);
        staffPass=findViewById(R.id.studentpass);

        loginbtn=findViewById(R.id.loginbtn);
        daytextview=findViewById(R.id.day_textview);
        datetextview=findViewById(R.id.date_textview);

        datetextview.setText(date1);
        daytextview.setText(day1);
        dbHelper = new DBHelper(Studentlogin.this);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if ((!staffId.getText().equals("") && staffId.getText() !=null) && (!staffPass.getText().equals("") && staffPass !=null))
                {

                    Cursor cursor=dbHelper.getstulogincrct(staffId.getText().toString());
                    if (cursor.getCount() == 0) {
                        Toast.makeText(Studentlogin.this, "Username OR Password Incorrect...", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (cursor.moveToFirst()) {
                        do {
                            String STUDENTID=cursor.getString(0);
                            String STUDENTNAME = cursor.getString(1);
                            String STUDENTCLASS = cursor.getString(2);
                            String STUDENTPASS=cursor.getString(3);

                            uname=STUDENTID;
                            upass=STUDENTPASS;

                            postID=STUDENTID;
                            postNAME=STUDENTNAME;
                            postCLASS=STUDENTCLASS;

                        } while (cursor.moveToNext());
                    }

                    if((staffId.getText().toString().equals(uname)) && (staffPass.getText().toString().equals(upass)))
                    {
                        staffId.setText("");
                        staffPass.setText("");
                        callstudentpage();
                    }
                    else
                    {
                        Toast.makeText(Studentlogin.this, "Invalid Login Details...", Toast.LENGTH_SHORT).show();
                    }



                }
                else {
                    Toast.makeText(Studentlogin.this, "Please Enter All The Data..!", Toast.LENGTH_SHORT).show();

                }


            }

        });

    }


    public void callstudentpage()
    {
        Intent i = new Intent(Studentlogin.this, Studenthome.class);
        i.putExtra("staffpostID",postID);
        i.putExtra("staffpostNAME",postNAME);
        i.putExtra("staffpostCLASS",postCLASS);
        startActivity(i);
    }
}

