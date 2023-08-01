package com.example.travel_danang_app.ui.singin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.travel_danang_app.R;
import com.example.travel_danang_app.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {
    private ActivitySignInBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}