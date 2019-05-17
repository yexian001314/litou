package com.sleep.uulib.bean;

import java.util.List;

/**
 * Created by SleepYM09 on 2017/11/6.
 * <p>
 * blog: www.sleepym09.com
 */

public class HomeFinanceListBean {
    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * newProjects : [{"orderId":"FD1711060000002","totalMoney":10000,"currentMoney":null,"lendRate":0.12,"baseRate":0.1,"activityRate":0,"noviceRate":0.02,"investPeriod":30,"createTime":1509947201000,"orderStatus":5,"subjectType":3,"id":1000475,"carBrand":"更换非官方","carModel":null,"carNumber":"AP1711060000002","subjectName":"新手002","loanType":null}]
     * activityProjects : [{"orderId":"FD1711060000004","totalMoney":50000,"currentMoney":null,"lendRate":0.1,"baseRate":0.09,"activityRate":0.01,"noviceRate":0,"investPeriod":30,"createTime":1509948585000,"orderStatus":5,"subjectType":2,"id":1000477,"carBrand":"大锅饭","carModel":null,"carNumber":"AP1711060000004","subjectName":"活动001","loanType":null}]
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private List<ProjectsBean> newProjects;
    private List<ProjectsBean> activityProjects;
    private List<ProjectsBean> uProjects;

    public List<ProjectsBean> getuProjects() {
        return uProjects;
    }

    public void setuProjects(List<ProjectsBean> uProjects) {
        this.uProjects = uProjects;
    }


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

    public List<ProjectsBean> getNewProjects() {
        return newProjects;
    }

    public void setNewProjects(List<ProjectsBean> newProjects) {
        this.newProjects = newProjects;
    }

    public List<ProjectsBean> getActivityProjects() {
        return activityProjects;
    }

    public void setActivityProjects(List<ProjectsBean> activityProjects) {
        this.activityProjects = activityProjects;
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

    public static class ProjectsBean {
        /**
         * orderId : FD1711060000002
         * totalMoney : 10000.0
         * currentMoney : null
         * lendRate : 0.12
         * baseRate : 0.1
         * activityRate : 0.0
         * noviceRate : 0.02
         * investPeriod : 30
         * createTime : 1509947201000
         * orderStatus : 5
         * subjectType : 3
         * id : 1000475
         * carBrand : 更换非官方
         * carModel : null
         * carNumber : AP1711060000002
         * subjectName : 新手002
         * loanType : null
         */

        private String orderId;
        private double totalMoney;
        private double currentMoney;
        private double lendRate;
        private double baseRate;
        private double activityRate;
        private double noviceRate;
        private int investPeriod;
        private long createTime;
        private int orderStatus;
        private int subjectType;
        private int id;
        private String carBrand;
        private Object carModel;
        private String carNumber;
        private String subjectName;
        private Object loanType;
        private int payType;

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public double getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(double totalMoney) {
            this.totalMoney = totalMoney;
        }

        public double getCurrentMoney() {
            return currentMoney;
        }

        public void setCurrentMoney(double currentMoney) {
            this.currentMoney = currentMoney;
        }

        public double getLendRate() {
            return lendRate;
        }

        public void setLendRate(double lendRate) {
            this.lendRate = lendRate;
        }

        public double getBaseRate() {
            return baseRate;
        }

        public void setBaseRate(double baseRate) {
            this.baseRate = baseRate;
        }

        public double getActivityRate() {
            return activityRate;
        }

        public void setActivityRate(double activityRate) {
            this.activityRate = activityRate;
        }

        public double getNoviceRate() {
            return noviceRate;
        }

        public void setNoviceRate(double noviceRate) {
            this.noviceRate = noviceRate;
        }

        public int getInvestPeriod() {
            return investPeriod;
        }

        public void setInvestPeriod(int investPeriod) {
            this.investPeriod = investPeriod;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getSubjectType() {
            return subjectType;
        }

        public void setSubjectType(int subjectType) {
            this.subjectType = subjectType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCarBrand() {
            return carBrand;
        }

        public void setCarBrand(String carBrand) {
            this.carBrand = carBrand;
        }

        public Object getCarModel() {
            return carModel;
        }

        public void setCarModel(Object carModel) {
            this.carModel = carModel;
        }

        public String getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(String carNumber) {
            this.carNumber = carNumber;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public Object getLoanType() {
            return loanType;
        }

        public void setLoanType(Object loanType) {
            this.loanType = loanType;
        }
    }
}
