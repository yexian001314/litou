package com.shqj.mine.activity

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.CompoundButton
import com.alibaba.android.arouter.facade.annotation.Route
import com.sleep.commonlib.util.StringUtil
import com.sleep.commonlib.util.ToastUtil
import com.sleep.uulib.UUApplication
import com.sleep.uulib.bean.SetTradingPwdBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.uubase.UUBaseActivity
import com.shqj.mine.R
import com.shqj.mine.mvp.presenter.SetTradingPwdPresenter
import com.shqj.mine.mvp.view.SetTradingPwdView
import kotlinx.android.synthetic.main.activity_set_trading_pwd.*

/**
 * 设置交易密码
 */
@Route(path = ArouterConstant.MINE_SET_TRADING_PWD)
class SetTradingPwdActivity : UUBaseActivity(), View.OnClickListener, TextWatcher, CompoundButton.OnCheckedChangeListener, SetTradingPwdView {

    private lateinit var mPresenter: SetTradingPwdPresenter

    override fun getLayoutResourse(): Int {
        return R.layout.activity_set_trading_pwd
    }

    override fun initView() {
        setTitle(getString(R.string.title_set_trading_pwd))
        tv_sure.setBackgroundResource(R.drawable.shape_dark_corner3)
        tv_sure.isClickable = false
        tv_sure.setOnClickListener(this)

        et_set_pwd.addTextChangedListener(this)
        et_set_pwd_again.addTextChangedListener(this)

        cb_visible_set.setOnCheckedChangeListener(this)
        cb_visible_set.isChecked = false
        cb_visible_confirm.setOnCheckedChangeListener(this)
        cb_visible_confirm.isChecked = false
    }

    override fun initData() {
        mPresenter = SetTradingPwdPresenter(this,this,mStateManager)
    }

    override fun retryGetData() {
        mStateManager.showContent()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_sure -> {
                //确定设置
                val firstPwd = et_set_pwd.text.toString().trim()
                val secondPwd = et_set_pwd_again.text.toString().trim()
                if (firstPwd != secondPwd) {
                    ToastUtil.showToast("两次密码输入不一致,请您重新输入")
                    return
                }
                if (firstPwd.length != 6 || secondPwd.length != 6) {
                    ToastUtil.showToast("支付密码为6位")
                    return
                }
                mPresenter.setTradingPwd(firstPwd)
            }
        }
    }

    override fun getDataFinish() {
    }

    override fun setTradingPwdSuccess(data: SetTradingPwdBean) {
        ToastUtil.showToast("设置成功")
        UUApplication.user?.setIsTradersPwBinded(true)
        finish()
    }

    /**
     * 检查确定按钮是否可以点击
     */
    private fun checkBtn() {
        if (!StringUtil.isEmpty(et_set_pwd.text.toString().trim()) && !StringUtil.isEmpty(et_set_pwd_again.text.toString().trim())) {
            tv_sure.setBackgroundResource(R.drawable.shape_accent_corner3)
            tv_sure.isClickable = true
        } else {
            tv_sure.setBackgroundResource(R.drawable.shape_dark_corner3)
            tv_sure.isClickable = false
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when(buttonView?.id){
            R.id.cb_visible_set -> {
                if (isChecked) {
                    et_set_pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()         //显示密码
                } else {
                    et_set_pwd.transformationMethod = PasswordTransformationMethod.getInstance()            //隐藏密码
                }
                et_set_pwd.setSelection(if (TextUtils.isEmpty(et_set_pwd.text)) 0 else et_set_pwd.length())  //光标挪到最后
            }
            R.id.cb_visible_confirm -> {
                if (isChecked) {
                    et_set_pwd_again.transformationMethod = HideReturnsTransformationMethod.getInstance()         //显示密码
                } else {
                    et_set_pwd_again.transformationMethod = PasswordTransformationMethod.getInstance()            //隐藏密码
                }
                et_set_pwd_again.setSelection(if (TextUtils.isEmpty(et_set_pwd_again.text)) 0 else et_set_pwd_again.length())  //光标挪到最后
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {
        checkBtn()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}
