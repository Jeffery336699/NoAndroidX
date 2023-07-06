package com.circle.drawable.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.circle.drawable.R;

public class EditNameDialogFragment extends DialogFragment {

    private EditText mEditText;
    private Button mBtSure;

    //TODO 1.采用xml形式创建Dialog
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 去掉不需要的Title
        View view;
        if (getDialog() != null) {// 只有是dialog形式时才有这种操作[内嵌模式就没有dialog!]
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        // TODO: attachToRoot这个属性简直太重要了,为false表示它不被添加到任何的ViewGroup中,后续它就能被添加到其他容器中[eg Fragment被添加到ViewGroup]
        //  参考: https://www.jianshu.com/p/d43d0ac7e2f7
        view = inflater.inflate(R.layout.fragment_edit_name, container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 设置点击窗体外边是否可以取消dialog
        // getDialog().setCanceledOnTouchOutside(false);
        mEditText = view.findViewById(R.id.id_txt_your_name);
        mBtSure = view.findViewById(R.id.id_sure_edit_name);
        mBtSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "edit: " + mEditText.getText().toString(), Toast.LENGTH_SHORT).show();
                // dismiss(); //TODO 是以Dialog形式出现才能dismiss,因为这是Dialog的属性及方式;如果当做Fragment被内嵌到其他容器中,那就无法使用
            }
        });
    }
}