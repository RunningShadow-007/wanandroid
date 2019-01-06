package com.feiyang.wanandroid.ui.main.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/24 7:59 PM<br>
 * Desc: 导航数据<br>
 */
public class NaviData implements Parcelable {

    /**
     * articles : [{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1848,"link":"https://developers.google.cn/","niceDate":"2018-01-07","origin":"","projectLink":"","publishTime":1515322795000,"superChapterId":0,"superChapterName":"","tags":[],"title":"Google开发者","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1849,"link":"http://www.github.com/","niceDate":"2018-01-07","origin":"","projectLink":"","publishTime":1515322817000,"superChapterId":0,"superChapterName":"","tags":[],"title":"Github","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1850,"link":"https://stackoverflow.com/","niceDate":"2018-01-07","origin":"","projectLink":"","publishTime":1515322829000,"superChapterId":0,"superChapterName":"","tags":[],"title":"stackoverflow","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1851,"link":"https://juejin.im/","niceDate":"2018-01-07","origin":"","projectLink":"","publishTime":1515323408000,"superChapterId":0,"superChapterName":"","tags":[],"title":"掘金","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1852,"link":"https://www.csdn.net/","niceDate":"2018-01-07","origin":"","projectLink":"","publishTime":1515323423000,"superChapterId":0,"superChapterName":"","tags":[],"title":"CSDN","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1853,"link":"https://www.jianshu.com/","niceDate":"2018-01-07","origin":"","projectLink":"","publishTime":1515323438000,"superChapterId":0,"superChapterName":"","tags":[],"title":"简书","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1854,"link":"http://www.androidweekly.cn/","niceDate":"2018-01-07","origin":"","projectLink":"","publishTime":1515323568000,"superChapterId":0,"superChapterName":"","tags":[],"title":"开发技术周报","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1855,"link":"https://toutiao.io/","niceDate":"2018-01-07","origin":"","projectLink":"","publishTime":1515323607000,"superChapterId":0,"superChapterName":"","tags":[],"title":"开发者头条","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1856,"link":"https://segmentfault.com/t/android","niceDate":"2018-01-07","origin":"","projectLink":"","publishTime":1515323635000,"superChapterId":0,"superChapterName":"","tags":[],"title":"segmentfault","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1857,"link":"http://www.androiddevtools.cn/","niceDate":"2018-01-07","origin":"","projectLink":"","publishTime":1515323651000,"superChapterId":0,"superChapterName":"","tags":[],"title":"androiddevtools","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1858,"link":"https://developers.googleblog.cn/","niceDate":"2018-01-07","origin":"","projectLink":"","publishTime":1515323695000,"superChapterId":0,"superChapterName":"","tags":[],"title":"Google中文Blog","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"gank.io","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1859,"link":"http://gank.io/","niceDate":"2018-01-07","origin":"","projectLink":"","publishTime":1515323720000,"superChapterId":0,"superChapterName":"","tags":[],"title":"干货集中营","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1862,"link":"http://a.codekk.com/","niceDate":"2018-01-07","origin":"","projectLink":"","publishTime":1515324437000,"superChapterId":0,"superChapterName":"","tags":[],"title":"CodeKK","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1863,"link":"https://xiaozhuanlan.com/","niceDate":"2018-01-07","origin":"","projectLink":"","publishTime":1515324456000,"superChapterId":0,"superChapterName":"","tags":[],"title":"小专栏","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1864,"link":"http://www.wanandroid.com/article/list/0?cid=176","niceDate":"2018-01-07","origin":"","projectLink":"","publishTime":1515324541000,"superChapterId":0,"superChapterName":"","tags":[],"title":"国内大牛","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1865,"link":"https://github.com/android-cn/android-dev-com","niceDate":"2018-01-07","origin":"","projectLink":"","publishTime":1515324559000,"superChapterId":0,"superChapterName":"","tags":[],"title":"国外大牛","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1866,"link":"https://www.androidos.net.cn/sourcecode","niceDate":"2018-01-07","origin":"","projectLink":"","publishTime":1515324594000,"superChapterId":0,"superChapterName":"","tags":[],"title":"Android源码","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1867,"link":"http://design.1sters.com/","niceDate":"2018-01-07","origin":"","projectLink":"","publishTime":1515324880000,"superChapterId":0,"superChapterName":"","tags":[],"title":"Material Design 中文版","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1868,"link":"https://leetcode.com/","niceDate":"2018-01-07","origin":"","projectLink":"","publishTime":1515325010000,"superChapterId":0,"superChapterName":"","tags":[],"title":"leetcode","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2405,"link":"https://dl.google.com/dl/android/maven2/index.html","niceDate":"2018-02-25","origin":"","projectLink":"","publishTime":1519537704000,"superChapterId":0,"superChapterName":"","tags":[],"title":"google mvn仓库","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2406,"link":"http://jcenter.bintray.com/","niceDate":"2018-02-25","origin":"","projectLink":"","publishTime":1519537722000,"superChapterId":0,"superChapterName":"","tags":[],"title":"jcenter仓库","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"小编","chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3469,"link":"https://mvnrepository.com/artifact/com.google.code.gson/gson","niceDate":"2018-10-10","origin":"","projectLink":"","publishTime":1539139799000,"superChapterId":0,"superChapterName":"","tags":[],"title":"maven仓库","type":0,"userId":-1,"visible":1,"zan":0}]
     * cid : 272
     * name : 常用网站
     */

    private int cid;

    private String name;

    public boolean isSelected = false;

    private List<ArticlesData.ArticleBean> articles;

    protected NaviData(Parcel in) {
        cid = in.readInt();
        name = in.readString();
        articles = in.createTypedArrayList(ArticlesData.ArticleBean.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cid);
        dest.writeString(name);
        dest.writeTypedList(articles);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NaviData> CREATOR = new Creator<NaviData>() {
        @Override
        public NaviData createFromParcel(Parcel in) {
            return new NaviData(in);
        }

        @Override
        public NaviData[] newArray(int size) {
            return new NaviData[size];
        }
    };

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ArticlesData.ArticleBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesData.ArticleBean> articles) {
        this.articles = articles;
    }

}
