package com.circle.drawable.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GalleryRecyclerView extends RecyclerView {

    /**
     * 记录当前第一个View
     */
    private View mCurrentView;

    private OnItemScrollChangeListener mItemScrollChangeListener;

    public void setOnItemScrollChangeListener(
            OnItemScrollChangeListener mItemScrollChangeListener) {
        this.mItemScrollChangeListener = mItemScrollChangeListener;
    }

    public interface OnItemScrollChangeListener {
        void onChange(View view, int position);
    }

    public GalleryRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        // this.setOnScrollListener(this);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        mCurrentView = getChildAt(0);
        // TODO: 解决第一次进来未滚动时回调position,设置主图片
        if (mItemScrollChangeListener != null) {
            mItemScrollChangeListener.onChange(mCurrentView,getChildPosition(mCurrentView));
        }
    }


    @Override
    public void onScrollStateChanged(int arg0) {
    }

    /**
     * 滚动时，判断当前第一个View是否发生变化，发生才回调
     */
    @Override
    public void onScrolled(int arg0, int arg1) {
        Log.i("TAG", "onScrolled: arg0="+arg0+" , arg1="+arg1);
        View newView = getChildAt(0);
        // TODO: 优化
        //  1. 只有在图片变化的时候才回调变化后的索引,为的是外面设置主图
        //  2. recyclerView内部除了手指滑动的处理,还处理了惯性"一撇"的处理,都在这个回调里
        if (mItemScrollChangeListener != null) {
            if (newView != null && newView != mCurrentView) {
                mCurrentView = newView;
                mItemScrollChangeListener.onChange(mCurrentView,getChildPosition(mCurrentView));
            }
        }
    }

}