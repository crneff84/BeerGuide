package com.epicodus.beerguide;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BreweriesActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.breweryFindButton) Button mBreweryFindButton;
    @Bind(R.id.breweryEditText) EditText mBreweryEditText;
    @Bind(R.id.breweriesListView) ListView mBreweriesListView;
    private ArrayList<String> breweries = new ArrayList<>();
    public static final String TAG = "logs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breweries2);
        ButterKnife.bind(this);

        Typeface chunkFiveFont = Typeface.createFromAsset(getAssets(), "fonts/Chunkfive.otf");
        mBreweryFindButton.setTypeface(chunkFiveFont);

        mBreweryFindButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == mBreweryFindButton) {
            String breweryInput = mBreweryEditText.getText().toString();
            if(breweryInput.equals("")) {
                Toast.makeText(BreweriesActivity.this, "Enter a Brewery", Toast.LENGTH_LONG).show();
            } else {
                breweries.add(breweryInput);
                ArrayAdapter adapter = new ArrayAdapter(BreweriesActivity.this, android.R.layout.simple_list_item_1, breweries);
                mBreweriesListView.setAdapter(adapter);
            }
        }
    }
}
