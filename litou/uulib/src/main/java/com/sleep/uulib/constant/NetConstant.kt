package com.sleep.uulib.constant

/**
 * Created by SleepYM09 on 2017/10/18.
 *
 * blog: www.sleepym09.com
 */
object NetConstant {
    //------------------------------------- Response\Code -------------------------------------------------------

    //成功
    const val RESULT_SUCCESS = "00"

    //服务端报错01
    const val RESULT_ERROR_01 = "01"

    //服务端报错X00
    const val RESULT_ERROR_X00 = "X00"

    //他端登录
    const val RESULT_NOT_LOGIN = "11"

    //------------------------------------- 请求url ------------------------------------------------------------


    //开发环境
    const val DEV_URL = "http://119.23.48.207:8080/"
    //开发环境
    const val DEV_WEB_URL = "http://119.23.48.207:8080/"
    //测试环境
    const val TEST_URL = "http://120.79.91.39/"
    //测试环境
    const val TEST_WEB_URL = "http://120.79.91.39/"

    //正式环境
    const val FORMAL_URL = "https://www.uumoney.cn/"
    //正式环境
    const val FORMAL_WEB_URL = "https://www.uumoney.cn/"

    //正式服
    const val BASE_URL = "https://www.ztltw.com/"
    //正式服
    const val BASE_WEB_URL = "https://www.ztltw.com/"

////    测试环境
//    const val BASE_URL = "https://new.ztltw.com/"
////    测试环境
//    const val BASE_WEB_URL =BASE_URL
//    const val BASE_WEB_URL = "https://new.ztltw.com/"

    //登录
    const val USER_LOGIN = "rs/userAccount/loginForMobile"

    //检查手机号是否已经注册过
    const val CHECK_PHONE_IS_USED = "rs/userAccount/checkMobileIsExist"

    //首页获取banner数据
    const val HOME_BANNER = "rs/banner/queryBanners"
//    //
//    const val HOME_BANNER = "rs/banner/queryBanners"

    //理财列表
    const val FINANCE_MANAGER_LIST = "rs/financing/queryLessList"

    //登录
    const val LOGIN = "rs/userAccount/loginForMobile"

    //可用红包
    const val MY_WELFARE_LIST = "rs/redPackets/selectRateAndRedByUserId"

    //创建订单
    const val CREATE_ORDER = "rs/investOrder/createInvestOrder"

    //支付订单
    const val PAYMENT = "rs/payorder/payBlance"

    //注册
    const val USER_REGISTER = "rs/userAccount/registry"


    //首页理财列表
    const val HOME_FINANCE_MANAGER_LIST = "rs/financing/queryAppFinancingRank"

    //首页公告数据
    const val HOME_ANNOCEMENT_LIST = "rs/annocement/queryAnnocement"

    //获取验证码
    const val GET_VERIFY_CODE = "rs/verify/sendVerifyCode"

    //找回密码
    const val FIND_PASSWORD = "rs/userAccount/updatePassWordMobile"

    //查询标的详情
    const val PROJECT_DETAIL = "rs/financing/queryDetail"

    //获取当前用户是否投资过当前标的
    const val CHECK_INVESTED = "rs/investOrder/checkInvested"

    //查询标详情的投资记录列表
    const val PROJECT_INVEST_RECORD = "rs/investOrder/queryInvenstorderList"

    //查询用户信息
    const val QUERY_USER_INFO = "rs/userAccount/queryCurrentUserInfo"

    //查询用户银行信息
    const val QUERY_BIND_BANK_INFO = "rs/fundAccount/queryBankCard"

    //预充值
    const val PRE_PAYMENT = "rs/chargeAction/chargeMoney"

    //查询充值限制
    const val QUERY_WITH_DRAW_LIMIT = "rs/fundAccount/queryWithdrawLimit"

    //提现
    const val WITHDRAW = "rs/chargeAction/withdraw"

    //退出登录
    const val LOGOUT = "rs/userAccount/logout"

    //修改支付密码
    const val CHANGE_TRADING_PASSWORD = "rs/fundAccount/changeTradersPwMobile"

    //设置支付密码
    const val SET_TRADING_PASSWORD = "rs/fundAccount/addTradersPw"

    //资金明细数据
    const val FINANCIAL_DETAILS = "rs/fundAccount/queryMoneyRecord"

    //我的投资数据
    const val INVEST_RECORD = "rs/investOrder/queryUserInvenst"

