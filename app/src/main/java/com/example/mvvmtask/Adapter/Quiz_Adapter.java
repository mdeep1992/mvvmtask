package com.example.mvvmtask.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmtask.Interface.Listener;
import com.example.mvvmtask.Model.Quiz.Result;
import com.example.mvvmtask.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz_Adapter extends RecyclerView.Adapter<Quiz_Adapter.viewholder>implements Listener {
    Context context;
    ArrayList<Result> quizlist=new ArrayList<>();
String seletedans;
    LinearLayoutManager manager;
    Quizanans_Adapter adapter;
   String correctAnswer;
   int totalcrtanswer=0;

    public Quiz_Adapter(Context context, ArrayList<Result> quizlist) {
        this.context = context;
        this.quizlist = quizlist;
    }

    @NonNull
    @Override
    public Quiz_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quizquestion_row, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Quiz_Adapter.viewholder holder, int position) {
        holder.question.setText(quizlist.get(position).getQuestion());
        manager = new LinearLayoutManager(context);
        holder.recycler_ques.setHasFixedSize(true);
        holder.recycler_ques.setLayoutManager(manager);
        quizlist.get(position).getIncorrectAnswers().add(quizlist.get(position).getCorrectAnswer());
        List<String> answers=quizlist.get(position).getIncorrectAnswers();
        Collections.shuffle(answers);
        adapter = new Quizanans_Adapter(context, answers,this);
        holder.recycler_ques.setAdapter(adapter);
       correctAnswer=quizlist.get(position).getCorrectAnswer();
//        Log.e("seletedans", seletedans);
        Log.e("correctAnswer", correctAnswer);
       if (correctAnswer.equals(seletedans)){
           totalcrtanswer++;
           Log.e("totalcrtanswer>>>", String.valueOf(totalcrtanswer));
       }else{
           Log.e("totalcrtanswer_status","false");
       }
    }

    @Override
    public int getItemCount() {
        return quizlist.size();
    }

    @Override
    public void oncheck(int position) {
        Log.e("checkposition", String.valueOf(quizlist.get(position)));
      seletedans=(quizlist.get(position).getCorrectAnswer());
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView question;
        RecyclerView recycler_ques;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            question=itemView.findViewById(R.id.question);
            recycler_ques=itemView.findViewById(R.id.recycler_question);
        }
    }
}
