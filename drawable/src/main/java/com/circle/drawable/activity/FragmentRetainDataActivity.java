package com.circle.drawable.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.circle.drawable.R;
import com.circle.drawable.fragment.LoadingDialog;
import com.circle.drawable.fragment.RetainedFragment;

/**
 * TODO:此种方式也是仅仅适合数据加载完了之后,只是数据可以存放大的数据到Fragment中(Fragment需要设置retainInstance)
 *  局限性:还是无法解决在数据加载过程中处理旋转屏幕的异常状况
 *  参考:https://blog.csdn.net/lmj623565791/article/details/37936275?spm=1001.2014.3001.5502
 */
public class FragmentRetainDataActivity extends Activity {

    private static final String TAG = "FragmentRetainDataActivity";
    private RetainedFragment dataFragment;
    private DialogFragment mLoadingDialog;
    private ImageView mImageView;
    private Bitmap mBitmap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_retain_data);
        Log.e(TAG, "onCreate");

        // find the retained fragment on activity restarts
        FragmentManager fm = getFragmentManager();
        dataFragment = (RetainedFragment) fm.findFragmentByTag("data");
        // create the fragment and data the first time
        if (dataFragment == null) {
            // add the fragment
            dataFragment = new RetainedFragment();
            fm.beginTransaction().add(dataFragment, "data").commit();
        }
        mBitmap = collectMyLoadedData();
        initData();

        // the data is available in dataFragment.getData()
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mImageView = findViewById(R.id.id_imageView);
        if (mBitmap == null) {
            mLoadingDialog = new LoadingDialog();
            mLoadingDialog.show(getFragmentManager(), "LOADING_DIALOG");
            RequestQueue newRequestQueue = Volley.newRequestQueue(FragmentRetainDataActivity.this);
            ImageRequest imageRequest = new ImageRequest(
                    "http://g.hiphotos.baidu.com/image/pic/item/6d81800a19d8bc3e770bd00d868ba61ea9d345f2.jpg",
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            mBitmap = response;
                            mImageView.setImageBitmap(mBitmap);
                            // load the data from the web
                            dataFragment.setData(mBitmap);
                            mLoadingDialog.dismiss();
                        }
                    }, 0, 0, Config.RGB_565, null);
            newRequestQueue.add(imageRequest);
        } else {
            mImageView.setImageBitmap(mBitmap);
        }

    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        // store the data in the fragment
        dataFragment.setData(mBitmap);
    }

    private Bitmap collectMyLoadedData() {
        return dataFragment.getData();
    }

}