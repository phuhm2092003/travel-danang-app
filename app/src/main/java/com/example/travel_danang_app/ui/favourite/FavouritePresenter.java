package com.example.travel_danang_app.ui.favourite;

import android.util.Log;

import com.example.travel_danang_app.model.FavouriteResponse;
import com.example.travel_danang_app.model.Location;
import com.example.travel_danang_app.network.ApiClient;
import com.example.travel_danang_app.network.ApiService;
import com.example.travel_danang_app.ui.home.HomeContract;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouritePresenter implements FavourtieContract.Presenter {
    private final FavourtieContract.View view;
    private final ApiService apiService;
    private final FirebaseUser currentUser;

    public FavouritePresenter(FavourtieContract.View view) {
        this.view = view;
        this.apiService = ApiClient.getClient().create(ApiService.class);
        this.currentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void getFavouriteLocations() {
        if (!isUserSignIn()) return;
        Call<ArrayList<Location>> call = apiService.getFavouriteLocations(currentUser.getUid());
        call.enqueue(new Callback<ArrayList<Location>>() {
            @Override
            public void onResponse(Call<ArrayList<Location>> call, Response<ArrayList<Location>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Location> locations = response.body();
                    view.onDisplayFavouriteLocations(locations);
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
                        Log.e("TAG", "Add favourte success");
                        getFavouriteLocations(); // Load location list
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
                        getFavouriteLocations(); // Load location list
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
