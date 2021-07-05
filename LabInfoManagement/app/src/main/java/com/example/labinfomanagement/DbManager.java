package com.example.labinfomanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbManager extends SQLiteOpenHelper {
    private static final String dbname="lab.db";

    public DbManager(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry_inst="create table IF NOT EXISTS instructor" +
                "(instructor_id INTEGER PRIMARY KEY autoincrement," +
                "instructor_name TEXT NOT NULL," +
                "instructor_email TEXT NOT NULL UNIQUE," +
                "instructor_password TEXT NOT NULL," +
                "instructor_phone_no CHAR(10) UNIQUE);";

        String qry_lab="create TABLE IF NOT EXISTS lab" +
                "(lab_id INTEGER PRIMARY KEY autoincrement," +
                "lab_name TEXT NOT NULL, " +
                "lab_slot TEXT NOT NULL," +
                "lab_classes_count integer not NULL," +
                "instructor_id INTEGER," +
                "FOREIGN KEY (instructor_id) " +
                "REFERENCES instructor (instructor_id) " +
                "ON UPDATE CASCADE ON DELETE CASCADE);";

        String qry_student="CREATE TABLE IF NOT EXISTS student" +
                "(student_id INTEGER PRIMARY KEY autoincrement," +
                "student_usn text  NOT NULL," +
                "student_name TEXT NOT NULL," +
                "student_attendance integer NOT NULL," +
                "lab_id INTEGER,FOREIGN KEY (lab_id)" +
                "REFERENCES lab (lab_id) ON UPDATE CASCADE ON DELETE CASCADE);";
        db.execSQL(qry_inst);
        db.execSQL(qry_lab);
        db.execSQL(qry_student);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
    }

    public boolean addInstructor(String p1,String p2, String p3,String p4){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("instructor_name",p1);
        cv.put("instructor_email",p2);
        cv.put("instructor_password",p3);
        cv.put("instructor_phone_no",p4);
        long res=db.insert("instructor",null,cv);
        return res != -1;

    }
    public boolean findInstructor(String p1,String p2){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM instructor WHERE instructor_email = ? ", new String[]{p1});
        if (cursor.moveToFirst()){
            String password;
            password = cursor.getString(3);
            if (p2.equals(password)){
                cursor.close();
                return true;
            }
            else {
                cursor.close();
                return false;
            }

        }
        else {
            cursor.close();
            return false;
        }

    }
    public boolean addLab(String p1,String p2,String p3){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        int id=getUserId(p3);
        cv.put("lab_name",p1);
        cv.put("lab_slot",p2);
        cv.put("lab_classes_count",0);
        cv.put("instructor_id",id);
        long res=db.insert("lab",null,cv);
        return res != -1;
    }
    public Cursor readLabData(String p1){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT lab_id,lab_name,lab_slot,lab_classes_count FROM instructor,lab WHERE  instructor.instructor_email = ? AND lab.instructor_id=instructor.instructor_id", new String[]{p1});
        return cursor;
    }
    public Integer getUserId(String p1){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM instructor WHERE instructor_email = ? ", new String[]{p1});
        if (cursor.moveToFirst()){
            int id=cursor.getInt(0);
            return id;
        }
        return -1;
    }
    public ArrayList<String> getLab(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        ArrayList<String> lab=new ArrayList<String>();
        Cursor cursor=db.rawQuery("SELECT * FROM lab WHERE lab_id = ? ", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()){
            lab.add(cursor.getString(1));
            lab.add(cursor.getString(2));
            lab.add(cursor.getString(3));

        }
        return lab;
    }
    public Cursor readStudentData(String p1){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT student_id,student_usn,student_name,student_attendance FROM lab,student WHERE  lab.lab_id = ? AND lab.lab_id=student.lab_id", new String[]{p1});
        return cursor;
    }
    public boolean addStudent(String p1,String p2,int id){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("student_usn",p1);
        cv.put("student_name",p2);
        cv.put("student_attendance",0);
        cv.put("lab_id",id);
        long res=db.insert("student",null,cv);
        return res != -1;
    }
    public void takeAttendance(int p1, int p2){
        SQLiteDatabase db=this.getWritableDatabase();
        String qry="UPDATE student SET student_attendance="+"'"+p2+"' "+"WHERE student_id="+"'"+p1+"'"+";";
        db.execSQL(qry);
    }
    public void SubmitAttendance(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT lab_classes_count FROM lab WHERE lab_id = ? ", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()){
            int class_count=cursor.getInt(0);
            class_count+=1;
            String qry="UPDATE lab SET lab_classes_count="+"'"+class_count+"' "+"WHERE lab_id ="+"'"+id+"' ";
            db.execSQL(qry);

        }
    }
    public void deleteLab(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        String qry="DELETE FROM lab WHERE lab_id="+"'"+id+"' ";
        db.execSQL(qry);
    }
}
