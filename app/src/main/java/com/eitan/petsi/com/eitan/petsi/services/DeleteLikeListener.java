package com.eitan.petsi.com.eitan.petsi.services;

import retrofit.RetrofitError;

/**
 * Created by eitan on 09/11/2014.
 */
public interface DeleteLikeListener {

    public void onDeleteLikeSuccess();

    public void onDeleteLikeFailed();

    public void onRestCallError(RetrofitError error);
}
