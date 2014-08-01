package com.eitan.petsi;

import android.app.Application;
import android.content.Context;

/**
 * Created by eitan on 26/07/2014.
 */
public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
