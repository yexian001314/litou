package com.sleep.uulib.bean;

/**
 * Created by SleepYM09 on 2017/11/8.
 * <p>
 * blog: www.sleepym09.com
 */

public class CheckPhoneBean {
    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * isExist : true
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private boolean isExist;

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

    public boolean isIsExist() {
        return isExist;
    }

    public void setIsExist(boolean isExist) {
        this.isExist = isExist;
    }

    public static class ResponseStatusBean {
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
}
