package com.circle.drawable.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.circle.drawable.R;

public class PropertyAnim2Activity extends Activity implements
        OnCheckedChangeListener {
    private ViewGroup viewGroup;
    private GridLayout mGridLayout;
    private int mVal;
    private LayoutTransition mTransition;

    private CheckBox mAppear, mChangeAppear, mDisAppear, mChangeDisAppear;
    private ImageView mIvBall;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim2);
        viewGroup = findViewById(R.id.id_container);
        mIvBall = findViewById(R.id.iv_ball);

        mAppear = findViewById(R.id.id_appear);
        mChangeAppear = findViewById(R.id.id_change_appear);
        mDisAppear = findViewById(R.id.id_disappear);
        mChangeDisAppear = findViewById(R.id.id_change_disappear);

        mAppear.setOnCheckedChangeListener(this);
        mChangeAppear.setOnCheckedChangeListener(this);
        mDisAppear.setOnCheckedChangeListener(this);
        mChangeDisAppear.setOnCheckedChangeListener(this);

        // 创建一个GridLayout
        mGridLayout = new GridLayout(this);
        // 设置每列5个按钮
        mGridLayout.setColumnCount(5);
        // 添加到布局中
        viewGroup.addView(mGridLayout);
        //默认动画全部开启
        mTransition = new LayoutTransition();
        mGridLayout.setLayoutTransition(mTransition);

    }

    /**
     * 添加按钮
     *
     * @param view
     */
    public void addBtn(View view) {
        final Button button = new Button(this);
        button.setText((++mVal) + "");
        mGridLayout.addView(button, Math.min(1, mGridLayout.getChildCount()));
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mGridLayout.removeView(button);
            }
        });
    }

    @SuppressLint("ObjectAnimatorBinding")
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mTransition = new LayoutTransition();
        // TODO: LayoutTransition.APPEARING 当一个View在ViewGroup中出现时，对此View设置的动画
        // mTransition.setAnimator(
        //         LayoutTransition.APPEARING,
        //         (mAppear.isChecked() ? mTransition
        //                 .getAnimator(LayoutTransition.APPEARING) : null));
        // TODO: 还能自定义该属性动画!!
        mTransition.setAnimator(LayoutTransition.APPEARING,
                (mAppear.isChecked() ? ObjectAnimator.ofFloat(this, "scaleX", 0, 1).setDuration(800) : null));

        // TODO: LayoutTransition.CHANGE_APPEARING 当一个View在ViewGroup中出现时，对此View对其他View位置造成影响，对其他View设置的动画
        mTransition.setAnimator(LayoutTransition.CHANGE_APPEARING,
                (mChangeAppear.isChecked() ? mTransition.getAnimator(LayoutTransition.CHANGE_APPEARING) : null));

        // TODO: LayoutTransition.DISAPPEARING  当一个View在ViewGroup中消失时，对此View设置的动画
        // mTransition.setAnimator(
        //         LayoutTransition.DISAPPEARING,
        //         (mDisAppear.isChecked() ? mTransition
        //                 .getAnimator(LayoutTransition.DISAPPEARING) : null));

        mTransition.setAnimator(LayoutTransition.DISAPPEARING,
                (mDisAppear.isChecked() ? ObjectAnimator.ofFloat(this, "scaleX", 1, 0).setDuration(800) : null));

        // TODO: LayoutTransition.CHANGE_DISAPPEARING 当一个View在ViewGroup中消失时，对此View对其他View位置造成影响，对其他View设置的动画
        mTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,
                (mChangeDisAppear.isChecked() ? mTransition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING) : null));
        mGridLayout.setLayoutTransition(mTransition);
    }

    public void onAnimXml(View view) {
        // 加载动画
        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.scalexy);
        mIvBall.setPivotX(0);
        mIvBall.setPivotY(0);
        // 显示的调用invalidate
        // mIvBall.invalidate();
        anim.setTarget(mIvBall);
        anim.start();
    }
}