package com.example.attv0;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity7 extends AppCompatActivity {

    AutoCompleteTextView actv;
    TextView Id,Name,Clas,Subcode,Stu;
    Button getDATA,updateDATA;

    String[] IDS={"T101","T102","T103","T104","T105","T106","T107","T108"};
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);


        actv = findViewById(R.id.autoCompleteTextView1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,IDS);
        actv.setAdapter(adapter);
        actv.setThreshold(1);


        Id=findViewById(R.id.iddb);
        Name=findViewById(R.id.namedb);
        Clas=findViewById(R.id.classdb);
        Subcode=findViewById(R.id.subcodedb);
        Stu=findViewById(R.id.studentsdb);


        getDATA=findViewById(R.id.getstaffdata);
        dbHelper=new DBHelper(this);


        getDATA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!actv.getText().equals(""))
                {

                    Cursor cursor=dbHelper.getStaffData(actv.getText().toString());
                    if (cursor.getCount() == 0) {
                        Toast.makeText(MainActivity7.this, ""+actv.getText().toString()+",Not yet Registered...", Toast.LENGTH_SHORT).show();
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

                            Id.setText(STAFFID);
                            Name.setText(STAFFNAME);
                            Clas.setText(STAFFCLASS);
                            Subcode.setText(STAFFSUBCODE);
                            Stu.setText(STAFFSTUDENTS);


                        } while (cursor.moveToNext());
                    }
                }}
        });


        updateDATA=findViewById(R.id.updatestaff);

        updateDATA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String staffid=Id.getText().toString();
                String staffname=Name.getText().toString();
                String staffclass=Clas.getText().toString();
                String staffsubcode=Subcode.getText().toString();
                String staffstu=Stu.getText().toString();

                dbHelper.updatestaffdetails(staffid, staffname, staffclass, staffsubcode, staffstu);
                Id.setText("");
                Name.setText("");
                Clas.setText("");
                Subcode.setText("");
                Stu.setText("");
                Toast.makeText(MainActivity7.this,""+staffid+",UPDATED SUCCESSFULLY!!!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}