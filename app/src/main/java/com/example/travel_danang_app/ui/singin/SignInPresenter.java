package com.example.travel_danang_app.ui.singin;

import com.example.travel_danang_app.utils.UtilsDataInput;

import com.google.firebase.auth.FirebaseAuth;

public class SignInPresenter implements SignInContract.Presenter {
    private final SignInContract.View view;

    public SignInPresenter(SignInContract.View view) {
        this.view = view;
    }

    @Override
    public void signIn(String email, String password) {
        if (!isValidDataInput(email, password)) {
            return;
        }

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        view.onShowLoading();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    view.onHideLoading();
                    if (task.isSuccessful()) {
                        view.onSignInSuccess();
                    } else {
                        view.onSignInFailed();
                    }
                });
    }

    private boolean isValidDataInput(String email, String password) {
        if (UtilsDataInput.isEmptyData(email, password)) {
            view.onDataInputEmpty();
            return false;
        }
        return true;
    }
}
