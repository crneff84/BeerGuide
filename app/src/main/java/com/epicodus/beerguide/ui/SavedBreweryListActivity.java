package com.epicodus.beerguide.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.beerguide.Constants;
import com.epicodus.beerguide.R;
import com.epicodus.beerguide.adapters.FirebaseBreweryViewHolder;
import com.epicodus.beerguide.models.Brewery;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedBreweryListActivity extends AppCompatActivity {
    private DatabaseReference mBreweryReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.breweriesRecyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breweries2);
        ButterKnife.bind(this);

        mBreweryReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BREWERIES);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Brewery, FirebaseBreweryViewHolder>
                (Brewery.class, R.layout.brewery_list_item, FirebaseBreweryViewHolder.class,
                        mBreweryReference) {

            @Override
            protected void populateViewHolder(FirebaseBreweryViewHolder viewHolder,
                                              Brewery model, int position) {
                viewHolder.bindBrewery(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}