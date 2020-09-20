package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        switch (view.getId()){
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
            case R.id.bt_cheng:
            case R.id.bt_chu:
            case R.id.bt_dian:
            case R.id.bt_jia:
            case R.id.bt_jian:
                Button btn0 = (Button) view;
                String addContent = btn0.getText().toString();
                show = (TextView) findViewById(R.id.input);
                String nowContent = show.getText().toString();
                String newContent = nowContent +addContent;
                show.setText(newContent);
                  break;

            case R.id.bt_C:
                show = (TextView) findViewById(R.id.input);
                show.setText("");
                show = (TextView) findViewById(R.id.output);
                show.setText("");
                break;

            case R.id.bt_DEL:
                show = (TextView) findViewById(R.id.input);
                String nowContent1 = show.getText().toString();
                String cut = nowContent1.substring(0,nowContent1.length()-1);
                show.setText(cut);
                break;

            case R.id.bt_dengyu:
                show = (TextView) findViewById(R.id.output);
                show.setText("=");
                show = (TextView) findViewById(R.id.input);
                String result = show.getText().toString();
                break;

            case  R.id.Ex:
                Intent intent = new Intent(MainActivity.this,excalculator.class);
                startActivity(intent);
                break;


            //获取设置的配置信息
            Configuration mConfiguration = this.getResources().getConfiguration();
            //获取屏幕方向
            int ori = mConfiguration.orientation;
            if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
                //横屏
            } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
                //竖屏
            }






            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
}