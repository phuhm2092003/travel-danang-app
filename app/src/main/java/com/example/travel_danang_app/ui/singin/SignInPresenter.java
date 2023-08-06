package com.example.travel_danang_app.ui.singin;

import com.example.travel_danang_app.utils.UtilsDataInput;

import com.google.firebase.auth.FirebaseAuth;

public class SignInPresenter implements SignInContract.Presenter {
    private SignInContract.View view;
    private FirebaseAuth firebaseAuth;

    public SignInPresenter(SignInContract.View view) {
        this.view = view;
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void signIn(String email, String password) {
        if (UtilsDataInput.isEmptyData(email, password)) {
            view.onDataInputEmpty();
            return;
        }

        view.showLoading();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    view.hideLoading();
                    if (task.isSuccessful()) {
                        view.onSignInSuccess();
                    } else {
                        view.onSignInFailed();
                    }
                });
    }
}
