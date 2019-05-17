package com.sleep.uulib.account

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.twitterlib.wigdet.dialog.DialogFactory
import com.shqj.mine.mvp.presenter.RegistPresenter
import com.sleep.commonlib.util.*
import com.sleep.commonlib.widget.TimerCount
import com.sleep.uulib.R
import com.sleep.uulib.UUApplication
import com.sleep.uulib.bean.CheckPhoneBean
import com.sleep.uulib.bean.RegisterInfo
import com.sleep.uulib.bean.VerificationCodeBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ArouterParamConstant
import com.sleep.uulib.constant.Constant
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.model.CommonModule
import com.sleep.uulib.mvp.view.RegistView
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import com.sleep.uulib.util.UUCommonUtil
import com.sleep.uulib.uubase.HtmlUrlActivity
import com.sleep.uulib.uubase.UUBaseActivity
import com.sleep.uulib.widget.fragmentdialog.IdentifyDialogLogicSetter
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_regist.*
import java.util.*

/**
 * 注册
 */
@Route(path = ArouterConstant.UUBASE_REGIST)
class RegistActivity : UUBaseActivity(), View.OnClickListener, RegistView, View.OnFocusChangeListener, CompoundButton.OnCheckedChangeListener {

    private var downTimer: TimerCount? = null
    private lateinit var mPresenter: RegistPresenter
    /**
     * 密码是否明文显示
     */
    private var psdIsShow = false

    companion object {
        private val PHONE_NUM = "phoneNum"
        fun launch(phoneNum: String = "") {
            ARouter.getInstance().build(ArouterConstant.UUBASE_REGIST).withString(PHONE_NUM, phoneNum).navigation()
        }
    }

    override fun getLayoutResourse(): Int {
        return R.layout.activity_regist
    }

    override fun initView() {
        setTitle(getString(R.string.regist_title))
        setToolBarColor(R.color.white)
        (getBarView(TitleView.LEFT_IV) as ImageView).setImageResource(R.mipmap.icon_black_left_icon)
        (getBarView(TitleView.TITLE_TV) as TextView).setTextColor(resources.getColor(R.color.black))
        tv_register.setBackgroundResource(R.drawable.shape_gray_corner)

        tv_register_rule.setOnClickListener(this)
        tv_get_comfirm_code.setOnClickListener(this)
        tv_get_comfirm_code.isClickable = false
        tv_register.setOnClickListener(this)
        tv_register.isClickable = false
        cb_visible.setOnCheckedChangeListener(this)
        cb_visible.isChecked = false

        iv_clearn.setOnClickListener(this)

        et_comfirm_code.addTextChangedListener(ConfirmTextWatcher())
        et_comfirm_code.onFocusChangeListener = this
        et_set_pwd.addTextChangedListener(PswTextWatcher())
        et_set_pwd.onFocusChangeListener = this

        et_phone_num.addTextChangedListener(PhoneNumTextWatcher())
        et_phone_num.onFocusChangeListener = this

        et_invite_code.onFocusChangeListener = this
    }

    override fun initData() {
        mPresenter = RegistPresenter(this, this, mStateManager)
    }

    override fun onDestroy() {
        downTimer?.cancel()
        downTimer = null
        super.onDestroy()
    }

