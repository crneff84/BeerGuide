package com.epicodus.beerguide.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.beerguide.Constants;
import com.epicodus.beerguide.R;
import com.epicodus.beerguide.models.Brewery;
import com.epicodus.beerguide.ui.BreweryDetailActivity;
import com.epicodus.beerguide.ui.BreweryDetailFragment;
import com.epicodus.beerguide.util.ItemTouchHelperAdapter;
import com.epicodus.beerguide.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Guest on 12/19/16.
 */
public class FirebaseBreweryListAdapter extends FirebaseRecyclerAdapter<Brewery, FirebaseBreweryViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Brewery> mBreweries = new ArrayList<>();
    private int mOrientation;

    public FirebaseBreweryListAdapter(Class<Brewery> modelClass, int modelLayout,
                                         Class<FirebaseBreweryViewHolder> viewHolderClass,
                                         Query ref, OnStartDragListener onStartDragListener, Context context) {

        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mBreweries.add(dataSnapshot.getValue(Brewery.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void populateViewHolder(final FirebaseBreweryViewHolder viewHolder, Brewery model, int position) {
        viewHolder.bindBrewery(model);

        mOrientation = viewHolder.itemView.getResources().getConfiguration().orientation;
        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            createDetailFragment(0);
        }

        viewHolder.mBreweryImageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }

        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = viewHolder.getAdapterPosition();
                if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    createDetailFragment(itemPosition);
                } else {
                    Intent intent = new Intent(mContext, BreweryDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                    intent.putExtra(Constants.EXTRA_KEY_BREWERIES, Parcels.wrap(mBreweries));
                    mContext.startActivity(intent);
                }
            }
        });
    }

    private void createDetailFragment(int position) {
        BreweryDetailFragment detailFragment = BreweryDetailFragment.newInstance(mBreweries, position);
        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.breweryDetailContainer, detailFragment);
        ft.commit();
        Log.d("hey", "in N/A area");
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mBreweries, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mBreweries.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexInFirebase() {
        for (Brewery brewery : mBreweries) {
            int index = mBreweries.indexOf(brewery);
            DatabaseReference ref = getRef(index);
            brewery.setIndex(Integer.toString(index));
            ref.setValue(brewery);
        }
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}
