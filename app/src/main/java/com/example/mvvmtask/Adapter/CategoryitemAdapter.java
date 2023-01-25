package com.example.mvvmtask.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvmtask.Interface.CategoryListlistener;
import com.example.mvvmtask.Model.CategoryItemList.CategoryItemListModel;
import com.example.mvvmtask.Model.CategoryItemList.Item;
import com.example.mvvmtask.Model.CategoryList.CategorylistModel;
import com.example.mvvmtask.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class CategoryitemAdapter extends RecyclerView.Adapter<CategoryitemAdapter.viewholder> {
    ArrayList<Item> categoryitemlist;
    Context context;

    public CategoryitemAdapter(ArrayList<Item> categoryitemlist, Context context) {
        this.categoryitemlist = categoryitemlist;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryitemAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_list_grid_view, parent, false);
        return new viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryitemAdapter.viewholder holder, int position) {
        String id = String.valueOf(categoryitemlist.get(position).getCategoryId());
        holder.itemname.setText(categoryitemlist.get(position).getName());
        if (categoryitemlist.get(position).getThumbnail() != null) {
            byte[] bytes = Base64.decode(String.valueOf(categoryitemlist.get(position).getThumbnail()), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            Glide.with(context).load(decodedByte).into(holder.itemimage);
        } else {
            Glide.with(context).load(R.drawable.no_pictures).into(holder.itemimage);
        }
    }

    @Override
    public int getItemCount() {
        return categoryitemlist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        MaterialCardView totalcard;
        MaterialTextView itemname;
        ImageView itemimage;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            itemimage = itemView.findViewById(R.id.img_item);
            itemname = itemView.findViewById(R.id.txt_itemname);
            totalcard = itemView.findViewById(R.id.itemlay);
        }
    }
}
