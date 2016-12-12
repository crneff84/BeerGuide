package com.epicodus.beerguide.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/2/16.
 */

@Parcel
public class Brewery {
    String name;
    String id;

    public Brewery() {}

    public Brewery(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

}

