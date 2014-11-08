package com.eitan.petsi.com.eitan.petsi.services;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by eitan on 08/11/2014.
 */
public class AddAdTask implements Callback<PostActionResponse>{

    private AddAdListener addAdListener;
    private String desc;
    private String size;
    private String type;
    private String user;
    private String petName;
    private String story;
    private String photoURL;
    private String gender;
    private String age;

    public AddAdTask(AddAdListener addAdListener, String desc, String size, String type, String user, String petName, String story, String photoURL, String gender, String age) {
        this.addAdListener = addAdListener;
        this.desc = desc;
        this.size = size;
        this.type = type;
        this.user = user;
        this.petName = petName;
        this.story = story;
        this.photoURL = photoURL;
        this.gender = gender;
        this.age = age;
    }


    public void addAd(){
        PetsiRestClient.get().addAd(desc,size,type,user,petName,story,photoURL,gender,age,this);
    }

    @Override
    public void success(PostActionResponse postActionResponse, Response response) {
        if (postActionResponse.isSuccess())
            addAdListener.onAddSuccess();
        else
            addAdListener.onAddFailed();
    }

    @Override
    public void failure(RetrofitError error) {
        addAdListener.onRestCallError(error);
    }
}
