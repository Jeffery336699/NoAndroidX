package com.circle.drawable.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * 支持上下反弹效果的ScrollView
 *
 * @author zhy
 */
public class BounceScrollView extends ScrollView {

    private static final String TAG = "BounceScrollView";
    private boolean isCalled;

    private Callback mCallback;

    /**
     * 包含的View
     */
    private View mView;
    /**
     * 存储正常时的位置
     */
    private Rect mRect = new Rect();

    /**
     * y坐标
     */
    private int y;

    private boolean isFirst = true;

    public BounceScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /***
     * 根据 XML 生成视图工作完成.该函数在生成视图的最后调用，在所有子视图添加完之后. 即使子类覆盖了 onFinishInflate
     * 方法，也应该调用父类的方法，使该方法得以执行.
     */
    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0)
            mView = getChildAt(0);
        super.onFinishInflate();
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_MOVE){
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onTouchEvent:   mView="+mView);
        if (mView != null) {
            commonOnTouch(ev);
        }

        return super.onTouchEvent(ev);
    }

    private void commonOnTouch(MotionEvent ev) {
        int action = ev.getAction();
        int cy = (int) ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            /**
             * 跟随手指移动
             */
            case MotionEvent.ACTION_MOVE:
                int dy = cy - y;
                if (isFirst) {
                    dy = 0;
                    isFirst = false;
                }
                y = cy;
                if (isNeedMove()) {
                    if (mRect.isEmpty()) {
                        /**
                         * 记录移动前的位置
                         */
                        mRect.set(mView.getLeft(), mView.getTop(),
                                mView.getRight(), mView.getBottom());
                    }
                    // Log.e(TAG, "commonOnTouch:   mView.getTop()="+mView.getTop()+"  , mView.getBottom()="+mView.getBottom());
                    // TODO: 根据measureHeight后,default getTop在0位置, getBottom在measureHeight位置;无关屏幕的高度,就相当于屏幕足够大
                    mView.layout(mView.getLeft(), mView.getTop() + 2 * dy / 3,
                            mView.getRight(), mView.getBottom() + 2 * dy / 3);

                    if (shouldCallBack(dy)) {
                        if (mCallback != null) {
                            if (!isCalled) {
                                isCalled = true;
                                resetPosition();
                                mCallback.callback();
                            }
                        }
                    }
                }
                break;
            /**
             * 反弹回去
             */
            case MotionEvent.ACTION_UP:
                if (!mRect.isEmpty()) {
                    resetPosition();
                }
                break;
        }
    }

    /**
     * 当从上往下，移动距离达到一半时，回调接口
     *
     * @return
     */
    private boolean shouldCallBack(int dy) {
        Log.i(TAG, "shouldCallBack:    mView.getTop()="+mView.getTop() +", getHeight() / 2="+getHeight() / 2);
        if (dy > 0 && mView.getTop() > getHeight() / 2)
            return true;
        return false;
    }

    private void resetPosition() {
        Log.e(TAG, "resetPosition:   mRect.top="+mRect.top +"  , mView.getTop()="+mView.getTop());
        Animation animation = new TranslateAnimation(0, 0, mView.getTop(),
                mRect.top);
        animation.setDuration(200);
        animation.setFillAfter(true);
        // animation.setAnimationListener(new Animation.AnimationListener() {
        //     @Override
        //     public void onAnimationStart(Animation animation) {
        //         Log.w(TAG, "onAnimationStart:   mView.getTop()="+mView.getTop());
        //     }
        //
        //     @Override
        //     public void onAnimationEnd(Animation animation) {
        //         Log.w(TAG, "onAnimationEnd:   mView.getTop()="+mView.getTop());
        //     }
        //
        //     @Override
        //     public void onAnimationRepeat(Animation animation) {
        //
        //     }
        // });
        mView.startAnimation(animation);
        // TODO: 上面补间动画有坑的,实际的位置并没有变化,只是给你个动画的假象,所以这里得真正的改变mView的位置!妙哉~
        mView.layout(mRect.left, mRect.top, mRect.right, mRect.bottom);
        // TODO: 参数重置到初始状态
        mRect.setEmpty();
        isFirst = true;
        isCalled = false;
    }

    /***
     * 是否需要移动布局 inner.getMeasuredHeight():获取的是控件的总高度
     *
     * getHeight()：获取的是屏幕的高度
     *
     * @return
     */
    public boolean isNeedMove() {
        // Log.w(TAG, "mView.getMeasuredHeight(): "+mView.getMeasuredHeight()+" , getHeight():"+getHeight());
        // TODO: 虽然ListView还有很多没有显示到屏幕上,但是那些都会被计算在measureHeight中
        int offset = mView.getMeasuredHeight() - getHeight();
        // TODO: getScrollY则是一种容器中子控件的滚动表现,往上超出的为正值;
        int scrollY = getScrollY();
        Log.i(TAG, "offset: "+offset+ " , scrollY:"+scrollY);
        // TODO: 0是顶部，后面那个是底部
        if (scrollY == 0 || scrollY == offset) {
            return true;
        }
        return false;
    }

    public void setCallBack(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {
        void callback();
    }

}