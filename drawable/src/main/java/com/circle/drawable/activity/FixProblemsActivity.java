package com.circle.drawable.activity;

import android.app.FragmentManager;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.circle.drawable.fragment.OtherRetainedFragment;
import com.circle.drawable.service.MyAsyncTask;

import java.util.List;

/**
 * TODO:数据状态的保存+网络请求(加载数据过程)等,最佳实践 [写的太好了!!相见恨晚]
 *  参考鸿洋大神: https://blog.csdn.net/lmj623565791/article/details/37936275?spm=1001.2014.3001.5502
 */
public class FixProblemsActivity extends ListActivity {
    private static final String TAG = "FixProblemsActivity";
    private ListAdapter mAdapter;
    private List<String> mDatas;
    private OtherRetainedFragment dataFragment;
    private MyAsyncTask mMyTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");

        // find the retained fragment on activity restarts
        FragmentManager fm = getFragmentManager();
        dataFragment = (OtherRetainedFragment) fm.findFragmentByTag("data");

        // create the fragment and data the first time
        if (dataFragment == null) {
            // add the fragment
            dataFragment = new OtherRetainedFragment();
            fm.beginTransaction().add(dataFragment, "data").commit();
        }
        mMyTask = dataFragment.getData();
        if (mMyTask != null) {
            mMyTask.setActivity(this);
        } else {
            mMyTask = new MyAsyncTask(this);
            dataFragment.setData(mMyTask);
            mMyTask.execute();
        }
        // the data is available in dataFragment.getData()
    }


    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        Log.e(TAG, "onRestoreInstanceState");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mMyTask.setActivity(null);
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }

    /**
     * 回调
     */
    public void onTaskCompleted() {
        mDatas = mMyTask.getItems();
        mAdapter = new ArrayAdapter<>(FixProblemsActivity.this,
                android.R.layout.simple_list_item_1, mDatas);
        setListAdapter(mAdapter);
    }

}