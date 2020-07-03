package com.example.uipractice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonLogin;
    private Button buttonSignUpDisplay;
    private TextView textViewForgetPassword;
    private EditText editTextLoginEmail;
    private EditText editTextLoginPassword;
    private ProgressDialog progressDialog;
    private SignInButton buttonGoogleLogin;
    private Button buttonFacebookLogin;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(this,HomePageActivity.class));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonSignUpDisplay =(Button) findViewById(R.id.button_sign_up_item_display);
        buttonLogin = (Button) findViewById(R.id.button_login);
        firebaseAuth = FirebaseAuth.getInstance();
        textViewForgetPassword = (TextView) findViewById(R.id.text_view_forget_your_password);
        editTextLoginEmail = (EditText) findViewById(R.id.edit_text_login_page_enter_email);
        editTextLoginPassword = (EditText) findViewById(R.id.edit_text_login_page_enter_password);
        buttonGoogleLogin = (SignInButton) findViewById(R.id.button_google_login);
        buttonFacebookLogin = (Button) findViewById(R.id.button_facebook_login);
        progressDialog = new ProgressDialog(this);
        buttonLogin.setOnClickListener(this);
        buttonSignUpDisplay.setOnClickListener(this);
        textViewForgetPassword.setOnClickListener(this);
        buttonGoogleLogin.setOnClickListener(this);
        buttonFacebookLogin.setOnClickListener(this);
    }
    private void userLogin() {
        String email = editTextLoginEmail.getText().toString().trim();
        String password = editTextLoginPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            editTextLoginEmail.setError("Please enter email");
            return;
        }
        if(TextUtils.isEmpty(password)){
            editTextLoginPassword.setError("Please enter password");
            return;
        }
        progressDialog.setMessage("Logging In...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
                }
                else {
                    Toast.makeText(LoginActivity.this, "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogin){
            userLogin();
        }
        if (view == buttonSignUpDisplay){
            startActivity(new Intent(this,SignUpActivity.class));
        }
        if (view == textViewForgetPassword){
            startActivity(new Intent(this,ForgetPasswordActivity.class));
        }
        if (view == buttonFacebookLogin){

        }
        if (view == buttonGoogleLogin){


        }

    }

}
