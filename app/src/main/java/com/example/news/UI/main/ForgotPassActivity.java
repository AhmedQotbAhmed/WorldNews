package com.example.news.UI.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.news.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText forgPass_email;
    private Button forgButton_dn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        forgButton_dn = findViewById(R.id.forgotpass_Button);
        forgPass_email = findViewById(R.id.email_forgPass);

        forgButton_dn.setOnClickListener(this);


    }


    private void forgotPassword() {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        String emailAddress = forgPass_email.getText().toString();
        if (emailAddress.isEmpty()) {
            forgPass_email.setError("Email is required");
            forgPass_email.requestFocus();
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            forgPass_email.setError(" please Enter a valid email");
            forgPass_email.requestFocus();

        }
        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), " Email sent.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), " Email Not found.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.forgotpass_Button:
                forgotPassword();
        }
    }
}
