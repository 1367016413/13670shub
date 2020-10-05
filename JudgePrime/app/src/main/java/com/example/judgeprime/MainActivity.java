package com.example.judgeprime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Scanner;

import static java.lang.Math.sqrt;

public class MainActivity extends AppCompatActivity {
   TextView v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view) {
        v1 = (TextView)findViewById(R.id.t1);


        final Runnable worker = new Runnable() {
            @Override
            public void run() {
                int x = 23;
                boolean isPrime = true;
                if (x == 1) {
                    isPrime = false;
                }
                for (int i = 2; i < x; i++) {
                    if (x % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) {
                     v1.setText(x+"是素数");
                } else {
                    v1.setText(x+"不是素数");
                }
            }
        };
        Thread workThread = new Thread(null,worker,"WorkThread");
        workThread.start();
    }




}