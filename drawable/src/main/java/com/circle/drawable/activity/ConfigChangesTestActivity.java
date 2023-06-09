package com.circle.drawable.activity;

import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.circle.drawable.fragment.LoadingDialog;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author zhy
 * 参考: https://blog.csdn.net/lmj623565791/article/details/37936275?spm=1001.2014.3001.5502
 */
public class ConfigChangesTestActivity extends ListActivity {
    private static final String TAG = "ConfigChangesTestActivity";
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
        mLoadingDialog = new LoadingDialog();
        mLoadingDialog.show(getFragmentManager(), "LoadingDialog");
        mLoadDataAsyncTask = new LoadDataAsyncTask();
        mLoadDataAsyncTask.execute();
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        mAdapter = new ArrayAdapter<>(ConfigChangesTestActivity.this,
                android.R.layout.simple_list_item_1, mDatas);
        setListAdapter(mAdapter);
    }

    /**
     * 模拟耗时操作
     *
     * @return
     */
    private ArrayList<String> generateTimeConsumingDatas() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
        }
        return new ArrayList<>(Arrays.asList("通过Fragment保存大量数据",
                "onSaveInstanceState保存数据",
                "getLastNonConfigurationInstance已经被弃用", "RabbitMQ", "Hadoop",
                "Spark"));
    }

    /**
     * 当配置发生变化时，不会重新启动Activity。但是会回调此方法，用户自行进行对屏幕旋转后进行处理
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e(TAG, "onConfigurationChanged");
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }

    }

    private class LoadDataAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            mDatas = generateTimeConsumingDatas();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
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