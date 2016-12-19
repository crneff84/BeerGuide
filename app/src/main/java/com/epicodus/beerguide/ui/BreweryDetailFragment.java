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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BreweryDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.breweryImageView) ImageView mImageLabel;
    @Bind(R.id.breweryNameTextView) TextView mNameLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.saveBreweryButton) TextView mSaveBreweryButton;

    private Brewery mBrewery;
    private ArrayList<Brewery> mBreweries;
    private int mPosition;

    public static BreweryDetailFragment newInstance(ArrayList<Brewery> breweries, Integer position) {
        BreweryDetailFragment breweryDetailFragment = new BreweryDetailFragment();
        Bundle args = new Bundle();

        args.putParcelable(Constants.EXTRA_KEY_BREWERIES, Parcels.wrap(breweries));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);

        breweryDetailFragment.setArguments(args);
        return breweryDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBreweries = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_BREWERIES));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mBrewery = mBreweries.get(mPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brewery_detail, container, false);
        ButterKnife.bind(this, view);

        //Picasso.with(view.getContext()).load(mBrewery.getImageUrl()).into(mImageLabel);

        mNameLabel.setText(mBrewery.getName());
        mWebsiteLabel.setText(mBrewery.getWebsite());
        //mPhoneLabel.setText(mBrewery.getPhone());
        //mAddressLabel.setText(android.text.TextUtils.join(", ", mBrewery.getAddress()));

        mSaveBreweryButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveBreweryButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference breweryRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_BREWERIES)
                    .child(uid);

            DatabaseReference pushRef = breweryRef.push();
            String pushId = pushRef.getKey();
            mBrewery.setPushId(pushId);
            pushRef.setValue(mBrewery);

            Toast.makeText(getContext(),"Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
