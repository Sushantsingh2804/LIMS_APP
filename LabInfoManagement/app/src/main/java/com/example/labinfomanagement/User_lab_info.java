package com.example.labinfomanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class User_lab_info extends AppCompatActivity {
    int id;
    String Active_User;
    ArrayList<String> lab=new ArrayList<String>();
    TextView name,slot,class_count,lab_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_lab_info);
        id=getIntent().getIntExtra("labid",-1);
        Active_User = getIntent().getStringExtra("Active_User");
        DbManager db = new DbManager(this);
        lab=db.getLab(id);
        lab_id=findViewById(R.id.lab_id);
        slot=findViewById(R.id.slot);
        class_count=findViewById(R.id.classes);
        name=findViewById(R.id.name);
        lab_id.setText("Lab ID: "+id);
        slot.setText("Timmings:"+lab.get(1));
        class_count.setText("Classes:"+lab.get(2));
        name.setText("Lab Name:"+lab.get(0));
    }
    public void onStudentClick(View view){
        Intent intent=new Intent(User_lab_info.this,Student.class);
        intent.putExtra("labid",id);
        intent.putExtra("Active_User", Active_User);
        startActivity(intent);
    }
    public void onAttendance(View view){

        Intent intent = new Intent(User_lab_info.this,Attendance.class);
        intent.putExtra("labid", id);
        intent.putExtra("Active_User", Active_User);
        startActivity(intent);
    }

    public void onDelbtnClick(View view){
        DbManager db = new DbManager(this);
        db.deleteLab(id);
        Toast.makeText(this, "Lab Deleted Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(User_lab_info.this,User_lab.class);
        intent.putExtra("Active_User", Active_User);
        startActivity(intent);

    }
    public void onBackinfo(View view){
        Intent intent = new Intent(User_lab_info.this,User_lab.class);
        intent.putExtra("Active_User", Active_User);
        startActivity(intent);
    }



}