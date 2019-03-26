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
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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

    public static final String BASE_URLS = "https://www.wanandroid.com/";

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
        File             cacheFile        = new File(Constants.PATH_CACHE);
        Cache            cache            = new Cache(cacheFile, 1024 * 1024 * 50);
        X509TrustManager x509TrustManager = initX509TrustManager();
        SSLSocketFactory sslSocketFactory;
        try {
            sslSocketFactory = initSSLFactory(x509TrustManager);
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS)
                    .readTimeout(DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS)
                    .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS)
                    .retryOnConnectionFailure(true)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .addNetworkInterceptor(new CacheInterceptor())
                    .addNetworkInterceptor(new ReadCookieInterceptor())
                    .addNetworkInterceptor(new SaveCookieInterceptor())
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .cache(cache)
                    .sslSocketFactory(sslSocketFactory, x509TrustManager)
                    .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getApp())))
                    .build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

    }

    public Retrofit getRetrofit() {
        return getRetrofit(BASE_URL);
    }

    public Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
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

    private SSLSocketFactory initSSLFactory(X509TrustManager trustManager) throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
        return sslContext.getSocketFactory();
    }

    private X509TrustManager initX509TrustManager() {
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        return trustManager;
    }


}
