package com.example.noandroidx;

import android.annotation.SuppressLint;
import android.arch.core.executor.ArchTaskExecutor;
import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.noandroidx.vm.MyViewModel;

import java.util.UUID;

public class LiveDataActivity extends AppCompatActivity implements ILog {
    public static MyLiveData<String> liveData;
    public static Observer<String> observer = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            //更新UI
            Log.i(TAG, observer.hashCode() + "--MainActivity--onChanged: -->" + s);
//                ((Button) findViewById(R.id.bt)).setText(s);
        }
    };
    private Button bt;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);
        bt = findViewById(R.id.bt);
        getLifecycle().addObserver((GenericLifecycleObserver) (source, event) -> {
            switch (event) {
                case ON_CREATE:
                    Log.i(TAG, "ON_CREATE: ");
                    break;
                case ON_START:
                    liveData.hashCode();
                    Log.i(TAG, "ON_START: ");
                    break;
                case ON_RESUME:
                    Log.i(TAG, "ON_RESUME: ");
                    break;
                case ON_PAUSE:
                    Log.i(TAG, "ON_PAUSE: ");
                    break;
                case ON_STOP:
                    Log.i(TAG, "ON_STOP: ");
                    break;
                case ON_DESTROY:
                    Log.i(TAG, "ON_DESTROY: ");
                    break;
                case ON_ANY:
                    throw new IllegalArgumentException("ON_ANY must not been send by anybody");
            }
        });
        MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        liveData = myViewModel.getLiveData();

        liveData.observe(this, observer);
        myViewModel.loadDatas();
    }

    public void onJumpNext(View view) {
        startActivity(new Intent(this, SecordActivity.class));
    }

    public void onSetValue(View view) {
        liveData.setValue("主动发送值~");
    }

    public void onThreadSetValue(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                liveData.postValue("子线程发");
            }
        }).start();
    }

    public void onFastSetValue(View view) {
        for (int i = 0; i < 10; i++) {
            liveData.setValue("-->" + i);
        }
    }


    public void onFastPostValue(View view) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            liveData.postValue("-->" + i);
            Thread.sleep(10);
        }
    }

    volatile boolean flag = false;
    volatile int data = 0;

    @SuppressLint("RestrictedApi")
    public void onTestHandler(View view) throws Exception {
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() {
            int i;

            @Override
            public void run() {
                Log.i(TAG, "run: -->" + (++i));
                flag = false;
            }
        };

        for (int i = 0; i < 1000; i++) {
            myPost(i, runnable);
            Thread.sleep(5);
        }
    }

    @SuppressLint("RestrictedApi")
    void myPost(int i, Runnable runnable) {
        Log.i(TAG, "myPost: " + i);
        if (flag) return;
        flag = true;
        ArchTaskExecutor.getInstance().postToMainThread(runnable);
    }

    @SuppressLint("RestrictedApi")
    public void onWhileDo(View view) throws InterruptedException {
        bt.setText("开始了。。。");
        Runnable runnable = new Runnable() {
            int i;
            @Override
            public void run() {
                ++i;
                bt.setText("执行--" + i);
                Log.i(TAG, Thread.currentThread().getName()+"--run: -->" + i);
                flag = false;
            }
        };
        HandlerThread handlerThread = new HandlerThread("xxx");
        handlerThread.start();
        Handler handler = new Handler(Looper.getMainLooper());
        for (int i = 0; i < 1000; i++) {
            Log.i(TAG, "for: " + i);
            Thread.sleep(5);
            if (flag) continue;
            flag = true;
//            ArchTaskExecutor.getInstance().postToMainThread(runnable);
            handler.post(runnable);
        }
    }

    public void onCenterClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10);
                        liveData.postValue(UUID.randomUUID().toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}