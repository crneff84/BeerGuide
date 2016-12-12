package com.epicodus.beerguide.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.beerguide.Constants;
import com.epicodus.beerguide.R;
import com.epicodus.beerguide.models.Brewery;
import com.epicodus.beerguide.ui.BreweryDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseBreweryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseBreweryViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindBrewery(Brewery brewery) {
        ImageView restaurantImageView = (ImageView) mView.findViewById(R.id.breweryImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.breweryNameTextView);

//        Picasso.with(mContext)
//                .load(restaurant.getImageUrl())
//                .resize(MAX_WIDTH, MAX_HEIGHT)
//                .centerCrop()
//                .into(restaurantImageView);

        nameTextView.setText(brewery.getName());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Brewery> breweries = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BREWERIES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    breweries.add(snapshot.getValue(Brewery.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, BreweryDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("breweries", Parcels.wrap(breweries));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
