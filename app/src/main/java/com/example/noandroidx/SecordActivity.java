package com.example.noandroidx;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecordActivity extends AppCompatActivity implements ILog{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secord);
        LiveDataActivity.liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //更新UI
                Log.i(TAG,  "SecordActivity--onChanged: -->" + s);
            }
        });
//        LiveDataActivity.liveData.observe(this,LiveDataActivity.observer);//observer不能重复注入
    }
    int i;
    public void onViewClick(View view) {
        LiveDataActivity.liveData.setValue((++i)+" -- ");
    }
}