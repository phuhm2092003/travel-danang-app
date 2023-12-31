package com.example.travel_danang_app.ui.forgotpass;

public interface ForgotPasswordContract {
    interface View {
        void onDataInputEmpty();

        void onEmailInValid();

        void onShowLoading();

        void onHideLoading();

        void onSendEmailForgotPasswordSuccess();

        void onSendEmailForgotPasswordFailed();
    }

    interface Presenter {
        void sendEmailForgotPassword(String email);
    }
}
