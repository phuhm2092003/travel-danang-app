package com.example.travel_danang_app.ui.singin;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.travel_danang_app.model.GetRoleUserResponse;
import com.example.travel_danang_app.network.ApiClient;
import com.example.travel_danang_app.network.ApiService;
import com.example.travel_danang_app.utils.UtilsDataInput;

import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInPresenter implements SignInContract.Presenter {
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    private SignInContract.View view;
    private FirebaseAuth firebaseAuth;

    private ApiService apiService;

    public SignInPresenter(SignInContract.View view) {
        this.view = view;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.apiService = ApiClient.getClient().create(ApiService.class);
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
                        callApiGetRoleUserById();
                    } else {
                        view.onSignInFailed();
                    }
                });
    }

    @Override
    public void callApiGetRoleUserById() {
        String idCurrentUser = firebaseAuth.getCurrentUser().getUid();
        Call<GetRoleUserResponse> call = apiService.getRoleUser(idCurrentUser);
        call.enqueue(new Callback<GetRoleUserResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetRoleUserResponse> call, @NonNull Response<GetRoleUserResponse> response) {
                if (response.isSuccessful()) {
                    handleResponseSuccess(response);
                } else {
                    onCallApiError();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetRoleUserResponse> call, @NonNull Throwable t) {
                onCallApiFailed();
            }
        });
    }

    private void handleResponseSuccess(Response<GetRoleUserResponse> response) {
        GetRoleUserResponse getRoleUserResponse = response.body();
        if (getRoleUserResponse != null) {
            String role = getRoleUserResponse.getRole();
            checkRoleUser(role);
        }
    }

    private void checkRoleUser(String role) {
        if (role.equals(ROLE_ADMIN)) {
            view.onSignInSuccessAsAdmin();
        } else if (role.equals(ROLE_USER)) {
            view.onSignInSuccessAsUser();
        } else {
            // Người dùng không tồn tại trong cở sở dữ liệu
            view.onSignInFailed();
            firebaseAuth.signOut();
        }
    }

    private void onCallApiError() {
        Log.e("TAG", "Call api error");
        firebaseAuth.signOut();
    }

    private void onCallApiFailed() {
        Log.e("TAG", "Call api failed");
        firebaseAuth.signOut();
    }
}
