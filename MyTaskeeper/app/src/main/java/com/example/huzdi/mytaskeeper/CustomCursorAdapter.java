package com.example.huzdi.mytaskeeper;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.huzdi.mytaskeeper.database.TaskContract;


public class CustomCursorAdapter extends RecyclerView.Adapter<CustomCursorAdapter.TaskViewHolder> {

    // Class variables for the Cursor that holds task data and the Context
    private Cursor mCursor;
    private Context mContext;


    public CustomCursorAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Inflate the task_layout to a view
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.task_layout, parent, false);


        return new TaskViewHolder(view);

    }




    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {

        int idIndex = mCursor.getColumnIndex(TaskContract.TaskEntry._ID);
        int taskIndex = mCursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_TASK);
        int descriptionIndex = mCursor.getColumnIndex(TaskContract.TaskEntry.DESCRIPTION);
        int priorityIndex = mCursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_PRIORITY);
        int dateIndex = mCursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_DATE);
        int hourIndex = mCursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_HOUR);


        mCursor.moveToPosition(position);

        final int id = mCursor.getInt(idIndex);
        String task = mCursor.getString(taskIndex);
        String description = mCursor.getString(descriptionIndex);
        int priority = mCursor.getInt(priorityIndex);
        String date = mCursor.getString(dateIndex);
        String hour = mCursor.getString(hourIndex);

        holder.itemView.setTag(id);
        holder.taskName.setText(task);
        holder.taskDescriptionView.setText(description);


        String priorityString = "" + priority;
        holder.priorityView.setText(priorityString);

        holder.dateTextView.setText(date);
        holder.hourTextView.setText(hour);


        GradientDrawable priorityCircle = (GradientDrawable) holder.priorityView.getBackground();
        int priorityColor = getPriorityColor(priority);
        priorityCircle.setColor(priorityColor);

        holder.taskName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),AddTaskActivity.class);
                view.getContext().startActivity(intent);

            }
        });


    }




    private int getPriorityColor(int priority) {
        int priorityColor = 0;

        switch (priority) {
            case 1:
                priorityColor = ContextCompat.getColor(mContext, R.color.materialRed);
                break;
            case 2:
                priorityColor = ContextCompat.getColor(mContext, R.color.materialOrange);
                break;
            case 3:
                priorityColor = ContextCompat.getColor(mContext, R.color.materialYellow);
                break;
            default:
                break;
        }
        return priorityColor;
    }


    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }


    public Cursor swapCursor(Cursor c) {

        if (mCursor == c) {
            return null;
        }
        Cursor temp = mCursor;
        this.mCursor = c;


        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }


    class TaskViewHolder extends RecyclerView.ViewHolder {


        TextView taskDescriptionView;
        TextView priorityView;
        TextView dateTextView;
        TextView hourTextView;
        TextView taskName;


        public TaskViewHolder(View itemView) {
            super(itemView);

            taskName = itemView.findViewById(R.id.taskName);
            taskDescriptionView = itemView.findViewById(R.id.taskDescription);
            priorityView = itemView.findViewById(R.id.priorityTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            hourTextView = itemView.findViewById(R.id.hourTextView);

        }
    }
}