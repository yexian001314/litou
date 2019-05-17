package com.sleep.uulib.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.sleep.uulib.constant.Constant;

import java.util.List;

/**
 * Created by SleepYM09 on 2017/12/11.
 * <p>
 * blog: www.sleepym09.com
 */

public class InvestRecordBean {
    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * page : {"pageSize":10,"start":0,"data":[{"id":1000800,"orderId":"FD1801150006002","investOrderId":"I1801170006039","carBrand":"大众","carModel":"","investMoney":100,"lendRate":0.1,"createTime":1516158973000,"endTime":null,"investPeriod":28,"everydayIncome":0.77,"orderStatus":2,"subjectName":"修改借款单测试0115A","loanType":"1","expiredTime":1518500027000},{"id":1000800,"orderId":"FD1801150006002","investOrderId":"I1801170006038","carBrand":"大众","carModel":"","investMoney":100,"lendRate":0.1,"createTime":1516158810000,"endTime":null,"investPeriod":28,"everydayIncome":0.77,"orderStatus":2,"subjectName":"修改借款单测试0115A","loanType":"1","expiredTime":1518500027000},{"id":1000800,"orderId":"FD1801150006002","investOrderId":"I1801170006037","carBrand":"大众","carModel":"","investMoney":100,"lendRate":0.1,"createTime":1516158631000,"endTime":null,"investPeriod":28,"everydayIncome":0.77,"orderStatus":2,"subjectName":"修改借款单测试0115A","loanType":"1","expiredTime":1518500027000},{"id":1000800,"orderId":"FD1801150006002","investOrderId":"I1801170006036","carBrand":"大众","carModel":"","investMoney":100,"lendRate":0.1,"createTime":1516158279000,"endTime":null,"investPeriod":28,"everydayIncome":0.77,"orderStatus":2,"subjectName":"修改借款单测试0115A","loanType":"1","expiredTime":1518500027000},{"id":1000776,"orderId":"FD1801100000013","investOrderId":"I1801120006001","carBrand":"大众","carModel":"","investMoney":100,"lendRate":0.1,"createTime":1515734305000,"endTime":null,"investPeriod":28,"everydayIncome":0.77,"orderStatus":2,"subjectName":"3修改后测试","loanType":"1","expiredTime":1518081199000},{"id":1000792,"orderId":"FD1801110000014","investOrderId":"I1801110000029","carBrand":"秦岭","carModel":null,"investMoney":100,"lendRate":0.11,"createTime":1515642836000,"endTime":null,"investPeriod":30,"everydayIncome":0.91,"orderStatus":2,"subjectName":"金银花","loanType":"1","expiredTime":1518144170000},{"id":1000792,"orderId":"FD1801110000014","investOrderId":"I1801110000028","carBrand":"秦岭","carModel":null,"investMoney":100,"lendRate":0.11,"createTime":1515642793000,"endTime":null,"investPeriod":30,"everydayIncome":0.91,"orderStatus":2,"subjectName":"金银花","loanType":"1","expiredTime":1518144170000},{"id":1000792,"orderId":"FD1801110000014","investOrderId":"I1801110000026","carBrand":"秦岭","carModel":null,"investMoney":100,"lendRate":0.11,"createTime":1515642719000,"endTime":null,"investPeriod":30,"everydayIncome":0.91,"orderStatus":2,"subjectName":"金银花","loanType":"1","expiredTime":1518144170000},{"id":1000792,"orderId":"FD1801110000014","investOrderId":"I1801110000025","carBrand":"秦岭","carModel":null,"investMoney":100,"lendRate":0.11,"createTime":1515642634000,"endTime":null,"investPeriod":30,"everydayIncome":0.91,"orderStatus":2,"subjectName":"金银花","loanType":"1","expiredTime":1518144170000},{"id":1000792,"orderId":"FD1801110000014","investOrderId":"I1801110000024","carBrand":"秦岭","carModel":null,"investMoney":100,"lendRate":0.11,"createTime":1515642605000,"endTime":null,"investPeriod":30,"everydayIncome":0.91,"orderStatus":2,"subjectName":"金银花","loanType":"1","expiredTime":1518144170000}],"totalCount":14,"totalPageCount":2,"hasNextPage":true,"hasPreviousPage":false,"dataSizeOfCurrentPage":10,"currentPageNo":1}
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private PageBean page;

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
         * pageSize : 10
         * start : 0
         * data : [{"id":1000800,"orderId":"FD1801150006002","investOrderId":"I1801170006039","carBrand":"大众","carModel":"","investMoney":100,"lendRate":0.1,"createTime":1516158973000,"endTime":null,"investPeriod":28,"everydayIncome":0.77,"orderStatus":2,"subjectName":"修改借款单测试0115A","loanType":"1","expiredTime":1518500027000},{"id":1000800,"orderId":"FD1801150006002","investOrderId":"I1801170006038","carBrand":"大众","carModel":"","investMoney":100,"lendRate":0.1,"createTime":1516158810000,"endTime":null,"investPeriod":28,"everydayIncome":0.77,"orderStatus":2,"subjectName":"修改借款单测试0115A","loanType":"1","expiredTime":1518500027000},{"id":1000800,"orderId":"FD1801150006002","investOrderId":"I1801170006037","carBrand":"大众","carModel":"","investMoney":100,"lendRate":0.1,"createTime":1516158631000,"endTime":null,"investPeriod":28,"everydayIncome":0.77,"orderStatus":2,"subjectName":"修改借款单测试0115A","loanType":"1","expiredTime":1518500027000},{"id":1000800,"orderId":"FD1801150006002","investOrderId":"I1801170006036","carBrand":"大众","carModel":"","investMoney":100,"lendRate":0.1,"createTime":1516158279000,"endTime":null,"investPeriod":28,"everydayIncome":0.77,"orderStatus":2,"subjectName":"修改借款单测试0115A","loanType":"1","expiredTime":1518500027000},{"id":1000776,"orderId":"FD1801100000013","investOrderId":"I1801120006001","carBrand":"大众","carModel":"","investMoney":100,"lendRate":0.1,"createTime":1515734305000,"endTime":null,"investPeriod":28,"everydayIncome":0.77,"orderStatus":2,"subjectName":"3修改后测试","loanType":"1","expiredTime":1518081199000},{"id":1000792,"orderId":"FD1801110000014","investOrderId":"I1801110000029","carBrand":"秦岭","carModel":null,"investMoney":100,"lendRate":0.11,"createTime":1515642836000,"endTime":null,"investPeriod":30,"everydayIncome":0.91,"orderStatus":2,"subjectName":"金银花","loanType":"1","expiredTime":1518144170000},{"id":1000792,"orderId":"FD1801110000014","investOrderId":"I1801110000028","carBrand":"秦岭","carModel":null,"investMoney":100,"lendRate":0.11,"createTime":1515642793000,"endTime":null,"investPeriod":30,"everydayIncome":0.91,"orderStatus":2,"subjectName":"金银花","loanType":"1","expiredTime":1518144170000},{"id":1000792,"orderId":"FD1801110000014","investOrderId":"I1801110000026","carBrand":"秦岭","carModel":null,"investMoney":100,"lendRate":0.11,"createTime":1515642719000,"endTime":null,"investPeriod":30,"everydayIncome":0.91,"orderStatus":2,"subjectName":"金银花","loanType":"1","expiredTime":1518144170000},{"id":1000792,"orderId":"FD1801110000014","investOrderId":"I1801110000025","carBrand":"秦岭","carModel":null,"investMoney":100,"lendRate":0.11,"createTime":1515642634000,"endTime":null,"investPeriod":30,"everydayIncome":0.91,"orderStatus":2,"subjectName":"金银花","loanType":"1","expiredTime":1518144170000},{"id":1000792,"orderId":"FD1801110000014","investOrderId":"I1801110000024","carBrand":"秦岭","carModel":null,"investMoney":100,"lendRate":0.11,"createTime":1515642605000,"endTime":null,"investPeriod":30,"everydayIncome":0.91,"orderStatus":2,"subjectName":"金银花","loanType":"1","expiredTime":1518144170000}]
         * totalCount : 14
         * totalPageCount : 2
         * hasNextPage : true
         * hasPreviousPage : false
         * dataSizeOfCurrentPage : 10
         * currentPageNo : 1
         */

