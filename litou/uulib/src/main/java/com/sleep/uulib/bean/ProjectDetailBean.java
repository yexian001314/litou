package com.sleep.uulib.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sleep.commonlib.util.StringUtil;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by SleepYM09 on 2017/12/1.
 * <p>
 * blog: www.sleepym09.com
 */

public class ProjectDetailBean {

    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * financingOrder : {"secondHandCarEO":null,"id":1000715,"uid":1001285,"financerPhone":null,"realName":"孙**","nickName":null,"sex":0,"totalMoney":120000,"currentMoney":null,"toAccountMoney":null,"lendRate":0.1,"loanedTime":null,"investPeriod":20,"hasBorrowDays":null,"guaranteeMoney":null,"interestMoney":null,"createTime":1513913667000,"startTime":1513913958000,"endTime":null,"successTime":null,"projectStatus":null,"totalInvester":0,"orderId":"FD1712220000007","orderStatus":5,"expiredTime":null,"identifyCertificate":null,"cusServiceId":900000116,"phoneNumber":"13633025660","assessmentPrice":120000,"carNumber":"AP1712220000007","baseRate":0.1,"baseRateToMoney":657.54,"activityRate":0,"activityRateToMoney":0,"activityDesc":"","noviceRate":0,"noviceRateToMoney":0,"noviceDesc":"","subjectType":1,"subjectName":"车款","totalRateMoney":657.54}
     * secondHandCarInfo : {"id":1000848,"carNumber":"AP1712220000007","city":"厦门","regCity":"杭州","carImage":"[{\"uploadImage\":\"http://119.23.48.207:8080/backWeb/images/verify/ORDER_CAR_FD1712220000007_9ee1cb09b6764836bbb68f62bb74bd35jpg.jpg\",\"uploadImageName\":\"ORDER_CAR_FD1712220000007_9ee1cb09b6764836bbb68f62bb74bd35jpg.jpg\",\"uploadDate\":1513925006009,\"uploadUserId\":900000030,\"uploadUserName\":\"超级用户\"}]","carBrand":"悟空","carColor":"黑色","buyTime":1513900800000,"registerTime":1513900800000,"assessmentPrice":120000,"buyPrice":120000,"patrolNumber":"浙A6******","carRoadHaul":"107","houseAddress":"","houseArea":0,"houseUse":"","creditRating":"","creditIndustry":"","creditWorkingCity":"","creditWorkingYears":"","loanUse":"1、专业评估机构评估，确保评估情况合理。\n2、平台对项目进行二次风险审核，严格筛选借款人，对于借款人的居住地进行了走访调查，确认真实性。\n3、借款人征信情况良好，无法院执行记录。\n4、借款人借款属于车辆质押借款类型，在前期调查了借款人还款来源，信用情况和自有资产情况下，予以小额度的借款，分周期分散了借款风险。\n5、如果到期客户出现逾期情况，可以通过借款合同向法院或仲裁机构提起起诉或仲裁申请，要求借款人赔付相应损失。","repaymentSource":"1、\t借款人还款来源为个人每月稳定收入，借款人工作情况稳定，有充足的偿还能力。\n2、\t风控团队已对借款人的证明材料进行了实地调查，确认借款人提供资料的真实性。\n3、\t通过社保情况调查，确认了借款人工作情况及收入情况，确保其还款能力。\n4、\t借款人自有汽车，在借款偿还完毕前，车辆存放入第三方保理公司汽车监管库，在借款人逾期的情况下，第三方保理公司将通过法律途径进行强制征收。综上，本项目投资人预期收益有充分的保障","reskConrol":"U车贷系列投资项目依托于第三方支付平台富友和利投网平台专业风控团队的严格审查，经过严格论证和实践操作，通过债权转让形式推出。根据借款人的借款金额，质抵押物情况和借款方提供的综合材料评级等确定是否借款和放款额度。","loanType":"1","projectNote":"U车贷系列项目是利投网针对车辆短期质押贷款模式推出的理财产品，对接有优质质抵押物的个人短期抵押借款出借（投资）服务，满足出借人对个人质抵押借款类项目的出借或投资需要,本次融资需求为人民币*****（RMB ￥*****）元。\n车贷系列项目拥有借款周期短、回报率高，按日计息，按月付息，到期还本，是具有较高资金流动性的理财服务模式，可在较短期限内，安全可靠的获得较高收益，完善资产配置。适用于当前有闲置资金，但是一段时间内将有使用需求的理财人群。","subjectName":"车款","companyType":"","legalPerson":"","businessLicenseNo":"","organizationCode":"","ratepayerNo":"","registeUnit":"","companyName":"","registeredAddress":"","creditCode":""}
     * mobilePhone : 136****5660
     * isIdentityUserInfo : true
     * isPhoneVerified : true
     * isBandCard : true
     * createTime : 1513753507000
     * identityCard : 211************698
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private FinancingOrderBean financingOrder;
    private SecondHandCarInfoBean secondHandCarInfo;
    private String mobilePhone;
    private boolean isIdentityUserInfo;
    private boolean isPhoneVerified;
    private boolean isBandCard;
    private long createTime;
    private String identityCard;
    private int investPeriod;
    private String interestMethod;

