package com.example.mvvmtask.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.mvvmtask.R;
import com.example.mvvmtask.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText ed_email,ed_password;
    TextInputLayout tx_email,tx_password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        doInitContent();
        doValidation();
    }

    private void doValidation() {
        if (ed_email.getText().toString().isEmpty()){
            tx_email.setHelperText("please enter the email");
        }else if (ed_email.getText().toString().matches(Utils.emailPattern)){
            tx_email.setHelperText("");
            tx_email.setHelperText("invalid email ");
        }else if (ed_password.getText().toString().isEmpty()){
            tx_email.setHelperText(" ");
            tx_password.setHelperText("please enter the password");
        }
        else if((Utils.isValidPassword(ed_password.getText().toString()))){
            tx_password.setHelperText("");
            tx_password.setHelperText("password must have six digits and contains atleast one number and one special character");
        }else{

        }
    }

    private void doInitContent() {
        ed_email=findViewById(R.id.etx_loginemail);
        ed_password=findViewById(R.id.etx_loginpassword);
        tx_email=findViewById(R.id.txt_loginemail);
        tx_password=findViewById(R.id.txt_loginpassword);
        login=findViewById(R.id.btn_login);

    }
}