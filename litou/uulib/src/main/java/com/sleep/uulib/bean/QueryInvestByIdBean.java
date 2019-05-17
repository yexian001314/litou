package com.sleep.uulib.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.sleep.uulib.constant.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SleepYM09 on 2018/2/28.
 * <p>
 * blog: www.sleepym09.com
 */

public class QueryInvestByIdBean implements MultiItemEntity, Parcelable {
    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * interestVOS : [{"id":1,"itemId":1,"interest":16.87,"principal":null,"extraInterest":null,"payTime":1522208454000,"state":0,"totalPeriod":2},{"id":2,"itemId":2,"interest":18.68,"principal":null,"extraInterest":null,"payTime":1524886854000,"state":0,"totalPeriod":2},{"id":3,"itemId":3,"interest":18.71,"principal":2000,"extraInterest":null,"payTime":1527478794000,"state":0,"totalPeriod":2}]
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private List<InterestVOSBean> interestVOS;

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

    public List<InterestVOSBean> getInterestVOS() {
        return interestVOS;
    }

    public void setInterestVOS(List<InterestVOSBean> interestVOS) {
        this.interestVOS = interestVOS;
    }

    @Override
    public int getItemType() {
        return Constant.EXPEND_LEVEL_1;
    }

    public static class ResponseStatusBean implements Parcelable {
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.code);
            dest.writeString(this.message);
        }

        public ResponseStatusBean() {
        }

        protected ResponseStatusBean(Parcel in) {
            this.code = in.readString();
            this.message = in.readString();
        }

        public static final Creator<ResponseStatusBean> CREATOR = new Creator<ResponseStatusBean>() {
            @Override
            public ResponseStatusBean createFromParcel(Parcel source) {
                return new ResponseStatusBean(source);
            }

            @Override
            public ResponseStatusBean[] newArray(int size) {
                return new ResponseStatusBean[size];
            }
        };
    }

    public static class InterestVOSBean implements Parcelable {
        /**
         * id : 1
         * itemId : 1
         * interest : 16.87
         * principal : null
         * extraInterest : null
         * payTime : 1522208454000
         * state : 0
         * totalPeriod : 2
         */

        private int id;
        private int itemId;
        private double interest;
        private double principal;
        private double extraInterest;
        private long payTime;
        private int state;
        private int totalPeriod;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public double getInterest() {
            return interest;
        }

        public void setInterest(double interest) {
            this.interest = interest;
        }

        public double getPrincipal() {
            return principal;
        }

        public void setPrincipal(double principal) {
            this.principal = principal;
        }

        public double getExtraInterest() {
            return extraInterest;
        }

        public void setExtraInterest(double extraInterest) {
            this.extraInterest = extraInterest;
        }

        public long getPayTime() {
            return payTime;
        }

        public void setPayTime(long payTime) {
            this.payTime = payTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getTotalPeriod() {
            return totalPeriod;
        }

        public void setTotalPeriod(int totalPeriod) {
            this.totalPeriod = totalPeriod;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.itemId);
            dest.writeDouble(this.interest);
            dest.writeDouble(this.principal);
            dest.writeDouble(this.extraInterest);
            dest.writeLong(this.payTime);
            dest.writeInt(this.state);
            dest.writeInt(this.totalPeriod);
        }

        public InterestVOSBean() {
        }

        protected InterestVOSBean(Parcel in) {
            this.id = in.readInt();
            this.itemId = in.readInt();
            this.interest = in.readDouble();
            this.principal = in.readDouble();
            this.extraInterest = in.readDouble();
            this.payTime = in.readLong();
            this.state = in.readInt();
            this.totalPeriod = in.readInt();
        }

        public static final Creator<InterestVOSBean> CREATOR = new Creator<InterestVOSBean>() {
            @Override
            public InterestVOSBean createFromParcel(Parcel source) {
                return new InterestVOSBean(source);
            }

            @Override
            public InterestVOSBean[] newArray(int size) {
                return new InterestVOSBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.responseStatus, flags);
        dest.writeByte(this.success ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isException ? (byte) 1 : (byte) 0);
        dest.writeList(this.interestVOS);
    }

    public QueryInvestByIdBean() {
    }

    protected QueryInvestByIdBean(Parcel in) {
        this.responseStatus = in.readParcelable(ResponseStatusBean.class.getClassLoader());
        this.success = in.readByte() != 0;
        this.isException = in.readByte() != 0;
        this.interestVOS = new ArrayList<InterestVOSBean>();
        in.readList(this.interestVOS, InterestVOSBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<QueryInvestByIdBean> CREATOR = new Parcelable.Creator<QueryInvestByIdBean>() {
        @Override
        public QueryInvestByIdBean createFromParcel(Parcel source) {
            return new QueryInvestByIdBean(source);
        }

        @Override
        public QueryInvestByIdBean[] newArray(int size) {
            return new QueryInvestByIdBean[size];
        }
    };
}
