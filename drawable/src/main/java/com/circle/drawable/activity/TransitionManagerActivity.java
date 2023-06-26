package com.circle.drawable.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.circle.drawable.R;

public class TransitionManagerActivity extends AppCompatActivity {

    private ImageView img01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_manager);
        initView();
        initAction();
    }

    private void initAction() {
        img01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 简单一行直接就有动画效果了，既高效又解耦
                TransitionManager.beginDelayedTransition((ViewGroup) img01.getParent());
                FrameLayout.LayoutParams lp= (FrameLayout.LayoutParams) img01.getLayoutParams();
                lp.gravity = Gravity.RIGHT;
                img01.requestLayout();
            }
        });
    }

    private void initView() {
        img01 = findViewById(R.id.img_01);
    }
}