package com.feiyang.wanandroid;

import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.facebook.stetho.Stetho;
import com.feiyang.wanandroid.core.util.SpUtils;
import com.feiyang.wanandroid.ui.login.activity.LoginActivity;

import io.reactivex.plugins.RxJavaPlugins;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/10/25 3:41 PM<br>
 * Desc: <br>
 */
public class App extends Application {
    private static final String TAG = App.class.getSimpleName();

    private static App mInstance;

    private static volatile boolean isFirstLaunch = true;

    public static synchronized App getApp() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
        RxJavaPlugins.setErrorHandler(throwable -> Log.e(TAG, "RxJava 触发了onError: ", throwable));
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory();
        }
        Glide.get(this).trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }

    public boolean isLogined() {
        String username = SpUtils.getUserName();
        String token    = SpUtils.getToken();
        return !TextUtils.isEmpty(username) && !TextUtils.isEmpty(token);
    }

    public boolean isFirstLaunch() {
        return isFirstLaunch;
    }

    public synchronized void invalidFirstLaunch() {
        isFirstLaunch = false;
    }

    public void goLogin(boolean launchByService) {
        SpUtils.invalidLogin();
        Intent intent = new Intent(this, LoginActivity.class);
        int    flag   = launchByService ? Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK : Intent.FLAG_ACTIVITY_NEW_TASK;
        intent.addFlags(flag);
        startActivity(intent);
    }
}
