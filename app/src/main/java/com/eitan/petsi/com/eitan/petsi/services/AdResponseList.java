package com.eitan.petsi.com.eitan.petsi.services;

import java.util.List;

/**
 * Created by eitan on 01/11/2014.
 */
public class AdResponseList {

    private List<AdResponseItem> adResponseItems;

    public AdResponseList(List<AdResponseItem> adResponseItems) {
        this.adResponseItems = adResponseItems;
    }

    public List<AdResponseItem> getAdResponseItems() {
        return adResponseItems;
    }

    public void setAdResponseItems(List<AdResponseItem> adResponseItems) {
        this.adResponseItems = adResponseItems;
    }
}
