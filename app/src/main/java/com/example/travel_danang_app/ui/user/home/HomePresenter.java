package com.example.travel_danang_app.ui.user.home;


import android.util.Log;

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
    private HomeContract.View view;
    private ApiService apiService;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
        this.apiService = ApiClient.getClient().create(ApiService.class);
    }

    @Override
    public void onCallGetLocationsApi() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            return;
        }
        Call<ArrayList<Location>> call = apiService.getLocations(currentUser.getUid());
        call.enqueue(new Callback<ArrayList<Location>>() {
            @Override
            public void onResponse(Call<ArrayList<Location>> call, Response<ArrayList<Location>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Location> list = response.body();
                    view.onDisplayLocations(list);
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
