package com.example.huzdi.project;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huzdi on 25.06.2017.
 */

public class User {
    public String email;
    public String userName;
    public String password;

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public User(){
    }
    public User (String email, String userName , String password){
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", email);
        result.put("userName", userName);
        result.put("password",password);

        return result;
    }

    protected void writeNewUser(String userId, String email, String name, String password){
        User user = new User(email, name, password);

        mDatabase.child("users").child(userId).setValue(user);

    }


}
