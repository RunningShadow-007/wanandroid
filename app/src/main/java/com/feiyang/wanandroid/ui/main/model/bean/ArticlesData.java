package com.feiyang.wanandroid.ui.main.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.feiyang.wanandroid.base.BaseItem;
import com.feiyang.wanandroid.core.constants.Constants;

import java.util.List;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/23 3:25 PM<br>
 * Desc: <br>
 */
public class ArticlesData implements Parcelable {

    /**
     * curPage : 2
     * datas : [{"apkLink":"","author":"SupKing_a520","chapterId":171,"chapterName":"binder","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7631,"link":"https://www.jianshu.com/p/c1daf5653a47","niceDate":"2018-12-07","origin":"","projectLink":"","publishTime":1544113884000,"superChapterId":171,"superChapterName":"framework","tags":[],"title":"Android：从源码角度来赏析Binder机制的优美","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":" Roll圈圈","chapterId":355,"chapterName":"他人收集","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7630,"link":"https://www.jianshu.com/p/2d1a3ba27e76","niceDate":"2018-12-07","origin":"","projectLink":"","publishTime":1544113860000,"superChapterId":349,"superChapterName":"开放API","tags":[],"title":"开放通用Api，总有你喜欢的","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"辰之猫","chapterId":60,"chapterName":"Android Studio相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7629,"link":"https://www.jianshu.com/p/e54db232df62","niceDate":"2018-12-07","origin":"","projectLink":"","publishTime":1544113821000,"superChapterId":60,"superChapterName":"开发环境","tags":[],"title":"让你明明白白的使用RecyclerView&mdash;&mdash;SnapHelper详解","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7633,"link":"https://mp.weixin.qq.com/s/cLhf-SSS3I5SkPEFd5Cxbw","niceDate":"2018-12-07","origin":"","projectLink":"","publishTime":1544112000000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"不到100行代码实现左右对齐TextView","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"code小生","chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7634,"link":"https://mp.weixin.qq.com/s/i1GfYke-9419uYsZgCMiJg","niceDate":"2018-12-07","origin":"","projectLink":"","publishTime":1544112000000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"对 Kotlin 与 Java 编程语言的思考","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"郭霖","chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7635,"link":"https://mp.weixin.qq.com/s/cBISvWo8TnvlJ3oSKJ544g","niceDate":"2018-12-07","origin":"","projectLink":"","publishTime":1544112000000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"30张图带你彻底理解红黑树","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"辰之猫","chapterId":100,"chapterName":"RecyclerView","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7627,"link":"https://www.jianshu.com/p/d1ab5f6be73f","niceDate":"2018-12-06","origin":"","projectLink":"","publishTime":1544111204000,"superChapterId":179,"superChapterName":"5.+高新技术","tags":[],"title":"Android组件化&mdash;&mdash;SXRecyclerView","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"NightFarmer","chapterId":375,"chapterName":"Flutter","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7626,"link":"https://www.jianshu.com/p/26b32dc2087e","niceDate":"2018-12-06","origin":"","projectLink":"","publishTime":1544110868000,"superChapterId":375,"superChapterName":"跨平台","tags":[],"title":"Android工程内嵌Flutter，跨平台的渐进式解决方案","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"mandypig","chapterId":99,"chapterName":"具体案例","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7590,"link":"https://www.jianshu.com/p/7241ed34346a","niceDate":"2018-12-06","origin":"","projectLink":"","publishTime":1544110372000,"superChapterId":94,"superChapterName":"自定义控件","tags":[],"title":"不到100行代码实现左右对齐TextView","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"gityuan","chapterId":78,"chapterName":"性能优化","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7622,"link":"http://gityuan.com/2016/01/02/memory-analysis-command/","niceDate":"2018-12-06","origin":"","projectLink":"","publishTime":1544093713000,"superChapterId":79,"superChapterName":"热门专题","tags":[],"title":"Android内存分析命令","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"看书的小蜗牛","chapterId":86,"chapterName":"图片处理","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7621,"link":"https://www.jianshu.com/p/d5714e8987f3","niceDate":"2018-12-06","origin":"","projectLink":"","publishTime":1544092914000,"superChapterId":87,"superChapterName":"图片加载","tags":[],"title":"Android Bitmap变迁与原理解析（4.x-8.x）","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7623,"link":"https://mp.weixin.qq.com/s/Vt__FGCcWftanYW5ndyXmA","niceDate":"2018-12-06","origin":"","projectLink":"","publishTime":1544025600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"RecyclerView库中被我们忽略的部分","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"code小生","chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7624,"link":"https://mp.weixin.qq.com/s/99eQ1_9wzYYByNTIrzOVfA","niceDate":"2018-12-06","origin":"","projectLink":"","publishTime":1544025600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"Android 设备唯一标识（多种实现方案）","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Jamin_正宗红罐辣酱","chapterId":252,"chapterName":"奇怪的Bug","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7617,"link":"https://www.jianshu.com/p/18950c9b0ec9","niceDate":"2018-12-05","origin":"","projectLink":"","publishTime":1544005813000,"superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"从Daemons到finalize timed out after 10 seconds","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Jamin_正宗红罐辣酱","chapterId":252,"chapterName":"奇怪的Bug","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7616,"link":"https://www.jianshu.com/p/84e7a99e8467","niceDate":"2018-12-05","origin":"","projectLink":"","publishTime":1544005797000,"superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"Android Crash的防护与追踪","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7618,"link":"https://mp.weixin.qq.com/s/WP8kUvcyHETlkDIdvGktdA","niceDate":"2018-12-05","origin":"","projectLink":"","publishTime":1543939200000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Android高斯模糊你所不知道的坑","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"code小生","chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7619,"link":"https://mp.weixin.qq.com/s/dq_wBsp1sUYA2M_oW9hv4Q","niceDate":"2018-12-05","origin":"","projectLink":"","publishTime":1543939200000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"OPPO Android 开发技术面总结","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"郭霖","chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7620,"link":"https://mp.weixin.qq.com/s/kcbEto2ljhhCSNknIWtbzA","niceDate":"2018-12-05","origin":"","projectLink":"","publishTime":1543939200000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"Android Monitor工具详解大全","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"zqljintu","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"网上有各种各样的免费API,在众里寻找后，最后发现何不将这些API集合在一个App中呢，鉴于个人能力有限，就选取了部分的API来创作。 感谢知乎日报的Api，提供了App中的日报板块。 感谢豆瓣的Api，提供了App中的电影板块。 感谢WanAndroid的Api，提供了开发板块。 感谢Gank.io的Api，提供了娱乐版块","envelopePic":"http://wanandroid.com/blogimgs/271a4c3f-8066-4038-b4e6-9e5756463403.png","fresh":false,"id":7612,"link":"http://www.wanandroid.com/blog/show/2445","niceDate":"2018-12-04","origin":"","projectLink":"https://github.com/zqljintu/Assembly-number","publishTime":1543928671000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"集结号//Assembly-number&mdash;&mdash;集合了多项内容的客户端","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"xujiaji","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"一个非常简洁清爽的清单工具，帮您轻松记录个人计划。本App的特色就是简洁，让人一目了然，在交互上让人体会到视觉上的舒适感。固定的4种清单分类在顶部可直接切换列表。API由WanAndroid提供","envelopePic":"http://wanandroid.com/blogimgs/238c157d-a397-4070-af0f-bf039061f653.png","fresh":false,"id":7611,"link":"http://www.wanandroid.com/blog/show/2444","niceDate":"2018-12-04","origin":"","projectLink":"https://github.com/xujiaji/Todo","publishTime":1543928609000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"玩清单-怎么这么好用这么好看！才...才不是因为自己开发的呢！o(*￣3￣)o ","type":0,"userId":-1,"visible":1,"zan":0}]
     * offset : 20
     * over : false
     * pageCount : 290
     * size : 20
     * total : 5798
     */

