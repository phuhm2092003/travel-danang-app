package com.example.travel_danang_app.ui.singin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.widget.Toast;

import com.example.travel_danang_app.MainActivity;
import com.example.travel_danang_app.R;
import com.example.travel_danang_app.databinding.ActivitySignInBinding;
import com.example.travel_danang_app.ui.forgotpass.ForgotPasswordActivity;
import com.example.travel_danang_app.ui.signup.SignUpActivity;
import com.example.travel_danang_app.utils.UtilsProgressDialog;

public class SignInActivity extends AppCompatActivity implements SignInContract.View {
    public static final String DATA_INPUT_EMPTY_MESSAGE = "Vui lòng nhập đầy đủ thông tin";
    public static final String SIGN_IN_FAILED_MESSSAGE = "Đăng nhập không thành công";
    public static final String LOADING_MESSAGE = "Vui lòng chờ....";
    private ActivitySignInBinding binding;
    private SignInPresenter signInPresenter;
    private UtilsProgressDialog utilsProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        signInPresenter = new SignInPresenter(this);
        utilsProgressDialog = new UtilsProgressDialog(this);

        setListeners();
    }

    private void setListeners() {
        binding.forgotPassTextView.setOnClickListener(view -> onStartForgotPasswordActivity());
        binding.signUpTextView.setOnClickListener(view -> onStartSignUpActivity());
        binding.passwordToggle.setOnClickListener(view -> onPasswordToggleClicked());
        binding.signInButton.setOnClickListener(view -> onSignInButtonClicked());
    }

    private void onStartForgotPasswordActivity() {
        startActivity(new Intent(this, ForgotPasswordActivity.class));
    }

    private void onStartSignUpActivity() {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    private void onPasswordToggleClicked() {
        TransformationMethod transformationMethod;
        int imageResource;

        boolean isPasswordVisible = binding.passwordEditText.getTransformationMethod() instanceof HideReturnsTransformationMethod;
        if (isPasswordVisible) {
            transformationMethod = PasswordTransformationMethod.getInstance();
            imageResource = R.drawable.eye_off;
        } else {
            transformationMethod = HideReturnsTransformationMethod.getInstance();
            imageResource = R.drawable.eye_on;
        }

        binding.passwordEditText.setTransformationMethod(transformationMethod);
        binding.passwordToggle.setImageResource(imageResource);
    }

    private void onSignInButtonClicked() {
        String email = binding.emailEditText.getText().toString().trim();
        String password = binding.passwordEditText.getText().toString().trim();

        signInPresenter.signIn(email, password);
    }

    @Override
    public void onDataInputEmpty() {
        Toast.makeText(this, DATA_INPUT_EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        utilsProgressDialog.showLoadingDialog(LOADING_MESSAGE);
    }

    @Override
    public void hideLoading() {
        utilsProgressDialog.hideLoadingDialog();
    }

    @Override
    public void onSignInSuccessAsAdmin() {
        Log.e("TAG", "onSignInSuccessAsAdmin");
    }

    @Override
    public void onSignInSuccessAsUser() {
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }

    @Override
    public void onSignInFailed() {
        Toast.makeText(this, SIGN_IN_FAILED_MESSSAGE, Toast.LENGTH_SHORT).show();
    }
}