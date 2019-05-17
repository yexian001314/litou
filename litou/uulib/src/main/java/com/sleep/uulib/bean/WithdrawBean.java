package com.sleep.uulib.bean;

/**
 * Created by SleepYM09 on 2017/12/8.
 * <p>
 * blog: www.sleepym09.com
 */

public class WithdrawBean {

    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * payCode : SFJ1710260000010
     * formData : <body onload="pay.submit()">正在提交请稍后。。。。。。。。<form method="post"  name="pay" id="pay" action="https://jzh-test.fuiou.com/jzh/app/500003.action"><input name="mchnt_cd" type="hidden" value="0002900F0096235"/><input name="amt" type="hidden" value="9900"/><input name="mchnt_txn_ssn" type="hidden" value="SFJ1710260000010"/><input name="login_id" type="hidden" value="15868454632"/><input name="page_notify_url" type="hidden" value= "http://120.78.76.57:8081/webBmWithdrawServlet?method=callback-front"/><input name="back_notify_url" type="hidden" value= "http://120.78.76.57:8081/webBmWithdrawServlet?method=callback-back"/><input name="signature" type="hidden" value= "Ecl8NjHPxymJzpLMvYNwNI5+cpngYkHfQ3RdhjMikJcLLQ4jCh4ZML7c2Zrh6xDU/w6Uqy1I3jPM+Xd1q6faXZFSgGJnSIV1pq2ltTNd5MKV4BIgRNDECqEJD+uCsvi+9qJCFehY4pYk/1wKd5qZKHQKHiOgBdJo8TF2EJTVLPA="/></form></body>
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private String payCode;
    private String formData;

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

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
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
