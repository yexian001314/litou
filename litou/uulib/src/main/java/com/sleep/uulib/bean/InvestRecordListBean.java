package com.sleep.uulib.bean;

import java.util.List;

/**
 * Created by SleepYM09 on 2017/12/5.
 * <p>
 * blog: www.sleepym09.com
 */

public class InvestRecordListBean {
    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * page : {"pageSize":8,"start":0,"data":[{"investOrderId":"I1707210000001","investerUid":1000389121554,"financerUid":1000389121554,"investMoney":1000.12,"lendRate":0.15,"investPeriod":30,"createTime":1500628159000,"startTime":1500628172000,"everydayIncome":12.32,"orderStatus":2,"orderId":"FD1707210000004","phoneNumber":"13734128107"},{"investOrderId":"I1707210000007","investerUid":1000381,"financerUid":1000383,"investMoney":200,"lendRate":0.12,"investPeriod":60,"createTime":1500629561000,"startTime":1500629568000,"everydayIncome":3.94,"orderStatus":2,"orderId":"FD1707210000005","phoneNumber":"18768143482"},{"investOrderId":"I1707210000009","investerUid":1000386,"financerUid":1000383,"investMoney":10000,"lendRate":0.12,"investPeriod":60,"createTime":1500630066000,"startTime":1500630081000,"everydayIncome":197.25,"orderStatus":2,"orderId":"FD1707210000005","phoneNumber":"13760659779"},{"investOrderId":"I1707210000010","investerUid":1000378,"financerUid":1000383,"investMoney":10000,"lendRate":0.07,"investPeriod":90,"createTime":1500630202000,"startTime":1500630237000,"everydayIncome":172.6,"orderStatus":2,"orderId":"FD1707210000011","phoneNumber":"15858223934"},{"investOrderId":"I1707210000011","investerUid":1000386,"financerUid":1000383,"investMoney":14700,"lendRate":0.12,"investPeriod":60,"createTime":1500630205000,"startTime":1500630213000,"everydayIncome":289.96,"orderStatus":2,"orderId":"FD1707210000005","phoneNumber":"13760659779"},{"investOrderId":"I1707210000012","investerUid":1000378,"financerUid":1000385,"investMoney":10000,"lendRate":0.15,"investPeriod":95,"createTime":1500630329000,"startTime":1500630335000,"everydayIncome":390.4,"orderStatus":2,"orderId":"FD1707210000009","phoneNumber":"15858223934"},{"investOrderId":"I1707210000013","investerUid":1000386,"financerUid":1000383,"investMoney":100,"lendRate":0.12,"investPeriod":60,"createTime":1500631149000,"startTime":1500631158000,"everydayIncome":1.96,"orderStatus":2,"orderId":"FD1707210000005","phoneNumber":"13760659779"},{"investOrderId":"I1707210000014","investerUid":1000386,"financerUid":1000383,"investMoney":100,"lendRate":0.12,"investPeriod":90,"createTime":1500631190000,"startTime":1500631201000,"everydayIncome":2.95,"orderStatus":2,"orderId":"FD1707210000002","phoneNumber":"13760659779"}],"totalCount":12,"totalPageCount":2,"hasPreviousPage":false,"dataSizeOfCurrentPage":8,"currentPageNo":1,"hasNextPage":true}
     * invests : []
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private PageBean page;
    private List<?> invests;

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

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<?> getInvests() {
        return invests;
    }

