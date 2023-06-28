package com.circle.drawable.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.circle.drawable.R;
import com.circle.drawable.view.MyImageView;

public class ImageScaleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_scale);
        initView();
    }

    private void initView() {
        MyImageView ivJoke = findViewById(R.id.c_joke);
        ivJoke.setOnClickIntent(new MyImageView.OnViewClickListener() {
            @Override
            public void onViewClick(MyImageView view) {
                Log.e("MyImageView", "MyImageView.OnViewClickListener:---- ");
            }
        });

        ivJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MyImageView", "View.OnClickListener:---- ");
            }
        });
    }
}