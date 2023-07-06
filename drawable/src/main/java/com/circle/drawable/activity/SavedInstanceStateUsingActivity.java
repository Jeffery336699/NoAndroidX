package com.circle.drawable.activity;

import android.app.DialogFragment;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.circle.drawable.fragment.LoadingDialog;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 不考虑加载时，进行旋转的情况，有意的避开这种情况，后面例子会介绍解决方案
 * TODO:这种方式在「数据加载完后」,旋转屏幕数据能正常保留且不会有任何异常等
 *  https://blog.csdn.net/lmj623565791/article/details/37936275?spm=1001.2014.3001.5502
 * @author zhy
 */
public class SavedInstanceStateUsingActivity extends ListActivity {
    private static final String TAG = "SavedInstanceStateUsingActivity";
    private ListAdapter mAdapter;
    private ArrayList<String> mDatas;
    private DialogFragment mLoadingDialog;
    private LoadDataAsyncTask mLoadDataAsyncTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        initData(savedInstanceState);
    }

    /**
     * 初始化数据
     */
    private void initData(Bundle savedInstanceState) {
        if (savedInstanceState != null)
            mDatas = savedInstanceState.getStringArrayList("mDatas");

        if (mDatas == null) {
            mLoadingDialog = new LoadingDialog();
            mLoadingDialog.show(getFragmentManager(), "LoadingDialog");
            mLoadDataAsyncTask = new LoadDataAsyncTask();
            mLoadDataAsyncTask.execute();
        } else {
            initAdapter();
        }

    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        mAdapter = new ArrayAdapter<>(
                SavedInstanceStateUsingActivity.this,
                android.R.layout.simple_list_item_1, mDatas);
        setListAdapter(mAdapter);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        Log.e(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState");
        outState.putSerializable("mDatas", mDatas);
    }

    /**
     * 模拟耗时操作
     *
     * @return
     */
    private ArrayList<String> generateTimeConsumingDatas() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        return new ArrayList<>(Arrays.asList("通过Fragment保存大量数据",
                "onSaveInstanceState保存数据",
                "getLastNonConfigurationInstance已经被弃用", "RabbitMQ", "Hadoop",
                "Spark"));
    }

    private class LoadDataAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            mDatas = generateTimeConsumingDatas();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO: 如果在数据加载过程中旋转屏幕,与mLoadingDialog绑定的FragmentManager会为null,mLoadingDialog进行相关Fragment操作会爆fm空指针异常
            //  参考: https://blog.csdn.net/lmj623565791/article/details/37936275?spm=1001.2014.3001.5502
            Log.i(TAG, "onPostExecute: ---getFragmentManager="+mLoadingDialog.getFragmentManager()); // 这个会为null
            mLoadingDialog.dismiss();
            initAdapter();
        }
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }

}