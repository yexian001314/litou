package com.sleep.uulib.bean;

/**
 * Created by SleepYM09 on 2017/11/16.
 * <p>
 * blog: www.sleepym09.com
 */

public class FindPasswordBean {
    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * userInfo : {"id":1000309,"deviceType":3,"password":"9n6RLoKwSvVo94cYrVttUgwk5H0=","realName":"何乐聪","mobilePhone":"18701418790","identityCard":"33068119950120705x","isIdentityVerified":true,"isPhoneVerified":true,"isBandCard":false,"sex":1,"qq":"222","weiXin":"2321321","totalMoney":703835.61,"balanceMoney":702335.61,"loanMoney":2000,"createTime":1498733446000,"lastUpdateTime":1499856558000,"customerServiceId":900000063,"isDeleted":false,"userType":5,"isIdentityUserInfo":true,"isDebt":false,"address":"浙江","email":"111@111.com","signCount":1,"totalPoints":301,"totalInvestMoney":20000,"freeWithdrawNum":0,"vipLevel":"VIP0","freeWithdrawLimit":403835.61,"monthInvestMoney":0,"investingAmount":-200000,"investingIncome":-203835.61,"freezingAmount":201500,"hasIncome":203835.61,"totalIncome":0,"monthInvestIncome":203835.61,"tradersPw":"AXbrgGcfM1UNxy8edbzutiSdaCc=","isTradersPwBinded":true,"loginAccount":"18701418790"}
     * isIdentityUserInfo : true
     * isTrueTradersPw : false
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private UserInfo userInfo;
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

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
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
