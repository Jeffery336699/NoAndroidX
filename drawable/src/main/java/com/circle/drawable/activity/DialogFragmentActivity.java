package com.circle.drawable.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.circle.drawable.BuildConfig;
import com.circle.drawable.R;
import com.circle.drawable.fragment.EditNameDialogFragment;
import com.circle.drawable.fragment.FirstFragment;
import com.circle.drawable.fragment.LoginDialogFragment;

public class DialogFragmentActivity extends AppCompatActivity implements LoginDialogFragment.LoginInputListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);
    }

    public void onXml(View view) {
        EditNameDialogFragment editNameDialog = new EditNameDialogFragment();
        editNameDialog.show(getFragmentManager(), "EditNameDialog");
    }

    public void onCreateDialog(View view) {
        LoginDialogFragment dialog = new LoginDialogFragment();
        dialog.show(getFragmentManager(), "loginDialog");
    }

    // TODO: 通过接口回调与Fragment通信
    @Override
    public void onLoginInputComplete(String username, String password) {
        Toast.makeText(this, "username=" + username + "\r\npassword=" + password, Toast.LENGTH_SHORT).show();
    }

    int i = 1;

    public void showDialogInDifferentScreen(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        EditNameDialogFragment newFragment = new EditNameDialogFragment();

        boolean mIsLargeLayout = getResources().getBoolean(R.bool.large_layout);
        // TODO: debug调试
        if (BuildConfig.DEBUG) {
            mIsLargeLayout = (i++) % 2 == 0;
        }
        Log.e("TAG", mIsLargeLayout + "");
        if (mIsLargeLayout) {
            // The device is using a large layout, so show the fragment as a
            // dialog
            newFragment.show(fragmentManager, "dialog");
        } else {
            // The device is smaller, so show the fragment fullscreen
            FragmentTransaction transaction = fragmentManager
                    .beginTransaction();
            // For a little polish, specify a transition animation
            transaction
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // To make it fullscreen, use the 'content' root view as the
            // container
            // for the fragment, which is always the root view for the activity

            transaction.replace(R.id.id_ly, newFragment)
                    .commit();
        }
    }

    public void onChangeForm(View view) {
        showDialogInDifferentScreen(view);
    }
}