package com.sleep.uulib.account

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.twitterlib.wigdet.dialog.CommonFragmentDialog
import com.example.twitterlib.wigdet.dialog.DialogFactory
import com.shqj.mine.mvp.presenter.LoginPresenter
import com.sleep.commonlib.base.BaseEvent
import com.sleep.commonlib.base.EventCode
import com.sleep.commonlib.util.*
import com.sleep.uulib.R
import com.sleep.uulib.UUApplication
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.Constant
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.view.LoginView
import com.sleep.uulib.netWork.multi_base_url.MultiBaseUrlInterceptor
import com.sleep.uulib.uubase.UUBaseActivity
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.uulib_activity_login.*
import org.simple.eventbus.Subscriber

@Route(path = ArouterConstant.UUBASE_LOGIN)
class LoginActivity : UUBaseActivity(), View.OnClickListener, CompoundButton.OnCheckedChangeListener, LoginView, View.OnFocusChangeListener {

    private val mPresenter: LoginPresenter get() = LoginPresenter(this, this, mStateManager)

    companion object {
        private val JUMP_POSITION = "jumpPosition"
        private val CODE_REQUEST_SET_GESTRUE = 101//设置手势密码

        /**
         * 跳转到主页的位置
         * 不填为正常弹出栈里的登录界面
         */
        fun launch(position: Int = -1) {
            ARouter.getInstance()
                    .build(ArouterConstant.UUBASE_LOGIN)
                    .withInt(JUMP_POSITION, position)
                    .navigation()
        }
    }

    private var exitPosition = -1

    override fun shouldReceiveOtherLogin(): Boolean {
        return false
    }

    override fun getLayoutResourse(): Int {
        return R.layout.uulib_activity_login
    }

    override fun onLeftViewClick(iv_left: ImageView) {
        super.onLeftViewClick(iv_left)
        MobclickAgent.onEvent(this, "dl_dbtc_btn")
    }

    override fun initView() {
        setTitle(getString(R.string.login_title))
        setToolBarColor(R.color.white)
        (getBarView(TitleView.LEFT_IV) as ImageView).setImageResource(R.mipmap.icon_login_close)
        tv_login.setOnClickListener(this)
        tv_login.isClickable = false
        tv_login.setBackgroundResource(R.drawable.shape_gray_corner)

        cb_eyes.setOnCheckedChangeListener(this)
        cb_eyes.isChecked = false
        iv_clearn.setOnClickListener(this)

        tv_forget_pwd.setOnClickListener(this)
        bt_regist.setOnClickListener(this)

        //选择服务器
        iv_choose_server.setOnClickListener(this)

        et_phone_num.addTextChangedListener(PhoneNumTextWatcher())
        et_phone_num.onFocusChangeListener = this
        et_psd.addTextChangedListener(PsdTextWatcher())
        et_psd.onFocusChangeListener = this
    }

    override fun initData() {
        exitPosition = intent.getIntExtra(JUMP_POSITION, -1)
        et_phone_num.setText(SPUtil.getInstance().getString(Constant.PHONE_NUM))
    }

