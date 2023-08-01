package com.example.travel_danang_app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.travel_danang_app.R;
import com.example.travel_danang_app.ui.singin.SignInActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    public static final int DELAY_MILLIS = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Khởi chạy màng hình đăng nhập sau thời gian trể
        new Handler().postDelayed(() -> startActivity(new Intent(this, SignInActivity.class)), DELAY_MILLIS);
    }
}