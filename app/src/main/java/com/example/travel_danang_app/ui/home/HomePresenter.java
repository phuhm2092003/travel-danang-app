package com.example.travel_danang_app.ui.home;


import android.util.Log;

import com.example.travel_danang_app.model.FavouriteResponse;
import com.example.travel_danang_app.model.Location;
import com.example.travel_danang_app.network.ApiClient;
import com.example.travel_danang_app.network.ApiService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter {
    private final HomeContract.View view;
    private final ApiService apiService;
    private final FirebaseUser currentUser;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
        this.apiService = ApiClient.getClient().create(ApiService.class);
        this.currentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    private boolean isUserSignIn() {
        return currentUser != null;
    }

    @Override
    public void getLocations() {
        if (!isUserSignIn()) return;
        Call<ArrayList<Location>> call = apiService.getLocations(currentUser.getUid());
        call.enqueue(new Callback<ArrayList<Location>>() {
            @Override
            public void onResponse(Call<ArrayList<Location>> call, Response<ArrayList<Location>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Location> list = response.body();
                    view.displayLocations(list);
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
                        Log.e("TAG", "Add favourite success");
                        getLocations();
                    } else {
                        Log.e("TAG", "Add favourite failed");
                    }
                } else {
                    Log.e("TAG", "Add favourite error");
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
                        Log.e("TAG", "Remove favourite success");
                        getLocations();
                    } else {
                        Log.e("TAG", "Remove favourite failed");
                    }
                } else {
                    Log.e("TAG", "Remove favourite error");
                }
            }

            @Override
            public void onFailure(Call<FavouriteResponse> call, Throwable t) {
                Log.e("TAG", "Remove api RemoveFavouriteLocation falied");
            }
        });
    }
}
