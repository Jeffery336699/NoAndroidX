package com.example.noandroidx;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.example.noandroidx.bean.User;
import com.example.noandroidx.vm.MyViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ILog {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);//高版本

        myViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                Log.i(TAG, "onChanged: " + users.size());
            }
        });

    }
}