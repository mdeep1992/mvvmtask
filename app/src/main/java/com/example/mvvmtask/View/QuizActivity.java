package com.example.mvvmtask.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvvmtask.Adapter.Quiz_Adapter;
import com.example.mvvmtask.Model.Quiz.Result;
import com.example.mvvmtask.R;
import com.example.mvvmtask.ViewModel.ListViewModel;
import com.example.mvvmtask.ViewModel.QuizViewModel;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    Context context;
    RecyclerView recyclerView;
    Button submit;
    Quiz_Adapter adapter;
    ProgressBar progressBar;
    QuizViewModel quizViewModel;
    LinearLayoutManager manager;
    ArrayList<Result>quizlist=new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        recyclerView=findViewById(R.id.quizrecycler_question);
        submit=findViewById(R.id.btn_submit);
        progressBar=findViewById(R.id.progressBar_quiz);
        doInitViewModel();
    }

    private void doInitViewModel() {
        progressBar.setVisibility(View.VISIBLE);
        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        quizViewModel.quizlist.observe(this, new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setHasFixedSize(true);
                manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(manager);
                adapter=new Quiz_Adapter(context,quizlist);
                recyclerView.setAdapter(adapter);
                quizViewModel.loadquestions();
            }
        });
        quizViewModel.ErrorMsg.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(context, quizViewModel.ErrorMsg.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}