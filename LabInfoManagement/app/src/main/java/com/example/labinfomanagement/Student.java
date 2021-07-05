package com.example.labinfomanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Student extends AppCompatActivity {
    int id;
    RecyclerView studentView;
    String Active_User;
    ArrayList<modelStudent> studentHolder=new ArrayList<modelStudent>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        id=getIntent().getIntExtra("labid",-1);
        Active_User = getIntent().getStringExtra("Active_User");
        studentView=findViewById(R.id.studentView);
        studentView.setLayoutManager(new LinearLayoutManager(this));
        Cursor cursor = new DbManager(this).readStudentData(String.valueOf(id));
        while (cursor.moveToNext()) {
            modelStudent obj = new modelStudent(cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getInt(0));
            studentHolder.add(obj);
        }
        studentAdapter adapter = new studentAdapter(studentHolder);
        studentView.setAdapter(adapter);
    }
    public void onClickAdd(View view){
        Intent intent = new Intent(Student.this,Add_Student.class);
        intent.putExtra("labid", id);
        intent.putExtra("Active_User", Active_User);
        startActivity(intent);
    }
    public void onBackstudent(View view){
        Intent intent = new Intent(Student.this,User_lab_info.class);
        intent.putExtra("Active_User", Active_User);
        intent.putExtra("labid", id);
        startActivity(intent);
    }

}