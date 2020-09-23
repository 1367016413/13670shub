package com.example.activitypass;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.os.Bundle;

public class TheAty extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//调用其父类Activity的onCreate方法来实现对界面的图画绘制工作
        setContentView(R.layout.activity_the_aty);//setContentView(R.layout.activity_the_aty)的作用是加载第二个activity的界面
        Intent i =getIntent();

        tv=(TextView)findViewById(R.id.tv);//用TextView显示值
        tv.setText(i.getStringExtra("data"));

    }
}