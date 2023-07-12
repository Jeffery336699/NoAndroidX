package com.circle.drawable.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * ViewGroup的事件分发 , very nice!
 * 参考鸿洋大神: https://blog.csdn.net/lmj623565791/article/details/39102591?spm=1001.2014.3001.5502
 */
public class MyLinearLayout extends LinearLayout {
    private static final String TAG = MyLinearLayout.class.getSimpleName();

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        /**
         * TODO: 就算拦截下来自己处理,那你也得能处理 (eg clickable,setOnclickListener,setLongClickListener)
         *  1.默认情况一些组件(eg TextView)是不消耗事件的,一般具备clickable属性的的View具备消费事件的能力
         *  2.可以手动设置如上一些事件监听,或者属性,使其可以消费事件的能力
         *  3.后续的ACTION_MOVE,ACTION_UP就会都交给它处理 (拦截下来,默认交给ViewGroup处理了,它不能消费该事件就往上层抛)
         */
        // setClickable(true); // TODO 使该View具备消费该事件的能力; 或者干脆点直接onTouchEvent方法返回true
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "dispatchTouchEvent ACTION_UP");
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent ACTION_UP");
                break;

            default:
                break;
        }
        boolean onTouchEvent = super.onTouchEvent(event);
        Log.e(TAG, "onTouchEvent 结果:"+onTouchEvent);
        return onTouchEvent;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onInterceptTouchEvent ACTION_DOWN");
                // return true; // TODO 如果在这里就拦截将接受不到后续的所有事件,该ViewGroup及childView,灾难级别的(MOVE,UP)
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onInterceptTouchEvent ACTION_MOVE");
                // return true; // TODO 在这里拦截后续childView可以通过requestDisallowInterceptTouchEvent,还能接收到事件
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onInterceptTouchEvent ACTION_UP");
                break;

            default:
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        Log.e(TAG, "requestDisallowInterceptTouchEvent ");
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

}
