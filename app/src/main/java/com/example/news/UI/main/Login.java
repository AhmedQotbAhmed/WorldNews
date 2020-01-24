package com.example.news.UI.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.news.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Handler progHandler = new Handler();

    private int count = 0;
    private LinearLayout myProgress;
    private ConstraintLayout myLoginView;
    private ImageView btn_google, btn_facebook;
    private Button btn_login;
    private Button btn_frgPass;
    private MaterialButton signUp_var;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    private static  final int RC_SignIn=0;
    private EditText password, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar loginTbr = findViewById(R.id.login_toolbar);
        loginTbr.setTitle("login");
        setSupportActionBar(loginTbr);


        // Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(GoogleSignInOptions)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                }).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();


        password = findViewById(R.id.password_login);

        email = findViewById(R.id.email_login);
        btn_frgPass = findViewById(R.id.fr_password);
        signUp_var = findViewById(R.id.Sign_up_btn);
        myLoginView = findViewById(R.id.loginView);
        myProgress = findViewById(R.id.progress);
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

                while (count < 30) {
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

        initAuthStateListene ();


    }

    private void signInUser() {
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();

        if (passwordText.length() < 8) {
            password.setError(" Minimum length of Password is should be 8 ");
            password.requestFocus();
            return;
        }
        if (emailText.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            email.setError(" please Enter a valid email");
            email.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);

                        }

                        else {
                            Toast.makeText(getApplicationContext(), "Email or password incorrect.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );

    }

    private void signInGoogle(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(intent,RC_SignIn);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SignIn) {
            GoogleSignInResult task = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                if (task.isSuccess()) {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = task.getSignInAccount();
                    firebaseAuthWithGoogle(account);
                }
         else {


        }
    }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), " is successful.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {

                            Toast.makeText(getApplicationContext(), " incorrect Authentication.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.Login_Button: {
                signInUser();
                break;
            }

            case R.id.Sign_up_btn: {
                Intent intent = new Intent(this, SignUp.class);
                startActivity(intent);

                break;
            }
            case R.id.icon_google: {
                signInGoogle();


                break;
            }

            case R.id.fr_password: {
                Intent intent = new Intent(this, ForgotPassActivity.class);
                startActivity(intent);

                break;
            }


        }
    }

    private void initAuthStateListene() {

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null&& user.isAnonymous()) {
                    Log.e("signIn", user.getUid());

                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                } else {

                    Log.d("sign out", user.getUid());
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener );

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null ){

            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
