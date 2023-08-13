package com.example.travel_danang_app.ui.singin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;

import com.example.travel_danang_app.MainActivity;
import com.example.travel_danang_app.R;
import com.example.travel_danang_app.databinding.ActivitySignInBinding;
import com.example.travel_danang_app.ui.forgotpass.ForgotPasswordActivity;
import com.example.travel_danang_app.ui.signup.SignUpActivity;
import com.example.travel_danang_app.utils.UtilsMessage;
import com.example.travel_danang_app.utils.UtilsProgressDialog;

public class SignInActivity extends AppCompatActivity implements SignInContract.View {
    public static final String DATA_INPUT_EMPTY_MESSAGE = "Vui lòng nhập đầy đủ thông tin";
    public static final String SIGN_IN_FAILED_MESSAGE = "Đăng nhập không thành công";
    private ActivitySignInBinding signInBinding;
    private SignInPresenter signInPresenter;
    private UtilsProgressDialog utilsProgressDialog;
    private UtilsMessage utilsMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signInBinding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(signInBinding.getRoot());
        initObjects();
        setListeners();
    }

    private void initObjects() {
        signInPresenter = new SignInPresenter(this);
        utilsProgressDialog = new UtilsProgressDialog(this);
        utilsMessage = new UtilsMessage(this);
    }

    private void setListeners() {
        signInBinding.passwordToggle.setOnClickListener(view -> onPasswordToggleClicked());
        signInBinding.forgotPassTextView.setOnClickListener(view -> launchForgotPasswordActivity());
        signInBinding.signUpTextView.setOnClickListener(view -> launchSignUpActivity());
        signInBinding.signInButton.setOnClickListener(view -> onSignInButtonClicked());
    }

    private void onPasswordToggleClicked() {
        TransformationMethod transformationMethodEditText;
        int imagePasswordToggle;

        boolean isPasswordVisible = signInBinding.passwordEditText.getTransformationMethod() instanceof HideReturnsTransformationMethod;
        if (isPasswordVisible) {
            transformationMethodEditText = PasswordTransformationMethod.getInstance();
            imagePasswordToggle = R.drawable.eye_off;
        } else {
            transformationMethodEditText = HideReturnsTransformationMethod.getInstance();
            imagePasswordToggle = R.drawable.eye_on;
        }

        signInBinding.passwordEditText.setTransformationMethod(transformationMethodEditText);
        signInBinding.passwordToggle.setImageResource(imagePasswordToggle);
    }

    private void launchForgotPasswordActivity() {
        startActivity(new Intent(this, ForgotPasswordActivity.class));
    }

    private void launchSignUpActivity() {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    private void onSignInButtonClicked() {
        String email = signInBinding.emailEditText.getText().toString().trim();
        String password = signInBinding.passwordEditText.getText().toString().trim();

        signInPresenter.signIn(email, password);
    }

    @Override
    public void onDataInputEmpty() {
        utilsMessage.showMessage(DATA_INPUT_EMPTY_MESSAGE);
    }

    @Override
    public void onShowLoading() {
        utilsProgressDialog.showLoadingDialog();
    }

    @Override
    public void onHideLoading() {
        utilsProgressDialog.hideLoadingDialog();
    }

    @Override
    public void onSignInSuccess() {
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }

    @Override
    public void onSignInFailed() {
        utilsMessage.showMessage(SIGN_IN_FAILED_MESSAGE);
    }
}