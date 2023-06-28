package com.circle.drawable.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.circle.drawable.R;
import com.circle.drawable.callback.DelButtonClickListener;

public class QQListView extends ListView {

    private static final String TAG = "QQlistView";

    // private static final int VELOCITY_SANP = 200;
    // private VelocityTracker mVelocityTracker;
    /**
     * 用户滑动的最小距离
     */
    private int touchSlop;

    /**
     * 是否响应滑动
     */
    private boolean isSliding;

    /**
     * 手指按下时的x坐标
     */
    private int xDown;
    /**
     * 手指按下时的y坐标
     */
    private int yDown;
    /**
     * 手指移动时的x坐标
     */
    private int xMove;
    /**
     * 手指移动时的y坐标
     */
    private int yMove;

    private LayoutInflater mInflater;

    private PopupWindow mPopupWindow;
    private int mPopupWindowHeight;
    private int mPopupWindowWidth;

    private Button mDelBtn;
    /**
     * 为删除按钮提供一个回调接口
     */
    private DelButtonClickListener mListener;

    /**
     * 当前手指触摸的View
     */
    private View mCurrentView;

    /**
     * 当前手指触摸的位置
     */
    private int mCurrentViewPos;

    /**
     * 必要的一些初始化
     *
     * @param context
     * @param attrs
     */
    public QQListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        View view = mInflater.inflate(R.layout.delete_btn, null);
        mDelBtn = (Button) view.findViewById(R.id.id_item_btn);
        mPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        /**
         * 先调用下measure,否则拿不到宽和高
         */
        mPopupWindow.getContentView().measure(0, 0);
        mPopupWindowHeight = mPopupWindow.getContentView().getMeasuredHeight();
        mPopupWindowWidth = mPopupWindow.getContentView().getMeasuredWidth();
        Log.i(TAG, "mPopupWindowHeight: " + mPopupWindowHeight + "  , mPopupWindowWidth:" + mPopupWindowWidth);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (action) {

            case MotionEvent.ACTION_DOWN:
                xDown = x;
                yDown = y;
                Log.w(TAG, "getX: " + x + " , getY:" + y);
                Log.w(TAG, "getRawX: " + ev.getRawX() + " , getRawY:" + ev.getRawY());
                /**
                 * 如果当前popupWindow显示，则直接隐藏，然后屏蔽ListView的touch事件的下传
                 */
                if (mPopupWindow.isShowing()) {
                    dismissPopWindow();
                    return false;
                }
                // 获得当前手指按下时的item的位置
                mCurrentViewPos = pointToPosition(xDown, yDown);
                Log.e(TAG, "mCurrentViewPos: " + mCurrentViewPos + ", getFirstVisiblePosition:" + getFirstVisiblePosition());
                // 获得当前手指按下时的item
                View view = getChildAt(mCurrentViewPos - getFirstVisiblePosition());
                mCurrentView = view;
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = x;
                yMove = y;
                int dx = xMove - xDown;
                int dy = yMove - yDown;
                /**
                 * 判断是否是从右到左的滑动
                 */
                if (xMove < xDown && Math.abs(dx) > touchSlop && Math.abs(dy) < touchSlop) {
                    Log.i(TAG, "touchslop = " + touchSlop + " , dx = " + dx + " , dy = " + dy);
                    isSliding = true;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        Log.i(TAG, "onTouchEvent: ====>  come on");
        /**
         * 如果是从右到左的滑动才相应
         */
        if (isSliding) {
            switch (action) {
                case MotionEvent.ACTION_MOVE:

                    int[] location = new int[2];
                    // 获得当前item的位置x与y
                    mCurrentView.getLocationOnScreen(location);
                    // 设置popupWindow的动画
                    mPopupWindow.setAnimationStyle(R.style.popwindow_delete_btn_anim_style);
                    mPopupWindow.update();
                    // TODO: 老师的写法简便，xml可以直接写好marginRight，同时x如果超出parent的边界的话也能“自适应"为靠边摆放
                    mPopupWindow.showAtLocation(mCurrentView, Gravity.LEFT | Gravity.TOP,
                            location[0] + mCurrentView.getWidth(), location[1] + mCurrentView.getHeight() / 2
                                    - mPopupWindowHeight / 2);
                    // TODO: 自我理解的写法(xml中不写死距离右边的边距)
                    // mPopupWindow.showAtLocation(mCurrentView, Gravity.LEFT | Gravity.TOP,
                    //        location[0] + mCurrentView.getWidth() - mPopupWindowWidth - dpToPx(getContext(),15), location[1] + mCurrentView.getHeight() / 2
                    //                - mPopupWindowHeight / 2);
                    // 设置删除按钮的回调
                    mDelBtn.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mListener != null) {
                                mListener.clickHappend(mCurrentViewPos);
                                mPopupWindow.dismiss();
                            }
                        }
                    });
                    Log.e(TAG, "location[0]=" + location[0] + " , location[1]=" + location[1] + "  ,  mCurrentView.getWidth()=" + mCurrentView.getWidth());
                    Log.e(TAG, "mPopupWindow.getHeight()=" + mPopupWindowHeight);

                    break;
                case MotionEvent.ACTION_UP:
                    isSliding = false;

            }
            // 相应滑动期间屏幕itemClick事件，避免发生冲突
            return true;
        }

        return super.onTouchEvent(ev);
    }

    /**
     * 隐藏popupWindow
     */
    private void dismissPopWindow() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    public void setDelButtonClickListener(DelButtonClickListener listener) {
        mListener = listener;
    }

    private int dpToPx(Context context, float dpValue) {
        // 获取屏幕密度
        final float scale = context.getResources().getDisplayMetrics().density;
        // 结果+0.5是为了int取整时更接近
        return (int) (dpValue * scale + 0.5f);
    }


}