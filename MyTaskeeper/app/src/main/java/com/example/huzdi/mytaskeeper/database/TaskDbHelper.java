package com.example.huzdi.mytaskeeper.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by huzdi on 23.10.2017.
 */

public class TaskDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "taskDb.db";

    private static final int VERSION = 6;


    TaskDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_TABLE = "CREATE TABLE " + TaskContract.TaskEntry.TABLE_NAME + " (" +
                TaskContract.TaskEntry._ID + " INTEGER PRIMARY KEY, " +
                TaskContract.TaskEntry.COLUMN_TASK + " TEXT NOT NULL, " +
                TaskContract.TaskEntry.DESCRIPTION + " TEXT, " +
                TaskContract.TaskEntry.COLUMN_PRIORITY + " INTEGER NOT NULL, " +
                TaskContract.TaskEntry.COLUMN_DATE + " TEXT, " +
                TaskContract.TaskEntry.COLUMN_HOUR + " TEXT);";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TaskContract.TaskEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
