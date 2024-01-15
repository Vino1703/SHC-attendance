package com.example.attv0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homemain extends AppCompatActivity {

    Button stu,staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homemain);

        stu=findViewById(R.id.stulogin);

        stu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Homemain.this,Studentlogin.class);
                startActivity(i);
            }
        });

        staff=findViewById(R.id.stafflogin);

        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Homemain.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
}