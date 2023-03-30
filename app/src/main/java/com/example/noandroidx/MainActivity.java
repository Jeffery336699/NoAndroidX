package com.example.noandroidx;

import android.arch.lifecycle.HolderFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.example.noandroidx.bean.User;
import com.example.noandroidx.fragment.HostFragment;
import com.example.noandroidx.vm.MyViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ILog {

    private static final String HOST_TAG = "jekema_HostFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);//高版本

//        BlankFragment fragment = BlankFragment.newInstance();
//        ViewModelProviders.of(fragment).get(MyViewModel.class);

        FragmentManager fm = getSupportFragmentManager();
        Log.i(TAG, "FragmentManager: "+fm.hashCode());
        Fragment fragment = fm.findFragmentByTag(HOST_TAG);
        if (fragment == null) {
            fragment = new HostFragment();
            fm.beginTransaction().add(fragment, HOST_TAG).commitAllowingStateLoss();
        }
        Log.i(TAG, "fragment: "+fragment.hashCode());

        myViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                Log.i(TAG, "onChanged: " + users.size());
            }
        });

    }
}