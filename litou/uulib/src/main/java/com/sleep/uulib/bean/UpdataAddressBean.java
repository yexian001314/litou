package com.sleep.uulib.bean;

/**
 * Created by SleepYM09 on 2017/12/13.
 * <p>
 * blog: www.sleepym09.com
 */

public class UpdataAddressBean {
    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * isIdentityUserInfo : false
     * isTrueTradersPw : false
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private boolean isIdentityUserInfo;
    private boolean isTrueTradersPw;

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

    public boolean isIsIdentityUserInfo() {
        return isIdentityUserInfo;
    }

    public void setIsIdentityUserInfo(boolean isIdentityUserInfo) {
        this.isIdentityUserInfo = isIdentityUserInfo;
    }

    public boolean isIsTrueTradersPw() {
        return isTrueTradersPw;
    }

    public void setIsTrueTradersPw(boolean isTrueTradersPw) {
        this.isTrueTradersPw = isTrueTradersPw;
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
