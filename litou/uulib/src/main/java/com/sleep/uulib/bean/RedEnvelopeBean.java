package com.sleep.uulib.bean;

import java.util.List;

/**
 * Created by SleepYM09 on 2017/12/12.
 * <p>
 * blog: www.sleepym09.com
 */

public class RedEnvelopeBean {
    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * projects : {"pageSize":8,"start":0,"data":[{"id":913,"templeteId":10,"packetsCode":"RE1707210000007","packetsAmount":20.12,"packetsStatus":1,"packetsUserId":1000378707210,"packetsUseTime":1500868140000,"packetsUseType":1,"financingOrderId":"FD1707210000009","investOrderId":"I1707240000002","packetsIncome":0.77,"createTime":1500868140000,"updateTime":1500868140000,"description":"注册红包","isDeleted":false,"dueTime":1516204800000,"validDay":180,"useRule":1000.12,"packetsType":1,"useRuleInvestPeriod":90},{"id":907,"templeteId":4,"packetsCode":"RE1707210000001","packetsAmount":5,"packetsStatus":1,"packetsUserId":1000378,"createTime":1500626286000,"description":"注册红包","isDeleted":false,"dueTime":1516204800000,"validDay":180,"useRule":1000,"packetsType":1,"useRuleInvestPeriod":30},{"id":908,"templeteId":5,"packetsCode":"RE1707210000002","packetsAmount":25,"packetsStatus":1,"packetsUserId":1000378,"createTime":1500626286000,"description":"注册红包","isDeleted":false,"dueTime":1516204800000,"validDay":180,"useRule":5000,"packetsType":1,"useRuleInvestPeriod":30},{"id":909,"templeteId":6,"packetsCode":"RE1707210000003","packetsAmount":50,"packetsStatus":1,"packetsUserId":1000378,"createTime":1500626286000,"description":"注册红包","isDeleted":false,"dueTime":1516204800000,"validDay":180,"useRule":10000,"packetsType":1,"useRuleInvestPeriod":30},{"id":910,"templeteId":7,"packetsCode":"RE1707210000004","packetsAmount":12,"packetsStatus":1,"packetsUserId":1000378,"createTime":1500626286000,"description":"注册红包","isDeleted":false,"dueTime":1516204800000,"validDay":180,"useRule":1000,"packetsType":1,"useRuleInvestPeriod":60},{"id":911,"templeteId":8,"packetsCode":"RE1707210000005","packetsAmount":60,"packetsStatus":1,"packetsUserId":1000378,"createTime":1500626286000,"description":"注册红包","isDeleted":false,"dueTime":1516204800000,"validDay":180,"useRule":5000,"packetsType":1,"useRuleInvestPeriod":60},{"id":914,"templeteId":11,"packetsCode":"RE1707210000008","packetsAmount":100,"packetsStatus":1,"packetsUserId":1000378,"createTime":1500626286000,"description":"注册红包","isDeleted":false,"dueTime":1516204800000,"validDay":180,"useRule":5000,"packetsType":1,"useRuleInvestPeriod":90},{"id":915,"templeteId":12,"packetsCode":"RE1707210000009","packetsAmount":200,"packetsStatus":1,"packetsUserId":1000378,"createTime":1500626286000,"description":"注册红包","isDeleted":false,"dueTime":1516204800000,"validDay":180,"useRule":10000,"packetsType":1,"useRuleInvestPeriod":90}],"totalCount":9,"hasNextPage":true,"hasPreviousPage":false,"currentPageNo":1,"totalPageCount":2,"dataSizeOfCurrentPage":8}
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
         * data : [{"id":913,"templeteId":10,"packetsCode":"RE1707210000007","packetsAmount":20.12,"packetsStatus":1,"packetsUserId":1000378707210,"packetsUseTime":1500868140000,"packetsUseType":1,"financingOrderId":"FD1707210000009","investOrderId":"I1707240000002","packetsIncome":0.77,"createTime":1500868140000,"updateTime":1500868140000,"description":"注册红包","isDeleted":false,"dueTime":1516204800000,"validDay":180,"useRule":1000.12,"packetsType":1,"useRuleInvestPeriod":90},{"id":907,"templeteId":4,"packetsCode":"RE1707210000001","packetsAmount":5,"packetsStatus":1,"packetsUserId":1000378,"createTime":1500626286000,"description":"注册红包","isDeleted":false,"dueTime":1516204800000,"validDay":180,"useRule":1000,"packetsType":1,"useRuleInvestPeriod":30},{"id":908,"templeteId":5,"packetsCode":"RE1707210000002","packetsAmount":25,"packetsStatus":1,"packetsUserId":1000378,"createTime":1500626286000,"description":"注册红包","isDeleted":false,"dueTime":1516204800000,"validDay":180,"useRule":5000,"packetsType":1,"useRuleInvestPeriod":30},{"id":909,"templeteId":6,"packetsCode":"RE1707210000003","packetsAmount":50,"packetsStatus":1,"packetsUserId":1000378,"createTime":1500626286000,"description":"注册红包","isDeleted":false,"dueTime":1516204800000,"validDay":180,"useRule":10000,"packetsType":1,"useRuleInvestPeriod":30},{"id":910,"templeteId":7,"packetsCode":"RE1707210000004","packetsAmount":12,"packetsStatus":1,"packetsUserId":1000378,"createTime":1500626286000,"description":"注册红包","isDeleted":false,"dueTime":1516204800000,"validDay":180,"useRule":1000,"packetsType":1,"useRuleInvestPeriod":60},{"id":911,"templeteId":8,"packetsCode":"RE1707210000005","packetsAmount":60,"packetsStatus":1,"packetsUserId":1000378,"createTime":1500626286000,"description":"注册红包","isDeleted":false,"dueTime":1516204800000,"validDay":180,"useRule":5000,"packetsType":1,"useRuleInvestPeriod":60},{"id":914,"templeteId":11,"packetsCode":"RE1707210000008","packetsAmount":100,"packetsStatus":1,"packetsUserId":1000378,"createTime":1500626286000,"description":"注册红包","isDeleted":false,"dueTime":1516204800000,"validDay":180,"useRule":5000,"packetsType":1,"useRuleInvestPeriod":90},{"id":915,"templeteId":12,"packetsCode":"RE1707210000009","packetsAmount":200,"packetsStatus":1,"packetsUserId":1000378,"createTime":1500626286000,"description":"注册红包","isDeleted":false,"dueTime":1516204800000,"validDay":180,"useRule":10000,"packetsType":1,"useRuleInvestPeriod":90}]
         * totalCount : 9
         * hasNextPage : true
         * hasPreviousPage : false
         * currentPageNo : 1
         * totalPageCount : 2
         * dataSizeOfCurrentPage : 8
         */

