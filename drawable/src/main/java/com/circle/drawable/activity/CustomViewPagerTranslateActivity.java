package com.circle.drawable.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.circle.drawable.R;
import com.circle.drawable.view.MyJazzyViewPager;

public class CustomViewPagerTranslateActivity extends AppCompatActivity {
    protected static final String TAG = "CustomViewPagerTranslateActivity";
    private int[] mImgIds;
    private MyJazzyViewPager mViewPager;
    // private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_pager_translate);
        mImgIds = new int[]{R.drawable.wx1, R.drawable.wx2, R.drawable.wx3, R.drawable.wx4};
        mViewPager = findViewById(R.id.id_viewPager);
        // mViewPager.setOffscreenPageLimit(10); // 设置默认左右显示的View实例的数量,很重要的参数
        mViewPager.setAdapter(new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView= new ImageView(CustomViewPagerTranslateActivity.this);
                imageView.setImageResource(mImgIds[position]);
                imageView.setScaleType(ScaleType.CENTER_CROP);
                container.addView(imageView);
                mViewPager.setObjectForPosition(imageView, position);
                return imageView;

                // FIXME: 1.嵌套一层规避最底部一层出现的情况,只是采用parentContainer遮挡住了;
                //  2.同时也可以把多余的那个View找到隐藏掉的方式
                // View view = getLayoutInflater().inflate(R.layout.item_iv_container, container, false);
                // ImageView imageView = view.findViewById(R.id.iv_pic);
                // imageView.setImageResource(mImgIds[position]);
                // container.addView(view);
                // mViewPager.setObjectForPosition(view, position);
                // return view;
            }

            @Override
            public int getCount() {
                return mImgIds.length;
            }
        });
    }

    public void onClickBt(View view) {
        //TODO 原生ViewPager确实是横向排列,且Left,x,tranlationX等并没有通过动画有所改变 eg childCount=4, firstLeft=0, secondLeft=750, threeLeft=1500, fourLeft=2250
        int childCount = mViewPager.getChildCount();
        System.out.println("childCount="+childCount+", firstLeft="+mViewPager.getChildAt(0).getLeft()+", secondLeft="+mViewPager.getChildAt(1).getLeft()
                +", threeLeft="+mViewPager.getChildAt(2).getLeft()+", fourLeft="+mViewPager.getChildAt(3).getLeft());
    }

    public void onTestCoordinate(View view) {
        // left=0 , translationX=156.0 , x=156.0
        System.out.println("left="+view.getLeft()+" , translationX="+view.getTranslationX()+" , x="+view.getX());
    }
}