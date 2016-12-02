package com.epicodus.beerguide.models;

/**
 * Created by Guest on 12/2/16.
 */
public class Beer {
    private String mName;
    private String mId;

    public Beer(String name, String id) {
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
