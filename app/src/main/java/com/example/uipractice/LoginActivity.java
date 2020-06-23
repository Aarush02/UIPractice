package com.example.uipractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private Button buttonLogin;
    private Button buttonSignUpDisplay;
    private TextView textViewForgetPassword;
    private EditText editTextLoginEmail;
    private EditText getEditTextLoginPassword;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonSignUpDisplay =(Button) findViewById(R.id.button_sign_up_item_display);
        buttonLogin = (Button) findViewById(R.id.button_login);
        textViewForgetPassword = (TextView) findViewById(R.id.text_view_forget_your_password);
        findViewById(R.id.button_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button_login:
                        startActivity(new Intent(LoginActivity.this,HomePageActivity.class));
                        break;
                }

            }
        });
        findViewById(R.id.button_sign_up_item_display).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button_sign_up_item_display:
                        startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                        break;
                }

            }
        });
        findViewById(R.id.text_view_forget_your_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.text_view_forget_your_password:
                        startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));
                }
            }
        });
    }
}
