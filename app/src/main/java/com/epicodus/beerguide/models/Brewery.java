package com.epicodus.beerguide.models;

/**
 * Created by Guest on 12/2/16.
 */
public class Brewery {
    private String mName;
    private String mId;

    public Brewery(String name, String id) {
        this.mName = name;
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public String getId() {
        return mId;
    }

}

