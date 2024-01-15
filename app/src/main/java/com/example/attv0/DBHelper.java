package com.example.attv0;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;

    public DBHelper(@Nullable Context context) {
        super(context, "attendance.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL("CREATE TABLE \"staffdetails\" (\n" +
                "\t\"staffid\"\tTEXT,\n" +
                "\t\"staffname\"\tTEXT,\n" +
                "\t\"staffclass\"\tTEXT,\n" +
                "\t\"staffsubjcode\"\tTEXT,\n" +
                "\t\"staffstudents\"\tTEXT,\n" +
                "\t\"staffpass\"\tTEXT\n" +
                ");");

        DB.execSQL("CREATE TABLE \"studentdetails\" (\n" +
                "\t\"studentid\"\tTEXT,\n" +
                "\t\"studentname\"\tTEXT,\n" +
                "\t\"studentclass\"\tTEXT,\n" +
                "\t\"studentpass\"\tTEXT\n" +
                ");");

        DB.execSQL("CREATE TABLE \"absentdetails\" (\n" +
                "\t\"staffid\"\tTEXT,\n" +
                "\t\"dayorder\"\tTEXT,\n" +
                "\t\"hour\"\tTEXT,\n" +
                "\t\"subjcode\"\tTEXT,\n" +
                "\t\"absentee\"\tTEXT\n" +
                ");");

       DB.execSQL("INSERT INTO staffdetails (staffid,staffname,staffclass,staffsubjcode,staffstudents,staffpass) VALUES (\n" +
               "                'ADMIN',\n" +
               "                'KOMATHI',\n" +
               "                'MCA',\n" +
               "                'ADMINISTRATOR',\n" +
               "                'NULL',\n" +
               "                'admin'\n" +
               "        );");

        DB.execSQL("INSERT INTO studentdetails (studentid,studentname,studentclass,studentpass) VALUES (\n" +
                "                'BP211043',\n" +
                "                'THINESH RAAJAN S M',\n" +
                "                'MCA',\n" +
                "                'BP211043'\n" +
                "        );");
        DB.execSQL("INSERT INTO studentdetails (studentid,studentname,studentclass,studentpass) VALUES (\n" +
                "                'BP211025',\n" +
                "                'KOMATHI',\n" +
                "                'MCA',\n" +
                "                'BP211025'\n" +
                "        );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists staffdetails");
        db.execSQL("drop Table if exists absentdetails");
        db.execSQL("drop Table if exists studentdetails");
    }

    public boolean checkuser(String StaffId)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM staffdetails WHERE staffid = ?", new String[] {StaffId});
        if (c.moveToFirst()){
            do{
                if(c.getCount() > 0 )
                {
                    Toast.makeText(context, "STAFF ID Already Exists...", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }while(c.moveToNext());
        }
        c.close();
        return true;


    }


    public void addnewuser(String StaffId, String StaffName, String StaffClass,String StaffSubCode,String StaffStu,String StaffPass) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("staffid", StaffId);
        values.put("staffname", StaffName);
        values.put("staffclass", StaffClass);
        values.put("staffsubjcode", StaffSubCode);
        values.put("staffstudents", StaffStu);
        values.put("staffpass",StaffPass);
        db.insert("staffdetails", null, values);
        db.close();
    }


    public Cursor getuserlogincrct(String StaffID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM staffdetails WHERE staffid = '"+StaffID+"'", null);
        return cursor;
    }

    public void addabsentee(String StaffID,String dorder, String hour, String subcode,String absentees) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("staffid", StaffID);
        values.put("dayorder", dorder);
        values.put("hour", hour);
        values.put("subjcode", subcode);
        values.put("absentee", absentees);
        db.insert("absentdetails", null, values);
        db.close();
    }

    public Cursor getStaffData(String iD) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM staffdetails WHERE staffid = '"+iD+"'", null);
        return cursor;
    }

    public void updatestaffdetails(String Staffid, String Staffname, String Staffclass, String  Staffsubcode, String Staffstu)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("staffid", Staffid);
        values.put("staffname", Staffname);
        values.put("staffclass", Staffclass);
        values.put("staffsubjcode", Staffsubcode);
        values.put("staffstudents", Staffstu);
        db.update("staffdetails",values,"staffid = ?",new String[] {Staffid});
    }

    public void deleteData(String SID)
    {
        SQLiteDatabase db = this.getWritableDatabase();

       db.delete("staffdetails","staffid = ?",new String[] {SID});
    }

    public Cursor getstulogincrct(String StaffID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM studentdetails WHERE studentid = '"+StaffID+"'", null);
        return cursor;
    }

    public Cursor StaffStudents(String StaffID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT staffstudents FROM staffdetails WHERE staffid = '"+StaffID+"'", null);
        return cursor;
    }

    public Cursor StaffClass(String StaffID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT staffclass FROM staffdetails WHERE staffid = '"+StaffID+"'", null);
        return cursor;
    }

    public Cursor StaffSubcode(String StaffID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT staffsubjcode FROM staffdetails WHERE staffid = '"+StaffID+"'", null);
        return cursor;
    }

    public Cursor getAbsentDetail(String StaffID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT dayorder,hour,subjcode,absentee FROM absentdetails WHERE staffid = '"+StaffID+"'", null);
        return cursor;
    }

    public Cursor getAbsentDetailDelete(String SubCode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT absentee FROM absentdetails WHERE subjcode = '"+SubCode+"'", null);
        return cursor;
    }

    public void delAbsentDetail(String SC, String SID, String AB)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM absentdetails WHERE subjcode = '"+SC+"' AND staffid = '"+SID+"' and absentee = '"+AB+"';");
    }


    public Cursor getStuAbsentDetail(String StuID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT dayorder,hour,subjcode FROM absentdetails WHERE absentee = '"+StuID+"'", null);
        return cursor;
    }



}
