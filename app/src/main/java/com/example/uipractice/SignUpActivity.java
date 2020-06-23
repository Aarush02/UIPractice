package com.example.uipractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity {
    private Button buttonSignUp;
    private Button buttonLogInDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        buttonLogInDisplay =(Button) findViewById(R.id.button_log_in_item_display);
        buttonSignUp = (Button) findViewById(R.id.button_sign_up);
        findViewById(R.id.button_sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button_sign_up:
                        startActivity(new Intent(SignUpActivity.this,HomePageActivity.class));
                        break;
                }

            }
        });
        findViewById(R.id.button_log_in_item_display).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button_log_in_item_display:
                        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                        break;
                }

            }
        });
    }
}