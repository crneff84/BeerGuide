package com.epicodus.beerguide.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/2/16.
 */

@Parcel
public class Brewery {
    String mName;
    String mId;

    public Brewery() {}

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

