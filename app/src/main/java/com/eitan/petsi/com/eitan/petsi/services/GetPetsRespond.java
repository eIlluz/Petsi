package com.eitan.petsi.com.eitan.petsi.services;

import com.eitan.petsi.data.Pet;

import java.util.ArrayList;

/**
 * Created by eitan on 30/08/2014.
 */
public class GetPetsRespond {

    private boolean success;
    private String message;
    private ArrayList<Pet> mPetlist;

    public GetPetsRespond(boolean success, String message, ArrayList<Pet> mPetlist) {
        this.success = success;
        this.message = message;
        this.mPetlist = mPetlist;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Pet> getPetlist() {
        return mPetlist;
    }

    public void setmPetlist(ArrayList<Pet> Petlist) {
        this.mPetlist = mPetlist;
    }
}
