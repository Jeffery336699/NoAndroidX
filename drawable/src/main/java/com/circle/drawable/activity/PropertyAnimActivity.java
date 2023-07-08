package com.circle.drawable.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.circle.drawable.R;
import com.circle.drawable.utils.Globals;

public class PropertyAnimActivity extends AppCompatActivity {

    private static final String TAG = "PropertyAnimActivity";
    private ImageView mBlueBall;
    private int mScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim);
        mBlueBall = findViewById(R.id.id_ball);
        mScreenHeight = Globals.getScreenHeight();
    }

    public void verticalRun(View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, mScreenHeight
                - mBlueBall.getHeight());
        animator.setTarget(mBlueBall);
        animator.setDuration(1000).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mBlueBall.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mBlueBall.setTranslationY(0);
            }
        });
    }

    /**
     * 抛物线
     *
     * @param view
     */
    public void paowuxian(View view) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
            // fraction = t / duration
            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue) {
                Log.e(TAG, fraction * 3 + "");// fraction自然是[0,1]变化
                // x方向200px/s ，则y方向0.5 * 10 * t
                PointF point = new PointF();
                point.x = 200 * fraction * 3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                mBlueBall.setX(point.x);
                mBlueBall.setY(point.y);
            }
        });
        AnimatorListenerAdapter adapter = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mBlueBall.setX(0);
                mBlueBall.setY(0);
            }
        };
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                System.out.println("onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                System.out.println("onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                System.out.println("onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                System.out.println("onAnimationRepeat");
            }
        });

        mBlueBall.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO: 演示cancel动画立即停止，停在当前的位置 [回调:onAnimationCancel,onAnimationEnd]
                // valueAnimator.cancel();
                // TODO: end动画直接到最终状态 [回调:onAnimationEnd]
                // valueAnimator.end();
            }
        }, 1800);
    }

    public void viewAnim(View view) {
        // need API12
        mBlueBall.animate()//
                .alpha(0)//
                .y(mScreenHeight / 2.0f).setDuration(1000)
                // need API 12
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "START");
                    }
                    // need API 16
                }).withEndAction(new Runnable() {

                    @Override
                    public void run() {
                        Log.e(TAG, "END");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mBlueBall.setY(0);
                                mBlueBall.setAlpha(1.0f);
                            }
                        });
                    }
                }).start();
    }

    @SuppressLint("ObjectAnimatorBinding")
    public void propertyValuesHolder(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0f, 1f);
         PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", 0,
                mScreenHeight / 2f, 0);
        ObjectAnimator.ofPropertyValuesHolder(mBlueBall, pvhX, pvhY).setDuration(1000).start();
    }
}