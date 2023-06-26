package com.circle.drawable;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.circle.drawable.activity.CustomDrawableActivity;
import com.circle.drawable.activity.DrawableActivity;
import com.circle.drawable.activity.QQListviewActivity;
import com.circle.drawable.activity.StartupActivity;
import com.circle.drawable.activity.TransitionDemoActivity;
import com.circle.drawable.activity.TransitionManagerActivity;
import com.circle.drawable.activity.TransitionManagerXmlActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MenuListActivity extends ListActivity {

    private final Map<String, Class<? extends Activity>> map = new LinkedHashMap<String, Class<? extends Activity>>() {
        {
            put("Drawable定制化", DrawableActivity.class);
            put("Drawable自定义属性", CustomDrawableActivity.class);
            put("TransitionManager动画API使用", TransitionManagerActivity.class);
            put("TransitionManager【xml】转场动画使用", TransitionManagerXmlActivity.class);
            put("QQListView删除", QQListviewActivity.class);
        }
    };

    private List<String> titles = new ArrayList<>(map.keySet());
    private List<Class<? extends Activity>> targetClazz = new ArrayList<>(map.values());


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<String> items = fillList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);

    }

    private List<String> fillList() {
        return titles;
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        startActivity(new Intent(this, targetClazz.get(position)));
    }

}
