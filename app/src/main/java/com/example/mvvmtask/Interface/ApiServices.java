package com.example.mvvmtask.Interface;

import com.example.mvvmtask.Model.list.Datum;
import com.example.mvvmtask.Model.list.ListModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {
    @GET("api/users?page=2")
    Call<ListModel>doGetLists();

}
