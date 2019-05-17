package com.sleep.uulib.bean;

import java.util.List;

/**
 * Created by SleepYM09 on 2017/12/20.
 * <p>
 * blog: www.sleepym09.com
 */

public class RechargeLimitBean {

    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * bankLimitModels : [{"bank":"工商银行","bankCode":"0102","cardType":"借记卡","orgNumber":"0801020000","singleLimit":"100000","singleDayLimit":"200000","singleMonthLimit":"1000000","bankPhotoUrl":"https://www.uumoney.cn/bankphoto/gongshang.png","countLimit":5},{"bank":"农业银行","bankCode":"0103","cardType":"借记卡","orgNumber":"0801030000","singleLimit":"20000","singleDayLimit":"100000","singleMonthLimit":"1000000","bankPhotoUrl":"https://www.uumoney.cn/bankphoto/nongye.png","countLimit":null},{"bank":"中国银行","bankCode":"0104","cardType":"借记卡","orgNumber":"0801040000","singleLimit":"50000","singleDayLimit":"200000","singleMonthLimit":"1000000","bankPhotoUrl":"https://www.uumoney.cn/bankphoto/zhongguo.png","countLimit":null},{"bank":"建设银行","bankCode":"0105","cardType":"借记卡","orgNumber":"0801050000","singleLimit":"50000","singleDayLimit":"100000","singleMonthLimit":"1000000","bankPhotoUrl":"https://www.uumoney.cn/bankphoto/jianshe.png","countLimit":null},{"bank":"邮储银行","bankCode":"0403","cardType":"借记卡","orgNumber":"0801000000","singleLimit":"100000","singleDayLimit":"200000","singleMonthLimit":"1000000","bankPhotoUrl":"https://www.uumoney.cn/bankphoto/youzheng.png","countLimit":null},{"bank":"平安银行","bankCode":"0307","cardType":"借记卡","orgNumber":"0804100000","singleLimit":"100000","singleDayLimit":"200000","singleMonthLimit":"1000000","bankPhotoUrl":"https://www.uumoney.cn/bankphoto/pingan.png","countLimit":null},{"bank":"民生银行","bankCode":"0305","cardType":"借记卡","orgNumber":"0803050000","singleLimit":"100000","singleDayLimit":"200000","singleMonthLimit":"1000000","bankPhotoUrl":"https://www.uumoney.cn/bankphoto/minsheng.png","countLimit":null},{"bank":"光大银行","bankCode":"0303","cardType":"借记卡","orgNumber":"0803030000","singleLimit":"50000","singleDayLimit":"50000","singleMonthLimit":"1000000","bankPhotoUrl":"https://www.uumoney.cn/bankphoto/guangda.png","countLimit":null},{"bank":"广发银行","bankCode":"0306","cardType":"借记卡","orgNumber":"0803060000","singleLimit":"100000","singleDayLimit":"200000","singleMonthLimit":"1000000","bankPhotoUrl":"https://www.uumoney.cn/bankphoto/guangfa.png","countLimit":null},{"bank":"中信银行","bankCode":"0302","cardType":"借记卡","orgNumber":"0803020000","singleLimit":"10000","singleDayLimit":"10000","singleMonthLimit":"20000","bankPhotoUrl":"https://www.uumoney.cn/bankphoto/zhongxin.png","countLimit":null},{"bank":"兴业银行","bankCode":"0309","cardType":"借记卡","orgNumber":"0803090000","singleLimit":"50000","singleDayLimit":"50000","singleMonthLimit":"1000000","bankPhotoUrl":"https://www.uumoney.cn/bankphoto/xingye.png","countLimit":null},{"bank":"华夏银行","bankCode":"0304","cardType":"借记卡","orgNumber":"0803040000","singleLimit":"100000","singleDayLimit":"200000","singleMonthLimit":"1000000","bankPhotoUrl":"https://www.uumoney.cn/bankphoto/huaxia.png","countLimit":null},{"bank":"招商银行","bankCode":"0308","cardType":"借记卡","orgNumber":"0803080000","singleLimit":"50000","singleDayLimit":"200000","singleMonthLimit":"1000000","bankPhotoUrl":"https://www.uumoney.cn/bankphoto/zhaoshang.png","countLimit":null},{"bank":"浦发银行","bankCode":"0310","cardType":"借记卡","orgNumber":"0803100000","singleLimit":"100000","singleDayLimit":"200000","singleMonthLimit":"1000000","bankPhotoUrl":"https://www.uumoney.cn/bankphoto/pufa.png","countLimit":null},{"bank":"交通银行","bankCode":"0301","cardType":"借记卡","orgNumber":"0803010000","singleLimit":"50000","singleDayLimit":"50000","singleMonthLimit":"1000000","bankPhotoUrl":"https://www.uumoney.cn/bankphoto/jiaotong.png","countLimit":null},{"bank":"北京银行","bankCode":null,"cardType":"借记卡","orgNumber":"0804031000","singleLimit":"50000","singleDayLimit":"50000","singleMonthLimit":"1000000","bankPhotoUrl":"https://www.uumoney.cn/bankphoto/beijing.png","countLimit":null},{"bank":"上海银行","bankCode":null,"cardType":"借记卡","orgNumber":"0804010000","singleLimit":"50000","singleDayLimit":"50000","singleMonthLimit":"1000000","bankPhotoUrl":"https://www.uumoney.cn/bankphoto/shanghai.png","countLimit":null}]
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private List<BankLimitModelsBean> bankLimitModels;

