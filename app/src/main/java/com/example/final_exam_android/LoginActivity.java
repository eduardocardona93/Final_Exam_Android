package com.example.final_exam_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText loginUserEv, loginPasswordEv;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginBtn);
        loginUserEv = findViewById(R.id.loginUserEv);
        loginPasswordEv = findViewById(R.id.loginPasswordEv);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginUserEv.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(),"Username Field Empty", Toast.LENGTH_LONG).show();
                }else if(loginPasswordEv.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(),"Password Field Empty", Toast.LENGTH_LONG).show();
                }else if(!loginUserEv.getText().toString().equalsIgnoreCase("user1") || !loginPasswordEv.getText().toString().equals("password1")){
                    Toast.makeText(getBaseContext(),"Incorrect Username or password", Toast.LENGTH_LONG).show();
                }else{
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                    Toast.makeText(getBaseContext(),"Welcome ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}