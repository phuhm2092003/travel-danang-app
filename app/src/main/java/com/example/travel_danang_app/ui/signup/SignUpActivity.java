package com.example.travel_danang_app.ui.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travel_danang_app.R;
import com.example.travel_danang_app.databinding.ActivitySignUpBinding;
import com.example.travel_danang_app.ui.singin.SignInActivity;
import com.example.travel_danang_app.utils.UtilsProgressDialog;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View {
    public static final String DATA_EMPTY_MESSAGE = "Vui lòng nhập đầy đủ thông tin";
    public static final String EMAIL_INVALID_MESSAGE = "Email sai định dạng";
    public static final String FULLNAME_INVALID_MESSAGE = "Tên chưa ký tự số";
    public static final String PASSWORD_LENGTH_INVALID_MESSGAE = "Mật khẩu phải ít nhất 6 ký tự";
    public static final String LOADING_MESSGAE = "Vui lòng chờ...";
    public static final String SIGN_UP_SUCESS_MESSAGE = "Đăng ký thành công";
    public static final String SIGN_UP_FAILED_MESSAGE = "Email đã có người sử dụng";
    private ActivitySignUpBinding signUpBinding;
    private SignUpPresenter signUpPresenter;
    private UtilsProgressDialog utilsProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(signUpBinding.getRoot());

        signUpPresenter = new SignUpPresenter(this);
        utilsProgressDialog = new UtilsProgressDialog(this);

        initToolbar();
        setListeners();
    }

    private void initToolbar() {
        setSupportActionBar(signUpBinding.toolbar);
        getSupportActionBar().setTitle(null);
    }

    private void setListeners() {
        signUpBinding.toolbar.setNavigationOnClickListener(view -> onBackPressed());
        signUpBinding.signInTextView.setOnClickListener(view -> onBackPressed());
        signUpBinding.passwordToggle.setOnClickListener(view -> onPasswordToggleClicked());
        signUpBinding.signUpButton.setOnClickListener(view -> onSignUpButtonClicked());
    }

    private void onPasswordToggleClicked() {
        TransformationMethod transformationMethod;
        int imageResource;

        boolean isPasswordVisible = signUpBinding.passwordEditText.getTransformationMethod() instanceof HideReturnsTransformationMethod;
        if (isPasswordVisible) {
            transformationMethod = PasswordTransformationMethod.getInstance();
            imageResource = R.drawable.eye_off;
        } else {
            transformationMethod = HideReturnsTransformationMethod.getInstance();
            imageResource = R.drawable.eye_on;
        }

        signUpBinding.passwordEditText.setTransformationMethod(transformationMethod);
        signUpBinding.passwordToggle.setImageResource(imageResource);
    }

    private void onSignUpButtonClicked() {
        String email = signUpBinding.emailEditText.getText().toString().trim();
        String fullName = signUpBinding.fullnameEditText.getText().toString().trim();
        String password = signUpBinding.passwordEditText.getText().toString().trim();

        signUpPresenter.signUp(email, fullName, password);
    }

    @Override
    public void onDataInputEmpty() {
        Toast.makeText(this, DATA_EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmailInValid() {
        Toast.makeText(this, EMAIL_INVALID_MESSAGE, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFullNameInvalid() {
        Toast.makeText(this, FULLNAME_INVALID_MESSAGE, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordLengthInValid() {
        Toast.makeText(this, PASSWORD_LENGTH_INVALID_MESSGAE, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        utilsProgressDialog.showLoadingDialog(LOADING_MESSGAE);
    }

    @Override
    public void hideLoading() {
        utilsProgressDialog.hideLoadingDialog();
    }

    @Override
    public void signUpSuccess() {
        Toast.makeText(this, SIGN_UP_SUCESS_MESSAGE, Toast.LENGTH_SHORT).show();
        learDataEditText();
    }

    private void learDataEditText() {
        EditText[] arr = {signUpBinding.emailEditText, signUpBinding.fullnameEditText, signUpBinding.passwordEditText};
        for (EditText edt : arr) {
            edt.setText(null);
        }
    }

    @Override
    public void signUpFailed(String err) {
        Toast.makeText(this, SIGN_UP_FAILED_MESSAGE, Toast.LENGTH_SHORT).show();
        Log.e("TAG", "signUpFailed: " + err);
    }
}