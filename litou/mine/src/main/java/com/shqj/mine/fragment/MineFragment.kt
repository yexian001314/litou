package com.shqj.mine.fragment


import android.Manifest
import android.content.Intent
import android.net.Uri
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.twitterlib.wigdet.dialog.CommonFragmentDialog
import com.example.twitterlib.wigdet.dialog.DialogFactory
import com.shqj.mine.R
import com.shqj.mine.mvp.presenter.MinePresenter
import com.shqj.mine.mvp.view.IMineView
import com.sleep.commonlib.util.LibCommonUtil
import com.sleep.commonlib.util.LogUtil
import com.sleep.commonlib.util.SPUtil
import com.sleep.commonlib.util.ToastUtil
import com.sleep.uulib.UUApplication
import com.sleep.uulib.account.LoginActivity
import com.sleep.uulib.bean.QueryBankBean
import com.sleep.uulib.bean.QueryUserInfoBean
import com.sleep.uulib.bean.RedBagAndCouponBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.Constant
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.model.CommonModule
import com.sleep.uulib.mvp.view.QueryUserInfoView
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.util.NumberFormatUtils
import com.sleep.uulib.uubase.HtmlUrlActivity
import com.sleep.uulib.uubase.UUBaseFragment
import com.umeng.analytics.MobclickAgent
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.PermissionListener
import kotlinx.android.synthetic.main.fragment_mine.*


/**
 * A simple [Fragment] subclass.
 */
@Route(path = ArouterConstant.APP_MINE_FRAGMENT)
class MineFragment : UUBaseFragment(), View.OnClickListener, QueryUserInfoView, IMineView<RedBagAndCouponBean> {
    override fun loadRedBagAndConpon(data: RedBagAndCouponBean) {
        redBagAndCoupon = data.redPacketsCount + data.rateCount
        tv_coupons_acount.text = "(" + redBagAndCoupon + ")"
    }

    companion object {
        val REQUEST_CODE_SETTING = 300
    }

    private var redBagAndCoupon: Int = 0
    private var mIsShowTotalAssets = false
    private var mIsNeedRefreshUserInfo = true
    private val mPresenter: MinePresenter get() = MinePresenter(this, mStateManager)
    override fun shouldReceiveOtherLogin(): Boolean {
        return false
    }

    override fun getLayoutResourse(): Int {
        return R.layout.fragment_mine
    }

    override fun initView(view: View) {
        tv_recharge.setOnClickListener(this)
        tv_withdraw.setOnClickListener(this)
        iv_home_eyes.setOnClickListener(this)
        rl_financial_details.setOnClickListener(this)
        rl_my_invest.setOnClickListener(this)
        rl_date_of_payment.setOnClickListener(this)
        rl_my_welfare.setOnClickListener(this)
        rl_security_settings.setOnClickListener(this)
        ll_customer_service_center.setOnClickListener(this)
        ll_help_center.setOnClickListener(this)
        rl_feed_back.setOnClickListener(this)
        rl_avatar.setOnClickListener(this)
        iv_question.setOnClickListener(this)
        tv_login_in.setOnClickListener(this)
        ll_invest_friend.setOnClickListener(this)
        tv_coupons_acount.text = ""
    }

    override fun onFirstUserVisible() {
        CommonModule.queryUserInfo(this)
    }

    override fun onUserVisible() {
        if (mIsNeedRefreshUserInfo) {
            CommonModule.queryUserInfo(this)
            mIsNeedRefreshUserInfo = false
        }
    }

    override fun initData() {

    }

    override fun onResume() {
        //super.onResume就会调用OnUserVisible方法，所以状态变更需要放在super.onResume()之前
        mIsNeedRefreshUserInfo = true
        super.onResume()
        if (UUApplication.user == null) {
            ll_mine_contian.visibility = View.VISIBLE
            ll_acount_assest.visibility = View.GONE
            //可用余额未登录是隐藏登入是显示
            rl_mine_total_money.visibility = View.GONE
            tv_coupons_acount.text = ""
        } else {
            ll_acount_assest.visibility = View.VISIBLE
            ll_mine_contian.visibility = View.GONE
            rl_mine_total_money.visibility = View.VISIBLE
            mPresenter.requestRedBagAndCoupon()
        }
        mIsShowTotalAssets = SPUtil.getInstance().getBoolean(Constant.IS_SHOW_TOTAL_ASSETS, true)

        //资产总额
        checkIsShowAssets()
    }

    override fun retryGetData() {
        mStateManager.showLoading()
        CommonModule.queryUserInfo(this)
    }

