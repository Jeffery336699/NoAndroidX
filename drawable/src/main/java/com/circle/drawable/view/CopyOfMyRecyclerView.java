package com.circle.drawable.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class CopyOfMyRecyclerView extends RecyclerView {

    public CopyOfMyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private View mCurrentView;

    /**
     * 滚动时回调的接口
     */
    private OnItemScrollChangeListener mItemScrollChangeListener;

    public void setOnItemScrollChangeListener(
            OnItemScrollChangeListener mItemScrollChangeListener) {
        this.mItemScrollChangeListener = mItemScrollChangeListener;
    }

    public interface OnItemScrollChangeListener {
        void onChange(View view, int position);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.i("TAG", "onLayout: " + l + "," + t + "," + r + "," + b);
        mCurrentView = getChildAt(0);
        // TODO: 这里是为了第一次进来,没有滑动的时候,通过回调去做主图的显示
        if (mItemScrollChangeListener != null) {
            mItemScrollChangeListener.onChange(mCurrentView,getChildPosition(mCurrentView));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            // TODO: recycle容器中也是存放的是可见数量的view,第一个一定是可见的第一个view
            mCurrentView = getChildAt(0);
            Log.e("TAG", getChildPosition(getChildAt(0)) + "");
            if (mItemScrollChangeListener != null) {
                // TODO: 根据屏幕可见的view,通过getChildPosition(view)可以获取该view对应到的position
                mItemScrollChangeListener.onChange(mCurrentView, getChildPosition(mCurrentView));
            }
        }
        return super.onTouchEvent(e);
    }

}