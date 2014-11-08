package com.eitan.petsi.com.eitan.petsi.services;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by eitan on 08/11/2014.
 */
public class DeleteAdTask implements Callback<PostActionResponse>{

    private DeleteAdListener deleteAdListener;
    private String id;

    public DeleteAdTask(DeleteAdListener deleteAdListener, String id) {
        this.deleteAdListener = deleteAdListener;
        this.id = id;
    }

    public void deleteAd(){
        PetsiRestClient.get().deleteAd(id,this);
    }
    @Override
    public void success(PostActionResponse postActionResponse, Response response) {

        if (postActionResponse.isSuccess()){
            deleteAdListener.onDeleteSuccess();
        }else{
            deleteAdListener.onDeleteFailed();
        }
    }

    @Override
    public void failure(RetrofitError error) {
        deleteAdListener.onRestCallError(error);
    }
}
