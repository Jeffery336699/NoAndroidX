package com.example.noandroidx;

import android.annotation.SuppressLint;
import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;

@SuppressLint("RestrictedApi")
public class Empty implements GenericLifecycleObserver {
    private static final String TAG = "T-MyLife";
    @Override
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        switch (event) {
            case ON_CREATE:
                Log.i(TAG, this.getClass().getSimpleName()+"---ON_CREATE: ");
                break;
            case ON_START:
                break;
            case ON_RESUME:
                break;
            case ON_PAUSE:
                break;
            case ON_STOP:
                break;
            case ON_DESTROY:
                break;
            case ON_ANY:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
    }
}
