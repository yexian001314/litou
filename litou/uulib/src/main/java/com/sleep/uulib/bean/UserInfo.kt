package com.sleep.uulib.bean

/**
 * Created by SleepYM09 on 2017/10/18.
 *
 * blog: www.sleepym09.com
 */
class UserInfo {
    /**
     * id : 1000309
     * deviceId :
     * deviceType : 3
     * deviceName :
     * password : 9n6RLoKwSvVo94cYrVttUgwk5H0=
     * realName : 何乐聪
     * nickName :
     * mobilePhone : 18701418790
     * identityCard : 33068119950120705x
     * isIdentityVerified : true
     * isPhoneVerified : true
     * isBandCard : false
     * sex : 1
     * qq : 123
     * weiXin : 2321321
     * avatarUrl :
     * identityCardUrl :
     * totalMoney : 703635.61
     * balanceMoney : 676155.61
     * loanMoney : 2000
     * createTime : 1500276484000
     * lastUpdateTime : 1500345923000
     * customerServiceId : 900000063
     * isDeleted : false
     * userType : 5
     * gesturePw :
     * isGesturePwBinded : false
     * isIdentityUserInfo : true
     * isWithdrawLock : false
     * isDebt : false
     * bankName :
     * bankCardCode :
     * address : 浙江
     * email : 111@111.com
     * relatedMobilePhone :
     * signCount : 3
     * totalPoints : 36
     * totalInvestMoney : 40000
     * freeWithdrawNum : 0
     * vipLevel : 0
     * freeWithdrawLimit : 403635.61
     * monthInvestMoney : 20000
     * investingAmount : -180000
     * investingIncome : -85479.47
     * freezingAmount : 207700
     * hasIncome : 203835.61
     * totalIncome : 118356.14
     * monthInvestIncome : 203835.61
     * tradersPw : AXbrgGcfM1UNxy8edbzutiSdaCc=
     * isTradersPwBinded : true
     * registryPlatform :
     * channelId :
     * loginAccount : 18701418790
     */

    private var id: Long = 0
    private var deviceId: String? = null
    private var deviceType: Int = 0
    private var deviceName: String? = null
    private var password: String? = null
    private var realName: String? = null
    private var nickName: String? = null
    private var mobilePhone: String? = null
    private var identityCard: String? = null
    private var isIdentityVerified: Boolean = false
    private var isPhoneVerified: Boolean = false
    private var isBandCard: Boolean = false
    private var sex: Int = 0
    private var qq: String? = null
    private var weiXin: String? = null
    private var avatarUrl: String? = null
    private var identityCardUrl: String? = null
    private var totalMoney: Double = 0.toDouble()
    private var balanceMoney: Double = 0.toDouble()
    private var loanMoney: Double = 0.toDouble()
    private var createTime: Long = 0
    private var lastUpdateTime: Long = 0
    private var customerServiceId: Long = 0
    private var isDeleted: Boolean = false
    private var userType: Int = 0
    private var gesturePw: String? = null
    private var isGesturePwBinded: Boolean = false
    private var isIdentityUserInfo: Boolean = false
    private var isWithdrawLock: Boolean = false
    private var isDebt: Boolean = false
    private var bankInfo: QueryBankBean.BankCardEOsBean? = null
    private var address: String? = null
    private var email: String? = null
    private var relatedMobilePhone: String? = null
    private var signCount: Int = 0
    private var totalPoints: Double = 0.toDouble()
    private var totalInvestMoney: Double = 0.toDouble()
    private var freeWithdrawNum: Int = 0
    private var vipLevel: String? = null
    private var freeWithdrawLimit: Double = 0.toDouble()
    private var monthInvestMoney: Double = 0.toDouble()
    private var investingAmount: Double = 0.toDouble()
    private var investingIncome: Double = 0.toDouble()
    private var freezingAmount: Double = 0.toDouble()
    private var hasIncome: Double = 0.toDouble()
    private var totalIncome: Double = 0.toDouble()
    private var monthInvestIncome: Double = 0.toDouble()
    private var tradersPw: String? = null
    private var isTradersPwBinded: Boolean = false
    private var registryPlatform: String? = null
    private var channelId: String? = null
    private var loginAccount: String? = null

    private var isSign: Boolean = false

    fun isSign(): Boolean {
        return isSign
    }

    fun setSign(sign: Boolean): UserInfo {
        isSign = sign
        return this
    }

    fun getId(): Long {
        return id
    }

    fun setId(id: Long) {
        this.id = id
    }

    fun getDeviceId(): String? {
        return deviceId
    }

    fun setDeviceId(deviceId: String) {
        this.deviceId = deviceId
    }

    fun getDeviceType(): Int {
        return deviceType
    }

    fun setDeviceType(deviceType: Int) {
        this.deviceType = deviceType
    }

    fun getDeviceName(): String? {
        return deviceName
    }

