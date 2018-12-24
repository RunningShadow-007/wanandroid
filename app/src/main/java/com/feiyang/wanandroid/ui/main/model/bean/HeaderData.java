package com.feiyang.wanandroid.ui.main.model.bean;

import com.feiyang.wanandroid.base.BaseItem;
import com.feiyang.wanandroid.core.constants.Constants;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/20 7:18 PM<br>
 * Desc: <br>
 */
public class HeaderData implements BaseItem {
    private List<BannerData> dataList;

    public HeaderData(@NonNull List<BannerData> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getItemType() {
        return Constants.TYPE_HEADER;
    }

    @Override
    public HeaderData getData() {
        return this;
    }

    public List<BannerData> getDataList() {
        if (dataList == null) {
            return new ArrayList<>();
        }
        return dataList;
    }

    public void setDataList(List<BannerData> dataList) {
        this.dataList = dataList;
    }
}
