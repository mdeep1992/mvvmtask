package com.example.mvvmtask.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmtask.Interface.ApiServices;
import com.example.mvvmtask.Model.RegisterModel;
import com.example.mvvmtask.Model.list.ListModel;
import com.example.mvvmtask.Utils;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterViewModel extends AndroidViewModel {
private MutableLiveData<RegisterModel>register=new MutableLiveData<>();
    public RegisterViewModel(@NonNull Application application) {
        super(application);

//        public LiveData<RegisterModel> doRegister() {
//            if (register == null) {
//                register = new MutableLiveData<RegisterModel>();
//
//                doregisterApi(JsonObject jsonObject);
//            }
//
//            return register;
//        }

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
                    Log.e("token", response.body().getToken() );
                    register.setValue(response.body());
                }else{
register.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                Log.e("tokenfailure", t.getLocalizedMessage() );

            }
        });

    }
}
