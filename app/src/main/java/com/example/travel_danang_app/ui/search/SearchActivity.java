package com.example.travel_danang_app.ui.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.travel_danang_app.R;
import com.example.travel_danang_app.adapter.LocationAdapter;
import com.example.travel_danang_app.databinding.ActivitySearchBinding;
import com.example.travel_danang_app.interfaces.ItemLocationClicked;
import com.example.travel_danang_app.model.Location;
import com.example.travel_danang_app.ui.home.HomeFragment;
import com.example.travel_danang_app.ui.location.LocationDetailActivity;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchContract.View, ItemLocationClicked {
    private ActivitySearchBinding searchBinding;
    private SearchContract.Presenter searchPresenter;
    private LocationAdapter locationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchBinding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(searchBinding.getRoot());

        setUpRecyclerView();
        searchPresenter = new SearchPresenter(this);
        setListeners();

    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        searchBinding.locationsSearchRecyclerView.setLayoutManager(layoutManager);

        locationAdapter = new LocationAdapter(this);
        searchBinding.locationsSearchRecyclerView.setAdapter(locationAdapter);
        searchBinding.locationsSearchRecyclerView.setNestedScrollingEnabled(false);
    }

    private void setListeners() {
        searchBinding.searchButton.setOnClickListener(view -> onSearchButtonClicked());
        searchBinding.backButton.setOnClickListener(view -> onBackPressed());
    }

    private void onSearchButtonClicked() {
        String searchInput = searchBinding.searchEditText.getText().toString().trim();
        searchPresenter.getLocationsSearch(searchInput);
    }

    @Override
    public void onSearchDisplayLocations(List<Location> locations) {
        if (locations.isEmpty()) {
            searchBinding.locationsEmptyTextView.setVisibility(View.VISIBLE);
        } else {
            searchBinding.locationsEmptyTextView.setVisibility(View.GONE);
        }
        locationAdapter.setListLocation(locations);
    }

    @Override
    public void onFavouriteLocationClicked(boolean isFavourite, int idLocation) {
        if (isFavourite) {
            searchPresenter.addFavouriteLocation(idLocation);
        } else {
            searchPresenter.removeFavouriteLocation(idLocation);
        }
    }

    @Override
    public void onLaunchDetailLocationActivity(Location location) {
        Intent intent = new Intent(this, LocationDetailActivity.class);
        intent.putExtra(HomeFragment.EXTRA_OBJECT_LOCATION, location);
        startActivity(intent);
    }
}