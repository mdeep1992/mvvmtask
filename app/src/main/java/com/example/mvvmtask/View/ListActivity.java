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
import android.view.ViewTreeObserver;
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
    ProgressBar progressBar, progress_page;
    ImageView listback;
    ListViewModel listViewModel;
    ArrayList<Datum> list = new ArrayList<>();
    Boolean isscrolling = false;
    int visibleitem, totalcount, scrolloutitem;
    LinearLayoutManager manager;
    int i = 1;
    Boolean apiLoading = false;
    NestedScrollView scrollview;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.recycler_list);
        progressBar = findViewById(R.id.progress_list);
        listback = findViewById(R.id.listback);
        progress_page = findViewById(R.id.progress_recycler);
        scrollview = findViewById(R.id.scrollview);
        doInitViewModel();

        scrollview.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                View view=scrollview.getChildAt(scrollview.getChildCount()-1);

                int diff= (view.getBottom()-(scrollview.getHeight()+scrollview.getScrollY()));

                if(diff==0){
                    if (!apiLoading) {
//                    Log.e("scroll", "scrolled");
                        isscrolling = true;
//                        if (i<=1){
//                        progressBar.setVisibility(View.VISIBLE);}else{
//                            progress_page.setVisibility(View.VISIBLE);
//                        }
                        i = i + 1;

                        Log.e("i>>>", String.valueOf(i));
                        int totalpages = listViewModel.userList.getValue().getTotalPages();
                        progressBar.setVisibility(View.GONE);
                        progress_page.setVisibility(View.GONE);
                        if (i <= totalpages) {
                            Log.e("condition check i>>", String.valueOf(i));
                            callAPi(i);
                        }

                    }

                }
            }
        });

        listback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void callAPi(int page) {
        apiLoading = true;
        progress_page.setVisibility(View.VISIBLE);
        listViewModel.loadlist(page);
        Log.e("pageno_2", String.valueOf(page));
    }

    private void doInitViewModel() {
        progressBar.setVisibility(View.VISIBLE);
        listViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        listViewModel.userList.observe(this, new Observer<ListModel>() {
            @Override
            public void onChanged(ListModel listModel) {
                apiLoading = false;
                progressBar.setVisibility(View.GONE);
                recyclerView.setHasFixedSize(true);
                manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(manager);
                Log.e("pageno1", String.valueOf(i));
                if (i == 1) {

                    progress_page.setVisibility(View.VISIBLE);
                    list.clear();
                    Log.e("Apicall1", "response>>>>1");
                    list.addAll(listModel.getData());
                    adapter = new ListAdapter(ListActivity.this, list);
                    recyclerView.setAdapter(adapter);

                } else {
                    progressBar.setVisibility(View.GONE);
                    progress_page.setVisibility(View.VISIBLE);
                    Log.e("Apicall2", "response>2");
                    list.addAll(listModel.getData());
                    adapter = new ListAdapter(ListActivity.this, list);
                    recyclerView.setAdapter(adapter);

                }
            }

        });
        listViewModel.loadlist(i);

        listViewModel.ErrorMsg.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(ListActivity.this, listViewModel.ErrorMsg.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}