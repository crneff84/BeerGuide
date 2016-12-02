package com.epicodus.beerguide.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.beerguide.R;

import org.w3c.dom.Text;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BreweryLocatorActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.brewerySearchListView) ListView mBrewerySearchListView;
    @Bind(R.id.mapButton) Button mMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery_locator);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("The following breweries are located near: " + location);

        mMapButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mMapButton) {
            String map = "http://maps.google.com/maps?q=" + getIntent().getStringExtra("location");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
            startActivity(mapIntent);
        }
    }
}
