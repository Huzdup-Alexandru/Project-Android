package com.example.huzdi.mytaskeeper;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.huzdi.mytaskeeper.database.TaskContract;

import java.util.Calendar;

/**
 * Created by huzdi on 23.10.2017.
 */

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDatePicker, btnTimePicker;
    TextView txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int mPriority;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        ((RadioButton) findViewById(R.id.radButton1)).setChecked(true);
        mPriority = 1;

        btnDatePicker = findViewById(R.id.btn_date);
        btnTimePicker = findViewById(R.id.btn_time);
        txtDate = findViewById(R.id.in_date);
        txtTime = findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        final Calendar c = Calendar.getInstance();
        if (view == btnDatePicker) {
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            mYear = year;
                            mMonth = monthOfYear;
                            mDay = dayOfMonth;


                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (view == btnTimePicker) {

            // Get Current Time
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            mMinute = minute;
                            mHour = hourOfDay;

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);

            setAlarm(c.getTimeInMillis());
            timePickerDialog.show();


        }


    }

    private void setAlarm(long timeInMillis) {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, 1000, pendingIntent);

        Toast.makeText(this, "Alarm is set!", Toast.LENGTH_SHORT).show();
    }

    public void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }

    public void onClickUpdateTask(View view) {
        String input = ((EditText) findViewById(R.id.editTextTaskDescription)).getText().toString();
        String task = ((EditText) findViewById(R.id.editTaskName)).getText().toString();

        if (input.length() == 0) {
            return;
        }

        String date = Integer.toString(mDay) + "/" + Integer.toString(mMonth) + "/" + Integer.toString(mYear);
        String hour = Integer.toString(mHour) + ":" + Integer.toString(mMinute);

        ContentValues contentValues = new ContentValues();

        contentValues.get(TaskContract.TaskEntry.COLUMN_TASK);

        contentValues.put(TaskContract.TaskEntry.DESCRIPTION, input);
        contentValues.put(TaskContract.TaskEntry.COLUMN_PRIORITY, mPriority);
        contentValues.put(TaskContract.TaskEntry.COLUMN_DATE, date);
        contentValues.put(TaskContract.TaskEntry.COLUMN_HOUR, hour);

        int id = (int) view.getId();
        String stringId = Integer.toString(id);
        Uri uri = TaskContract.TaskEntry.CONTENT_URI;
        uri = uri.buildUpon().appendPath(stringId).build();


    }


    public void onClickAddTask(View view) {

        String input = ((EditText) findViewById(R.id.editTextTaskDescription)).getText().toString();
        String task = ((EditText) findViewById(R.id.editTaskName)).getText().toString();

        if (input.length() == 0) {
            return;
        }

        String date = Integer.toString(mDay) + "/" + Integer.toString(mMonth) + "/" + Integer.toString(mYear);
        String hour = Integer.toString(mHour) + ":" + Integer.toString(mMinute);


        ContentValues contentValues = new ContentValues();

        contentValues.put(TaskContract.TaskEntry.COLUMN_TASK, task);
        contentValues.put(TaskContract.TaskEntry.DESCRIPTION, input);
        contentValues.put(TaskContract.TaskEntry.COLUMN_PRIORITY, mPriority);
        contentValues.put(TaskContract.TaskEntry.COLUMN_DATE, date);
        contentValues.put(TaskContract.TaskEntry.COLUMN_HOUR, hour);

        Uri uri = getContentResolver().insert(TaskContract.TaskEntry.CONTENT_URI, contentValues);


        if (uri != null) {
            Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
        }

        finish();

    }


    public void onPrioritySelected(View view) {
        if (((RadioButton) findViewById(R.id.radButton1)).isChecked()) {
            mPriority = 1;
        } else if (((RadioButton) findViewById(R.id.radButton2)).isChecked()) {
            mPriority = 2;
        } else if (((RadioButton) findViewById(R.id.radButton3)).isChecked()) {
            mPriority = 3;
        }
    }
}


