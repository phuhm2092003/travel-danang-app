package com.example.travel_danang_app.ui.forgotpass;


import com.example.travel_danang_app.utils.UtilsDataInput;

import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter {
    private final ForgotPasswordContract.View view;

    public ForgotPasswordPresenter(ForgotPasswordContract.View view) {
        this.view = view;
    }

    @Override
    public void sendEmailForgotPassword(String emailAddress) {
        if (!isDataValid(emailAddress)) {
            return;
        }

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        view.onShowLoading();

        mAuth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(task -> {
            view.onHideLoading();
            if (task.isSuccessful()) {
                view.onSendEmailForgotPasswordSuccess();
            } else {
                view.onSendEmailForgotPasswordFailed();
            }
        });
    }

    private boolean isDataValid(String emailAddress) {
        if (UtilsDataInput.isEmptyData(emailAddress)) {
            view.onDataInputEmpty();
            return false;
        }

        if (!UtilsDataInput.isEmailValid(emailAddress)) {
            view.onEmailInValid();
            return false;
        }

        return true;
    }
}
