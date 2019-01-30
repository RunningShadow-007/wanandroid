package com.feiyang.wanandroid.core.net;

import com.feiyang.wanandroid.base.BaseResponse;
import com.feiyang.wanandroid.ui.login.model.bean.LoginData;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;
import com.feiyang.wanandroid.ui.main.model.bean.BannerData;
import com.feiyang.wanandroid.ui.main.model.bean.CollectionData;
import com.feiyang.wanandroid.ui.main.model.bean.HotWordsData;
import com.feiyang.wanandroid.ui.main.model.bean.KnowledgeHierarchyData;
import com.feiyang.wanandroid.ui.main.model.bean.NaviData;
import com.feiyang.wanandroid.ui.main.model.bean.ProjectCategoryData;
import com.feiyang.wanandroid.ui.main.model.bean.SiteData;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    /**
     * 常用网站
     *
     * @return
     */
    @GET("friend/json")
    Observable<BaseResponse<List<SiteData>>> getSiteList();

    /**
     * 搜索热词
     * 目前搜索最多的关键词。
     *
     * @return
     */
    @GET("hotkey/json")
    Observable<BaseResponse<List<HotWordsData>>> getHotWordsList();

    /**
     * 体系数据
     *
     * @return
     */
    @GET("tree/json")
    Observable<BaseResponse<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyList();

    /**
     * 知识体系下的文章
     *
     * @param pageNo
     * @param cid    分类的id，上述二级目录的id
     * @return
     */
    @GET("article/list/{pageNo}/json")
    Observable<BaseResponse<ArticlesData>> getKnowledgeArticleList(@Path("pageNo") int pageNo, @Query("cid") int cid);

    /**
     * 导航数据
     *
     * @return
     */
    @GET("navi/json")
    Observable<BaseResponse<List<NaviData>>> getNaviList();

    /**
     * 项目为包含一个分类，该接口返回整个分类。
     *
     * @return
     */
    @GET("project/tree/json")
    Observable<BaseResponse<List<ProjectCategoryData>>> getProjectCateList();

    /**
     * http://www.wanandroid.com/project/list/1/json?cid=294
     * 某一个分类下项目列表数据，分页展示
     *
     * @param pageNo
     * @param cid    cid 分类的id，上面项目分类接口
     * @return
     */
    @GET("project/list/{pageNo}/json")
    Observable<BaseResponse<ArticlesData>> getProjectArticles(@Path("pageNo") int pageNo, @Query("cid") int cid);

    /**
     * 登录接口
     *
     * @param param (username,password)
     * @return
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseResponse<LoginData>> login(@FieldMap Map<String, String> param);


    /**
     * 注册接口
     *
     * @param param (username,password,repassword)
     * @return
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<BaseResponse<LoginData>> regist(@FieldMap Map<String, String> param);


    /**
     * 退出
     *
     * @return
     */
    @GET("user/logout/json")
    Observable<BaseResponse<Object>> logout();

    /**
     * 获取收藏列表
     *
     * @param pageNo
     * @return
     */
    @GET("lg/collect/list/{pageNo}/json")
    Observable<BaseResponse<CollectionData>> getCollectionList(@Path("pageNo") int pageNo);

    /**
     * 收藏站内文章
     *
     * @param id 文章id
     * @return
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseResponse<Object>> collectInnerArticle(@Path("id") long id);

    /**
     * 收藏站外文章
     *
     * @return
     */
    @POST("lg/collect/add/json")
    @FormUrlEncoded
    Observable<BaseResponse<Object>> collectOuterArticle(@Field("title") String title, @Field("author") String author, @Field("link") String link);


    /**
     * 取消收藏(文章列表)
     *
     * @param id 文章
     * @return
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<BaseResponse<Object>> uncollect(@Path("id") long id);

    /**
     * 取消收藏(我的收藏页面（该页面包含自己录入的内容）)
     *
     * @param id
     * @param originId {originId 列表页下发，无则为-1}
     * @return
     */
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    Observable<BaseResponse<Object>> uncollect(@Path("id") long id, @Field("originId") long originId);


    /**
     * 收藏网站列表
     *
     * @return
     */
    @POST("lg/collect/usertools/json")
    Observable<BaseResponse<CollectionData>> getCollectionSite();

    /**
     * 收藏网址
     *
     * @return
     */
    @POST("lg/collect/addtool/json")
    @FormUrlEncoded
    Observable<BaseResponse<Object>> collectSite(@Field("name") String name, @Field("link") String link);



    /**
     * 删除收藏网站
     *
     * @return
     */
    @POST("lg/collect/deletetool/json")
    @FormUrlEncoded
    Observable<BaseResponse<Object>> deleteCollectSite(@Field("id") String id);


    /**
     * 搜索
     *
     * @param pageNo
     * @param k
     * @return
     */
    @POST("article/query/{pageNo}/json")
    @FormUrlEncoded
    Observable<BaseResponse<ArticlesData>> queryByWords(@Path("pageNo") int pageNo, @Field("k") String k);



}