    override fun queryUserInfoFailure(e: Throwable?, errorCode: Int) {
        NetCommonMethod.judgeError(errorCode, mStateManager)
    }

    override fun queryUserInfoSuccess(data: QueryUserInfoBean) {

    }

    override fun queryBankFailure(e: Throwable?, errorCode: Int) {
        NetCommonMethod.judgeError(errorCode, mStateManager)
    }

    override fun queryBankSuccess(data: QueryBankBean) {

        mStateManager.showContent()
        setValue()
        if (!data.isSuccess) {
            return
        }
        if (data.bankCardEOs == null || data.bankCardEOs.size < 1) {
            return
        }
        if (data.bankCardEOs[0] == null) {
            return
        }
        if (!TextUtils.isEmpty(data.bankCardEOs[0].bankName)) {
            if (UUApplication.user != null) {
                UUApplication.user?.setBankInfo(data.bankCardEOs[0])
            }
        }
    }

    override fun onClick(v: View?) {
        if (LibCommonUtil.isFastDoubleClick()) {
            return
        }
        when (v?.id) {
            R.id.tv_recharge -> {
                //充值
                MobclickAgent.onEvent(context, "wd_cz_btn")
                if (UUApplication.user != null) {
                    goRecharge()
                }
            }
            R.id.tv_withdraw -> {
                //提现
                if (UUApplication.user != null) {
                    MobclickAgent.onEvent(context, "wd_tx_btn")
                    goWithdraw()
                }}
            R.id.ll_invest_friend -> {
                //我的邀请
                MobclickAgent.onEvent(context, "hd_wdyq")
                HtmlUrlActivity.launch(NetConstant.H5_MY_INVITE, true)
            }
            R.id.tv_login_in -> {
                //去登陆
                if (UUApplication.user == null) {
                    LoginActivity.launch()
                }
            }
//            R.id.ll_total_assets -> {-
//                //跳转web，查看个人资产配置
//                MobclickAgent.onEvent(context, "wd_zjmx")
//                HtmlUrlActivity.launch(NetConstant.MINE_MONEY_CHART, true)
//            }
            R.id.rl_avatar -> {
                //去登陆
                if (UUApplication.user == null) {
                    LoginActivity.launch()
                    return
                }
                //个人资料
                ARouter.getInstance().build(ArouterConstant.MINE_PERSONAL_DATA).navigation()
            }
            R.id.iv_question -> {

//                跳转web，查看个人资产配置
                MobclickAgent.onEvent(context, "wd_zjmx")
                HtmlUrlActivity.launch(NetConstant.MINE_MONEY_CHART, true)
            }
            R.id.iv_home_eyes -> {
                //是否显示总资产
                mIsShowTotalAssets = !mIsShowTotalAssets
                SPUtil.getInstance().put(Constant.IS_SHOW_TOTAL_ASSETS, mIsShowTotalAssets)
                checkIsShowAssets()
            }
            R.id.rl_financial_details -> {
                //去登陆
                if (UUApplication.user == null) {
                    LoginActivity.launch()
                    return
                }
                //资金明细
                ARouter.getInstance().build(ArouterConstant.MINE_FINANCIAL_DETAIL).navigation()
            }
            R.id.rl_my_invest -> {
                //去登陆
                if (UUApplication.user == null) {
                    LoginActivity.launch()
                    return
                }
                //我的投资
                MobclickAgent.onEvent(context, "wd_wdtz")
                ARouter.getInstance().build(ArouterConstant.MINE_MY_INVEST).navigation()
            }
            R.id.ll_help_center -> {
                HtmlUrlActivity.launch(NetConstant.H5_HELP_CENTER, false)
            }
            R.id.ll_customer_service_center -> {
                //联系客服
                MobclickAgent.onEvent(context, "hd_jfzx_lxkf")
                DialogFactory.instance.getBottomDialog(R.layout.layout_bottom_dialog_contact_service, object : CommonFragmentDialog.ILogicSetter {
                    override fun setLogic(fragmentDialog: CommonFragmentDialog, rootView: View) {
                        //QQ客服
                        rootView.findViewById<TextView>(R.id.tv_contact_qq_server).setOnClickListener({
                            contackQQServer()
                            fragmentDialog.dismiss()
                        })

                        //电话客服
                        rootView.findViewById<View>(R.id.tv_call_phone).setOnClickListener({
                            fragmentDialog.dismiss()
                            DialogFactory.instance.getPhoneCallDialog(View.OnClickListener {
                                call()
                            }).show(fragmentManager, "contact_server_dialog")
                        })

                        //取消
                        rootView.findViewById<View>(R.id.tv_cancel).setOnClickListener({
                            fragmentDialog.dismiss()
                        })
                    }

                }).show(fragmentManager, "contact_service")
            }
            R.id.rl_date_of_payment -> {
                //去登陆
                if (UUApplication.user == null) {
                    LoginActivity.launch()
                    return
                }
                //回款日程
                MobclickAgent.onEvent(context, "wd_hkjh")
                ARouter.getInstance().build(ArouterConstant.MINE_PAYMENT_SCHEDULE).navigation()
            }
            R.id.rl_my_welfare -> {
                //去登陆
                if (UUApplication.user == null) {
                    LoginActivity.launch()
                    return
                }
                //我的优惠
                MobclickAgent.onEvent(context, "wd_wdjl")
                ARouter.getInstance().build(ArouterConstant.MINE_MY_WELFARE).navigation()
            }
            R.id.rl_security_settings -> {
                //去登陆
                if (UUApplication.user == null) {
                    LoginActivity.launch()
                    return
                }
                //安全设置
                MobclickAgent.onEvent(context, "wd_wd_aqsz")
                ARouter.getInstance().build(ArouterConstant.MINE_SECURITY_SETTING).navigation()
            }
            R.id.rl_feed_back -> {
                //意见反馈
                MobclickAgent.onEvent(context, "hd_feedback")
                ARouter.getInstance().build(ArouterConstant.INFORMATION_FEEDBACK).navigation()
            }
        }
    }
//第一章 回归
    /**
     16年夏，九游联盟的一则公告，打破了世界的宁静，尘封百年的世界开始了新的篇章。
     “虚拟意识头盔正式开售---- 2116-8-6”。公告内容很简单，简单到任谁一看都为之振奋。
     “张恒看着坐在客厅中，看着全息投影屏幕整个人都有些呆滞了，足足半个小时，他那略显苍老的手，有些不受控制的有些颤抖。”
     电子竞技从发展之初到如今已有百年时光，其中不断出现创新，改革。操作方式从遥感到键盘在到光学触控，人体动作感应，不断的变化着，
     但是到21世纪下半页，时间仿佛有轮回一般，本是欣荣了几十年的微型空间定位人体感应的操作方式，突然又被尘封了几十年的物体键盘所替代了。
     为什么呢，开始时玩家都有些疑惑，不久后就释然了起来。----差距。
     人体操作是很有意思，也有非常强的体验感。但是网络游戏的创立之初目的是什么？是让人感受到技术压制别人的快感。突破难关快感，达到前所未有的高度的快感。
     但是人体操作是有局限性的，人体的动作和各种细节的操作，完全无法跟上意识。世界级别的电子竞技玩家的操作和普通玩家产生差距的关键所在就是体力。
     体力的差距是决定高手和普通玩家的关键所在。
     21世纪初期，很多人都希望把电竞录入奥林匹克项目当中，但是因为并没有多少体育精神，所以很简单的被拒，
     但谁也没想到几十年后竟然诞生了奥林匹克电竞联赛，于是乎电竞失去了电竞的特色。
     电子竞技最大的特色是什么，是操作跟上意识且能快过意识，是的，那种激烈到依靠本能做出反应。那种一场游戏之后大脑有些缺氧的快感。
     于是乎，电子竞技回到了手指的年代，一晃又是数十年。
     多年前，张恒因为意外，受过手伤，本是刚进职业圈没几年，但是已经积累了偌大人气的他，顿时跌到了人生的低谷。
     一个手速已近连普通玩家都无法达到的职业玩家，如何能在职业圈中继续走下去。
        刚
    **/
    /**
     * 跳转QQ客服
     */
    private fun contackQQServer() {
        if (LibCommonUtil.isAvilible(context, "com.tencent.mobileqq") || LibCommonUtil.isAvilible(context, "com.tencent.tim")) {
            val urlQQ = "http://wpa.b.qq.com/cgi/wpa.php?ln=2&uin=4006688956"
            HtmlUrlActivity.launch(urlQQ)
        } else {
            ToastUtil.showToast("请先安装腾讯QQ或TIM")
        }
    }

