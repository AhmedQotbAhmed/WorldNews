package com.example.news.UI.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.news.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText Fname ,Lname,email,password,repassword,mobile_num;
    private Button SignUp;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar mainTbr =  findViewById(R.id.sign_up_toolbar);
        mainTbr.setTitle("Sign Up");
        setSupportActionBar(mainTbr);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        Fname=findViewById(R.id.fname);
        Lname=findViewById(R.id.lname);
        email=findViewById(R.id.email_signUp);
        password=findViewById(R.id.pass_signUp);
        repassword=findViewById(R.id.repass_signUp);
        mobile_num=findViewById(R.id.mobile_signUp);
        SignUp=findViewById(R.id.forgotpass_Button);
SignUp.setOnClickListener(this);


    }


    private void  registerUser(){

        String emailText=email.getText().toString();
        String passwordText=password.getText().toString();
        String FnameText =Fname.getText().toString();
        String LnameText= Lname.getText().toString();
        String rePasswordText =repassword.getText().toString();
        String mobile_Text= mobile_num.getText().toString();


        if(passwordText.length()<8)
        {
            password.setError(" Minimum length of Password is should be 8 ");
            password.requestFocus();

        }
        if(emailText.isEmpty())
            {
            email.setError("Email is required");
            email.requestFocus();


        }
        if(passwordText.isEmpty())
            {
            password.setError("Password is required");
            password.requestFocus();

        }
        if(!passwordText.equals(rePasswordText))
            {
            repassword.setError("Password doesn't match");
            repassword.requestFocus();

        }
        if(LnameText.isEmpty())
            {
            Lname.setError("Lname is required");
            Lname.requestFocus();

        }
        if(FnameText.isEmpty())
             {
            Fname.setError("Fname is required");
            Fname.requestFocus();

        }
        if(mobile_Text.isEmpty())
            {
            mobile_num.setError("mobile is required");
            mobile_num.requestFocus();

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
            {
                email.setError(" please Enter a valid email");
                email.requestFocus();


        }
        mAuth.createUserWithEmailAndPassword(emailText,passwordText).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"SignUp Successfully",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUp.this, Login.class);
                    startActivity(intent);


                }
                else {
                    Log.e(" Authentication failed"
                            , "createUserWithEmail:failure", task.getException());
                    Toast.makeText(getApplicationContext(), "Authentication failed try again.",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forgotpass_Button:
                registerUser();
                break;

        }
    }
}
