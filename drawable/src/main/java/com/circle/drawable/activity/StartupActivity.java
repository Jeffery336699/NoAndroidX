package com.circle.drawable.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.circle.drawable.MenuListActivity;
import com.circle.drawable.R;

public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        Toast.makeText(this, "快速启动 (见页面)", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> startActivity(new Intent(StartupActivity.this, MenuListActivity.class)), 700);
    }
}