package com.epicodus.beerguide;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.epicodus.beerguide.adapters.BeerListAdapter;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BeerActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.beerFindButton) Button mBeerFindButton;
    @Bind(R.id.beerEditText) EditText mBeerEditText;
    @Bind(R.id.beerRecyclerView) RecyclerView mBeerRecyclerView;

    private BeerListAdapter mAdapter;

    private ArrayList<Beer> mBeers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
        ButterKnife.bind(this);

        Typeface chunkFiveFont = Typeface.createFromAsset(getAssets(), "fonts/Chunkfive.otf");
        mBeerFindButton.setTypeface(chunkFiveFont);

        mBeerFindButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == mBeerFindButton) {
            String beerInput = mBeerEditText.getText().toString();
            if(beerInput.equals("")) {
                Toast.makeText(BeerActivity.this, "Enter a Beer", Toast.LENGTH_LONG).show();
            } else {
                final BeerSearchService beerSearchService = new BeerSearchService();

                beerSearchService.findBeers(beerInput, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) {
                        mBeers = beerSearchService.processResults(response);

                        BeerActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter = new BeerListAdapter(getApplicationContext(), mBeers);
                                mBeerRecyclerView.setAdapter(mAdapter);
                                RecyclerView.LayoutManager layoutManager =
                                        new LinearLayoutManager(BeerActivity.this);
                                mBeerRecyclerView.setLayoutManager(layoutManager);
                                mBeerRecyclerView.setHasFixedSize(true);
                            }
                        });
                    }
                });
            }
        }
    }
}
