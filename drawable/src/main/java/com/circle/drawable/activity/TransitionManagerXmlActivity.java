package com.circle.drawable.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import com.circle.drawable.R;

public class TransitionManagerXmlActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean toggle = true;
    private View img01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_xml_manager_start);
        bindData();
    }

    @Override
    public void onClick(View v) {
        ViewGroup root = findViewById(R.id.root);
        Scene startScene = Scene.getSceneForLayout(root, R.layout.activity_transition_xml_manager_start, this);
        Scene endScene = Scene.getSceneForLayout(root, R.layout.activity_transition_xml_manager_end, this);

        if (toggle) {
            TransitionManager.go(endScene, new ChangeBounds());
        } else {
            TransitionManager.go(startScene, new ChangeBounds());
        }

        bindData(); //需要重新设置点击事件，因为在动画开始的一瞬间，原来的View已经从布局中移除
        toggle = !toggle;
    }

    private void bindData() {
        img01 = findViewById(R.id.img_01);
        img01.setOnClickListener(this);
    }
}