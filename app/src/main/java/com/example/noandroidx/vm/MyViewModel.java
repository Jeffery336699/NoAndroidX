package com.example.noandroidx.vm;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

import com.example.noandroidx.ILog;
import com.example.noandroidx.MyLiveData;
import com.example.noandroidx.bean.User;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel implements ILog {
    private MutableLiveData<List<User>> users;
    private MyLiveData<String> liveData;

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    public MyLiveData<String> getLiveData() {
        if (liveData == null) {
            liveData = new MyLiveData<>();
        }
        return liveData;
    }

    public void loadDatas(){
//        HandlerThread handlerThread = new HandlerThread("HandlerThread名字");
//        handlerThread.start();
////        Handler handler = new Handler(Looper.getMainLooper());
//        Handler handler = new Handler(handlerThread.getLooper());
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Log.i(TAG, "run: --->Thread:" + Thread.currentThread().getName());
//                try {
//                    liveData.postValue("1数据更新！");
//                    liveData.postValue("2数据更新！");
//                    liveData.postValue("3数据更新！");
//                    liveData.postValue("4数据更新！");
//                    liveData.postValue("5数据更新！");
//                    liveData.postValue("6数据更新！");
//                    liveData.postValue("7数据更新！");
//                    liveData.postValue("8数据更新！");
//                    liveData.postValue("9数据更新！");
//                    liveData.postValue("10数据更新！");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 2000);


//        for (int i = 0; i < 1000; i++) {
//            int finalI = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    liveData.postValue(finalI +"数据更新！");
//                }
//            }).start();
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        liveData.postValue(1 +"数据更新！");
        liveData.postValue(2 +"数据更新！");
        liveData.postValue(3 +"数据更新！");
    }


    private void loadUsers() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(new User("" + i, i));
        }
        users.setValue(list);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
