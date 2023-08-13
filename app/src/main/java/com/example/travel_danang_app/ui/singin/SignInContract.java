package com.example.travel_danang_app.ui.singin;

public interface SignInContract {
    interface View {
        void onDataInputEmpty();

        void onShowLoading();

        void onHideLoading();

        void onSignInSuccess();

        void onSignInFailed();
    }

    interface Presenter {
        void signIn(String email, String password);
    }
}
