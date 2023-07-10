package com.circle.drawable.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.circle.drawable.R;

public class NOAidlStudyActivity extends Activity {

    private IBinder mPlusBinder;
    private ServiceConnection mServiceConnPlus = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("client", "mServiceConnPlus onServiceDisconnected");
            mPlusBinder = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("client", " mServiceConnPlus onServiceConnected");
            Log.e("client", " service = "+service);
            mPlusBinder = service;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noaidl_study);
    }

    public void bindService(View view) {
        Intent intentPlus = new Intent();
        intentPlus.setAction("com.zhy.aidl.calcplus");
        intentPlus.setPackage(getPackageName());
        boolean plus = bindService(intentPlus, mServiceConnPlus, Context.BIND_AUTO_CREATE);
        Log.e("plus", plus + "");
    }

    public void unbindService(View view) {
        unbindService(mServiceConnPlus);
    }

    /**
     * TODO : AIDL操作就是拿到binder的代理来操作,proxy走的binder端的transact()方法,把参数传递到底层驱动(就包含方法所对应的code)
     *  底层驱动通过查询到对应的服务端,调用服务端的binder的onTransact方法,最后根据code调用到对应的方法
     */
    public void mulInvoked(View view) {
        if (mPlusBinder == null) {
            Toast.makeText(this, "未连接服务端或服务端被异常杀死", Toast.LENGTH_SHORT).show();
        } else {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            int _result;
            try {
                _data.writeInterfaceToken("CalcPlusService");
                _data.writeInt(50);
                _data.writeInt(12);
                mPlusBinder.transact(0x110, _data, _reply, 0);
                _reply.readException();
                _result = _reply.readInt();
                Toast.makeText(this, _result + "", Toast.LENGTH_SHORT).show();

            } catch (RemoteException e) {
                e.printStackTrace();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }
    }

    public void divInvoked(View view) {
        if (mPlusBinder == null) {
            Toast.makeText(this, "未连接服务端或服务端被异常杀死", Toast.LENGTH_SHORT).show();
        } else {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            int _result;
            try {
                _data.writeInterfaceToken("CalcPlusService");
                _data.writeInt(36);
                _data.writeInt(12);
                mPlusBinder.transact(0x111, _data, _reply, 0);
                _reply.readException();
                _result = _reply.readInt();
                Toast.makeText(this, _result + "", Toast.LENGTH_SHORT).show();

            } catch (RemoteException e) {
                e.printStackTrace();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }

    }
}