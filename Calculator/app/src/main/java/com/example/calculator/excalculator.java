package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.calculator.R.*;

public class excalculator extends AppCompatActivity {
      TextView view1;
      TextView view2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_excalculator);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt0:
            case R.id.bt1:
            case R.id.bt4:
            case R.id.bt2:
            case R.id.bt3:
            case R.id.bt5:
            case R.id.bt6:
            case R.id.bt7:
            case R.id.bt8:
            case R.id.bt9:
            case id.bt_dian:
                Button btn0 = (Button) view;
                String addContent = btn0.getText().toString();
                view1 = (TextView) findViewById(R.id.input);
                String nowContent = view1.getText().toString();
                String newContent = nowContent + addContent;
                view1.setText(newContent);
                break;

            case R.id.bt_C:
                view1 = (TextView) findViewById(R.id.input);
                view1.setText("");
                view1 = (TextView) findViewById(R.id.output);
                view1.setText("");
                break;

            case R.id.bt_DEL:
                view1 = (TextView) findViewById(R.id.input);
                String nowContent1 = view1.getText().toString();
                String cut = nowContent1.substring(0, nowContent1.length() - 1);
                view1.setText(cut);
                break;
        }
    }

    public void tenjz(View view) {
    }

    public void twojz(View view) {
        view1 = (TextView) findViewById(R.id.input);
        view2 = (TextView) findViewById(id.output);
        String count = view1.getText().toString();
        String res = Integer.toBinaryString(Integer.parseInt(count));
        view2.setText(res);
    }

    public void sixteenjz(View view) {
        view1 = (TextView) findViewById(R.id.input);
        view2 = (TextView) findViewById(id.output);
        String count = view1.getText().toString();
        String res = Integer.toHexString(Integer.parseInt(count));
        view2.setText(res);
    }

    public void eightjz(View view) {
        view1 = (TextView) findViewById(R.id.input);
        view2 = (TextView) findViewById(id.output);
        String count = view1.getText().toString();
        String res = Integer.toOctalString(Integer.parseInt(count));
        view2.setText(res);
    }


    public void back(View view) {
        Intent intent = new Intent(excalculator.this, MainActivity.class);
        startActivity(intent);
    }

    public void zhuan(View view) {
        Intent zs = new Intent(excalculator.this, zhuanshi.class);
        startActivity(zs);
    }
}