    override fun retryGetData() {
        mStateManager.showContent()
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        when (v?.id) {
            R.id.et_phone_num -> {
                if (hasFocus) {
                    MobclickAgent.onEvent(this, "zc_srsj")
                    if (et_phone_num.text.toString().isNotEmpty()) {
                        iv_clearn.visibility = View.VISIBLE
                    } else {
                        iv_clearn.visibility = View.INVISIBLE
                    }
                }
            }

            R.id.et_set_pwd -> {
                if (hasFocus) {
                    MobclickAgent.onEvent(this, "zc_srmm")
                }
            }
            R.id.et_comfirm_code -> {
                if(hasFocus){
                    MobclickAgent.onEvent(this, "zc_sryzm")
                }
            }
            R.id.et_invite_code -> {
                if(hasFocus){
                    MobclickAgent.onEvent(this, "zc_sryqm")
                }
            }
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (isChecked) {
            et_set_pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()     //显示密码
            cb_visible.setBackgroundResource(R.mipmap.eyes_open_icon)
        } else {
            et_set_pwd.transformationMethod = PasswordTransformationMethod.getInstance()        //隐藏密码
            cb_visible.setBackgroundResource(R.mipmap.eyes_close_icon)
        }
        et_set_pwd.setSelection(if (TextUtils.isEmpty(et_set_pwd.text)) 0 else et_set_pwd.length())
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_get_comfirm_code -> {
                MobclickAgent.onEvent(this, "zc_hxyzm_btn")
                mPresenter.checkPhoneIsExist(et_phone_num.text.toString())
            }
            R.id.iv_clearn -> {
                //点击清除按钮
                et_phone_num.setText("")
            }
            R.id.tv_register -> {
                if (!checkRegisterIsClickable()) {
                    return
                }
                //注册按钮
                if (LibCommonUtil.isFastDoubleClick()) {
                    return
                }
                MobclickAgent.onEvent(this, "zc_zc_btn")
                val phoneNum = et_phone_num.text.toString()
                val strComfirmCode = et_comfirm_code.text.toString().trim({ it <= ' ' })
                val strPwd = et_set_pwd.text.toString().trim()
                val strInviteCode = et_invite_code.text.toString().trim()
                if (strPwd.length < 6) {
                    ToastUtil.showToast("登录密码由6位以上数字、字母组合")
                    return
                }
                val treeMap = TreeMap<String, Any?>()
                treeMap.put(NetConstant.MOBILE_PHONE, phoneNum)
                treeMap.put(NetConstant.VERIFY_CODE, strComfirmCode)
                treeMap.put(NetConstant.PASSWORD, strPwd)
                treeMap.put(NetConstant.CHANNEL_ID, UUCommonUtil.getChannelId(this))
                if (!TextUtils.isEmpty(strInviteCode)) {
                    treeMap.put(NetConstant.RELATE_MOBILE_PHONE, strInviteCode)
                }
                treeMap.put(NetConstant.DEVICE_MODEL, DeviceUtils.getModel())
                treeMap.put(NetConstant.IMEI, PhoneUtils.getIMEI())
                treeMap.put(NetConstant.MOBILE_VERSION, DeviceUtils.getSDKVersion().toString())
                treeMap.put(NetConstant.OS, "0")
                mPresenter.regist(treeMap)
            }

            R.id.tv_register_rule -> {
                if (LibCommonUtil.isFastDoubleClick()) {
                    return
                }
                //用户协议
                HtmlUrlActivity.launch(NetConstant.H5_REGIST_AGREEMENT)
            }
        }
    }

    override fun checkSuccess(data: CheckPhoneBean) {
        if (data.isIsExist) {
            //手机号已存在
            DialogFactory.instance.getCommonDialog("提示", "手机号已被注册", "去登陆", "知道了", true, View.OnClickListener {
                RegistActivity@ this.finish()
            }).show(supportFragmentManager, "phone_exit_dialog")
        } else {
            //先弹出加载验证码需要的验证狂、验证成功才获取验证码
            val phoneNum = et_phone_num.text.toString()
            if (StringUtil.isMobileNumber(phoneNum)) {
                DialogFactory.instance.identifyDialog(object : IdentifyDialogLogicSetter.ConfirmClickListener {
                    override fun onConfirmClick(identifyStr: String) {
                        CommonModule.getVerifyCode(this@RegistActivity, phoneNum, "1", mListener, identifyStr)
                    }

                }).show(supportFragmentManager, "identify_code")
            } else {
                ToastUtil.showToast("请先输入手机号")
            }
        }
    }

