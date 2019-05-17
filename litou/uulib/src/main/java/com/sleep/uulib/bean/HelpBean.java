package com.sleep.uulib.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SleepYM09 on 2018/4/2.
 * <p>
 * blog: www.sleepym09.com
 */

public class HelpBean implements Serializable {
    private static final long serialVersionUID = 0L;
    @Override
    public String toString() {
        return "HelpBean{" +
                "responseStatus=" + responseStatus +
                ", success=" + success +
                ", isException=" + isException +
                ", helpMenuEOS=" + helpMenuEOS +
                '}';
    }

    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * helpMenuEOS : [{"id":328,"menuId":328,"title":"你好","content":"hello","createTime":1522662746000,"updateTime":1522662746000,"state":0}]
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private List<HelpMenuEOSBean> helpMenuEOS;

    public ResponseStatusBean getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatusBean responseStatus) {
        this.responseStatus = responseStatus;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isIsException() {
        return isException;
    }

    public void setIsException(boolean isException) {
        this.isException = isException;
    }

    public List<HelpMenuEOSBean> getHelpMenuEOS() {
        return helpMenuEOS;
    }

    public void setHelpMenuEOS(List<HelpMenuEOSBean> helpMenuEOS) {
        this.helpMenuEOS = helpMenuEOS;
    }

    public static class ResponseStatusBean {
        @Override
        public String toString() {
            return "ResponseStatusBean{" +
                    "code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }

        /**
         * code : 00
         * message : 操作成功
         */

        private String code;
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class HelpMenuEOSBean {
        @Override
        public String toString() {
            return "HelpMenuEOSBean{" +
                    "id=" + id +
                    ", menuId=" + menuId +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", createTime=" + createTime +
                    ", updateTime=" + updateTime +
                    ", state=" + state +
                    '}';
        }

        /**
         * id : 328
         * menuId : 328
         * title : 你好
         * content : hello
         * createTime : 1522662746000
         * updateTime : 1522662746000
         * state : 0
         */

        private int id;
        private int menuId;
        private String title;
        private String content;
        private long createTime;
        private long updateTime;
        private int state;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMenuId() {
            return menuId;
        }

        public void setMenuId(int menuId) {
            this.menuId = menuId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
