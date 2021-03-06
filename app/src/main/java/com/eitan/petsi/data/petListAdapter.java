package com.eitan.petsi.data;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eitan.petsi.App;
import com.eitan.petsi.R;
import com.eitan.petsi.aws.FileDownloadCallBack;
import com.eitan.petsi.views.FavImage;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by eitan on 23/08/2014.
 */
public class petListAdapter extends ArrayAdapter<Pet>
{

    Context mContext;
    ArrayList<Pet> mPetList;
    LayoutInflater mInflate;
    App app;

    public petListAdapter(Context context, ArrayList<Pet> objects,App app) {
        super(context, R.layout.pet_item, objects);
        mContext = context;
        mPetList = objects;
        mInflate = (LayoutInflater)mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        this.app = app;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null)
        {
            convertView = mInflate.inflate(R.layout.pet_item,null,true);
            holder = new ViewHolder();
            holder.petName = (TextView)convertView.findViewById(R.id.petname);
            holder.petOtherInfo = (TextView)convertView.findViewById(R.id.petDetails);
            holder.petImage = (ImageView)convertView.findViewById(R.id.petimage);
            holder.favImage = (FavImage)convertView.findViewById(R.id.det_fav_btn);
            convertView.setTag(holder);
        } else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.petName.setText(mPetList.get(position).getPetDetails().getName());
        holder.petOtherInfo.setText(mPetList.get(position).getPetDetails().getGender() + ", " +
                mPetList.get(position).getPetDetails().getAge() + ", " +
                mPetList.get(position).getPetDetails().getSize());
        holder.favImage.turnOnOff(app.isOnFavList(mPetList.get(position).getAdData().getAdID()));

        Activity activity = (Activity)mContext;
        App app = (App)activity.getApplication();
        //app.getFileForS3Key(mPetList.get(position).getPetDetails().getPhotoUrl(),new FileDownloadCallBackPicasso(holder));


        Picasso.with(mContext).load(mPetList.get(position).getPetDetails().getPhotoUrl())
                .resizeDimen(R.dimen.pet_item_width,R.dimen.pet_item_height)
               .placeholder(R.drawable.ic_dog)
               .error(R.drawable.ic_launcher)
               .into(holder.petImage);

        return convertView;
    }

    private static class ViewHolder {
        public TextView petName;
        public ImageView petImage;
        public TextView petOtherInfo;
        public FavImage favImage;
    }

}
