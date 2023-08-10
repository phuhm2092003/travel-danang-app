package com.example.travel_danang_app.network;

import com.example.travel_danang_app.model.FavouriteResponse;
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

    @FormUrlEncoded
    @POST(ManaUrl.GET_FAVOURITE_LOCATIONS_URL)
    Call<ArrayList<Location>> getFavouriteLocations(@Field("idUser") String idUser);

    @FormUrlEncoded
    @POST(ManaUrl.ADD_FAVOURITE_URL)
    Call<FavouriteResponse> addFavourite(@Field("idUser") String idUser, @Field("idLocation") int idLocation);

    @FormUrlEncoded
    @POST(ManaUrl.REMOVE_FAVOURITE_URL)
    Call<FavouriteResponse> removeFavourite(@Field("idUser") String idUser, @Field("idLocation") int idLocation);
}
