package com.example.travel_danang_app.ui.search;

import android.util.Log;

import com.example.travel_danang_app.model.FavouriteResponse;
import com.example.travel_danang_app.model.Location;
import com.example.travel_danang_app.network.ApiClient;
import com.example.travel_danang_app.network.ApiService;
import com.example.travel_danang_app.utils.UtilsDataInput;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter implements SearchContract.Presenter {
    private final SearchContract.View view;
    private final ApiService apiService;
    private final FirebaseUser currentUser;
    private String searchInput;

    public SearchPresenter(SearchContract.View view) {
        this.view = view;
        this.apiService = ApiClient.getClient().create(ApiService.class);
        this.currentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void getLocationsSearch(String searchInput) {
        this.searchInput = searchInput;
        ArrayList<Location> listSearch = new ArrayList<>();
        if (!isUserSignIn()) return;
        if (UtilsDataInput.isEmptyData(searchInput)) {
            view.onSearchDisplayLocations(listSearch);
        } else {
            // Get list location
            Call<ArrayList<Location>> call = apiService.getLocations(currentUser.getUid());
            call.enqueue(new Callback<ArrayList<Location>>() {
                @Override
                public void onResponse(Call<ArrayList<Location>> call, Response<ArrayList<Location>> response) {
                    if (response.isSuccessful()) {
                        // Search
                        ArrayList<Location> list = response.body();
                        for (Location location : list){
                            if(location.getTenDiaDiem().toLowerCase().contains(searchInput.toLowerCase())){
                                listSearch.add(location);
                            }
                        }
                        view.onSearchDisplayLocations(listSearch);
                    } else {
                        Log.e("TAG", "Call get location API error: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Location>> call, Throwable t) {
                    Log.e("TAG", "Call get location API failed: " + t);
                }
            });
        }
    }

    @Override
    public void addFavouriteLocation(int idLocation) {
        if (!isUserSignIn()) return;
        Call<FavouriteResponse> call = apiService.addFavourite(currentUser.getUid(), idLocation);
        call.enqueue(new Callback<FavouriteResponse>() {
            @Override
            public void onResponse(Call<FavouriteResponse> call, Response<FavouriteResponse> response) {
                if (response.isSuccessful()) {
                    boolean status = response.body().isStatus();
                    if (status) {
                        Log.e("TAG", "Add favourte success");
                        getLocationsSearch(searchInput);
                    } else {
                        Log.e("TAG", "Add favourte failed");
                    }
                } else {
                    Log.e("TAG", "Add favourte error");
                }
            }

            @Override
            public void onFailure(Call<FavouriteResponse> call, Throwable t) {
                Log.e("TAG", "Call api AddFavouriteLocation falied");
            }
        });
    }

    @Override
    public void removeFavouriteLocation(int idLocation) {
        if (!isUserSignIn()) return;
        Call<FavouriteResponse> call = apiService.removeFavourite(currentUser.getUid(), idLocation);
        call.enqueue(new Callback<FavouriteResponse>() {
            @Override
            public void onResponse(Call<FavouriteResponse> call, Response<FavouriteResponse> response) {
                if (response.isSuccessful()) {
                    boolean status = response.body().isStatus();
                    if (status) {
                        Log.e("TAG", "Remove favourte success");
                        getLocationsSearch(searchInput);
                    } else {
                        Log.e("TAG", "Remove favourte failed");
                    }
                } else {
                    Log.e("TAG", "Remove favourte error");
                }
            }

            @Override
            public void onFailure(Call<FavouriteResponse> call, Throwable t) {
                Log.e("TAG", "Remove api RemoveFavouriteLocation falied");
            }
        });
    }

    private boolean isUserSignIn() {
        return currentUser != null;
    }
}
