package com.example.administrator.recycleviewpractice;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * <pre>
 *
 *   author   :   Alex
 *   e_mail   :   18238818283@sina.cn
 *   time     :   2017/09/30
 *   desc     :
 *   version  :   V 1.0.5
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    private Context mContext;
    private int mOrientation;
    private Drawable drawable;
    public static final int VERTICAL = LinearLayoutManager.VERTICAL;
    public static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    public MyItemDecoration(Context mContext, int mOrientation) {
        final TypedArray array = mContext.obtainStyledAttributes(ATTRS);
        drawable = array.getDrawable(0);
        array.recycle();
        setOrientation(mOrientation);
    }
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL){
            drawVertical(c,parent);
        }else{
            drawHorizontal(c,parent);
        }
    }
    private void drawHorizontal(Canvas c, RecyclerView parent) {
       final  int top = parent.getPaddingTop();
       final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i=0;i<childCount;i++){
            final  View  view = parent.getChildAt(i);
            RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) view.getLayoutParams();
            final  int left = view.getLeft()+param.leftMargin;
            final int right = left + drawable.getIntrinsicWidth();
            drawable.setBounds(left,top,right,bottom);
            drawable.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right =  parent.getWidth() - parent.getPaddingRight();
        final int child_count = parent.getChildCount();
        for (int i= 0;i<child_count;i++){
            final  View view = parent.getChildAt(i);
            RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) view.getLayoutParams();
            final int top  = view.getBottom()+param.bottomMargin;
            final int bottom = top + drawable.getIntrinsicHeight();
            drawable.setBounds(left,top,right,bottom);
            drawable.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
       if (mOrientation == VERTICAL){
           outRect.set(0,0,0,drawable.getIntrinsicHeight());
       }else {
           outRect.set(0,0,drawable.getIntrinsicWidth(),0);
       }
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL){
            throw new IllegalArgumentException("invalid orientation");
        }
        this.mOrientation = orientation;
    }
}
