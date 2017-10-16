package com.example.huzdi.taskkeeper.First.data;

import android.provider.BaseColumns;
import android.provider.ContactsContract;

/**
 * Created by huzdi on 16.10.2017.
 */

public class TaskContract {

    private TaskContract(){

    }

    public static final class HabitEntry implements BaseColumns
    {
        public final static String TABLE_NAME = "tasks";

        public final static String ID = BaseColumns._ID;

        public final static String COLUMN_TASK_NAME = "name";

        public final static String COLUMN_TASK_DESCRIPTION = "desc";

        public final static String COLUMN_NUMBER_OF_TIMES = "numberOfTimes";


    }}
