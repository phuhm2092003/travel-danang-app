package com.example.travel_danang_app.ui.singin;

public interface SignInContract {
    interface View {
        void onDataInputEmpty();

        void showLoading();

        void hideLoading();

        void onSignInSuccessAsAdmin();

        void onSignInSuccessAsUser();

        void onSignInFailed();
    }

    interface Presenter {
        void signIn(String email, String password);

        void callApiGetRoleUserById();
    }
}
