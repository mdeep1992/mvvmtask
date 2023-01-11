package com.example.mvvmtask.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mvvmtask.Adapter.CategoryAdapter;
import com.example.mvvmtask.Adapter.CategoryitemAdapter;
import com.example.mvvmtask.Adapter.ListAdapter;
import com.example.mvvmtask.Interface.CategoryListlistener;
import com.example.mvvmtask.Model.CategoryItemList.CategoryItemListModel;
import com.example.mvvmtask.Model.CategoryItemList.Item;
import com.example.mvvmtask.Model.CategoryList.Category;
import com.example.mvvmtask.Model.CategoryList.CategorylistModel;
import com.example.mvvmtask.Model.list.Datum;
import com.example.mvvmtask.Model.list.ListModel;
import com.example.mvvmtask.R;
import com.example.mvvmtask.ViewModel.ListViewModel;
import com.example.mvvmtask.ViewModel.RecyclerViewModel;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {
    RecyclerView categorylistrecycler, categoryitem_recycler;
    CategoryAdapter catlistadapter;
    CategoryitemAdapter categoryitemAdapter;
    ProgressBar progressBar;
    RecyclerViewModel recyclerViewModel;
    ArrayList<Category> categorylist = new ArrayList<>();
    ArrayList<Item> catitemlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        doInitContent();
        doInitViewmodel();

    }

    private void doInitViewmodel() {
        recyclerViewModel = new ViewModelProvider(this).get(RecyclerViewModel.class);
        recyclerViewModel.getcategorylist();
        recyclerViewModel.catitemlist.observe(this, new Observer<CategoryItemListModel>() {
            @Override
            public void onChanged(CategoryItemListModel categoryItemListModel) {
                final LinearLayoutManager[] manager = {new GridLayoutManager(getApplicationContext(),3,RecyclerView.VERTICAL,false)};
                categoryitem_recycler.setHasFixedSize(true);
                categoryitem_recycler.setLayoutManager(manager[0]);
                catitemlist.clear();
                catitemlist.addAll(categoryItemListModel.getItems());
                categoryitemAdapter=new CategoryitemAdapter(catitemlist,getApplicationContext());
                categoryitem_recycler.setAdapter(categoryitemAdapter);
                categoryitemAdapter.notifyDataSetChanged();
            }
        });


        recyclerViewModel.catlist.observe(this, new Observer<CategorylistModel>() {
            @Override
            public void onChanged(CategorylistModel category) {
                final LinearLayoutManager[] manager = {new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false)};
                categorylistrecycler.setHasFixedSize(true);
                categorylistrecycler.setLayoutManager(manager[0]);
                categorylist.clear();
                categorylist.addAll(category.getCategory());
                catlistadapter=new CategoryAdapter(getApplicationContext(),categorylist, new CategoryListlistener() {
                    @Override
                    public void Itemclicked(int id) {

                        recyclerViewModel.getcategoryitem(id);
                    }
                });
                categorylistrecycler.setAdapter(catlistadapter);
                catlistadapter.notifyDataSetChanged();
            }

        });



    }

    private void doInitContent() {
        categorylistrecycler = findViewById(R.id.category);
        categoryitem_recycler = findViewById(R.id.recycler_item_view);
        progressBar = findViewById(R.id.progressBar);
    }
}