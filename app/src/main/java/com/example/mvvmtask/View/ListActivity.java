package com.example.mvvmtask.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mvvmtask.Adapter.ListAdapter;
import com.example.mvvmtask.Model.list.Datum;
import com.example.mvvmtask.Model.list.ListModel;
import com.example.mvvmtask.R;
import com.example.mvvmtask.ViewModel.ListViewModel;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
RecyclerView recyclerView;
ListAdapter adapter;
ProgressBar progressBar;
    ArrayList<Datum> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.recycler_list);
        progressBar = findViewById(R.id.progress_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListViewModel model = new ViewModelProvider(this).get(ListViewModel.class);
        model.doGetList().observe(this, new Observer<ListModel>() {
            @Override
            public void onChanged(ListModel listModel) {
                progressBar.setVisibility(View.GONE);
                list.clear();
                list.addAll(listModel.getData());
                adapter=new ListAdapter(ListActivity.this,list);
                recyclerView.setAdapter(adapter);
            }
        });

    }
}