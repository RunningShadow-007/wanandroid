package com.feiyang.wanandroid.ui.main.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/25 11:27 AM<br>
 * Desc: 收藏列表数据<br>
 */
public class CollectionData implements Parcelable {

    /**
     * curPage : 1
     * datas : [{"author":"goldze","chapterId":385,"chapterName":"架构","courseId":13,"desc":"基于MVVMHabit框架，结合阿里ARouter打造的一套Android-Databinding组件化开发方案。","envelopePic":"http://wanandroid.com/blogimgs/ebeabcb0-60c3-4d8b-a4e9-b1c0a620ac04.png","id":39512,"link":"http://www.wanandroid.com/blog/show/2461","niceDate":"29分钟前","origin":"","originId":7700,"publishTime":1545706420000,"title":"Android MVVM组件化架构方案","userId":13096,"visible":0,"zan":0}]
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 20
     * total : 1
     */

    private int curPage;

    private int             offset;

    private boolean         over;

    private int             pageCount;

    private int             size;

    private int             total;

    private List<DatasBean> datas;

    protected CollectionData(Parcel in) {
        curPage = in.readInt();
        offset = in.readInt();
        over = in.readByte() != 0;
        pageCount = in.readInt();
        size = in.readInt();
        total = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(curPage);
        dest.writeInt(offset);
        dest.writeByte((byte) (over ? 1 : 0));
        dest.writeInt(pageCount);
        dest.writeInt(size);
        dest.writeInt(total);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CollectionData> CREATOR = new Creator<CollectionData>() {
        @Override
        public CollectionData createFromParcel(Parcel in) {
            return new CollectionData(in);
        }

        @Override
        public CollectionData[] newArray(int size) {
            return new CollectionData[size];
        }
    };

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * author : goldze
         * chapterId : 385
         * chapterName : 架构
         * courseId : 13
         * desc : 基于MVVMHabit框架，结合阿里ARouter打造的一套Android-Databinding组件化开发方案。
         * envelopePic : http://wanandroid.com/blogimgs/ebeabcb0-60c3-4d8b-a4e9-b1c0a620ac04.png
         * id : 39512
         * link : http://www.wanandroid.com/blog/show/2461
         * niceDate : 29分钟前
         * origin :
         * originId : 7700
         * publishTime : 1545706420000
         * title : Android MVVM组件化架构方案
         * userId : 13096
         * visible : 0
         * zan : 0
         */

        private String author;

        private int    chapterId;

        private String chapterName;

        private int    courseId;

        private String desc;

        private String envelopePic;

        private int    id;

        private String link;

        private String niceDate;

        private String origin;

        private int    originId;

        private long   publishTime;

        private String title;

        private int    userId;

        private int    visible;

        private int    zan;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public int getOriginId() {
            return originId;
        }

        public void setOriginId(int originId) {
            this.originId = originId;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }
    }
}
