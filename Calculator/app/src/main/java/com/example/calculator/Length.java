package com.example.calculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Length extends AppCompatActivity {
    EditText v1;
    EditText v2;
    EditText v3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);
    }

    String[] array = new String[] { "长度", "重量"};
    public void bt3(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择需要换算的单位").

                        setItems(array, new DialogInterface.OnClickListener() {

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent Len = new Intent(Length.this, Length.class);
                                startActivity(Len);
                                break;
                            case 1:
                                Intent Wei = new Intent(Length.this, Weight.class);
                                startActivity(Wei);

                        }
                        Toast.makeText(Length.this, "选择" + array[which],
                                0).show();

                    }

                }).
                        create().show();
    }

    public void heic(View view) {
        v1 = (EditText) findViewById(R.id.W1);
        v2 = (EditText) findViewById(R.id.W2);
        v3 = (EditText) findViewById(R.id.W3);
        String res = v1.getText().toString();
        double r1 = Integer.parseInt(res);
        double r2 = r1/1000;
        double r3 = r2/1000;
        v1.setText(r1+"厘米");
        v2.setText(r2+"米");
        v3.setText(r3+"千米");
    }

    public void heim(View view) {
        v1 = (EditText) findViewById(R.id.W1);
        v2 = (EditText) findViewById(R.id.W2);
        v3 = (EditText) findViewById(R.id.W3);
        String res = v2.getText().toString();
        double r2 = Integer.parseInt(res);
        double r1 = r2*1000;
        double r3 = r2/1000;
        v1.setText(r1+"厘米");
        v2.setText(r2+"米");
        v3.setText(r3+"千米");
    }

    public void heik(View view) {
        v1 = (EditText) findViewById(R.id.W1);
        v2 = (EditText) findViewById(R.id.W2);
        v3 = (EditText) findViewById(R.id.W3);
        String res = v3.getText().toString();
        double r3 = Integer.parseInt(res);
        double r2 = r3*1000;
        double r1 = r2*1000;
        v1.setText(r1+"厘米");
        v2.setText(r2+"米");
        v3.setText(r3+"千米");
    }
}