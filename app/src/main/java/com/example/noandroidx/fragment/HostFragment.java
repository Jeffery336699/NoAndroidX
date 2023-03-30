package com.example.noandroidx.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noandroidx.ILog;
import com.example.noandroidx.R;

public class HostFragment extends Fragment implements ILog {

    public HostFragment() {
        setRetainInstance(true);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, this.getClass().getSimpleName()+"---onCreate: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, this.getClass().getSimpleName()+"---onDestroy: ");
    }
}