    @Override
    public String toString() {
        return "RechargeLimitBean{" +
                "responseStatus=" + responseStatus +
                ", success=" + success +
                ", isException=" + isException +
                ", bankLimitModels=" + bankLimitModels +
                '}';
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

    public List<BankLimitModelsBean> getBankLimitModels() {
        return bankLimitModels;
    }

    public void setBankLimitModels(List<BankLimitModelsBean> bankLimitModels) {
        this.bankLimitModels = bankLimitModels;
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

    public static class BankLimitModelsBean {
        @Override
        public String toString() {
            return "BankLimitModelsBean{" +
                    "bank='" + bank + '\'' +
                    ", bankCode='" + bankCode + '\'' +
                    ", cardType='" + cardType + '\'' +
                    ", orgNumber='" + orgNumber + '\'' +
                    ", singleLimit='" + singleLimit + '\'' +
                    ", singleDayLimit='" + singleDayLimit + '\'' +
                    ", singleMonthLimit='" + singleMonthLimit + '\'' +
                    ", bankPhotoUrl='" + bankPhotoUrl + '\'' +
                    ", countLimit=" + countLimit +
                    '}';
        }

        /**
         * bank : 工商银行
         * bankCode : 0102
         * cardType : 借记卡
         * orgNumber : 0801020000
         * singleLimit : 100000
         * singleDayLimit : 200000
         * singleMonthLimit : 1000000
         * bankPhotoUrl : https://www.uumoney.cn/bankphoto/gongshang.png
         * countLimit : 5
         */

        private String bank;
        private String bankCode;
        private String cardType;
        private String orgNumber;
        private String singleLimit;
        private String singleDayLimit;
        private String singleMonthLimit;
        private String bankPhotoUrl;
        private int countLimit;

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public String getOrgNumber() {
            return orgNumber;
        }

        public void setOrgNumber(String orgNumber) {
            this.orgNumber = orgNumber;
        }

        public String getSingleLimit() {
            return singleLimit;
        }

        public void setSingleLimit(String singleLimit) {
            this.singleLimit = singleLimit;
        }

        public String getSingleDayLimit() {
            return singleDayLimit;
        }

        public void setSingleDayLimit(String singleDayLimit) {
            this.singleDayLimit = singleDayLimit;
        }

        public String getSingleMonthLimit() {
            return singleMonthLimit;
        }

        public void setSingleMonthLimit(String singleMonthLimit) {
            this.singleMonthLimit = singleMonthLimit;
        }

        public String getBankPhotoUrl() {
            return bankPhotoUrl;
        }

        public void setBankPhotoUrl(String bankPhotoUrl) {
            this.bankPhotoUrl = bankPhotoUrl;
        }

        public int getCountLimit() {
            return countLimit;
        }

        public void setCountLimit(int countLimit) {
            this.countLimit = countLimit;
        }
    }
}
