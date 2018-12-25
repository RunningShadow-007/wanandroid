package com.feiyang.wanandroid.ui.main.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/24 8:04 PM<br>
 * Desc: 项目分类<br>
 */
public class ProjectCategoryData implements Parcelable {

    /**
     * children : []
     * courseId : 13
     * id : 402
     * name : 跨平台应用
     * order : 145001
     * parentChapterId : 293
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

    protected ProjectCategoryData(Parcel in) {
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

    public static final Creator<ProjectCategoryData> CREATOR = new Creator<ProjectCategoryData>() {
        @Override
        public ProjectCategoryData createFromParcel(Parcel in) {
            return new ProjectCategoryData(in);
        }

        @Override
        public ProjectCategoryData[] newArray(int size) {
            return new ProjectCategoryData[size];
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
