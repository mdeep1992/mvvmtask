package com.example.mvvmtask.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmtask.Interface.ApiServices;
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
private MutableLiveData<RegisterModel>register= new MutableLiveData<>();
    public RegisterViewModel(@NonNull Application application) {
        super(application);

    }

 public void doregisterApi(JsonObject jsonObject) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices= retrofit.create(ApiServices.class);
        Call<RegisterModel>call=apiServices.doRegister(jsonObject);
        call.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                if (response.isSuccessful()) {
                    register.setValue(response.body());
                }else{

                }
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {

            }
        });

    }
}
