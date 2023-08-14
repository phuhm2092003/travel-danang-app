package com.example.travel_danang_app.ui.changepass;

import com.example.travel_danang_app.utils.UtilsDataInput;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class ChangePasswordPresenter implements ChangePasswordContract.Presenter {
    private final ChangePasswordContract.View view;
    private final FirebaseUser currentUser;

    public ChangePasswordPresenter(ChangePasswordContract.View view) {
        this.view = view;
        this.currentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void changePassword(String passwordOld, String passwordNew) {
        if (!isDataInputValid(passwordOld, passwordNew) || currentUser == null) {
            return;
        }

        view.onShowLoading();
        // Xác thực lại người dùng
        AuthCredential credential = EmailAuthProvider
                .getCredential(Objects.requireNonNull(currentUser.getEmail()), passwordOld);
        currentUser.reauthenticate(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        updatePasswordToCurrentUser(passwordNew);
                    } else {
                        view.onChangePasswordFailed();
                        view.onHideLoading();
                    }
                });
    }

    private void updatePasswordToCurrentUser(String passwordNew) {
        currentUser.updatePassword(passwordNew)
                .addOnCompleteListener(task1 -> {
                    view.onHideLoading();
                    if (task1.isSuccessful()) {
                        view.onChangePasswordSuccess();
                    }
                });
    }

    private boolean isDataInputValid(String passwordOld, String passwordNew) {
        if (UtilsDataInput.isEmptyData(passwordOld, passwordNew)) {
            view.onDataInputEmpty();
            return false;
        }

        if (!UtilsDataInput.isPasswordLengthValid(passwordNew)) {
            view.onPasswordLengthInValid();
            return false;
        }

        return true;
    }
}
