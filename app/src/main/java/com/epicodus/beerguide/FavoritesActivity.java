package com.epicodus.beerguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        ArrayList<String> breweries = intent.getStringArrayListExtra("breweries");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, breweries);
        mFavoritesListView.setAdapter(adapter);
    }
}
