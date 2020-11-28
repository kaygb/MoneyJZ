package com.kaygb.money;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kaygb.money.User;
import com.kaygb.money.DbHelper;
import com.kaygb.money.UserSevice;

import androidx.appcompat.app.AppCompatActivity;

public class RegActivity extends AppCompatActivity {
    private Button mBtnToLogin;
    private Button mBtnReg;
    private EditText mUsername;
    private EditText mPassword;
    private EditText mPasswordTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        //跳转登录页面
        mBtnToLogin = findViewById(R.id.btn_to_login);
        setListenter(mBtnToLogin);

        // 用户注册
        mUsername = findViewById(R.id.edit_new_name);
        mPassword = findViewById(R.id.edit_new_passwd);
        mPasswordTwo = findViewById(R.id.edit_new_passwd_two);
        mBtnReg = findViewById(R.id.btnreg);

        mBtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = mUsername.getText().toString().trim();
                String passWord = mPassword.getText().toString().trim();
                String passWordTwo = mPasswordTwo.getText().toString().trim();
                Log.i("TAG", userName);
                Log.i("TAG", passWord);

                User user = new User();
                user.setUsername(userName);
                user.setPassword(passWord);

                System.out.println(user.getUsername());
                System.out.println(user.getPassword());
                System.out.println(passWordTwo);

                // 验证两次密码


                if (userName.equals("") || passWord.equals("")||passWordTwo.equals("")){	//判断两次密码是否为空
                    Toast.makeText(getApplicationContext(),"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                }else if (!passWord.equals(passWordTwo)){
                    Toast.makeText(getApplication(),"密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
                }else if(passWord.equals(passWordTwo)){
                    UserSevice userService = new UserSevice(getBaseContext());
                    boolean flag = userService.Register(user);
                    if (flag) {
                        Toast.makeText(RegActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(RegActivity.this, "未知错误", Toast.LENGTH_SHORT).show();
                }




            }
        });




    }

    // 页面intent跳转监听
    private void setListenter(Button lsbtn) {
        RegActivity.OnClick onClick = new RegActivity.OnClick();
        lsbtn.setOnClickListener(onClick);
    }
    // intent跳转
    private class OnClick implements View.OnClickListener {
        @Override

        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()) {
                case R.id.btn_to_login:
                    intent = new Intent(RegActivity.this, LoginActivity.class);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
            startActivity(intent);
            finish();
        }
    }
}