package com.example.calculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
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
                Caculater Z = new Caculater(result);
                Double result1 = Z.suan();
                show2.setText("=" + result1);
                break;

            case R.id.Ex:
                Intent intent = new Intent(MainActivity.this, excalculator.class);
                startActivity(intent);
                break;

            case R.id.bt_cha:
                Intent intent1 = new Intent(MainActivity.this, change.class);
                startActivity(intent1);
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
        show2.setText("=" + Math.sin(b));

    }

    public void cos(View view) {
        show = (TextView) findViewById(R.id.input);
        show2 = (TextView) findViewById(R.id.output);
        String count = show.getText().toString();
        double a = Double.valueOf(count);
        double b = Math.toRadians(a);
        show2.setText("=" + Math.cos(b));
    }

    public void kuohao(View view) {
        show = (TextView) findViewById(R.id.input);
        show2 = (TextView) findViewById(R.id.output);
        String a = show.getText().toString();
        String b = a + "(";
        show.setText(b);
    }

    public void kuohao2(View view) {
        show = (TextView) findViewById(R.id.input);
        show2 = (TextView) findViewById(R.id.output);
        String a = show.getText().toString();
        String b = a + ")";
        show.setText(b);
    }


    class Caculater {
        private String[] sArry;
        private Stack<String> houx = new Stack<String>();
        private Stack<String> fuhao = new Stack<String>();

        Caculater(String str) {
            int i = str.length() - 1;
            String temp = "";
            int j = 0;
            Boolean bool = true;
            while (true) {
                if (!bool) break;
                if (i == j) {
                    bool = false;
                }
                if (str.charAt(j) == '+'
                        || str.charAt(j) == '-'
                        || str.charAt(j) == '*'
                        || str.charAt(j) == '/'
                        || str.charAt(j) == '('
                        || str.charAt(j) == ')') {
                    temp += '#';
                    temp += str.charAt(j);
                    temp += '#';
                } else {
                    temp += str.charAt(j);
                }
                j++;
            }
            sArry = temp.split("#+"); //用正则表达式分割成字符串
        }

        //后序排列
        public void backsort() {
            //循环sArry
            for (int i = 0; i < sArry.length; i++) {
                //如果不是字符，就直接push入houx栈
                if (!sArry[i].equals("+")
                        && !sArry[i].equals("-")
                        && !sArry[i].equals("*")
                        && !sArry[i].equals("/")
                        && !sArry[i].equals("(")
                        && !sArry[i].equals(")")) {
                    houx.push(sArry[i]);
                    continue;
                    //否则是字符，若符号栈为空，直接入栈
                } else if (fuhao.isEmpty()) {
                    fuhao.push(sArry[i]);
                    continue;
                    //如果为（括号，直接入符号栈
                } else if (sArry[i].equals("(")) {
                    fuhao.push(sArry[i]);
                    continue;
                    //如果为）括号
                } else if (sArry[i].equals(")")) {
                    /**
                     * 不断出栈直到（括号出现
                     *
                     */
                    while (!fuhao.peek().equals("(")) {
                        houx.push(fuhao.pop());
                    }
                    fuhao.pop();//清掉（括号
                    //如果不为空，且要入的符号比符号栈顶的符号优先级高，则直接push入符号栈
                } else if (!fuhao.isEmpty() && check(sArry[i], fuhao.peek())) {            //
                    fuhao.push(sArry[i]);
                    continue;
                    //否则，将符号栈内优先级高的符号出栈，push入houx栈，再将符号存进符号栈
                } else {
                    houx.push(fuhao.pop());
                    fuhao.push(sArry[i]);
                    continue;
                }
            }

            while (!fuhao.isEmpty()) {
                houx.push(fuhao.pop());
            }
        }

        //check对比优先级
        private boolean check(String a, String b) {
            if (b.equals("(")) {
                return true;
            }
            if ((b.equals("*") || b.equals("/")) && (a.equals("+") || a.equals("-"))) {
                return false;
            }
            if ((b.equals("+") || b.equals("-")) && (a.equals("*") || a.equals("/"))) {
                return true;
            }
            return false;
        }


        public Double suan() {

            backsort();
            Stack<Double> end = new Stack<Double>();
            for (int i = 0; i < houx.size(); i++) {
                //如果是加号，end pop出来两个数字，计算后结果入栈
                if (houx.get(i).equals("+")) {
                    Double b = end.pop();
                    Double a = end.pop();
                    end.push(a + b);
                    continue;
                    //如果是减号，end pop出栈两个数字，计算后结果入栈
                } else if (houx.get(i).equals("-")) {
                    Double b = end.pop();
                    Double a = end.pop();
                    end.push(a - b);
                    continue;
                    //如果是乘号，end pop出栈两个数字，计算后结果入栈
                } else if (houx.get(i).equals("*")) {
                    Double b = end.pop();
                    Double a = end.pop();
                    end.push(a * b);
                    continue;
                    //如果是除号，end pop出栈两个数字，计算后结果入栈
                } else if (houx.get(i).equals("/")) {
                    Double b = end.pop();
                    Double a = end.pop();
                    end.push(a / b);
                    continue;
                } else if (houx.get(i).isEmpty()) {
                    continue;
                } else {
                    end.push(Double.parseDouble(houx.get(i)));
                }

            }


            return end.pop();
        }

    }


    //创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_about_us: //关于
                AlertDialog aboutDialog = new AlertDialog.Builder(this).create();
                aboutDialog.setMessage("这是一个帮助");
                aboutDialog.show();
                break;
        }
        return false;
    }
}
















