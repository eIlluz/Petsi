package com.eitan.petsi.com.eitan.petsi.services;

import java.util.List;

import retrofit.RetrofitError;

/**
 * Created by eitan on 09/11/2014.
 */
public interface GetAdsUserLikesListener {

    public void onGetAdsUserLikesSuccess(List<AdResponseItem> likes);

    public void onRestCallError(RetrofitError error);
}
