package com.feiyang.wanandroid.core.net.interceptor;

import com.feiyang.wanandroid.util.NetworkUtils;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/21 3:03 PM<br>
 * Desc: <br>
 */
public class CacheInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isConnected()) {
            request = request.newBuilder()
                             .cacheControl(CacheControl.FORCE_CACHE)
                             .build();
        }
        Response response = chain.proceed(request);
        if (NetworkUtils.isConnected()) {
            int maxAge = 0;
            //有网络不缓存
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma")
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 28;
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
        return response;
    }
}
