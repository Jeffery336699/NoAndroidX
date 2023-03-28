package com.example.noandroidx.vm;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.noandroidx.bean.User;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
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
