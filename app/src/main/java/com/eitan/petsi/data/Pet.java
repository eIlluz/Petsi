package com.eitan.petsi.data;

/**
 * Created by eitan on 25/07/2014.
 */
public class Pet {

    private AdData adData;
    private OwnerDetails ownerDetails;
    private PetDetails petDetails;

    public AdData getAdData() {
        return adData;
    }

    public void setAdData(AdData adData) {
        this.adData = adData;
    }

    public OwnerDetails getOwnerDetails() {
        return ownerDetails;
    }

    public void setOwnerDetails(OwnerDetails ownerDetails) {
        this.ownerDetails = ownerDetails;
    }

    public PetDetails getPetDetails() {
        return petDetails;
    }

    public void setPetDetails(PetDetails petDetails) {
        this.petDetails = petDetails;
    }

    public Pet(AdData adData, OwnerDetails ownerDetails, PetDetails petDetails) {
        this.adData = adData;
        this.ownerDetails = ownerDetails;
        this.petDetails = petDetails;
    }
}
