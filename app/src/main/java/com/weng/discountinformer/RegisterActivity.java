package com.weng.discountinformer;
/**
 * Created by Weng Anxiang on 2017/3/11.
 */
import android.content.SharedPreferences;
import android.os.UserManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText userIdET;
    private EditText passwordET;
    private EditText confirmPasswordET;
    private EditText sendVerifyCodeET;
    private Button buttonSendVerifyCode;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    //判断两次输入密码是否一致
    private boolean isPasswordEqualed() {
        String s1 = passwordET.getText().toString();
        String s2 = confirmPasswordET.getText().toString();
        if (s1.equals(s2)){
            return true;
        }else{
            return false;
        }
    }
    //判断账号是否已经被注册
    private boolean isRegistered() {
        String id = userIdET.getText().toString();
        if (UserDataManager.isExisted(id)){
            Toast.makeText(RegisterActivity.this, "用户名已经存在", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
}
