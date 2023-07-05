package com.circle.drawable.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.circle.drawable.R;
import com.circle.drawable.view.BounceScrollView;

import java.util.ArrayList;
import java.util.Arrays;

public class BounceScrollActivity extends AppCompatActivity {

    private ListView mListView;
    private BounceScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bounce_scroll);
        mScrollView = (BounceScrollView) findViewById(R.id.id_scrollView);
        mScrollView.setCallBack(new BounceScrollView.Callback() {

            @Override
            public void callback() {
                Toast.makeText(BounceScrollActivity.this, "you can do something!", Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(BounceScrollActivity.this,
                        CompletionServiceActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        mListView = (ListView) findViewById(R.id.id_listView);
        mListView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, new ArrayList<>(
                Arrays.asList("Hello", "World", "Welcome", "Java",
                        "Android", "Lucene", "C++", "C#", "HTML",
                        "Welcome", "Java", "Android", "Lucene", "C++",
                        "C#", "HTML","item","Hello", "World", "Welcome", "Java",
                        "Android", "Lucene", "C++", "C#", "HTML",
                        "Welcome", "Java"))));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(BounceScrollActivity.this, "position="+position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}