package com.example.travel_danang_app.ui.forgotpass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.travel_danang_app.databinding.ActivityForgotPasswordBinding;
import com.example.travel_danang_app.utils.UtilsDataInput;
import com.example.travel_danang_app.utils.UtilsMessage;
import com.example.travel_danang_app.utils.UtilsProgressDialog;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordContract.View {
    public static final String EMAIL_INVALID_MESSAGE = "Email sai định dạng";
    public static final String SEND_EMAIL_FAILED_MESSAGE = "Tài khoản của bạn chưa được đăng ký";
    public static final String SEND_EMAIL_SUCCESS_MESSAGE = "Gửi email thành công! Vui lòng kiểm tra email.";
    public static final String DATA_EMPTY_MESSAGE = "Vui lòng nhập Email";
    private ActivityForgotPasswordBinding binding;
    private ForgotPasswordPresenter forgotPasswordPresenter;
    private UtilsProgressDialog utilsProgressDialog;
    private UtilsMessage utilsMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initObjects();
        initToolbar();
        setListeners();
    }

    private void initObjects() {
        forgotPasswordPresenter = new ForgotPasswordPresenter(this);
        utilsProgressDialog = new UtilsProgressDialog(this);
        utilsMessage = new UtilsMessage(this);
    }

    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(null);
    }

    private void setListeners() {
        binding.toolbar.setNavigationOnClickListener(view -> onBackPressed());
        binding.sendMailButton.setOnClickListener(view -> sendEmailForgotPassword());
    }

    private void sendEmailForgotPassword() {
        String email = binding.emailEditText.getText().toString().trim();
        forgotPasswordPresenter.sendEmailForgotPassword(email);
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
    public void onShowLoading() {
        utilsProgressDialog.showLoadingDialog();
    }

    @Override
    public void onHideLoading() {
        utilsProgressDialog.hideLoadingDialog();
    }

    @Override
    public void onSendEmailForgotPasswordSuccess() {
        utilsMessage.showMessage(SEND_EMAIL_SUCCESS_MESSAGE);
        UtilsDataInput.clearDataInput(binding.emailEditText);
    }

    @Override
    public void onSendEmailForgotPasswordFailed() {
        utilsMessage.showMessage(SEND_EMAIL_FAILED_MESSAGE);
    }
}