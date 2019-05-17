package com.shqj.arrange.activity

import android.content.Intent
import android.graphics.Rect
import android.support.design.widget.BottomSheetBehavior
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.twitterlib.wigdet.dialog.DialogFactory
import com.sleep.commonlib.util.SpannableStringUtils
import com.sleep.commonlib.util.ToastUtil
import com.sleep.uulib.UUApplication
import com.sleep.uulib.bean.ChooseWelfareBean
import com.sleep.uulib.bean.FinancingOrderBean
import com.sleep.uulib.bean.InvestResultBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ArouterParamConstant
import com.sleep.uulib.util.NumberFormatUtils
import com.sleep.uulib.util.NumberUtils
import com.sleep.uulib.uubase.UUBaseActivity
import com.sleep.uulib.widget.keyboard.SelectPopupWindow
import com.umeng.analytics.MobclickAgent
import com.shqj.arrange.R
import com.shqj.arrange.mvp.prestener.InvestPresenter
import com.shqj.arrange.mvp.view.InvestView
import kotlinx.android.synthetic.main.arrange_activity_invest.*

/**
 * 需要参数
 *  PROJECT_DETAILS: 项目详情资料
 *  SUBJECT_NAME: 项目名称
 */
@Route(path = ArouterConstant.ARRANGE_INVEST)
class InvestActivity : UUBaseActivity(), View.OnClickListener, SelectPopupWindow.OnPopWindowClickListener, InvestView, View.OnFocusChangeListener, View.OnTouchListener {

    companion object {

        /**
         * 选择红包的请求吗
         */
        private val REQUEST_CODE_CHOOSE_RED_PACKET = 200

        /**
         * 选择加息券的请求吗
         */
        private val REQUEST_CODE_CHOOSE_TICKET = 201

        /**
         * 跳转投资页，用户点击重新投资需要回到 [ProjectDetailActivity],根据此状态码来判断是否需要关闭当前界面
         */
        private val REQUEST_CODE_LAUNCH_INVEST_RESULT = 202
    }

    /**
     * 用户选择的红包
     */
    private var mChooseRedPacket: ChooseWelfareBean.DatasBean? = null

    /**
     * 用户选择的加息券
     */
    private var mChooseTicket: ChooseWelfareBean.DatasBean? = null

    /**
     * 根据输入金额是否为空判断能否选择加息券或者红包
     */
    private var canChooseWelfare = false
    private var mSubjectName: String = ""
    private var mProjectDetail: FinancingOrderBean? = null

    private var behavior: BottomSheetBehavior<View>? = null

    private lateinit var mPresenter: InvestPresenter

    override fun getLayoutResourse(): Int {
        return R.layout.arrange_activity_invest
    }

