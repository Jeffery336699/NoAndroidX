package com.circle.drawable.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.circle.drawable.R;
import com.circle.drawable.adapter.CommonAdapter;
import com.circle.drawable.holder.ViewHolder;
import com.circle.drawable.view.PortraitListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PortraitListViewActivity extends Activity {

    private PortraitListView mListView;
    private CommonAdapter<Pair<String,Integer>> mAdapter;
    private List<Pair<String,Integer>> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_portrait_list_view);

        mListView =  findViewById(R.id.id_lv);
        initDatas();
        initViews();

    }

    private void initViews() {
        mListView =  findViewById(R.id.id_lv);
        mAdapter = new CommonAdapter<Pair<String,Integer>>(this, mDatas, R.layout.item2) {
            @Override
            public void convert(ViewHolder helper, Pair<String,Integer> item) {
                View convertView = helper.getConvertView();
                convertView.setBackgroundColor(item.second);
                ViewGroup.LayoutParams lp = convertView.getLayoutParams();
                lp.height = mListView.getItemHeight();
                convertView.setLayoutParams(lp);
                helper.setText(R.id.id_title2, item.first);
            }
        };

        mListView.setAdapter(mAdapter);
    }

    private void initDatas() {
        mDatas = new ArrayList<>();

        for (int i = 'A'; i <= 'Z'; i++) {
            int color = Color.rgb(new Random().nextInt(255),
                    new Random().nextInt(255),
                    new Random().nextInt(255));
            Pair<String,Integer> pair = new Pair<>(String.valueOf((char) +i), color);
            mDatas.add(pair);
        }
    }
}
