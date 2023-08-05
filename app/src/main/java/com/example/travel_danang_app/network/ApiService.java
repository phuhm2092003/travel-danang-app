package com.example.travel_danang_app.network;

import com.example.travel_danang_app.model.AddUserResponse;
import com.example.travel_danang_app.model.GetRoleUserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST(ManaUrl.GET_ROLE_USER)
    Call<GetRoleUserResponse> getRoleUser(@Path("id") String id);
    @FormUrlEncoded
    @POST(ManaUrl.ADD_USER_USER_ROLE)
    Call<AddUserResponse> addUser(@Field("id") String id);
}
