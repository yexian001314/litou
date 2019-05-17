package com.sleep.uulib.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SleepYM09 on 2017/12/22.
 * <p>
 * blog: www.sleepym09.com
 */

public class SecondHandCarEO implements Parcelable {
    /**
     * 汽车表
     */
    private static final long serialVersionUID = 1L;

    /**
     * 车编号 ----> 贷款编号
     */
    private String carNumber;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 购买时间
     */
    private long buyTime;

    /**
     * 登记城市
     */
    private String regCity;

    /**
     * 购买价格
     */
    private double buyPrice;

    /**
     * 借款金额
     */
    private double totalMoney;

    /**
     * 借款周期
     */
    private int investPeriod;

    /**
     * 创建时间
     */
    private long createTime;

    /**
     * 状态;0-未配单;1-已配单;2-待审核;3-审核成功;4-审核失败;
     */
    private int carStatus;

    /**
     * 是否禁用
     */
    private boolean isDeleted;

    /**
     * 车图片
     */
    private String carImage;

    /**
     * 身份证图片
     */
    private String identityImage;

    /**
     * 证件图片
     */
    private String certificateImage;

    /**
     * 最后更新时间
     */
    private long lastUpdateTime;

    /**
     * 车品牌
     */
    private String carBrand;

    /**
     * 车型号
     */
    private String carModel;

    /**
     * 车颜色
     */
    private String carColor;

    /**
     * 上牌日期
     */
    private long registerTime;
    /**
     * 评估价格
     */
    private double assessmentPrice;
    /**
     * 客服账号
     */
    private String cusServiceAccount;
    /**
     * 客服ID
     */
    private long cusServiceId;
    /**
     * 车牌号
     */
    private String patrolNumber;
    /**
     * 行驶里程
     */
    private String carRoadHaul;
    /**
     * 房屋地址
     */
    private String houseAddress;
    /**
     * 每平方米价格
     */
    private double houseUnitPrice;
    /**
     * 房屋面积
     */
    private double houseArea;
    /**
     * 房屋评估价值
     */
    private double houseAssessmentPrice;
    /**
     * 房屋规划用途
     */
    private String houseUse;
    /**
     * 信用评级
     */
    private String creditRating;
    /**
     * 工作行业
     */
    private String creditIndustry;
    /**
     * 工作城市
     */
    private String creditWorkingCity;
    /**
     * 工作年限
     */
    private String creditWorkingYears;
    /**
     * 年收入
     */
    private double creditAnnualIncome;
    /**
     * 信用卡额度
     */
    private double creditLimit;
    /**
     * 借款用途
     */
    private String loanUse;
    /**
     * 还款来源
     */
    private String repaymentSource;
    /**
     * 风险控制
     */
    private String reskConrol;
    /**
     * 贷款类型
     */
    private String loanType;
    /**
     * 项目说明
     */
    private String projectNote;
    /**
     * 项目名称
     */
    private String subjectName;
    /**
     * 是否平台代收付  0:借款人 1：平台 
     */
    private boolean isReplacePay;
    /**
     * 借款人姓名
     */
    private String realName;
    /**
     * 身份证号码
     */
    private String identityCard;
    /**
     * 借款人性别
     */
    private int sex;

    /**
     *企业类型
     */
    private String companyType;
    /**
     *法人
     */
    private String legalPerson;
    /**
     *企业营业执照号码
     */
    private String businessLicenseNo;
    /**
     * 组织机构代码
     */
    private String organizationCode;
    /**
     *营业期限(年)
     */
    private int businessLimit;
    /**
     *纳税人识别号
     */
    private String ratepayerNo;
    /**
     * 登记单位
     */
    private String registeUnit;
    /**
     * 质押资产评估价
     */
    private double propertyAssess;
    /**
     * 担保公司
     */
    private String companyName;
    /**
     * 注册地址
     */
    private String registeredAddress;

    /**
     * 全国统一信用代码
     */
    private String creditCode;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(long buyTime) {
        this.buyTime = buyTime;
    }

    public String getRegCity() {
        return regCity;
    }

