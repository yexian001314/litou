package com.shqj.mine.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.uubase.UUBaseActivity
import com.shqj.mine.R

/**
 * 修改登录密码
 */
@Route(path = ArouterConstant.MINE_MODIFY_LOGIN_PWD)
class ModifyLoginPwdActivity : UUBaseActivity() {

    override fun getLayoutResourse(): Int {
        return R.layout.activity_modify_login_pwd
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun retryGetData() {
    }
}
