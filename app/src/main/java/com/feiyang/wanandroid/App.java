package com.feiyang.wanandroid;

import android.app.Application;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/10/25 3:41 PM<br>
 * Desc: <br>
 */
public class App extends Application {
    private static App mInstance;

    public static App getApp() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
