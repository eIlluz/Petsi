package com.eitan.petsi.com.eitan.petsi.services;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by eitan on 08/11/2014.
 */
public class UpdateUserTask implements Callback<PostActionResponse> {

    private UpdateUserListener updateUserListener;
    private String id;
    private String name;
    private String lastName;
    private String phoneNum;
    private String address;
    private String birthDate;

    public UpdateUserTask(UpdateUserListener updateUserListener, String id, String firstName, String lastName, String phoneNum, String address,String birthDate) {
        this.updateUserListener = updateUserListener;
        this.id = id;
        this.name = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.address = address;
        this.birthDate = birthDate;
    }

    public void updateUser(){
        Map<String,String> fields = new HashMap<String, String>();

        if (id != null && !id.isEmpty())
            fields.put("id",id);
        if (name != null && !name.isEmpty())
            fields.put("name",name);
        if (lastName != null && !lastName.isEmpty())
            fields.put("lastName",lastName);
        if (address != null && !address.isEmpty())
            fields.put("address",address);
        if (phoneNum != null && !phoneNum.isEmpty())
            fields.put("phoneNum",phoneNum);
        if (birthDate != null && !birthDate.isEmpty())
            fields.put("birthDate",birthDate);

        PetsiRestClient.get().updateUser(fields, this);
    }
    @Override
    public void success(PostActionResponse postActionResponse, Response response) {

        if (postActionResponse.isSuccess()){
            updateUserListener.onUpdateSuccess(postActionResponse);
        }else {
            updateUserListener.onUpdateFailed(postActionResponse);
        }

    }

    @Override
    public void failure(RetrofitError error) {
        updateUserListener.onRestCallError(error);
    }
}
