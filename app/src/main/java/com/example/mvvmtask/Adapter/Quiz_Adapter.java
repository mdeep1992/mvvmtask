package com.example.mvvmtask.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmtask.Interface.Listener;
import com.example.mvvmtask.Model.Quiz.Model;
import com.example.mvvmtask.Model.Quiz.Result;
import com.example.mvvmtask.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz_Adapter extends RecyclerView.Adapter<Quiz_Adapter.viewholder> {
    Context context;
    ArrayList<Result> quizlist;
    String correctAnswer;
    int totalcrtanswer = 0;
    Listener listener;

    String Selectedtext, Select_text;
    int lastposition = 0;
    ArrayList<Model> qlist = new ArrayList<>();

    public Quiz_Adapter(Context context, ArrayList<Result> quizlist, Listener listener) {
        this.context = context;
        this.quizlist = quizlist;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Quiz_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quizquestion_row, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Quiz_Adapter.viewholder holder, int position) {
        int p = position;
        holder.setIsRecyclable(false);
        holder.question.setText(quizlist.get(position).getQuestion());


        List<String> ans = quizlist.get(position).getIncorrectAnswers();
        ans.add(quizlist.get(position).getCorrectAnswer());
        Collections.shuffle(ans);
        int id = 0;
        for (String answers : ans) {
            RadioButton radioButton = new RadioButton(context);
            radioButton.setId(id);
            id++;
            radioButton.setText(answers);
            holder.radiogroup.addView(radioButton);
        }

        holder.radiogroup.setOnCheckedChangeListener((radioGroup, i) -> {
//                Log.e("ans", ans.get(i));
            Selectedtext = ans.get(i);
            Boolean alreadyselected=false;
            for(Model selectedData : qlist){
                Log.e("selectedDataposition", String.valueOf(selectedData.getPosition()));
                Log.e("position", String.valueOf(position));
                if(selectedData.getPosition()==position){
                    alreadyselected=true ;
                    qlist.get(qlist.indexOf(selectedData)).setSelectedtext(Selectedtext);
                    Log.e("qlistindex", String.valueOf(qlist.indexOf(selectedData)));
                    listener.oncheck(qlist, p);
                }
            }
            if(!alreadyselected) {
                Log.e("no>>>>>>>>>>>", "no:2");
                qlist.add(new Model(quizlist.get(p).getQuestion(), Selectedtext,p));
                listener.oncheck(qlist, p);
            }

            Log.e("listsize", String.valueOf(qlist.size()));
        });


    }

    @Override
    public int getItemCount() {
        return quizlist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView question;

        RadioGroup radiogroup;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question);
            radiogroup = itemView.findViewById(R.id.radiogroup);
        }
    }
}
