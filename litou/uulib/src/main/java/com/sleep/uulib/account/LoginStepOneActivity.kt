package com.sleep.uulib.account

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.sleep.commonlib.util.StringUtil
import com.sleep.uulib.R
import com.sleep.uulib.bean.CheckPhoneBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.view.LoginStepOnView
import com.sleep.uulib.uubase.UUBaseActivity
import com.shqj.mine.mvp.presenter.LogintStepOnePresenter
import kotlinx.android.synthetic.main.activity_login_step_one.*
import java.util.*

/**
 * 检测手机号是否注册
 */
@Route(path = ArouterConstant.UUBASE_LOGIN_STEP_ONE)
class LoginStepOneActivity : UUBaseActivity(), View.OnClickListener, TextWatcher, LoginStepOnView {

    private val mOnePresenter: LogintStepOnePresenter get() = LogintStepOnePresenter(this, this, mStateManager)

    override fun getLayoutResourse(): Int {
        return R.layout.activity_login_step_one
    }

    override fun shouldReceiveOtherLogin(): Boolean {
        return false
    }

    override fun initView() {
        setTitle(getString(R.string.login_title))
        bt_next_step.setOnClickListener(this)
        et_login_tel_num.addTextChangedListener(this)
    }

    override fun initData() {
    }

    override fun onResume() {
        super.onResume()
        et_login_tel_num.setText("")
        et_login_tel_num.clearFocus()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.bt_next_step -> if (bt_next_step.isClickable) {
                //检测手机号
                check()
            }
        }
    }

    override fun retryGetData() {
        mStateManager.showContent()
    }

    private fun check() {
        val treeMap = TreeMap<String, Any>()
        treeMap.put(NetConstant.MOBILE_PHONE, et_login_tel_num.text.toString())
        mOnePresenter.checkPhoneIsExist(treeMap)
    }

    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(str: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (StringUtil.isMobileNumber(str?.toString())) {
            bt_next_step.setBackgroundResource(R.drawable.shape_accent_corner3)
            bt_next_step.isClickable = true
        } else {
            bt_next_step.setBackgroundResource(R.drawable.shape_dark_corner3)
            bt_next_step.isClickable = false
        }
    }

    override fun getDataFinish() {

    }

    override fun checkSuccess(data: CheckPhoneBean) {
        mStateManager.showContent()
        if (data.isIsExist) {
            //手机号已存在，跳转到登录界面
            LoginStepTwoActivity.launch(et_login_tel_num.text.toString())
        } else {
            //手机号不存在，跳转到注册界面
            RegistActivity.launch(et_login_tel_num.text.toString())
        }
    }

}
