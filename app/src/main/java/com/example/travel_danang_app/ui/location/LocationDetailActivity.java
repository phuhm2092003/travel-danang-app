package com.example.travel_danang_app.ui.location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.bumptech.glide.Glide;
import com.example.travel_danang_app.R;
import com.example.travel_danang_app.databinding.ActivityLocationDetailBinding;
import com.example.travel_danang_app.model.Location;

import com.example.travel_danang_app.ui.home.HomeFragment;
import com.example.travel_danang_app.ui.home.HomePresenter;

import java.text.NumberFormat;
import java.util.Locale;

public class LocationDetailActivity extends AppCompatActivity {
    private ActivityLocationDetailBinding binding;
    private LocationDetailPresenter locationDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLocationDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        locationDetailPresenter = new LocationDetailPresenter();
        displayLocationDetail();
        setListeners();
    }

    private void displayLocationDetail() {
        Location location = getLocationDetail();
        Glide.with(this)
                .load(location.getHinhAnh())
                .centerCrop()
                .placeholder(R.drawable.photo_default)
                .into(binding.photo);
        binding.tenDiaDiem.setText(location.getTenDiaDiem());
        binding.viTri.setText(location.getViTri());
        binding.thoiGian.setText(String.format("\uD83D\uDCCD Giờ mở cửa %s", location.getGioMoCua().toLowerCase()));
        binding.giaVe.setText(String.format("\uD83D\uDCCD Giá vé %s / Người", formatPrice(location.getGiaVe())));
        binding.khuyenNghi.setText(String.format("\uD83D\uDCCD Phù hợp với %s", location.getKhuyenNghi().toLowerCase()));
        binding.gioiThieu.setText("\uD83D\uDCCD" + location.getGioiThieu());
        binding.favouriteCheckButton.setChecked(location.getIsFavourite() == 1);
    }

    private Location getLocationDetail() {
        Intent intent = getIntent();
        return (Location) intent.getSerializableExtra(HomeFragment.EXTRA_OBJECT_LOCATION);
    }

    private String formatPrice(String price) {
        double amount = Double.parseDouble(price);
        Locale locale = new Locale("vi", "VN");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

        return numberFormat.format(amount);
    }

    private void setListeners(){
        binding.backButton.setOnClickListener(view -> onBackPressed());
        binding.favouriteCheckButton.setOnClickListener(view -> {
            if(binding.favouriteCheckButton.isChecked()){
                locationDetailPresenter.addFavouriteLocation(getLocationDetail().getId());
            }else {
                locationDetailPresenter.removeFavouriteLocation(getLocationDetail().getId());
            }
        });
    }

}