    //回款日程
//    const val PAYMENT_SCHEDULE = "rs/investOrder/queryUserReturnedMoney"
    const val PAYMENT_SCHEDULE = "rs/interest/queryRefundList"

    //我的优惠(红包)
    const val RED_ENVELOPS = "rs/redPackets/selectByUserId"
    //帮助中心菜单
    const val HELP_CENTER_MENU = "rs/userHelpCenter/getHelpMenus"
    //帮助中心条目
    const val HELP_CENTER_ITEM = "rs/userHelpCenter/getHelpMenuRecords"
    //我的优惠(加息券)
    const val MY_COUPON = "rs/interestRate/selectByUserId"
    //加息卷和红包
    const val RED_PAG_AND_COUPON = "rs/fundAccount/myuyufaxFinancing"

    //我的优惠(获取红包以及加息券)
    const val RED_ENVELOPS_AND_COUPON = "rs/redPackets/selectRateAndRedByUserId"

    //保存地址
    const val UPDATA_ADDRESS = "rs/userAccount/updateAddress"

    //查询签到信息
    const val IS_SIGNED = "rs/userSign/registration"

    //签到
    const val USER_SIGN = "rs/userSign/userSign"

    //获取积分商品
    const val GET_INTEGRAL_COMMODITY = "rs/userPoints/queryShopList"

    //用户反馈
    const val ADVICE_FEEDBACK = "rs/feedBack/create"

    //检查更新
    const val APP_VERSION_INFO = "rs/appVsersion/getLatestVersion"

    //实名认证
    const val AUTHENTICATION = "rs/fundAccount/addAuthentication"

    //限额说明
    const val BANK_LIMIT = "rs/fuyouPay/getBankLimit"

    //获取校验码图片
    const val IDENTIFY_CODE_URL =BASE_URL+"veryfy"

    //获取详情中的限额
    const val GET_NOVICE_LIMIT = BASE_URL + "rs/investOrder/getNoviceLimit"

    //根据单号查询付息表
    const val QUERY_INVEST_LIST_BY_ID = BASE_URL + "rs/interest/queryInvestList"

    //获取splash广告图
    const val SPLASH_BANNER_URL = BASE_URL + "rs/banner/queryStartBanner"

    //------------------------------------- Html5 ---------------------------------------------------------

    //首页-->安全保障
    const val HOME_SECURITY = BASE_WEB_URL + "mobile-webapp/satefy.html "

    //首页-->底部安全保障
    const val HOME_INFORMATION_SHOW = BASE_WEB_URL + "aboutData-h5.html"

    //首页-->新手指引
    const val HOME_GREEN_HAND_GUIDE = BASE_WEB_URL + "mobile-webapp/guide.html"

    //项目详情-->项目介绍
    const val PROJECT_INTRODUCE = BASE_WEB_URL + "mobile-webapp/projectParticulars.html?key=%s"

    //项目详情-->资料图片
    const val PROJECT_PICTURE = BASE_WEB_URL + "mobile-webapp/projectDatum.html?key=%s"

    //项目详情-->安全保障
    const val PROJECT_SECURITY = BASE_WEB_URL + "mobile-webapp/projectControl.html?key=%s"

    //用户资金配置
    const val MINE_MONEY_CHART = BASE_WEB_URL + "mobile-webapp/userMoneryChart.html"

    //关于我们
    const val ABOUT_US = BASE_WEB_URL + "mobile-webapp/aboutUs.html "

    //互动-->积分商城
    const val H5_INTEGRATION_MALL = BASE_WEB_URL + "/mobile-webapp/integralShop.html"

    //互动-->购买记录
    const val H5_INTEGRATION_MALL_RECORD = BASE_WEB_URL + "/mobile-webapp/integralRecord.html"

    //互动-->我的邀请
    const val H5_MY_INVITE = BASE_WEB_URL + "/mobile-webapp/earnScore-h5.html "

    //企业贷贷款协议
    const val H5_LOAN_AGREEMENT = BASE_WEB_URL + "mobile-webapp/contract.html?key=%s"

    //车贷贷款协议
    const val H5_LOANC_AGREEMENT = BASE_WEB_URL + "mobile-webapp/contractC.html?key=%s"

    //注册协议
    const val H5_REGIST_AGREEMENT = BASE_WEB_URL + "mobile-webapp/agreement.html"

    //公告
    const val H5_NOTICE = BASE_WEB_URL + "mobile-webapp/noticeCon.html?id=%s"

    //借款合同
    const val H5_ = BASE_WEB_URL + "mobile-webapp/noticeCon.html?id=%s"

