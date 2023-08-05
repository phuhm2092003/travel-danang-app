package com.example.travel_danang_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;

import com.example.travel_danang_app.databinding.ActivityMainBinding;
import com.example.travel_danang_app.ui.user.FavouriteFragment;
import com.example.travel_danang_app.ui.user.home.HomeFragment;
import com.example.travel_danang_app.ui.user.SettingFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        switchFragment(new HomeFragment());

        mainBinding.bottomMenuNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_home:
                    switchFragment(new HomeFragment());
                    setBottonNavIcons(R.drawable.ic_home_fill, R.drawable.ic_favourite_line, R.drawable.ic_setting_line);
                    return true;
                case R.id.menu_favourite:
                    switchFragment(new FavouriteFragment());
                    setBottonNavIcons(R.drawable.ic_home_line, R.drawable.ic_favourite_fill, R.drawable.ic_setting_line);
                    return true;
                case R.id.menu_setting:
                    switchFragment(new SettingFragment());
                    setBottonNavIcons(R.drawable.ic_home_line, R.drawable.ic_favourite_line, R.drawable.ic_setting_fill);
                    return true;
                default:
                    return false;
            }
        });
    }

    private void setBottonNavIcons(int imgResourceHome, int imgResourceFavourite, int imgResourceSetting) {
        mainBinding.bottomMenuNavigation.getMenu().findItem(R.id.menu_home).setIcon(imgResourceHome);
        mainBinding.bottomMenuNavigation.getMenu().findItem(R.id.menu_favourite).setIcon(imgResourceFavourite);
        mainBinding.bottomMenuNavigation.getMenu().findItem(R.id.menu_setting).setIcon(imgResourceSetting);
    }

    public void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }
}