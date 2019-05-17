package com.shqj.mine.activity

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.CompoundButton
import com.alibaba.android.arouter.facade.annotation.Route
import com.sleep.commonlib.util.LibCommonUtil
import com.sleep.commonlib.util.StringUtil
import com.sleep.commonlib.util.ToastUtil
import com.sleep.commonlib.widget.TimerCount
import com.sleep.uulib.UUApplication
import com.sleep.uulib.bean.ModifyTradingPwdBean
import com.sleep.uulib.bean.VerificationCodeBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.mvp.model.CommonModule
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import com.sleep.uulib.uubase.UUBaseActivity
import com.shqj.mine.R
import com.shqj.mine.mvp.presenter.ModifyTradingPwdPresenter
import com.shqj.mine.mvp.view.ModifyTradingPwdView
import kotlinx.android.synthetic.main.activity_modity_trading_pwd.*

/**
 * 修改支付密码
 */
@Route(path = ArouterConstant.MINE_MODIFY_TRADING_PWD)
class ModifyTradingPwdActivity : UUBaseActivity(), View.OnClickListener, CompoundButton.OnCheckedChangeListener, ModifyTradingPwdView, View.OnFocusChangeListener {

    private var downTimer: TimerCount? = null
    private lateinit var mPresenter: ModifyTradingPwdPresenter

    override fun getLayoutResourse(): Int {
        return R.layout.activity_modity_trading_pwd
    }

    override fun initView() {
        setTitle(getString(R.string.title_modify_trading_pwd))

        et_phone_num.setText(UUApplication.user?.getMobilePhone())

        //是否显示密码
        cb_visible.setOnCheckedChangeListener(this)
        cb_visible.isChecked = false

        et_confirm_code.addTextChangedListener(EtConfirmTextWatcher())
        et_new_pwd.addTextChangedListener(EtNewPwdTextWatcher())
        et_phone_num.addTextChangedListener(PhoneNumTextWatcher())
        et_phone_num.onFocusChangeListener = this

        iv_clearn.setOnClickListener(this)

        //确定按钮
        tv_sure.setBackgroundResource(R.drawable.shape_dark_corner3)
        tv_sure.isClickable = false

        tv_sure.setOnClickListener(this)
        tv_get_comfirm_code.setOnClickListener(this)
    }

    override fun initData() {
        mPresenter = ModifyTradingPwdPresenter(this, this, mStateManager)
    }

    override fun retryGetData() {
        mStateManager.showContent()
    }

    override fun onDestroy() {
        downTimer?.cancel()
        downTimer = null
        super.onDestroy()
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        when (v?.id) {
            R.id.et_phone_num -> {
                if (hasFocus && et_phone_num.text.toString().isNotEmpty()) {
                    iv_clearn.visibility = View.VISIBLE
                } else {
                    iv_clearn.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tv_sure -> {
                //确定
                if (et_new_pwd.text.toString().trim().length != 6) {
                    ToastUtil.showToast("请输入六位密码")
                    return
                }
                if (et_confirm_code.text.toString().trim().length != 4) {
                    ToastUtil.showToast("请输入四位验证码")
                    return
                }
                mPresenter.modifyTradingPwd(et_new_pwd.text.toString(), et_confirm_code.text.toString())
            }
            R.id.tv_get_comfirm_code -> {
                if(StringUtil.isMobileNumber(et_phone_num.text.toString())){
                    //获取验证码
                    CommonModule.getVerifyCode(this, et_phone_num.text.toString(), "5", mListener)
                }else{
                    ToastUtil.showToast("请输入正确手机号")
                }
            }
            R.id.iv_clearn -> {
                //清空手机号
                et_phone_num.setText("")
            }
        }
    }

    override fun getDataFinish() {
    }

    override fun modifySuccess(data: ModifyTradingPwdBean) {
        ToastUtil.showToast("修改成功")
        finish()
    }

    /**
     * 获取验证码的回调监听
     */
    private val mListener = object : OnLoadDataListener<VerificationCodeBean> {
        override fun onFailure(e: Throwable?, errorCode: Int) {
            NetCommonMethod.judgeError(errorCode, mStateManager)
        }

        override fun onSuccess(data: VerificationCodeBean) {
            downTimer = LibCommonUtil.getTimerCount(tv_get_comfirm_code)
            downTimer?.start()
        }
    }

    /**
     * 检查确定按钮是否可以点击
     */
    private fun checkBtn(s: Editable?) {
        if (s.toString().trim({ it <= ' ' }).isNotEmpty() && !StringUtil.isEmpty(et_new_pwd.text.toString().trim()) && StringUtil.isMobileNumber(et_phone_num.text.toString())) {
            tv_sure.setBackgroundResource(R.drawable.shape_accent_corner3)
            tv_sure.isClickable = true
        } else {
            tv_sure.setBackgroundResource(R.drawable.shape_dark_corner3)
            tv_sure.isClickable = false
        }
    }

    /**
     * 是否显示密码
     */
    override fun onCheckedChanged(p0: CompoundButton?, isChecked: Boolean) {
        if (isChecked) {
            et_new_pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()     //显示密码
        } else {
            et_new_pwd.transformationMethod = PasswordTransformationMethod.getInstance()        //隐藏密码
        }
        et_new_pwd.setSelection(if (TextUtils.isEmpty(et_new_pwd.text)) 0 else et_new_pwd.length())        //光标挪到最后
    }

    /**
     * 两个EditText的TeextWatcher
     */
    private inner class EtConfirmTextWatcher : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            checkBtn(p0)
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    }

    private inner class EtNewPwdTextWatcher : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            checkBtn(p0)
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

    }

    private inner class PhoneNumTextWatcher : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            if (et_phone_num.text.toString().isNotEmpty()) {
                iv_clearn.visibility = View.VISIBLE
            } else {
                iv_clearn.visibility = View.INVISIBLE
            }

            //检查是否输入完整手机号来改变获取验证码的颜色
            if (StringUtil.isMobileNumber(et_phone_num.text.toString())) {
                tv_get_comfirm_code.setTextColor(resources.getColor(com.sleep.uulib.R.color.colorAccent))
            } else {
                tv_get_comfirm_code.setTextColor(resources.getColor(com.sleep.uulib.R.color.color_4c4c4c))
            }
            checkBtn(p0)
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

    }
}
