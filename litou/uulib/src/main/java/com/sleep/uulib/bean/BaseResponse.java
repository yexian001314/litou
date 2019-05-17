package com.sleep.uulib.bean;

/**
 * Created by SleepYM09 on 2017/10/19.
 * <p>
 * blog: www.sleepym09.com
 */

public class BaseResponse<T> {

    public BaseResponse() {
    }

    public BaseResponse(String json, T response) {
        this.json = json;
        this.response = response;
    }

    private String json;
    private T response;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "json='" + json + '\'' +
                ", response=" + response +
                '}';
    }
}
