package com.feiyang.wanandroid.ui.main.vm;

import android.app.Application;

import com.feiyang.wanandroid.base.BaseViewModel;
import com.feiyang.wanandroid.ui.main.model.MainRepository;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;
import com.feiyang.wanandroid.ui.main.model.bean.BannerData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/13 10:50 AM<br>
 * Desc: <br>
 */
public class MainViewModel extends BaseViewModel {
    private MainRepository mRepository;

    public MutableLiveData<List<ArticlesData.ArticleBean>> articleList = new MutableLiveData<>();

    public MutableLiveData<List<BannerData>> bannerList = new MutableLiveData<>();

    public MutableLiveData<Void> isLoadFailed = new MutableLiveData<>();

    public MutableLiveData<Integer> pageCount = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepository = MainRepository.getInstance();
    }

    public void getBannerList() {
        Disposable subscribe = mRepository.getBannerList()
                                          .subscribe(bannerData -> bannerList.postValue(bannerData));
        disposable.add(subscribe);
    }

    public void getArticleList(int pageNo) {
        Disposable subscribe = mRepository.getArticleList(pageNo)
                                          .doOnSubscribe(disposable -> {
                                              loading.postValue(true);
                                          })
                                          .doOnTerminate(() -> loading.postValue(false))
                                          .subscribe(articlesData -> {
                                              pageCount.postValue(articlesData.getPageCount());
                                              articleList.postValue(articlesData.getDatas());
                                          }, new Consumer<Throwable>() {
                                              @Override
                                              public void accept(Throwable throwable) throws Exception {
                                                  isLoadFailed.postValue(null);
                                              }
                                          });
        disposable.add(subscribe);
    }
}
