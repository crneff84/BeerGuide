package com.epicodus.beerguide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 12/2/16.
 */
public class BrewerySearchService {

    public static void findBreweries(String name, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BREWERY_SEARCH_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.SEARCH_QUERY, name);
        urlBuilder.addQueryParameter("key", Constants.BREWERY_DB_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Brewery> processResults(Response response) {
        ArrayList<Brewery> breweries = new ArrayList<>();

        try {
            String jsonData = response.body().string();

            if(response.isSuccessful()) {
                JSONObject brewerydbJSON = new JSONObject(jsonData);
                JSONArray breweriesJSON = brewerydbJSON.getJSONArray("data");
                for(int i = 0; i < breweriesJSON.length(); i++) {
                    JSONObject breweryJSON = breweriesJSON.getJSONObject(i);

                    String name = breweryJSON.getString("name");

                    Brewery brewery = new Brewery(name);
                    breweries.add(brewery);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return breweries;
    }
}
