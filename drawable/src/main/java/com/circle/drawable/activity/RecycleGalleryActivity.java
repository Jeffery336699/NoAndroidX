package com.circle.drawable.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.circle.drawable.R;
import com.circle.drawable.adapter.GalleryAdapter;
import com.circle.drawable.view.CopyOfMyRecyclerView;
import com.circle.drawable.view.GalleryRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecycleGalleryActivity extends Activity {

    // private CopyOfMyRecyclerView mRecyclerView;
    private GalleryRecyclerView mRecyclerView;
    private GalleryAdapter mAdapter;
    private List<Integer> mDatas;
    private ImageView mImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_recycle_gallery);

        mImg = findViewById(R.id.id_content);

        mDatas = new ArrayList<>(Arrays.asList(R.drawable.a,
                R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e,
                R.drawable.f, R.drawable.g, R.drawable.katong1, R.drawable.katong2));

        mRecyclerView =  findViewById(R.id.id_recyclerview_horizontal);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new GalleryAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnItemScrollChangeListener(new GalleryRecyclerView.OnItemScrollChangeListener() {
            @Override
            public void onChange(View view, int position) {
                mImg.setImageResource(mDatas.get(position));
                Log.i("TAG", "onChange:   position="+position);
            }
        });

        mAdapter.setOnItemClickLitener(new GalleryAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
				// Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_SHORT).show();
                mImg.setImageResource(mDatas.get(position));
            }
        });

    }

}