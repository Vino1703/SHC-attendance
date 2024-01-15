package com.example.attv0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Studentatt extends AppCompatActivity {

    TextView GetStuAbsente, DisplayStuAbsente;
    private DBHelper dbHelper;
    ArrayList<String> Abs = new ArrayList<String>();

    Integer rowid=0;

    String postID="";
    String postNAME="";
    String postCLASS="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentatt);
        dbHelper = new DBHelper(this);


        Intent intent1=getIntent();
        String id1= intent1.getStringExtra("staffpostID");
        postID=id1;
        String name1= intent1.getStringExtra("staffpostNAME");
        postNAME=name1;
        String classs1= intent1.getStringExtra("staffpostCLASS");
        postCLASS=classs1;

        DisplayStuAbsente =  findViewById(R.id.studisattend);
        GetStuAbsente = findViewById(R.id.stuattget);
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(800);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        GetStuAbsente.startAnimation(anim);



        GetStuAbsente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor = dbHelper.getStuAbsentDetail(postID);

                GetStuAbsente.setVisibility(View.GONE);

                if (cursor.getCount() == 0) {
                    Toast.makeText(Studentatt.this, "No Absentees Registered...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (cursor.moveToFirst()) {
                    do {
                        String Aday=cursor.getString(0);
                        String Ahour=cursor.getString(1);
                        String Asubjcode=cursor.getString(2);

                        Integer ROW = rowid+=1;
                        String ROWS = ROW.toString();
                        Abs.add("S.No "+ROWS+" - "+Aday+" - HOUR "+Ahour+" - SUBJECT CODE "+Asubjcode);

                        // Toast.makeText(MainActivity14.this,""+Bookings,Toast.LENGTH_SHORT).show();

                        //for (String details : Bookings)
                        //{
                        // DisplayBook.setText(details);
                        //}

                        DisplayStuAbsente.setText("");

                        for (int i=0;i < Abs.size();i++)
                        {
                            String Books = Abs.get(i);
                            DisplayStuAbsente.append("\n \n"+Books);

                            //Toast.makeText(MainActivity14.this,""+Bookings.get(i),Toast.LENGTH_SHORT).show();
                        }



                    } while (cursor.moveToNext());
                }

            }
        });

    }
}