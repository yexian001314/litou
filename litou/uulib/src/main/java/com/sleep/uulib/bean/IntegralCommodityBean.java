package com.sleep.uulib.bean;

import java.util.List;

/**
 * Created by SleepYM09 on 2018/1/18.
 * <p>
 * blog: www.sleepym09.com
 */

public class IntegralCommodityBean {
    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * itemTypeEOList : [{"id":9,"createTime":1510103596000,"itemName":"30元充值卡","itemModel":"30元","itemType":10,"remark":"","stock":800,"state":"0","requireoints":11600,"imageUrl":""},{"id":8,"createTime":1510103596000,"itemName":"50元充值卡","itemModel":"50元","itemType":10,"remark":"","stock":800,"state":"0","requireoints":19000,"imageUrl":""},{"id":7,"createTime":1510103596000,"itemName":"100元充值卡","itemModel":"100元","itemType":10,"remark":"","stock":800,"state":"0","requireoints":36000,"imageUrl":""},{"id":6,"createTime":1510103596000,"itemName":"50元京东卡","itemModel":"50元","itemType":11,"remark":"","stock":800,"state":"0","requireoints":19600,"imageUrl":""},{"id":5,"createTime":1510103596000,"itemName":"100元京东卡","itemModel":"100元","itemType":11,"remark":"","stock":800,"state":"0","requireoints":38000,"imageUrl":""},{"id":4,"createTime":1510103596000,"itemName":"200元京东卡","itemModel":"200元","itemType":11,"remark":"","stock":800,"state":"0","requireoints":72000,"imageUrl":""}]
     * totalCount : 9
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private int totalCount;
    private List<ItemTypeEOListBean> itemTypeEOList;

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

    public List<ItemTypeEOListBean> getItemTypeEOList() {
        return itemTypeEOList;
    }

    public void setItemTypeEOList(List<ItemTypeEOListBean> itemTypeEOList) {
        this.itemTypeEOList = itemTypeEOList;
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

    public static class ItemTypeEOListBean {
        /**
         * id : 9
         * createTime : 1510103596000
         * itemName : 30元充值卡
         * itemModel : 30元
         * itemType : 10
         * remark :
         * stock : 800
         * state : 0
         * requireoints : 11600
         * imageUrl :
         */

        private int id;
        private long createTime;
        private String itemName;
        private String itemModel;
        private int itemType;
        private String remark;
        private int stock;
        private String state;
        private int requireoints;
        private String imageUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getItemModel() {
            return itemModel;
        }

        public void setItemModel(String itemModel) {
            this.itemModel = itemModel;
        }

        public int getItemType() {
            return itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getRequireoints() {
            return requireoints;
        }

        public void setRequireoints(int requireoints) {
            this.requireoints = requireoints;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
