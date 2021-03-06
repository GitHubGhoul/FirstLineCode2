package com.wxd.firstlinecode.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wxd.firstlinecode.R;
import com.wxd.firstlinecode.broadcast.BroadcastMainActivity;

import java.util.zip.InflaterOutputStream;

public class LoginActivity extends BaseActivity {

    private EditText username;
    private EditText password;
    private ProgressBar progressBar;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.loading);
        btnLogin = findViewById(R.id.login);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(username.getText().toString().trim()) && !TextUtils.isEmpty(password.getText().toString().trim())) {
                    btnLogin.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(username.getText().toString().trim()) && !TextUtils.isEmpty(password.getText().toString().trim())) {
                    btnLogin.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(LoginActivity.this);
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        if (user.equals("wxd") && pass.equals("123456")) {
                            startActivity(new Intent(LoginActivity.this, BroadcastMainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "用户名或者密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 2000);
            }
        });
    }

    private void hideSoftKeyboard(Activity activity){
        View view = activity.getCurrentFocus();
        if(view!=null){
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}