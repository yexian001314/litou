package com.sleep.uulib.bean;

import java.util.List;

/**
 * Created by SleepYM09 on 2017/11/9.
 * <p>
 * blog: www.sleepym09.com
 */

public class FinanceListBean {


    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * projects : {"pageSize":8,"start":8,"data":[{"orderId":"FD1707170000003","totalMoney":15000.12,"currentMoney":12.12,"lendRate":0.05,"baseRate":0.05,"activityRate":0.12,"noviceRate":0.12,"investPeriod":30,"createTime":1500273020000,"orderStatus":5,"subjectType":1,"id":1000331,"carBrand":"","carModel":"","carNumber":"AP1707170000003","subjectName":"信用贷"},{"orderId":"FD1707160000003","totalMoney":50000,"currentMoney":100,"lendRate":0.5,"baseRate":0.5,"activityRate":0,"noviceRate":0,"investPeriod":20,"createTime":1500205436000,"orderStatus":5,"subjectType":1,"id":1000328,"carBrand":null,"carModel":null,"carNumber":"AP1707160000007","subjectName":"信用贷"},{"orderId":"FD1707150000003","totalMoney":20000,"currentMoney":4200,"lendRate":0.11,"baseRate":0.11,"activityRate":0,"noviceRate":0,"investPeriod":5,"createTime":1500125172000,"orderStatus":5,"subjectType":1,"id":1000325,"carBrand":"乐视","carModel":"乐视无人驾驶","carNumber":"AP1707150000003","subjectName":"乐视乐视无人驾驶"},{"orderId":"FD1707150000001","totalMoney":10000,"currentMoney":7988,"lendRate":0.15,"baseRate":0.15,"activityRate":0,"noviceRate":0,"investPeriod":45,"createTime":1500119352000,"orderStatus":5,"subjectType":1,"id":1000323,"carBrand":"雷诺","carModel":"卡缤","carNumber":"AP1707150000001","subjectName":"雷诺卡缤"},{"orderId":"FD1707140000003","totalMoney":10000,"currentMoney":null,"lendRate":0.1501,"baseRate":0.15,"activityRate":0,"noviceRate":0,"investPeriod":45,"createTime":1500020302000,"orderStatus":5,"subjectType":1,"id":1000321,"carBrand":"的是非得失","carModel":"萨达","carNumber":"AP1707140000003","subjectName":"的是非得失萨达"},{"orderId":"FD1707130000002","totalMoney":100000,"currentMoney":null,"lendRate":0.02,"baseRate":0.01,"activityRate":0.01,"noviceRate":0,"investPeriod":30,"createTime":1499932191000,"orderStatus":5,"subjectType":2,"id":1000318,"carBrand":"未知","carModel":"未知","carNumber":"AP1608090000002","subjectName":"未知未知"},{"orderId":"FD1608090000005","totalMoney":100000,"currentMoney":26923,"lendRate":0.12,"baseRate":0.09,"activityRate":0,"noviceRate":0.03,"investPeriod":30,"createTime":1470733894000,"orderStatus":5,"subjectType":3,"id":1000299,"carBrand":"奔驰","carModel":"s800l","carNumber":"AP1608090000011","subjectName":"奔驰s800l"},{"orderId":"FD1707130000001","totalMoney":2000,"currentMoney":2000,"lendRate":0.03,"baseRate":0.03,"activityRate":0,"noviceRate":0,"investPeriod":20,"createTime":1499931986000,"orderStatus":6,"subjectType":1,"id":1000317,"carBrand":"奇瑞","carModel":"qq","carNumber":"AP1707130000004","subjectName":"奇瑞qq"}],"totalCount":146,"currentPageNo":2,"hasNextPage":true,"totalPageCount":19,"dataSizeOfCurrentPage":8,"hasPreviousPage":true}
     * financingTotalCount : 146
     * completeTotalCount : 0
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;

    @Override
    public String toString() {
        return "FinanceListBean{" +
                "responseStatus=" + responseStatus +
                ", success=" + success +
                ", isException=" + isException +
                ", projects=" + projects +
                ", financingTotalCount=" + financingTotalCount +
                ", completeTotalCount=" + completeTotalCount +
                '}';
    }

    private ProjectsBean projects;
    private int financingTotalCount;
    private int completeTotalCount;

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

    public ProjectsBean getProjects() {
        return projects;
    }

    public void setProjects(ProjectsBean projects) {
        this.projects = projects;
    }

    public int getFinancingTotalCount() {
        return financingTotalCount;
    }

    public void setFinancingTotalCount(int financingTotalCount) {
        this.financingTotalCount = financingTotalCount;
    }

    public int getCompleteTotalCount() {
        return completeTotalCount;
    }

