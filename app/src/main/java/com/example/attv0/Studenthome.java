package com.example.attv0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Studenthome extends AppCompatActivity {

    Button home,profile,attendance,moodle,logout;

    String postID="";
    String postNAME="";
    String postCLASS="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studenthome);

        Intent intent1=getIntent();
        String id1= intent1.getStringExtra("staffpostID");
        postID=id1;
        String name1= intent1.getStringExtra("staffpostNAME");
        postNAME=name1;
        String classs1= intent1.getStringExtra("staffpostCLASS");
        postCLASS=classs1;

        attendance=findViewById(R.id.stuattendance);

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Studenthome.this,Studentatt.class);
                i.putExtra("staffpostID",postID);
                i.putExtra("staffpostNAME",postNAME);
                i.putExtra("staffpostCLASS",postCLASS);
                startActivity(i);
            }
        });

        logout=findViewById(R.id.studentlogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Studenthome.this,Homemain.class);
                startActivity(i);

            }
        });


    }
}