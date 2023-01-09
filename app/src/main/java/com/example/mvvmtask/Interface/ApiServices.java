package com.example.mvvmtask.Interface;

import com.example.mvvmtask.Model.RegisterModel;
import com.example.mvvmtask.Model.list.ListModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {
    @GET("api/users?page=2")
    Call<ListModel>doGetLists();
    @POST("api/register")
    Call<RegisterModel>doRegister(@Body JsonObject jsonObject);
}
