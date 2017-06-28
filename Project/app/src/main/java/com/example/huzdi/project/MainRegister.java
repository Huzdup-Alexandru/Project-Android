package com.example.huzdi.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

/**
 * Created by huzdi on 20.06.2017.
 */

public class MainRegister extends Activity {
    EditText email;
    EditText password;
    Button register;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password1);
        register = (Button)findViewById(R.id.register);




    }




    @Override
    public void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        String email_string = email.getText().toString();
        String password_string = password.getText().toString();

        mAuth.createUserWithEmailAndPassword(email_string, password_string).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
           if(task.isSuccessful()){
               Log.d(TAG, "createUserWithEmail:success");
               FirebaseUser user = mAuth.getCurrentUser();

           } else {
               Log.w(TAG,"creatUserWithEmail:failure",task.getException());
               Toast.makeText(MainRegister.this,"Authentication failed",Toast.LENGTH_LONG).show();
           }
            }
        });


    }

}
