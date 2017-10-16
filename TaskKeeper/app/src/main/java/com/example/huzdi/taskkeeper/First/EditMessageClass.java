package com.example.huzdi.taskkeeper.First;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.huzdi.taskkeeper.R;

/**
 * Created by huzdi on 26.09.2017.
 */

public class EditMessageClass extends AppCompatActivity implements View.OnClickListener{
    String messageText;
    int position;

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_layout);

        btnDatePicker = findViewById(R.id.btn_date);
        btnTimePicker = findViewById(R.id.btn_time);
        txtDate = findViewById(R.id.in_date);
        txtTime = findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);


        Intent intent = getIntent();
        messageText = intent.getStringExtra(Intent_Constants.INTENT_MESSAGE_DATA);
        position = intent.getIntExtra(Intent_Constants.INTENT_ITEM_POSITION, -1);
        EditText messageData = findViewById(R.id.message);
        messageData.setText(messageText);
    }

    public void saveButtonClicked(View view) {
        String changedMessageText = ((EditText) findViewById(R.id.message)).getText().toString();
        Intent intent = new Intent();
        intent.putExtra(Intent_Constants.INTENT_CHANGED_MESSAGE, changedMessageText);
        intent.putExtra(Intent_Constants.INTENT_ITEM_POSITION, position);
        setResult(Intent_Constants.INTENT_RESULT_CODE_TWO, intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        if(view == btnDatePicker){
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (view == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();



        }
    }
}
