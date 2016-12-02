package com.epicodus.beerguide.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

public class BreweriesActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.breweryFindButton) Button mBreweryFindButton;
    @Bind(R.id.breweryEditText) EditText mBreweryEditText;
    @Bind(R.id.breweriesRecyclerView) RecyclerView mBreweriesRecyclerView;

    private BreweryListAdapter mAdapter;

    private ArrayList<Brewery> mBreweries = new ArrayList<>();

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
                final BrewerySearchService brewerySearchService = new BrewerySearchService();

                brewerySearchService.findBreweries(breweryInput, new Callback() {
                   @Override
                    public void onFailure(Call call, IOException e) {
                       e.printStackTrace();
                   }

                    @Override
                    public void onResponse(Call call, Response response) {
                        mBreweries = brewerySearchService.processResults(response);

                        BreweriesActivity.this.runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                mAdapter = new BreweryListAdapter(getApplicationContext(), mBreweries);
                                mBreweriesRecyclerView.setAdapter(mAdapter);
                                RecyclerView.LayoutManager layoutManager =
                                        new LinearLayoutManager(BreweriesActivity.this);
                                mBreweriesRecyclerView.setLayoutManager(layoutManager);
                                mBreweriesRecyclerView.setHasFixedSize(true);
                            }
                        });
                    }
                });
            }
        }
    }
}
