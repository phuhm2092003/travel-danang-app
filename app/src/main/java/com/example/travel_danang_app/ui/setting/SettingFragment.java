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

import java.util.Objects;

public class SettingFragment extends Fragment {
    public static final String DIALOG_MESSAGE = "Bạn có muốn đăng xuất?";
    private FragmentSettingBinding settingBinding;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        settingBinding = FragmentSettingBinding.inflate(inflater, container, false);
        return settingBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initObjects();
        fetchCurrentUserInfo();
        setListeners();
    }

    private void initObjects() {
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
    }

    private void fetchCurrentUserInfo() {
        if (currentUser == null) {
            return;
        }
        Glide.with(requireContext())
                .load(currentUser.getPhotoUrl())
                .centerCrop()
                .placeholder(R.drawable.photo_default)
                .into(settingBinding.photoCurrentUser);
        settingBinding.nameCurrentUser.setText(currentUser.getDisplayName());
        settingBinding.emailCurrentUser.setText(currentUser.getEmail());
    }

    private void setListeners() {
        settingBinding.changePassswordButton.setOnClickListener(view -> onChangePasswordButtonClicked());
        settingBinding.logoutButton.setOnClickListener(view -> onLogoutButtonClicked());
    }

    private void onChangePasswordButtonClicked() {
        startActivity(new Intent(requireContext(), ChangePasswordActivity.class));
    }

    private void onLogoutButtonClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(DIALOG_MESSAGE);

        builder.setPositiveButton("Có", (dialogInterface, i) -> {
            firebaseAuth.signOut();
            startActivity(new Intent(requireContext(), SignInActivity.class));
            requireActivity().finishAffinity();
        });

        builder.setNegativeButton("Huỷ", (dialogInterface, i) -> {
        });

        Dialog dialog = builder.create();
        dialog.show();
    }
}