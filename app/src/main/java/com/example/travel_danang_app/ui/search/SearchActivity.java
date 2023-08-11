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
        searchBinding.searchButton.setOnClickListener(view ->{
            String searchInput = searchBinding.searchEditText.getText().toString().toString();
            searchPresenter.getLocations(searchInput);
        });
        searchBinding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        searchBinding.locationsSearchRecyclerView.setLayoutManager(layoutManager);

        locationAdapter = new LocationAdapter(this);
        searchBinding.locationsSearchRecyclerView.setAdapter(locationAdapter);
        searchBinding.locationsSearchRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void onSearchDisplayLocations(List<Location> locations) {
        if(locations.isEmpty() || locations.size() == 0){
            searchBinding.locationsEmptyTextView.setVisibility(View.VISIBLE);
            locationAdapter.setListLocation(null);
        }else {
            searchBinding.locationsEmptyTextView.setVisibility(View.GONE);
            locationAdapter.setListLocation(locations);
        }
    }

    @Override
    public void onFavouriteLocationClick(boolean isFavourite, int idLocation) {
        if (isFavourite) {
            searchPresenter.addFavouriteLocation(idLocation);
        } else {
            searchPresenter.removeFavouriteLocation(idLocation);
        }
    }

    @Override
    public void onLaucnhDetailLocationActivity(Location location) {
        Intent intent = new Intent(this, LocationDetailActivity.class);
        intent.putExtra(HomeFragment.EXTRA_OBJECT_LOCATION, location);
        startActivity(intent);
    }
}