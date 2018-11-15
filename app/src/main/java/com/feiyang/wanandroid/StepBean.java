package com.feiyang.wanandroid;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/10/29 2:49 PM<br>
 * Desc: <br>
 */
public class StepBean {
    private String title;

    private boolean isSelected;//当前已选中

    private boolean isSnail;//是结尾节点

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSnail() {
        return isSnail;
    }

    public void setSnail(boolean snail) {
        isSnail = snail;
    }
}
