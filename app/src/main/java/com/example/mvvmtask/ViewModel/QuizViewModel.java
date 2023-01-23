package com.example.mvvmtask.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmtask.Interface.ApiServices;
import com.example.mvvmtask.Interface.NetworkHandler;
import com.example.mvvmtask.Model.Quiz.QuizModel;
import com.example.mvvmtask.Model.Quiz.Result;
import com.example.mvvmtask.Model.list.ListModel;
import com.example.mvvmtask.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizViewModel extends AndroidViewModel {
    public MutableLiveData<Result> quizlist=new MutableLiveData<>();
    public MutableLiveData<String>ErrorMsg =new MutableLiveData<>();
    public QuizViewModel(@NonNull Application application) {
        super(application);
    }

    public void loadquestions(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.quiz_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices =retrofit .create(ApiServices.class);
        Call<Result> responsecall = apiServices.getlist();
        responsecall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.e("quizlist", response.body().toString());
                quizlist.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage() );
            }
        });

    }
}
