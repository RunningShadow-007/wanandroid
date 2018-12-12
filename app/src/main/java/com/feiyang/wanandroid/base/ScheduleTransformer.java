package com.feiyang.wanandroid.base;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/23 4:10 PM<br>
 * Desc: <br>
 */
public class ScheduleTransformer<T> implements ObservableTransformer<T, T> {
    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread());
    }
}