        private int pageSize;
        private int start;
        private int totalCount;
        private int totalPageCount;
        private boolean hasNextPage;
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

        public int getTotalPageCount() {
            return totalPageCount;
        }

        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
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

        public static class DataBean extends AbstractExpandableItem<QueryInvestByIdBean> implements MultiItemEntity, Parcelable {
            /**
             * id : 1000800
             * orderId : FD1801150006002
             * investOrderId : I1801170006039
             * carBrand : 大众
             * carModel :
             * investMoney : 100.0
             * lendRate : 0.1
             * createTime : 1516158973000
             * endTime : null
             * investPeriod : 28
             * everydayIncome : 0.77
             * orderStatus : 2
             * subjectName : 修改借款单测试0115A
             * loanType : 1
             * expiredTime : 1518500027000
             */

            private int id;
            private String orderId;
            private String investOrderId;
            private String carBrand;
            private String carModel;
            private double investMoney;
            private double lendRate;
            private long createTime;
            private long endTime;
            private int investPeriod;
            private double everydayIncome;
            /**
             * //状态 1待支付、2已支付、3还款中、5已完成
             */
            private int orderStatus;
            private String subjectName;
            private String loanType;
            private long expiredTime;
            private double interestRate;
            private double packetsAmount;
            /**
             * 1 -> 一次性还本付息
             * 2 -> 分期还款
             */
            private int payType;
            private int period;
            private int totalPeriod;
            private long payTime;

