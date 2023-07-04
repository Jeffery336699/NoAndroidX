package com.circle.drawable.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.circle.drawable.R;
import com.circle.drawable.test.CompletionServiceDemo;
import com.circle.drawable.test.TestInvokeAll;

/**
 * 鸿洋大神博客:https://blog.csdn.net/lmj623565791/article/details/27250059?spm=1001.2014.3001.5502
 */
public class CompletionServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completion_service);
    }

    public void onCompletionService(View view) {
        CompletionServiceDemo.main(new String[]{});
    }

    public void onInvokeAll(View view) throws InterruptedException {
        TestInvokeAll.main(new String[]{});
    }
}