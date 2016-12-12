package com.epicodus.beerguide.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.beerguide.Constants;
import com.epicodus.beerguide.R;
import com.epicodus.beerguide.models.Brewery;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BreweryDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.breweryImageView) ImageView mImageLabel;
    @Bind(R.id.breweryNameTextView) TextView mNameLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveBreweryButton) TextView mSaveBreweryButton;

    private Brewery mBrewery;

    public static BreweryDetailFragment newInstance(Brewery brewery) {
        BreweryDetailFragment breweryDetailFragment = new BreweryDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("brewery", Parcels.wrap(brewery));
        breweryDetailFragment.setArguments(args);
        return breweryDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBrewery = Parcels.unwrap(getArguments().getParcelable("brewery"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brewery_detail, container, false);
        ButterKnife.bind(this, view);

        //Picasso.with(view.getContext()).load(mBrewery.getImageUrl()).into(mImageLabel);

        mNameLabel.setText(mBrewery.getName());
        //mPhoneLabel.setText(mBrewery.getPhone());
        //mAddressLabel.setText(android.text.TextUtils.join(", ", mBrewery.getAddress()));

        mSaveBreweryButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveBreweryButton) {
            DatabaseReference breweryRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_BREWERIES);
            breweryRef.push().setValue(mBrewery);
            Toast.makeText(getContext(),"Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
