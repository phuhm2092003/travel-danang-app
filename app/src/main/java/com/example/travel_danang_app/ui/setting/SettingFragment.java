package com.example.travel_danang_app.ui.setting;

import android.app.AlertDialog;
import android.app.Dialog;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.travel_danang_app.R;
import com.example.travel_danang_app.databinding.FragmentSettingBinding;
import com.example.travel_danang_app.ui.changepass.ChangePasswordActivity;
import com.example.travel_danang_app.ui.singin.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingFragment extends Fragment {
    private FragmentSettingBinding settingBinding;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        settingBinding = FragmentSettingBinding.inflate(inflater, container, false);
        View view = settingBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        fetchCurrentUserInfo();
        setListeners();
    }

    private void fetchCurrentUserInfo() {
        currentUser= firebaseAuth.getCurrentUser();
        if(currentUser == null){
            return;
        }
        Glide.with(getContext())
                .load(currentUser.getPhotoUrl())
                .centerCrop()
                .placeholder(R.drawable.photo_default)
                .into(settingBinding.photoCurrentUser);
        settingBinding.nameCurrentUser.setText(currentUser.getDisplayName());
        settingBinding.emailCurrentUser.setText(currentUser.getEmail());
    }

    private void setListeners(){
        settingBinding.changePassswordButton.setOnClickListener(view -> onChangePasswordButtonClicked());
        settingBinding.logoutButton.setOnClickListener(view -> onLogoutButtonClicked());
    }

    private void onChangePasswordButtonClicked() {
        startActivity(new Intent(getContext(), ChangePasswordActivity.class));
    }

    private void onLogoutButtonClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Bạn có muốn đăng xuất?");
        builder.setPositiveButton("Có", (dialogInterface, i) -> {
            firebaseAuth.signOut();
            startActivity(new Intent(getContext(), SignInActivity.class));
            getActivity().finish();
        });
        builder.setNegativeButton("Huỷ", (dialogInterface, i) -> {});

        Dialog dialog = builder.create();
        dialog.show();
    }
}