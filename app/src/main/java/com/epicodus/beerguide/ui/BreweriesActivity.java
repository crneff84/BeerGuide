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
    @Bind(R.id.savedBreweriesButton) Button mSavedBreweriesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breweries2);
        ButterKnife.bind(this);

        mSavedBreweriesButton.setOnClickListener(this);
   }

    @Override
    public void onClick(View v) {

        if (v == mSavedBreweriesButton) {
            Intent intent = new Intent(BreweriesActivity.this, SavedBreweryListActivity.class);
            startActivity(intent);
        }
    }
}
