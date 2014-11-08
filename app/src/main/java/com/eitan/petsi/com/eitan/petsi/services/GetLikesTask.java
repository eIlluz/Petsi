package com.eitan.petsi.com.eitan.petsi.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by eitan on 09/11/2014.
 */
public class GetLikesTask implements Callback<List<FavRespond>>{

    private GetLikesListener getLikesListener;
    private String user;
    private String adId;

    public GetLikesTask(GetLikesListener getLikesListener) {
        this.getLikesListener = getLikesListener;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public void getLikes(){
        Map<String,String> fields = new HashMap<String, String>();

        if (user != null && !user.isEmpty())
            fields.put("user",user);
        if (adId != null && !adId.isEmpty())
            fields.put("adID",adId);

        PetsiRestClient.get().getLikes(fields,this);
    }
    @Override
    public void success(List<FavRespond> favResponds, Response response) {

        getLikesListener.onGetLikesSuccess(favResponds);
    }

    @Override
    public void failure(RetrofitError error) {
        getLikesListener.onRestCallError(error);
    }
}
