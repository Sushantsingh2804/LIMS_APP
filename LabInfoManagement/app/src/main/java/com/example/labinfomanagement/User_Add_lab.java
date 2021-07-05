package com.example.labinfomanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class User_Add_lab extends AppCompatActivity {
    EditText lab,slot;
    String Active_User;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add_lab);
        Active_User = getIntent().getStringExtra("Active_User");
        initViews();
    }
    public void onAddBtnClick(View view) {
        DbManager db = new DbManager(this);
        if (isValid()) {
            if (db.addLab(lab.getText().toString(),slot.getText().toString(),Active_User)) {
                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(User_Add_lab.this, User_lab.class));
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }
            lab.setText("");
            slot.setText("");
            Intent intent = new Intent(User_Add_lab.this,User_lab.class);
            intent.putExtra("Active_User",Active_User);
            startActivity(intent);
        }
    }
        private void initViews(){
            lab=findViewById(R.id.lab_name);
            slot=findViewById(R.id.lab_slot);

        }
        private boolean isValid(){
            if(lab.getText().toString().equals("")){
                Toast.makeText(this, "Please Enter the Name", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (slot.getText().toString().equals("")){
                Toast.makeText(this, "Please Enter the Slot", Toast.LENGTH_SHORT).show();
                return false;
            }
            else {
                return true;
            }
        }
}
