package com.circle.drawable.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.circle.drawable.R;
import com.circle.drawable.fragment.MainTab01;
import com.circle.drawable.fragment.MainTab02;
import com.circle.drawable.fragment.MainTab03;
import com.circle.drawable.view.BadgeView;

import java.util.ArrayList;
import java.util.List;

public class BadgeActivity extends FragmentActivity {
    private static final String TAG = "BadgeActivity";
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    /**
     * 顶部三个LinearLayout
     */
    private ViewGroup mTabLiaotian;
    private LinearLayout mTabFaxian;
    private LinearLayout mTabTongxunlun;

    /**
     * 顶部的三个TextView
     */
    private TextView mLiaotian;
    private TextView mFaxian;
    private TextView mTongxunlu;

    /**
     * 分别为每个TabIndicator创建一个BadgeView
     */
    private BadgeView mBadgeViewforLiaotian;
    private BadgeView mBadgeViewforFaxian;
    private BadgeView mBadgeViewforTongxunlu;

    /**
     * Tab的那个引导线
     */
    private ImageView mTabLine;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;
    /**
     * 屏幕的宽度
     */
    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge);

        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        initView();

        initTabLine();

        /**
         * 初始化Adapter
         */
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };

        mViewPager.setAdapter(mAdapter);

        /**
         * 设置监听
         */
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                // 重置所有TextView的字体颜色
                resetTextView();
                switch (position) {
                    case 0:
                        /**
                         * 设置消息通知
                         */
                        mTabLiaotian.removeView(mBadgeViewforLiaotian);
                        mBadgeViewforLiaotian.setBadgeCount(5);
                        mTabLiaotian.addView(mBadgeViewforLiaotian);
                        mLiaotian.setTextColor(getResources().getColor(R.color.green));
                        break;
                    case 1:
                        /**
                         * 设置消息通知
                         */
                        mFaxian.setTextColor(getResources().getColor(R.color.green));
                        // mTabFaxian.removeView(mBadgeViewforFaxian);
                        // mBadgeViewforFaxian.setBadgeCount(15);
                        // mTabFaxian.addView(mBadgeViewforFaxian);
                        break;
                    case 2:
                        mTongxunlu.setTextColor(getResources().getColor(R.color.green));

                        break;
                }

                currentIndex = position;
                Log.w(TAG, "onPageSelected: -----currentIndex=" + currentIndex);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(TAG, "onPageScrolled: -----position=" + position + " , positionOffset=" + positionOffset + " , ");
                /**
                 * 利用position和currentIndex判断用户的操作是哪一页往哪一页滑动
                 * 然后改变根据positionOffset动态改变TabLine的leftMargin
                 */
                if (currentIndex == 0 && position == 0)// 0->1
                {
                    LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
                            .getLayoutParams();
                    lp.leftMargin = (int) (positionOffset * (screenWidth * 1.0 / 3) + currentIndex * (screenWidth / 3));
                    mTabLine.setLayoutParams(lp);

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
                            .getLayoutParams();
                    lp.leftMargin = (int) (-(1 - positionOffset) * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                    mTabLine.setLayoutParams(lp);

                } else if (currentIndex == 1 && position == 1) // 1->2
                {
                    LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
                            .getLayoutParams();
                    lp.leftMargin = (int) (positionOffset * (screenWidth * 1.0 / 3) + currentIndex * (screenWidth / 3));
                    mTabLine.setLayoutParams(lp);
                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
                            .getLayoutParams();
                    lp.leftMargin = (int) (-(1 - positionOffset) * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                    mTabLine.setLayoutParams(lp);

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mViewPager.setCurrentItem(1);

    }

    /**
     * 根据屏幕的宽度，初始化引导线的宽度
     */
    private void initTabLine() {
        mTabLine = (ImageView) findViewById(R.id.id_tab_line);
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        screenWidth = outMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine.getLayoutParams();
        lp.width = screenWidth / 3;
        mTabLine.setLayoutParams(lp);
    }

    /**
     * 重置颜色
     */
    protected void resetTextView() {
        mLiaotian.setTextColor(getResources().getColor(R.color.black));
        mFaxian.setTextColor(getResources().getColor(R.color.black));
        mTongxunlu.setTextColor(getResources().getColor(R.color.black));
    }

    /**
     * 初始化控件，初始化Fragment
     */
    private void initView() {

        mTabLiaotian =  findViewById(R.id.id_tab_liaotian_ly);
        mTabFaxian =  findViewById(R.id.id_tab_faxian_ly);
        mTabTongxunlun =  findViewById(R.id.id_tab_tongxunlu_ly);

        mLiaotian = (TextView) findViewById(R.id.id_liaotian);
        mFaxian = (TextView) findViewById(R.id.id_faxian);
        mTongxunlu = (TextView) findViewById(R.id.id_tongxunlu);

        MainTab01 tab01 = new MainTab01();
        MainTab02 tab02 = new MainTab02();
        MainTab03 tab03 = new MainTab03();
        mFragments.add(tab01);
        mFragments.add(tab02);
        mFragments.add(tab03);

        mBadgeViewforFaxian = new BadgeView(this);
        mBadgeViewforLiaotian = new BadgeView(this);
        mBadgeViewforTongxunlu = new BadgeView(this);

    }
}