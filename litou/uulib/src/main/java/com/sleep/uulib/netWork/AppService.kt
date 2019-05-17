package com.sleep.uulib.netWork

import com.google.gson.JsonObject
import com.sleep.uulib.bean.*
import com.sleep.uulib.constant.NetConstant
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*


/**
 * Created by SleepYM09 on 2017/10/18.
 *
 * blog: www.sleepym09.com
 */
interface AppService {

    /**
     * 通用网络获取json网络请求
     */
    @POST("{path}/")
    fun postWithJson(@Body map: TreeMap<String, Any>, @Path(value = "path", encoded = true) url: String): Observable<JsonObject>

    /**
     * 获取用户信息
     */
    @POST(NetConstant.CHECK_PHONE_IS_USED)
    fun checkPhoneIsUsed(@Body map: TreeMap<String, Any>): Observable<CheckPhoneBean>

    /**
     * 获取首页banner数据
     */
    @POST(NetConstant.HOME_BANNER)
    fun getHomeBannerData(@Body map: TreeMap<String, Any>): Observable<HomeBannerBean>

    /**
     * 获取首页FinanceList数据
     */
    @POST(NetConstant.HOME_FINANCE_MANAGER_LIST)
    fun getFinanceListData(@Body map: TreeMap<String, Any>): Observable<HomeFinanceListBean>

    /**
     * 获取首页公告数据
     */
    @POST(NetConstant.HOME_ANNOCEMENT_LIST)
    fun getAnnocementsData(@Body map: TreeMap<String, Any>): Observable<HomeAnnocementsBean>

    /**
     * 获取理财页数据
     */
    @POST(NetConstant.FINANCE_MANAGER_LIST)
    fun getFinanceManagerList(@Body map: TreeMap<String, Any>): Observable<FinanceListBean>

    /**
     * 登录
     */
    @POST(NetConstant.LOGIN)
    fun login(@Body treeMap: TreeMap<String, Any?>): Observable<LoginStepTwoBean>

    /**
     * 获取验证码
     */
    @POST(NetConstant.GET_VERIFY_CODE)
    fun getVerifyCode(@Body treeMap: TreeMap<String, Any?>): Observable<VerificationCodeBean>

    /**
     * 找回密码
     */
    @POST(NetConstant.FIND_PASSWORD)
    fun findPassword(@Body treeMap: TreeMap<String, Any?>): Observable<FindPasswordBean>

    /**
     * 获取项目详情
     */
    @POST(NetConstant.PROJECT_DETAIL)
    fun getProjectDetail(@Body treeMap: TreeMap<String, Any?>): Observable<ProjectDetailBean>

    /**
     * 获取当前用户是否投资过当前标的
     */
    @POST(NetConstant.CHECK_INVESTED)
    fun checkInvested(@Body treeMap: TreeMap<String, Any?>): Observable<CheckInvestedBean>

    /**
     * 获取理财页数据
     */
    @POST(NetConstant.PROJECT_INVEST_RECORD)
    fun getInvestRecord(@Body map: TreeMap<String, Any>): Observable<InvestRecordListBean>

    /**
     * 查询用户资料
     */
    @POST(NetConstant.QUERY_USER_INFO)
    fun queryUserInfo(@Body map: TreeMap<String, Any>): Observable<QueryUserInfoBean>

    /**
     * 查询用户银行信息
     */
    @POST(NetConstant.QUERY_BIND_BANK_INFO)
    fun queryBank(@Body map: TreeMap<String, Any>): Observable<QueryBankBean>

    /**
     * 预充值，获取订单号
     */
    @POST(NetConstant.PRE_PAYMENT)
    fun prerecharge(@Body map: TreeMap<String, Any>): Observable<PrepaymentBean>

    /**
     * 获取请求富有充值的表单
     */
    @POST("{path}/")
    fun RealRecharge(@Body map: TreeMap<String, Any>, @Path(value = "path", encoded = true) url: String): Observable<RealRechargeResultBean>

    /**
     * 获取免费提现额度
     */
    @POST(NetConstant.QUERY_WITH_DRAW_LIMIT)
    fun refreshFreeLimit(@Body map: TreeMap<String, Any>): Observable<WithdrawLimitBean>

    /**
     * 提现
     */
    @POST(NetConstant.WITHDRAW)
    fun withdraw(@Body map: TreeMap<String, Any>): Observable<WithdrawBean>

    /**
     * 退出登录
     */
    @POST(NetConstant.LOGOUT)
    fun logout(@Body map: TreeMap<String, Any>): Observable<Any>

    /**
     * 修改支付密码
     */
    @POST(NetConstant.CHANGE_TRADING_PASSWORD)
    fun modifyTradingPwd(@Body map: TreeMap<String, Any?>): Observable<ModifyTradingPwdBean>

    /**
     * 设置支付密码
     */
    @POST(NetConstant.SET_TRADING_PASSWORD)
    fun setTradingPwd(@Body map: TreeMap<String, Any?>): Observable<SetTradingPwdBean>