            public long getPayTime() {
                return payTime;
            }

            public void setPayTime(long payTime) {
                this.payTime = payTime;
            }

            public int getPeriod() {
                return period;
            }

            public void setPeriod(int period) {
                this.period = period;
            }

            public int getTotalPeriod() {
                return totalPeriod;
            }

            public void setTotalPeriod(int totalPeriod) {
                this.totalPeriod = totalPeriod;
            }

            public int getPayType() {
                return payType;
            }

            public void setPayType(int payType) {
                this.payType = payType;
            }

            public double getInterestRate() {
                return interestRate;
            }

            public void setInterestRate(double interestRate) {
                this.interestRate = interestRate;
            }

            public double getPacketsAmount() {
                return packetsAmount;
            }

            public void setPacketsAmount(double packetsAmount) {
                this.packetsAmount = packetsAmount;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getInvestOrderId() {
                return investOrderId;
            }

            public void setInvestOrderId(String investOrderId) {
                this.investOrderId = investOrderId;
            }

            public String getCarBrand() {
                return carBrand;
            }

            public void setCarBrand(String carBrand) {
                this.carBrand = carBrand;
            }

            public String getCarModel() {
                return carModel;
            }

            public void setCarModel(String carModel) {
                this.carModel = carModel;
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

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public long getEndTime() {
                return endTime;
            }

            public void setEndTime(long endTime) {
                this.endTime = endTime;
            }

            public int getInvestPeriod() {
                return investPeriod;
            }

            public void setInvestPeriod(int investPeriod) {
                this.investPeriod = investPeriod;
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

            public String getSubjectName() {
                return subjectName;
            }

            public void setSubjectName(String subjectName) {
                this.subjectName = subjectName;
            }

            public String getLoanType() {
                return loanType;
            }

            public void setLoanType(String loanType) {
                this.loanType = loanType;
            }

            public long getExpiredTime() {
                return expiredTime;
            }

            public void setExpiredTime(long expiredTime) {
                this.expiredTime = expiredTime;
            }


            @Override
            public int getLevel() {
                return 0;
            }

            @Override
            public int getItemType() {
                return Constant.EXPEND_LEVEL_0;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.orderId);
                dest.writeString(this.investOrderId);
                dest.writeString(this.carBrand);
                dest.writeString(this.carModel);
                dest.writeDouble(this.investMoney);
                dest.writeDouble(this.lendRate);
                dest.writeLong(this.createTime);
                dest.writeLong(this.endTime);
                dest.writeInt(this.investPeriod);
                dest.writeDouble(this.everydayIncome);
                dest.writeInt(this.orderStatus);
                dest.writeString(this.subjectName);
                dest.writeString(this.loanType);
                dest.writeLong(this.expiredTime);
                dest.writeDouble(this.interestRate);
                dest.writeDouble(this.packetsAmount);
                dest.writeInt(this.payType);
                dest.writeInt(this.period);
                dest.writeInt(this.totalPeriod);
                dest.writeLong(this.payTime);
            }

            public DataBean() {
            }

            protected DataBean(Parcel in) {
                this.id = in.readInt();
                this.orderId = in.readString();
                this.investOrderId = in.readString();
                this.carBrand = in.readString();
                this.carModel = in.readString();
                this.investMoney = in.readDouble();
                this.lendRate = in.readDouble();
                this.createTime = in.readLong();
                this.endTime = in.readLong();
                this.investPeriod = in.readInt();
                this.everydayIncome = in.readDouble();
                this.orderStatus = in.readInt();
                this.subjectName = in.readString();
                this.loanType = in.readString();
                this.expiredTime = in.readLong();
                this.interestRate = in.readDouble();
                this.packetsAmount = in.readDouble();
                this.payType = in.readInt();
                this.period = in.readInt();
                this.totalPeriod = in.readInt();
                this.payTime = in.readLong();
            }

            public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
                @Override
                public DataBean createFromParcel(Parcel source) {
                    return new DataBean(source);
                }

                @Override
                public DataBean[] newArray(int size) {
                    return new DataBean[size];
                }
            };
        }
    }
}