    override fun retryGetData() {
        mStateManager.showContent()
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (isChecked) {
            et_psd.transformationMethod = HideReturnsTransformationMethod.getInstance()     //显示密码
            cb_eyes.setBackgroundResource(R.mipmap.eyes_open_icon)
        } else {
            et_psd.transformationMethod = PasswordTransformationMethod.getInstance()        //隐藏密码
            cb_eyes.setBackgroundResource(R.mipmap.eyes_close_icon)
        }
        et_psd.setSelection(if (TextUtils.isEmpty(et_psd.text)) 0 else et_psd.length())
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        when (v?.id) {
            R.id.et_phone_num -> {
                if (hasFocus) {
                    MobclickAgent.onEvent(this, "dl_dl_srsjh")
                    if (et_phone_num.text.toString().isNotEmpty()) {
                        iv_clearn.visibility = View.VISIBLE
                    } else {
                        iv_clearn.visibility = View.INVISIBLE
                    }
                }
            }
            R.id.et_psd -> {
                if (hasFocus) {
                    MobclickAgent.onEvent(this, "dl_dl_srmm")
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_clearn -> {
                //点击清除按钮
                et_phone_num.setText("")
            }
            R.id.tv_login -> {
                if (!checkBtnClickable()) {
                    return
                }
                //立即登录
                MobclickAgent.onEvent(this, "dl_dl_btn")
                mPresenter.login(et_phone_num.text.toString(), et_psd.text.toString())
            }
            R.id.tv_forget_pwd -> {
                if (LibCommonUtil.isFastDoubleClick()) {
                    return
                }
                //忘记密码
                MobclickAgent.onEvent(this, "dl_zc_wjmm")
                ForgetLoginPwdActivity.launch()
            }
            R.id.bt_regist -> {
                if (LibCommonUtil.isFastDoubleClick()) {
                    return
                }
                //注册
                MobclickAgent.onEvent(this, "dl_zc_btn")
                RegistActivity.launch()
            }
            R.id.iv_choose_server -> {
                //点击六次更换服务器
                if (Utils.isApkDebugable(this)) {
//                    chooseServerUrl()
                }
            }
        }
    }

    override fun loginSuccess() {
        ToastUtil.showRightCenterToast(this, "登录成功")
        SPUtil.getInstance().put(Constant.GES_PSD_ERROR_COUNT, 0)
        if (!UUApplication.user!!.getMobilePhone().equals(SPUtil.getInstance().getString(Constant.GES_PSD_LOGIN_PHONE_NUMBER))) {
            SPUtil.getInstance().put(Constant.GES_PSD_HAS, false)
            ARouter.getInstance().build(ArouterConstant.UUBASE_GESTRUE).navigation(this, 11)
            return
        }
//        if (exitPosition >= 0) {
//            ARouter.getInstance().build(ArouterConstant.APP_MAIN_ACTIVITY)
//                    .withInt(ArouterParamConstant.CHOOSE_POSITION, exitPosition)
//                    .navigation()
//        } else {
        finish()
//        }
    }


    private fun changeLoginBtnStatus() {
        if (!StringUtil.isEmpty(et_phone_num.text.toString()) && !StringUtil.isEmpty(et_psd.text.toString())) {
            tv_login.setBackgroundResource(R.drawable.shape_accent_corner)
            tv_login.isClickable = true
        } else {
            tv_login.isClickable = false
            tv_login.setBackgroundResource(R.drawable.shape_gray_corner)
        }
    }

    private fun checkBtnClickable(): Boolean {
        if (!StringUtil.isMobileNumber(et_phone_num.text.toString())) {
            ToastUtil.showToast(getString(R.string.warning_please_input_right_phone_num))
            return false
        }
        //去掉密码位数验证
//        if (!StringUtil.isMatcherStr(et_psd.text.toString(), Constant.OLD_PSD_PARTTERN)) {
//            ToastUtil.showToast(getString(R.string.warning_please_input_right_old_psd))
//            return false
//        }
        return true
    }

    @Subscriber
    private fun receiveRegistSuccess(data: BaseEvent<Any>) {
        if (data.eventCode == EventCode.LOGIN && !LoginActivity@ this.isFinishing) {
            finish()
        }
    }


    private inner class PhoneNumTextWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable) {
            if (s.toString().isNotEmpty()) {
                iv_clearn.visibility = View.VISIBLE
            } else {
                iv_clearn.visibility = View.INVISIBLE
            }
            changeLoginBtnStatus()
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
                et_psd.setSelection(if (TextUtils.isEmpty(et_psd.text)) 0 else et_psd.length())
            }
            changeLoginBtnStatus()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

    }

    private fun chooseServerUrl() {
        DialogFactory.instance.getBottomDialog(R.layout.dialog_choose_server_url, object : CommonFragmentDialog.ILogicSetter {
            override fun setLogic(fragmentDialog: CommonFragmentDialog, rootView: View) {
                rootView.findViewById<View>(R.id.tv_online_environment).setOnClickListener {
                    MultiBaseUrlInterceptor.getInstance().setGlobalDomain(NetConstant.FORMAL_URL)
                    ToastUtil.showToast("当前为正式环境")
                    fragmentDialog.dismiss()
                }
                rootView.findViewById<View>(R.id.tv_test_environment).setOnClickListener {
                    MultiBaseUrlInterceptor.getInstance().setGlobalDomain(NetConstant.TEST_URL)
                    ToastUtil.showToast("当前为测试环境")
                    fragmentDialog.dismiss()
                }
                rootView.findViewById<View>(R.id.tv_dev_environment).setOnClickListener {
                    MultiBaseUrlInterceptor.getInstance().setGlobalDomain(NetConstant.DEV_URL)
                    ToastUtil.showToast("当前为开发环境")
                    fragmentDialog.dismiss()
                }
            }

        }).show(supportFragmentManager, "choose_url_dialog")
    }

}
