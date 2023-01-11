package com.example.mvvmtask.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

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
ImageView listback;
    ListViewModel listViewModel;
    ArrayList<Datum> list=new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.recycler_list);
        progressBar = findViewById(R.id.progress_list);
        listback=findViewById(R.id.listback);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        doInitViewModel();

listback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        onBackPressed();
    }
});
    }

    private void doInitViewModel() {
        listViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        listViewModel.doGetList().observe(this, new Observer<ListModel>() {
            @Override
            public void onChanged(ListModel listModel) {
                progressBar.setVisibility(View.GONE);
                list.clear();
                list.addAll(listModel.getData());
                adapter=new ListAdapter(ListActivity.this,list);
                recyclerView.setAdapter(adapter);
            }
        });
        listViewModel.ErrorMsg.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(ListActivity.this,listViewModel.ErrorMsg.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}