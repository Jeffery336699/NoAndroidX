package com.circle.drawable.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.circle.drawable.R;
import com.circle.drawable.view.GestureLockViewGroup;

public class GestureLockActivity extends AppCompatActivity {

    private GestureLockViewGroup mLockViewGroup;
    private static final String TAG = "GestureLockActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_lock);

        initView();
    }

    private void initView() {
        mLockViewGroup = findViewById(R.id.id_gestureLockViewGroup);
        mLockViewGroup.setAnswer(new int[]{1, 2, 3});
        mLockViewGroup.setOnGestureLockViewListener(new GestureLockViewGroup.OnGestureLockViewListener() {
            @Override
            public void onBlockSelected(int cId) {
                Log.i(TAG, "onBlockSelected: --cId=" + cId);
            }

            @Override
            public void onGestureEvent(boolean matched) {
                Log.i(TAG, "onGestureEvent: --matched=" + matched);
            }

            @Override
            public void onUnmatchedExceedBoundary() {
                Log.i(TAG, "onUnmatchedExceedBoundary: ");
            }
        });
    }
}