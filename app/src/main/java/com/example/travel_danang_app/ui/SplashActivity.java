package com.example.travel_danang_app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.travel_danang_app.MainActivity;
import com.example.travel_danang_app.R;
import com.example.travel_danang_app.ui.singin.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    public static final int DELAY_MILLIS = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (isUserSignIn()) {
            launchMainActivity();
        } else {
            launchSignInActivityWithDelay();
        }
    }

    private boolean isUserSignIn() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser != null;
    }

    private void launchMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void launchSignInActivityWithDelay() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, SignInActivity.class));
            finish();
        }, DELAY_MILLIS);
    }
}