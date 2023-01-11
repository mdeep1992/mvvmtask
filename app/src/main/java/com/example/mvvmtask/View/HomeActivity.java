package com.example.mvvmtask.View;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mvvmtask.AppConstants;
import com.example.mvvmtask.R;
import com.example.mvvmtask.Utils;
import com.google.android.material.card.MaterialCardView;

public class HomeActivity extends AppCompatActivity {
MaterialCardView userlist,multiple_recycler;
ImageView logout,back;
private Context context;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        userlist=findViewById(R.id.list_card);
        logout=findViewById(R.id.log_out);
        back=findViewById(R.id.back);
        multiple_recycler=findViewById(R.id.multiplerecycler);
        userlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l=new Intent(HomeActivity.this,ListActivity.class);
                startActivity(l);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils. setStringResource(HomeActivity.this, AppConstants.APP_LOGIN, "no");
                Intent i=new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(i);
                finishAffinity();
            }
        });
        multiple_recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this,RecyclerActivity.class);
                startActivity(i);

            }
        });
    }
}