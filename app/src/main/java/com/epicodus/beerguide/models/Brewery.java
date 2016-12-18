package com.epicodus.beerguide.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/2/16.
 */

@Parcel
public class Brewery {
    String name;
    String breweryId;
    private String pushId;

    public Brewery() {}

    public Brewery(String name, String breweryId) {
        this.name = name;
        this.breweryId = breweryId;
    }

    public String getName() {
        return name;
    }

    public String getBreweryId() {
        return breweryId;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}

