package com.circle.drawable.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;

public class RetainedFragment extends Fragment {
    // data object we want to retain
    private Bitmap data;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: 这里的保存状态需要在onCreate方法中就要设置了,retain this fragment
        setRetainInstance(true);
    }

    public void setData(Bitmap data) {
        this.data = data;
    }

    public Bitmap getData() {
        return data;
    }
}