package com.epicodus.beerguide.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/2/16.
 */

@Parcel
public class Brewery {
    String name;
    String breweryId;
    String website;
    String imageUrl;
    private String pushId;
    String index;

    public Brewery() {}

    public Brewery(String name, String breweryId, String website, String imageUrl) {
        this.name = name;
        this.breweryId = breweryId;
        this.website = website;
        this.imageUrl = imageUrl;
        this.index = "not_specified";
    }

    public String getName() {
        return name;
    }

    public String getBreweryId() {
        return breweryId;
    }

    public String getWebsite() {
        return website;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}

