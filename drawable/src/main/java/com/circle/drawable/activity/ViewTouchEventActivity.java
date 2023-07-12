package com.circle.drawable.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;

import com.circle.drawable.R;

/**
 * 鸿洋老师讲的真的太好了
 * 参考: https://blog.csdn.net/lmj623565791/article/details/38960443?spm=1001.2014.3001.5502
 */
public class ViewTouchEventActivity extends Activity {
    protected static final String TAG = "MyButton";
    private Button mButton;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_touch_event);

        mButton = findViewById(R.id.id_btn);
        mButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "onTouch ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "onTouch ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "onTouch ACTION_UP");
                        break;
                    default:
                        break;
                }

                return false;
            }
        });
        /**
         * 详细还得看鸿洋博客,真的太牛逼了!!
         * TODO: 关于ActionEvent.Up与ActionEvent.Down的差值,来判断事件的响应
         *  1.如果<100ms(ViewConfiguration.getTapTimeout),NO --> click事件
         *  2.[100,500](ViewConfiguration.getLongPressTimeout)
         *      YES --> click事件
         *  3.>500ms,此时LongClick事件是有了,但是click事件根据setOnLongClick中回调的返回值决定
         *      a.true --> NO click
         *      b.false --> YES click
         */
        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "onclick", Toast.LENGTH_SHORT).show();
            }
        });

        mButton.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "setOnLongClickListener", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // TODO: 测试 
        findViewById(R.id.id_btn2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewTouchEventActivity.this, "222", Toast.LENGTH_SHORT).show();
            }
        });
    }


}