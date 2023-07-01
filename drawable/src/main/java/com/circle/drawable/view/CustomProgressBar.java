package com.circle.drawable.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.circle.drawable.R;

public class CustomProgressBar extends View {
    /**
     * 第一圈的颜色
     */
    private int mFirstColor;
    /**
     * 第二圈的颜色
     */
    private int mSecondColor;
    /**
     * 圈的宽度
     */
    private int mCircleWidth;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 当前进度
     */
    private int mProgress;

    /**
     * 速度
     */
    private int mSpeed;

    /**
     * 是否应该开始下一个
     */
    private boolean isNext = false;

    public CustomProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomProgressBar(Context context) {
        this(context, null);
    }

    /**
     * 必要的初始化，获得一些自定义的值
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    @SuppressLint("NonConstantResourceId")
    public CustomProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgressBar, defStyle, 0);

        // TODO: 这种才能在xml没有设置自定义属性时,能够以默认值补上
        mFirstColor = a.getColor(R.styleable.CustomProgressBar_firstColor, Color.GREEN);
        mSecondColor = a.getColor(R.styleable.CustomProgressBar_secondColor, Color.RED);
        mCircleWidth = a.getDimensionPixelSize(R.styleable.CustomProgressBar_circleWidth, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
        mSpeed = a.getInt(R.styleable.CustomProgressBar_speed, 20);// 默认20

        /*int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomProgressBar_firstColor:
                    mFirstColor = a.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.CustomProgressBar_secondColor:
                    mSecondColor = a.getColor(attr, Color.RED);
                    break;
                case R.styleable.CustomProgressBar_circleWidth:
                    mCircleWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomProgressBar_speed:
                    mSpeed = a.getInt(attr, 20);// 默认20
                    break;
            }
        }*/

        a.recycle();
        mPaint = new Paint();
        // 绘图线程
        new Thread() {
            public void run() {
                while (true) {
                    mProgress++;
                    if (mProgress == 360) {
                        mProgress = 0;
                        if (!isNext)
                            isNext = true;
                        else
                            isNext = false;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(1000 / mSpeed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {

        int centre = getWidth() / 2; // 获取圆心的x坐标
        int radius = centre - mCircleWidth / 2;// 半径
        mPaint.setStrokeWidth(mCircleWidth); // 设置圆环的宽度
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心,TODO 只是描边
        // 用于定义的圆弧的形状和大小的界限
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);
        if (!isNext) {// 第一颜色的圈完整，第二颜色跑
            mPaint.setColor(mFirstColor); // 设置底部圆环的颜色
            canvas.drawCircle(centre, centre, radius, mPaint); // 画出底部圆环
            mPaint.setColor(mSecondColor); // 设置圆弧的颜色
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        } else {
            mPaint.setColor(mSecondColor); // 设置底部圆环的颜色
            canvas.drawCircle(centre, centre, radius, mPaint); // 画出底部圆环
            mPaint.setColor(mFirstColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        }
        /**
         * 直接绘制圆弧
         * mPaint.setColor(mFirstColor);
         * canvas.drawArc(oval, -90, 180, false, mPaint); // 根据进度画圆弧
         */

    }
}
