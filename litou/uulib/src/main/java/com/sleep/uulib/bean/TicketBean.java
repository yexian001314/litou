package com.sleep.uulib.bean;

import java.util.List;

/**
 * Created by SleepYM09 on 2018/1/5.
 * <p>
 * blog: www.sleepym09.com
 */

public class TicketBean {

    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * projects : {"pageSize":10,"start":0,"data":[{"id":142,"templeteId":6,"rateCode":"RA1709070000019","lendRate":0.01,"rateStatus":1,"rateUserId":1001046,"createTime":1515046143000,"description":"注册加息券","isDeleted":false,"dueTime":1517724560000,"useRule":1000,"validDay":30,"rateType":1,"useRuleInvestPeriod":0},{"id":143,"templeteId":6,"rateCode":"RA1709070000020","lendRate":0.02,"rateStatus":1,"rateUserId":1001046,"createTime":1515046143000,"description":"注册加息券","isDeleted":false,"dueTime":1517724560000,"useRule":1000,"validDay":30,"rateType":1,"useRuleInvestPeriod":0},{"id":144,"templeteId":6,"rateCode":"RA1709070000021","lendRate":0.01,"rateStatus":3,"rateUserId":1001046,"createTime":1515046143000,"description":"注册加息券","isDeleted":false,"dueTime":1517724560000,"useRule":1000,"validDay":30,"rateType":1,"useRuleInvestPeriod":0},{"id":145,"templeteId":6,"rateCode":"RA1709070000022","lendRate":0.02,"rateStatus":4,"rateUserId":1001046,"createTime":1515046143000,"description":"注册加息券","isDeleted":false,"dueTime":1517724560000,"useRule":1000,"validDay":30,"rateType":1,"useRuleInvestPeriod":0}],"totalCount":4,"hasNextPage":false,"totalPageCount":1,"hasPreviousPage":false,"dataSizeOfCurrentPage":4,"currentPageNo":1}
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
         * pageSize : 10
         * start : 0
         * data : [{"id":142,"templeteId":6,"rateCode":"RA1709070000019","lendRate":0.01,"rateStatus":1,"rateUserId":1001046,"createTime":1515046143000,"description":"注册加息券","isDeleted":false,"dueTime":1517724560000,"useRule":1000,"validDay":30,"rateType":1,"useRuleInvestPeriod":0},{"id":143,"templeteId":6,"rateCode":"RA1709070000020","lendRate":0.02,"rateStatus":1,"rateUserId":1001046,"createTime":1515046143000,"description":"注册加息券","isDeleted":false,"dueTime":1517724560000,"useRule":1000,"validDay":30,"rateType":1,"useRuleInvestPeriod":0},{"id":144,"templeteId":6,"rateCode":"RA1709070000021","lendRate":0.01,"rateStatus":3,"rateUserId":1001046,"createTime":1515046143000,"description":"注册加息券","isDeleted":false,"dueTime":1517724560000,"useRule":1000,"validDay":30,"rateType":1,"useRuleInvestPeriod":0},{"id":145,"templeteId":6,"rateCode":"RA1709070000022","lendRate":0.02,"rateStatus":4,"rateUserId":1001046,"createTime":1515046143000,"description":"注册加息券","isDeleted":false,"dueTime":1517724560000,"useRule":1000,"validDay":30,"rateType":1,"useRuleInvestPeriod":0}]
         * totalCount : 4
         * hasNextPage : false
         * totalPageCount : 1
         * hasPreviousPage : false
         * dataSizeOfCurrentPage : 4
         * currentPageNo : 1
         */

        private int pageSize;
        private int start;
        private int totalCount;
        private boolean hasNextPage;
        private int totalPageCount;
        private boolean hasPreviousPage;
        private int dataSizeOfCurrentPage;
        private int currentPageNo;
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

        public int getTotalPageCount() {
            return totalPageCount;
        }

        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public int getDataSizeOfCurrentPage() {
            return dataSizeOfCurrentPage;
        }

        public void setDataSizeOfCurrentPage(int dataSizeOfCurrentPage) {
            this.dataSizeOfCurrentPage = dataSizeOfCurrentPage;
        }

        public int getCurrentPageNo() {
            return currentPageNo;
        }

        public void setCurrentPageNo(int currentPageNo) {
            this.currentPageNo = currentPageNo;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 142
             * templeteId : 6
             * rateCode : RA1709070000019
             * lendRate : 0.01
             * rateStatus : 1
             * rateUserId : 1001046
             * createTime : 1515046143000
             * description : 注册加息券
             * isDeleted : false
             * dueTime : 1517724560000
             * useRule : 1000
             * validDay : 30
             * rateType : 1
             * useRuleInvestPeriod : 0
             */

            private int id;
            private int templeteId;
            private String rateCode;
            private double lendRate;
            private int rateStatus;
            private int rateUserId;
            private long createTime;
            private String description;
            private boolean isDeleted;
            private long dueTime;
            private int useRule;
            private int validDay;
            private int rateType;
            private int useRuleInvestPeriod;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTempleteId() {
                return templeteId;
            }

            public void setTempleteId(int templeteId) {
                this.templeteId = templeteId;
            }

            public String getRateCode() {
                return rateCode;
            }

            public void setRateCode(String rateCode) {
                this.rateCode = rateCode;
            }

            public double getLendRate() {
                return lendRate;
            }

            public void setLendRate(double lendRate) {
                this.lendRate = lendRate;
            }

            public int getRateStatus() {
                return rateStatus;
            }

            public void setRateStatus(int rateStatus) {
                this.rateStatus = rateStatus;
            }

            public int getRateUserId() {
                return rateUserId;
            }

            public void setRateUserId(int rateUserId) {
                this.rateUserId = rateUserId;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public boolean isIsDeleted() {
                return isDeleted;
            }

            public void setIsDeleted(boolean isDeleted) {
                this.isDeleted = isDeleted;
            }

            public long getDueTime() {
                return dueTime;
            }

            public void setDueTime(long dueTime) {
                this.dueTime = dueTime;
            }

            public int getUseRule() {
                return useRule;
            }

            public void setUseRule(int useRule) {
                this.useRule = useRule;
            }

            public int getValidDay() {
                return validDay;
            }

            public void setValidDay(int validDay) {
                this.validDay = validDay;
            }

            public int getRateType() {
                return rateType;
            }

            public void setRateType(int rateType) {
                this.rateType = rateType;
            }

            public int getUseRuleInvestPeriod() {
                return useRuleInvestPeriod;
            }

            public void setUseRuleInvestPeriod(int useRuleInvestPeriod) {
                this.useRuleInvestPeriod = useRuleInvestPeriod;
            }
        }
    }
}
