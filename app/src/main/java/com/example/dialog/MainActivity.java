package com.example.dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    final View viewDialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.customdialog, null, false);
    builder.setTitle("自定义对话框")
            .setView(viewDialog)
            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
        @Override
    }
    public void onClick(DialogInterface dialog, int which) {
        EditText editTextUserId = viewDialog.findViewById(R.id.edittext_user_id);
        EditText editTextUserPassword = viewDialog.findViewById(R.id.edittext_user_password);
        Toast.makeText(MainActivity.this, "UserId：" + editTextUserId.getText().toString()
                + "UserPassword：" + editTextUserPassword.getText().toString(), Toast.LENGTH_LONG).show();
    }
})
        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

        }
        });
    builder.create().show();
