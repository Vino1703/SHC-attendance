package com.example.attv0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {

    TextView daytextview,datetextview,dayordertextview;
    ImageView attentry,viewentry,deleteentry,Slogout;

    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
    SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
    String date1 = sdf1.format(new Date());
    String day1 = sdf2.format(new Date());



    String postID="";
    String postNAME="";
    String postCLASS="";
    String postSUBCODE="";
    String postTOTSTU="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        attentry=findViewById(R.id.attentry);
        daytextview=findViewById(R.id.day_textview);
        datetextview=findViewById(R.id.date_textview);
        dayordertextview=findViewById(R.id.dayorder_textview);
        dayordertextview.append("1");
        datetextview.setText(date1);
        daytextview.setText(day1);


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

        Intent intent1=getIntent();
        String id1= intent1.getStringExtra("staffpostID");
        postID=id1;
        String name1= intent1.getStringExtra("staffpostNAME");
        postNAME=name1;
        String classs1= intent1.getStringExtra("staffpostCLASS");
        postCLASS=classs1;
        String subcod1= intent1.getStringExtra("staffpostSUBCODE");
        postSUBCODE=subcod1;
        String totstu1= intent1.getStringExtra("staffpostTOTSTU");
        postTOTSTU=totstu;

        Intent intent2=getIntent();
        String id2= intent2.getStringExtra("staffpostID");
        postID=id2;
        String name2= intent2.getStringExtra("staffpostNAME");
        postNAME=name2;
        String classs2= intent2.getStringExtra("staffpostCLASS");
        postCLASS=classs2;
        String subcod2= intent2.getStringExtra("staffpostSUBCODE");
        postSUBCODE=subcod2;
        String totstu2= intent2.getStringExtra("staffpostTOTSTU");
        postTOTSTU=totstu;



        //Toast.makeText(MainActivity2.this,""+postID,Toast.LENGTH_SHORT).show();
       // Toast.makeText(MainActivity2.this,""+postNAME,Toast.LENGTH_SHORT).show();
        //Toast.makeText(MainActivity2.this,""+postCLASS,Toast.LENGTH_SHORT).show();
        //Toast.makeText(MainActivity2.this,""+postSUBCODE,Toast.LENGTH_SHORT).show();
       // Toast.makeText(MainActivity2.this,""+postTOTSTU,Toast.LENGTH_SHORT).show();


        attentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity2.this,MainActivity3.class);
                i.putExtra("staffpostID",postID);
                i.putExtra("staffpostNAME",postNAME);
                i.putExtra("staffpostCLASS",postCLASS);
                i.putExtra("staffpostSUBCODE",postSUBCODE);
                i.putExtra("staffpostTOTSTU",postTOTSTU);
                startActivity(i);
            }
        });


        Slogout = findViewById(R.id.logot);
        Slogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this,Homemain.class);
                startActivity(i);
            }
        });


        viewentry = findViewById(R.id.viewentry);
        viewentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this,MainActivity9.class);
                i.putExtra("staffpostID",postID);
                i.putExtra("staffpostNAME",postNAME);
                i.putExtra("staffpostCLASS",postCLASS);
                i.putExtra("staffpostSUBCODE",postSUBCODE);
                i.putExtra("staffpostTOTSTU",postTOTSTU);
                startActivity(i);
            }
        });


        deleteentry = findViewById(R.id.deleteentry);
        deleteentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this,MainActivity10.class);
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
