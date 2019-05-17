package com.sleep.uulib.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SleepYM09 on 2017/12/1.
 * <p>
 * blog: www.sleepym09.com
 */

public class FinancingOrderBean implements Parcelable {
    /**
     * secondHandCarEO : null
     * id : 1000715
     * uid : 1001285
     * financerPhone : null
     * realName : 孙**
     * nickName : null
     * sex : 0
     * totalMoney : 120000.0
     * currentMoney : null
     * toAccountMoney : null
     * lendRate : 0.1
     * loanedTime : null
     * investPeriod : 20
     * hasBorrowDays : null
     * guaranteeMoney : null
     * interestMoney : null
     * createTime : 1513913667000
     * startTime : 1513913958000
     * endTime : null
     * successTime : null
     * projectStatus : null
     * totalInvester : 0
     * orderId : FD1712220000007
     * orderStatus : 5
     * expiredTime : null
     * identifyCertificate : null
     * cusServiceId : 900000116
     * phoneNumber : 13633025660
     * assessmentPrice : 120000.0
     * carNumber : AP1712220000007
     * baseRate : 0.1
     * baseRateToMoney : 657.54
     * activityRate : 0.0
     * activityRateToMoney : 0.0
     * activityDesc :
     * noviceRate : 0.0
     * noviceRateToMoney : 0.0
     * noviceDesc :
     * subjectType : 1
     * subjectName : 车款
     * totalRateMoney : 657.54
     */

    private SecondHandCarEO secondHandCarEO;
    private int id;
    private int uid;
    private String financerPhone;
    private String realName;
    private String nickName;
    private int sex;
    private double totalMoney;
    private double currentMoney;
    private double toAccountMoney;
    private double lendRate;
    private long loanedTime;
    private int investPeriod;
    private int hasBorrowDays;
    private double guaranteeMoney;
    private double interestMoney;
    private long createTime;
    private long startTime;
    private long endTime;
    private long successTime;
    private int projectStatus;
    private int totalInvester;
    private String orderId;
    private int orderStatus;
    private long expiredTime;
    private boolean identifyCertificate;
    private int cusServiceId;
    private String phoneNumber;
    private double assessmentPrice;
    private String carNumber;
    private double baseRate;
    private double baseRateToMoney;
    private double activityRate;
    private double activityRateToMoney;
    private String activityDesc;
    private double noviceRate;
    private double noviceRateToMoney;
    private String noviceDesc;
    private int subjectType;
    private String subjectName;
    private double totalRateMoney;
    /**
     * 标的计算利息周期
     */
    private int actPeriod;

    private int payType;

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getActPeriod() {
        return actPeriod;
    }

    public void setActPeriod(int actPeriod) {
        this.actPeriod = actPeriod;
    }

    public SecondHandCarEO getSecondHandCarEO() {
        return secondHandCarEO;
    }

