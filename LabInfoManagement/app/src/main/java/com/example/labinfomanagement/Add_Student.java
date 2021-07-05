package com.example.labinfomanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Add_Student extends AppCompatActivity {
    int id;
    EditText usn,name;
    String Active_User;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        id = getIntent().getIntExtra("labid",-1);
        Active_User = getIntent().getStringExtra("Active_User");
        initViews();
    }
    public void onAddStudentClick(View view) {
        DbManager db = new DbManager(this);
        if (isValid()) {
            if (db.addStudent(usn.getText().toString(),name.getText().toString(),id)) {
                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(User_Add_lab.this, User_lab.class));
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }
            usn.setText("");
            name.setText("");
            Intent intent = new Intent(Add_Student.this,Student.class);
            intent.putExtra("labid", id);
            intent.putExtra("Active_User", Active_User);
            startActivity(intent);
        }
    }
    public void onBackbtnClick(View view){
        Intent intent = new Intent(Add_Student.this,Student.class);
        intent.putExtra("Active_User", Active_User);
        intent.putExtra("labid", id);
        startActivity(intent);
    }
    private void initViews(){
        usn=findViewById(R.id.usn);
        name=findViewById(R.id.studentName);
    }
    private boolean isValid(){
        if(usn.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter the Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (name.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter the Slot", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            if(!(Pattern.matches("[A-Za-z]{2,25}",name.getText().toString()))){
                Toast.makeText(this, "Enter a valid name", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
    }
}