    /**
     * 获取资金明细数据
     */
    @POST(NetConstant.FINANCIAL_DETAILS)
    fun getFinancialDetailData(@Body map: TreeMap<String, Any?>): Observable<InvestDetailBean>

    /**
     * 获取资金明细数据
     */
    @POST(NetConstant.INVEST_RECORD)
    fun getMyInvestData(@Body map: TreeMap<String, Any?>): Observable<InvestRecordBean>

    /**
     * 获取回款日程
     */
    @POST(NetConstant.PAYMENT_SCHEDULE)
    fun getPaymentSchedule(@Body map: TreeMap<String, Any?>): Observable<PaymentScheduleBean>

    /**
     * 获取我的优惠(红包)
     */
    @POST(NetConstant.RED_ENVELOPS)
    fun getRedPacketData(@Body map: TreeMap<String, Any?>): Observable<RedEnvelopeBean>
    /**
     * 帮助中心菜单
     */
    @POST(NetConstant.HELP_CENTER_MENU)
    fun getHelpCenterMenuData(@Body map: TreeMap<String, Any?>): Observable<HelpBean>
    /**
     * 加息卷和红包总数
     */
    @POST(NetConstant.RED_PAG_AND_COUPON)
    fun getRedBagAndCouPon(@Body map: TreeMap<String, Any?>): Observable<RedBagAndCouponBean>
    /**
     * 帮助中心item
     */
    @POST(NetConstant.HELP_CENTER_ITEM)
    fun getHelpCenterItemData(@Body map: TreeMap<String, Any?>): Observable<HelpCenterBean>

    /**
     * 获取我的优惠（加息券）
     */
    @POST(NetConstant.MY_COUPON)
    fun getTicketData(@Body map: TreeMap<String, Any?>): Observable<TicketBean>

    /**
     * 保存地址
     */
    @POST(NetConstant.UPDATA_ADDRESS)
    fun saveAddress(@Body map: TreeMap<String, Any?>): Observable<UpdataAddressBean>

    /**
     * 查询用户是否签到
     */
    @POST(NetConstant.IS_SIGNED)
    fun queryUserIsSign(@Body map: TreeMap<String, Any?>): Observable<IsSignedBean>

    /**
     * 签到
     */
    @POST(NetConstant.USER_SIGN)
    fun userSign(@Body map: TreeMap<String, Any?>): Observable<SignBean>

    /**
     * 获取积分商品
     */
    @POST(NetConstant.GET_INTEGRAL_COMMODITY)
    fun getIntegralCommodity(@Body map: TreeMap<String, Any?>): Observable<IntegralCommodityBean>

    /**
     * 用户反馈
     */
    @POST(NetConstant.ADVICE_FEEDBACK)
    fun submitFeedback(@Body map: TreeMap<String, Any?>): Observable<AdviceFeedbackBean>

    /**
     * 精彩活动 直接获取首页banner数据
     */
    @POST(NetConstant.HOME_BANNER)
    fun getWonderfulData(@Body map: TreeMap<String, Any?>): Observable<HomeBannerBean>


    /**
     * 选择红包
     */
    @POST(NetConstant.MY_WELFARE_LIST)
    fun getCanUserWelfare(@Body map: TreeMap<String, Any?>): Observable<ChooseWelfareBean>

    /**
     * 创建订单
     */
    @POST(NetConstant.CREATE_ORDER)
    fun creatInvestOrder(@Body map: TreeMap<String, Any?>): Observable<CreateOrderBean>

    /**
     * 订单支付
     */
    @POST(NetConstant.PAYMENT)
    fun invest(@Body map: TreeMap<String, Any?>): Observable<InvestResultBean>

    /**
     * 订单支付
     */
    @POST(NetConstant.USER_REGISTER)
    fun regist(@Body map: TreeMap<String, Any?>): Observable<RegisterInfo>

    /**
     * 订单支付
     */
    @POST(NetConstant.AUTHENTICATION)
    fun getIdentificationData(@Body map: TreeMap<String, Any?>): Observable<CertificationBean>

    /**
     * 银行限额说明
     */
    @GET(NetConstant.BANK_LIMIT)
    fun getRechargeLimitData(): Observable<RechargeLimitBean>

    /**
     * 获取详情中的限额
     */
    @GET(NetConstant.GET_NOVICE_LIMIT)
    fun getProjectDetailLimitData(): Observable<ProjectDetailLimit>

    /**
     *根据单号查询付息表
     */
    @POST(NetConstant.QUERY_INVEST_LIST_BY_ID)
    fun queryInvestListByOrderId(@Body map: TreeMap<String, Any?>): Observable<QueryInvestByIdBean>

    /**
     *获取splash广告图
     */
    @POST(NetConstant.SPLASH_BANNER_URL)
    fun getSplashBannerUrl(@Body map: TreeMap<String, Any?>): Observable<SplashBannerBean>
}