    private int curPage;

    private int offset;

    private boolean over;

    private int pageCount;

    private int size;

    private int total;

    private List<ArticleBean> datas;

    protected ArticlesData(Parcel in) {
        curPage = in.readInt();
        offset = in.readInt();
        over = in.readByte() != 0;
        pageCount = in.readInt();
        size = in.readInt();
        total = in.readInt();
        datas = in.createTypedArrayList(ArticleBean.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(curPage);
        dest.writeInt(offset);
        dest.writeByte((byte) (over ? 1 : 0));
        dest.writeInt(pageCount);
        dest.writeInt(size);
        dest.writeInt(total);
        dest.writeTypedList(datas);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ArticlesData> CREATOR = new Creator<ArticlesData>() {
        @Override
        public ArticlesData createFromParcel(Parcel in) {
            return new ArticlesData(in);
        }

        @Override
        public ArticlesData[] newArray(int size) {
            return new ArticlesData[size];
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

    public List<ArticleBean> getDatas() {
        return datas;
    }

    public void setDatas(List<ArticleBean> datas) {
        this.datas = datas;
    }

    public static class ArticleBean implements Parcelable, BaseItem {
        /**
         * apkLink :
         * author : SupKing_a520
         * chapterId : 171
         * chapterName : binder
         * collect : false
         * courseId : 13
         * desc :
         * envelopePic :
         * fresh : false
         * id : 7631
         * link : https://www.jianshu.com/p/c1daf5653a47
         * niceDate : 2018-12-07
         * origin :
         * projectLink :
         * publishTime : 1544113884000
         * superChapterId : 171
         * superChapterName : framework
         * tags : []
         * title : Android：从源码角度来赏析Binder机制的优美
         * type : 0
         * userId : -1
         * visible : 1
         * zan : 0
         */

        private String apkLink;

        private String author;

        private int chapterId;

        private String chapterName;

        private boolean collect;

        private int courseId;

        private String desc;

        private String envelopePic;

        private boolean fresh;

        private int id;

        private String link;

        private String niceDate;

        private String origin;

        private String projectLink;

        private long publishTime;

        private int superChapterId;

        private String superChapterName;

        private String title;

        private int type;

        private int userId;

        private int visible;

        private int zan;

        private List<TagBean> tags;

        protected ArticleBean(Parcel in) {
            apkLink = in.readString();
            author = in.readString();
            chapterId = in.readInt();
            chapterName = in.readString();
            collect = in.readByte() != 0;
            courseId = in.readInt();
            desc = in.readString();
            envelopePic = in.readString();
            fresh = in.readByte() != 0;
            id = in.readInt();
            link = in.readString();
            niceDate = in.readString();
            origin = in.readString();
            projectLink = in.readString();
            publishTime = in.readLong();
            superChapterId = in.readInt();
            superChapterName = in.readString();
            title = in.readString();
            type = in.readInt();
            userId = in.readInt();
            visible = in.readInt();
            zan = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(apkLink);
            dest.writeString(author);
            dest.writeInt(chapterId);
            dest.writeString(chapterName);
            dest.writeByte((byte) (collect ? 1 : 0));
            dest.writeInt(courseId);
            dest.writeString(desc);
            dest.writeString(envelopePic);
            dest.writeByte((byte) (fresh ? 1 : 0));
            dest.writeInt(id);
            dest.writeString(link);
            dest.writeString(niceDate);
            dest.writeString(origin);
            dest.writeString(projectLink);
            dest.writeLong(publishTime);
            dest.writeInt(superChapterId);
            dest.writeString(superChapterName);
            dest.writeString(title);
            dest.writeInt(type);
            dest.writeInt(userId);
            dest.writeInt(visible);
            dest.writeInt(zan);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<ArticleBean> CREATOR = new Creator<ArticleBean>() {
            @Override
            public ArticleBean createFromParcel(Parcel in) {
                return new ArticleBean(in);
            }

            @Override
            public ArticleBean[] newArray(int size) {
                return new ArticleBean[size];
            }
        };

        public String getApkLink() {
            return apkLink;
        }

        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }

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

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
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

        public boolean isFresh() {
            return fresh;
        }

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
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

        public String getProjectLink() {
            return projectLink;
        }

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getSuperChapterId() {
            return superChapterId;
        }

        public void setSuperChapterId(int superChapterId) {
            this.superChapterId = superChapterId;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public List<TagBean> getTags() {
            return tags;
        }

        public void setTags(List<TagBean> tags) {
            this.tags = tags;
        }

        @Override
        public int getItemType() {
            return Constants.TYPE_DATA;
        }

        @Override
        public ArticleBean getData() {
            return this;
        }

        public static class TagBean implements Parcelable {
            private String name;

            private String url;

            protected TagBean(Parcel in) {
                name = in.readString();
                url = in.readString();
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(name);
                dest.writeString(url);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            public static final Creator<TagBean> CREATOR = new Creator<TagBean>() {
                @Override
                public TagBean createFromParcel(Parcel in) {
                    return new TagBean(in);
                }

                @Override
                public TagBean[] newArray(int size) {
                    return new TagBean[size];
                }
            };

            public String getName() {
                return name == null ? "" : name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url == null ? "" : url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