    override fun initView() {
        mProjectDetail = intent.getParcelableExtra(ArouterParamConstant.PROJECT_DETAILS)
        mSubjectName = intent.getStringExtra(ArouterParamConstant.SUBJECT_NAME)
        if (mProjectDetail == null || mSubjectName.isEmpty()) {
            ToastUtil.showErrorToast("InvestActivity params is error")
            finish()
        }

        //可用余额
        if (UUApplication.user != null) {
            val strBalance = NumberFormatUtils.getFormatNumber(UUApplication.user?.getBalanceMoney()!!, 2)
            tv_value_available_amount.text = "可用余额（元）：$strBalance"
        }

        //设置预期年化收益率
        var strExpectRate = StringBuilder(NumberFormatUtils.getFormatNumber(NumberUtils.mul(mProjectDetail?.baseRate, 100.00), 1))
        when {
            mProjectDetail?.subjectType == 1 -> {
                //普通标 U系列加息放在活动加息里面
                if (mProjectDetail?.activityRate != 0.0) {
                    strExpectRate.append("+").append(NumberFormatUtils.getFormatNumber(NumberUtils.mul(mProjectDetail?.activityRate, 100.00), 1))
                }
            }
            mProjectDetail?.subjectType == 2 -> {
                //活动标
                if (mProjectDetail?.activityRate != 0.0) {
                    strExpectRate.append("+").append(NumberFormatUtils.getFormatNumber(NumberUtils.mul(mProjectDetail?.activityRate, 100.00), 1))
                }
            }
            mProjectDetail?.subjectType == 3 -> {
                //新手加息
                if (mProjectDetail?.noviceRate != 0.0) {
                    strExpectRate.append("+").append(NumberFormatUtils.getFormatNumber(NumberUtils.mul(mProjectDetail?.noviceRate, 100.00), 1))
                }
            }
        }

        tv_annualized_return.text = "预期年化收益（%）：$strExpectRate"

        //设置项目名称
        setTitle(mSubjectName)
        //设置项目期限
        tv_project_deadline.text = "项目期限（天）：${mProjectDetail?.investPeriod.toString()}"
        //设置可投金额
        val remainCanUse = NumberFormatUtils.getNumberWithDigital(NumberUtils.sub(mProjectDetail?.totalMoney, mProjectDetail?.currentMoney), 2)
        tv_value_can_cast_the_amount.text = "剩余可投资金额（元）：$remainCanUse"
        //预期收益
        tv_value_expected_return.text = SpannableStringUtils.getBuilder("预期收益（元）：")
                .append("0.00")
                .setForegroundColor(resources.getColor(R.color.colorAccent))
                .create()
        //充值按钮
        tv_recharge.setOnClickListener(this)
        //选择红包
        ll_use_welfare.setOnClickListener(this)
        //选择优惠券
        ll_use_ticket.setOnClickListener(this)
        //弹出支付密码
        tv_confirm_payment.setOnClickListener(this)
        img_close.setOnClickListener(this)
        bt_sure_invest.setOnClickListener(this)
        tv_use_all_balance.setOnClickListener(this)
        et_amount_of_investment.addTextChangedListener(InputAmountTextWatcher())
        et_amount_of_investment.onFocusChangeListener = this

        behavior = BottomSheetBehavior.from(bottom_sheet)
        behavior?.state = BottomSheetBehavior.STATE_HIDDEN
        invest_root_view.setOnTouchListener(this)
    }

    override fun initData() {
        mPresenter = InvestPresenter(this, mStateManager)
    }