    fun setDeviceName(deviceName: String) {
        this.deviceName = deviceName
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getRealName(): String? {
        return realName
    }

    fun setRealName(realName: String) {
        this.realName = realName
    }

    fun getNickName(): String? {
        return nickName
    }

    fun setNickName(nickName: String) {
        this.nickName = nickName
    }

    fun getMobilePhone(): String? {
        return mobilePhone
    }

    fun setMobilePhone(mobilePhone: String) {
        this.mobilePhone = mobilePhone
    }

    fun getIdentityCard(): String? {
        return identityCard
    }

    fun setIdentityCard(identityCard: String) {
        this.identityCard = identityCard
    }

    fun isIsIdentityVerified(): Boolean {
        return isIdentityVerified
    }

    fun setIsIdentityVerified(isIdentityVerified: Boolean) {
        this.isIdentityVerified = isIdentityVerified
    }

    fun isIsPhoneVerified(): Boolean {
        return isPhoneVerified
    }

    fun setIsPhoneVerified(isPhoneVerified: Boolean) {
        this.isPhoneVerified = isPhoneVerified
    }

    fun isIsBandCard(): Boolean {
        return isBandCard
    }

    fun setIsBandCard(isBandCard: Boolean) {
        this.isBandCard = isBandCard
    }

    fun getSex(): Int {
        return sex
    }

    fun setSex(sex: Int) {
        this.sex = sex
    }

    fun getQq(): String? {
        return qq
    }

    fun setQq(qq: String) {
        this.qq = qq
    }

    fun getWeiXin(): String? {
        return weiXin
    }

    fun setWeiXin(weiXin: String) {
        this.weiXin = weiXin
    }

    fun getAvatarUrl(): String? {
        return avatarUrl
    }

    fun setAvatarUrl(avatarUrl: String) {
        this.avatarUrl = avatarUrl
    }

    fun getIdentityCardUrl(): String? {
        return identityCardUrl
    }

    fun setIdentityCardUrl(identityCardUrl: String) {
        this.identityCardUrl = identityCardUrl
    }

    fun getTotalMoney(): Double {
        return totalMoney
    }

    fun setTotalMoney(totalMoney: Double) {
        this.totalMoney = totalMoney
    }

    fun getBalanceMoney(): Double {
        return balanceMoney
    }

    fun setBalanceMoney(balanceMoney: Double) {
        this.balanceMoney = balanceMoney
    }

    fun getLoanMoney(): Double {
        return loanMoney
    }

    fun setLoanMoney(loanMoney: Double) {
        this.loanMoney = loanMoney
    }

    fun getCreateTime(): Long {
        return createTime
    }

    fun setCreateTime(createTime: Long) {
        this.createTime = createTime
    }

    fun getLastUpdateTime(): Long {
        return lastUpdateTime
    }

    fun setLastUpdateTime(lastUpdateTime: Long) {
        this.lastUpdateTime = lastUpdateTime
    }

    fun getCustomerServiceId(): Long {
        return customerServiceId
    }

    fun setCustomerServiceId(customerServiceId: Long) {
        this.customerServiceId = customerServiceId
    }

    fun isIsDeleted(): Boolean {
        return isDeleted
    }

    fun setIsDeleted(isDeleted: Boolean) {
        this.isDeleted = isDeleted
    }

    fun getUserType(): Int {
        return userType
    }

    fun setUserType(userType: Int) {
        this.userType = userType
    }

    fun getGesturePw(): String? {
        return gesturePw
    }

    fun setGesturePw(gesturePw: String) {
        this.gesturePw = gesturePw
    }

    fun isIsGesturePwBinded(): Boolean {
        return isGesturePwBinded
    }

    fun setIsGesturePwBinded(isGesturePwBinded: Boolean) {
        this.isGesturePwBinded = isGesturePwBinded
    }

    fun isIsIdentityUserInfo(): Boolean {
        return isIdentityUserInfo
    }

    fun setIsIdentityUserInfo(isIdentityUserInfo: Boolean) {
        this.isIdentityUserInfo = isIdentityUserInfo
    }

    fun isIsWithdrawLock(): Boolean {
        return isWithdrawLock
    }

    fun setIsWithdrawLock(isWithdrawLock: Boolean) {
        this.isWithdrawLock = isWithdrawLock
    }

    fun isIsDebt(): Boolean {
        return isDebt
    }

    fun setIsDebt(isDebt: Boolean) {
        this.isDebt = isDebt
    }

    fun getBankInfo(): QueryBankBean.BankCardEOsBean? {
        return bankInfo
    }

    fun setBankInfo(bankName: QueryBankBean.BankCardEOsBean) {
        this.bankInfo = bankName
    }

    fun getAddress(): String? {
        return address
    }

    fun setAddress(address: String) {
        this.address = address
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getRelatedMobilePhone(): String? {
        return relatedMobilePhone
    }

    fun setRelatedMobilePhone(relatedMobilePhone: String) {
        this.relatedMobilePhone = relatedMobilePhone
    }

    fun getSignCount(): Int {
        return signCount
    }

    fun setSignCount(signCount: Int) {
        this.signCount = signCount
    }

    fun getTotalPoints(): Double {
        return totalPoints
    }

    fun setTotalPoints(totalPoints: Double) {
        this.totalPoints = totalPoints
    }

    fun getTotalInvestMoney(): Double {
        return totalInvestMoney
    }

    fun setTotalInvestMoney(totalInvestMoney: Double) {
        this.totalInvestMoney = totalInvestMoney
    }

    fun getFreeWithdrawNum(): Int {
        return freeWithdrawNum
    }

    fun setFreeWithdrawNum(freeWithdrawNum: Int) {
        this.freeWithdrawNum = freeWithdrawNum
    }

    fun getVipLevel(): String? {
        return vipLevel
    }

    fun setVipLevel(vipLevel: String) {
        this.vipLevel = vipLevel
    }

    fun getFreeWithdrawLimit(): Double {
        return freeWithdrawLimit
    }

    fun setFreeWithdrawLimit(freeWithdrawLimit: Double) {
        this.freeWithdrawLimit = freeWithdrawLimit
    }

    fun getMonthInvestMoney(): Double {
        return monthInvestMoney
    }

    fun setMonthInvestMoney(monthInvestMoney: Double) {
        this.monthInvestMoney = monthInvestMoney
    }

    fun getInvestingAmount(): Double {
        return investingAmount
    }

    fun setInvestingAmount(investingAmount: Double) {
        this.investingAmount = investingAmount
    }

    fun getInvestingIncome(): Double {
        return investingIncome
    }

    fun setInvestingIncome(investingIncome: Double) {
        this.investingIncome = investingIncome
    }

    fun getFreezingAmount(): Double {
        return freezingAmount
    }

    fun setFreezingAmount(freezingAmount: Double) {
        this.freezingAmount = freezingAmount
    }

    fun getHasIncome(): Double {
        return hasIncome
    }

    fun setHasIncome(hasIncome: Double) {
        this.hasIncome = hasIncome
    }

    fun getTotalIncome(): Double {
        return totalIncome
    }

    fun setTotalIncome(totalIncome: Double) {
        this.totalIncome = totalIncome
    }

    fun getMonthInvestIncome(): Double {
        return monthInvestIncome
    }

    fun setMonthInvestIncome(monthInvestIncome: Double) {
        this.monthInvestIncome = monthInvestIncome
    }

    fun getTradersPw(): String? {
        return tradersPw
    }

    fun setTradersPw(tradersPw: String) {
        this.tradersPw = tradersPw
    }

    fun isIsTradersPwBinded(): Boolean {
        return isTradersPwBinded
    }

    fun setIsTradersPwBinded(isTradersPwBinded: Boolean) {
        this.isTradersPwBinded = isTradersPwBinded
    }

    fun getRegistryPlatform(): String? {
        return registryPlatform
    }

    fun setRegistryPlatform(registryPlatform: String) {
        this.registryPlatform = registryPlatform
    }

    fun getChannelId(): String? {
        return channelId
    }

    fun setChannelId(channelId: String) {
        this.channelId = channelId
    }

    fun getLoginAccount(): String? {
        return loginAccount
    }

    fun setLoginAccount(loginAccount: String) {
        this.loginAccount = loginAccount
    }

    override fun toString(): String {
        return "UserInfo(id=$id, deviceId=$deviceId, deviceType=$deviceType, deviceName=$deviceName, password=$password, realName=$realName, nickName=$nickName, mobilePhone=$mobilePhone, identityCard=$identityCard, isIdentityVerified=$isIdentityVerified, isPhoneVerified=$isPhoneVerified, isBandCard=$isBandCard, sex=$sex, qq=$qq, weiXin=$weiXin, avatarUrl=$avatarUrl, identityCardUrl=$identityCardUrl, totalMoney=$totalMoney, balanceMoney=$balanceMoney, loanMoney=$loanMoney, createTime=$createTime, lastUpdateTime=$lastUpdateTime, customerServiceId=$customerServiceId, isDeleted=$isDeleted, userType=$userType, gesturePw=$gesturePw, isGesturePwBinded=$isGesturePwBinded, isIdentityUserInfo=$isIdentityUserInfo, isWithdrawLock=$isWithdrawLock, isDebt=$isDebt, bankInfo=$bankInfo, address=$address, email=$email, relatedMobilePhone=$relatedMobilePhone, signCount=$signCount, totalPoints=$totalPoints, totalInvestMoney=$totalInvestMoney, freeWithdrawNum=$freeWithdrawNum, vipLevel=$vipLevel, freeWithdrawLimit=$freeWithdrawLimit, monthInvestMoney=$monthInvestMoney, investingAmount=$investingAmount, investingIncome=$investingIncome, freezingAmount=$freezingAmount, hasIncome=$hasIncome, totalIncome=$totalIncome, monthInvestIncome=$monthInvestIncome, tradersPw=$tradersPw, isTradersPwBinded=$isTradersPwBinded, registryPlatform=$registryPlatform, channelId=$channelId, loginAccount=$loginAccount, isSign=$isSign)"
    }
}