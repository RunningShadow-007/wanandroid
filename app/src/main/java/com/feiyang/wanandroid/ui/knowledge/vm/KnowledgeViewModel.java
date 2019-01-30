package com.feiyang.wanandroid.ui.knowledge.vm;

import android.app.Application;
import android.util.Log;

import com.feiyang.wanandroid.base.BaseViewModel;
import com.feiyang.wanandroid.ui.knowledge.model.KnowledgeRepository;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/28 4:42 PM<br>
 * Desc: <br>
 */
public class KnowledgeViewModel extends BaseViewModel {
    private static final String TAG = KnowledgeViewModel.class.getSimpleName();

    private KnowledgeRepository mRepository;

    public MutableLiveData<ArticlesData> hierarchyArticleList = new MutableLiveData<>();//知识体系下文章列表

    public MutableLiveData<Void> isLoadFailed = new MutableLiveData<>();

    public KnowledgeViewModel(@NonNull Application application) {
        super(application);
        mRepository = KnowledgeRepository.getInstance();
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
                                          .subscribe(articlesData -> {
                                              hierarchyArticleList.postValue(articlesData.orElse(null));
                                          }, throwable -> {
                                              Log.e(TAG, "getKnowledgeArticleList: ", throwable);
                                              isLoadFailed.postValue(null);
                                          });
        disposable.add(subscribe);
    }
}
