package com.example.uitest;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyDialogActivity extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private Button btnOK;
    private Button btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mydialog);
        // 创建对话框构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(MyDialogActivity.this, R.layout.mydialog, null);
        // 获取布局中的控件
        name=(EditText) view.findViewById(R.id.edname);
        password=(EditText) view.findViewById(R.id.edpassword);
        btnCancel=(Button) view.findViewById(R.id.cancel);
        btnOK=(Button) view.findViewById(R.id.ok);
        builder.setView(view);
        // 创建对话框
        final AlertDialog alertDialog = builder.create();
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = name.getText().toString().trim();
                String psd = password.getText().toString().trim();
                Toast.makeText(MyDialogActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();// 对话框消失
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();// 对话框消失
            }
        });
        alertDialog.show();
    }
}