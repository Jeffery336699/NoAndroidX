package com.circle.drawable.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.circle.drawable.R;

public class ViewGroupTouchEventActivity extends AppCompatActivity {

    private Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group_touch_event);
        mBt = findViewById(R.id.id_btn);
        // TODO: 在最开始就设置,猜想是执行顺序的问题
        // mBt.getParent().requestDisallowInterceptTouchEvent(true);
    }
}