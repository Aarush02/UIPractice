package com.example.uipractice;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ForgetPasswordActivity extends AppCompatActivity {
    private Button buttonForgetPassword;
    private EditText editTextForgetPassword;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        buttonForgetPassword = (Button) findViewById(R.id.button_forget_password);
        buttonForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button_forget_password:
                        startActivity(new Intent(ForgetPasswordActivity.this,LoginActivity.class));
                }
            }
        });
    }
}
