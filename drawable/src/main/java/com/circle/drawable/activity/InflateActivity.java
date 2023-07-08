package com.circle.drawable.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.circle.drawable.R;

/**
 * TODO: 这个知识点真的很重要
 * 参考: https://blog.csdn.net/lmj623565791/article/details/38171465?spm=1001.2014.3001.5502
 */
public class InflateActivity extends AppCompatActivity {

    private LayoutInflater mInflater;
    private LinearLayout mLinearLayout;
    private Button mView2;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInflater = LayoutInflater.from(this);
        /**
         * view1的layoutParams 应该为null
         * view2的layoutParams 应该不为null，且为FrameLayout.LayoutParams(为它的外层容器的LayoutParams类型)
         * view3为FrameLayout，且将这个button添加到Activity的内容区域了（因为R.id.content代表Activity内容区域）
         */
        View view1 = mInflater.inflate(R.layout.activity_inflate, null);
        mView2 = (Button) mInflater.inflate(R.layout.activity_inflate, findViewById(android.R.id.content), false);
        View view3 = mInflater.inflate(R.layout.activity_inflate, findViewById(android.R.id.content), true);

        Log.e("TAG", "view1 = " + view1 + " , view1.layoutParams = " + view1.getLayoutParams());
        // TODO: 此时LayoutParams.width要看,如果子控件的布局参数是120dp(具体的值),那个LayoutParams.width就直接为这个值;否则还得等到真正的测绘才能会去到
        Log.e("TAG", "view2 = " + mView2 + " , view2.layoutParams = " + mView2.getLayoutParams()+" , layoutParams.width="+ mView2.getLayoutParams().width+" , getMeasuredWidth="+ mView2.getMeasuredWidth());
        Log.e("TAG", "view3 = " + view3);

        /**
         * TODO:每个ViewGroup容器都有一个generateDefaultLayoutParams()的方法,该方法主要是给viewGroup.addView()到该容器的子View添加LayoutParams
         *  一般每个容器类会重写这个方法,给childView附上这个LayoutParams(eg LinearLayout.LayoutParams)
         *  而这个LayoutParams很重要,它会结合父容器的给到的模式规格和子容器自身的布局参数共同决定最终的宽高
         */
        // setContentView(R.layout.activity_inflate2);
        // mLinearLayout = findViewById(R.id.ll_container);
        // mLinearLayout.addView(view1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * TODO: 第二个结论
         *  除非你写死固定的120dp等,layoutParams的宽高直接给到你这个值,最终的measureWidth可能也是会是这个值(自定义控件可能会考虑到实际的屏幕宽高);
         *  layoutParams.width真的只能做个参考(价值很小,每个容器中确定的还不一样),最终还是得以measureWidth为准!
         *  否则在没有view被添加到容器中时,测绘等流程是不会走的,也就是说你获取不到测绘的宽高
         *  测绘流程是针对的整个测绘数,该View的宽高不仅跟你自身设置的宽高有关还与你父容器能给到你的有关(整体是个树形结构)
         */
        mView2.setText("xxxdff");
        mView2.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("TAG", "run addView前:  getLayoutParams().width="+mView2.getLayoutParams().width+" , getMeasuredWidth="+mView2.getMeasuredWidth());
                ((ViewGroup)findViewById(android.R.id.content)).addView(mView2);
            }
        },2000);

        mView2.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("TAG", "run addView后:  getLayoutParams().width="+mView2.getLayoutParams().width+" , getMeasuredWidth="+mView2.getMeasuredWidth());
            }
        },3000);


    }
}