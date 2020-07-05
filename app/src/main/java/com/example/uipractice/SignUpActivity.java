package com.example.uipractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonSignUp;
    private Button buttonLogInDisplay;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private CheckBox checkBoxTerms;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(this,HomePageActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        buttonLogInDisplay =(Button) findViewById(R.id.button_log_in_item_display);
        buttonSignUp = (Button) findViewById(R.id.button_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();
        editTextFirstName = (EditText) findViewById(R.id.edit_text_sign_up_page_enter_first_name);
        editTextLastName = (EditText) findViewById(R.id.edit_text_sign_up_page_enter_last_name);
        editTextEmail = (EditText) findViewById(R.id.edit_text_sign_up_page_enter_email);
        editTextPassword = (EditText) findViewById(R.id.edit_text_sign_up_page_enter_password);
        editTextConfirmPassword = (EditText) findViewById(R.id.edit_text_sign_up_page_enter__confirm_password);
        checkBoxTerms = (CheckBox) findViewById(R.id.check_box_sign_up_page_terms_and_condition);
        progressDialog = new ProgressDialog(this);
        buttonSignUp.setOnClickListener(this);
        buttonLogInDisplay.setOnClickListener(this);
        FirebaseDatabase database =  FirebaseDatabase.getInstance();
        DatabaseReference Myref = database.getReference();
    }

    private void registerUser(){
        final String firstName = editTextFirstName.getText().toString().trim();
        final String lastName = editTextLastName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();
        String confirmPassword  = editTextConfirmPassword.getText().toString().trim();
        if(TextUtils.isEmpty(firstName)){
            editTextFirstName.setError("Please enter your first name");
            return;
        }
        if(TextUtils.isEmpty(lastName)){
            editTextLastName.setError("Please enter your last name");
            return;
        }
        if(TextUtils.isEmpty(email)){
            editTextEmail.setError("Please enter email");
            return;
        }
        if(TextUtils.isEmpty(password)){
            editTextPassword.setError("Please enter password");
            return;
        }
        if(TextUtils.isEmpty(confirmPassword)){
            editTextConfirmPassword.setError("Please re-enter password");
            return;
        }
        if (!confirmPassword.equals(password))
        {
            editTextConfirmPassword.setError("Password do not match");
        }
        if (!checkBoxTerms.isChecked()){
            checkBoxTerms.setError("Please select the box to accept the terms and condition of this application");
        }
        else {
            progressDialog.setMessage("Registering...");
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        String userid = firebaseAuth.getCurrentUser().getUid();
                        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);
                        Map saveData = new HashMap();
                        {
                            saveData.put("First Name", firstName);
                            saveData.put("Last Name", lastName);
                            saveData.put("Email", email);
                        }
                        current_user_db.setValue(saveData);
                        finish();
                        startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
                    } else {
                        Toast.makeText(SignUpActivity.this, "User Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }


            });
        }
    }
    @Override
    public void onClick(View view) {
        if (view == buttonSignUp) {
            registerUser();
        }
        if (view == buttonLogInDisplay) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}