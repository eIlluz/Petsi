package com.eitan.petsi.com.eitan.petsi.services;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by eitan on 09/11/2014.
 */
public class DeleteLikeTask implements Callback<PostActionResponse> {

    private DeleteLikeListener deleteLikeListener;
    private String user;
    private String adID;

    public DeleteLikeTask(DeleteLikeListener deleteLikeListener, String user, String adID) {
        this.deleteLikeListener = deleteLikeListener;
        this.user = user;
        this.adID = adID;
    }

    public void deleteLike(){

        PetsiRestClient.get().deleteLike(adID,user,this);
    }

    @Override
    public void success(PostActionResponse postActionResponse, Response response) {
        if (postActionResponse.isSuccess())
            deleteLikeListener.onDeleteLikeSuccess();
        else
            deleteLikeListener.onDeleteLikeFailed();
    }

    @Override
    public void failure(RetrofitError error) {
        deleteLikeListener.onRestCallError(error);
    }
}
