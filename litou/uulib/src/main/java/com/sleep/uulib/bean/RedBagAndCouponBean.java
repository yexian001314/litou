package com.sleep.uulib.bean;

/**
 * Created by SleepYM09 on 2018/4/4.
 * <p>
 * blog: www.sleepym09.com
 */

public class RedBagAndCouponBean {
    @Override
    public String toString() {
        return "RedBagAndCouponBean{" +
                "responseStatus=" + responseStatus +
                ", success=" + success +
                ", isException=" + isException +
                ", todayIncome=" + todayIncome +
                ", totalIncome=" + totalIncome +
                ", totalMoney=" + totalMoney +
                ", balanceMoney=" + balanceMoney +
                ", ranking=" + ranking +
                ", bankMultiple=" + bankMultiple +
                ", isBandCard=" + isBandCard +
                ", isIdentityVerified=" + isIdentityVerified +
                ", isPhoneVerified=" + isPhoneVerified +
                ", investingAmount=" + investingAmount +
                ", investingIncome=" + investingIncome +
                ", hasPayCount=" + hasPayCount +
                ", freezingAmount=" + freezingAmount +
                ", hasIncome=" + hasIncome +
                ", monthInvestIncome=" + monthInvestIncome +
                ", redPacketsCount=" + redPacketsCount +
                ", rateCount=" + rateCount +
                '}';
    }

    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * todayIncome : 0.0
     * totalIncome : 310.2
     * totalMoney : 2098599.2
     * balanceMoney : 1946899.2
     * ranking : 0
     * bankMultiple : 0.0
     * isBandCard : true
     * isIdentityVerified : true
     * isPhoneVerified : true
     * investingAmount : 140200.0
     * investingIncome : 5114.3
     * hasPayCount : 1
     * freezingAmount : 11500.0
     * hasIncome : 310.2
     * monthInvestIncome : 310.2
     * redPacketsCount : 0
     * rateCount : 2
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private double todayIncome;
    private double totalIncome;
    private double totalMoney;
    private double balanceMoney;
    private int ranking;
    private double bankMultiple;
    private boolean isBandCard;
    private boolean isIdentityVerified;
    private boolean isPhoneVerified;
    private double investingAmount;
    private double investingIncome;
    private int hasPayCount;
    private double freezingAmount;
    private double hasIncome;
    private double monthInvestIncome;
    private int redPacketsCount;
    private int rateCount;

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

    public double getTodayIncome() {
        return todayIncome;
    }

    public void setTodayIncome(double todayIncome) {
        this.todayIncome = todayIncome;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getBalanceMoney() {
        return balanceMoney;
    }

    public void setBalanceMoney(double balanceMoney) {
        this.balanceMoney = balanceMoney;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public double getBankMultiple() {
        return bankMultiple;
    }

    public void setBankMultiple(double bankMultiple) {
        this.bankMultiple = bankMultiple;
    }

    public boolean isIsBandCard() {
        return isBandCard;
    }

    public void setIsBandCard(boolean isBandCard) {
        this.isBandCard = isBandCard;
    }

    public boolean isIsIdentityVerified() {
        return isIdentityVerified;
    }

    public void setIsIdentityVerified(boolean isIdentityVerified) {
        this.isIdentityVerified = isIdentityVerified;
    }

    public boolean isIsPhoneVerified() {
        return isPhoneVerified;
    }

    public void setIsPhoneVerified(boolean isPhoneVerified) {
        this.isPhoneVerified = isPhoneVerified;
    }

    public double getInvestingAmount() {
        return investingAmount;
    }

    public void setInvestingAmount(double investingAmount) {
        this.investingAmount = investingAmount;
    }

    public double getInvestingIncome() {
        return investingIncome;
    }

    public void setInvestingIncome(double investingIncome) {
        this.investingIncome = investingIncome;
    }

    public int getHasPayCount() {
        return hasPayCount;
    }

    public void setHasPayCount(int hasPayCount) {
        this.hasPayCount = hasPayCount;
    }

    public double getFreezingAmount() {
        return freezingAmount;
    }

    public void setFreezingAmount(double freezingAmount) {
        this.freezingAmount = freezingAmount;
    }

    public double getHasIncome() {
        return hasIncome;
    }

    public void setHasIncome(double hasIncome) {
        this.hasIncome = hasIncome;
    }

    public double getMonthInvestIncome() {
        return monthInvestIncome;
    }

    public void setMonthInvestIncome(double monthInvestIncome) {
        this.monthInvestIncome = monthInvestIncome;
    }

    public int getRedPacketsCount() {
        return redPacketsCount;
    }

    public void setRedPacketsCount(int redPacketsCount) {
        this.redPacketsCount = redPacketsCount;
    }

    public int getRateCount() {
        return rateCount;
    }

    public void setRateCount(int rateCount) {
        this.rateCount = rateCount;
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
