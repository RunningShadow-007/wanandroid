package com.feiyang.wanandroid.base;

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
    //当渲染器由于内存紧张被android系统kill或者渲染器由于自己原因挂掉了
    @Override
    public boolean onRenderProcessGone(WebView view, RenderProcessGoneDetail detail) {
        return super.onRenderProcessGone(view, detail);
    }
}
