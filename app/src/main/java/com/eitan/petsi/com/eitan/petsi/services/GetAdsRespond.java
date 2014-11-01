package com.eitan.petsi.com.eitan.petsi.services;

import com.eitan.petsi.data.Pet;

import java.util.List;

import retrofit.RetrofitError;

/**
 * Created by eitan on 01/11/2014.
 */
public interface GetAdsRespond {

    public void onAdsLoaded(List<Pet> ads);

    public void onRestCallError(RetrofitError error);
}
