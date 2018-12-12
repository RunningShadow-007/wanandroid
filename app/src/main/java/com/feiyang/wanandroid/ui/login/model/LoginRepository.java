package com.feiyang.wanandroid.ui.login.model;

import com.feiyang.wanandroid.core.net.ApiService;
import com.feiyang.wanandroid.core.net.NetworkObserver;
import com.feiyang.wanandroid.core.net.ServiceProvider;
import com.feiyang.wanandroid.ui.login.model.bean.ArticlesData;
import com.feiyang.wanandroid.ui.login.model.bean.BannerData;

import java.util.List;

import io.reactivex.Observable;


/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/23 4:04 PM<br>
 * Desc: <br>
 */
public class LoginRepository {
    private static volatile LoginRepository INSTANCE;

    private ApiService mRemote;

    private LoginRepository() {
        mRemote = ServiceProvider.getInstance().provide(ApiService.class);
    }

    public static LoginRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (LoginRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LoginRepository();
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
