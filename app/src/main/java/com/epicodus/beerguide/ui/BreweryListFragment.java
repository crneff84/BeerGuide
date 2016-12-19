package com.epicodus.beerguide.ui;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.beerguide.Constants;
import com.epicodus.beerguide.R;
import com.epicodus.beerguide.adapters.BreweryListAdapter;
import com.epicodus.beerguide.models.Brewery;
import com.epicodus.beerguide.services.BrewerySearchService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BreweryListFragment extends Fragment {
    @Bind(R.id.breweriesRecyclerView) RecyclerView mBreweriesRecyclerView;

    private BreweryListAdapter mAdapter;
    public ArrayList<Brewery> mBreweries = new ArrayList<>();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentBrewery;


    public BreweryListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mSharedPreferences.edit();

        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brewery_list, container, false);
        ButterKnife.bind(this, view);

        mRecentBrewery = mSharedPreferences.getString(Constants.PREFERENCES_BREWERY, null);

        if (mRecentBrewery != null) {
            getBreweries(mRecentBrewery);
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getBreweries(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void addToSharedPreferences(String brewery) {
        mEditor.putString(Constants.PREFERENCES_BREWERY, brewery).apply();
    }

    private void getBreweries(String breweryInput) {
        final BrewerySearchService brewerySearchService = new BrewerySearchService();

        brewerySearchService.findBreweries(breweryInput, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mBreweries = brewerySearchService.processResults(response);

                getActivity().runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        mAdapter = new BreweryListAdapter(getActivity(), mBreweries);
                        mBreweriesRecyclerView.setAdapter(mAdapter);

                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(getActivity());

                        mBreweriesRecyclerView.setLayoutManager(layoutManager);
                        mBreweriesRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

}
