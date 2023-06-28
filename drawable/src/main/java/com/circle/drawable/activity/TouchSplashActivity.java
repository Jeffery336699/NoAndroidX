package com.circle.drawable.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.circle.drawable.MenuListActivity;
import com.circle.drawable.R;

/**
 * Splash页面新增触摸就能立马跳转
 * 参考：https://blog.csdn.net/lmj623565791/article/details/23613403?spm=1001.2014.3001.5502
 */
public class TouchSplashActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_spalsh);

        handler.postDelayed(runnable = () -> {
            Intent intent = new Intent(TouchSplashActivity.this, MenuListActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            Intent intent = new Intent(TouchSplashActivity.this, MenuListActivity.class);
            startActivity(intent);
            finish();
            if (runnable != null)
                handler.removeCallbacks(runnable);
        }

        return super.onTouchEvent(event);
    }
}