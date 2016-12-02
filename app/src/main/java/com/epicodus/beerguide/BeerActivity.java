package com.epicodus.beerguide;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BeerActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.beerAddButton) Button mBeerAddButton;
    @Bind(R.id.beerEditText) EditText mBeerEditText;
    @Bind(R.id.beerListView) ListView mBeerListView;
    private ArrayList<String> beers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
        ButterKnife.bind(this);

        Typeface chunkFiveFont = Typeface.createFromAsset(getAssets(), "fonts/Chunkfive.otf");
        mBeerAddButton.setTypeface(chunkFiveFont);

        mBeerAddButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String beerInput = mBeerEditText.getText().toString();
        if(beerInput.equals("")) {
            Toast.makeText(BeerActivity.this, "Enter a Beer", Toast.LENGTH_LONG).show();
        } else {
            beers.add(beerInput);
            ArrayAdapter adapter = new ArrayAdapter(BeerActivity.this, android.R.layout.simple_list_item_1, beers);
            mBeerListView.setAdapter(adapter);
        }
    }
}
