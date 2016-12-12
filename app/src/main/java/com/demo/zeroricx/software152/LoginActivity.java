package com.demo.zeroricx.software152;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.cv_login)
    CardView mLogin;
    @BindView(R.id.atv_username)
    AutoCompleteTextView mUsername;
    @BindView(R.id.et_password)
    EditText mPassword;

    private String mUser;
    private String mPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparent(LoginActivity.this);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        if (preferences.contains("username")) {
            mUser = preferences.getString("username", "");
            mPwd = preferences.getString("password", "");
            goToMainActivity();
        }

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUser = mUsername.getText().toString();
                mPwd = mPassword.getText().toString();
                if (!mUser.isEmpty() && !mPwd.isEmpty()) {
                    goToMainActivity();
                    SharedPreferences preferences = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username", mUser);
                    editor.putString("password", mPwd);
                    editor.apply();
                } else {
                    Snackbar.make(mLogin, "请输入学号跟密码!", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);
        intent.putExtra("username", mUser);
        intent.putExtra("password", mPwd);
        startActivity(intent);
        LoginActivity.this.finish();
    }
}

