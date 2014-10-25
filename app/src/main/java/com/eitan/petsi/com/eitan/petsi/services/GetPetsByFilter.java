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

    public ArrayList<Pet> GetPets() throws GetPetsException {
        ArrayList<Pet> petsList = new ArrayList<Pet>();

        //-----------------------------------------------------------------------------------------------------
        //-----------------------------------------------------------------------------------------------------
        Calendar cal = Calendar.getInstance();

        cal.set(2014,Calendar.JANUARY,06);
        petsList.add(new Pet(new AdData("0001","eitanilluz@gmail.com",cal,cal,"open",7),
                new OwnerDetails("Eitan","05050000","Tel Aviv","b@e.com"),
                new PetDetails("Rexi","Male",2,"Dog","Very nice","Little nice dog","eitan-emr-1:imagaes/puppy.jpg","Big")));

        cal.set(2014,Calendar.APRIL,21);
        petsList.add(new Pet(new AdData("0005","b@mosh.com",cal,cal,"open",3),
                new OwnerDetails("Eitan","05050000","Tel Aviv","b@e.com"),
                new PetDetails("Shmoop","Male",2,"Dog","cool dog!","Big dog with bigger heart","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrRYr2V3lRDMcEA_fhHSBiTP0n0a-TvMGCdY-zz18g66ifus0G","Big")));

        cal.set(2014, Calendar.JULY, 12);
        petsList.add(new Pet(new AdData("0019","b@michal.com",cal,cal,"open",1),
                new OwnerDetails("Eitan","05050000","Tel Aviv","b@e.com"),
                new PetDetails("Seal","Female",2,"Cat","Very nice","Little nice cat","http://upload.wikimedia.org/wikipedia/commons/2/22/Turkish_Van_Cat.jpg","Big")));

        cal.set(2014,Calendar.MARCH,16);
        petsList.add(new Pet(new AdData("0022","b@eitan.com",cal,cal,"open",6),
                new OwnerDetails("Eitan","05050000","Tel Aviv","b@e.com"),
                new PetDetails("Joom","Male",4,"Dog","funky dog","good with children","http://www.yorkvision.co.uk/wp-content/uploads/2014/04/220.jpg","Big")));

        cal.set(2014,Calendar.APRIL,01);
        petsList.add(new Pet(new AdData("0035","b@mosh.com",cal,cal,"open",11),
                new OwnerDetails("Shlomo","05012345","Haifa","b@e.com"),
                new PetDetails("Cook","Male",2,"Dog","Speeeedy","Best friend of man, really good with children","http://m.flikie.com/ImageData/WallPapers/0e7feb7664664823a7e07cd5bc2de4b6.jpg","Big")));

        cal.set(2014, Calendar.DECEMBER, 22);
        petsList.add(new Pet(new AdData("0049","b@michal.com",cal,cal,"open",25),
                new OwnerDetails("Shlomo","05012345","Haifa","b@e.com"),
                new PetDetails("kitty","Female",1,"Cat","your cat!","Kitty is beautiful and playful kitten. She was saved from a street life and now looking for a loving home","http://images4.fanpop.com/image/photos/14700000/Beautifull-cat-cats-14749885-1600-1200.jpg","Small")));

        cal.set(2014, Calendar.MAY, 03);
        petsList.add(new Pet(new AdData("0051","b@michal.com",cal,cal,"open",7),
                new OwnerDetails("Shlomo","05012345","Haifa","b@e.com"),
                new PetDetails("Bulb","Male",5,"Dog","Smart dog","Trained dog, happy and loving","https://www.petfinder.com/wp-content/uploads/2012/11/147083304-dogs-home-alone-all-day-632x475.jpg","Small")));
        //-----------------------------------------------------------------------------------------------------
        //-----------------------------------------------------------------------------------------------------

        if (petsList.size() == 0)
        {
            throw new GetPetsException("No pets found");
        }
        return petsList;
    }
}
