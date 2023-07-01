package com.circle.drawable.view;

import android.annotation.SuppressLint;
import android.arch.core.executor.ArchTaskExecutor;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;

import com.circle.drawable.R;

public class CustomCircleImageView extends View {

    private static final String TAG = "CustomCircleImageView";
    /**
     * TYPE_CIRCLE / TYPE_ROUND
     */
    private int type;
    private static final int TYPE_CIRCLE = 0;
    private static final int TYPE_ROUND = 1;

    /**
     * 图片
     */
    private Bitmap mSrc;

    /**
     * 圆角的大小
     */
    private int mRadius;

    /**
     * 控件的宽度
     */
    private int mWidth;
    /**
     * 控件的高度
     */
    private int mHeight;

    public CustomCircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCircleImageView(Context context) {
        this(context, null);
    }

    /**
     * 初始化一些自定义的参数
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    @SuppressLint("RestrictedApi")
    public CustomCircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomCircleImageView, defStyle, 0);

        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomCircleImageView_src:
                    mSrc = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                    break;
                case R.styleable.CustomCircleImageView_type:
                    type = a.getInt(attr, 0);// 默认为Circle
                    break;
                case R.styleable.CustomCircleImageView_borderRadius:
                    mRadius = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f,
                            getResources().getDisplayMetrics()));// 默认为10DP
                    break;
            }
        }
        a.recycle();

        Log.i(TAG, "构造: mSrc.getWidth()=" + mSrc.getWidth() + "    , mSrc.getHeight()=" + mSrc.getHeight());
    }

    /**
     * 计算控件的高度和宽度
     * TODO:如果是一个矩形图片转圆形,采用的是wrap_content的话,控件的宽高取决于图片的大小,
     *  操作最终是把矩形-->正方形-->圆形
     *  在绘制的时候控件最终设置的是圆形的了,所以控件的宽高也就变成一样的了
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "onMeasure: mSrc.getWidth()=" + mSrc.getWidth() + "    , mSrc.getHeight()=" + mSrc.getHeight());
        /**
         * 设置宽度
         */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            mWidth = specSize;
        } else {
            // 由图片决定的宽
            int desireByImg = getPaddingLeft() + getPaddingRight()
                    + mSrc.getWidth();
            if (specMode == MeasureSpec.AT_MOST)// wrap_content
            {
                // TODO: 跟父类能给到的最大值specSize,取个最小的值
                mWidth = Math.min(desireByImg, specSize);
            } else
                mWidth = desireByImg;
        }

        /***
         * 设置高度
         */
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            mHeight = specSize;
        } else {
            int desire = getPaddingTop() + getPaddingBottom()
                    + mSrc.getHeight();

            if (specMode == MeasureSpec.AT_MOST)// wrap_content
            {
                mHeight = Math.min(desire, specSize);
            } else {
                Log.i(TAG, "specMode == MeasureSpec.UNSPECIFIED:" + (specMode == MeasureSpec.UNSPECIFIED));
                mHeight = desire;
            }
        }
        Log.i(TAG, "onMeasure: mWidth=" + mWidth + "    , mHeight=" + mHeight);
        // setMeasuredDimension(mWidth, mHeight);
        // TODO: 如果是设置为圆形的话,可以直接在这里就把控件的宽高改为正方形的了
        if (type == TYPE_CIRCLE) {
            setMeasuredDimension(Math.min(mWidth,mHeight), Math.min(mWidth,mHeight));
        }else {
            setMeasuredDimension(mWidth, mHeight);
        }
    }

    /**
     * 绘制
     */
    @Override
    protected void onDraw(Canvas canvas) {
        switch (type) {
            // 如果是TYPE_CIRCLE绘制圆形
            case TYPE_CIRCLE:
                int min = Math.min(mWidth, mHeight);
                /**
                 * 长度如果不一致，按小的值进行压缩
                 */
                mSrc = Bitmap.createScaledBitmap(mSrc, min, min, false);
                canvas.drawBitmap(createCircleImage(mSrc, min), 0, 0, null);
                break;
            case TYPE_ROUND:
                canvas.drawBitmap(createRoundConerImage(mSrc), 0, 0, null);
                break;
        }
    }

    /**
     * 根据原图和变长绘制圆形图片
     *
     * @param source
     * @param min
     * @return
     */
    private Bitmap createCircleImage(Bitmap source, int min) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);
        /**
         * 首先绘制圆形
         */
        canvas.drawCircle(min / 2.f, min / 2.f, min / 2.f, paint);
        /**
         * 使用SRC_IN，参考上面的说明
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        /**
         * 绘制图片
         */
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

    /**
     * 根据原图添加圆角
     *
     * @param source
     * @return
     */
    private Bitmap createRoundConerImage(Bitmap source) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        RectF rect = new RectF(0, 0, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rect, mRadius, mRadius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

}
