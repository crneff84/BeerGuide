package com.epicodus.beerguide.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.beerguide.Constants;
import com.epicodus.beerguide.R;
import com.epicodus.beerguide.adapters.FirebaseBreweryListAdapter;
import com.epicodus.beerguide.adapters.FirebaseBreweryViewHolder;
import com.epicodus.beerguide.models.Brewery;
import com.epicodus.beerguide.util.OnStartDragListener;
import com.epicodus.beerguide.util.SimpleItemTouchHelperCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedBreweryListFragment extends Fragment implements OnStartDragListener {
    @Bind(R.id.breweriesRecyclerView) RecyclerView mBreweriesRecyclerView;

    private FirebaseBreweryListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    public SavedBreweryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_brewery_list, container, false);
        ButterKnife.bind(this, view);
        setUpFirebaseAdapter();
        return view;
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_BREWERIES)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        mFirebaseAdapter = new FirebaseBreweryListAdapter(Brewery.class,
                R.layout.brewery_list_item_drag, FirebaseBreweryViewHolder.class,
                query, this, getActivity());

        mBreweriesRecyclerView.setHasFixedSize(true);
        mBreweriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBreweriesRecyclerView.setAdapter(mFirebaseAdapter);

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mFirebaseAdapter.notifyDataSetChanged();
            }
        });

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mBreweriesRecyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    //method is now public
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
