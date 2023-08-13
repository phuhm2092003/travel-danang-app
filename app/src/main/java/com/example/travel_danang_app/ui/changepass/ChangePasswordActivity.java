package com.example.travel_danang_app.ui.changepass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.travel_danang_app.R;
import com.example.travel_danang_app.databinding.ActivityChangePasswordBinding;
import com.example.travel_danang_app.utils.UtilsDataInput;
import com.example.travel_danang_app.utils.UtilsMessage;
import com.example.travel_danang_app.utils.UtilsProgressDialog;

public class ChangePasswordActivity extends AppCompatActivity implements ChangePasswordContract.View {
    public static final String DATA_EMPTY_MESSAGE = "Vui lòng nhập thông tin";
    public static final String PASSWORD_LENGTH_INVALID_MESSGAE = "Mật khẩu mới tối thiểu 6 ký tự";
    public static final String CHANGE_PASSWORD_SUCCESS_MESSAGE = "Cập nhật thành công";
    public static final String CHANGE_PASSWORD_FAILED_MESSAGE = "Sai mật khẩu cũ";
    public static final String LOADING_MESSAGE = "Vui lòng chờ...";
    private ActivityChangePasswordBinding changePasswordBinding;
    private ChangePasswordPresenter changePasswordPresenter;
    private UtilsMessage utilsMessage;
    private UtilsProgressDialog utilsProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changePasswordBinding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(changePasswordBinding.getRoot());
        initObjects();
        initToolbar();
        setListeners();
    }

    private void initObjects() {
        changePasswordPresenter = new ChangePasswordPresenter(this);
        utilsMessage = new UtilsMessage(this);
        utilsProgressDialog = new UtilsProgressDialog(this);
    }

    private void initToolbar() {
        setSupportActionBar(changePasswordBinding.toolbar);
        getSupportActionBar().setTitle(null);
    }

    private void setListeners() {
        changePasswordBinding.toolbar.setNavigationOnClickListener(view -> onBackPressed());
        changePasswordBinding.passwordOldToggle.setOnClickListener(view -> onPasswordToggleClicked(changePasswordBinding.passwordOldEditText, changePasswordBinding.passwordOldToggle));
        changePasswordBinding.passwordNewToggle.setOnClickListener(view -> onPasswordToggleClicked(changePasswordBinding.passwordNewEditText, changePasswordBinding.passwordNewToggle));
        changePasswordBinding.changePasswordButton.setOnClickListener(view -> onChangePasswordButtonClicked());
    }

    private void onPasswordToggleClicked(EditText passwordEditText, ImageView togglePassword) {
        TransformationMethod transformationMethod;
        int imageResource;

        boolean isPasswordVisible = passwordEditText.getTransformationMethod() instanceof HideReturnsTransformationMethod;
        if (isPasswordVisible) {
            transformationMethod = PasswordTransformationMethod.getInstance();
            imageResource = R.drawable.eye_off;
        } else {
            transformationMethod = HideReturnsTransformationMethod.getInstance();
            imageResource = R.drawable.eye_on;
        }

        passwordEditText.setTransformationMethod(transformationMethod);
        togglePassword.setImageResource(imageResource);
    }

    private void onChangePasswordButtonClicked() {
        String passwordOld = changePasswordBinding.passwordOldEditText.getText().toString();
        String passwordNew = changePasswordBinding.passwordNewEditText.getText().toString();

        changePasswordPresenter.onChangePassword(passwordOld, passwordNew);
    }

    @Override
    public void onDataInputEmpty() {
        utilsMessage.showMessage(DATA_EMPTY_MESSAGE);
    }

    @Override
    public void onPasswordLengthInValid() {
        utilsMessage.showMessage(PASSWORD_LENGTH_INVALID_MESSGAE);
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
    public void onChangePasswordSuccess() {
        utilsMessage.showMessage(CHANGE_PASSWORD_SUCCESS_MESSAGE);
        UtilsDataInput.clearDataInput(changePasswordBinding.passwordOldEditText, changePasswordBinding.passwordNewEditText);
    }

    @Override
    public void onChangePasswordFailed() {
        utilsMessage.showMessage(CHANGE_PASSWORD_FAILED_MESSAGE);
    }
}