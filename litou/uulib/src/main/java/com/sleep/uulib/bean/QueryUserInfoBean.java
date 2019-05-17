package com.sleep.uulib.bean;

/**
 * Created by SleepYM09 on 2017/12/6.
 * <p>
 * blog: www.sleepym09.com
 */

public class QueryUserInfoBean {
    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * userInfo : {"id":1001038,"deviceType":3,"password":"GO6YgykXl4ziRJorePPRumCwZO4=","realName":"平伟成","mobilePhone":"17681888132","identityCard":"331122197509071892","isIdentityVerified":true,"isPhoneVerified":true,"isBandCard":true,"sex":1,"totalMoney":1.01E7,"balanceMoney":8688900,"loanMoney":0,"createTime":1509931740000,"lastUpdateTime":1512542038000,"customerServiceId":1,"isDeleted":false,"userType":5,"isIdentityUserInfo":true,"isDebt":false,"address":"安徽省|合肥市|瑶海区|aaad1","signCount":1,"totalPoints":89850,"totalInvestMoney":422000,"freeWithdrawNum":0,"vipLevel":"5","freeWithdrawLimit":0,"monthInvestMoney":422000,"investingAmount":400000,"investingIncome":3041.07,"freezingAmount":1011100,"hasIncome":0,"totalIncome":0,"monthInvestIncome":0,"tradersPw":"m+WwtsaJYhHM9q/eXZSoRhfA3Sc=","isTradersPwBinded":true,"registryPlatform":"1014","channelId":1004,"isSign":false,"age":42,"type":0,"loginAccount":"17681888132"}
     * isIdentityUserInfo : true
     * isTrueTradersPw : false
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private UserInfo userInfo;

    @Override
    public String toString() {
        return "QueryUserInfoBean{" +
                "responseStatus=" + responseStatus +
                ", success=" + success +
                ", isException=" + isException +
                ", userInfo=" + userInfo +
                ", isIdentityUserInfo=" + isIdentityUserInfo +
                ", isTrueTradersPw=" + isTrueTradersPw +
                '}';
    }

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

}