    public String getInterestMethod() {
        return interestMethod;
    }

    public void setInterestMethod(String interestMethod) {
        this.interestMethod = interestMethod;
    }

    public int getInvestPeriod() {
        return investPeriod;
    }

    public void setInvestPeriod(int investPeriod) {
        this.investPeriod = investPeriod;
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

    public FinancingOrderBean getFinancingOrder() {
        return financingOrder;
    }

    public void setFinancingOrder(FinancingOrderBean financingOrder) {
        this.financingOrder = financingOrder;
    }

    public SecondHandCarInfoBean getSecondHandCarInfo() {
        return secondHandCarInfo;
    }

    public void setSecondHandCarInfo(SecondHandCarInfoBean secondHandCarInfo) {
        this.secondHandCarInfo = secondHandCarInfo;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public boolean isIsIdentityUserInfo() {
        return isIdentityUserInfo;
    }

    public void setIsIdentityUserInfo(boolean isIdentityUserInfo) {
        this.isIdentityUserInfo = isIdentityUserInfo;
    }

    public boolean isIsPhoneVerified() {
        return isPhoneVerified;
    }

    public void setIsPhoneVerified(boolean isPhoneVerified) {
        this.isPhoneVerified = isPhoneVerified;
    }

    public boolean isIsBandCard() {
        return isBandCard;
    }

    public void setIsBandCard(boolean isBandCard) {
        this.isBandCard = isBandCard;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
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

    public static class SecondHandCarInfoBean {
        /**
         * id : 1000848
         * carNumber : AP1712220000007
         * city : 厦门
         * regCity : 杭州
         * carImage : [{"uploadImage":"http://119.23.48.207:8080/backWeb/images/verify/ORDER_CAR_FD1712220000007_9ee1cb09b6764836bbb68f62bb74bd35jpg.jpg","uploadImageName":"ORDER_CAR_FD1712220000007_9ee1cb09b6764836bbb68f62bb74bd35jpg.jpg","uploadDate":1513925006009,"uploadUserId":900000030,"uploadUserName":"超级用户"}]
         * carBrand : 悟空
         * carColor : 黑色
         * buyTime : 1513900800000
         * registerTime : 1513900800000
         * assessmentPrice : 120000.0
         * buyPrice : 120000.0
         * patrolNumber : 浙A6******
         * carRoadHaul : 107
         * houseAddress :
         * houseArea : 0.0
         * houseUse :
         * creditRating :
         * creditIndustry :
         * creditWorkingCity :
         * creditWorkingYears :
         * loanUse : 1、专业评估机构评估，确保评估情况合理。
         2、平台对项目进行二次风险审核，严格筛选借款人，对于借款人的居住地进行了走访调查，确认真实性。
         3、借款人征信情况良好，无法院执行记录。
         4、借款人借款属于车辆质押借款类型，在前期调查了借款人还款来源，信用情况和自有资产情况下，予以小额度的借款，分周期分散了借款风险。
         5、如果到期客户出现逾期情况，可以通过借款合同向法院或仲裁机构提起起诉或仲裁申请，要求借款人赔付相应损失。
         * repaymentSource : 1、	借款人还款来源为个人每月稳定收入，借款人工作情况稳定，有充足的偿还能力。
         2、	风控团队已对借款人的证明材料进行了实地调查，确认借款人提供资料的真实性。
         3、	通过社保情况调查，确认了借款人工作情况及收入情况，确保其还款能力。
         4、	借款人自有汽车，在借款偿还完毕前，车辆存放入第三方保理公司汽车监管库，在借款人逾期的情况下，第三方保理公司将通过法律途径进行强制征收。综上，本项目投资人预期收益有充分的保障
         * reskConrol : U车贷系列投资项目依托于第三方支付平台富友和利投网平台专业风控团队的严格审查，经过严格论证和实践操作，通过债权转让形式推出。根据借款人的借款金额，质抵押物情况和借款方提供的综合材料评级等确定是否借款和放款额度。
         * loanType : 1
         * projectNote : U车贷系列项目是利投网针对车辆短期质押贷款模式推出的理财产品，对接有优质质抵押物的个人短期抵押借款出借（投资）服务，满足出借人对个人质抵押借款类项目的出借或投资需要,本次融资需求为人民币*****（RMB ￥*****）元。
         车贷系列项目拥有借款周期短、回报率高，按日计息，按月付息，到期还本，是具有较高资金流动性的理财服务模式，可在较短期限内，安全可靠的获得较高收益，完善资产配置。适用于当前有闲置资金，但是一段时间内将有使用需求的理财人群。
         * subjectName : 车款
         * companyType :
         * legalPerson :
         * businessLicenseNo :
         * organizationCode :
         * ratepayerNo :
         * registeUnit :
         * companyName :
         * registeredAddress :
         * creditCode :
         */

        private int id;
        private String carNumber;
        private String city;
        private String regCity;
        private String carImage;
        private String carBrand;
        private String carColor;
        private long buyTime;
        private long registerTime;
        private double assessmentPrice;
        private double buyPrice;
        private String patrolNumber;
        private String carRoadHaul;
        private String houseAddress;
        private double houseArea;
        private String houseUse;
        private String creditRating;
        private String creditIndustry;
        private String creditWorkingCity;
        private String creditWorkingYears;
        private String loanUse;
        private String repaymentSource;
        private String reskConrol;
        /**
         * 1 -> 车贷  2 -> 房贷 3 -> 企业贷
         */
        private String loanType;
        private String projectNote;
        private String subjectName;
        private String companyType;
        private String legalPerson;
        private String businessLicenseNo;
        private String organizationCode;
        private String ratepayerNo;
        private String registeUnit;
        private String companyName;
        private String registeredAddress;
        private String creditCode;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(String carNumber) {
            this.carNumber = carNumber;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getRegCity() {
            return regCity;
        }

        public void setRegCity(String regCity) {
            this.regCity = regCity;
        }

        public List<ImageInfo> getCarImage() {
            List<ImageInfo> carImages = null;
            if(carImage != null && !StringUtil.isEmpty(carImage)){
                Gson gson = new Gson();
                Type listType = new TypeToken<List<ImageInfo>>(){}.getType();
                carImages = gson.fromJson(carImage, listType);
            }
            return carImages;
        }

        public void setCarImage(String carImage) {
            this.carImage = carImage;
        }

        public String getCarBrand() {
            return carBrand;
        }

        public void setCarBrand(String carBrand) {
            this.carBrand = carBrand;
        }

        public String getCarColor() {
            return carColor;
        }

        public void setCarColor(String carColor) {
            this.carColor = carColor;
        }

        public long getBuyTime() {
            return buyTime;
        }

        public void setBuyTime(long buyTime) {
            this.buyTime = buyTime;
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

        public double getBuyPrice() {
            return buyPrice;
        }

        public void setBuyPrice(double buyPrice) {
            this.buyPrice = buyPrice;
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

        public double getHouseArea() {
            return houseArea;
        }

        public void setHouseArea(double houseArea) {
            this.houseArea = houseArea;
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
    }

    public static class ImageInfo{

        /**
         * uploadImage : https://www.uumoney.cn/backWeb/images/verify/ORDER_CAR_FD1712220000043_ba7c45a35d69465b8001537d372caf77jpg.jpg
         * uploadImageName : ORDER_CAR_FD1712220000043_ba7c45a35d69465b8001537d372caf77jpg.jpg
         * uploadDate : 1513933366963
         * uploadUserId : 900000122
         * uploadUserName : 褚炜栋
         */

        private String uploadImage;
        private String uploadImageName;
        private long uploadDate;
        private int uploadUserId;
        private String uploadUserName;

        public String getUploadImage() {
            return uploadImage;
        }

        public void setUploadImage(String uploadImage) {
            this.uploadImage = uploadImage;
        }

        public String getUploadImageName() {
            return uploadImageName;
        }

        public void setUploadImageName(String uploadImageName) {
            this.uploadImageName = uploadImageName;
        }

        public long getUploadDate() {
            return uploadDate;
        }

        public void setUploadDate(long uploadDate) {
            this.uploadDate = uploadDate;
        }

        public int getUploadUserId() {
            return uploadUserId;
        }

        public void setUploadUserId(int uploadUserId) {
            this.uploadUserId = uploadUserId;
        }

        public String getUploadUserName() {
            return uploadUserName;
        }

        public void setUploadUserName(String uploadUserName) {
            this.uploadUserName = uploadUserName;
        }
    }
}
