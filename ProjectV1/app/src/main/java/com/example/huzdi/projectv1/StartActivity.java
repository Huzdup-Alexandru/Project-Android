package com.example.huzdi.projectv1;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Fragments.PagerStrip;

public class StartActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;

    FirebaseDatabase database;
    DatabaseReference myRef;
    Object clubKey;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();
        imageView = (ImageView) findViewById(R.id.imageView_start);
        imageView1= (ImageView)findViewById(R.id.imageView2_start);
        imageView2 = (ImageView) findViewById(R.id.imageView3_start);
        imageView3 = (ImageView) findViewById(R.id.imageView4_start);
//        final PagerStrip pagerStrip=  new PagerStrip();
//        Object [] d = pagerStrip.getClass().getClasses();
//        if(d[1] ==  EcologistFragment.newInstance("ecologist","page1")){
//
//        }



        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Ecologist").child("Big");


        imageView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, PagerStrip.class);
                startActivity(intent);
            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this,PagerStrip.class);
                startActivity(intent);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this,PagerStrip.class);
                startActivity(intent);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, PagerStrip.class);
                startActivity(intent);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    protected void onStart() {
        super.onStart();
        readDataFromDatabase();
    }

    public void readDataFromDatabase(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnaphot : dataSnapshot.getChildren()){
                    clubKey = childSnaphot.getValue();
                    Log.d("TAG","Values is " + clubKey);
                }


//                Object value = dataSnapshot.getKey(Object.class);
//                Log.d("TAG","Values is " + clubkey);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });
    }

    public Object getClubkey() {
        return clubKey;
    }
}
