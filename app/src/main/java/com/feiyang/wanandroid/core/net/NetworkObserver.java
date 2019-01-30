package com.feiyang.wanandroid.core.net;

import android.annotation.SuppressLint;
import android.util.Log;

import com.feiyang.wanandroid.App;
import com.feiyang.wanandroid.base.BaseResponse;
import com.feiyang.wanandroid.core.util.Optional;
import com.feiyang.wanandroid.core.util.UiUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/23 4:21 PM<br>
 * Desc:  对网络请求的封装，统一处理请求发生异常的情况和请求发生错误的情况
 * <br>
 */
public class NetworkObserver {
    private static final String TAG = NetworkObserver.class.getSimpleName();

    private static final int ERROR = -1;//失败

    private static final int ERROR_TOKEN = -1001;//登录失效

    private static final int SUCCESS = 0;//成功

    @IntDef({ERROR, ERROR_TOKEN, SUCCESS})
    @Retention(RetentionPolicy.SOURCE)
    @interface RESPONSE_CODE {

    }

    public static <T> Observable<Optional<T>> on(Observable<BaseResponse<T>> observable) {
        return observable.subscribeOn(Schedulers.io())
                         .flatMap(new ResponseErrorFunction<>());
    }

    //如果是在service请求数据,调用该方法
    public static <T> Observable<Optional<T>> onService(Observable<BaseResponse<T>> observable) {
        return observable.subscribeOn(Schedulers.io())
                         .flatMap(new ResponseErrorOnServiceFunction<>());
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    private static class ResponseErrorOnServiceFunction<T> implements Function<BaseResponse<T>, Observable<Optional<T>>> {

        @Override
        public Observable<Optional<T>> apply(BaseResponse<T> tBaseResponse) {
            if (tBaseResponse == null) {
                Observable.just(1)
                          .observeOn(AndroidSchedulers.mainThread())
                          .subscribe(integer -> {
                              Log.e(TAG, "response=null ");
                              UiUtils.showShortToast("连接失败");
                          });
                throw new RuntimeException("连接失败");
            } else {
                int errorCode = tBaseResponse.getErrorCode();
                if (errorCode != SUCCESS) {
                    if (ERROR_TOKEN == tBaseResponse.getErrorCode()) {
                        App.getApp().goLogin(true);
                    } else {
                        Observable.just(1)
                                  .observeOn(AndroidSchedulers.mainThread())
                                  .subscribe(integer -> {
                                      Log.e(TAG, "response=null ");
                                      UiUtils.showShortToast(tBaseResponse.getErrorMsg());
                                  });
                    }
                    throw new RuntimeException(tBaseResponse.getErrorMsg() + "");
                } else {
                    T data = tBaseResponse.getData();
                    return Observable.just(Optional.ofNullable(data));
                }
            }
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    private static class ResponseErrorFunction<T> implements Function<BaseResponse<T>, Observable<Optional<T>>> {

        @Override
        public Observable<Optional<T>> apply(BaseResponse<T> tBaseResponse) {
            if (tBaseResponse == null) {
                Observable.just(1)
                          .observeOn(AndroidSchedulers.mainThread())
                          .subscribe(integer -> {
                              Log.e(TAG, "response=null ");
                              UiUtils.showShortToast("连接失败");
                          });
                throw new RuntimeException("连接失败");
            } else {
                int errorCode = tBaseResponse.getErrorCode();
                if (errorCode != SUCCESS) {
                    if (ERROR_TOKEN == tBaseResponse.getErrorCode()) {
                        App.getApp().goLogin(false);
                    } else {
                        Observable.just(1)
                                  .observeOn(AndroidSchedulers.mainThread())
                                  .subscribe(integer -> {
                                      Log.e(TAG, String.format("error_code is %s,error_msg is %s", errorCode, tBaseResponse.getErrorMsg()));
                                      UiUtils.showShortToast(tBaseResponse.getErrorMsg());
                                  });
                    }
                    throw new RuntimeException(tBaseResponse.getErrorMsg() + "");
                } else {
                    T data = tBaseResponse.getData();
                    return Observable.just(Optional.ofNullable(data));
                }
            }
        }
    }
}
