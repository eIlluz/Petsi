package com.eitan.petsi.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by eitan on 06/09/2014.
 */
public class PetListProvider {

    private Map<String, Pet> mPetMap = new HashMap<String, Pet>();

    public void setPetList(ArrayList<Pet> petList)
    {
        for (Pet pet : petList)
        {
            mPetMap.put(pet.getAdData().getAdID(),pet);
        }
    }

    public ArrayList<Pet> getPetArray()
    {
        ArrayList<Pet> petList = new ArrayList<Pet>();

        Iterator it = mPetMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            petList.add((Pet)pairs.getValue());
        }

        return petList;
    }
    public Pet getPetByAdID(String adID)
    {
        return mPetMap.get(adID);
    }

    public void removePet(String adID)
    {
        mPetMap.remove(adID);
    }

    public void addPet(Pet pet)
    {
        mPetMap.put(pet.getAdData().getAdID(),pet);
    }
}
