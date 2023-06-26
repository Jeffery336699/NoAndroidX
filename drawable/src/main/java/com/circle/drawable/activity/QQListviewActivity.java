package com.circle.drawable.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.circle.drawable.R;
import com.circle.drawable.callback.DelButtonClickListener;
import com.circle.drawable.view.QQListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QQListviewActivity extends AppCompatActivity {

    private QQListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqlistview);

        mListView = (QQListView) findViewById(R.id.id_listview);
        // 不要直接Arrays.asList
        mDatas = new ArrayList<String>(Arrays.asList("HelloWorld", "Welcome", "Java", "Android", "Servlet", "Struts",
                "Hibernate", "Spring", "HTML5", "Javascript", "Lucene"));
        for (int i = 0; i < 20; i++) {
            mDatas.add("item ---- "+i);
        }
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
        mListView.setAdapter(mAdapter);

        mListView.setDelButtonClickListener(new DelButtonClickListener()
        {
            @Override
            public void clickHappend(final int position)
            {
                Toast.makeText(QQListviewActivity.this, position + " : " + mAdapter.getItem(position), Toast.LENGTH_LONG).show();
                mAdapter.remove(mAdapter.getItem(position));
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(QQListviewActivity.this, position + " : " + mAdapter.getItem(position), Toast.LENGTH_LONG).show();
            }
        });
    }
}