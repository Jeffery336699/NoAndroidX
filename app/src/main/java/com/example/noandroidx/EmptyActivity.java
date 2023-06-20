package com.example.noandroidx;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class EmptyActivity extends Activity implements LifecycleOwner , LifecycleObserver {
    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
    private static final String TAG = "T-MyLife";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        getLifecycle().addObserver(this);//type1:订阅者需要做的事 (只需要关注订阅者就行了，实现LifecycleObserver)
        getLifecycle().addObserver(new Empty());//type2
//        mLifecycleRegistry.handleLifecycleEvent(xxx);//SupportFragment内部会调用这个方法，这是事件Event发送源头

        new Application.ActivityLifecycleCallbacks(){

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        };


    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void C(){//订阅者需要做的事
        Log.i(TAG, this.getClass().getSimpleName()+"--C: --ON_CREATE");
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {//发布者需要做的事
        Log.i(TAG, "getLifecycle: --->调用了");
        return mLifecycleRegistry;
    }
}