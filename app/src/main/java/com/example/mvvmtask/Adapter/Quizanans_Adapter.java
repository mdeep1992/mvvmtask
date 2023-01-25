package com.example.mvvmtask.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmtask.Interface.Listener;
import com.example.mvvmtask.Model.Quiz.Result;
import com.example.mvvmtask.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quizanans_Adapter extends RecyclerView.Adapter<Quizanans_Adapter.viewholder> {
    Context context;
    List<String> quizlist = new ArrayList<>();
    Listener listener;


    public Quizanans_Adapter(Context context, List<String> quizlist,Listener listener) {
        this.context = context;
        this.quizlist = quizlist;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Quizanans_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_row, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Quizanans_Adapter.viewholder holder, int position) {
        int p = position;
        Log.e("checkadapter",  "check adapter");
        holder.checkbox.setText(quizlist.get(position));
        Log.e("checkboxsettext",quizlist.get(position) );
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (holder.checkbox.isChecked()) {
                    Log.e("checkposition",  ">>>>>>>>>>");
                    listener.oncheck(p);
                } else {
                    Toast.makeText(context, "choose any one", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return quizlist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        CheckBox checkbox;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.checkbox);

        }
    }
}
