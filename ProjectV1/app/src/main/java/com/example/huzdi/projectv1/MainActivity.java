package com.example.huzdi.projectv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button button1;
    Button button2;


    //Toolbar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();

        button1 = (Button) findViewById(R.id.button_main);
        button2 = (Button) findViewById(R.id.button2_main);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StartActivity.class);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();

        if(res_id == R.id.action_bar_rrr){
            Toast.makeText(getApplicationContext(), "You are contacting us!", Toast.LENGTH_LONG).show();
        } else if(res_id == R.id.action_bar_bbb){
            Toast.makeText(getApplicationContext(), "Choose how to share your data!", Toast.LENGTH_LONG).show();
        } else if (res_id == R.id.action_bar_eee) {
            Toast.makeText(getApplicationContext(), "You are reporting us!", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    //\Toolbar
    }

