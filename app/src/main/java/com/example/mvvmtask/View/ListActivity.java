package com.example.mvvmtask.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.example.mvvmtask.Adapter.ListAdapter;
import com.example.mvvmtask.R;
import com.example.mvvmtask.ViewModel.ListViewModel;

public class ListActivity extends AppCompatActivity {
RecyclerView recyclerView;
ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        recyclerView = findViewById(R.id.recycler_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

                ListViewModel model = ViewModelProviders.of(this).get(ListViewModel.class);
    }
}