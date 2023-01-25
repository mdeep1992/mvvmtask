package com.example.mvvmtask.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.mvvmtask.Model.RegisterModel;
import com.example.mvvmtask.R;
import com.example.mvvmtask.Utils;
import com.example.mvvmtask.ViewModel.ListViewModel;
import com.example.mvvmtask.ViewModel.RegisterViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText ed_email, ed_password, ed_confirmpass;
    TextInputLayout tx_email, tx_password, tx_confirmpass;
    AppCompatButton register_btn;
    CheckBox policy;
    RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        doInitContent();
        doInitViewModel();
        ed_email.setText("eve.holt@reqres.in");
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                doValidation();
            }
        });

    }

    private void doInitViewModel() {
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        registerViewModel.register.observe(this, new Observer<RegisterModel>() {
            @Override
            public void onChanged(RegisterModel registerModel) {
                Intent l = new Intent(RegisterActivity.this, LoginActivity.class);
                l.putExtra("email", ed_email.getText().toString());
                startActivity(l);
            }
        });
        registerViewModel.ErrorMsg.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(RegisterActivity.this,registerViewModel.ErrorMsg.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doValidation() {
        if (ed_email.getText().toString().isEmpty()) {
            tx_email.setHelperText("please enter the email");
        } else if (!ed_email.getText().toString().trim().matches(Utils.emailPattern)) {
            tx_email.setHelperText("");
            tx_email.setHelperText("invalid email ");
        } else if ((!Utils.isValidPassword(ed_password.getText().toString().trim()))) {
            tx_email.setHelperText("");
            tx_password.setHelperText("password must have six digits and contains atleast one number and one special character");
        } else if (!ed_confirmpass.getText().toString().equals(ed_password.getText().toString())) {
            tx_password.setHelperText("");
            tx_confirmpass.setHelperText("password must be same");
        } else if (!policy.isChecked()) {
            tx_confirmpass.setHelperText("");
            Toast.makeText(this, "please select privacy policy", Toast.LENGTH_SHORT).show();
        } else {

            Log.e("res>>>", "response");
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("email", ed_email.getText().toString());
            jsonObject.addProperty("password", ed_password.getText().toString());
            registerViewModel.doregisterApi(jsonObject);

        }
    }

    private void doInitContent() {
        ed_email = findViewById(R.id.etx_email);
        ed_password = findViewById(R.id.etx_password);
        ed_confirmpass = findViewById(R.id.etx_confirm_password);
        tx_email = findViewById(R.id.txt_email);
        tx_password = findViewById(R.id.txt_password);
        tx_confirmpass = findViewById(R.id.txt_confirm_password);
        register_btn = findViewById(R.id.btn_register);
        policy = findViewById(R.id.privacy);
    }
}