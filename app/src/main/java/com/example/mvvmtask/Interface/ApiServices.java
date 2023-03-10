package com.example.mvvmtask.Interface;

import com.example.mvvmtask.Model.CategoryItemList.CategoryItemListModel;
import com.example.mvvmtask.Model.CategoryList.CategorylistModel;
import com.example.mvvmtask.Model.LoginModel;
import com.example.mvvmtask.Model.Quiz.QuizModel;
import com.example.mvvmtask.Model.Quiz.Result;
import com.example.mvvmtask.Model.RegisterModel;
import com.example.mvvmtask.Model.list.ListModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("api/users")
    Call<ListModel>doGetLists(@Query("page") int page);
    @POST("api/register")
    Call<RegisterModel>doRegister(@Body JsonObject jsonObject);
    @POST("api/login")
    Call<LoginModel>doLogin(@Body JsonObject jsonObject);
    @GET("api/client/master/items/available")
    Call<CategoryItemListModel>getitems(@Header("Authorization") String token, @Query("category_id")int categoryid);
    @GET("api/client/master/category/list")
    Call<CategorylistModel>getcatlist(@Header("Authorization")String token);
    @GET("api.php?amount=25&category=18")
    Call<QuizModel>getlist();
//    @POST("api/client/sales/create")
//    Call<AddItemResponse>SalesCreate_API (@Header("Authorization") String token, @Body JsonObject jsonObject);
//    @POST("api/client/sales/update")
//    Call<AddItemResponse>SalesUpdate_API (@Header("Authorization") String token, @Body JsonObject jsonObject);
//
//    @GET("api/client/sales/edit/{id}")
//    Call<SalesDetailModel> SalesEditAPI(
//            @Header("Authorization") String token,
//            @Path("id") String id);
}
//https://opentdb.com/