package com.feiyang.wanandroid.ui.main.model;

import com.feiyang.wanandroid.core.net.ApiService;
import com.feiyang.wanandroid.core.net.NetworkObserver;
import com.feiyang.wanandroid.core.net.ServiceProvider;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;
import com.feiyang.wanandroid.ui.main.model.bean.BannerData;
import com.feiyang.wanandroid.ui.main.model.bean.HotWordsData;
import com.feiyang.wanandroid.ui.main.model.bean.KnowledgeHierarchyData;
import com.feiyang.wanandroid.ui.main.model.bean.NaviData;
import com.feiyang.wanandroid.ui.main.model.bean.ProjectCategoryData;
import com.feiyang.wanandroid.ui.main.model.bean.SiteData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/13 11:55 AM<br>
 * Desc: model层的任务交给Repository来做,ViewModel只负责连接View(activity,fragment)和Model(repository)
 * 我们可以在Repository中从本地或者网络获取数据,View和ViewModel不需要关心数据从哪里获取
 * <br>
 */
@SuppressWarnings("JavaDoc")
public class MainRepository {
    private static volatile MainRepository INSTANCE;

    private ApiService mRemote;

    private MainRepository() {
        mRemote = ServiceProvider.getInstance().provide(ApiService.class);
    }

    public static MainRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (MainRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MainRepository();
                }
            }
        }
        return INSTANCE;
    }

    public Observable<ArticlesData> getArticleList(int pageNo) {
        return NetworkObserver.on(mRemote.getArticleList(pageNo));
    }

    /**
     * 获取banner的展示数据
     *
     * @return
     */
    public Observable<List<BannerData>> getBannerList() {
        return NetworkObserver.on(mRemote.getBannerList());
    }

    /**
     * 知识体系列表
     *
     * @return
     */
    public Observable<List<KnowledgeHierarchyData>> getKnowledgeHierarchyList() {
        return NetworkObserver.on(mRemote.getKnowledgeHierarchyList());
    }



    /**
     * 常用网站
     *
     * @return
     */
    public Observable<List<SiteData>> getSiteList() {
        return NetworkObserver.on(mRemote.getSiteList());
    }

    /**
     * 搜索热词
     * 目前搜索最多的关键词。
     *
     * @return
     */
    public Observable<List<HotWordsData>> getHotWordsList() {
        return NetworkObserver.on(mRemote.getHotWordsList());
    }

    /**
     * 导航数据
     *
     * @return
     */
    public Observable<List<NaviData>> getNaviList() {
        return NetworkObserver.on(mRemote.getNaviList());
    }

    /**
     * 项目为包含一个分类，该接口返回整个分类。
     *
     * @return
     */
    public Observable<List<ProjectCategoryData>> getProjectCateList() {
        return NetworkObserver.on(mRemote.getProjectCateList());
    }

    /**
     * http://www.wanandroid.com/project/list/1/json?cid=294
     * 某一个分类下项目列表数据，分页展示
     *
     * @param pageNo
     * @param cid    cid 分类的id，上面项目分类接口
     * @return
     */
    public Observable<ArticlesData> getProjectArticles(int pageNo, int cid) {
        return NetworkObserver.on(mRemote.getProjectArticles(pageNo, cid));
    }

}
