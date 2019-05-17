package com.sleep.uulib.bean;

/**
 * Created by SleepYM09 on 2017/10/20.
 * <p>
 * blog: www.sleepym09.com
 */

public class BaseData {
    private String netTag;

    public String getNetTag() {
        return netTag;
    }

    public void setNetTag(String netTag) {
        this.netTag = netTag;
    }

    @Override
    public String toString() {
        return "BaseData{" +
                "netTag='" + netTag + '\'' +
                '}';
    }
}
