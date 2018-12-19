package com.feiyang.wanandroid.ui.main.model;

import com.feiyang.wanandroid.core.net.ApiService;
import com.feiyang.wanandroid.core.net.NetworkObserver;
import com.feiyang.wanandroid.core.net.ServiceProvider;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;
import com.feiyang.wanandroid.ui.main.model.bean.BannerData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/13 11:55 AM<br>
 * Desc: <br>
 */
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

    public Observable<List<BannerData>> getBannerList() {
        return NetworkObserver.on(mRemote.getBannerList());
    }

}
