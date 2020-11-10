package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class zhuanshi extends AppCompatActivity {
TextView v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuanshi);
    }

    public void shiliui(View view) {
        v1 = (TextView) findViewById(R.id.tsix);
        String res = v1.getText().toString();
        long num = Long.parseLong(res, 16);
        String s = Long.toString(num);
        v1.setText(s);
    }


    public void ba(View view) {
        v1 = (TextView) findViewById(R.id.eig);
        String res = v1.getText().toString();
        int num = Integer.parseInt(res, 8);
        String s = String.valueOf(num);
        v1.setText(s);
    }

    public void er(View view) {v1 = (TextView) findViewById(R.id.two);
        String res = v1.getText().toString();
        int num = Integer.parseInt(res, 2);
        String s = String.valueOf(num);
        v1.setText(s);
    }
}