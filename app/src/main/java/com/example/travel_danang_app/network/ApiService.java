package com.example.travel_danang_app.network;

import com.example.travel_danang_app.model.GetRoleUserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST(ManaUrl.GET_ROLE_USER)
    Call<GetRoleUserResponse> getRoleUser(@Field("id") String id);
}
