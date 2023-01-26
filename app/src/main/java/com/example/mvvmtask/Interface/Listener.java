package com.example.mvvmtask.Interface;

import com.example.mvvmtask.Model.Quiz.Model;

import java.util.ArrayList;

public interface Listener {
    void oncheck(ArrayList<Model>qlist,int position);
}
