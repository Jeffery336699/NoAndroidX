package com.circle.drawable.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.circle.drawable.R;
import com.circle.drawable.utils.Globals;
import com.circle.drawable.view.ArcMenu;

public class ArcMenuActivity extends AppCompatActivity {

    private static final String TAG = "ArcMenuActivity";
    private ArcMenu mArcMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc_menu);
        initView();
        initListener();
    }

    private void initView() {
        mArcMenu = findViewById(R.id.id_arcmenu1);
        // TODO: 自己构造出measureSpec让其先走测绘流程,这里给出的值只是父容器建议它的参考,它内部还得根据自身具体情形决定最终的宽高
        mArcMenu.measure(View.MeasureSpec.makeMeasureSpec(Globals.getScreenWidth(), View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(Globals.getScreenHeight(), View.MeasureSpec.EXACTLY));
        Log.w(TAG, "onCreate:  mArcMenu.getMeasuredWidth()="+mArcMenu.getMeasuredWidth()+" , mArcMenu.getMeasuredHeight()="+mArcMenu.getMeasuredHeight());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mArcMenu.post(new Runnable() {
            @Override
            public void run() {
                Log.w(TAG, "onResume:  mArcMenu.getWidth()=" + mArcMenu.getWidth() + " , mArcMenu.getHeight()=" + mArcMenu.getHeight());
            }
        });
    }

    private void initListener() {
        mArcMenu.setOnMenuItemClickListener(new ArcMenu.OnMenuItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                Log.i(TAG, "onClick: pos="+pos+" , view="+view);
            }
        });
    }
}