package com.circle.drawable.fragment;

import android.annotation.SuppressLint;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class MyListFragment extends ListFragment {
    private AdapterView.OnItemClickListener mOnItemClickListener;

    public MyListFragment() {
    }

    public MyListFragment(AdapterView.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(l, v, position, id);
        }
    }
}
