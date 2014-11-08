package com.eitan.petsi.com.eitan.petsi.services;

import com.eitan.petsi.views.FavImage;

import java.util.List;

import retrofit.RetrofitError;

/**
 * Created by eitan on 09/11/2014.
 */
public interface GetLikesListener {

    public void onGetLikesSuccess(List<FavRespond> likes);

    public void onRestCallError(RetrofitError error);
}
