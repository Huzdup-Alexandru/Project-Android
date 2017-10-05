package com.example.huzdi.taskkeeper.First;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.huzdi.taskkeeper.R;

public class EditFieldClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_layout);
    }

    public void saveButtonClicked(View view) {
        String messageText = ((EditText) findViewById(R.id.message)).getText().toString();
        if (messageText.equals("")) {

        } else {
            Intent intent = new Intent();
            intent.putExtra(Intent_Constants.INTENT_NESSAGE_FIELD, messageText);
            setResult(Intent_Constants.INTENT_RESULT_CODE, intent);
        }
    }

}
