package com.circle.drawable.utils;

import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class Globals {
    private Globals() {
        throw new IllegalArgumentException("该工具类禁止new对象");
    }

    private static Application sApplication;

    public static Application getApplication() {
        if (sApplication == null) {
            try {
                sApplication = (Application) Class.forName("android.app.ActivityThread")
                        .getMethod("currentApplication")
                        .invoke(null, (Object[]) null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sApplication;
    }

    /*public static int getScreenWidth() {
        Resources resources = getApplication().getApplicationContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    *//**
     * 默认减去状态bar的高度
     * @return
     *//*
    public static int getScreenHeight() {
        Resources resources = getApplication().getApplicationContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels-getStatusBarHeight();
    }

    public static int getStatusBarHeight() {
        int height = 0;
        int resourceId = getApplication().getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = getApplication().getApplicationContext().getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }*/





}
