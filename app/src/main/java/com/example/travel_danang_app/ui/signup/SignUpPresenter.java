package com.example.travel_danang_app.ui.signup;

import android.net.Uri;

import com.example.travel_danang_app.utils.UtilsDataInput;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

public class SignUpPresenter implements SignUpContract.Presenter {
    private SignUpContract.View view;
    private FirebaseAuth firebaseAuth;

    public SignUpPresenter(SignUpContract.View view) {
        this.view = view;
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void signUp(String email, String fullname, String password) {
        if (!isValidDataInput(email, fullname, password)) {
            return;
        }
        view.showLoading();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    view.hideLoading();
                    if (task.isSuccessful()) {
                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(fullname)
                                .setPhotoUri(Uri.parse("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/271deea8-e28c-41a3-aaf5-2913f5f48be6/de7834s-6515bd40-8b2c-4dc6-a843-5ac1a95a8b55.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzI3MWRlZWE4LWUyOGMtNDFhMy1hYWY1LTI5MTNmNWY0OGJlNlwvZGU3ODM0cy02NTE1YmQ0MC04YjJjLTRkYzYtYTg0My01YWMxYTk1YThiNTUuanBnIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.BopkDn1ptIwbmcKHdAOlYHyAOOACXW0Zfgbs0-6BY-E"))
                                .build();

                        currentUser.updateProfile(profileUpdates)
                                .addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {

                                    }
                                });
                        view.signUpSuccess();
                        firebaseAuth.signOut();
                    } else {
                        view.signUpFailed(Objects.requireNonNull(task.getException()).toString());
                    }
                });
    }

    public boolean isValidDataInput(String email, String fullname, String password) {
        if (UtilsDataInput.isEmptyData(email, fullname, password)) {
            view.onDataInputEmpty();
            return false;
        }

        if (!UtilsDataInput.isEmailValid(email)) {
            view.onEmailInValid();
            return false;
        }

        if (!UtilsDataInput.isPasswordLengthValid(password)) {
            view.onPasswordLengthInValid();
            return false;
        }

        if (!UtilsDataInput.isString(fullname)) {
            view.onFullNameInvalid();
            return false;
        }

        return true;
    }

}
