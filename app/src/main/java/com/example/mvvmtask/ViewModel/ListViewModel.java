package com.example.mvvmtask.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.mvvmtask.Interface.ApiServices;
import com.example.mvvmtask.Interface.NetworkHandler;
import com.example.mvvmtask.Model.list.ListModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewModel extends AndroidViewModel {
    public MutableLiveData<ListModel> userList=new MutableLiveData<>();
    public MutableLiveData<String>ErrorMsg =new MutableLiveData<>();

    public ListViewModel(@NonNull Application application) {
        super(application);
    }

//    public LiveData<ListModel> doGetList() {
//        if (userList == null) {
//            userList = new MutableLiveData<ListModel>();
//
//            loadlist();
//        }
//
//        return userList;
//    }

    public void loadlist(int page) {

        ApiServices api = NetworkHandler.getRetrofitInstance().create(ApiServices.class);
        Call<ListModel> call = api.doGetLists(page);
        call.enqueue(new Callback<ListModel>() {
            @Override
            public void onResponse(Call<ListModel> call, Response<ListModel> response) {
                if(response.isSuccessful()){
                    Log.e("response",response.body().toString() );
                    userList.setValue(response.body());
                }else{


                    if (response.code()==422){
                        ErrorMsg.setValue(response.errorBody().toString());
                    }else  if (response.code()==401){
                        ErrorMsg.setValue("Invalid Login");
                    }else{
                        ErrorMsg.setValue("unknown error");
                    }
                }

            }

            @Override
            public void onFailure(Call<ListModel> call, Throwable t) {
                ErrorMsg.setValue("Unknown Error");
            }
        });
    }
}
