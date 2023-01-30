package com.example.mvvmtask.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mvvmtask.AppConstants;
import com.example.mvvmtask.R;
import com.example.mvvmtask.Utils;
import com.google.android.material.card.MaterialCardView;

import java.lang.reflect.Array;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    MaterialCardView userlist, multiple_recycler, Quiz;
    ImageView logout, back;
    Context context;
    Toolbar toolbar;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loadLocale();
        context=this;
        userlist = findViewById(R.id.list_card);
//        back=findViewById(R.id.back);
        multiple_recycler = findViewById(R.id.multiplerecycler);
        Quiz = findViewById(R.id.quizrecycler);
        toolbar = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        userlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l = new Intent(HomeActivity.this, ListActivity.class);
                startActivity(l);
            }
        });

        multiple_recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, RecyclerActivity.class);
                startActivity(i);

            }
        });
        Quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, QuizActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id = item.getItemId();
        if (item_id == R.id.setting) {
            Toast.makeText(HomeActivity.this, "setting clicked", Toast.LENGTH_SHORT).show();
        } else if (item_id == R.id.logout) {
            Utils.setStringResource(HomeActivity.this, AppConstants.APP_LOGIN, "no");
            Intent i = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(i);
            finishAffinity();
        } else if (item_id == R.id.Language) {
            doCalllanguageDialog();

            Toast.makeText(HomeActivity.this, "Language clicked", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void doCalllanguageDialog() {
        final String[] languages = {"English", "தமிழ்"};
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);
        alertDialog.setTitle("Choose Language");
        alertDialog.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    SetLocale("en");
                    recreate();
                } else if (i == 1) {
                    SetLocale("ta");
                    recreate();
                }
                dialogInterface.dismiss();
            }
        });
        AlertDialog mdialog = alertDialog.create();
        mdialog.show();
    }

    private void SetLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config =new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences sharedPreferences = getSharedPreferences("settings",  Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString( "settings",lang);
        editor.apply();
        Log.e("language", lang );
//        Utils.setLocale(context,lang);
    }
    public void loadLocale(){
        SharedPreferences sharedPreferences= getSharedPreferences("settings", Context.MODE_PRIVATE);
        String language= sharedPreferences.getString("settings", "");
        SetLocale(language);
//        SetLocale(Utils.getLocale(context));
        SetLocale(language);
//        Log.e("language>>>>>", Utils.getLocale(context));
    }




}