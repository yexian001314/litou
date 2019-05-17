package com.sleep.uulib.bean;

import java.util.List;

/**
 * Created by SleepYM09 on 2017/12/6.
 * <p>
 * blog: www.sleepym09.com
 */

public class QueryBankBean {


    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * bankCardEOs : [{"id":1000424,"cardName":"平**","isSelectChecked":"true","cardCode":"6217002870015407669","bankCode":"0105","bankName":"中国建设银行","singleLimit":50000,"singleDayLimit":100000,"singleMonthLimit":1000000,"countLimit":null}]
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private List<BankCardEOsBean> bankCardEOs;

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

    public List<BankCardEOsBean> getBankCardEOs() {
        return bankCardEOs;
    }

    public void setBankCardEOs(List<BankCardEOsBean> bankCardEOs) {
        this.bankCardEOs = bankCardEOs;
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

    public static class BankCardEOsBean {
        /**
         * id : 1000424
         * cardName : 平**
         * isSelectChecked : true
         * cardCode : 6217002870015407669
         * bankCode : 0105
         * bankName : 中国建设银行
         * singleLimit : 50000
         * singleDayLimit : 100000
         * singleMonthLimit : 1000000
         * countLimit : null
         */

        private int id;
        private String cardName;
        private String isSelectChecked;
        private String cardCode;
        private String bankCode;
        private String bankName;
        private int singleLimit;
        private int singleDayLimit;
        private int singleMonthLimit;
        private int countLimit;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getIsSelectChecked() {
            return isSelectChecked;
        }

        public void setIsSelectChecked(String isSelectChecked) {
            this.isSelectChecked = isSelectChecked;
        }

        public String getCardCode() {
            return cardCode;
        }

        public void setCardCode(String cardCode) {
            this.cardCode = cardCode;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public int getSingleLimit() {
            return singleLimit;
        }

        public void setSingleLimit(int singleLimit) {
            this.singleLimit = singleLimit;
        }

        public int getSingleDayLimit() {
            return singleDayLimit;
        }

        public void setSingleDayLimit(int singleDayLimit) {
            this.singleDayLimit = singleDayLimit;
        }

        public int getSingleMonthLimit() {
            return singleMonthLimit;
        }

        public void setSingleMonthLimit(int singleMonthLimit) {
            this.singleMonthLimit = singleMonthLimit;
        }

        public int getCountLimit() {
            return countLimit;
        }

        public void setCountLimit(int countLimit) {
            this.countLimit = countLimit;
        }
    }
}
