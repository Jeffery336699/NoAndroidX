package com.circle.drawable.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.circle.drawable.R;
import com.circle.drawable.fragment.MyListFragment;

import java.util.ArrayList;
import java.util.Map;

public abstract class ListFragmentActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public abstract @LayoutRes
    int getLayoutRes();

    public abstract @IdRes
    int getContainerId();

    public abstract Map<String, Class<? extends Fragment>> getMapData();

    private ArrayList<Class<? extends Fragment>> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        ListFragment listFragment = new MyListFragment(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(getMapData().keySet()));
        mFragments = new ArrayList<>(getMapData().values());
        listFragment.setListAdapter(adapter);
        transaction.add(getContainerId(), listFragment);
        transaction.commit();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment instantiate = Fragment.instantiate(this, mFragments.get(position).getCanonicalName());
        transaction.replace(getContainerId(), instantiate);
        transaction.addToBackStack(null); // 添加到返回栈,以便能像Activity那样能返回到上一个Fragment
        transaction.commit();
    }


}
