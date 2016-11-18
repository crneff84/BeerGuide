package com.epicodus.beerguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BreweryLocator extends AppCompatActivity {
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.brewerySearchListView) ListView mBrewerySearchListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery_locator);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("The following breweries are located near: " + location);
    }
}
