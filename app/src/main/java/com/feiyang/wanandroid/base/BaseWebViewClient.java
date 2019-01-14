package com.feiyang.wanandroid.base;

import android.os.Build;
import android.util.Log;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2019/1/10 5:09 PM<br>
 * Desc: <br>
 */
public class BaseWebViewClient extends WebViewClient {
    private static final String TAG=BaseWebViewClient.class.getSimpleName();
    //当渲染器由于内存紧张被android系统kill或者渲染器由于自己原因挂掉了,将与该渲染器关联的WebView实例回收,防止重新创建WebView实例时导致crash
    @Override
    public boolean onRenderProcessGone(WebView view, RenderProcessGoneDetail detail) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //didCrash ->true Render process crashed   false-> 被系统kill
            if (!detail.didCrash()){
                Log.e(TAG, "System killed the WebView rendering process " +
                        "to reclaim memory. Recreating...");
            }
        }
        return super.onRenderProcessGone(view, detail);
    }
}
