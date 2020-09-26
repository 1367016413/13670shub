package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    private static  final String tag ="asd";
    TextView show;
    TextView show2;
    Button bn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            case R.id.bt_cheng:
            case R.id.bt_chu:
            case R.id.bt_dian:
            case R.id.bt_jia:
            case R.id.bt_jian:
                Button btn0 = (Button) view;
                String addContent = btn0.getText().toString();
                show = (TextView) findViewById(R.id.input);
                String nowContent = show.getText().toString();
                String newContent = nowContent + addContent;
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
                String cut = nowContent1.substring(0, nowContent1.length() - 1);
                show.setText(cut);
                break;

            case R.id.bt_dengyu:
                show = (TextView) findViewById(R.id.input);
                show2 = (TextView) findViewById(R.id.output);
                String result = show.getText().toString();
                StringToRes P = new StringToRes();
                result = P.stringToRes(result);
                show2.setText("=" +result);
                break;


            case R.id.Ex:
                Intent intent = new Intent(MainActivity.this, excalculator.class);
                startActivity(intent);
                break;


            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }



    public void sin(View view) {
        show = (TextView) findViewById(R.id.input);
        show2 = (TextView) findViewById(R.id.output);
        String count = show.getText().toString();
        double a = Double.valueOf(count);
        double b = Math.toRadians(a);
        show2.setText("=" +Math.sin(b));

    }

    public void cos(View view) {
        show = (TextView) findViewById(R.id.input);
        show2 = (TextView) findViewById(R.id.output);
        String count = show.getText().toString();
        double a = Double.valueOf(count);
        double b = Math.toRadians(a);
        show2.setText("=" +Math.cos(b));
    }

    public void x2(View view) {
        show = (TextView) findViewById(R.id.input);
        show2 = (TextView) findViewById(R.id.output);
        String count = show.getText().toString();
        double a = Double.valueOf(count);
        show2.setText("=" +a*a);
    }


    public class StringToRes {



        //判断字符是否为操作符
        public boolean isOpr(String s) {

            if ("+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s)) {
                return true;
            }
            return false;
        }

        //判断操作符优先级
        public int priority(String s) {

            if ("+".equals(s) || "-".equals(s)) {
                return 1;
            } else {
                return 2;
            }
        }

        //将栈内单字符粘成字符串
        public void linkString(Stack<String> stack) {

            if (stack.peek().equals("#")) {
                return;
            }
            StringBuilder number = new StringBuilder();
            while (true) {
                String s = stack.peek();
                if (s.equals("#")) {
                    break;
                }
                number.insert(0, s);
                stack.pop();
            }
            stack.push(number.toString());
            stack.push("#");
            number.delete(0, number.length());
        }

        //计算运算式
        public void calculate(Stack<String> numStack, Stack<String> oprStack) {

            double res = 0;
            //弹出右操作数上的#并将其转为double计算
            numStack.pop();
            double rightNum = Double.parseDouble(numStack.pop());
            //弹出左操作数上的#并将其转为double计算
            numStack.pop();
            double leftNum = Double.parseDouble(numStack.pop());
            String opr = oprStack.pop();
            switch (opr) {
                case "+":
                    res = leftNum + rightNum;
                    break;
                case "-":
                    res = leftNum - rightNum;
                    break;
                case "*":
                    res = leftNum * rightNum;
                    break;
                case "/":
                    res = leftNum / rightNum;
                    break;
                default:
                    break;
            }

            //将计算结果压栈
            numStack.push(String.valueOf(res));
            numStack.push("#");
        }

        //接收字符串进行计算操作
        public String stringToRes(String str) {

            Stack<String> numStack = new Stack<String>();
            numStack.push("#");
            Stack<String> oprStack = new Stack<String>();
            String[] ss = new String[str.length()];
            for (int i = 0; i < str.length(); i++) {
                ss[i] = str.substring(i, i + 1);
            }

            for (String s : ss) {
                if (isOpr(s)) {
                    linkString(numStack);
                    if (oprStack.isEmpty()) {
                        oprStack.push(s);
                    } else {
                        while (!oprStack.isEmpty() && priority(s) <= priority(oprStack.peek())) {
                            linkString(numStack);
                            calculate(numStack, oprStack);
                        }
                        oprStack.push(s);
                    }
                } else {
                    numStack.push(s);
                }
            }
            while (!oprStack.isEmpty()) {
                linkString(numStack);
                calculate(numStack, oprStack);
            }
            Log.d(tag,numStack.peek());
            numStack.pop();
            Log.d(tag,numStack.peek());
            return numStack.peek();
        }
    }
}