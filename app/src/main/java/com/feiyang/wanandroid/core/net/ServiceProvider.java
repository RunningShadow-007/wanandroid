package com.feiyang.wanandroid.core.net;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.feiyang.wanandroid.App;
import com.feiyang.wanandroid.core.constants.Constants;
import com.feiyang.wanandroid.core.net.interceptor.CacheInterceptor;
import com.feiyang.wanandroid.core.net.interceptor.ReadCookieInterceptor;
import com.feiyang.wanandroid.core.net.interceptor.SaveCookieInterceptor;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/21 1:58 PM<br>
 * Desc: <br>
 */
public class ServiceProvider {
    private static final String BASE_URL = "http://www.wanandroid.com/";

    private static final int DEFAULT_TIME_OUT = 20000;

    private final String TAG = ServiceProvider.this.getClass().getSimpleName();

    private static ServiceProvider sInstance;

    private Retrofit retrofit;

    private OkHttpClient mOkHttpClient;

    private HashMap<String, Object> mApiMap = new HashMap<>();

    public static ServiceProvider getInstance() {
        if (sInstance == null) {
            synchronized (ServiceProvider.class) {
                if (sInstance == null) {
                    sInstance = new ServiceProvider();
                }
            }
        }
        return sInstance;
    }

    private ServiceProvider() {
        File cacheFile=new File(Constants.PATH_CACHE);
        Cache cache=new Cache(cacheFile,1024*1024*50);
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_TIME_OUT,TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .addNetworkInterceptor(new StethoInterceptor())
                .addNetworkInterceptor(new CacheInterceptor())
                .addNetworkInterceptor(new ReadCookieInterceptor())
                .addNetworkInterceptor(new SaveCookieInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(cache)
                .cookieJar(new PersistentCookieJar(new SetCookieCache(),new SharedPrefsCookiePersistor(App.getApp())))
                .build();
    }

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(mOkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public <T> T provide(Class<T> apiClass) {
        T api = null;
        try {
            api = (T) mApiMap.get(apiClass.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (api == null) {
                api = getRetrofit().create(apiClass);
                mApiMap.put(apiClass.getName(), api);
            }
        }
        return api;
    }


}
