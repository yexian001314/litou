package com.shqj.mine.activity

import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.example.twitterlib.wigdet.dialog.DialogFactory
import com.shqj.mine.R
import com.shqj.mine.mvp.presenter.WithdrawPresenter
import com.shqj.mine.mvp.view.WithdrawView
import com.sleep.commonlib.base.BaseEvent
import com.sleep.commonlib.base.EventCode
import com.sleep.commonlib.util.EditTextHelper
import com.sleep.commonlib.util.StringUtil
import com.sleep.uulib.UUApplication
import com.sleep.uulib.bean.RechargeLimitBean
import com.sleep.uulib.bean.WithdrawLimitBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ConfigConstant
import com.sleep.uulib.mvp.model.CommonModule
import com.sleep.uulib.mvp.view.CommonView
import com.sleep.uulib.util.GlideHelper
import com.sleep.uulib.util.NumberFormatUtils
import com.sleep.uulib.util.NumberUtils
import com.sleep.uulib.uubase.UUBaseActivity
import kotlinx.android.synthetic.main.mine_activity_withdraw.*
import org.simple.eventbus.Subscriber

/**
 * 提现
 */
@Route(path = ArouterConstant.MINE_WITHDRAW)
class WithdrawActivity : UUBaseActivity(), WithdrawView, View.OnClickListener, CommonView {
    override fun loadData(data: RechargeLimitBean) {
        for (imageUrl in data.bankLimitModels) {
            try {
                if (UUApplication.user!=null&&UUApplication.user?.getBankInfo()?.bankName!!.contains(imageUrl.bank)) {
                    imageUrl.bankPhotoUrl
                    Glide.with(this)
                            .applyDefaultRequestOptions(GlideHelper.getOptions(GlideHelper.ImageType.CIRCLE))
                            .load(imageUrl.bankPhotoUrl)
                            .into(iv_bank_icon)

                }
            }catch (error: Exception){}
        }
    }

    private lateinit var mPresenter: WithdrawPresenter
    //提现免费次数
    private var mFreeWithdrawNum: Int = 0
    //本次提现金额
    private var mWithdrawAmount: Double = 0.00

    override fun getLayoutResourse(): Int {
        return R.layout.mine_activity_withdraw
    }

