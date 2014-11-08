package com.eitan.petsi.com.eitan.petsi.services;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by eitan on 09/11/2014.
 */
public class AdLikeTask implements Callback<PostActionResponse> {

    private AdLikeListener adLikeListener;
    private String user;
    private String adID;

    public AdLikeTask(AdLikeListener adLikeListener, String user, String adID) {
        this.adLikeListener = adLikeListener;
        this.user = user;
        this.adID = adID;
    }

    public void adLike(){

        PetsiRestClient.get().adLike(adID,user,this);
    }

    @Override
    public void success(PostActionResponse postActionResponse, Response response) {
        if (postActionResponse.isSuccess())
            adLikeListener.onAdLikeSuccess();
        else
            adLikeListener.onAdLikeFailed();
    }

    @Override
    public void failure(RetrofitError error) {
        adLikeListener.onRestCallError(error);
    }
}
