package com.example.noandroidx;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class LifecycleActivity extends AppCompatActivity implements LifecycleObserver {

    private MyLife life;
    private static final String TAG = "T-MyLife";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        life = new MyLife();
        getLifecycle().addObserver(life);
        getLifecycle().addObserver(new MyLife2());
        getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void R() {
        Log.i(TAG, this.getClass().getSimpleName() + "--resume: ");
    }

    public void onClick(View view) {
        Toast.makeText(this, "~~", Toast.LENGTH_SHORT).show();
    }

    public void onCustom(View view) {
        startActivity(new Intent(this, EmptyActivity.class));
    }
}