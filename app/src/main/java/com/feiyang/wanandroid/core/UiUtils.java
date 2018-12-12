package com.feiyang.wanandroid.core;

import android.widget.Toast;

import com.feiyang.wanandroid.App;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/23 4:52 PM<br>
 * Desc: <br>
 */
public class UiUtils {
    public static void showToast(String s) {
        try {
            Toast toast = Toast.makeText(App.getApp(), s, Toast.LENGTH_SHORT);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showLongToast(String text) {
        try {
            Toast toast = Toast.makeText(App.getApp(), text, Toast.LENGTH_LONG);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showShortToast(String text) {
        try {
            Toast toast = Toast.makeText(App.getApp(), text, Toast.LENGTH_SHORT);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void showToastSafe(String text){
        try {
            if (!isFastClick()){
                Toast toast = Toast.makeText(App.getApp(), text, Toast.LENGTH_SHORT);
                toast.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static  long lastClickTime;

    public synchronized static boolean isFastClick() {
        long time     = System.currentTimeMillis();
        long timeDiff = time - lastClickTime;
        lastClickTime = time;
        return timeDiff < 500;
    }

    public synchronized static boolean isFastClick(long period) {
        long time     = System.currentTimeMillis();
        long timeDiff = time - lastClickTime;
        lastClickTime = time;
        return timeDiff < period;
    }
}
