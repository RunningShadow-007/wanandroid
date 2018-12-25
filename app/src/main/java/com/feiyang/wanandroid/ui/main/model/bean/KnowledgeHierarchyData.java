package com.feiyang.wanandroid.ui.main.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/24 6:11 PM<br>
 * Desc:知识体系 <br>
 */
public class KnowledgeHierarchyData implements Parcelable {


    /**
     * children : [{"children":[],"courseId":13,"id":60,"name":"Android Studio相关","order":1000,"parentChapterId":150,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":169,"name":"gradle","order":1001,"parentChapterId":150,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":269,"name":"官方发布","order":1002,"parentChapterId":150,"userControlSetTop":false,"visible":1}]
     * courseId : 13
     * id : 150
     * name : 开发环境
     * order : 1
     * parentChapterId : 0
     * userControlSetTop : false
     * visible : 1
     */

    private int courseId;

    private int                id;

    private String             name;

    private int                order;

    private int                parentChapterId;

    private boolean            userControlSetTop;

    private int                visible;

    private List<ChildrenBean> children;

    protected KnowledgeHierarchyData(Parcel in) {
        courseId = in.readInt();
        id = in.readInt();
        name = in.readString();
        order = in.readInt();
        parentChapterId = in.readInt();
        userControlSetTop = in.readByte() != 0;
        visible = in.readInt();
        children = in.createTypedArrayList(ChildrenBean.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(courseId);
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(order);
        dest.writeInt(parentChapterId);
        dest.writeByte((byte) (userControlSetTop ? 1 : 0));
        dest.writeInt(visible);
        dest.writeTypedList(children);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<KnowledgeHierarchyData> CREATOR = new Creator<KnowledgeHierarchyData>() {
        @Override
        public KnowledgeHierarchyData createFromParcel(Parcel in) {
            return new KnowledgeHierarchyData(in);
        }

        @Override
        public KnowledgeHierarchyData[] newArray(int size) {
            return new KnowledgeHierarchyData[size];
        }
    };

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public boolean isUserControlSetTop() {
        return userControlSetTop;
    }

    public void setUserControlSetTop(boolean userControlSetTop) {
        this.userControlSetTop = userControlSetTop;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean implements Parcelable {
        /**
         * children : []
         * courseId : 13
         * id : 60
         * name : Android Studio相关
         * order : 1000
         * parentChapterId : 150
         * userControlSetTop : false
         * visible : 1
         */

        private int courseId;

        private int     id;

        private String  name;

        private int     order;

        private int     parentChapterId;

        private boolean userControlSetTop;

        private int     visible;

        private List<?> children;

        protected ChildrenBean(Parcel in) {
            courseId = in.readInt();
            id = in.readInt();
            name = in.readString();
            order = in.readInt();
            parentChapterId = in.readInt();
            userControlSetTop = in.readByte() != 0;
            visible = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(courseId);
            dest.writeInt(id);
            dest.writeString(name);
            dest.writeInt(order);
            dest.writeInt(parentChapterId);
            dest.writeByte((byte) (userControlSetTop ? 1 : 0));
            dest.writeInt(visible);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<ChildrenBean> CREATOR = new Creator<ChildrenBean>() {
            @Override
            public ChildrenBean createFromParcel(Parcel in) {
                return new ChildrenBean(in);
            }

            @Override
            public ChildrenBean[] newArray(int size) {
                return new ChildrenBean[size];
            }
        };

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getParentChapterId() {
            return parentChapterId;
        }

        public void setParentChapterId(int parentChapterId) {
            this.parentChapterId = parentChapterId;
        }

        public boolean isUserControlSetTop() {
            return userControlSetTop;
        }

        public void setUserControlSetTop(boolean userControlSetTop) {
            this.userControlSetTop = userControlSetTop;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }
    }
}
