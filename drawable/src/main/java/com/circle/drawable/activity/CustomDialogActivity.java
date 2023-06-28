package com.circle.drawable.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.circle.drawable.R;

// TODO: 设置半透明Dialog样式需要采用原生Activity形式！！！
public class CustomDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }


    public void tip(View view) {
        Toast.makeText(this, "adfafadffafds", Toast.LENGTH_SHORT).show();
    }
}