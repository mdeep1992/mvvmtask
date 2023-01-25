package com.example.mvvmtask.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmtask.Interface.ApiServices;
import com.example.mvvmtask.Interface.NetworkHandler;
import com.example.mvvmtask.Model.RegisterModel;
import com.example.mvvmtask.Utils;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterViewModel extends AndroidViewModel {
public MutableLiveData<RegisterModel>register= new MutableLiveData<>();
public MutableLiveData<String>ErrorMsg=new MutableLiveData<>();
    public RegisterViewModel(@NonNull Application application) {
        super(application);

    }

 public void doregisterApi(JsonObject jsonObject) {
     ApiServices apiServices = NetworkHandler.getRetrofitInstance().create(ApiServices.class);
        Call<RegisterModel>call=apiServices.doRegister(jsonObject);
        call.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                if (response.isSuccessful()) {
                    Log.e("token", response.body().getToken() );
                    register.setValue(response.body());
                }else{
                    Log.e("error", "onResponse: "+ response.errorBody().toString() );
                    if (response.code()==422){
                    ErrorMsg.setValue(response.errorBody().toString());
                    }else {
                        ErrorMsg.setValue("unknown error");
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                Log.e("failure", "onFailure: " + t.getLocalizedMessage() );
                ErrorMsg.setValue("unknown error");
            }
        });

    }
}
