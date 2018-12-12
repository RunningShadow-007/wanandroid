package com.feiyang.wanandroid.core.net;

import com.feiyang.wanandroid.base.BaseResponse;
import com.feiyang.wanandroid.ui.login.model.bean.ArticlesData;
import com.feiyang.wanandroid.ui.login.model.bean.BannerData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/21 3:15 PM<br>
 * Desc: <br>
 */
@SuppressWarnings("JavaDoc")
public interface ApiService {
    /**
     * 首页文章列表
     *
     * @param pageNo
     * @return
     */
    @GET("article/list/{pageNo}/json")
    Observable<BaseResponse<ArticlesData>> getArticleList(@Path("pageNo") int pageNo);

    /**
     * 首页banner
     *
     * @return
     */
    @GET("banner/json")
    Observable<BaseResponse<List<BannerData>>> getBannerList();



}
