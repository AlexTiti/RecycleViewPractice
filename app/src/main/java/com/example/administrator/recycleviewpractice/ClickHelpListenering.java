package com.example.administrator.recycleviewpractice;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * <pre>
 *
 *   author   :   Alex
 *   e_mail   :   18238818283@sina.cn
 *   time     :   2017/09/30
 *   desc     :
 *   version  :   V 1.0.5
 */

public abstract class ClickHelpListenering  implements RecyclerView.OnItemTouchListener {

    private RecyclerView recyclerView;
    private GestureDetectorCompat mGestureDetectorCompat;
    private Context mContext;

    public ClickHelpListenering(RecyclerView recyclerView,Context mContext) {
        this.recyclerView = recyclerView;
        this.mContext = mContext;
        mGestureDetectorCompat = new GestureDetectorCompat(mContext, new ItemTouchGestureListener());
    }

    public abstract void onItemClick(RecyclerViewAdapter.ViewHolder viewHolder);
    public abstract void onItemLongClick(RecyclerViewAdapter.ViewHolder viewHolder);


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        mGestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public class ItemTouchGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View childView = recyclerView.findChildViewUnder(e.getX(),e.getY());
            if (childView != null){
                RecyclerViewAdapter.ViewHolder viewHolder = (RecyclerViewAdapter.ViewHolder) recyclerView.getChildViewHolder(childView);
                onItemClick(viewHolder);
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            View childView = recyclerView.findChildViewUnder(e.getX(),e.getY());
            if (childView != null){
                RecyclerViewAdapter.ViewHolder viewHolder = (RecyclerViewAdapter.ViewHolder) recyclerView.getChildViewHolder(childView);
                onItemLongClick(viewHolder);
            }
        }
    }


}


