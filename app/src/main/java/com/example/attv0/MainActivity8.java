package com.example.attv0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity8 extends AppCompatActivity {

    AutoCompleteTextView actv;
    TextView Id,Name,Clas,Subcode,Stu;
    Button getDATA,deleteDATA;
    String[] IDS={"T101","T102","T103","T104","T105","T106","T107","T108"};
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        actv = findViewById(R.id.autoCompleteTextView11);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,IDS);
        actv.setAdapter(adapter);
        actv.setThreshold(1);


        Id=findViewById(R.id.iddbb);
        Name=findViewById(R.id.namedbb);
        Clas=findViewById(R.id.classdbb);
        Subcode=findViewById(R.id.subcodedbb);
        Stu=findViewById(R.id.studentsdbb);


        getDATA=findViewById(R.id.getstaffdataa);
        dbHelper=new DBHelper(this);

        getDATA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!actv.getText().equals(""))
                {

                    Cursor cursor=dbHelper.getStaffData(actv.getText().toString());
                    if (cursor.getCount() == 0) {
                        Toast.makeText(MainActivity8.this, ""+actv.getText().toString()+",Not yet Registered...", Toast.LENGTH_SHORT).show();
                        Id.setText("");
                        Name.setText("");
                        Clas.setText("");
                        Subcode.setText("");
                        Stu.setText("");
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

                            Id.setText("ID : "+STAFFID);
                            Name.setText("NAME : "+STAFFNAME);
                            Clas.setText("CLASS : "+STAFFCLASS);
                            Subcode.setText("SUBJECTS : "+STAFFSUBCODE);
                            Stu.setText("STUDENTS : "+STAFFSTUDENTS);
                            deleteDATA.setVisibility(View.VISIBLE);


                        } while (cursor.moveToNext());
                    }
                }}
        });


        deleteDATA=findViewById(R.id.deletedata);

        deleteDATA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String TID = actv.getText().toString();

                //Toast.makeText(MainActivity8.this,""+TID,Toast.LENGTH_SHORT).show();

                dbHelper.deleteData(TID);


                Id.setText("");
                Name.setText("");
                Clas.setText("");
                Subcode.setText("");
                Stu.setText("");

                deleteDATA.setVisibility(View.INVISIBLE);

                Toast.makeText(MainActivity8.this,"STAFF "+TID+" Deleted Successfully!!!",Toast.LENGTH_SHORT).show();
            }
        });



    }
}