    /**
     * 联系客服，打电话
     */
    private fun call() {
        AndPermission.with(this)
                .requestCode(200)
                .permission(Manifest.permission.CALL_PHONE)
                .callback(permissionListener)
                // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框；
                // 这样避免用户勾选不再提示，导致以后无法申请权限。
                // 你也可以不设置。
                .rationale({ requestCode, rationale ->
                    // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                    AndPermission.rationaleDialog(context, rationale).show()
                })
                .start()
    }

    private val permissionListener = object : PermissionListener {
        override fun onSucceed(requestCode: Int, grantPermissions: List<String>) {

            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getString(R.string.CALL_PHONE)))
            try {
                startActivity(intent)
            } catch (e: SecurityException) {
                LogUtil.e(e.toString())
            }
        }

        override fun onFailed(requestCode: Int, deniedPermissions: List<String>) {

            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(context, deniedPermissions)) {
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(activity, REQUEST_CODE_SETTING).show()
            }
        }
    }

    /**
     * 跳转充值界面
     */
    private fun goRecharge() {
        //跳转实名认证
        if (!UUApplication.user?.isIsIdentityUserInfo()!!) {
            ARouter.getInstance().build(ArouterConstant.UUBASE_IDENTIFICATION).navigation()
            return
        }
        ARouter.getInstance().build(ArouterConstant.MINE_RECHARGE).navigation()
    }

    /**
     * 跳转提现界面
     */
    private fun goWithdraw() {
        if (UUApplication.user == null) {
            ToastUtil.showToast(R.string.please_login_first)
            return
        }

        //跳转实名认证页面
        if (!UUApplication.user?.isIsIdentityUserInfo()!!) {
            ARouter.getInstance().build(ArouterConstant.UUBASE_IDENTIFICATION).navigation()
            return
        }

        //跳转绑卡页面
        if (!UUApplication.user?.isIsBandCard()!!) {
            ARouter.getInstance().build(ArouterConstant.MINE_BIND_BANK_CARD).navigation()
            return
        }

        //跳转支付密码设置页面
        if (!UUApplication.user?.isIsTradersPwBinded()!!) {
            ARouter.getInstance().build(ArouterConstant.MINE_SET_TRADING_PWD).navigation()
            return
        }
        ARouter.getInstance().build(ArouterConstant.MINE_WITHDRAW).navigation()
    }

    /**
     * 设置是否显示资产总额
     */
    private fun checkIsShowAssets() {
        LogUtil.e("yexm+mIsShowTotalAssets", mIsShowTotalAssets.toString())
        if (mIsShowTotalAssets) {
            iv_home_eyes.setImageResource(R.mipmap.icon_eyes_open)
            if (UUApplication.user != null) {
                tv_total_assets.text = NumberFormatUtils.formatNumberWithComma(NumberFormatUtils.getNumberWithDigital(UUApplication.user?.getTotalMoney()!!, 2))
                tv_accumulated_earning_number.text = NumberFormatUtils.formatNumberWithComma(NumberFormatUtils.getNumberWithDigital(UUApplication.user?.getHasIncome()!!, 2))
                tv_online_investment.text = NumberFormatUtils.formatNumberWithComma(NumberFormatUtils.getNumberWithDigital(UUApplication.user?.getInvestingAmount()!! + UUApplication.user?.getFreezingAmount()!!, 2))
                tv_value_available_balance.text = NumberFormatUtils.formatNumberWithComma(NumberFormatUtils.getNumberWithDigital(UUApplication.user?.getBalanceMoney()!!, 2))
            }
        } else {
            iv_home_eyes.setImageResource(R.mipmap.icon_eyes_close)
            tv_total_assets.text = "*****"
            tv_online_investment.text = "*****"
            tv_accumulated_earning_number.text = "*****"
            tv_value_available_balance.text = "*****"
        }
    }

    /**
     * 设置网络请求回来的数据
     */
    private fun setValue() {

        if (UUApplication.user == null) {
            return
        }
        if (mIsShowTotalAssets) {
            //累计收益
            tv_accumulated_earning_number.text = NumberFormatUtils.formatNumberWithComma(NumberFormatUtils.getNumberWithDigital(UUApplication.user?.getHasIncome()!!, 2))
            tv_total_assets.text = NumberFormatUtils.formatNumberWithComma(NumberFormatUtils.getNumberWithDigital(UUApplication.user?.getTotalMoney()!!, 2))
            tv_online_investment.text = NumberFormatUtils.formatNumberWithComma(NumberFormatUtils.getNumberWithDigital(UUApplication.user?.getInvestingAmount()!! + UUApplication.user?.getFreezingAmount()!!, 2))
            //可用余额
            tv_value_available_balance.text = NumberFormatUtils.formatNumberWithComma(NumberFormatUtils.getNumberWithDigital(UUApplication.user?.getBalanceMoney()!!, 2))
        }
    }

}
