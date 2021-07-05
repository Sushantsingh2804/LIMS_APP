package com.example.labinfomanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Attendance extends AppCompatActivity {
    int id;
    String Active_User;
    RecyclerView AttendanceView;
    ArrayList<modelStudent> studentHolder=new ArrayList<modelStudent>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        id=getIntent().getIntExtra("labid",-1);
        Active_User = getIntent().getStringExtra("Active_User");
        AttendanceView=findViewById(R.id.AttendanceView);
        AttendanceView.setLayoutManager(new LinearLayoutManager(this));
        Cursor cursor = new DbManager(this).readStudentData(String.valueOf(id));
        while (cursor.moveToNext()) {
            modelStudent obj = new modelStudent(cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getInt(0));
            studentHolder.add(obj);
        }
        AttendanceAdapter adapter = new AttendanceAdapter(studentHolder,getApplicationContext());
        AttendanceView.setAdapter(adapter);
    }
    public void onClickSubmit(View view){
        DbManager db=new DbManager(this);
        db.SubmitAttendance(id);
        Intent intent = new Intent(Attendance.this,User_lab_info.class);
        intent.putExtra("labid", id);
        intent.putExtra("Active_User", Active_User);
        startActivity(intent);
    }
    public void onBackbtnClick(View view){
        Intent intent = new Intent(Attendance.this,User_lab_info.class);
        intent.putExtra("Active_User", Active_User);
        intent.putExtra("labid", id);
        startActivity(intent);
    }

}