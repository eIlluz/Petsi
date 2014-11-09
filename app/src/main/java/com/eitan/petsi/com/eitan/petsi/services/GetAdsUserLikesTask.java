package com.eitan.petsi.com.eitan.petsi.services;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by eitan on 09/11/2014.
 */
public class GetAdsUserLikesTask implements Callback<List<AdResponseItem>>{

    private GetAdsUserLikesListener getAdsUserLikesListener;
    private String user;

    public GetAdsUserLikesTask(GetAdsUserLikesListener getAdsUserLikesListener, String user) {
        this.getAdsUserLikesListener = getAdsUserLikesListener;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void getLikes(){

            PetsiRestClient.get().getLikesForUser(user,this);;
    }
    @Override
    public void success(List<AdResponseItem> favResponds, Response response) {

        getAdsUserLikesListener.onGetAdsUserLikesSuccess(favResponds);
    }

    @Override
    public void failure(RetrofitError error) {
        getAdsUserLikesListener.onRestCallError(error);
    }
}
