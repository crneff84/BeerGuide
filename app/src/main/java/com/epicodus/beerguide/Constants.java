package com.epicodus.beerguide;

/**
 * Created by Guest on 12/2/16.
 */
public class Constants {
    public static final String BREWERY_DB_KEY = BuildConfig.BREWERY_DB_KEY;
    public static final String BEER_SEARCH_BASE_URL = "http://api.brewerydb.com/v2/search?&type=beer";
    public static final String BREWERY_SEARCH_BASE_URL = "http://api.brewerydb.com/v2/search?&type=brewery";
    public static final String SEARCH_QUERY = "q";
    public static final String  PREFERENCES_LOCATION_KEY = "location";
    public static final String FIREBASE_CHILD_SEARCHED_LOCATION = "searchedLocation";
    public static final String FIREBASE_CHILD_BREWERIES = "breweries";
    public static final String PREFERENCES_BREWERY = "brewery";
}
