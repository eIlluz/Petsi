package com.eitan.petsi.com.eitan.petsi.services;

import retrofit.RetrofitError;

/**
 * Created by eitan on 09/11/2014.
 */
public interface AdLikeListener {

    public void onAdLikeSuccess();

    public void onAdLikeFailed();

    public void onRestCallError(RetrofitError error);
}
