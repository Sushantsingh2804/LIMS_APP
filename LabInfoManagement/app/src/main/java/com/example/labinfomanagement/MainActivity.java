package com.example.labinfomanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button login_button,signup_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_button=findViewById(R.id.login_button);
        signup_button=findViewById(R.id.signup_button);
        new DbManager(this);
        login_button.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,User_Login.class));
        });
        signup_button.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,User_Signup.class));
        });
    }
}