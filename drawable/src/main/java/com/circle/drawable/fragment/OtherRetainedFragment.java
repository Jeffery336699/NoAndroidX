package com.circle.drawable.fragment;

import android.app.Fragment;
import android.os.Bundle;

import com.circle.drawable.service.MyAsyncTask;

/**
 * 保存对象的Fragment
 *
 * @author zhy
 */
public class OtherRetainedFragment extends Fragment {

    // data object we want to retain
    // 保存一个异步的任务
    private MyAsyncTask data;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
        // TODO: 这个获取的FM根据情况来定
        //  1.该Fragment如果它存在于Activity的容器中,该方法获取到的FM则是getActivity.getFragmentManager
        //  2.该Fragment如果它存在于Fragment(上一个Parent Fragment)的容器中,该方法获取到的FM则是Parent_Fragment.getChildFragmentManager
        // getFragmentManager();
    }

    public void setData(MyAsyncTask data) {
        this.data = data;
    }

    public MyAsyncTask getData() {
        return data;
    }


}