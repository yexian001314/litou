package com.sleep.uulib.account

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.sleep.uulib.R
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.mvp.view.LoginView
import com.sleep.uulib.uubase.UUBaseActivity
import com.shqj.mine.mvp.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login_step_two.*

/**
 * 根据手机号和密码进行登录
 */
@Route(path = ArouterConstant.UUBASE_LOGIN_STEP_TWO)
class LoginStepTwoActivity : UUBaseActivity(), LoginView, TextWatcher, View.OnClickListener {

    private val mPresenter: LoginPresenter get() = LoginPresenter(this, this, mStateManager)

    companion object {
        private val PHONE_NUM = "phoneNum"
        fun launch(phoneNum: String) {
            ARouter.getInstance().build(ArouterConstant.UUBASE_LOGIN_STEP_TWO).withString(PHONE_NUM, phoneNum).navigation()
        }
    }

    /**
     * 经过验证已经注册过的手机号码
     */
    @Autowired
    @JvmField
    var phoneNum: String = ""

    override fun getLayoutResourse(): Int {
        return R.layout.activity_login_step_two
    }

    override fun initView() {
        setTitle(getString(R.string.login_title))
        et_login_pwd.addTextChangedListener(this)
        tv_login.setOnClickListener(this)
        tv_switch_account_or_register.setOnClickListener(this)
        tv_forget_pwd.setOnClickListener(this)
    }

    override fun initData() {
        tv_account.text = phoneNum
    }

    override fun loginSuccess() {
        mStateManager.showContent()
        //登录成功，跳转到首页
        ARouter.getInstance().build(ArouterConstant.APP_MAIN_ACTIVITY).navigation()
    }

    override fun afterTextChanged(p0: Editable?) {
        if (p0.toString().trim().isNotEmpty()) {
            tv_login.isClickable = true
            tv_login.setBackgroundResource(R.drawable.shape_accent_corner3)
        } else {
            tv_login.isClickable = false
            tv_login.setBackgroundResource(R.drawable.shape_dark_corner3)
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tv_login ->
                //登录
                login()
            R.id.tv_switch_account_or_register -> {
                //切换账号或注册
                finish()
            }
            R.id.tv_forget_pwd -> {
                //忘记密码
                ForgetLoginPwdActivity.launch(phoneNum)
            }
        }
    }

    override fun retryGetData() {
        mStateManager.showLoading()
        login()
    }

    private fun login() {
        mPresenter.login(phoneNum, et_login_pwd.text.toString())
    }

}
