package com.example.huzdi.taskkeeper.First;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.huzdi.taskkeeper.R;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<Task> arrayAdapter;
    ArrayAdapter<Task> taskArrayAdapter;
    MyTaskAdapter myTaskAdapter;
    String messageText;
    ArrayList<Task> tasks;
    Task task;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task = new Task();

        myTaskAdapter = new MyTaskAdapter(this,position,tasks);
        listView = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, EditMessageClass.class);
                intent.putExtra(Intent_Constants.INTENT_MESSAGE_DATA, arrayList.get(i));
                intent.putExtra(Intent_Constants.INTENT_ITEM_POSITION, i);
                startActivityForResult(intent, Intent_Constants.INTENT_REQUEST_CODE_TWO);


            }
        });

        try {
            Scanner scannner = new Scanner(openFileInput("Todo.txt"));
            while (scannner.hasNextLine()) {
                String data = scannner.nextLine();
                Task n = new Task();
                n.setDescription(data);
                arrayAdapter.add(n);
            }
            scannner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void onClick(View view) {

        Intent intent = new Intent();
        intent.setClass(MainActivity.this, EditFieldClass.class);
        startActivityForResult(intent, Intent_Constants.INTENT_REQUEST_CODE);
    }

    @Override
    public void onBackPressed() {
        try {
            PrintWriter pw = new PrintWriter(openFileOutput("Todo.txt", Context.MODE_PRIVATE));
            for (String data : arrayList) {
                pw.println(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Intent_Constants.INTENT_REQUEST_CODE) {
//            messageText = data.getStringExtra(Intent_Constants.INTENT_NESSAGE_FIELD);
//            arrayList.add(messageText);
            task.setDescription(data.getStringExtra(Intent_Constants.INTENT_NESSAGE_FIELD));
            tasks.add(task);
            arrayAdapter.notifyDataSetChanged();

        } else if (resultCode == Intent_Constants.INTENT_REQUEST_CODE_TWO) {
//            messageText = data.getStringExtra(Intent_Constants.INTENT_CHANGED_MESSAGE);
//            position = data.getIntExtra(Intent_Constants.INTENT_ITEM_POSITION, -1);
//
            task.setDescription(data.getStringExtra(Intent_Constants.INTENT_CHANGED_MESSAGE));
            tasks.remove(position);
            tasks.add(position,task);
            arrayList.remove(position);
            arrayList.add(position, messageText);
            arrayAdapter.notifyDataSetChanged();

        }
    }
}
