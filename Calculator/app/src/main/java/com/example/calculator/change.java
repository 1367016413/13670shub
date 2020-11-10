package com.example.calculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class change extends AppCompatActivity {
TextView v1;
TextView v2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
    }

    String[] array = new String[] { "长度", "重量"};
    public void bt3(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(change.this);

        builder.setTitle("选择需要换算的单位").

                        setItems(array, new DialogInterface.OnClickListener() {

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    switch (which){
                        case 0:
                            Intent Len = new Intent(change.this, Length.class);
                            startActivity(Len);
                            break;
                        case 1:
                            Intent Wei = new Intent(change.this, Weight.class);
                            startActivity(Wei);

                    }
                        Toast.makeText(change.this, "选择" + array[which],
                                0).show();

                    }

                }).

                        create().show();
    }


}
