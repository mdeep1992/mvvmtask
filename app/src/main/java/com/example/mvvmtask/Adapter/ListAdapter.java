package com.example.mvvmtask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvmtask.Model.list.Datum;
import com.example.mvvmtask.Model.list.ListModel;
import com.example.mvvmtask.R;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.viewholder> {
    private Context context;
    ArrayList<Datum>list;

    public ListAdapter(Context context, ArrayList<Datum> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ListAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.viewholder holder, int position) {
        holder.id.setText(list.get(position).getId());
        holder.mail.setText(list.get(position).getEmail());
        holder.first_name.setText(list.get(position).getFirstName());
        holder.last_name.setText(list.get(position).getLastName());
        Glide.with(context)
                .load(list.get(position).getAvatar())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        if(list!=null){
        return list.size();
        }return 0;
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView id,mail,first_name,last_name;
        ImageView image;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.id);
            mail=itemView.findViewById(R.id.email);
            first_name=itemView.findViewById(R.id.first_name);
            last_name=itemView.findViewById(R.id.last_name);
            image=itemView.findViewById(R.id.image_list);

        }
    }
}
