package com.sleep.uulib.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by SleepYM09 on 2017/12/14.
 * <p>
 * blog: www.sleepym09.com
 */

public class ChooseWelfareBean {

    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * datas : [{"id":546000000000,"type":1,"templeteId":1,"packetsCode":"RE1707130000067","packetsAmount":5,"packetsStatus":1,"packetsUserId":1000339,"packetsUseTime":1500105645000,"packetsUseType":1,"financingOrderId":"","investOrderId":"","packetsIncome":2,"createTime":1499946618000,"createUserId":546000000000,"updateTime":1500105645000,"updateUserId":546000000000,"description":"注册红包","isDeleted":false,"dueTime":1507737600000,"validDay":90,"useRule":500,"packetsType":1,"useRuleInvestPeriod":0},{"id":547,"type":1,"templeteId":1,"packetsCode":"RE1707130000068","packetsAmount":5,"packetsStatus":1,"packetsUserId":1000339,"packetsUseTime":null,"packetsUseType":null,"financingOrderId":null,"investOrderId":null,"packetsIncome":null,"createTime":1499946618000,"createUserId":null,"updateTime":null,"updateUserId":null,"description":"注册红包","isDeleted":false,"dueTime":1507737600000,"validDay":90,"useRule":500,"packetsType":1,"useRuleInvestPeriod":0},{"id":548,"type":1,"templeteId":1,"packetsCode":"RE1707130000069","packetsAmount":5,"packetsStatus":1,"packetsUserId":1000339,"packetsUseTime":null,"packetsUseType":null,"financingOrderId":null,"investOrderId":null,"packetsIncome":null,"createTime":1499946618000,"createUserId":null,"updateTime":null,"updateUserId":null,"description":"注册红包","isDeleted":false,"dueTime":1507737600000,"validDay":90,"useRule":500,"packetsType":1,"useRuleInvestPeriod":0},{"id":549,"type":1,"templeteId":1,"packetsCode":"RE1707130000070","packetsAmount":5,"packetsStatus":1,"packetsUserId":1000339,"packetsUseTime":null,"packetsUseType":null,"financingOrderId":null,"investOrderId":null,"packetsIncome":null,"createTime":1499946618000,"createUserId":null,"updateTime":null,"updateUserId":null,"description":"注册红包","isDeleted":false,"dueTime":1507737600000,"validDay":90,"useRule":500,"packetsType":1,"useRuleInvestPeriod":0},{"id":550,"type":1,"templeteId":1,"packetsCode":"RE1707130000071","packetsAmount":5,"packetsStatus":1,"packetsUserId":1000339,"packetsUseTime":null,"packetsUseType":null,"financingOrderId":null,"investOrderId":null,"packetsIncome":null,"createTime":1499946618000,"createUserId":null,"updateTime":null,"updateUserId":null,"description":"注册红包","isDeleted":false,"dueTime":1507737600000,"validDay":90,"useRule":500,"packetsType":1,"useRuleInvestPeriod":0},{"id":551,"type":1,"templeteId":1,"packetsCode":"RE1707130000072","packetsAmount":5,"packetsStatus":1,"packetsUserId":1000339,"packetsUseTime":null,"packetsUseType":null,"financingOrderId":null,"investOrderId":null,"packetsIncome":null,"createTime":1499946618000,"createUserId":null,"updateTime":null,"updateUserId":null,"description":"注册红包","isDeleted":false,"dueTime":1507737600000,"validDay":90,"useRule":500,"packetsType":1,"useRuleInvestPeriod":0},{"id":552,"type":1,"templeteId":2,"packetsCode":"RE1707130000073","packetsAmount":10,"packetsStatus":1,"packetsUserId":1000339,"packetsUseTime":null,"packetsUseType":null,"financingOrderId":null,"investOrderId":null,"packetsIncome":null,"createTime":1499946618000,"createUserId":null,"updateTime":null,"updateUserId":null,"description":"注册红包","isDeleted":false,"dueTime":1507737600000,"validDay":90,"useRule":1000,"packetsType":1,"useRuleInvestPeriod":0},{"id":553,"type":1,"templeteId":2,"packetsCode":"RE1707130000074","packetsAmount":10,"packetsStatus":1,"packetsUserId":1000339,"packetsUseTime":null,"packetsUseType":null,"financingOrderId":null,"investOrderId":null,"packetsIncome":null,"createTime":1499946618000,"createUserId":null,"updateTime":null,"updateUserId":null,"description":"注册红包","isDeleted":false,"dueTime":1507737600000,"validDay":90,"useRule":1000,"packetsType":1,"useRuleInvestPeriod":0},{"id":554,"type":1,"templeteId":2,"packetsCode":"RE1707130000075","packetsAmount":10,"packetsStatus":1,"packetsUserId":1000339,"packetsUseTime":null,"packetsUseType":null,"financingOrderId":null,"investOrderId":null,"packetsIncome":null,"createTime":1499946618000,"createUserId":null,"updateTime":null,"updateUserId":null,"description":"注册红包","isDeleted":false,"dueTime":1507737600000,"validDay":90,"useRule":1000,"packetsType":1,"useRuleInvestPeriod":0},{"id":555,"type":1,"templeteId":3,"packetsCode":"RE1707130000076","packetsAmount":20,"packetsStatus":1,"packetsUserId":1000339,"packetsUseTime":null,"packetsUseType":null,"financingOrderId":null,"investOrderId":null,"packetsIncome":null,"createTime":1499946618000,"createUserId":null,"updateTime":null,"updateUserId":null,"description":"注册红包","isDeleted":false,"dueTime":1507737600000,"validDay":90,"useRule":2000,"packetsType":1,"useRuleInvestPeriod":0},{"id":556,"type":1,"templeteId":3,"packetsCode":"RE1707130000077","packetsAmount":20,"packetsStatus":1,"packetsUserId":1000339,"packetsUseTime":null,"packetsUseType":null,"financingOrderId":null,"investOrderId":null,"packetsIncome":null,"createTime":1499946618000,"createUserId":null,"updateTime":null,"updateUserId":null,"description":"注册红包","isDeleted":false,"dueTime":1507737600000,"validDay":90,"useRule":2000,"packetsType":1,"useRuleInvestPeriod":0}]
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private List<DatasBean> datas;

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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
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

