package com.example.mvvmtask.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvmtask.Interface.CategoryListlistener;
import com.example.mvvmtask.Interface.CategoryitemListener;
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
    CategoryitemListener categoryitemListener;


    public CategoryitemAdapter(ArrayList<Item> categoryitemlist, Context context, CategoryitemListener categoryitemListener) {
        this.categoryitemlist = categoryitemlist;
        this.context = context;
        this.categoryitemListener = categoryitemListener;
    }

    @NonNull
    @Override
    public CategoryitemAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_list_grid_view, parent, false);
        return new viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryitemAdapter.viewholder holder, int position) {
        int p=position;
        String id = String.valueOf(categoryitemlist.get(position).getCategoryId());
        holder.itemname.setText(categoryitemlist.get(position).getName());
        if (categoryitemlist.get(position).getThumbnail() != null) {
            byte[] bytes = Base64.decode(String.valueOf(categoryitemlist.get(position).getThumbnail()), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            Glide.with(context).load(decodedByte).into(holder.itemimage);
        } else {
            Glide.with(context).load(R.drawable.no_pictures).into(holder.itemimage);
        }
          holder.linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryitemListener.OnClick(categoryitemlist.get(p),holder.getAdapterPosition(),!categoryitemlist.get(p).getSelect());
            }
        });

        if(categoryitemlist.get(p).getSelect()){
            holder.totalcard.setBackgroundColor(ContextCompat.getColor(context,R.color.green)); }
        else
            {holder.totalcard.setBackgroundColor(ContextCompat.getColor(context,R.color.white));}


    }


    @Override
    public int getItemCount() {
        return categoryitemlist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        MaterialCardView totalcard;
        MaterialTextView itemname;
        ImageView itemimage;
        LinearLayout linearlayout;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            itemimage = itemView.findViewById(R.id.img_item);
            itemname = itemView.findViewById(R.id.txt_itemname);
            totalcard = itemView.findViewById(R.id.itemlay);
            linearlayout=itemView.findViewById(R.id.linear_itemview);
        }
    }
}
