package com.feiyang.wanandroid.ui.login.vm;

import android.app.Application;

import com.feiyang.wanandroid.ui.login.model.LoginRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/22 1:24 PM<br>
 * Desc: <br>
 */
public class LoginViewModel extends AndroidViewModel {
    private LoginRepository mRepository;

    private CompositeDisposable mDisposable;

    public MutableLiveData<Boolean>loading=new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mDisposable = new CompositeDisposable();
        mRepository = LoginRepository.getInstance();
    }


    public void getBannerList() {
        Disposable subscribe = mRepository.getBannerList()
                                          .doOnSubscribe(disposable -> loading.postValue(true))
                                          .doOnTerminate(() -> loading.postValue(false))
                                          .subscribe(bannerData -> {

                                          });
        mDisposable.add(subscribe);
    }

    public void getArticleList(int pageNo) {
        Disposable subscribe = mRepository.getBannerList()
                                          .doOnSubscribe(disposable -> {

                                          })
                                          .subscribe(bannerData -> {

                                          });
        mDisposable.add(subscribe);
    }

    public void dispose() {
        mDisposable.dispose();
    }

}
