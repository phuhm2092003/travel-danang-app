package com.example.travel_danang_app.ui.changepass;

public interface ChangePasswordContract {
    interface View {
        void onDataInputEmpty();

        void onPasswordLengthInValid();

        void onShowLoading();

        void onHideLoading();

        void onChangePasswordSuccess();

        void onChangePasswordFailed();
    }

    interface Presenter {
        void changePassword(String passwordOld, String passwordNew);
    }
}
