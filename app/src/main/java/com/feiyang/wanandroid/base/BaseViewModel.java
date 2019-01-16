package com.feiyang.wanandroid.base;

import android.app.Application;
import android.text.TextUtils;

import com.feiyang.wanandroid.App;
import com.feiyang.wanandroid.core.net.ApiService;
import com.feiyang.wanandroid.core.net.NetworkObserver;
import com.feiyang.wanandroid.core.net.ServiceProvider;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.core.content.PermissionChecker;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/12 7:49 PM<br>
 * Desc: <br>
 */
public class BaseViewModel extends AndroidViewModel {
    private static final String TAG=BaseViewModel.class.getSimpleName();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public MutableLiveData<String> toast = new MutableLiveData<>();

    public MutableLiveData<ArticlesData.ArticleBean> collectData =new MutableLiveData<>();

    protected CompositeDisposable disposable;

    public MutableLiveData<Map<String, Integer>> checkPermissionResult = new MutableLiveData<>();

    public BaseViewModel(@NonNull Application application) {
        super(application);
        disposable = new CompositeDisposable();
    }

    public void dispose() {
        disposable.dispose();
    }

    public void verifyPermission(String permission) {
        Map<String, Integer> result = new HashMap<>();
        result.put(permission, PermissionChecker.checkSelfPermission(App.getApp(), permission));
        checkPermissionResult.postValue(result);
    }

    public void collectArticle(@NonNull ArticlesData.ArticleBean bean) {
        ApiService service = ServiceProvider.getInstance().provide(ApiService.class);
        if (!TextUtils.isEmpty(bean.getLink())) {
            boolean isInner = bean.getLink().contains("wanandroid.com");
            if (isInner) {
                //收藏站内文章
                Disposable subscribe = NetworkObserver.on(service.collectInnerArticle(bean.getId()))
                                                      .doOnSubscribe(disposable -> loading.postValue(true))
                                                      .doOnTerminate(() -> loading.postValue(false))
                                                      .subscribe(o -> {
                                                          collectData.postValue(bean);
                                                          toast.postValue("收藏成功!");
                                                      }, throwable -> toast.postValue(throwable.getMessage()));
                disposable.add(subscribe);
            } else {
                //收藏站外文章
                ApiService.CollectOuterParam param = new ApiService.CollectOuterParam();
                param.title = bean.getTitle();
                param.author = bean.getAuthor();
                param.link = bean.getLink();
                Disposable subscribe = NetworkObserver.on(service.collectOuterArticle(param))
                                                      .doOnSubscribe(disposable -> loading.postValue(true))
                                                      .doOnTerminate(() -> loading.postValue(false))
                                                      .subscribe(o -> {
                                                          collectData.postValue(bean);
                                                          toast.postValue("收藏成功!");
                                                      }, throwable -> toast.postValue(throwable.getMessage()));
                disposable.add(subscribe);
            }

        }

    }


}
