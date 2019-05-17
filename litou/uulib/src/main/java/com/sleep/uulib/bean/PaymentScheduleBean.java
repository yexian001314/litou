package com.sleep.uulib.bean;

import java.util.List;

/**
 * Created by SleepYM09 on 2017/12/12.
 * <p>
 * blog: www.sleepym09.com
 */

public class PaymentScheduleBean {
    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * totalCount : 1
     * returnFundVOS : [{"investOrderId":null,"orderId":"FD1803010000001","money":339.72,"time":1522553136000,"period":1,"totalPeriod":3,"subjectName":"先息后本002"}]
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private int totalCount;
    private List<ReturnFundVOSBean> returnFundVOS;

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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<ReturnFundVOSBean> getReturnFundVOS() {
        return returnFundVOS;
    }

    public void setReturnFundVOS(List<ReturnFundVOSBean> returnFundVOS) {
        this.returnFundVOS = returnFundVOS;
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

    public static class ReturnFundVOSBean {
        /**
         * investOrderId : null
         * orderId : FD1803010000001
         * money : 339.72
         * time : 1522553136000
         * period : 1
         * totalPeriod : 3
         * subjectName : 先息后本002
         */

        private Object investOrderId;
        private String orderId;
        private double money;
        private long time;
        private int period;
        private int totalPeriod;
        private String subjectName;

        public Object getInvestOrderId() {
            return investOrderId;
        }

        public void setInvestOrderId(Object investOrderId) {
            this.investOrderId = investOrderId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getPeriod() {
            return period;
        }

        public void setPeriod(int period) {
            this.period = period;
        }

        public int getTotalPeriod() {
            return totalPeriod;
        }

        public void setTotalPeriod(int totalPeriod) {
            this.totalPeriod = totalPeriod;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }
    }
}