    public static class DatasBean implements Parcelable {
        /**
         * id : 546000000000
         * type : 1
         * templeteId : 1
         * packetsCode : RE1707130000067
         * packetsAmount : 5
         * packetsStatus : 1
         * packetsUserId : 1000339
         * packetsUseTime : 1500105645000
         * packetsUseType : 1
         * financingOrderId :
         * investOrderId :
         * packetsIncome : 2.0
         * createTime : 1499946618000
         * createUserId : 546000000000
         * updateTime : 1500105645000
         * updateUserId : 546000000000
         * description : 注册红包
         * isDeleted : false
         * dueTime : 1507737600000
         * validDay : 90
         * useRule : 500
         * packetsType : 1
         * useRuleInvestPeriod : 0
         */

        private long id;
        private int type;
        private int templeteId;
        private String packetsCode;
        private double packetsAmount;
        private int packetsStatus;
        private long packetsUserId;
        private long packetsUseTime;
        private int packetsUseType;
        private int investType;
        private String financingOrderId;
        private String investOrderId;
        private double packetsIncome;
        private long createTime;
        private long createUserId;
        private long updateTime;
        private long updateUserId;
        private String description;
        private boolean isDeleted;
        private long dueTime;
        private int validDay;
        private int useRule;
        private int packetsType;
        private int useRuleInvestPeriod;
        private int subjectType;

        public int getSubjectType() {
            return subjectType;
        }

        public void setSubjectType(int subjectType) {
            this.subjectType = subjectType;
        }

        public int getInvestType() {
            return investType;
        }

        public void setInvestType(int investType) {
            this.investType = investType;
        }

        public long getId() {
            return id;
        }

        public DatasBean setId(long id) {
            this.id = id;
            return this;
        }

        public int getType() {
            return type;
        }

        public DatasBean setType(int type) {
            this.type = type;
            return this;
        }

        public int getTempleteId() {
            return templeteId;
        }

        public DatasBean setTempleteId(int templeteId) {
            this.templeteId = templeteId;
            return this;
        }

        public String getPacketsCode() {
            return packetsCode;
        }

        public DatasBean setPacketsCode(String packetsCode) {
            this.packetsCode = packetsCode;
            return this;
        }

        public double getPacketsAmount() {
            return packetsAmount;
        }

        public DatasBean setPacketsAmount(double packetsAmount) {
            this.packetsAmount = packetsAmount;
            return this;
        }

        public int getPacketsStatus() {
            return packetsStatus;
        }

        public DatasBean setPacketsStatus(int packetsStatus) {
            this.packetsStatus = packetsStatus;
            return this;
        }

        public long getPacketsUserId() {
            return packetsUserId;
        }

        public DatasBean setPacketsUserId(long packetsUserId) {
            this.packetsUserId = packetsUserId;
            return this;
        }

        public long getPacketsUseTime() {
            return packetsUseTime;
        }

        public DatasBean setPacketsUseTime(long packetsUseTime) {
            this.packetsUseTime = packetsUseTime;
            return this;
        }

        public int getPacketsUseType() {
            return packetsUseType;
        }

        public DatasBean setPacketsUseType(int packetsUseType) {
            this.packetsUseType = packetsUseType;
            return this;
        }

