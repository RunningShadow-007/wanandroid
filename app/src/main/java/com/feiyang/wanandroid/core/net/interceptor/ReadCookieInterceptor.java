package com.feiyang.wanandroid.core.net.interceptor;

import com.feiyang.wanandroid.core.util.SpUtils;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/21 5:08 PM<br>
 * Desc: <br>
 */
public class ReadCookieInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder builder  = chain.request().newBuilder();
        String          userName = SpUtils.getUserName();
        String          token    = SpUtils.getToken();
        builder.addHeader("Cookie", userName);
        builder.addHeader("Cookie", token);
        return chain.proceed(builder.build());
    }
}