    override fun retryGetData() {
        mStateManager.showContent()
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (v?.id != R.id.bottom_sheet && behavior?.state == BottomSheetBehavior.STATE_EXPANDED) {
            behavior?.state = BottomSheetBehavior.STATE_HIDDEN
            return true
        }
        return super.onTouchEvent(event)
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        when (v?.id) {
            R.id.et_amount_of_investment -> {
                if (hasFocus) {
                    MobclickAgent.onEvent(this, "sy_tz_tzje")
                }
                if (hasFocus && behavior?.state == BottomSheetBehavior.STATE_EXPANDED) {
                    behavior?.state = BottomSheetBehavior.STATE_HIDDEN
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_sure_invest -> {
                MobclickAgent.onEvent(this, "sy_tz_ljtz_btn")
                //第一个确认投资按钮,会生成确认订单
                confirmOrder()
                //解决behavior问题
                window.decorView.findViewById<View>(android.R.id.content).requestFocus()
            }
            R.id.tv_recharge -> {
                //跳转充值
                MobclickAgent.onEvent(this, "sy_tz_cz_btn")
                if (UUApplication.user != null) {
                    if (UUApplication.user?.isIsIdentityVerified()!!) {
                        ARouter.getInstance().build(ArouterConstant.MINE_RECHARGE).navigation()
                    } else {
                        ARouter.getInstance().build(ArouterConstant.UUBASE_IDENTIFICATION).navigation()
                    }
                } else {
                    ToastUtil.showToast("请先登录")
                }
            }
            R.id.ll_use_welfare -> {
                //解决behavior问题
                if (behavior?.state == BottomSheetBehavior.STATE_EXPANDED) {
                    behavior?.state = BottomSheetBehavior.STATE_HIDDEN
                }
                //跳转选择福利
                if (canChooseWelfare) {
                    MobclickAgent.onEvent(this, "sy_tz_wdhb")
                    ARouter.getInstance().build(ArouterConstant.MINE_CHOOSE_WELFARE)
                            .withString(ArouterParamConstant.ORDER_ID, mProjectDetail?.orderId)
                            .withInt(ArouterParamConstant.AMOUNT, et_amount_of_investment.text.toString().trim().toInt())
                            .withInt(ArouterParamConstant.INVEST_PERIOD, mProjectDetail?.investPeriod!!)
                            .withInt(ArouterParamConstant.SUBJECT_TYPE, mProjectDetail?.subjectType!!)
                            .navigation(this, REQUEST_CODE_CHOOSE_RED_PACKET)
                } else {
                    ToastUtil.showToast(getString(R.string.please_input_your_invest_amount))
                }
            }
            R.id.ll_use_ticket -> {
                //跳转选择优惠券
                if (behavior?.state == BottomSheetBehavior.STATE_EXPANDED) {
                    behavior?.state = BottomSheetBehavior.STATE_HIDDEN
                }
                if (canChooseWelfare) {
                    MobclickAgent.onEvent(this, "sy_tz_wdhb")
                    ARouter.getInstance().build(ArouterConstant.MINE_CHOOSE_TICKET)
                            .withString(ArouterParamConstant.ORDER_ID, mProjectDetail?.orderId)
                            .withInt(ArouterParamConstant.AMOUNT, et_amount_of_investment.text.toString().trim().toInt())
                            .withInt(ArouterParamConstant.INVEST_PERIOD, mProjectDetail?.investPeriod!!)
                            .withInt(ArouterParamConstant.SUBJECT_TYPE, mProjectDetail?.subjectType!!)
                            .navigation(this, REQUEST_CODE_CHOOSE_TICKET)
                } else {
                    ToastUtil.showToast(getString(R.string.please_input_your_invest_amount))
                }
            }
            R.id.tv_confirm_payment -> {
                MobclickAgent.onEvent(this, "tz_ljtz_qrzf")
                //弹出输入支付密码页面
                //关闭确认弹出窗口
                if (behavior?.state == BottomSheetBehavior.STATE_EXPANDED) {
                    behavior?.state = BottomSheetBehavior.STATE_HIDDEN
                }
                showPayment()
            }
            R.id.img_close -> {
                //关闭确认弹出窗口
                if (behavior?.state == BottomSheetBehavior.STATE_EXPANDED) {
                    behavior?.state = BottomSheetBehavior.STATE_HIDDEN
                }
            }
            R.id.tv_use_all_balance -> {
                //余额全投
                MobclickAgent.onEvent(this, "sy_tz_qt_btn")
                val currentBalanceMoney = NumberFormatUtils.getFormatNumberWithNoDigital(UUApplication.user?.getBalanceMoney()!!).toInt()
                val canuseMoney = currentBalanceMoney - currentBalanceMoney % 100
                if (canuseMoney >= 0) {
                    val projectCanInvestMoney = NumberUtils.sub(mProjectDetail?.totalMoney, mProjectDetail?.currentMoney)
                    if (canuseMoney >= projectCanInvestMoney) {
                        et_amount_of_investment.setText(projectCanInvestMoney.toInt().toString())
                    } else {
                        et_amount_of_investment.setText(canuseMoney.toString())
                    }
                } else {
                    et_amount_of_investment.setText("0")
                }
                et_amount_of_investment.setSelection(et_amount_of_investment.text.length)
            }
        }
    }

    override fun onPopWindowClickListener(psw: String?, complete: Boolean) {
        if (complete) {
            invest(psw)
        }
    }

    override fun investSuccess(data: InvestResultBean) {
        hideDialog()
        MobclickAgent.onEvent(this, "tz_tbjg_tbcg")
        //扣除用户的可用余额
        UUApplication.user?.setBalanceMoney(NumberUtils.sub(UUApplication.user?.getBalanceMoney(), et_amount_of_investment.text.toString().toDouble()))
        mProjectDetail?.currentMoney = NumberFormatUtils.add(mProjectDetail?.currentMoney, et_amount_of_investment.text.toString().toDouble())
        ARouter.getInstance().build(ArouterConstant.ARRANGE_INVEST_RESULT)
                .withBoolean(ArouterParamConstant.IS_SUCCESS, true)
                .withString(ArouterParamConstant.BID_NAME, mProjectDetail?.subjectName)
                .withString(ArouterParamConstant.PRICE, et_amount_of_investment.text.toString())
                .navigation()
        //投资成功，清空红包和加息券并重新设置数据
        mChooseRedPacket = null
        mChooseTicket = null
        et_amount_of_investment.setText("")
    }

    override fun investFailure() {
        hideDialog()
        MobclickAgent.onEvent(this, "tz_tbjg_tbsb")
        ARouter.getInstance().build(ArouterConstant.ARRANGE_INVEST_RESULT)
                .withBoolean(ArouterParamConstant.IS_SUCCESS, false)
                .withString(ArouterParamConstant.BID_NAME, null)
                .withString(ArouterParamConstant.PRICE, null)
                .navigation(this, REQUEST_CODE_LAUNCH_INVEST_RESULT)
    }

    private fun invest(psw: String?) {
        showDialog()
        mPresenter.invest(mProjectDetail?.orderId, et_amount_of_investment.text.toString(), psw, mChooseRedPacket?.id, mChooseTicket?.id)
    }

    /**
     * 确认订单
     */
    private fun confirmOrder() {


        //购买金额
        val strPurchaseAmount = et_amount_of_investment.text.toString().trim({ it <= ' ' })


        if (!UUApplication.user?.isIsIdentityUserInfo()!!) {
            ARouter.getInstance().build(ArouterConstant.UUBASE_IDENTIFICATION).navigation()
            return
        }

        if (!UUApplication.user?.isIsTradersPwBinded()!!) {
            ARouter.getInstance().build(ArouterConstant.MINE_SET_TRADING_PWD).navigation()
            return
        }

        if (TextUtils.isEmpty(strPurchaseAmount)) {
            ToastUtil.showToast(R.string.please_input_your_invest_amount)
            return
        }

        if (java.lang.Double.parseDouble(strPurchaseAmount) > NumberUtils.sub(mProjectDetail?.totalMoney, mProjectDetail?.currentMoney)) {
            ToastUtil.showToast("最大可投金额为${NumberFormatUtils.getFormatNumberWithNoDigital(NumberUtils.sub(mProjectDetail?.totalMoney, mProjectDetail?.currentMoney))}")
            return
        }

        if (java.lang.Double.parseDouble(strPurchaseAmount) > UUApplication.user?.getBalanceMoney()!!) {
            DialogFactory.instance.getCommonDialog("提示", "余额不足，请先充值", "去充值", "取消", true,
                    View.OnClickListener {
                        ARouter.getInstance().build(ArouterConstant.MINE_RECHARGE).navigation()
                    }, null).show(supportFragmentManager, "invest_recharge")
            return
        }

        if (strPurchaseAmount.toInt() % 100 != 0) {
            ToastUtil.showToast("投资金额为100的整数倍")
            return
        }

        behavior?.state = BottomSheetBehavior.STATE_EXPANDED
        //购买金额
        tv_value_purchase_amount.text = NumberFormatUtils.getNumberWithDigital(strPurchaseAmount.toDouble(), 2) + "元"
        //抵扣金额
        if (mChooseRedPacket != null) {
            tv_value_discount.text = NumberFormatUtils.getNumberWithDigital(mChooseRedPacket?.packetsAmount!!, 2) + "元"
        } else {
            tv_value_discount.text = "0.00元"
        }
        //余额需支付
        if (mChooseRedPacket != null) {
//            tv_value_balance_payable.text = NumberFormatUtils.getNumberWithDigital(NumberUtils.sub(strPurchaseAmount.toDouble(), mChooseRedPacket?.packetsAmount!!), 2) + "元"
            tv_value_balance_payable.text = NumberFormatUtils.getNumberWithDigital(strPurchaseAmount.toDouble(), 2) + "元"
        } else {
            tv_value_balance_payable.text = NumberFormatUtils.getNumberWithDigital(strPurchaseAmount.toDouble(), 2) + "元"
        }
    }

    override fun onResume() {
        super.onResume()
        if (UUApplication.user != null) {
            val strBalance = NumberFormatUtils.getFormatNumber(UUApplication.user?.getBalanceMoney()!!, 2)
            tv_value_available_amount.text = "可用余额（元）：$strBalance"
        }
        //设置可投金额
        val remainCanUse = NumberFormatUtils.getNumberWithDigital(NumberUtils.sub(mProjectDetail?.totalMoney, mProjectDetail?.currentMoney), 2)
        tv_value_can_cast_the_amount.text = "剩余可投资金额（元）：$remainCanUse"
    }

    /**
     * 显示密码框
     */
    private fun showPayment() {
        val selectPopupWindow = SelectPopupWindow(this, this)
        val rect = Rect()
        window.decorView.getWindowVisibleDisplayFrame(rect)
        val winHeight = window.decorView.height
        selectPopupWindow.showAtLocation(window.decorView, Gravity.BOTTOM, 0, winHeight - rect.bottom)
        selectPopupWindow.setForgetPsdClickListener {
            ARouter.getInstance().build(ArouterConstant.MINE_MODIFY_TRADING_PWD).navigation()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //选择红包的返回结果
        if (requestCode == REQUEST_CODE_CHOOSE_RED_PACKET) {
            if (resultCode == RESULT_OK) {
                //获取红包
                mChooseRedPacket = data?.getParcelableExtra(ArouterParamConstant.RED_PACKET)
                if (mChooseRedPacket != null) {
                    //展示选择的福利
                    tv_welfare_statue.text = "投资红包" + NumberFormatUtils.getFormatNumberWithNoDigital(mChooseRedPacket?.packetsAmount!!.toDouble()) + "元"
                }
            } else if (resultCode == RESULT_CANCELED) {
                mChooseRedPacket = null
                tv_welfare_statue.text = "选择红包"
            }
        }

        //选择加息券的返回结果
        if (requestCode == REQUEST_CODE_CHOOSE_TICKET) {
            if (resultCode == RESULT_OK) {
                //获取加息券
                mChooseTicket = data?.getParcelableExtra(ArouterParamConstant.TICKET)
                if (mChooseTicket != null) {
                    //展示选择的福利
                    tv_ticket_statue.text = "${mChooseTicket?.description}${NumberFormatUtils.getNumberWithDigital(NumberUtils.mul(mChooseTicket?.packetsAmount, 100.toDouble()), 1)}%"
                }
            } else if (resultCode == RESULT_CANCELED) {
                mChooseTicket = null
                tv_ticket_statue.text = "选择加息券"
            }
            resetExpectEarnings()
        }

        //投资失败，用户点击重新投资，需要关闭当前界面，回退到详情页
        if (requestCode == REQUEST_CODE_LAUNCH_INVEST_RESULT) {
            if (resultCode == RESULT_CANCELED) {
                finish()
            }
        }
    }

    private inner class InputAmountTextWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            canChooseWelfare = s.toString().trim { it <= ' ' }.isNotEmpty()
            mChooseRedPacket = null
            tv_welfare_statue.text = "选择红包"
            resetExpectEarnings()
        }
    }

    /**
     * 重新设置预期收益
     */
    private fun resetExpectEarnings() {
        val investEarnings = if (canChooseWelfare) {
            val bidInterest = calculateInterest(et_amount_of_investment.text.toString())
            NumberFormatUtils.getNumberWithDigital(bidInterest, 2)
        } else {
            "0.00"
        }
        tv_value_expected_return.text = SpannableStringUtils.getBuilder("预期收益（元）：")
                .append(investEarnings)
                .setForegroundColor(resources.getColor(R.color.colorAccent))
                .create()
    }

    /**
     * 根据选择的优惠券和红包计算年化收益
     */
    private fun calculateInterest(amount: String): Double {
        if (amount.isEmpty()) {
            return 0.toDouble()
        }
        //投资收益 = 投资金额*年利率/365 * 借款周期      (收益的公式)
        var currentLendRate = mProjectDetail?.lendRate!!
        //有加息券，需要加上加息券的利息
        if (mChooseTicket != null) {
            currentLendRate = NumberUtils.add(currentLendRate, mChooseTicket?.packetsAmount)
        }
        //投资金额 * 年利率
        val yearInvestNum = NumberUtils.mul(amount.trim { it <= ' ' }.toDouble(), currentLendRate)
        //每天的收益
        val dayInvestNum = NumberUtils.div(yearInvestNum, 365.toDouble())
        //后台算法此处去四位向上取整一次
        val finalDayInvestNum = NumberUtils.getRoundUpNum(dayInvestNum, 4)
        //标的能获得的收益
        val bidInterest = NumberUtils.mulFourRoundUp(finalDayInvestNum, mProjectDetail?.actPeriod?.toDouble()!!)
        return bidInterest
    }

    override fun onBackPressed() {
        if (behavior != null && behavior?.state == BottomSheetBehavior.STATE_EXPANDED) {
            behavior?.setState(BottomSheetBehavior.STATE_HIDDEN)
        } else {
            super.onBackPressed()
        }
    }
}