        public String getFinancingOrderId() {
            return financingOrderId;
        }

        public DatasBean setFinancingOrderId(String financingOrderId) {
            this.financingOrderId = financingOrderId;
            return this;
        }

        public String getInvestOrderId() {
            return investOrderId;
        }

        public DatasBean setInvestOrderId(String investOrderId) {
            this.investOrderId = investOrderId;
            return this;
        }

        public double getPacketsIncome() {
            return packetsIncome;
        }

        public DatasBean setPacketsIncome(double packetsIncome) {
            this.packetsIncome = packetsIncome;
            return this;
        }

        public long getCreateTime() {
            return createTime;
        }

        public DatasBean setCreateTime(long createTime) {
            this.createTime = createTime;
            return this;
        }

        public long getCreateUserId() {
            return createUserId;
        }

        public DatasBean setCreateUserId(long createUserId) {
            this.createUserId = createUserId;
            return this;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public DatasBean setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public long getUpdateUserId() {
            return updateUserId;
        }

        public DatasBean setUpdateUserId(long updateUserId) {
            this.updateUserId = updateUserId;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public DatasBean setDescription(String description) {
            this.description = description;
            return this;
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public DatasBean setDeleted(boolean deleted) {
            isDeleted = deleted;
            return this;
        }

        public long getDueTime() {
            return dueTime;
        }

        public DatasBean setDueTime(long dueTime) {
            this.dueTime = dueTime;
            return this;
        }

        public int getValidDay() {
            return validDay;
        }

        public DatasBean setValidDay(int validDay) {
            this.validDay = validDay;
            return this;
        }

        public int getUseRule() {
            return useRule;
        }

        public DatasBean setUseRule(int useRule) {
            this.useRule = useRule;
            return this;
        }

        public int getPacketsType() {
            return packetsType;
        }

        public DatasBean setPacketsType(int packetsType) {
            this.packetsType = packetsType;
            return this;
        }

        public int getUseRuleInvestPeriod() {
            return useRuleInvestPeriod;
        }

        public DatasBean setUseRuleInvestPeriod(int useRuleInvestPeriod) {
            this.useRuleInvestPeriod = useRuleInvestPeriod;
            return this;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(this.id);
            dest.writeInt(this.type);
            dest.writeInt(this.templeteId);
            dest.writeString(this.packetsCode);
            dest.writeDouble(this.packetsAmount);
            dest.writeInt(this.packetsStatus);
            dest.writeLong(this.packetsUserId);
            dest.writeLong(this.packetsUseTime);
            dest.writeInt(this.packetsUseType);
            dest.writeInt(this.investType);
            dest.writeString(this.financingOrderId);
            dest.writeString(this.investOrderId);
            dest.writeDouble(this.packetsIncome);
            dest.writeLong(this.createTime);
            dest.writeLong(this.createUserId);
            dest.writeLong(this.updateTime);
            dest.writeLong(this.updateUserId);
            dest.writeString(this.description);
            dest.writeByte(this.isDeleted ? (byte) 1 : (byte) 0);
            dest.writeLong(this.dueTime);
            dest.writeInt(this.validDay);
            dest.writeInt(this.useRule);
            dest.writeInt(this.packetsType);
            dest.writeInt(this.useRuleInvestPeriod);
            dest.writeInt(this.subjectType);
        }

        public DatasBean() {
        }

        protected DatasBean(Parcel in) {
            this.id = in.readLong();
            this.type = in.readInt();
            this.templeteId = in.readInt();
            this.packetsCode = in.readString();
            this.packetsAmount = in.readDouble();
            this.packetsStatus = in.readInt();
            this.packetsUserId = in.readLong();
            this.packetsUseTime = in.readLong();
            this.packetsUseType = in.readInt();
            this.investType = in.readInt();
            this.financingOrderId = in.readString();
            this.investOrderId = in.readString();
            this.packetsIncome = in.readDouble();
            this.createTime = in.readLong();
            this.createUserId = in.readLong();
            this.updateTime = in.readLong();
            this.updateUserId = in.readLong();
            this.description = in.readString();
            this.isDeleted = in.readByte() != 0;
            this.dueTime = in.readLong();
            this.validDay = in.readInt();
            this.useRule = in.readInt();
            this.packetsType = in.readInt();
            this.useRuleInvestPeriod = in.readInt();
            this.subjectType = in.readInt();
        }

        public static final Parcelable.Creator<DatasBean> CREATOR = new Parcelable.Creator<DatasBean>() {
            @Override
            public DatasBean createFromParcel(Parcel source) {
                return new DatasBean(source);
            }

            @Override
            public DatasBean[] newArray(int size) {
                return new DatasBean[size];
            }
        };
    }
}
