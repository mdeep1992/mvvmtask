package com.example.mvvmtask.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mvvmtask.AppConstants;
import com.example.mvvmtask.Model.LoginModel;
import com.example.mvvmtask.R;
import com.example.mvvmtask.Utils;
import com.example.mvvmtask.ViewModel.LoginViewModel;
import com.example.mvvmtask.ViewModel.RegisterViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText ed_email, ed_password;
    TextInputLayout tx_email, tx_password;
    Button login_btn,register_btn;
    String mail;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        doInitContent();
        getintent();
        setViewmodel();
        ed_email.setText("eve.holt@reqres.in");
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doValidation();
            }
        });
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(l);
            }
        });

    }

    private void setViewmodel() {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.login.observe(this, loginModel -> {
            Utils.setAuthToken(LoginActivity.this, loginModel.getToken());
            Utils.setStringResource(this, AppConstants.APP_LOGIN,"yes");
            Intent l = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(l);
            finish();
        });
        loginViewModel.ErrorMsg.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

//                Toast.makeText(LoginActivity.this,error,Toast.LENGTH_SHORT).show();
                Log.e("errormsggggggg", "onChanged: " );
                Toast.makeText(LoginActivity.this,loginViewModel.ErrorMsg.toString(),
                        Toast.LENGTH_LONG).show();


            }
        });

    }

    private void getintent() {
        mail = getIntent().getStringExtra("email");
    }

    private void doValidation() {
        if (ed_email.getText().toString().isEmpty()) {
            tx_email.setHelperText("please enter email");
        } else if (!ed_email.getText().toString().matches(Utils.emailPattern)) {
            tx_email.setHelperText("");
            tx_email.setHelperText("invalid email ");
        } else if (ed_password.getText().toString().isEmpty()) {
            tx_email.setHelperText(" ");
            tx_password.setHelperText("please enter the password");
        } else if ((!Utils.isValidPassword(ed_password.getText().toString()))) {
            tx_password.setHelperText("");
            tx_password.setHelperText("password must have six digits and contains atleast one number and one special character");
        } else {
            Log.e("res>>>", "response");
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("email", ed_email.getText().toString());
            jsonObject.addProperty("password", ed_password.getText().toString());
            loginViewModel.doLogin(jsonObject);
        }
    }

    private void doInitContent() {
        ed_email = findViewById(R.id.etx_loginemail);
        ed_password = findViewById(R.id.etx_loginpassword);
        tx_email = findViewById(R.id.txt_loginemail);
        tx_password = findViewById(R.id.txt_loginpassword);
        login_btn = findViewById(R.id.btn_login);
        register_btn = findViewById(R.id.btn_register);

    }
}