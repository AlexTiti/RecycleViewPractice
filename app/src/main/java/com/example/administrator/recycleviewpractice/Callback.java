package com.example.administrator.recycleviewpractice;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.Collections;

/**
 * <pre>
 *
 *   author   :   Alex
 *   e_mail   :   18238818283@sina.cn
 *   time     :   2017/09/30
 *   desc     :
 *   version  :   V 1.0.5
 */

public class Callback extends ItemTouchHelper.Callback {

    RecyclerViewAdapter adapter ;

    public Callback(RecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

//        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
//            Log.e("=========","LinearLayoutManager");
//            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
//            int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
//            return makeMovementFlags(dragFlags,swipeFlags);
//        }else if (recyclerView.getLayoutManager() instanceof GridLayoutManager){
            Log.e("=========","GridLayoutManager");
               int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                       ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
               int swipeFlags = 0;
               return makeMovementFlags(dragFlags,swipeFlags);
//        }
//        return 0;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
        if (toPosition == 0)
            return false;
        if (fromPosition<toPosition){
            for (int i = fromPosition ;i<toPosition;i++){
                Collections.swap(adapter.getList(),i,i+1);
            }
        }else{
            for (int i = toPosition ;i>fromPosition;i--){
                Collections.swap(adapter.getList(),i,i-1);
            }
        }
        adapter.notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        adapter.notifyItemRemoved(position);
        adapter.getList().remove(position);

    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE)
            viewHolder.itemView.setBackgroundColor(Color.GRAY);
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(Color.WHITE);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }
}
