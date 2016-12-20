package com.epicodus.beerguide.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.beerguide.Constants;
import com.epicodus.beerguide.models.Brewery;
import com.epicodus.beerguide.R;
import com.epicodus.beerguide.ui.BreweryDetailActivity;
import com.epicodus.beerguide.ui.BreweryDetailFragment;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 12/2/16.
 */
public class BreweryListAdapter extends RecyclerView.Adapter<BreweryListAdapter.BreweryViewHolder> {

    private ArrayList<Brewery> mBreweries = new ArrayList<>();
    private Context mContext;

    public BreweryListAdapter(Context context, ArrayList<Brewery> breweries) {
        mContext = context;
        mBreweries = breweries;
    }

    @Override
    public BreweryListAdapter.BreweryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brewery_list_item, parent, false);
        BreweryViewHolder viewHolder = new BreweryListAdapter.BreweryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BreweryListAdapter.BreweryViewHolder holder, int position) {
        holder.bindBrewery(mBreweries.get(position));
    }

    @Override
    public int getItemCount() {
        return mBreweries.size();
    }

    public class BreweryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.breweryNameTextView) TextView mBreweryNameTextView;
        @Bind(R.id.breweryImageView) ImageView mBreweryImageView;

        private Context mContext;
        private int mOrientation;

        public BreweryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            mOrientation = itemView.getResources().getConfiguration().orientation;

            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }

            itemView.setOnClickListener(this);
        }

        public void bindBrewery(Brewery brewery) {
            mBreweryNameTextView.setText(brewery.getName());

            if(brewery.getImageUrl().equals("N/A")) {

            } else {
                Picasso.with(mContext).load(brewery.getImageUrl()).into(mBreweryImageView);
            }
        }

//        @Override
//        public void onClick(View v) {
//            int itemPosition = getLayoutPosition();
//
//            Intent intent = new Intent(mContext, BreweryDetailActivity.class);
//            intent.putExtra("position", itemPosition + "");
//            intent.putExtra("breweries", Parcels.wrap(mBreweries));
//            mContext.startActivity(intent);
//        }

        private void createDetailFragment(int position) {
            BreweryDetailFragment detailFragment = BreweryDetailFragment.newInstance(mBreweries, position);
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.breweryDetailContainer, detailFragment);
            ft.commit();
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, BreweryDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_BREWERIES, Parcels.wrap(mBreweries));
                mContext.startActivity(intent);
            }
        }
    }
}
