package com.feiyang.wanandroid.core.net.interceptor;

import com.feiyang.wanandroid.util.SpUtils;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/21 5:35 PM<br>
 * Desc: <br>
 */
public class SaveCookieInterceptor implements Interceptor {
    private static final String COOKIE_USER = "loginUserName";

    private static final String COOKIE_TOKEN = "token_pass";

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request      request  = chain.request();
        Response     response = chain.proceed(request);
        List<String> cookies  = response.headers("Set-Cookie");
        if (!cookies.isEmpty()) {
            for (String cookie : cookies) {
                try {
                    if (cookie.startsWith(COOKIE_TOKEN)) {
                        SpUtils.setToken(cookie);
                    } else if (cookie.startsWith(COOKIE_USER)) {
                        SpUtils.setUserName(cookie);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }
}
