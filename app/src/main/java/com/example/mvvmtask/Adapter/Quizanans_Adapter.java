package com.example.mvvmtask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmtask.Model.Quiz.Result;
import com.example.mvvmtask.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quizanans_Adapter extends RecyclerView.Adapter<Quizanans_Adapter.viewholder> {
    Context context;
    List<String> quizlist=new ArrayList<>();


    public Quizanans_Adapter(Context context, List<String> quizlist) {
        this.context = context;
        this.quizlist = quizlist;
    }

    @NonNull
    @Override
    public Quizanans_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_row, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Quizanans_Adapter.viewholder holder, int position) {

        holder.checkbox.setText(quizlist.get(position));

    }

    @Override
    public int getItemCount() {
        return quizlist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        CheckBox checkbox;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            checkbox=itemView.findViewById(R.id.checkbox);

        }
    }
}
