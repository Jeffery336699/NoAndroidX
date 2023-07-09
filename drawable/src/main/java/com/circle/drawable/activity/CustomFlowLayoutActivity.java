package com.circle.drawable.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;

import com.circle.drawable.R;
import com.circle.drawable.utils.Globals;

public class CustomFlowLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_flow_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // new Handler().postDelayed(new Runnable() {
        //     @Override
        //     public void run() {
        //         onTest(findViewById(R.id.tvTest));
        //     }
        // },2000);
    }

    public void onTest(View view) {
        view.setTranslationY(-180);
    }
}