package com.sleep.uulib.bean;

import java.util.List;

/**
 * Created by SleepYM09 on 2017/12/25.
 * <p>
 * blog: www.sleepym09.com
 */

public class ProjectDetailLimit {
    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * page : {"pageSize":20,"start":0,"data":[],"totalCount":0,"hasNextPage":false,"currentPageNo":1,"totalPageCount":0,"hasPreviousPage":false,"dataSizeOfCurrentPage":0}
     * invests : []
     * totalMoney : 50000
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private PageBean page;
    private int totalMoney;
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

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
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
         * pageSize : 20
         * start : 0
         * data : []
         * totalCount : 0
         * hasNextPage : false
         * currentPageNo : 1
         * totalPageCount : 0
         * hasPreviousPage : false
         * dataSizeOfCurrentPage : 0
         */

        private int pageSize;
        private int start;
        private int totalCount;
        private boolean hasNextPage;
        private int currentPageNo;
        private int totalPageCount;
        private boolean hasPreviousPage;
        private int dataSizeOfCurrentPage;
        private List<?> data;

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

        public List<?> getData() {
            return data;
        }

        public void setData(List<?> data) {
            this.data = data;
        }
    }
}
