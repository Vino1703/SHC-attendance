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

public class MainActivity9 extends AppCompatActivity {

    TextView GetAbsente, DisplayAbsente;
    private DBHelper dbHelper;
    ArrayList<String> ABookings = new ArrayList<String>();

    Integer rowid=0;

    String postID="";
    String postNAME="";
    String postCLASS="";
    String postSUBCODE="";
    String postTOTSTU="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);



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

        DisplayAbsente = findViewById(R.id.disattend);
        GetAbsente = findViewById(R.id.attget);
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(800);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        GetAbsente.startAnimation(anim);



        GetAbsente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor = dbHelper.getAbsentDetail(postID);

                GetAbsente.setVisibility(View.GONE);

                if (cursor.getCount() == 0) {
                    Toast.makeText(MainActivity9.this, "No Absentees Registered...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (cursor.moveToFirst()) {
                    do {
                        String Aday=cursor.getString(0);
                        String Ahour=cursor.getString(1);
                        String Asubjcode=cursor.getString(2);
                        String Aabsentee=cursor.getString(3);

                        Integer ROW = rowid+=1;
                        String ROWS = ROW.toString();
                        ABookings.add("S.No "+ROWS+" - "+Aday+" - HOUR "+Ahour+" - SUBJECT CODE "+Asubjcode+" - ABSENTEES "+Aabsentee+"");

                        // Toast.makeText(MainActivity14.this,""+Bookings,Toast.LENGTH_SHORT).show();

                        //for (String details : Bookings)
                        //{
                        // DisplayBook.setText(details);
                        //}

                        DisplayAbsente.setText("");

                        for (int i=0;i < ABookings.size();i++)
                        {
                            String Books = ABookings.get(i);
                            DisplayAbsente.append("\n \n"+Books);

                            //Toast.makeText(MainActivity14.this,""+Bookings.get(i),Toast.LENGTH_SHORT).show();
                        }



                    } while (cursor.moveToNext());
                }

            }
        });

    }
}