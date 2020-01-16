package com.example.news.UI.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.news.R;
import com.google.android.material.button.MaterialButton;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Handler progHandler=new Handler();
    private int count=0;
   private LinearLayout myProgress;
    private ConstraintLayout myLoginView;
    private ImageView btn_google, btn_facebook;
    private Button btn_login;
    private Button btn_frgPass;
    private MaterialButton signUp_var;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar loginTbr =  findViewById(R.id.login_toolbar);
        loginTbr.setTitle("login");
        setSupportActionBar(loginTbr);

        btn_frgPass=findViewById(R.id.fr_password);
        signUp_var= findViewById(R.id.Sign_up_btn);
        myLoginView= findViewById(R.id.loginView);
        myProgress= findViewById(R.id.progress);
        btn_google = findViewById(R.id.icon_google);
        btn_facebook = findViewById(R.id.icon_facebook);
        btn_login = findViewById(R.id.Login_Button);

        btn_frgPass.setOnClickListener(this);
        btn_google.setOnClickListener(this);
        btn_facebook.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        signUp_var.setOnClickListener(this);



        new Thread(new Runnable() {
            @Override
            public void run() {

                while (count<30) {
                    count += 1;
                    android.os.SystemClock.sleep(50);
                }
                progHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        myLoginView.setVisibility(View.VISIBLE);

                    }
                });

                progHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        myProgress.setVisibility(View.INVISIBLE);


                    }
                });




            }
        }).start();



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.Login_Button: {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                break;
            }
            case R.id.Sign_up_btn: {
                Intent intent = new Intent(this, SignUp.class);
                startActivity(intent);

                break;
            }

            case R.id.fr_password: {
                Intent intent = new Intent(this, ForgotPassActivity.class);
                startActivity(intent);

                break;
            }


        }
    }



}
