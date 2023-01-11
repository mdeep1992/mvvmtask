package com.example.mvvmtask.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmtask.Interface.ApiServices;
import com.example.mvvmtask.Interface.NetworkHandler;
import com.example.mvvmtask.Model.ErrorLogin;
import com.example.mvvmtask.Model.LoginModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    public MutableLiveData<LoginModel>login=new MutableLiveData<>();
    public  MutableLiveData<String>ErrorMsg=new MutableLiveData<>();
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void doLogin(JsonObject jsonObject) {
        ApiServices apiServices = NetworkHandler.getRetrofitInstance().create(ApiServices.class);
        Call<LoginModel> call=apiServices.doLogin(jsonObject);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.isSuccessful()) {
                    Log.e("token", response.body().getToken() );
                    login.setValue(response.body());
                }else{

                    ResponseBody responseBody=response.errorBody();
                    String errorBody= String.valueOf(responseBody);
                    try {
                        JSONObject jsonObject1=new JSONObject(errorBody.trim());
                        if(jsonObject1.has("error")){
                            Log.e("error","error");
                            Log.e("error",jsonObject1.getString("error"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    Log.e("error", "onResponse: "+ response.errorBody().toString() );
//                    Log.e("error", "onResponse: "+ response.body().toString());
//                    if (response.code()==400){
//
//                        ErrorLogin errorParser=  new Gson().fromJson(response.errorBody().toString(), ErrorLogin.class);
//                            ErrorMsg.setValue(errorParser.getError());
//
//                        } else  if (response.code()==401){
//                        ErrorMsg.setValue("Invalid Login");
//                    }else{
//                        ErrorMsg.setValue("unknown error");
//                    }
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.e("failure", "onFailure: " + t.getLocalizedMessage() );
                 ErrorMsg.setValue("unknown error");
            }
        });

    }
}


