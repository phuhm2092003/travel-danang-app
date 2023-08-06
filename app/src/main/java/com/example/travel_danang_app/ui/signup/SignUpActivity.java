package com.example.travel_danang_app.ui.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.widget.EditText;


import com.example.travel_danang_app.R;
import com.example.travel_danang_app.databinding.ActivitySignUpBinding;
import com.example.travel_danang_app.utils.UtilsMessage;
import com.example.travel_danang_app.utils.UtilsProgressDialog;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View {
    public static final String DATA_EMPTY_MESSAGE = "Vui lòng nhập đầy đủ thông tin";
    public static final String EMAIL_INVALID_MESSAGE = "Email sai định dạng";
    public static final String FULLNAME_INVALID_MESSAGE = "Tên có chứa ký tự số";
    public static final String PASSWORD_LENGTH_INVALID_MESSGAE = "Mật khẩu phải ít nhất 6 ký tự";
    public static final String LOADING_MESSGAE = "Vui lòng chờ...";
    public static final String SIGN_UP_SUCESS_MESSAGE = "Đăng ký thành công";
    public static final String SIGN_UP_FAILED_MESSAGE = "Email đã có người sử dụng";
    private ActivitySignUpBinding signUpBinding;
    private SignUpPresenter signUpPresenter;
    private UtilsProgressDialog utilsProgressDialog;
    private UtilsMessage utilsMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(signUpBinding.getRoot());

        iniObjects();
        initToolbar();
        setListeners();
    }

    private void iniObjects() {
        signUpPresenter = new SignUpPresenter(this);
        utilsProgressDialog = new UtilsProgressDialog(this);
        utilsMessage = new UtilsMessage(this);
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
        utilsMessage.showMessage(DATA_EMPTY_MESSAGE);
    }

    @Override
    public void onEmailInValid() {
        utilsMessage.showMessage(EMAIL_INVALID_MESSAGE);
    }

    @Override
    public void onFullNameInvalid() {
        utilsMessage.showMessage(FULLNAME_INVALID_MESSAGE);
    }

    @Override
    public void onPasswordLengthInValid() {
        utilsMessage.showMessage(PASSWORD_LENGTH_INVALID_MESSGAE);
    }

    @Override
    public void onShowLoading() {
        utilsProgressDialog.showLoadingDialog(LOADING_MESSGAE);
    }

    @Override
    public void onHideLoading() {
        utilsProgressDialog.hideLoadingDialog();
    }

    @Override
    public void signUpSuccess() {
        utilsMessage.showMessage(SIGN_UP_SUCESS_MESSAGE);
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
        utilsMessage.showMessage(SIGN_UP_FAILED_MESSAGE);
        Log.e("TAG", "signUpFailed: " + err);
    }
}