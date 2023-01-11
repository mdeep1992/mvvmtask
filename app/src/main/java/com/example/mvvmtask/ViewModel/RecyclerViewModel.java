package com.example.mvvmtask.ViewModel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmtask.Interface.ApiServices;
import com.example.mvvmtask.Interface.NetworkHandler;
import com.example.mvvmtask.Model.CategoryItemList.CategoryItemListModel;
import com.example.mvvmtask.Model.CategoryList.Category;
import com.example.mvvmtask.Model.CategoryList.CategorylistModel;
import com.example.mvvmtask.Model.RegisterModel;
import com.example.mvvmtask.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecyclerViewModel extends AndroidViewModel {
    public MutableLiveData<CategorylistModel>catlist=new MutableLiveData<>();
    public MutableLiveData<CategoryItemListModel>catitemlist=new MutableLiveData<>();

    public RecyclerViewModel(@NonNull Application application) {
        super(application);
    }
public void getcategorylist(){
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Utils.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ApiServices apiServices =retrofit .create(ApiServices.class);
    Call<CategorylistModel> response=apiServices.getcatlist(Utils.token);
    Log.e("token", Utils.token );
    response.enqueue(new Callback<CategorylistModel>() {
        @Override
        public void onResponse(Call<CategorylistModel> call, Response<CategorylistModel> response) {
            Log.e("catlist", response.body().getCategory().toString());
            Log.e("catlist", response.body().toString());
            catlist.setValue( response.body());
        }
        @Override
        public void onFailure(Call<CategorylistModel> call, Throwable t) {
            Log.e("failure", t.getLocalizedMessage() );
        }
    });

}
public void getcategoryitem(int id){
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Utils.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ApiServices apiServices =retrofit .create(ApiServices.class);
    Call<CategoryItemListModel> response=apiServices.getitems(Utils.token);
    response.enqueue(new Callback<CategoryItemListModel>() {
        @Override
        public void onResponse(Call<CategoryItemListModel> call, Response<CategoryItemListModel> response) {
            Log.e("catitemlist", response.body().toString());
            catitemlist.setValue(response.body());
        }

        @Override
        public void onFailure(Call<CategoryItemListModel> call, Throwable t) {
            Log.e("failure", t.getLocalizedMessage() );
        }
    });
}
}
