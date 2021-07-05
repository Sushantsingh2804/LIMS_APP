package com.example.labinfomanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class User_Login extends AppCompatActivity {
    Button Back_button;
    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        Back_button=findViewById(R.id.Login_back);
        Back_button.setOnClickListener(v -> {
            startActivity(new Intent(User_Login.this,MainActivity.class));
        });
        initViews();
    }
    public void onSignupBtnClick(View view){
        startActivity(new Intent(User_Login.this,User_Signup.class));
    }
    public void onLoginBtnClick(View view){
        DbManager db=new DbManager(this);
        if(isValid()){
            if(db.findInstructor(email.getText().toString(),password.getText().toString())){
                Intent intent = new Intent(User_Login.this,User_lab.class);
                intent.putExtra("Active_User", email.getText().toString());
                startActivity(intent);
                email.setText("");
                password.setText("");
            }
            else {
                Toast.makeText(this,"Email or password is wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void initViews(){
        email=findViewById(R.id.login_user_email);
        password=findViewById(R.id.login_user_password);

    }
    private boolean isValid(){
        if (email.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter the Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter the Password", Toast.LENGTH_SHORT).show();
            return false;
        }

        else {
            if(!(Pattern.matches("^[A-za-z0-9]+[\\._]?[A-za-z0-9]+[@]\\w+[.]\\w{2,3}$",email.getText().toString()))){
                Toast.makeText(this, "Incorrect Email", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
    }
}