package com.example.travel_danang_app.ui.favourite;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.travel_danang_app.adapter.LocationAdapter;
import com.example.travel_danang_app.databinding.FragmentFavouriteBinding;
import com.example.travel_danang_app.databinding.FragmentHomeBinding;
import com.example.travel_danang_app.interfaces.ItemLocationClicked;
import com.example.travel_danang_app.model.Location;
import com.example.travel_danang_app.ui.home.HomeFragment;
import com.example.travel_danang_app.ui.location.LocationDetailActivity;

import java.util.ArrayList;

public class FavouriteFragment extends Fragment implements FavourtieContract.View, ItemLocationClicked {
    private FragmentFavouriteBinding favouriteBinding;
    private FavouritePresenter favouritePresenter;
    private LocationAdapter locationAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        favouriteBinding = FragmentFavouriteBinding.inflate(inflater, container, false);
        return favouriteBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initObjects();
        setUpFavouriteLocationsRecyclerView();
    }

    private void initObjects() {
        favouritePresenter = new FavouritePresenter(this);
        favouritePresenter.getFavouriteLocations();
    }

    private void setUpFavouriteLocationsRecyclerView() {
        LinearLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        favouriteBinding.faLocationsRecyclerView.setLayoutManager(layoutManager);

        locationAdapter = new LocationAdapter(this);
        favouriteBinding.faLocationsRecyclerView.setAdapter(locationAdapter);
        favouriteBinding.faLocationsRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void displayFavouriteLocations(ArrayList<Location> locations) {
        if(locations.isEmpty()){
            favouriteBinding.locationsEmptyTextView.setVisibility(View.VISIBLE);
        }else {
            favouriteBinding.locationsEmptyTextView.setVisibility(View.GONE);
        }
        locationAdapter.setListLocation(locations);
    }

    @Override
    public void onFavouriteLocationClicked(boolean isFavourite, int idLocation) {
        if (isFavourite) {
            favouritePresenter.addFavouriteLocation(idLocation);
        } else {
            favouritePresenter.removeFavouriteLocation(idLocation);
        }
    }

    @Override
    public void onLaunchDetailLocationActivity(Location location) {
        Intent intent = new Intent(requireContext(), LocationDetailActivity.class);
        intent.putExtra(HomeFragment.EXTRA_OBJECT_LOCATION, location);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        favouritePresenter.getFavouriteLocations();
    }
}