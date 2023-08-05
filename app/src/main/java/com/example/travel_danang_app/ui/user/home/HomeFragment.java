package com.example.travel_danang_app.ui.user.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.travel_danang_app.R;
import com.example.travel_danang_app.databinding.FragmentHomeBinding;

import java.math.BigInteger;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding homeBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = homeBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpImageSlider();
    }

    private void setUpImageSlider() {
        ArrayList<SlideModel> photos = new ArrayList<>();
        photos.add(new SlideModel("https://liontrip.vn/wp-content/uploads/2022/06/Tour-du-li%CC%A3ch-Da%CC%80-Na%CC%86%CC%83ng-_-Du-li%CC%A3ch-Lion-Trip.png", "", ScaleTypes.FIT));
        photos.add(new SlideModel("https://static.vinwonders.com/production/wkxKquWj-nha-co-hoi-an.jpg", "", ScaleTypes.CENTER_CROP));
        photos.add(new SlideModel("https://static.vinwonders.com/2022/04/cau-rong-da-nang-1-1.jpg", "", ScaleTypes.CENTER_CROP));
        photos.add(new SlideModel("https://reti.vn/blog/wp-content/uploads/2023/04/da-nang-ve-dem.png", "", ScaleTypes.CENTER_CROP));
        photos.add(new SlideModel("https://docs.portal.danang.gov.vn/images/image/hethongduongsong3_1583723140265.jpg", "", ScaleTypes.CENTER_CROP));

        homeBinding.imageSlider.setImageList(photos);
    }
}