    public void setInvests(List<?> invests) {
        this.invests = invests;
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

    public static class PageBean {
        /**
         * pageSize : 8
         * start : 0
         * data : [{"investOrderId":"I1707210000001","investerUid":1000389121554,"financerUid":1000389121554,"investMoney":1000.12,"lendRate":0.15,"investPeriod":30,"createTime":1500628159000,"startTime":1500628172000,"everydayIncome":12.32,"orderStatus":2,"orderId":"FD1707210000004","phoneNumber":"13734128107"},{"investOrderId":"I1707210000007","investerUid":1000381,"financerUid":1000383,"investMoney":200,"lendRate":0.12,"investPeriod":60,"createTime":1500629561000,"startTime":1500629568000,"everydayIncome":3.94,"orderStatus":2,"orderId":"FD1707210000005","phoneNumber":"18768143482"},{"investOrderId":"I1707210000009","investerUid":1000386,"financerUid":1000383,"investMoney":10000,"lendRate":0.12,"investPeriod":60,"createTime":1500630066000,"startTime":1500630081000,"everydayIncome":197.25,"orderStatus":2,"orderId":"FD1707210000005","phoneNumber":"13760659779"},{"investOrderId":"I1707210000010","investerUid":1000378,"financerUid":1000383,"investMoney":10000,"lendRate":0.07,"investPeriod":90,"createTime":1500630202000,"startTime":1500630237000,"everydayIncome":172.6,"orderStatus":2,"orderId":"FD1707210000011","phoneNumber":"15858223934"},{"investOrderId":"I1707210000011","investerUid":1000386,"financerUid":1000383,"investMoney":14700,"lendRate":0.12,"investPeriod":60,"createTime":1500630205000,"startTime":1500630213000,"everydayIncome":289.96,"orderStatus":2,"orderId":"FD1707210000005","phoneNumber":"13760659779"},{"investOrderId":"I1707210000012","investerUid":1000378,"financerUid":1000385,"investMoney":10000,"lendRate":0.15,"investPeriod":95,"createTime":1500630329000,"startTime":1500630335000,"everydayIncome":390.4,"orderStatus":2,"orderId":"FD1707210000009","phoneNumber":"15858223934"},{"investOrderId":"I1707210000013","investerUid":1000386,"financerUid":1000383,"investMoney":100,"lendRate":0.12,"investPeriod":60,"createTime":1500631149000,"startTime":1500631158000,"everydayIncome":1.96,"orderStatus":2,"orderId":"FD1707210000005","phoneNumber":"13760659779"},{"investOrderId":"I1707210000014","investerUid":1000386,"financerUid":1000383,"investMoney":100,"lendRate":0.12,"investPeriod":90,"createTime":1500631190000,"startTime":1500631201000,"everydayIncome":2.95,"orderStatus":2,"orderId":"FD1707210000002","phoneNumber":"13760659779"}]
         * totalCount : 12
         * totalPageCount : 2
         * hasPreviousPage : false
         * dataSizeOfCurrentPage : 8
         * currentPageNo : 1
         * hasNextPage : true
         */

        private int pageSize;
        private int start;
        private int totalCount;
        private int totalPageCount;
        private boolean hasPreviousPage;
        private int dataSizeOfCurrentPage;
        private int currentPageNo;
        private boolean hasNextPage;
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

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * investOrderId : I1707210000001
             * investerUid : 1000389121554
             * financerUid : 1000389121554
             * investMoney : 1000.12
             * lendRate : 0.15
             * investPeriod : 30
             * createTime : 1500628159000
             * startTime : 1500628172000
             * everydayIncome : 12.32
             * orderStatus : 2
             * orderId : FD1707210000004
             * phoneNumber : 13734128107
             */

            private String investOrderId;
            private long investerUid;
            private long financerUid;
            private double investMoney;
            private double lendRate;
            private int investPeriod;
            private long createTime;
            private long startTime;
            private double everydayIncome;
            private int orderStatus;
            private String orderId;
            private String phoneNumber;

            public String getInvestOrderId() {
                return investOrderId;
            }

            public void setInvestOrderId(String investOrderId) {
                this.investOrderId = investOrderId;
            }

            public long getInvesterUid() {
                return investerUid;
            }

            public void setInvesterUid(long investerUid) {
                this.investerUid = investerUid;
            }

            public long getFinancerUid() {
                return financerUid;
            }

            public void setFinancerUid(long financerUid) {
                this.financerUid = financerUid;
            }

            public double getInvestMoney() {
                return investMoney;
            }

            public void setInvestMoney(double investMoney) {
                this.investMoney = investMoney;
            }

            public double getLendRate() {
                return lendRate;
            }

            public void setLendRate(double lendRate) {
                this.lendRate = lendRate;
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

            public long getStartTime() {
                return startTime;
            }

            public void setStartTime(long startTime) {
                this.startTime = startTime;
            }

            public double getEverydayIncome() {
                return everydayIncome;
            }

            public void setEverydayIncome(double everydayIncome) {
                this.everydayIncome = everydayIncome;
            }

            public int getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(int orderStatus) {
                this.orderStatus = orderStatus;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }
        }
    }
}
