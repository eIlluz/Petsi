package com.eitan.petsi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by eitan on 08/08/2014.
 */
public class navDrawerAdapter extends ArrayAdapter<String> {

    private final Context  context;
    private final String[] actions;
    private final int[] imageId;

    public navDrawerAdapter(Context context,String[] actions, int[] imageId) {

        super(context, R.layout.drawer_item, actions);
        this.context = context;
        this.actions = actions;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Get the layout inflater.
        LayoutInflater lInflate = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = lInflate.inflate(R.layout.drawer_item,null,true);
        TextView actionTextView = (TextView)convertView.findViewById(R.id.nav_action_text);
        ImageView actionImageView = (ImageView)convertView.findViewById(R.id.nav_action_icon);

        actionTextView.setText(actions[position]);
        actionImageView.setImageResource(imageId[position]);
//        actionImageView.setImageResource(R.drawable.ic_drawer);
        return convertView;

    }
}
