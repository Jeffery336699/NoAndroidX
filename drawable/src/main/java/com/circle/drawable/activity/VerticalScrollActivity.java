package com.circle.drawable.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.circle.drawable.R;
import com.circle.drawable.view.VerticalLinearLayout;

/**
 * TODO: 得使用低版本的设备，目前测试mumu模拟器的API 23的可以，其他API27的有问题
 */
public class VerticalScrollActivity extends Activity {

    private VerticalLinearLayout mMianLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_scroll);

        mMianLayout = findViewById(R.id.id_main_ly);
        mMianLayout.setOnPageChangeListener(new VerticalLinearLayout.OnPageChangeListener()
        {
            @Override
            public void onPageChange(int currentPage)
            {
//				mMianLayout.getChildAt(currentPage);
                Toast.makeText(VerticalScrollActivity.this, "第"+(currentPage+1)+"页", Toast.LENGTH_SHORT).show();
            }
        });
    }
}