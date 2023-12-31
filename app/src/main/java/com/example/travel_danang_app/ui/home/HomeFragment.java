package com.example.travel_danang_app.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import com.example.travel_danang_app.ui.search.SearchActivity;
import com.example.travel_danang_app.adapter.LocationAdapter;
import com.example.travel_danang_app.databinding.FragmentHomeBinding;
import com.example.travel_danang_app.interfaces.ItemLocationClicked;
import com.example.travel_danang_app.model.Location;
import com.example.travel_danang_app.ui.location.LocationDetailActivity;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.View, ItemLocationClicked {
    public static final String EXTRA_OBJECT_LOCATION = "LocationDetail";
    private FragmentHomeBinding homeBinding;
    private HomePresenter homePresenter;
    private LocationAdapter locationAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return homeBinding.getRoot();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initObjects();
        setListImageSlider();
        setUplocationsRecyclerView();

        homeBinding.searchEditText.setOnTouchListener((view1, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                launchSearchActivity();
            }
            return false;
        });
    }

    private void launchSearchActivity() {
        startActivity(new Intent(requireContext(), SearchActivity.class));
    }

    private void initObjects() {
        homePresenter = new HomePresenter(this);
        homePresenter.getLocations();
    }

    private void setListImageSlider() {
        ArrayList<SlideModel> photos = getPhotos();
        homeBinding.imageSlider.setImageList(photos);
    }

    @NonNull
    private ArrayList<SlideModel> getPhotos() {
        ArrayList<SlideModel> photos = new ArrayList<>();
        photos.add(new SlideModel("https://liontrip.vn/wp-content/uploads/2022/06/Tour-du-li%CC%A3ch-Da%CC%80-Na%CC%86%CC%83ng-_-Du-li%CC%A3ch-Lion-Trip.png", "", ScaleTypes.FIT));
        photos.add(new SlideModel("https://static.vinwonders.com/production/wkxKquWj-nha-co-hoi-an.jpg", "", ScaleTypes.CENTER_CROP));
        photos.add(new SlideModel("https://static.vinwonders.com/2022/04/cau-rong-da-nang-1-1.jpg", "", ScaleTypes.CENTER_CROP));
        photos.add(new SlideModel("https://reti.vn/blog/wp-content/uploads/2023/04/da-nang-ve-dem.png", "", ScaleTypes.CENTER_CROP));
        photos.add(new SlideModel("https://docs.portal.danang.gov.vn/images/image/hethongduongsong3_1583723140265.jpg", "", ScaleTypes.CENTER_CROP));
        return photos;
    }

    private void setUplocationsRecyclerView() {
        LinearLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        homeBinding.locationsRecyclerView.setLayoutManager(layoutManager);

        locationAdapter = new LocationAdapter(this);
        homeBinding.locationsRecyclerView.setAdapter(locationAdapter);
        homeBinding.locationsRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void displayLocations(List<Location> locations) {
        locationAdapter.setListLocation(locations);
    }

    @Override
    public void onFavouriteLocationClicked(boolean isFavourite, int idLocation) {
        if (isFavourite) {
            homePresenter.addFavouriteLocation(idLocation);
        } else {
            homePresenter.removeFavouriteLocation(idLocation);
        }
    }

    @Override
    public void onLaunchDetailLocationActivity(Location location) {
        Intent intent = new Intent(getContext(), LocationDetailActivity.class);
        intent.putExtra(EXTRA_OBJECT_LOCATION, location);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        homePresenter.getLocations();
    }
}