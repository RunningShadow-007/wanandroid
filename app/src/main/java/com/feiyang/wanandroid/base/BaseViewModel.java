package com.feiyang.wanandroid.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/12 7:49 PM<br>
 * Desc: <br>
 */
public class BaseViewModel extends AndroidViewModel {
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public MutableLiveData<String> toast = new MutableLiveData<>();

    protected CompositeDisposable disposable;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        disposable = new CompositeDisposable();
    }

    public void dispose() {
        disposable.dispose();
    }
}
