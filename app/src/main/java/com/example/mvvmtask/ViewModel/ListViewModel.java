package com.example.mvvmtask.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmtask.Interface.ApiServices;
import com.example.mvvmtask.Interface.ErrorParser;
import com.example.mvvmtask.Model.list.ListModel;
import com.example.mvvmtask.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListViewModel extends AndroidViewModel {
    private MutableLiveData<ListModel> userList;
    private MutableLiveData<String> ErrorMsg;

    public ListViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ListModel> doGetList() {
        if (userList == null) {
            userList = new MutableLiveData<ListModel>();

            loadlist();
        }

        return userList;
    }

    private void loadlist() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServices api = retrofit.create(ApiServices.class);
        Call<ListModel> call = api.doGetLists();
        call.enqueue(new Callback<ListModel>() {
            @Override
            public void onResponse(Call<ListModel> call, Response<ListModel> response) {
                if(response.isSuccessful()){
                    userList.setValue(response.body());
                }else{
                    String errorMsg=  new ErrorParser(response.errorBody()).getMessage();
                    ErrorMsg.setValue(errorMsg);
                }

            }

            @Override
            public void onFailure(Call<ListModel> call, Throwable t) {
                ErrorMsg.setValue("Unknown Error");
            }
        });
    }
}
