package com.circle.drawable.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.circle.drawable.R;
import com.circle.drawable.holder.ViewHolder;
import com.circle.drawable.utils.ImageLoader;

import java.util.List;

public class MyAdapter extends CommonAdapter<String> {
    private String mDirPath;

    public MyAdapter(Context context, List<String> mDatas, int itemLayoutId , String dirPath) {
        super(context, mDatas, itemLayoutId);
        this.mDirPath = dirPath;
    }

    @Override
    public void convert(ViewHolder helper, String item) {
        helper.setImageResource(R.id.id_item_image,R.drawable.friends_sends_pictures_no);
        helper.setImageByUrl(R.id.id_item_image,mDirPath + "/" + item);
    }

}
