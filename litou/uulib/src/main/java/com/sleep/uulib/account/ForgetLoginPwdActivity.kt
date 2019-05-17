package com.sleep.uulib.account

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.CompoundButton
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.shqj.mine.mvp.presenter.ForgetLoginPwdPresenter
import com.sleep.commonlib.util.LibCommonUtil
import com.sleep.commonlib.util.StringUtil
import com.sleep.commonlib.util.ToastUtil
import com.sleep.commonlib.widget.TimerCount
import com.sleep.uulib.R
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.Constant
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.view.ForgetLoginPwdView
import com.sleep.uulib.uubase.UUBaseActivity
import kotlinx.android.synthetic.main.activity_forget_login_pwd.*
import java.util.*


/**
 * 忘记登录密码/修改登录密码
 */
@Route(path = ArouterConstant.UUBASE_FORGET_LOGIN_PWD)
class ForgetLoginPwdActivity : UUBaseActivity(), View.OnClickListener, CompoundButton.OnCheckedChangeListener, ForgetLoginPwdView, View.OnFocusChangeListener {

    private lateinit var mPresenter: ForgetLoginPwdPresenter

    private var downTimer: TimerCount? = null

    /**
     * 经过验证已经注册过的手机号码
     */
    @Autowired
    @JvmField
    var phoneNum: String = ""

    companion object {
        private val PHONE_NUM = "phoneNum"
        fun launch(phoneNum: String = "") {
            ARouter.getInstance().build(ArouterConstant.UUBASE_FORGET_LOGIN_PWD).withString(PHONE_NUM, phoneNum).navigation()
        }
    }

    override fun getLayoutResourse(): Int {
        return R.layout.activity_forget_login_pwd
    }

    override fun initView() {
        if (!StringUtil.isEmpty(phoneNum)) {
            //忘记密码
            setTitle(getString(R.string.retrieve_login_pwd))
        } else {
            //找回登录密码
            setTitle(getString(R.string.title_forget_pwd))
        }
        et_phone_num.setText(phoneNum)
        et_phone_num.onFocusChangeListener = this

        //获取验证码
        tv_get_comfirm_code.setOnClickListener(this)
        //确认
        tv_login.setOnClickListener(this)
        tv_login.isClickable = false

        iv_clearn.setOnClickListener(this)

        //验证规则
        et_new_pwd.addTextChangedListener(PsdTextWatcher())
        et_confirm_code.addTextChangedListener(ConfirmCodeTextWatcher())
        et_phone_num.addTextChangedListener(PhoneNumTextWatcher())

        //是否明文显示密码
        cb_visible.setOnCheckedChangeListener(this)
        cb_visible.isChecked = false

    }

    override fun initData() {
        mPresenter = ForgetLoginPwdPresenter(this, this, mStateManager)
    }

    override fun retryGetData() {
        mStateManager.showContent()
    }

    override fun onDestroy() {
        downTimer?.cancel()
        downTimer = null
        super.onDestroy()
    }

    override fun onClick(v: View?) {

        //点击事件对象判断
        when (v?.id) {
            R.id.tv_get_comfirm_code -> {
                if (StringUtil.isMobileNumber(et_phone_num.text.toString())) {
                    //获取验证码
                    val treeMap = TreeMap<String, Any?>()
                    treeMap.put(NetConstant.MOBILE_PHONE, et_phone_num.text.toString())
                    treeMap.put(NetConstant.VERIFY_CODE_TYPE, "5")
                    mPresenter.getVerifyCode(treeMap)
                } else {
                    ToastUtil.showToast("请输入手机号")
                }
            }
            R.id.tv_login -> {
                if (!checkInput()) {
                    return
                }
                val treeMap = TreeMap<String, Any?>()
                treeMap.put(NetConstant.MOBILE_PHONE, et_phone_num.text.toString())
                treeMap.put(NetConstant.VERIFY_CODE, et_confirm_code.text.toString())
                treeMap.put(NetConstant.PASSWORD, et_new_pwd.text.toString())
                mPresenter.findPassword(treeMap)
            }

            R.id.iv_clearn -> {
                //清空手机号
                et_phone_num.setText("")
            }
        }
    }

    override fun findPasswordSuccess() {
        ToastUtil.showToast("修改成功")
//        ARouter.getInstance().build(ArouterConstant.APP_MAIN_ACTIVITY).withInt(ArouterParamConstant.CHOOSE_POSITION, 0).navigation()
        finish()
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

    /**
     * 检查三个edittext输入是否合法
     */
    private fun changeBtnBackground() {
        if (!StringUtil.isEmpty(et_phone_num.text.toString()) && !StringUtil.isEmpty(et_new_pwd.text.toString()) && !StringUtil.isEmpty(et_confirm_code.text.toString())) {
            tv_login.setBackgroundResource(R.drawable.shape_accent_corner)
            tv_login.isClickable = true
        } else {
            tv_login.setBackgroundResource(R.drawable.shape_gray_corner)
            tv_login.isClickable = false
        }
    }

    private fun checkInput(): Boolean {
        if (!StringUtil.isMobileNumber(et_phone_num.text.toString())) {
            ToastUtil.showToast(getString(R.string.warning_please_input_right_phone_num))
            return false
        }
        if (!StringUtil.isMatcherStr(et_confirm_code.text.toString(), Constant.CONFIRM_CORD_LETTER)) {
            ToastUtil.showToast(getString(R.string.warning_please_input_right_confirm_code))
            return false
        }
        if (!StringUtil.isMatcherStr(et_new_pwd.text.toString(), Constant.PSD_PATTERN)) {
            ToastUtil.showToast(getString(R.string.warning_please_input_right_psd))
            return false
        }
        return true
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (isChecked) {
            //显示密码
            et_new_pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            //隐藏密码
            et_new_pwd.transformationMethod = PasswordTransformationMethod.getInstance()
        }
        et_new_pwd.setSelection(if (StringUtil.isEmpty(et_new_pwd.text)) 0 else et_new_pwd.length())
    }

    override fun getVerifyCodeSuccess() {
        downTimer = LibCommonUtil.getTimerCount(tv_get_comfirm_code)
        downTimer?.start()
    }

    private inner class PhoneNumTextWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (et_phone_num.text.toString().isNotEmpty()) {
                iv_clearn.visibility = View.VISIBLE
            } else {
                iv_clearn.visibility = View.INVISIBLE
            }

            //检查是否输入完整手机号来改变获取验证码的颜色
            if (StringUtil.isMobileNumber(et_phone_num.text.toString())) {
                tv_get_comfirm_code.setTextColor(resources.getColor(R.color.colorAccent))
            } else {
                tv_get_comfirm_code.setTextColor(resources.getColor(R.color.color_4c4c4c))
            }
            changeBtnBackground()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

    }

    private inner class ConfirmCodeTextWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            changeBtnBackground()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    }

    private inner class PsdTextWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable) {
            if (!StringUtil.isMatcherStr(s.toString(), Constant.STR_DIGIT_LETTER) && s.toString().isNotEmpty()) {
                s.delete(s.toString().length - 1, s.toString().length)
                et_new_pwd.setSelection(if (TextUtils.isEmpty(et_new_pwd.text)) 0 else et_new_pwd.length())
            }
            changeBtnBackground()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

    }
}
