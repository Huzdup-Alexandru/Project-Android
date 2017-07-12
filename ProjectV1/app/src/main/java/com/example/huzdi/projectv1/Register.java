package com.example.huzdi.projectv1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Register extends AppCompatActivity {
    Button button;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        button = (Button) findViewById(R.id.button_register);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create();
            }
        });

    }
    public void create(){
        editText1 = (EditText) findViewById(R.id.edit_register);
        editText2 = (EditText) findViewById(R.id.edit2_register);

        String email = editText1.getText().toString();
        String password = editText2.getText().toString();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            boolean flag = false;

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    flag = true;
                    Log.d("TAG", "createUserWithEmail:success");
                    currentUser = mAuth.getCurrentUser();

                } else {
                    flag = false;
                    Log.w("TAG","createUserWithEmail:failure",task.getException());
                    Toast.makeText(Register.this,"Authentication failed.",Toast.LENGTH_SHORT).show();
                }
                if(flag){
                    Intent intent = new Intent(Register.this,StartActivity.class);
                    startActivity(intent);
                }

            }
        });
    }


}
