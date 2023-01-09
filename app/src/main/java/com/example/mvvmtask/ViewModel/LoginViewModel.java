package com.example.mvvmtask.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmtask.Model.LoginModel;

public class LoginViewModel extends AndroidViewModel {
    public MutableLiveData<LoginModel>login=new MutableLiveData<>();
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }
}
