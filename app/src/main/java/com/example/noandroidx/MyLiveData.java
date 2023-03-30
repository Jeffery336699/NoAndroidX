package com.example.noandroidx;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

public class MyLiveData<T> extends MutableLiveData<T> implements ILog {

    @Override
    protected void onActive() {
        super.onActive();
        Log.i(TAG, "MyLiveData onActive: ");
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        Log.i(TAG, "MyLiveData inActive: ");
    }


}
