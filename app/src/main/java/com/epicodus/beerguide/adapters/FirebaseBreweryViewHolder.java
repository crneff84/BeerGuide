package com.epicodus.beerguide.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.beerguide.Constants;
import com.epicodus.beerguide.R;
import com.epicodus.beerguide.models.Brewery;
import com.epicodus.beerguide.ui.BreweryDetailActivity;
import com.epicodus.beerguide.util.ItemTouchHelperViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseBreweryViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;
    public ImageView mBreweryImageView;

    public FirebaseBreweryViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindBrewery(Brewery brewery) {
        mBreweryImageView = (ImageView) mView.findViewById(R.id.breweryImageView);
        TextView breweryNameTextView = (TextView) mView.findViewById(R.id.breweryNameTextView);

        if(brewery.getImageUrl().equals("N/A")) {

        } else {
            Picasso.with(mContext)
                    .load(brewery.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mBreweryImageView);
        }

        breweryNameTextView.setText(brewery.getName());
    }

    @Override
    public void onItemSelected() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_on);
        set.setTarget(itemView);
        set.start();
    }

    @Override
    public void onItemClear() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_off);
        set.setTarget(itemView);
        set.start();
    }
}
