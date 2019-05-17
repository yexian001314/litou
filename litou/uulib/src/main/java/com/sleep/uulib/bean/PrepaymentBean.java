package com.sleep.uulib.bean;

/**
 * Created by SleepYM09 on 2017/12/8.
 * <p>
 * blog: www.sleepym09.com
 */

public class PrepaymentBean {
    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * payCode : SFJ1710250000212
     * payJumpUrl : rs/fooyouPay/sendPurchase
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private String payCode;
    private String payJumpUrl;

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

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayJumpUrl() {
        return payJumpUrl;
    }

    public void setPayJumpUrl(String payJumpUrl) {
        this.payJumpUrl = payJumpUrl;
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
