package com.example.huzdi.taskkeeper.First.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.huzdi.taskkeeper.First.Task;

/**
 * Created by huzdi on 16.10.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "taskDB";
    private static final int DATABASE_VERSION = 1;


    public DBHelper(Context c){
        super(c,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_TASK_TABLE  = "CREATE TABLE " + TaskContract.HabitEntry.COLUMN_TASK_NAME + " ("
                + TaskContract.HabitEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TaskContract.HabitEntry.COLUMN_TASK_NAME + " TEXT NOT NULL, "
                + TaskContract.HabitEntry.COLUMN_TASK_DESCRIPTION + " TEXT,"
                + TaskContract.HabitEntry.COLUMN_NUMBER_OF_TIMES + " INTEGER NOT NULL DEFAULT 0);";

        sqLiteDatabase.execSQL(SQL_CREATE_TASK_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
