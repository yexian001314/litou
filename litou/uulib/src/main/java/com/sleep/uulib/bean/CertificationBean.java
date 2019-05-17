package com.sleep.uulib.bean;

/**
 * Created by SleepYM09 on 2017/12/15.
 * <p>
 * blog: www.sleepym09.com
 */

public class CertificationBean {
    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * date : 正在提交请稍后。。。。。。。。<body onload="aut.submit()"><form method="post"  name="aut" id="aut" action="https://jzh-test.fuiou.com/jzh/app/appWebReg.action">
     <input name="ver" type="hidden" value="0.44"/>
     <input name="mchnt_txn_ssn" type="hidden" value="FJS1710170000137"/>
     <input name="mchnt_cd" type="hidden" value= "0002900F0096235"/>
     <input name="certif_id" type="hidden" value= "362324199006140314"/>
     <input name="certif_tp" type="hidden" value= "0"/>
     <input name="page_notify_url" type="hidden" value= "http://120.78.76.57:8081/authenticatServlet?method=callback-front"/>
     <input name="back_notify_url" type="hidden" value= "http://120.78.76.57:8081/authenticatServlet?method=callback-backend"/>
     <input name="signature" type="hidden" value= "1NgnaMwcsxS+clfoZQxpCyPkrVa909Knh/Rxh41wc295IQKes+Z59obtrJJ0y/v+0dolpyEwTNTP
     axQMT48NAyreQIR9ESKtIpR+wXOgA3cc3ksBMjwUERQh3t3hSwzTOEocKdtjOlsmoP1m22bRTCmF
     Rfb3hUZWHexg3eRwxKA="/>
     <input name="user_id_from" type="hidden" value= "1001098"/>
     </form></body>
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private String date;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
