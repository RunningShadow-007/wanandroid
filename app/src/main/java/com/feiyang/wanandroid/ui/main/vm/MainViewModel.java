package com.feiyang.wanandroid.ui.main.vm;

import android.app.Application;
import android.util.Log;

import com.feiyang.wanandroid.base.BaseViewModel;
import com.feiyang.wanandroid.ui.main.model.MainRepository;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;
import com.feiyang.wanandroid.ui.main.model.bean.BannerData;
import com.feiyang.wanandroid.ui.main.model.bean.KnowledgeHierarchyData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/13 10:50 AM<br>
 * Desc: <br>
 */
public class MainViewModel extends BaseViewModel {
    public static final String TAG = MainViewModel.class.getSimpleName();

    private MainRepository mRepository;

    public MutableLiveData<List<ArticlesData.ArticleBean>> articleList = new MutableLiveData<>();

    public MutableLiveData<List<BannerData>> bannerList = new MutableLiveData<>();

    public MutableLiveData<Void> isLoadFailed = new MutableLiveData<>();

    public MutableLiveData<Integer> pageCount = new MutableLiveData<>();

    public MutableLiveData<List<KnowledgeHierarchyData>> hierarchyList = new MutableLiveData<>();//知识体系列表

    public MutableLiveData<List<ArticlesData.ArticleBean>> hierarchyArticleList = new MutableLiveData<>();//知识体系下文章列表

    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepository = MainRepository.getInstance();
    }

    public void getBannerList() {
        Disposable subscribe = mRepository.getBannerList()
                                          .subscribe(bannerData -> bannerList.postValue(bannerData));
        disposable.add(subscribe);
    }

    /**
     * 获取时候偶也文章列表
     *
     * @param pageNo 页码
     */
    public void getArticleList(int pageNo) {
        Disposable subscribe = mRepository.getArticleList(pageNo)
                                          .doOnSubscribe(disposable -> loading.postValue(true))
                                          .doOnTerminate(() -> loading.postValue(false))
                                          .subscribe(articlesData -> {
                                              pageCount.postValue(articlesData.getPageCount());
                                              articleList.postValue(articlesData.getDatas());
                                          }, throwable -> isLoadFailed.postValue(null));
        disposable.add(subscribe);
    }

    /**
     * 知识体系列表
     *
     * @return
     */
    public void getKnowledgeHierarchyList() {
        Disposable subscribe = mRepository.getKnowledgeHierarchyList()
                                          .doOnSubscribe(disposable -> loading.postValue(true))
                                          .doOnTerminate(() -> loading.postValue(false))
                                          .subscribe(knowledgeHierarchyData -> {
                                              hierarchyList.postValue(knowledgeHierarchyData);
                                          }, throwable -> Log.e(TAG, "getKnowledgeHierarchyList: ", throwable));
        disposable.add(subscribe);
    }

    /**
     * 知识体系下的文章
     *
     * @param pageNo
     * @param cid    分类的id，上述二级目录的id
     * @return
     */
    public void getKnowledgeArticleList(int pageNo, int cid) {
        Disposable subscribe = mRepository.getKnowledgeArticleList(pageNo, cid)
                                          .doOnSubscribe(disposable -> loading.postValue(true))
                                          .doOnTerminate(() -> loading.postValue(false))
                                          .subscribe(articlesData-> {
                                                  hierarchyArticleList.postValue(articlesData.getDatas());
                                          }, throwable -> Log.e(TAG, "getKnowledgeArticleList: ", throwable));
        disposable.add(subscribe);
    }
}