    //积分明细
    const val H5_INTEGRAL_DETAIL = BASE_WEB_URL + "mobile-webapp/integralDetail.html"

    //如何获取积分
    const val H5_INTEGRAL_EXPLAIN = BASE_WEB_URL + "mobile-webapp/integraExplain.html"

    //积分商品详情
    const val H5_PRODUCTS_EXCHANGE = BASE_WEB_URL + "mobile-webapp/productsExchange.html?id=%s&type=%s"
    const val H5_HELP_CENTER = BASE_WEB_URL + "mobile-webapp/help-centerh-h5.html"
    const val H5_MESSAGE_CENTER = BASE_WEB_URL + "mobile-webapp/aboutUU-h5.html"

    //------------------------------------- 请求参数 ------------------------------------------------------------

    const val PAGE_SIZE = 10
    const val STR_PAGE_SIZE = "pageSize"
    const val PAGE_LARGE_SIZE = 20
    const val START_PAGE = 0
    const val MOBILE_PHONE = "mobilePhone"
    const val PASSWORD = "password"
    const val STATES = "status"
    const val PLATFORM = "platform"
    const val LIMIT = "limit"
    const val STATRT = "start"
    const val SUBJECT_TYPE = "subjectType"
    const val VERIFY_CODE_TYPE = "verifyCodeType"
    const val VERIFY_CODE = "verifyCode"
    const val ORDER_ID = "orderId"
    const val INVEST_ORDER_ID = "investOrderId"
    const val ORDER_STATUS = "orderStatus"
    const val ORDER_STATUS_LIST = "orderStatusList"
    const val DEVICE_TYPE = "deviceType"
    const val DEVICE_ID = "deviceId"
    const val ID = "id"
    const val CHARGE_MONEY = "chargeMoney"
    const val PAY_CODE = "payCode"
    const val TOTAL_MONEY = "totalMoney"
    const val NEW_TRADERS_PW = "newTradersPw"
    const val TRADERS_PW = "tradersPw"
    const val FUND_TYPE = "fundType"
    const val KEY_QUERY_INVENST_TYPE = "queryInvenstType"
    const val QUERY_TYPE = "queryType"
    const val PACKETS_USER_ID = "packetsUserId"
    const val PROVINCE_ID = "province"
    const val CITY_ID = "city"
    const val COUNTY_ID = "county"
    const val STREET_ID = "street"
    const val CONTENT = "content"
    const val FEEDBACK_TYPE = "feedBackType"
    const val TRAN_PAW = "tranPaw"
    const val REDPACKETS_ID = "redPacketsId"
    const val INTEREST_RATE_ID = "interestRateId"
    const val IMEI = "imei"
    const val CHANNEL_ID = "channelId"
    const val RELATE_MOBILE_PHONE = "relatedMobilePhone"
    const val DEVICE_MODEL = "deviceModel"
    const val MOBILE_VERSION = "mobileVersion"
    const val OS = "os"
    const val REAL_NAME = "realName"
    const val CERTNO = "certNo"
    const val CODE = "code"
    const val IS_ASC = "isAsc"
    const val ANNOUNCEMENT_TYPE = "announcementType"
    const val INVESTER_UID = "investerUid"

    //------------------------------------- errorCode -----------------------------------------------------------
    /**
     * 未知错误
     */
    const val UNKNOW_ERROR = -1
    /**
     * 超时
     */
    const val TIME_OUT_ERROR = -2
    /**
     * 网络不可用
     */
    const val NET_CANT_USE_ERROR = -3

    /**
     * 在他端登录
     */
    const val NET_OTHER_LOGIN = -4

    /**
     * 服务器错误
     */
    const val NET_SERVER_ERROR = -5

    /**
     * 错误但不至于崩溃的错误
     */
    const val NET_SERVER_FAILURE_DONT_ERROR = -6

    /**
     * 服务器维护
     */
    const val NET_SYSTEM_MAINTAIN_ERROR = -7

    /**
     * 服务器繁忙
     */
    const val NET_SYSTEM_BUSY_ERROR = -8

    /**
     * 系统维护时带在header中的key
     */
    const val SYSTEM_ERROR_TAG = "x-server-deploy"
    /**
     * 系统维护时带在header中的标识
     */
    const val SYSTEM_MAINTAIN_TAG = "yes"
    /**
     * 系统繁忙时带在header中的标识
     */
    const val SYSTEM_BUSY_TAG = "no"
}