    override fun registSuccess(data: RegisterInfo) {
        DialogFactory.instance.getCommonDialog("注册", "新手高福利，错过不再有\n" +
                "赶紧完善资料使用红包吧", "继续去完善", "取消", false,
                View.OnClickListener {
                    if (!UUApplication.user?.isIsIdentityVerified()!!) {
                        ARouter.getInstance().build(ArouterConstant.UUBASE_IDENTIFICATION).navigation()
                        finish()
                    }
                },
                View.OnClickListener {
                    ARouter.getInstance().build(ArouterConstant.APP_MAIN_ACTIVITY).withInt(ArouterParamConstant.CHOOSE_POSITION, 0).navigation()
                    finish()
                }).show(supportFragmentManager, "regist_dialog")
    }

    private val mListener = object : OnLoadDataListener<VerificationCodeBean> {
        override fun onFailure(e: Throwable?, errorCode: Int) {
            NetCommonMethod.judgeError(errorCode, mStateManager)
            ToastUtil.showToast("验证码输入错误")
        }

        override fun onSuccess(data: VerificationCodeBean) {
            downTimer = LibCommonUtil.getTimerCount(tv_get_comfirm_code)
            downTimer?.start()
        }
    }

    private fun checkRegisterIsClickable(): Boolean {
        if (!StringUtil.isMobileNumber(et_phone_num.text.toString())) {
            ToastUtil.showToast(getString(R.string.warning_please_input_right_phone_num))
            return false
        }
        if (et_comfirm_code.text.toString().length != 4) {
            ToastUtil.showToast(getString(R.string.warning_please_input_right_confirm_code))
            return false
        }
        if (!StringUtil.isMatcherStr(et_set_pwd.text.toString(), Constant.PSD_PATTERN)) {
            ToastUtil.showToast(getString(R.string.warning_please_input_right_psd))
            return false
        }
        return true
    }

    /**
     * 根据输入设置注册按钮背景
     */
    private fun changeRegistBtnClickBackground() {
        val phoneNumIsMatch = !StringUtil.isEmpty(et_phone_num.text.toString())
        val confirmCodeIsMatch = !StringUtil.isEmpty(et_comfirm_code.text.toString())
        val psdIsMatch = !StringUtil.isEmpty(et_set_pwd.text.toString())
        if (phoneNumIsMatch && confirmCodeIsMatch && psdIsMatch) {
            tv_register.setBackgroundResource(R.drawable.shape_accent_corner)
            tv_register.isClickable = true
        } else {
            tv_register.setBackgroundResource(R.drawable.shape_gray_corner)
            tv_register.isClickable = false
        }
    }

    private inner class PhoneNumTextWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable) {
            if (et_phone_num.text.toString().isNotEmpty()) {
                iv_clearn.visibility = View.VISIBLE
            } else {
                iv_clearn.visibility = View.INVISIBLE
            }
            if (downTimer == null || downTimer?.isRun!!) {
                if (StringUtil.isMobileNumber(s.toString())) {
                    tv_get_comfirm_code.setTextColor(resources.getColor(R.color.colorAccent))
                    tv_get_comfirm_code.isClickable = true
                } else {
                    tv_get_comfirm_code.setTextColor(resources.getColor(R.color.color_4c4c4c))
                    tv_get_comfirm_code.isClickable = false
                }
            }
            changeRegistBtnClickBackground()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    }

    private inner class ConfirmTextWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            changeRegistBtnClickBackground()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

    }

    private inner class PswTextWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable) {
            if (!StringUtil.isMatcherStr(s.toString(), Constant.STR_DIGIT_LETTER) && s.toString().isNotEmpty()) {
                s.delete(s.toString().length - 1, s.toString().length)
                et_set_pwd.setSelection(if (TextUtils.isEmpty(et_set_pwd.text)) 0 else et_set_pwd.length())
            }
            changeRegistBtnClickBackground()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

    }
}
