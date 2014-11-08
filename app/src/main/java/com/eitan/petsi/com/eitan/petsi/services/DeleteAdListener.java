package com.eitan.petsi.com.eitan.petsi.services;

import retrofit.RetrofitError;

/**
 * Created by eitan on 08/11/2014.
 */
public interface DeleteAdListener {

    public void onDeleteSuccess();

    public void onDeleteFailed();

    public void onRestCallError(RetrofitError error);
}
