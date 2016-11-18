package com.epicodus.beerguide;

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

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
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

        mFindBreweryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String location = mLocationEditText.getText().toString();
                if(location.equals("")) {
                    Toast.makeText(MainActivity.this, "Enter a Location", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, BreweryLocator.class);
                    intent.putExtra("location", location);
                    startActivity(intent);
                }
            }
        });



        mBeerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BeerActivity.class);
                startActivity(intent);
            }
        });

        mBreweriesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BreweriesActivity.class);
                startActivity(intent);
            }
        });
    }
}
