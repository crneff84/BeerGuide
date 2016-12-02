package com.epicodus.beerguide.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.beerguide.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.breweriesButton) Button mBreweriesButton;
    @Bind(R.id.beerButton) Button mBeerButton;
    @Bind(R.id.findBreweryButton) Button mFindBreweryButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface chunkFiveFont = Typeface.createFromAsset(getAssets(), "fonts/Chunkfive.otf");
        mBreweriesButton.setTypeface(chunkFiveFont);
        mBeerButton.setTypeface(chunkFiveFont);
        mFindBreweryButton.setTypeface(chunkFiveFont);

        mBreweriesButton.setOnClickListener(this);
        mBeerButton.setOnClickListener(this);
        mFindBreweryButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String location = mLocationEditText.getText().toString();
        if(v == mFindBreweryButton) {
            if (location.equals("")) {
                Toast.makeText(MainActivity.this, "Enter a Location", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(MainActivity.this, BreweryLocatorActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }
        }

        if(v == mBreweriesButton) {
            Intent intent = new Intent(MainActivity.this, BreweriesActivity.class);
            startActivity(intent);
        }

        if(v == mBeerButton) {
            Intent intent = new Intent(MainActivity.this, BeerActivity.class);
            startActivity(intent);
        }
    }
}