    public void setSecondHandCarEO(SecondHandCarEO secondHandCarEO) {
        this.secondHandCarEO = secondHandCarEO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFinancerPhone() {
        return financerPhone;
    }

    public void setFinancerPhone(String financerPhone) {
        this.financerPhone = financerPhone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(double currentMoney) {
        this.currentMoney = currentMoney;
    }

    public Object getToAccountMoney() {
        return toAccountMoney;
    }

    public void setToAccountMoney(double toAccountMoney) {
        this.toAccountMoney = toAccountMoney;
    }

    public double getLendRate() {
        return lendRate;
    }

    public void setLendRate(double lendRate) {
        this.lendRate = lendRate;
    }

    public long getLoanedTime() {
        return loanedTime;
    }

    public void setLoanedTime(long loanedTime) {
        this.loanedTime = loanedTime;
    }

    public int getInvestPeriod() {
        return investPeriod;
    }

    public void setInvestPeriod(int investPeriod) {
        this.investPeriod = investPeriod;
    }

    public int getHasBorrowDays() {
        return hasBorrowDays;
    }

    public void setHasBorrowDays(int hasBorrowDays) {
        this.hasBorrowDays = hasBorrowDays;
    }

    public double getGuaranteeMoney() {
        return guaranteeMoney;
    }

    public void setGuaranteeMoney(double guaranteeMoney) {
        this.guaranteeMoney = guaranteeMoney;
    }

    public double getInterestMoney() {
        return interestMoney;
    }

    public void setInterestMoney(double interestMoney) {
        this.interestMoney = interestMoney;
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

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(long successTime) {
        this.successTime = successTime;
    }

    public int getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(int projectStatus) {
        this.projectStatus = projectStatus;
    }

    public int getTotalInvester() {
        return totalInvester;
    }

    public void setTotalInvester(int totalInvester) {
        this.totalInvester = totalInvester;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public boolean getIdentifyCertificate() {
        return identifyCertificate;
    }

    public void setIdentifyCertificate(boolean identifyCertificate) {
        this.identifyCertificate = identifyCertificate;
    }

    public int getCusServiceId() {
        return cusServiceId;
    }

    public void setCusServiceId(int cusServiceId) {
        this.cusServiceId = cusServiceId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getAssessmentPrice() {
        return assessmentPrice;
    }

    public void setAssessmentPrice(double assessmentPrice) {
        this.assessmentPrice = assessmentPrice;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public double getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(double baseRate) {
        this.baseRate = baseRate;
    }

    public double getBaseRateToMoney() {
        return baseRateToMoney;
    }

    public void setBaseRateToMoney(double baseRateToMoney) {
        this.baseRateToMoney = baseRateToMoney;
    }

    public double getActivityRate() {
        return activityRate;
    }

    public void setActivityRate(double activityRate) {
        this.activityRate = activityRate;
    }

    public double getActivityRateToMoney() {
        return activityRateToMoney;
    }

    public void setActivityRateToMoney(double activityRateToMoney) {
        this.activityRateToMoney = activityRateToMoney;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public double getNoviceRate() {
        return noviceRate;
    }

    public void setNoviceRate(double noviceRate) {
        this.noviceRate = noviceRate;
    }

    public double getNoviceRateToMoney() {
        return noviceRateToMoney;
    }

    public void setNoviceRateToMoney(double noviceRateToMoney) {
        this.noviceRateToMoney = noviceRateToMoney;
    }

    public String getNoviceDesc() {
        return noviceDesc;
    }

    public void setNoviceDesc(String noviceDesc) {
        this.noviceDesc = noviceDesc;
    }

    public int getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(int subjectType) {
        this.subjectType = subjectType;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public double getTotalRateMoney() {
        return totalRateMoney;
    }

    public void setTotalRateMoney(double totalRateMoney) {
        this.totalRateMoney = totalRateMoney;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.secondHandCarEO, flags);
        dest.writeInt(this.id);
        dest.writeInt(this.uid);
        dest.writeString(this.financerPhone);
        dest.writeString(this.realName);
        dest.writeString(this.nickName);
        dest.writeInt(this.sex);
        dest.writeDouble(this.totalMoney);
        dest.writeDouble(this.currentMoney);
        dest.writeDouble(this.toAccountMoney);
        dest.writeDouble(this.lendRate);
        dest.writeLong(this.loanedTime);
        dest.writeInt(this.investPeriod);
        dest.writeInt(this.hasBorrowDays);
        dest.writeDouble(this.guaranteeMoney);
        dest.writeDouble(this.interestMoney);
        dest.writeLong(this.createTime);
        dest.writeLong(this.startTime);
        dest.writeLong(this.endTime);
        dest.writeLong(this.successTime);
        dest.writeInt(this.projectStatus);
        dest.writeInt(this.totalInvester);
        dest.writeString(this.orderId);
        dest.writeInt(this.orderStatus);
        dest.writeLong(this.expiredTime);
        dest.writeByte(this.identifyCertificate ? (byte) 1 : (byte) 0);
        dest.writeInt(this.cusServiceId);
        dest.writeString(this.phoneNumber);
        dest.writeDouble(this.assessmentPrice);
        dest.writeString(this.carNumber);
        dest.writeDouble(this.baseRate);
        dest.writeDouble(this.baseRateToMoney);
        dest.writeDouble(this.activityRate);
        dest.writeDouble(this.activityRateToMoney);
        dest.writeString(this.activityDesc);
        dest.writeDouble(this.noviceRate);
        dest.writeDouble(this.noviceRateToMoney);
        dest.writeString(this.noviceDesc);
        dest.writeInt(this.subjectType);
        dest.writeString(this.subjectName);
        dest.writeDouble(this.totalRateMoney);
        dest.writeInt(this.actPeriod);
        dest.writeInt(this.payType);
    }

    public FinancingOrderBean() {
    }

    protected FinancingOrderBean(Parcel in) {
        this.secondHandCarEO = in.readParcelable(SecondHandCarEO.class.getClassLoader());
        this.id = in.readInt();
        this.uid = in.readInt();
        this.financerPhone = in.readString();
        this.realName = in.readString();
        this.nickName = in.readString();
        this.sex = in.readInt();
        this.totalMoney = in.readDouble();
        this.currentMoney = in.readDouble();
        this.toAccountMoney = in.readDouble();
        this.lendRate = in.readDouble();
        this.loanedTime = in.readLong();
        this.investPeriod = in.readInt();
        this.hasBorrowDays = in.readInt();
        this.guaranteeMoney = in.readDouble();
        this.interestMoney = in.readDouble();
        this.createTime = in.readLong();
        this.startTime = in.readLong();
        this.endTime = in.readLong();
        this.successTime = in.readLong();
        this.projectStatus = in.readInt();
        this.totalInvester = in.readInt();
        this.orderId = in.readString();
        this.orderStatus = in.readInt();
        this.expiredTime = in.readLong();
        this.identifyCertificate = in.readByte() != 0;
        this.cusServiceId = in.readInt();
        this.phoneNumber = in.readString();
        this.assessmentPrice = in.readDouble();
        this.carNumber = in.readString();
        this.baseRate = in.readDouble();
        this.baseRateToMoney = in.readDouble();
        this.activityRate = in.readDouble();
        this.activityRateToMoney = in.readDouble();
        this.activityDesc = in.readString();
        this.noviceRate = in.readDouble();
        this.noviceRateToMoney = in.readDouble();
        this.noviceDesc = in.readString();
        this.subjectType = in.readInt();
        this.subjectName = in.readString();
        this.totalRateMoney = in.readDouble();
        this.actPeriod = in.readInt();
        this.payType = in.readInt();
    }

    public static final Parcelable.Creator<FinancingOrderBean> CREATOR = new Parcelable.Creator<FinancingOrderBean>() {
        @Override
        public FinancingOrderBean createFromParcel(Parcel source) {
            return new FinancingOrderBean(source);
        }

        @Override
        public FinancingOrderBean[] newArray(int size) {
            return new FinancingOrderBean[size];
        }
    };
}
