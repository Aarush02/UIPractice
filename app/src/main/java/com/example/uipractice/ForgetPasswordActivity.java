package com.example.uipractice;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonForgetPassword;
    private EditText editTextForgetPassword;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        buttonForgetPassword = (Button) findViewById(R.id.button_forget_password);
        editTextForgetPassword = (EditText) findViewById(R.id.edit_text_forget_password_page_enter_email);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        buttonForgetPassword.setOnClickListener(this);
    }
    private void resetPassword(){
        String email = editTextForgetPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Sending Email...");
        progressDialog.show();
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgetPasswordActivity.this,"We have sent you instructions to reset your password!",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(ForgetPasswordActivity.this,LoginActivity.class));
                }else {
                    Toast.makeText(ForgetPasswordActivity.this,"Failed to send reset email!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ForgetPasswordActivity.super.onBackPressed();
                    }
                }).create().show();
    }

    @Override
    public void onClick(View view) {
        if (view == buttonForgetPassword){
            resetPassword();
        }


    }
}
