package com.example.mvvmtask.View;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
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
    ArrayList<Datum> list = new ArrayList<>();
    Boolean isscrolling = false;
    int visibleitem, totalcount, scrolloutitem;
    LinearLayoutManager manager;
    NestedScrollView scrollView;
    int i = 1;
    Boolean page = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.recycler_list);
        progressBar = findViewById(R.id.progress_list);
        listback = findViewById(R.id.listback);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        doInitViewModel(1);

        listback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                        if (newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
//                            isscrolling=true;
//                        }
                isscrolling = true;
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleitem = manager.getChildCount();
                totalcount = manager.getItemCount();
                scrolloutitem = manager.findFirstVisibleItemPosition();
                Log.e("scroll", "scrolling");
                if (isscrolling && (visibleitem + scrolloutitem == totalcount)) {
                    Log.e("scroll", "scrolled");
                    isscrolling = false;
//                            fetchdata();
                    progressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("page", listViewModel.userList.getValue().getTotalPages().toString());
                            i = i + 1;
                            int totalpages=listViewModel.userList.getValue().getTotalPages();
                                    if (i <=totalpages) {
                                        doInitViewModel(i);
                                    }

                            }

                    }, 2000);
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }

    private void doInitViewModel(int page) {
        listViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        listViewModel.userList.observe(this, new Observer<ListModel>() {
            @Override
            public void onChanged(ListModel listModel) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setHasFixedSize(true);
                manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(manager);
                if (page == 1) {
                    list.clear();
                    list.addAll(listModel.getData());
                    adapter = new ListAdapter(ListActivity.this, list);
                    recyclerView.setAdapter(adapter);
                } else {
//                list.clear();

                    list.addAll(listModel.getData());
                    adapter = new ListAdapter(ListActivity.this, list);
                    recyclerView.setAdapter(adapter);
                }
            }

        });


        listViewModel.loadlist(page);
        listViewModel.ErrorMsg.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(ListActivity.this, listViewModel.ErrorMsg.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}