package com.example.attv0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity5 extends AppCompatActivity {

    TextView daytextview,datetextview;
    Button AddStaff,ViewStaff,AdminLogout,EditStaff,DeleteStaff;

    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
    SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
    String date1 = sdf1.format(new Date());
    String day1 = sdf2.format(new Date());




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);


        daytextview=findViewById(R.id.day_textview);
        datetextview=findViewById(R.id.date_textview);

        datetextview.setText(date1);
        daytextview.setText(day1);


        AddStaff=findViewById(R.id.addstaff);
        ViewStaff=findViewById(R.id.viewstaff);




        AddStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity5.this,MainActivity4.class);
                startActivity(i);
            }
        });

        ViewStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity5.this,MainActivity6.class);
                startActivity(i);
            }
        });

        AdminLogout=findViewById(R.id.adminlogout);




        AdminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity5.this,MainActivity.class);
                startActivity(i);
            }
        });


        EditStaff=findViewById(R.id.editstaff);

        EditStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity5.this,MainActivity7.class);
                startActivity(i);

            }
        });


        DeleteStaff=findViewById(R.id.deletestaff);

        DeleteStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity5.this,MainActivity8.class);
                startActivity(i);

            }
        });






    }
}