package com.circle.drawable.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.circle.drawable.activity.FixProblemsActivity;
import com.circle.drawable.fragment.LoadingDialog;

@SuppressLint("StaticFieldLeak")
public class MyAsyncTask extends AsyncTask<Void, Void, Void> {

    private FixProblemsActivity activity;
    /**
     * 是否完成
     */
    private boolean isCompleted;
    /**
     * 进度框
     */
    private LoadingDialog mLoadingDialog;
    private List<String> items;

    public MyAsyncTask(FixProblemsActivity activity) {
        this.activity = activity;
    }

    /**
     * 开始时，显示加载框
     */
    @Override
    protected void onPreExecute() {
        mLoadingDialog = new LoadingDialog();
        mLoadingDialog.show(activity.getFragmentManager(), "LOADING");
    }

    /**
     * 加载数据
     */
    @Override
    protected Void doInBackground(Void... params) {
        items = loadingData();
        return null;
    }

    /**
     * 加载完成回调当前的Activity
     */
    @Override
    protected void onPostExecute(Void unused) {
        isCompleted = true;
        notifyActivityTaskCompleted();
        if (mLoadingDialog != null)
            mLoadingDialog.dismiss();
    }

    public List<String> getItems() {
        return items;
    }

    private List<String> loadingData() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
        }
        return new ArrayList<>(Arrays.asList("通过Fragment保存大量数据",
                "onSaveInstanceState保存数据",
                "getLastNonConfigurationInstance已经被弃用", "RabbitMQ", "Hadoop",
                "Spark"));
    }

    /**
     * 设置Activity，因为Activity会一直变化
     *
     * @param activity
     */
    public void setActivity(FixProblemsActivity activity) {
        // 如果上一个Activity销毁，将与上一个Activity绑定的DialogFragment销毁
        if (activity == null) {
            mLoadingDialog.dismiss();
        }
        // 设置为当前的Activity
        this.activity = activity;
        // 开启一个与当前Activity绑定的等待框
        if (activity != null && !isCompleted) {
            // TODO: 这里我只是UI重新给你展现出来,跟new的还是之前的没啥关系;
            //  内部的AsyncTask还是没变,还是之前的那个还在继续加载数据,这点很重要
            //  还有Dialog多次show并且还是同一tag没事,前提你的把之前那个dialog.dismiss()掉
            mLoadingDialog = new LoadingDialog();
            mLoadingDialog.show(activity.getFragmentManager(), "LOADING");
        }
        // 如果完成，通知Activity
        if (isCompleted) {
            notifyActivityTaskCompleted();
        }
    }

    private void notifyActivityTaskCompleted() {
        // TODO: 只有activity处于AIDL显示状态才会回调任务完成;你在销毁重现这个过程中,当然不会给你回调呀
        //  触发阶段有两个不会给你忘记掉
        if (null != activity) {
            activity.onTaskCompleted();
        }
    }

}