package com.epicodus.beerguide.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.beerguide.models.Brewery;
import com.epicodus.beerguide.ui.BreweryDetailFragment;

import java.util.ArrayList;

/**
 * Created by Chance on 12/10/16.
 */

public class BreweryPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Brewery> mBreweries;

    public BreweryPagerAdapter(FragmentManager fm, ArrayList<Brewery> breweries) {
        super(fm);
        mBreweries = breweries;
    }

    @Override
    public Fragment getItem(int position) {
        return BreweryDetailFragment.newInstance(mBreweries, position);
    }

    @Override
    public int getCount() {
        return mBreweries.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mBreweries.get(position).getName();
    }
}
