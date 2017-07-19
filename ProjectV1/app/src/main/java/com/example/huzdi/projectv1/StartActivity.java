package com.example.huzdi.projectv1;

import android.app.FragmentTransaction;
import android.content.Intent;

import android.graphics.pdf.PdfDocument;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTabStrip;
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

import java.util.ArrayList;

import Fragments.EcologistFragment;
import Fragments.FunnyFragment;
import Fragments.KnowledgeFragment;
import Fragments.PagerStrip;
import Fragments.SportiveFragment;

public class StartActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;

    FirebaseDatabase database;
    DatabaseReference myRef;
    String clubKey;

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
        imageView4 = (ImageView) findViewById(R.id.imageView5_start);


        final Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
             readDataFromDatabase();
                handler.postDelayed(this,100);
            }
        };
        handler.post(run);

        if(findViewById(R.id.pager) !=null){
            if(savedInstanceState != null){
                return;
            }


        }





        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Ecologist").child("Big");
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this,BrainTrainerActivity.class);
                startActivity(intent);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                KnowledgeFragment knowledgeFragment = new KnowledgeFragment();

                knowledgeFragment.getClass();
                Intent intent = new Intent(StartActivity.this, PagerStrip.class);
                intent.getStringExtra("ecologist");
                startActivity(intent);
            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent one = new Intent(StartActivity.this, PagerStrip.class);
                one.getStringExtra("funny");
                startActivity(one);

            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this,PagerStrip.class);
                intent.getStringExtra("knowledge");
                startActivity(intent);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, PagerStrip.class);
                intent.getStringExtra("sport");
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
                ArrayList<String> dadada = new  ArrayList<>();
                for(DataSnapshot childSnaphot : dataSnapshot.getChildren()){
                    clubKey = childSnaphot.getValue().toString();
                    Log.d("TAG","Values is " + clubKey);
                    dadada.add(clubKey);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });
    }


}
