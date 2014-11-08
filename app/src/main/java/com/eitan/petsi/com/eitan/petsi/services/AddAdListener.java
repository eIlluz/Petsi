package com.eitan.petsi.com.eitan.petsi.services;

import retrofit.RetrofitError;

/**
 * Created by eitan on 24/10/2014.
 */
public interface AddAdListener {

    public void onAddSuccess();

    public void onAddFailed();

    public void onRestCallError(RetrofitError error);
}
