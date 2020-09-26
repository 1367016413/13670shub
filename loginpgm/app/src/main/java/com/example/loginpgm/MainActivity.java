package com.example.loginpgm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText abc;
    private EditText euserpassword;
    private Button  btnlogin;
    private Button btncancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        abc = (EditText) findViewById(R.id.e_user_name);
        euserpassword = (EditText) findViewById(R.id.e_user_password);
        btnlogin = (Button) findViewById(R.id.b_btn_login);
        btncancel = (Button) findViewById(R.id.b_btn_cancel);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = abc.getText().toString();
                String userpassword = euserpassword.getText().toString();
                if(username.equals("abc")&& userpassword.equals("123")){
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}