    public void setCompleteTotalCount(int completeTotalCount) {
        this.completeTotalCount = completeTotalCount;
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

    public static class ProjectsBean {
        @Override
        public String toString() {
            return "ProjectsBean{" +
                    "pageSize=" + pageSize +
                    ", start=" + start +
                    ", totalCount=" + totalCount +
                    ", currentPageNo=" + currentPageNo +
                    ", hasNextPage=" + hasNextPage +
                    ", totalPageCount=" + totalPageCount +
                    ", dataSizeOfCurrentPage=" + dataSizeOfCurrentPage +
                    ", hasPreviousPage=" + hasPreviousPage +
                    ", data=" + data +
                    '}';
        }

        /**
         * pageSize : 8
         * start : 8
         * data : [{"orderId":"FD1707170000003","totalMoney":15000.12,"currentMoney":12.12,"lendRate":0.05,"baseRate":0.05,"activityRate":0.12,"noviceRate":0.12,"investPeriod":30,"createTime":1500273020000,"orderStatus":5,"subjectType":1,"id":1000331,"carBrand":"","carModel":"","carNumber":"AP1707170000003","subjectName":"信用贷"},{"orderId":"FD1707160000003","totalMoney":50000,"currentMoney":100,"lendRate":0.5,"baseRate":0.5,"activityRate":0,"noviceRate":0,"investPeriod":20,"createTime":1500205436000,"orderStatus":5,"subjectType":1,"id":1000328,"carBrand":null,"carModel":null,"carNumber":"AP1707160000007","subjectName":"信用贷"},{"orderId":"FD1707150000003","totalMoney":20000,"currentMoney":4200,"lendRate":0.11,"baseRate":0.11,"activityRate":0,"noviceRate":0,"investPeriod":5,"createTime":1500125172000,"orderStatus":5,"subjectType":1,"id":1000325,"carBrand":"乐视","carModel":"乐视无人驾驶","carNumber":"AP1707150000003","subjectName":"乐视乐视无人驾驶"},{"orderId":"FD1707150000001","totalMoney":10000,"currentMoney":7988,"lendRate":0.15,"baseRate":0.15,"activityRate":0,"noviceRate":0,"investPeriod":45,"createTime":1500119352000,"orderStatus":5,"subjectType":1,"id":1000323,"carBrand":"雷诺","carModel":"卡缤","carNumber":"AP1707150000001","subjectName":"雷诺卡缤"},{"orderId":"FD1707140000003","totalMoney":10000,"currentMoney":null,"lendRate":0.1501,"baseRate":0.15,"activityRate":0,"noviceRate":0,"investPeriod":45,"createTime":1500020302000,"orderStatus":5,"subjectType":1,"id":1000321,"carBrand":"的是非得失","carModel":"萨达","carNumber":"AP1707140000003","subjectName":"的是非得失萨达"},{"orderId":"FD1707130000002","totalMoney":100000,"currentMoney":null,"lendRate":0.02,"baseRate":0.01,"activityRate":0.01,"noviceRate":0,"investPeriod":30,"createTime":1499932191000,"orderStatus":5,"subjectType":2,"id":1000318,"carBrand":"未知","carModel":"未知","carNumber":"AP1608090000002","subjectName":"未知未知"},{"orderId":"FD1608090000005","totalMoney":100000,"currentMoney":26923,"lendRate":0.12,"baseRate":0.09,"activityRate":0,"noviceRate":0.03,"investPeriod":30,"createTime":1470733894000,"orderStatus":5,"subjectType":3,"id":1000299,"carBrand":"奔驰","carModel":"s800l","carNumber":"AP1608090000011","subjectName":"奔驰s800l"},{"orderId":"FD1707130000001","totalMoney":2000,"currentMoney":2000,"lendRate":0.03,"baseRate":0.03,"activityRate":0,"noviceRate":0,"investPeriod":20,"createTime":1499931986000,"orderStatus":6,"subjectType":1,"id":1000317,"carBrand":"奇瑞","carModel":"qq","carNumber":"AP1707130000004","subjectName":"奇瑞qq"}]
         * totalCount : 146
         * currentPageNo : 2
         * hasNextPage : true
         * totalPageCount : 19
         * dataSizeOfCurrentPage : 8
         * hasPreviousPage : true
         */

        private int pageSize;
        private int start;
        private int totalCount;
        private int currentPageNo;
        private boolean hasNextPage;
        private int totalPageCount;
        private int dataSizeOfCurrentPage;
        private boolean hasPreviousPage;
        private List<DataBean> data;

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getCurrentPageNo() {
            return currentPageNo;
        }

        public void setCurrentPageNo(int currentPageNo) {
            this.currentPageNo = currentPageNo;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getTotalPageCount() {
            return totalPageCount;
        }

        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
        }

        public int getDataSizeOfCurrentPage() {
            return dataSizeOfCurrentPage;
        }

        public void setDataSizeOfCurrentPage(int dataSizeOfCurrentPage) {
            this.dataSizeOfCurrentPage = dataSizeOfCurrentPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            @Override
            public String toString() {
                return "DataBean{" +
                        "orderId='" + orderId + '\'' +
                        ", totalMoney=" + totalMoney +
                        ", currentMoney=" + currentMoney +
                        ", lendRate=" + lendRate +
                        ", baseRate=" + baseRate +
                        ", activityRate=" + activityRate +
                        ", noviceRate=" + noviceRate +
                        ", investPeriod=" + investPeriod +
                        ", createTime=" + createTime +
                        ", orderStatus=" + orderStatus +
                        ", subjectType=" + subjectType +
                        ", id=" + id +
                        ", carBrand='" + carBrand + '\'' +
                        ", carModel='" + carModel + '\'' +
                        ", carNumber='" + carNumber + '\'' +
                        ", subjectName='" + subjectName + '\'' +
                        ", payType=" + payType +
                        '}';
            }

            /**
             * orderId : FD1707170000003
             * totalMoney : 15000.12
             * currentMoney : 12.12
             * lendRate : 0.05
             * baseRate : 0.05
             * activityRate : 0.12
             * noviceRate : 0.12
             * investPeriod : 30
             * createTime : 1500273020000
             * orderStatus : 5
             * subjectType : 1
             * id : 1000331
             * carBrand :
             * carModel :
             * carNumber : AP1707170000003
             * subjectName : 信用贷
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
            private String carModel;
            private String carNumber;
            private String subjectName;
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

            public String getCarModel() {
                return carModel;
            }

            public void setCarModel(String carModel) {
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
        }
    }
}