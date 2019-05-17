package com.sleep.uulib.netWork

import com.google.gson.JsonObject
import com.sleep.uulib.bean.*
import java.util.*


/**
 * 作者：alsoWell on 2017/7/24 14:46
 * 邮箱：1161882463@qq.com
 */
class AppRequest private constructor() : RetrofitManager() {

    companion object {
        private val apprequest = AppRequest()

        val INSTANCE: AppRequest = apprequest
    }

    val service = getRetrofit().create(AppService::class.java)!!

    /**
     * 检查手机号是否注册过
     */
    fun checkPhoneIsUsed(treeMap: TreeMap<String, Any>, observer: ProgressObserver<CheckPhoneBean>) {
        val observable = service.checkPhoneIsUsed(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 获取原始json方法示例
     */
    fun postWithJson(treeMap: TreeMap<String, Any>, path: String, observer: BaseObserver<JsonObject>) {
        val observable = service.postWithJson(treeMap, path)
        setSubscribe(observable, observer)
    }

    /**
     * 获取首页banner数据
     */
    fun getHomeBannerData(treeMap: TreeMap<String, Any>, observer: BaseObserver<HomeBannerBean>) {
        val observable = service.getHomeBannerData(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 获取首页FinanceList数据
     */
    fun getHomeFinanceListData(treeMap: TreeMap<String, Any>, observer: BaseObserver<HomeFinanceListBean>) {
        val observable = service.getFinanceListData(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 获取首页公告数据
     */
    fun getAnnocementsData(treeMap: TreeMap<String, Any>, observer: BaseObserver<HomeAnnocementsBean>) {
        val observable = service.getAnnocementsData(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 获取理财页数据
     */
    fun getFinanceManagerList(treeMap: TreeMap<String, Any>, observer: BaseObserver<FinanceListBean>) {
        val observable = service.getFinanceManagerList(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 登录
     */
    fun login(treeMap: TreeMap<String, Any?>, progressObserver: ProgressObserver<LoginStepTwoBean>) {
        val observable = service.login(treeMap)
        setSubscribe(observable, progressObserver)
    }

    /**
     * 获取验证码
     */
    fun getVerifyCode(treeMap: TreeMap<String, Any?>, progressObserver: ProgressObserver<VerificationCodeBean>) {
        val observable = service.getVerifyCode(treeMap)
        setSubscribe(observable, progressObserver)
    }

    /**
     * 找回密码
     */
    fun findPassword(treeMap: TreeMap<String, Any?>, progressObserver: ProgressObserver<FindPasswordBean>) {
        val observable = service.findPassword(treeMap)
        setSubscribe(observable, progressObserver)
    }

    /**
     * 获取项目详情资料
     */
    fun getProjectDetail(treeMap: TreeMap<String, Any?>, baseObserver: BaseObserver<ProjectDetailBean>) {
        val observable = service.getProjectDetail(treeMap)
        setSubscribe(observable, baseObserver)
    }

    /**
     * 获取当前用户是否投资过当前标的
     */
    fun checkInvested(treeMap: TreeMap<String, Any?>, baseObserver: BaseObserver<CheckInvestedBean>) {
        val observable = service.checkInvested(treeMap)
        setSubscribe(observable, baseObserver)
    }

    /**
     * 获取项目详情中投资记录
     */
    fun getInvestRecord(treeMap: TreeMap<String, Any>, observer: BaseObserver<InvestRecordListBean>) {
        val observable = service.getInvestRecord(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 查询用户信息
     */
    fun queryUserInfo(treeMap: TreeMap<String, Any>, observer: BaseObserver<QueryUserInfoBean>) {
        val observable = service.queryUserInfo(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 查询用户银行信息
     */
    fun queryBank(treeMap: TreeMap<String, Any>, observer: BaseObserver<QueryBankBean>) {
        val observable = service.queryBank(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 预充值，获取订单号
     */
    fun prerecharge(treeMap: TreeMap<String, Any>, observer: BaseObserver<PrepaymentBean>) {
        val observable = service.prerecharge(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 充值，获取富有充值提交表单
     */
    fun realRecharge(treeMap: TreeMap<String, Any>, path: String, observer: BaseObserver<RealRechargeResultBean>) {
        val observable = service.RealRecharge(treeMap, path)
        setSubscribe(observable, observer)
    }

    /**
     * 获取免费提现额度
     */
    fun refreshFreeLimit(treeMap: TreeMap<String, Any>, observer: BaseObserver<WithdrawLimitBean>) {
        val observable = service.refreshFreeLimit(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 提现
     */
    fun withdraw(treeMap: TreeMap<String, Any>, observer: BaseObserver<WithdrawBean>) {
        val observable = service.withdraw(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 退出登录
     */
    fun logout(treeMap: TreeMap<String, Any>, observer: BaseObserver<Any>) {
        val observable = service.logout(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 修改支付密码
     */
    fun modifyTradingPwd(treeMap: TreeMap<String, Any?>, observer: BaseObserver<ModifyTradingPwdBean>) {
        val observable = service.modifyTradingPwd(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 设置支付密码
     */
    fun setTradingPwd(treeMap: TreeMap<String, Any?>, observer: BaseObserver<SetTradingPwdBean>) {
        val observable = service.setTradingPwd(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 获取资金明细数据
     */
    fun getFinancialDetailData(treeMap: TreeMap<String, Any?>, observer: BaseObserver<InvestDetailBean>) {
        val observable = service.getFinancialDetailData(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 获取我的投资数据
     */
    fun getMyInvestData(treeMap: TreeMap<String, Any?>, observer: BaseObserver<InvestRecordBean>) {
        val observable = service.getMyInvestData(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 获取回款日程
     */
    fun getPaymentSchedule(treeMap: TreeMap<String, Any?>, observer: BaseObserver<PaymentScheduleBean>) {
        val observable = service.getPaymentSchedule(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 获取我的优惠(红包)
     */
    fun getRedPacketData(treeMap: TreeMap<String, Any?>, observer: BaseObserver<RedEnvelopeBean>) {
        val observable = service.getRedPacketData(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 获取我的优惠(红包)
     */
    fun getHelpCenterMenuData(treeMap: TreeMap<String, Any?>, observer: BaseObserver<HelpBean>) {
        val observable = service.getHelpCenterMenuData(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 获取我的优惠(红包)
     */
    fun getRedBagAndCoupon(treeMap: TreeMap<String, Any?>, observer: BaseObserver<RedBagAndCouponBean>) {
        val observable = service.getRedBagAndCouPon(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 获取我的优惠(红包)
     */
    fun getHelpCenterItemData(treeMap: TreeMap<String, Any?>, observer: BaseObserver<HelpCenterBean>) {
        val observable = service.getHelpCenterItemData(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 获取我的优惠(加息券)
     */
    fun getTicketData(treeMap: TreeMap<String, Any?>, observer: BaseObserver<TicketBean>) {
        val observable = service.getTicketData(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 保存地址
     */
    fun saveAddress(treeMap: TreeMap<String, Any?>, observer: BaseObserver<UpdataAddressBean>) {
        val observable = service.saveAddress(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 查询用户是否签到
     */
    fun queryUserIsSign(treeMap: TreeMap<String, Any?>, observer: BaseObserver<IsSignedBean>) {
        val observable = service.queryUserIsSign(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 签到
     */
    fun userSign(treeMap: TreeMap<String, Any?>, observer: BaseObserver<SignBean>) {
        val observable = service.userSign(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 获取积分商品
     */
    fun getIntegralCommodity(treeMap: TreeMap<String, Any?>, observer: BaseObserver<IntegralCommodityBean>) {
        val observable = service.getIntegralCommodity(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 用户反馈
     */
    fun submitFeedback(treeMap: TreeMap<String, Any?>, observer: BaseObserver<AdviceFeedbackBean>) {
        val observable = service.submitFeedback(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 精彩活动
     */
    fun getWonderfulData(treeMap: TreeMap<String, Any?>, observer: BaseObserver<HomeBannerBean>) {
        val observable = service.getWonderfulData(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 可用红包
     */
    fun getCanUserWelfare(treeMap: TreeMap<String, Any?>, observer: BaseObserver<ChooseWelfareBean>) {
        val observable = service.getCanUserWelfare(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 创建订单
     */
    fun creatInvestOrder(treeMap: TreeMap<String, Any?>, observer: BaseObserver<CreateOrderBean>) {
        val observable = service.creatInvestOrder(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 投资
     */
    fun invest(treeMap: TreeMap<String, Any?>, observer: BaseObserver<InvestResultBean>) {
        val observable = service.invest(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 注册
     */
    fun regist(treeMap: TreeMap<String, Any?>, observer: BaseObserver<RegisterInfo>) {
        val observable = service.regist(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 实名认证
     */
    fun getIdentificationData(treeMap: TreeMap<String, Any?>, observer: BaseObserver<CertificationBean>) {
        val observable = service.getIdentificationData(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 限额列表
     */
    fun getRechargeLimitData(observer: BaseObserver<RechargeLimitBean>) {
        val observable = service.getRechargeLimitData()
        setSubscribe(observable, observer)
    }

    /**
     * 获取详情中的限额
     */
    fun getProjectDetailLimitData(observer: BaseObserver<ProjectDetailLimit>) {
        val observable = service.getProjectDetailLimitData()
        setSubscribe(observable, observer)
    }

    /**
     * 根据单号查询付息表
     */
    fun queryInvestListByOrderId(treeMap: TreeMap<String, Any?>, observer: BaseObserver<QueryInvestByIdBean>) {
        val observable = service.queryInvestListByOrderId(treeMap)
        setSubscribe(observable, observer)
    }

    /**
     * 获取splash广告图
     */
    fun getSplashBannerUrl(treeMap: TreeMap<String, Any?>, observer: BaseObserver<SplashBannerBean>) {
        val observable = service.getSplashBannerUrl(treeMap)
        setSubscribe(observable, observer)
    }
}
