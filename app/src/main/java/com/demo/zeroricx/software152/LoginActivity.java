package com.demo.zeroricx.software152;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.jaeger.library.StatusBarUtil;


public class LoginActivity extends AppCompatActivity {
    CardView mLogin;
    AutoCompleteTextView mUsername;
    EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StatusBarUtil.setTransparent(LoginActivity.this);
        mLogin = (CardView) findViewById(R.id.cv_login);
        mUsername = (AutoCompleteTextView) findViewById(R.id.atv_username);
        mPassword = (EditText) findViewById(R.id.et_password);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mUsername.getText().toString().equals(null) && !mPassword.getText().toString().equals(null)) {
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    intent.putExtra("username", mUsername.getText().toString());
                    intent.putExtra("password", mPassword.getText().toString());
                    startActivity(intent);
                    finish();
                } else {
                    Snackbar.make(mLogin, "请输入学号跟密码!", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}

