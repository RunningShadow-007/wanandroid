package com.feiyang.wanandroid.ui.main.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/24 6:06 PM<br>
 * Desc: 搜索热词<br>
 */
public class HotWordsData implements Parcelable {

    /**
     * id : 8
     * link :
     * name : 逆向 加固
     * order : 8
     * visible : 1
     */

    private int id;

    private String link;

    private String name;

    private int    order;

    private int    visible;

    protected HotWordsData(Parcel in) {
        id = in.readInt();
        link = in.readString();
        name = in.readString();
        order = in.readInt();
        visible = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(link);
        dest.writeString(name);
        dest.writeInt(order);
        dest.writeInt(visible);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HotWordsData> CREATOR = new Creator<HotWordsData>() {
        @Override
        public HotWordsData createFromParcel(Parcel in) {
            return new HotWordsData(in);
        }

        @Override
        public HotWordsData[] newArray(int size) {
            return new HotWordsData[size];
        }
    };

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

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }
}
