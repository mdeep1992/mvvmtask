package com.example.mvvmtask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmtask.Interface.CategoryListlistener;
import com.example.mvvmtask.Model.CategoryList.Category;
import com.example.mvvmtask.Model.CategoryList.CategorylistModel;
import com.example.mvvmtask.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewholder> {
    Context context;
    ArrayList<Category> categorylist;
    CategoryListlistener catlistener;
   int selecteditemposition=0;


    public CategoryAdapter(Context context, ArrayList<Category> categorylist, CategoryListlistener catlistener) {
        this.context = context;
        this.categorylist = categorylist;
        this.catlistener = catlistener;
    }

    @NonNull
    @Override
    public CategoryAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.salecategory_row, parent, false);
        return new viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewholder holder, int position) {
         int id=categorylist.get(position).getId();
         holder.btn.setText(categorylist.get(position).getCategoryName());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecteditemposition = holder.getAdapterPosition();
                catlistener.Itemclicked(id);
            }
        });


        if(selecteditemposition == position) {
            holder.btn.setBackgroundColor(ContextCompat.getColor(context,R.color.primary));
        }  else{
            holder.btn.setBackgroundColor(ContextCompat.getColor(context,R.color.primary_light));
        }
    }

    @Override
    public int getItemCount() {
        return categorylist.size() ;
    }

    public class viewholder extends RecyclerView.ViewHolder {
        MaterialButton btn;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            btn=itemView.findViewById(R.id.category);
        }
    }
}
