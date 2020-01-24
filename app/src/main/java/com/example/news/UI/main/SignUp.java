package com.example.news.UI.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.news.R;

public class SignUp extends AppCompatActivity {
    private EditText Fname ,Lname,email,password,repassword,mobile_num;
    private Button SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar mainTbr =  findViewById(R.id.sign_up_toolbar);
        mainTbr.setTitle("Sign Up");
        setSupportActionBar(mainTbr);
        Fname=findViewById(R.id.fname);
        Lname=findViewById(R.id.lname);
        email=findViewById(R.id.email_signUp);
        password=findViewById(R.id.pass_signUp);
        repassword=findViewById(R.id.repass_signUp);
        mobile_num=findViewById(R.id.mobile_signUp);
        SignUp=findViewById(R.id.signUp_Button);






    }
}
