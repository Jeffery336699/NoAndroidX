package com.example.noandroidx;

import android.app.Activity;
import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyProvider extends ContentProvider {
    private static final String TAG = "MyProvider";

    @Override
    public boolean onCreate() {
        ((Application) getContext()).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.d(TAG, activity.getClass().getSimpleName() + "--onActivityCreated: ");
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.d(TAG, activity.getClass().getSimpleName() + "--onActivityStarted: ");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.d(TAG, activity.getClass().getSimpleName() + "--onActivityResumed: ");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.d(TAG, activity.getClass().getSimpleName() + "--onActivityPaused: ");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.d(TAG, activity.getClass().getSimpleName() + "--onActivityStopped: ");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.d(TAG, activity.getClass().getSimpleName() + "--onActivitySaveInstanceState: ");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.d(TAG, activity.getClass().getSimpleName() + "--onActivityDestroyed: ");
            }
        });
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
