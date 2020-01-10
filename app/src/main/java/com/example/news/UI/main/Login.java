package com.example.news.UI.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.news.R;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Handler progHandler=new Handler();
    private int count=0;
   private LinearLayout myProgress;
    private ConstraintLayout myLoginView;
    private ImageButton btn_google, btn_facebook;
    private Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        myLoginView= findViewById(R.id.loginView);
        myProgress= findViewById(R.id.progress);
        btn_google = findViewById(R.id.icon_google);
        btn_facebook = findViewById(R.id.icon_facebook);
        btn_login = findViewById(R.id.Login_Button);


        btn_google.setOnClickListener(this);
        btn_facebook.setOnClickListener(this);
        btn_login.setOnClickListener(this);



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
        if (v.getId() == R.id.Login_Button)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}
