package com.example.mvvmtask.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.mvvmtask.AppConstants;
import com.example.mvvmtask.R;
import com.example.mvvmtask.Utils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView textview=findViewById(R.id.Splashtext);
        Animation anim = new AlphaAnimation(0.0f, 1.5f);
        anim.setDuration(1000); //You can manage the blinking time with this parameter
        anim.setStartOffset(1500);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        textview.startAnimation(anim);
        Thread thread=new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    doValidate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }



        };
        thread.start();
    }
    private void doCallRegister() {
        Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
    private void doCallHome(){
        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private void doCallLogin() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    private void doValidate(){
        String isAppLogin = Utils. getStringResource(this, AppConstants.APP_LOGIN);
        Log.e("status", isAppLogin);
        if (isAppLogin.isEmpty() || isAppLogin.equals("no")) {
            /* First Login*/
            Log.e("validate", "login" );
            doCallLogin();

        } else {
            Log.e("validate", "home" );

            doCallHome();

        }
    }
    }
