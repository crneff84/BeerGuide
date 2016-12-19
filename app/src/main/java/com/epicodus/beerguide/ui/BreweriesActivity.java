package com.epicodus.beerguide.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.epicodus.beerguide.Constants;
import com.epicodus.beerguide.R;
import com.epicodus.beerguide.adapters.BreweryListAdapter;
import com.epicodus.beerguide.models.Brewery;
import com.epicodus.beerguide.services.BrewerySearchService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BreweriesActivity extends AppCompatActivity implements View.OnClickListener {
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
//    private String mRecentBrewery;
//    @Bind(R.id.breweryFindButton) Button mBreweryFindButton;
//    @Bind(R.id.breweryEditText) EditText mBreweryEditText;
//    @Bind(R.id.breweriesRecyclerView) RecyclerView mBreweriesRecyclerView;
    @Bind(R.id.savedBreweriesButton) Button mSavedBreweriesButton;

//    private BreweryListAdapter mAdapter;
//    private ArrayList<Brewery> mBreweries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breweries2);
        ButterKnife.bind(this);

        Typeface chunkFiveFont = Typeface.createFromAsset(getAssets(), "fonts/Chunkfive.otf");
        mSavedBreweriesButton.setTypeface(chunkFiveFont);

//      mBreweryFindButton.setOnClickListener(this);
        mSavedBreweriesButton.setOnClickListener(this);
//      mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//      mRecentBrewery = mSharedPreferences.getString(Constants.PREFERENCES_BREWERY, null);
//
//      if (mRecentBrewery != null) {
//          getBreweries(mRecentBrewery);
//      }
   }

    @Override
    public void onClick(View v) {
//        if(v == mBreweryFindButton) {
//            String breweryInput = mBreweryEditText.getText().toString();
//            if(breweryInput.equals("")) {
//                Toast.makeText(BreweriesActivity.this, "Enter a Brewery", Toast.LENGTH_LONG).show();
//            } else {
//                getBreweries(breweryInput);
//            }
//        }
        if (v == mSavedBreweriesButton) {
            Intent intent = new Intent(BreweriesActivity.this, SavedBreweryListActivity.class);
            startActivity(intent);
        }
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search, menu);
//        ButterKnife.bind(this);
//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
//
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                addToSharedPreferences(query);
//                getBreweries(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }

//    private void addToSharedPreferences(String brewery) {
//        mEditor.putString(Constants.PREFERENCES_BREWERY, brewery).apply();
//    }

//    private void getBreweries(String breweryInput) {
//        final BrewerySearchService brewerySearchService = new BrewerySearchService();
//
//        brewerySearchService.findBreweries(breweryInput, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) {
//                mBreweries = brewerySearchService.processResults(response);
//
//                BreweriesActivity.this.runOnUiThread(new Runnable(){
//                    @Override
//                    public void run() {
//                        mAdapter = new BreweryListAdapter(getApplicationContext(), mBreweries);
//                        mBreweriesRecyclerView.setAdapter(mAdapter);
//                        RecyclerView.LayoutManager layoutManager =
//                                new LinearLayoutManager(BreweriesActivity.this);
//                        mBreweriesRecyclerView.setLayoutManager(layoutManager);
//                        mBreweriesRecyclerView.setHasFixedSize(true);
//                    }
//                });
//            }
//        });
//    }
}
