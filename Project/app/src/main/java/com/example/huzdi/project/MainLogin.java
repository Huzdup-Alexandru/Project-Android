package com.example.huzdi.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by huzdi on 20.06.2017.
 */

public class MainLogin extends Activity {
    private EditText email;
    private EditText password;

    private Button login;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        email =  findViewById(R.id.email_login);
        password =  findViewById(R.id.password_login);
        login =  findViewById(R.id.button_login);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null){
                    startActivity(new Intent(MainLogin.this, AccountActivity.class));
                }
            }
        };



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startSignIn();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignIn(){
        String inputEmail = email.getText().toString();
        String inputPassword = password.getText().toString();

        if(TextUtils.isEmpty(inputEmail) || TextUtils.isEmpty(inputPassword)){
            Toast.makeText(MainLogin.this,"Fields are empty",Toast.LENGTH_LONG).show();

        } else {

            mAuth.signInWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {

                        Toast.makeText(MainLogin.this, "Sign in problem", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
    }