    public void setRegCity(String regCity) {
        this.regCity = regCity;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
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

    public int getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(int carStatus) {
        this.carStatus = carStatus;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public String getIdentityImage() {
        return identityImage;
    }

    public void setIdentityImage(String identityImage) {
        this.identityImage = identityImage;
    }

    public String getCertificateImage() {
        return certificateImage;
    }

    public void setCertificateImage(String certificateImage) {
        this.certificateImage = certificateImage;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
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

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }

    public double getAssessmentPrice() {
        return assessmentPrice;
    }

    public void setAssessmentPrice(double assessmentPrice) {
        this.assessmentPrice = assessmentPrice;
    }

    public String getCusServiceAccount() {
        return cusServiceAccount;
    }

    public void setCusServiceAccount(String cusServiceAccount) {
        this.cusServiceAccount = cusServiceAccount;
    }

    public long getCusServiceId() {
        return cusServiceId;
    }

    public void setCusServiceId(long cusServiceId) {
        this.cusServiceId = cusServiceId;
    }

    public String getPatrolNumber() {
        return patrolNumber;
    }

    public void setPatrolNumber(String patrolNumber) {
        this.patrolNumber = patrolNumber;
    }

    public String getCarRoadHaul() {
        return carRoadHaul;
    }

    public void setCarRoadHaul(String carRoadHaul) {
        this.carRoadHaul = carRoadHaul;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public double getHouseUnitPrice() {
        return houseUnitPrice;
    }

    public void setHouseUnitPrice(double houseUnitPrice) {
        this.houseUnitPrice = houseUnitPrice;
    }

    public double getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(double houseArea) {
        this.houseArea = houseArea;
    }

    public double getHouseAssessmentPrice() {
        return houseAssessmentPrice;
    }

    public void setHouseAssessmentPrice(double houseAssessmentPrice) {
        this.houseAssessmentPrice = houseAssessmentPrice;
    }

    public String getHouseUse() {
        return houseUse;
    }

    public void setHouseUse(String houseUse) {
        this.houseUse = houseUse;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    public String getCreditIndustry() {
        return creditIndustry;
    }

    public void setCreditIndustry(String creditIndustry) {
        this.creditIndustry = creditIndustry;
    }

    public String getCreditWorkingCity() {
        return creditWorkingCity;
    }

    public void setCreditWorkingCity(String creditWorkingCity) {
        this.creditWorkingCity = creditWorkingCity;
    }

    public String getCreditWorkingYears() {
        return creditWorkingYears;
    }

    public void setCreditWorkingYears(String creditWorkingYears) {
        this.creditWorkingYears = creditWorkingYears;
    }

    public double getCreditAnnualIncome() {
        return creditAnnualIncome;
    }

    public void setCreditAnnualIncome(double creditAnnualIncome) {
        this.creditAnnualIncome = creditAnnualIncome;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getLoanUse() {
        return loanUse;
    }

    public void setLoanUse(String loanUse) {
        this.loanUse = loanUse;
    }

    public String getRepaymentSource() {
        return repaymentSource;
    }

    public void setRepaymentSource(String repaymentSource) {
        this.repaymentSource = repaymentSource;
    }

    public String getReskConrol() {
        return reskConrol;
    }

    public void setReskConrol(String reskConrol) {
        this.reskConrol = reskConrol;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getProjectNote() {
        return projectNote;
    }

    public void setProjectNote(String projectNote) {
        this.projectNote = projectNote;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public boolean isReplacePay() {
        return isReplacePay;
    }

    public void setReplacePay(boolean replacePay) {
        isReplacePay = replacePay;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getBusinessLicenseNo() {
        return businessLicenseNo;
    }

    public void setBusinessLicenseNo(String businessLicenseNo) {
        this.businessLicenseNo = businessLicenseNo;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public int getBusinessLimit() {
        return businessLimit;
    }

    public void setBusinessLimit(int businessLimit) {
        this.businessLimit = businessLimit;
    }

    public String getRatepayerNo() {
        return ratepayerNo;
    }

    public void setRatepayerNo(String ratepayerNo) {
        this.ratepayerNo = ratepayerNo;
    }

    public String getRegisteUnit() {
        return registeUnit;
    }

    public void setRegisteUnit(String registeUnit) {
        this.registeUnit = registeUnit;
    }

    public double getPropertyAssess() {
        return propertyAssess;
    }

    public void setPropertyAssess(double propertyAssess) {
        this.propertyAssess = propertyAssess;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.carNumber);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.city);
        dest.writeLong(this.buyTime);
        dest.writeString(this.regCity);
        dest.writeDouble(this.buyPrice);
        dest.writeDouble(this.totalMoney);
        dest.writeInt(this.investPeriod);
        dest.writeLong(this.createTime);
        dest.writeInt(this.carStatus);
        dest.writeByte(this.isDeleted ? (byte) 1 : (byte) 0);
        dest.writeString(this.carImage);
        dest.writeString(this.identityImage);
        dest.writeString(this.certificateImage);
        dest.writeLong(this.lastUpdateTime);
        dest.writeString(this.carBrand);
        dest.writeString(this.carModel);
        dest.writeString(this.carColor);
        dest.writeLong(this.registerTime);
        dest.writeDouble(this.assessmentPrice);
        dest.writeString(this.cusServiceAccount);
        dest.writeLong(this.cusServiceId);
        dest.writeString(this.patrolNumber);
        dest.writeString(this.carRoadHaul);
        dest.writeString(this.houseAddress);
        dest.writeDouble(this.houseUnitPrice);
        dest.writeDouble(this.houseArea);
        dest.writeDouble(this.houseAssessmentPrice);
        dest.writeString(this.houseUse);
        dest.writeString(this.creditRating);
        dest.writeString(this.creditIndustry);
        dest.writeString(this.creditWorkingCity);
        dest.writeString(this.creditWorkingYears);
        dest.writeDouble(this.creditAnnualIncome);
        dest.writeDouble(this.creditLimit);
        dest.writeString(this.loanUse);
        dest.writeString(this.repaymentSource);
        dest.writeString(this.reskConrol);
        dest.writeString(this.loanType);
        dest.writeString(this.projectNote);
        dest.writeString(this.subjectName);
        dest.writeByte(this.isReplacePay ? (byte) 1 : (byte) 0);
        dest.writeString(this.realName);
        dest.writeString(this.identityCard);
        dest.writeInt(this.sex);
        dest.writeString(this.companyType);
        dest.writeString(this.legalPerson);
        dest.writeString(this.businessLicenseNo);
        dest.writeString(this.organizationCode);
        dest.writeInt(this.businessLimit);
        dest.writeString(this.ratepayerNo);
        dest.writeString(this.registeUnit);
        dest.writeDouble(this.propertyAssess);
        dest.writeString(this.companyName);
        dest.writeString(this.registeredAddress);
        dest.writeString(this.creditCode);
    }

    public SecondHandCarEO() {
    }

    protected SecondHandCarEO(Parcel in) {
        this.carNumber = in.readString();
        this.phoneNumber = in.readString();
        this.city = in.readString();
        this.buyTime = in.readLong();
        this.regCity = in.readString();
        this.buyPrice = in.readDouble();
        this.totalMoney = in.readDouble();
        this.investPeriod = in.readInt();
        this.createTime = in.readLong();
        this.carStatus = in.readInt();
        this.isDeleted = in.readByte() != 0;
        this.carImage = in.readString();
        this.identityImage = in.readString();
        this.certificateImage = in.readString();
        this.lastUpdateTime = in.readLong();
        this.carBrand = in.readString();
        this.carModel = in.readString();
        this.carColor = in.readString();
        this.registerTime = in.readLong();
        this.assessmentPrice = in.readDouble();
        this.cusServiceAccount = in.readString();
        this.cusServiceId = in.readLong();
        this.patrolNumber = in.readString();
        this.carRoadHaul = in.readString();
        this.houseAddress = in.readString();
        this.houseUnitPrice = in.readDouble();
        this.houseArea = in.readDouble();
        this.houseAssessmentPrice = in.readDouble();
        this.houseUse = in.readString();
        this.creditRating = in.readString();
        this.creditIndustry = in.readString();
        this.creditWorkingCity = in.readString();
        this.creditWorkingYears = in.readString();
        this.creditAnnualIncome = in.readDouble();
        this.creditLimit = in.readDouble();
        this.loanUse = in.readString();
        this.repaymentSource = in.readString();
        this.reskConrol = in.readString();
        this.loanType = in.readString();
        this.projectNote = in.readString();
        this.subjectName = in.readString();
        this.isReplacePay = in.readByte() != 0;
        this.realName = in.readString();
        this.identityCard = in.readString();
        this.sex = in.readInt();
        this.companyType = in.readString();
        this.legalPerson = in.readString();
        this.businessLicenseNo = in.readString();
        this.organizationCode = in.readString();
        this.businessLimit = in.readInt();
        this.ratepayerNo = in.readString();
        this.registeUnit = in.readString();
        this.propertyAssess = in.readDouble();
        this.companyName = in.readString();
        this.registeredAddress = in.readString();
        this.creditCode = in.readString();
    }

    public static final Parcelable.Creator<SecondHandCarEO> CREATOR = new Parcelable.Creator<SecondHandCarEO>() {
        @Override
        public SecondHandCarEO createFromParcel(Parcel source) {
            return new SecondHandCarEO(source);
        }

        @Override
        public SecondHandCarEO[] newArray(int size) {
            return new SecondHandCarEO[size];
        }
    };
}