    override fun initView() {
        setTitle(getString(R.string.title_withdraw))

        //可提现额度
        tv_available_withdraw_amount.text = ("可用余额\n("
                + NumberFormatUtils.getFormatNumber(UUApplication.user?.getBalanceMoney()!!, 2) + ")元")

        //免费提现次数
        tv_free_withdraw_num.text = ("当前享提现特权" + UUApplication.user?.getFreeWithdrawNum() + "次")

        //全部提现
        tv_witdraw_all.setOnClickListener(this)

        //提现按钮
        tv_withdraw.setOnClickListener(this)
        if (UUApplication.user != null) {
            tv_bank_name.text = UUApplication.user?.getBankInfo()?.bankName + "(尾号${UUApplication.user?.getBankInfo()?.cardCode?.substring(
                    UUApplication.user?.getBankInfo()?.cardCode?.length!! - 4, UUApplication.user?.getBankInfo()?.cardCode?.length!! )})"

            tv_bank_limit.text = "当前免费提现次数为${UUApplication.user?.getFreeWithdrawNum()}次,超出此金额，收取每笔超出\n金额的0.5%手续费（最低2元）,单笔限49999.00元"
        }
        et_money_amount.addTextChangedListener(WithdrawTextWatcher())
    }

    override fun initData() {
        mPresenter = WithdrawPresenter(this, mStateManager)
        mPresenter.getRechargeLimitData()
    }

    override fun retryGetData() {
        //新版本取消免费额度，采用免费次数
//        mPresenter.refreshFreeLimit(this)
        CommonModule.refreshUserInfo(this)
    }

    override fun onResume() {
        super.onResume()
        //新版本取消免费额度，采用免费次数
//        mPresenter.refreshFreeLimit(this)
        CommonModule.refreshUserInfo(this)
        mFreeWithdrawNum = UUApplication.user?.getFreeWithdrawNum()!!
        et_money_amount.setText("")
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_witdraw_all -> {
                //全部提现按钮
                et_money_amount.setText(NumberFormatUtils.getNumberWithDigital(UUApplication.user?.getBalanceMoney()!!, 2))
                EditTextHelper.INSTANCE.selectLastPosition(et_money_amount)
            }
            R.id.tv_withdraw -> {
                //提现
                val strAmount = et_money_amount.text.toString().trim({ it <= ' ' })
                val amount = java.lang.Double.parseDouble(strAmount)

                if (amount <= 5 && mFreeWithdrawNum <= 0) {
                    showWarningDialog("提现金额低于5.00不可提现")
                    return
                }

                if (amount == 0.toDouble()) {
                    showWarningDialog("提现金额不能为0元")
                    return
                }

                mWithdrawAmount = strAmount.toDouble()
                mPresenter.withdraw(this, strAmount)
            }
        }
    }

    override fun getDataFinish() {

    }

    override fun refreshSuccess() {
        resetView()
    }

    override fun refreshFailure() {
        CommonModule.refreshUserInfo(this)
    }

    override fun refreshFreeLimit(data: WithdrawLimitBean) {
//        mFreeWithdrawNum = data.freeWithdrawLimit
//        tv_free_withdraw_limit.text = ("当前免费额度为"
//                + NumberFormatUtils.getFormatNumber(mFreeWithdrawNum, 2)
//                + "元，超出部分按1%收取提现手续费")
    }

    private fun resetView() {
        mFreeWithdrawNum = UUApplication.user?.getFreeWithdrawNum()!!
        tv_free_withdraw_limit.text = "当月剩余免费提现次数为${mFreeWithdrawNum}次"
        if (mFreeWithdrawNum <= 0) {
            et_money_amount.hint = "手续费为提现金额的0.03%，最低5元"
        } else {
            et_money_amount.hint = ""
        }
    }

    inner class WithdrawTextWatcher : TextWatcher {

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {

            if (s.toString().trim { it <= ' ' }.isNotEmpty()) {
                et_money_amount.setTextSize(COMPLEX_UNIT_SP,20f)
                tv_withdraw.setBackgroundResource(R.drawable.shape_accent_corner)
                tv_withdraw.isClickable = true

            } else {
                et_money_amount.setTextSize(COMPLEX_UNIT_SP,14f)
                tv_withdraw.setBackgroundResource(R.drawable.shape_gray_corner)
                tv_withdraw.isClickable = false
            }

            //去除小数点
            val temp = s.toString()
            val posDot = temp.indexOf(".")
            if (posDot > 0) {
                if (temp.length - posDot - 1 > 2) {
                    s?.delete(posDot + 3, posDot + 4)
                }
            } else if (posDot == 0) {
                s?.delete(0, 1)
            }

            //rebuild
            //  计算手续费并设置(暂时不计算免费次数)
            //没有免手续费次数
            val strAmount = et_money_amount.text.toString().trim({ it <= ' ' })
            if (!StringUtil.isEmpty(strAmount) && "." != strAmount) {
                //用户输入的金额
                val amount = java.lang.Double.parseDouble(strAmount)
                //用户可用余额
                val balanceMoney = UUApplication.user?.getBalanceMoney()!!
                if (amount > balanceMoney) {
                    //最多不能提现超过100万   解决有200w 输入100w 在多输入的时候会弹窗两次问题
                    if (ConfigConstant.MAX_WITHDRAW_LIMIT < UUApplication.user?.getBalanceMoney()!!) {
                        showWarningDialog("最多可提现金额为${ConfigConstant.MAX_WITHDRAW_LIMIT}")
                        et_money_amount.setText(NumberFormatUtils.getFormatNumber(ConfigConstant.MAX_WITHDRAW_LIMIT.toDouble(), 2))
                    } else {
                        showWarningDialog("最多可提现金额为${NumberFormatUtils.getFormatNumber(UUApplication.user?.getBalanceMoney()!!, 2)}")
                        et_money_amount.setText(NumberFormatUtils.getFormatNumber(UUApplication.user?.getBalanceMoney()!!, 2))
                    }
                    EditTextHelper.INSTANCE.selectLastPosition(et_money_amount)
                    return
                }
                if (amount > ConfigConstant.MAX_WITHDRAW_LIMIT) {
                    //最多不能提现超过100万
                    showWarningDialog("单笔提现金额不能大于100万元")
                    et_money_amount.setText(NumberFormatUtils.getNumberWithDigital(ConfigConstant.MAX_WITHDRAW_LIMIT.toDouble(), 2))
                    EditTextHelper.INSTANCE.selectLastPosition(et_money_amount)
                    return
                }
                //需要计算手续费的部分
                if (mFreeWithdrawNum <= 0) {
                    val mul = NumberUtils.mul(amount, ConfigConstant.FREE_LIMIT)
                    val formatNumber = NumberFormatUtils.getFormatNumber(mul, 2)
                    if (mul < 5 && mul != 0.toDouble()) {
                        tv_procedure_fee_num.text = "5.00元"
                    } else {
                        tv_procedure_fee_num.text = formatNumber + "元"
                    }
                } else {
                    tv_procedure_fee_num.text = "0.00元"
                }
            } else {
                tv_procedure_fee_num.text = "0.00元"
            }
        }
    }

    private fun showWarningDialog(descrip: String) {
        DialogFactory.instance.getCommonDialog("提示", descrip,
                "知道了", null, true, null).show(supportFragmentManager, "withdraw_warning")
    }

    @Subscriber
    fun onWithdrawSuccess(baseEvent: BaseEvent<Any>) {
        if (baseEvent.eventCode == EventCode.WITHDRAW_SUCCESS) {
            UUApplication.user?.setBalanceMoney(NumberUtils.sub(UUApplication.user?.getBalanceMoney(), mWithdrawAmount))
            //设置可用余额
            tv_available_withdraw_amount.text = ("可用余额\n("
                    + NumberFormatUtils.getFormatNumber(UUApplication.user?.getBalanceMoney()!!, 2) + ")元")
        }
    }
}
