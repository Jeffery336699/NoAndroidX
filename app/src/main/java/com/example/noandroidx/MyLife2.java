package com.example.noandroidx;

import static android.arch.lifecycle.Lifecycle.State.STARTED;

import android.annotation.SuppressLint;
import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

@SuppressLint("RestrictedApi")
public class MyLife2 implements GenericLifecycleObserver {
    boolean shouldBeActive(LifecycleOwner source) {
        //onStart,onResume,onPause
        return source.getLifecycle().getCurrentState().isAtLeast(STARTED);
    }

    @Override
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        switch (event) {
            case ON_CREATE:
                System.out.println("MyLife2---ON_CREATE");
                break;
            case ON_START:
                System.out.println("MyLife2---ON_START");
                break;
            case ON_RESUME:
                System.out.println("MyLife2---ON_RESUME");
                break;
            case ON_PAUSE:
                System.out.println("MyLife2---ON_PAUSE");
                break;
            case ON_STOP:
                System.out.println("MyLife2---ON_STOP");
                break;
            case ON_DESTROY:
                System.out.println("MyLife2---ON_DESTROY");
                break;
            case ON_ANY:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
//        System.out.println("MyLife2--->shouldBeActive:"+shouldBeActive(source));
    }
}
