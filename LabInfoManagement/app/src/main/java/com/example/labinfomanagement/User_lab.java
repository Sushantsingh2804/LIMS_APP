package com.example.labinfomanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class User_lab extends AppCompatActivity {
    String Active_User;
    RecyclerView labView;
    ArrayList<modelLab> labHolder=new ArrayList<modelLab>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_lab);
        Active_User = getIntent().getStringExtra("Active_User");
        labView=findViewById(R.id.labView);
        labView.setLayoutManager(new LinearLayoutManager(this));
        Cursor cursor = new DbManager(this).readLabData(Active_User);
            while (cursor.moveToNext()) {
                modelLab obj = new modelLab(cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getInt(0));
                labHolder.add(obj);
            }
            labAdapter adapter = new labAdapter(labHolder,getApplicationContext(),Active_User);
            labView.setAdapter(adapter);
    }
    public void onClickAdd(View view){
        Intent intent = new Intent(User_lab.this,User_Add_lab.class);
        intent.putExtra("Active_User", Active_User);
        startActivity(intent);
    }
    public void onLogOut(View view){
        Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(User_lab.this,MainActivity.class));
    }

}