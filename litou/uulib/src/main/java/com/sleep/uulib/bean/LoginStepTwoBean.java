package com.sleep.uulib.bean;

import com.sleep.uulib.bean.UserInfo;

/**
 * Created by SleepYM09 on 2017/11/10.
 * <p>
 * blog: www.sleepym09.com
 */

public class LoginStepTwoBean {


    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * userInfo : {"id":1000309,"deviceId":"","deviceType":3,"deviceName":"","password":"9n6RLoKwSvVo94cYrVttUgwk5H0=","realName":"何乐聪","nickName":"","mobilePhone":"18701418790","identityCard":"33068119950120705x","isIdentityVerified":true,"isPhoneVerified":true,"isBandCard":false,"sex":1,"qq":"123","weiXin":"2321321","avatarUrl":"","identityCardUrl":"","totalMoney":703635.61,"balanceMoney":676155.61,"loanMoney":2000,"createTime":1500276484000,"lastUpdateTime":1500345923000,"customerServiceId":900000063,"isDeleted":false,"userType":5,"gesturePw":"","isGesturePwBinded":false,"isIdentityUserInfo":true,"isWithdrawLock":false,"isDebt":false,"bankName":"","bankCardCode":"","address":"浙江","email":"111@111.com","relatedMobilePhone":"","signCount":3,"totalPoints":36,"totalInvestMoney":40000,"freeWithdrawNum":0,"vipLevel":"0","freeWithdrawLimit":403635.61,"monthInvestMoney":20000,"investingAmount":-180000,"investingIncome":-85479.47,"freezingAmount":207700,"hasIncome":203835.61,"totalIncome":118356.14,"monthInvestIncome":203835.61,"tradersPw":"AXbrgGcfM1UNxy8edbzutiSdaCc=","isTradersPwBinded":true,"registryPlatform":"","channelId":"","loginAccount":"18701418790"}
     * authkey : 41346857028e4c62865ada32e3c4054e
     * isTrueTradersPw : false
     * isIdentityUserInfo : false
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private UserInfo userInfo;
    private String authkey;
    private boolean isTrueTradersPw;
    private boolean isIdentityUserInfo;

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

    public String getAuthkey() {
        return authkey;
    }

    public void setAuthkey(String authkey) {
        this.authkey = authkey;
    }

    public boolean isIsTrueTradersPw() {
        return isTrueTradersPw;
    }

    public void setIsTrueTradersPw(boolean isTrueTradersPw) {
        this.isTrueTradersPw = isTrueTradersPw;
    }

    public boolean isIsIdentityUserInfo() {
        return isIdentityUserInfo;
    }

    public void setIsIdentityUserInfo(boolean isIdentityUserInfo) {
        this.isIdentityUserInfo = isIdentityUserInfo;
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

