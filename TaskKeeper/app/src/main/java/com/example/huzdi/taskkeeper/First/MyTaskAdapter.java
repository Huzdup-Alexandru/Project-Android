package com.example.huzdi.taskkeeper.First;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.huzdi.taskkeeper.R;

import java.util.ArrayList;

/**
 * Created by huzdi on 16.10.2017.
 */

public class MyTaskAdapter extends ArrayAdapter<Task> {

    ViewHolder viewHolder;

    private static class ViewHolder{
        private TextView itemView;
    }

    public MyTaskAdapter(Context context, int textViewResourceId, ArrayList<Task> tasks ){
        super(context,textViewResourceId,tasks);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.to_do_layout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.itemView = (TextView) convertView.findViewById(R.id.message);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Task item = getItem(position);
        if (item!= null) {
            // My layout has only one TextView
            // do whatever you want with your string and long
            viewHolder.itemView.setText(String.format("%s %d", item.getDescription()));
        }

        return convertView;
    }
}