        private int pageSize;
        private int start;
        private int totalCount;
        private boolean hasNextPage;
        private boolean hasPreviousPage;
        private int currentPageNo;
        private int totalPageCount;
        private int dataSizeOfCurrentPage;
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

        public int getDataSizeOfCurrentPage() {
            return dataSizeOfCurrentPage;
        }

        public void setDataSizeOfCurrentPage(int dataSizeOfCurrentPage) {
            this.dataSizeOfCurrentPage = dataSizeOfCurrentPage;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 913
             * templeteId : 10
             * packetsCode : RE1707210000007
             * packetsAmount : 20.12
             * packetsStatus : 1
             * packetsUserId : 1000378707210
             * packetsUseTime : 1500868140000
             * packetsUseType : 1
             * financingOrderId : FD1707210000009
             * investOrderId : I1707240000002
             * packetsIncome : 0.77
             * createTime : 1500868140000
             * updateTime : 1500868140000
             * description : 注册红包
             * isDeleted : false
             * dueTime : 1516204800000
             * validDay : 180
             * useRule : 1000.12
             * packetsType : 1
             * useRuleInvestPeriod : 90
             * investType：1
             */

            private int id;
            private int templeteId;
            private String packetsCode;
            private double packetsAmount;
            private int packetsStatus;
            private long packetsUserId;
            private long packetsUseTime;
            private int packetsUseType;
            private String financingOrderId;
            private String investOrderId;
            private double packetsIncome;
            private long createTime;
            private long updateTime;
            private String description;
            private boolean isDeleted;
            private long dueTime;
            private int validDay;
            private double useRule;
            private int packetsType;
            private int useRuleInvestPeriod;
            private int investType;

            public int getInvestType() {
                return investType;
            }

            public void setInvestType(int investType) {
                this.investType = investType;
            }

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

            public String getPacketsCode() {
                return packetsCode;
            }

            public void setPacketsCode(String packetsCode) {
                this.packetsCode = packetsCode;
            }

            public double getPacketsAmount() {
                return packetsAmount;
            }

            public void setPacketsAmount(double packetsAmount) {
                this.packetsAmount = packetsAmount;
            }

            public int getPacketsStatus() {
                return packetsStatus;
            }

            public void setPacketsStatus(int packetsStatus) {
                this.packetsStatus = packetsStatus;
            }

            public long getPacketsUserId() {
                return packetsUserId;
            }

            public void setPacketsUserId(long packetsUserId) {
                this.packetsUserId = packetsUserId;
            }

            public long getPacketsUseTime() {
                return packetsUseTime;
            }

            public void setPacketsUseTime(long packetsUseTime) {
                this.packetsUseTime = packetsUseTime;
            }

            public int getPacketsUseType() {
                return packetsUseType;
            }

            public void setPacketsUseType(int packetsUseType) {
                this.packetsUseType = packetsUseType;
            }

            public String getFinancingOrderId() {
                return financingOrderId;
            }

            public void setFinancingOrderId(String financingOrderId) {
                this.financingOrderId = financingOrderId;
            }

            public String getInvestOrderId() {
                return investOrderId;
            }

            public void setInvestOrderId(String investOrderId) {
                this.investOrderId = investOrderId;
            }

            public double getPacketsIncome() {
                return packetsIncome;
            }

            public void setPacketsIncome(double packetsIncome) {
                this.packetsIncome = packetsIncome;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
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

            public int getValidDay() {
                return validDay;
            }

            public void setValidDay(int validDay) {
                this.validDay = validDay;
            }

            public double getUseRule() {
                return useRule;
            }

            public void setUseRule(double useRule) {
                this.useRule = useRule;
            }

            public int getPacketsType() {
                return packetsType;
            }

            public void setPacketsType(int packetsType) {
                this.packetsType = packetsType;
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
