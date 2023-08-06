package com.example.travel_danang_app.ui.setting.changepass;

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
    public void onChangePassword(String passwordOld, String passwordNew) {
        if (!isDataInputValid(passwordOld, passwordNew)) {
            return;
        }

        if (currentUser == null) {
            return;
        }

        view.onShowLoading();
        // Auth current user
        AuthCredential credential = EmailAuthProvider
                .getCredential(Objects.requireNonNull(currentUser.getEmail()), passwordOld);

        currentUser.reauthenticate(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Update password
                        currentUser.updatePassword(passwordNew)
                                .addOnCompleteListener(task1 -> {
                                    view.onHideLoading();
                                    if (task1.isSuccessful()) {
                                        view.onChangePasswordSuccess();
                                    }
                                });
                    } else {
                        view.onChangePasswordFailed();
                        view.onHideLoading();
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
