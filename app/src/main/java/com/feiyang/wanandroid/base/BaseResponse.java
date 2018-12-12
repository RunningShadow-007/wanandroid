package com.feiyang.wanandroid.base;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/11/23 3:20 PM<br>
 * Desc: <br>
 */
public class BaseResponse<T> {
    private T data;

    private int errorCode;

    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
