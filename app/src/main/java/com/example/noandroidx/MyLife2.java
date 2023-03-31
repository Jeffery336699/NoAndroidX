package com.example.noandroidx;

import android.annotation.SuppressLint;
import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

@SuppressLint("RestrictedApi")
public class MyLife2 implements GenericLifecycleObserver {
    @Override
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {

    }
}
