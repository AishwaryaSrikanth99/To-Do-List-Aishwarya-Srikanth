package com.example.android.todolist_as2551;
//libraries
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Aishwarya Srikanth on 10/1/2017.
 */

public class CustomAdapter extends ArrayAdapter<todoactivities> {
    Context context;

    LayoutInflater inf;
    public CustomAdapter(Context context, ArrayList<todoactivities> taskwork) {
        super(context,R.layout.rowdesign, taskwork);
        this.context = context;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        inf=LayoutInflater.from(getContext());
        View view1 = inf.inflate(R.layout.rowdesign,parent,false);
        todoactivities itemPosition =getItem(position);
        TextView tt =(TextView)view1.findViewById(R.id.tnid);
        TextView td =(TextView)view1.findViewById(R.id.tdid);
        final CheckBox chx = (CheckBox)view1.findViewById(R.id.cb1);
        tt.setText(itemPosition.getTaskwhat());
        td.setText(itemPosition.getTaskwhy());
        chx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if checkbox is checked, list view item should get deleted
                if(chx.isChecked())
                {
                    remove(position);
                }
            }
        });
        return view1;
    }
    //function to remove item
    public void remove(int position)
    {
        remove(getItem(position));
    }
}
