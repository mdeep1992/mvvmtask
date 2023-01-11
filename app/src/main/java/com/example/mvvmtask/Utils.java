package com.example.mvvmtask;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static final String BASE_URL ="https://reqres.in/";
    public static final String token = "Bearer "+"808|so3xxzb4KtBF6V4L4434twluqNqQZxR4ZY1MS21t";
    public static final String base_url = "https://zeoner.com/calcibill/";
   public static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";



    public void emailpattern(){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    }
    public static  Boolean isValidPassword( String password) {
      Pattern pattern;
      Matcher matcher;
        String specialCharacters = "-@%\\[\\}+'!/#$^?:;,\\(\"\\)~`.*=&\\{>\\]<_";
        String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[!@#$%^&*()?<>{};:',])(?=\\S+$).{6,20}$";
        pattern = Pattern.compile(PASSWORD_REGEX);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public static String getAuthToken(Context context){
        SharedPreferences sharedPreferences= context.getSharedPreferences(AppConstants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(AppConstants.LOGIN_SESSION_TOKEN, "");
    }
    public static void  setAuthToken(Context context,String token){
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.SHARED_PREF_NAME,  Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstants.LOGIN_SESSION_TOKEN, token);
        editor.apply();
    }
    public static String getemail(Context context){
        SharedPreferences sharedPreferences= context.getSharedPreferences(AppConstants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(AppConstants.LOGIN_SESSION_EMAIL, "");
    }
    public static void  setemail(Context context,String email){
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.SHARED_PREF_NAME,  Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstants.LOGIN_SESSION_EMAIL, email);
        editor.apply();
    }
    public static void  setStringResource(Context context,String key,String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.SHARED_PREF_NAME,  Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public static String getStringResource(Context context,String key){
        SharedPreferences sharedPreferences= context.getSharedPreferences(AppConstants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(key, "");
    }
}
