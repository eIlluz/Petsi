package com.eitan.petsi.com.eitan.petsi.services;

import com.eitan.petsi.data.AdData;
import com.eitan.petsi.data.OwnerDetails;
import com.eitan.petsi.data.Pet;
import com.eitan.petsi.data.PetDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by eitan on 01/11/2014.
 */
public class GetAdsTask implements Callback<List<AdResponseItem>> {


    private GetAdsRespond getAdsRespond;
    private int age;
    private String type;
    private String gender;
    private String size;
    private String user;

    public GetAdsTask(GetAdsRespond getAdsRespond, int age, String type, String gender, String size, String user) {
        this.getAdsRespond = getAdsRespond;
        this.age = age;
        this.type = type;
        this.gender = gender;
        this.size = size;
        this.user = user;
    }

    public void getAds(){
        PetsiRestClient.get().getAd(age,size,gender,type,user,this);
    }

    @Override
    public void success(List<AdResponseItem> adResponseList, Response response) {

        ArrayList<Pet> petsList = new ArrayList<Pet>();

        if (adResponseList != null && adResponseList != null) {
            for (AdResponseItem item : adResponseList) {

                petsList.add(new Pet(new AdData(Integer.toString(item.getId()), item.getUser(), item.getCreatedOn(), item.getCreatedOn(), "open", 7),
                        new OwnerDetails(item.getUser(), "05050000", "Tel Aviv", item.getUser()),
                        new PetDetails(item.getPetName(), item.getGender(), (int) item.getAge(), item.getType(), item.getDescription(), item.getStory(),
                                item.getPhotoURL(), item.getSize())));
            }
        }
        getAdsRespond.onAdsLoaded(petsList);
    }

    @Override
    public void failure(RetrofitError error) {

        getAdsRespond.onRestCallError(error);
    }
}
