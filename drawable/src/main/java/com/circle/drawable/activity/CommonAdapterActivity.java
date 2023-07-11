package com.circle.drawable.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.circle.drawable.R;
import com.circle.drawable.adapter.CommonAdapter;
import com.circle.drawable.holder.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonAdapterActivity extends AppCompatActivity {

    private ListView mListView;
    private List<String> mDatas = new ArrayList<>(Arrays.asList("张三", "李四", "王五", "马六","杨洋",
            "王一博", "杨幂", "古娜力扎" ,"迪丽热巴","杨颖","赵丽颖","赵露思","赵今麦","张子枫"));
    private CommonAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_adapter);
        mListView = (ListView) findViewById(R.id.id_lv_main);

        //设置适配器
        mListView.setAdapter(mAdapter = new CommonAdapter<String>(
                getApplicationContext(), mDatas, R.layout.item_list) {
            @Override
            public void convert(ViewHolder viewHolder, String item) {
                viewHolder.setText(R.id.tv_title,item);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CommonAdapterActivity.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        });

    }
}