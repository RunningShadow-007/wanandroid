package com.feiyang.wanandroid;

import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.facebook.stetho.Stetho;
import com.feiyang.wanandroid.ui.login.activity.LoginActivity;
import com.feiyang.wanandroid.util.SpUtils;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/10/25 3:41 PM<br>
 * Desc: <br>
 */
public class App extends Application {
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

    public void goLogin() {
        SpUtils.invalidLogin();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
