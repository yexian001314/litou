package com.sleep.uulib.bean;

import java.util.List;

/**
 * Created by SleepYM09 on 2017/12/11.
 * <p>
 * blog: www.sleepym09.com
 */

public class InvestDetailBean {

    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * projects : {"pageSize":8,"start":0,"data":[{"fundType":3,"totalMoney":-1000.12,"afterBalanceMoney":7900.12,"amountFrozen":0.13,"acceptAmount":0.12,"createTime":1500460983000,"note":""},{"fundType":3,"totalMoney":-1000,"afterBalanceMoney":8900,"amountFrozen":0,"acceptAmount":0,"createTime":1500459998000,"note":""},{"fundType":3,"totalMoney":-100,"afterBalanceMoney":-100,"amountFrozen":0,"acceptAmount":0,"createTime":1500431663000,"note":""}],"totalCount":3,"hasNextPage":false,"dataSizeOfCurrentPage":3,"hasPreviousPage":false,"currentPageNo":1,"totalPageCount":1}
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private ProjectsBean projects;

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
         * pageSize : 8
         * start : 0
         * data : [{"fundType":3,"totalMoney":-1000.12,"afterBalanceMoney":7900.12,"amountFrozen":0.13,"acceptAmount":0.12,"createTime":1500460983000,"note":""},{"fundType":3,"totalMoney":-1000,"afterBalanceMoney":8900,"amountFrozen":0,"acceptAmount":0,"createTime":1500459998000,"note":""},{"fundType":3,"totalMoney":-100,"afterBalanceMoney":-100,"amountFrozen":0,"acceptAmount":0,"createTime":1500431663000,"note":""}]
         * totalCount : 3
         * hasNextPage : false
         * dataSizeOfCurrentPage : 3
         * hasPreviousPage : false
         * currentPageNo : 1
         * totalPageCount : 1
         */

        private int pageSize;
        private int start;
        private int totalCount;
        private boolean hasNextPage;
        private int dataSizeOfCurrentPage;
        private boolean hasPreviousPage;
        private int currentPageNo;
        private int totalPageCount;
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

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
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

        public int getCurrentPageNo() {
            return currentPageNo;
        }

        public void setCurrentPageNo(int currentPageNo) {
            this.currentPageNo = currentPageNo;
        }

        public int getTotalPageCount() {
            return totalPageCount;
        }

        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * fundType : 3
             * totalMoney : -1000.12
             * afterBalanceMoney : 7900.12
             * amountFrozen : 0.13
             * acceptAmount : 0.12
             * createTime : 1500460983000
             * note :
             */

            private int fundType;
            private double totalMoney;
            private double afterBalanceMoney;
            private double amountFrozen;
            private double acceptAmount;
            private long createTime;
            private String note;

            public int getFundType() {
                return fundType;
            }

            public void setFundType(int fundType) {
                this.fundType = fundType;
            }

            public double getTotalMoney() {
                return totalMoney;
            }

            public void setTotalMoney(double totalMoney) {
                this.totalMoney = totalMoney;
            }

            public double getAfterBalanceMoney() {
                return afterBalanceMoney;
            }

            public void setAfterBalanceMoney(double afterBalanceMoney) {
                this.afterBalanceMoney = afterBalanceMoney;
            }

            public double getAmountFrozen() {
                return amountFrozen;
            }

            public void setAmountFrozen(double amountFrozen) {
                this.amountFrozen = amountFrozen;
            }

            public double getAcceptAmount() {
                return acceptAmount;
            }

            public void setAcceptAmount(double acceptAmount) {
                this.acceptAmount = acceptAmount;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }
        }
    }
}
