package com.kaygb.money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import com.kaygb.money.User;
import com.kaygb.money.DbHelper;
import com.kaygb.money.UserSevice;

public class LoginActivity extends AppCompatActivity {
    private Button mBtn_ToReg;
    private EditText editname;
    private EditText editpasswd;
    private Button mBtnLogin;
    private String strname;
    private String strpasswd;
    private SharedHelper sh;
    private Context mContext;
    private CheckBox mCbMpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = getApplicationContext();
        sh = new SharedHelper(mContext);
        bindViews();

        // 判断是否勾选记住密码
        mCbMpwd = findViewById(R.id.mpwd);

        // 跳转注册
        mBtn_ToReg =findViewById(R.id.btn_to_reg);
        setListenter(mBtn_ToReg);
    }
    private void bindViews() {
        editname = (EditText)findViewById(R.id.editname);
        editpasswd = (EditText)findViewById(R.id.editpasswd);
        mBtnLogin = (Button)findViewById(R.id.btnlogin);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strname = editname.getText().toString();
                strpasswd = editpasswd.getText().toString();
                UserSevice userService = new UserSevice(getBaseContext());
                boolean flag = userService.Login(strname,strpasswd);
                if (flag){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
                    if (mCbMpwd.isChecked()){ // 如果勾选记住密码，则下次自动填充
                        sh.save(strname,strpasswd);
                    }
                    finish();
                }else {
                    Toast.makeText(mContext, "登录失败,请检查账号或密码", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Map<String,String> data = sh.read();
        editname.setText(data.get("username"));
        editpasswd.setText(data.get("passwd"));
    }

        /**
         * Created by Jay on 2015/9/2 0002.
         */
    public class SharedHelper {

        private Context mContext;

        public SharedHelper() {
        }

        public SharedHelper(Context mContext) {
            this.mContext = mContext;
        }


        //定义一个保存数据的方法
        public void save(String username, String passwd) {
            SharedPreferences sp = mContext.getSharedPreferences("mysp", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("username", username);
            editor.putString("passwd", passwd);
            editor.commit();
//            Toast.makeText(mContext, "信息已写入SharedPreference中", Toast.LENGTH_SHORT).show();
            Toast.makeText(mContext, "账号信息已保存，下次打开将自动填充", Toast.LENGTH_SHORT).show();
        }

        //定义一个读取SP文件的方法
        public Map<String, String> read() {
            Map<String, String> data = new HashMap<String, String>();
            SharedPreferences sp = mContext.getSharedPreferences("mysp", Context.MODE_PRIVATE);
            data.put("username", sp.getString("username", ""));
            data.put("passwd", sp.getString("passwd", ""));
            return data;
        }
    }
    // 页面intent跳转监听
    private void setListenter(Button lsbtn) {
        OnClick onClick = new OnClick();
        lsbtn.setOnClickListener(onClick);
    }
    // intent跳转
    private class OnClick implements View.OnClickListener {
        @Override

        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()) {
                case R.id.btn_to_reg:
                    intent = new Intent(LoginActivity.this, RegActivity.class);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
            startActivity(intent);
            finish();
        }
    }

}
