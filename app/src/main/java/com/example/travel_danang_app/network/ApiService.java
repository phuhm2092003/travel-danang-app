package com.example.travel_danang_app.network;

import com.example.travel_danang_app.model.Location;

import java.util.ArrayList;


import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST(ManaUrl.GET_LOCATIONS_URL)
    Call<ArrayList<Location>> getLocations(@Field("idUser") String idUser);
}
