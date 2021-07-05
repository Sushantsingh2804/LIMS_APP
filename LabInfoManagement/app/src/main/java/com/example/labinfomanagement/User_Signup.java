package com.example.labinfomanagement;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class User_Signup extends AppCompatActivity {
    Button Back_button;
    EditText name,email,password,phone_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);
        Back_button=findViewById(R.id.signup_back);
        Back_button.setOnClickListener(v -> startActivity(new Intent(User_Signup.this,MainActivity.class)));
        initViews();
    }
    public void onSubmitBtnClick(View view){
        DbManager db=new DbManager(this);
        if(isValid()){
            if (db.addInstructor(name.getText().toString(),email.getText().toString(),password.getText().toString(),phone_no.getText().toString())){
                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(User_Signup.this,User_Login.class));
            }
            else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }
            name.setText("");
            email.setText("");
            password.setText("");
            phone_no.setText("");
        }


    }
    private void initViews(){
        name=findViewById(R.id.signup_user_name);
        email=findViewById(R.id.signup_user_email);
        password=findViewById(R.id.signup_user_password);
        phone_no=findViewById(R.id.signup_user_phone);

    }
    private boolean isValid(){
        if(name.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter the Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (email.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter the Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter the Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (phone_no.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter the Phone Number", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            if(!(Pattern.matches("[A-Za-z]{2,25}",name.getText().toString()))){
                Toast.makeText(this, "Enter a valid name", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(!(Pattern.matches("^[A-za-z0-9]+[\\._]?[A-za-z0-9]+[@]\\w+[.]\\w{2,3}$",email.getText().toString()))){
                Toast.makeText(this, "Incorrect Email", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!(Pattern.matches("[6-9][0-9]{9}",phone_no.getText().toString()))){
                Toast.makeText(this, "Incorrect Phone Number", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
    }
}