package com.eitan.petsi.data;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eitan.petsi.R;

import java.util.List;

/**
 * Created by eitan on 23/08/2014.
 */
public class petListAdapter extends ArrayAdapter<Pet>
{

    Context mContext;
    List<Pet> mPetList;


    public petListAdapter(Context context, List<Pet> objects) {
        super(context, R.layout.pet_item, objects);
        mContext = context;
        mPetList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater lInflate = (LayoutInflater)mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = lInflate.inflate(R.layout.pet_item,null,true);
        TextView petName = (TextView)convertView.findViewById(R.id.petname);
        petName.setText(mPetList.get(position).getPetDetails().getName());
        return convertView;
    }
}
