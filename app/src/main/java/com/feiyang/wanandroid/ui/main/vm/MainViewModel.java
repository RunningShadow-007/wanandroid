package com.feiyang.wanandroid.ui.main.vm;

import android.app.Application;
import android.util.Log;

import com.feiyang.wanandroid.ui.login.vm.LoginViewModel;
import com.feiyang.wanandroid.ui.main.model.MainRepository;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;
import com.feiyang.wanandroid.ui.main.model.bean.BannerData;
import com.feiyang.wanandroid.ui.main.model.bean.KnowledgeHierarchyData;
import com.feiyang.wanandroid.ui.main.model.bean.NaviData;
import com.feiyang.wanandroid.ui.main.model.bean.ProjectCategoryData;

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
@SuppressWarnings("JavaDoc")
public class MainViewModel extends LoginViewModel {
    public static final String TAG = MainViewModel.class.getSimpleName();

    private MainRepository mRepository;

    public MutableLiveData<List<ArticlesData.ArticleBean>> articleList = new MutableLiveData<>();

    public MutableLiveData<List<BannerData>> bannerList = new MutableLiveData<>();

    public MutableLiveData<Void> isLoadFailed = new MutableLiveData<>();

    public MutableLiveData<Integer> pageCount = new MutableLiveData<>();

    public MutableLiveData<List<KnowledgeHierarchyData>> hierarchyList = new MutableLiveData<>();//知识体系列表


    public MutableLiveData<List<NaviData>> navList = new MutableLiveData<>();//全部导航数据

    public MutableLiveData<List<ProjectCategoryData>> projectCateList = new MutableLiveData<>();//项目分类列表

    public MutableLiveData<ArticlesData> projectArticles = new MutableLiveData<>();//项目文章列表

    public MutableLiveData<ArticlesData> collectDatas = new MutableLiveData<>();//收藏列表数据

    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepository = MainRepository.getInstance();
    }

    public void getBannerList() {
        Disposable subscribe = mRepository.getBannerList()
                                          .subscribe(bannerData -> {
                                              bannerList.postValue(bannerData.orElse(null));
                                          });
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
                                              if (articlesData.isPresent()) {
                                                  pageCount.postValue(articlesData.get().getPageCount());
                                                  articleList.postValue(articlesData.get().getDatas());
                                              }
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
                                              hierarchyList.postValue(knowledgeHierarchyData.orElse(null));
                                          }, throwable -> Log.e(TAG, "getKnowledgeHierarchyList: ", throwable));
        disposable.add(subscribe);
    }


    /**
     * 导航数据
     *
     * @return
     */
    public void getNaviList() {
        Disposable subscribe = mRepository.getNaviList()
                                          .doOnSubscribe(disposable -> loading.postValue(true))
                                          .doOnTerminate(() -> loading.postValue(false))
                                          .subscribe(naviData -> {
                                              navList.postValue(naviData.orElse(null));
                                          }, throwable -> Log.e(TAG, "getNaviList: ", throwable));
        disposable.add(subscribe);
    }

    public void clearSelected(List<NaviData> list) {
        for (NaviData data : list) {
            if (data.isSelected) {
                data.isSelected = false;
                break;
            }
        }
    }

    public void getProjectCateList() {
        Disposable subscribe = mRepository.getProjectCateList()
                                          .doOnSubscribe(disposable -> loading.postValue(true))
                                          .doOnTerminate(() -> loading.postValue(false))
                                          .subscribe(data -> {
                                              projectCateList.postValue(data.orElse(null));
                                          }, throwable -> Log.e(TAG, "getProjectCateList", throwable));
        disposable.add(subscribe);
    }

    public void getProjectArticles(int pageNo, int cid) {
        Disposable subscribe = mRepository.getProjectArticles(pageNo, cid)
                                          .doOnSubscribe(disposable -> loading.postValue(true))
                                          .doOnTerminate(() -> loading.postValue(false))
                                          .subscribe(data -> {
                                              projectArticles.postValue(data.orElse(null));
                                          }, throwable -> Log.e(TAG, "getProjectCateList", throwable));
        disposable.add(subscribe);
    }

    public void getCollectionList(int pageNo) {
        Disposable subscribe = mRepository.getCollectionList(pageNo)
                                          .doOnSubscribe(disposable -> loading.postValue(true))
                                          .doOnTerminate(() -> loading.postValue(false))
                                          .subscribe(data -> {
                                              collectDatas.postValue(data.orElse(null));
                                          }, throwable -> Log.e(TAG, "getCollectionList: ", throwable));
        disposable.add(subscribe);

    }

}
