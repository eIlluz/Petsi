package com.eitan.petsi.com.eitan.petsi.services;

import com.eitan.petsi.data.AdData;
import com.eitan.petsi.data.OwnerDetails;
import com.eitan.petsi.data.Pet;
import com.eitan.petsi.data.PetDetails;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by eitan on 30/08/2014.
 */
public class GetPetsByFilter
{
    private String mAnimal;
    private String mAge;
    private String mSize;
    private String mGender;

    public GetPetsByFilter(String mAnimal, String mAge, String mSize, String mGender) {
        this.mAnimal = mAnimal;
        this.mAge = mAge;
        this.mSize = mSize;
        this.mGender = mGender;
    }

    public String getmAnimal() {
        return mAnimal;
    }

    public void setmAnimal(String mAnimal) {
        this.mAnimal = mAnimal;
    }

    public String getmAge() {
        return mAge;
    }

    public void setmAge(String mAge) {
        this.mAge = mAge;
    }

    public String getmSize() {
        return mSize;
    }

    public void setmSize(String mSize) {
        this.mSize = mSize;
    }

    public String getmGender() {
        return mGender;
    }

    public void setmGender(String mGender) {
        this.mGender = mGender;
    }

    public ArrayList<Pet> GetPets()
    {
        ArrayList<Pet> petsList = new ArrayList<Pet>();

        Calendar cal = Calendar.getInstance();

        cal.set(2014,Calendar.JANUARY,06);
        petsList.add(new Pet(new AdData("0001","b@eitan.com",cal,cal,"open"),
                new OwnerDetails("Eitan","05050000","Tel Aviv","b@e.com"),
                new PetDetails("Rexi","Male",2,"Dog","Very nice","Little nice dog","http://wallpaperhall.com/wp-content/uploads/2014/06/puppy-cutest-puppy-ever-koxlx6zg.jpg","Big")));

        cal.set(2014,Calendar.APRIL,21);
        petsList.add(new Pet(new AdData("0005","b@mosh.com",cal,cal,"open"),
                new OwnerDetails("Eitan","05050000","Tel Aviv","b@e.com"),
                new PetDetails("Shmoop","Male",2,"Dog","cool dog!","Big dog with bigger heart","http://bunkblog.net/wp-content/uploads/2013/07/BMD5.jpg","Big")));

        cal.set(2014,Calendar.JULY,12);
        petsList.add(new Pet(new AdData("0019","b@michal.com",cal,cal,"open"),
                new OwnerDetails("Eitan","05050000","Tel Aviv","b@e.com"),
                new PetDetails("Seal","Female",2,"Cat","Very nice","Little nice dog","data:image/jpeg;base64,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","Big")));

        return petsList;
    }
}
