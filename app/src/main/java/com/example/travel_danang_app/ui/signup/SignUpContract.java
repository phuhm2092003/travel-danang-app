package com.example.travel_danang_app.ui.signup;

public interface SignUpContract {
    interface View {
        void onDataInputEmpty();

        void onEmailInValid();

        void onFullNameInvalid();

        void onPasswordLengthInValid();

        void showLoading();

        void hideLoading();

        void signUpSuccess();

        void signUpFailed(String err);
    }

    interface Presenter {
        void signUp(String email, String fullname, String password);

    }
}
