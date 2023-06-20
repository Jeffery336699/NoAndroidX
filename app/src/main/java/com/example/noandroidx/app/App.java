package com.example.noandroidx.app;

import android.app.Application;
import android.content.Intent;

import com.github.anrwatchdog.ANRError;
import com.github.anrwatchdog.ANRWatchDog;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new ANRWatchDog()
                .setANRListener(new ANRWatchDog.ANRListener() {
                    @Override
                    public void onAppNotResponding(ANRError error) {
                        restartApp();
                    }
                }).start();
    }

    private void restartApp() {
        Intent intent = getPackageManager()
                .getLaunchIntentForPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
