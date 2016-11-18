package com.epicodus.beerguide;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.breweryFindButton) Button mBreweryFindButton;
    @Bind(R.id.breweryEditText) EditText mBreweryEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface chunkFiveFont = Typeface.createFromAsset(getAssets(), "fonts/Chunkfive.otf");
        mAppNameTextView.setTypeface(chunkFiveFont);

        mBreweryFindButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String breweryInput = mBreweryEditText.getText().toString();
                if(breweryInput.equals("")) {
                    Toast.makeText(MainActivity.this, "Enter a Brewery", Toast.LENGTH_LONG).show();
                } else {

                }
            }
        });

    }
}
