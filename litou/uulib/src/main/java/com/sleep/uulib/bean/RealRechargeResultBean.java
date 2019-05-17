package com.sleep.uulib.bean;

/**
 * Created by SleepYM09 on 2017/12/8.
 * <p>
 * blog: www.sleepym09.com
 */

public class RealRechargeResultBean {
    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * serviceUrl : <body onload="pay.submit()">正在提交请稍后。。。。。。。。<form method="post"  name="pay" id="pay" action="https://jzh-test.fuiou.com/jzh/app/500002.action"><input name="mchnt_cd" type="hidden" value="0002900F0096235"/><input name="mchnt_txn_ssn" type="hidden" value="SFJ1710250000213"/><input name="login_id" type="hidden" value="18367180809"/><input name="amt" type="hidden" value="10000"/><input name="page_notify_url" type="hidden" value= "http://120.78.76.57:8081/fooyouPayServlet?method=callback-front"/><input name="back_notify_url" type="hidden" value= "http://120.78.76.57:8081/fooyouPayServlet?method=callback-return"/><input name="signature" type="hidden" value= "7SKz/Ove3ipB8zNdDjkT/uhJjH4qzhvOALeQuA4dc74MUWGk1fsUjco9e8t5obpmkghPEP0ClprL
     ysmKd6GmlJOW/+LTYnAEtaiKuyaXtfO2Ah4TnMdaE383GF+m1CmcTGOfmZbYhUkra2u7vhUv0XOm
     FMsQH0oiDQH0WcJNo+8="/></form></body>
     * serviceType : 1
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private String serviceUrl;
    private String serviceType;

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

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
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
