package com.shqj.mine.activity

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.shqj.mine.R
import com.shqj.mine.mvp.presenter.RechargePresenter
import com.shqj.mine.mvp.view.RechargeView
import com.sleep.commonlib.base.BaseEvent
import com.sleep.commonlib.base.EventCode
import com.sleep.commonlib.util.StringUtil
import com.sleep.commonlib.util.ToastUtil
import com.sleep.uulib.UUApplication
import com.sleep.uulib.bean.QueryBankBean
import com.sleep.uulib.bean.QueryUserInfoBean
import com.sleep.uulib.bean.RealRechargeResultBean
import com.sleep.uulib.bean.RechargeLimitBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ConfigConstant
import com.sleep.uulib.mvp.model.CommonModule
import com.sleep.uulib.mvp.view.QueryUserInfoView
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.util.GlideHelper
import com.sleep.uulib.util.NumberUtils
import com.sleep.uulib.uubase.HtmlContentActivity
import com.sleep.uulib.uubase.UUBaseActivity
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.mine_activity_recharge.*
import org.simple.eventbus.Subscriber

/**
 * 充值
 */
@Route(path = ArouterConstant.MINE_RECHARGE)
class RechargeActivity : UUBaseActivity(), RechargeView, QueryUserInfoView {
    override fun loadData(data: RechargeLimitBean) {
        for (imageUrl in data.bankLimitModels) {
            if (UUApplication.user!=null&&UUApplication.user?.getBankInfo()?.bankName!!.contains(imageUrl.bank)) {
                Glide.with(this)
                        .applyDefaultRequestOptions(GlideHelper.getOptions(GlideHelper.ImageType.CIRCLE))
                        .load(imageUrl.bankPhotoUrl)
                        .into(iv_bank_icon)
            }
        }
    }

    private val mPresenter: RechargePresenter get() = RechargePresenter(this, mStateManager)

    /**
     * 当前成功提交充值的金额，如果充值成功，点击继续充值，回到本界面需要该数值计算余额
     */
    private var mRechargeAmount: Long = 0

    override fun getLayoutResourse(): Int {
        return R.layout.mine_activity_recharge
    }

    override fun initView() {
        setTitle(getString(R.string.title_recharge))
        tv_sure.setOnClickListener({
            MobclickAgent.onEvent(this, "wd_cz_xyb_btn")
            goRecharge()
        })
        et_money_amount.addTextChangedListener(RechargeTextWatcher())
        tv_bank_name.text = UUApplication.user?.getBankInfo()?.bankName
        //设置限额提醒
        if (UUApplication.user?.getBankInfo() == null || UUApplication.user?.getBankInfo()?.singleLimit == 0) {
            showDialog()
            CommonModule.queryUserInfo(this)
            return
        }
        tv_limt.setOnClickListener(View.OnClickListener {
            ARouter.getInstance().build(ArouterConstant.MINE_RECHARGE_LIMIT_INTRODUCE).navigation()
        })
        if (UUApplication.user != null) {
            tv_bank_name.text = UUApplication.user?.getBankInfo()?.bankName + "(尾号${UUApplication.user?.getBankInfo()?.cardCode?.substring(
                    UUApplication.user?.getBankInfo()?.cardCode?.length!!-4, UUApplication.user?.getBankInfo()?.cardCode?.length!! )})"
            val singleLimit = UUApplication.user?.getBankInfo()?.singleLimit
            val singleDayLimit = UUApplication.user?.getBankInfo()?.singleDayLimit
            val countLimit = UUApplication.user?.getBankInfo()?.countLimit
            if (UUApplication.user?.getBankInfo()?.countLimit != 0) {
                tv_bank_limit.text = "该行单笔限额$singleLimit,单日限额$singleDayLimit\n单日仅限${countLimit}笔成功交易"
            } else {
                tv_bank_limit.text = "该行单笔限额$singleLimit,单日限额$singleDayLimit"
            }
        }
    }


    override fun initData() {
        mPresenter.getRechargeLimitData()
    }

    override fun retryGetData() {
        mStateManager.showContent()
    }

    override fun onResume() {
        super.onResume()
//        if (UUApplication.user != null) {
//            tv_can_use_balance.text = ("可用余额\n(" + NumberFormatUtils.getFormatNumber(UUApplication.user?.getBalanceMoney()!!, 2) + ")元")
//        }
        et_money_amount.setText("")
    }

    override fun getDataFinish() {
        hideDialog()
    }

    override fun getRealRechargeResultSuccess(data: RealRechargeResultBean) {
        hideDialog()
        HtmlContentActivity.launch(data.serviceUrl)
    }

    override fun queryUserInfoFailure(e: Throwable?, errorCode: Int) {
        hideDialog()
        NetCommonMethod.judgeError(errorCode, mStateManager)
    }

    override fun queryUserInfoSuccess(data: QueryUserInfoBean) {
    }

    override fun queryBankFailure(e: Throwable?, errorCode: Int) {
        hideDialog()
        NetCommonMethod.judgeError(errorCode, mStateManager)
    }

    override fun queryBankSuccess(data: QueryBankBean) {
        hideDialog()
    }

    /**
     * 开始充值，充值分两部，先请求服务器，获得payCode，
     * 然后根据payCode，请求服务器获取富有表单，然后将表单用webView提交并访问富有充值界面
     */
    private fun goRecharge() {
        val strAmount = et_money_amount.text.toString().trim({ it <= ' ' })


        if (TextUtils.isEmpty(strAmount)) {
            ToastUtil.showToast(R.string.please_input_recharge_amount)
            return
        }

        if (java.lang.Long.parseLong(strAmount) < 100) {
            ToastUtil.showToast(R.string.recharge_amount_least_one_hundred)
            return
        }

        mRechargeAmount = java.lang.Long.parseLong(strAmount)
        showDialog()
        mPresenter.recharge(strAmount)
    }


    @Subscriber
    fun onRechargeSuccess(baseEvent: BaseEvent<Any>) {
        if (baseEvent.eventCode == EventCode.CONTINUE_RECHARGE) {
            UUApplication.user?.setBalanceMoney(NumberUtils.add(UUApplication.user?.getBalanceMoney(), java.lang.Double.valueOf(mRechargeAmount.toDouble())))
        } else if (baseEvent.eventCode == EventCode.RECHARGE_SUCCESS) {
            UUApplication.user?.setBalanceMoney(NumberUtils.add(UUApplication.user?.getBalanceMoney(), java.lang.Double.valueOf(mRechargeAmount.toDouble())))
            finish()
        }
    }

    /**
     * 充值金额输入框的监听器
     */
    inner class RechargeTextWatcher : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
            if (s.toString().trim { it <= ' ' }.isNotEmpty()) {
                tv_sure.isClickable = true
                tv_sure.background = resources.getDrawable(R.drawable.shape_accent_corner)
            } else {
                tv_sure.isClickable = false
                tv_sure.background = resources.getDrawable(R.drawable.shape_gray_corner)
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (!StringUtil.isEmpty(s)) {
                if (java.lang.Long.parseLong(s.toString()) > ConfigConstant.MAX_RECHARGE_LIMIT) {
                    val maxRechargeLimit = ConfigConstant.MAX_RECHARGE_LIMIT.toString()
                    et_money_amount.setText(maxRechargeLimit)
                    et_money_amount.setSelection(maxRechargeLimit.length)
                    ToastUtil.showToast("已达到最大充值数额")
                }
            }
        }
    }
}
