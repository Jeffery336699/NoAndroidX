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

    public static int getScreenWidth() {
        Resources resources = getApplication().getApplicationContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        Resources resources = getApplication().getApplicationContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

}
