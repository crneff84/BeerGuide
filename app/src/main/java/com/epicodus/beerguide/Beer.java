package com.epicodus.beerguide;

/**
 * Created by Guest on 12/2/16.
 */
public class Beer {
    private String mName;
    private String mDescription;
    private String mAbv;

    public Beer(String name, String description, String abv) {
        this.mName = name;
        this.mDescription = description;
        this.mAbv = abv;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getAbv() {
        return mAbv;
    }
}
