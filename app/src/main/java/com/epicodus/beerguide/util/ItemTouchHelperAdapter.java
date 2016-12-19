package com.epicodus.beerguide.util;

/**
 * Created by Guest on 12/19/16.
 */
public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
