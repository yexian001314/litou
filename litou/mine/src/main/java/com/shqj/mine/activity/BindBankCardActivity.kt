package com.shqj.mine.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.uubase.UUBaseActivity
import com.shqj.mine.R

/**
 * @Deprecated
 *
 */
@Route(path = ArouterConstant.MINE_BIND_BANK_CARD)
class BindBankCardActivity : UUBaseActivity() {
    override fun getLayoutResourse(): Int {
        return R.layout.mine_activity_bind_bank_card
    }

    override fun initView() {
        setTitle(getString(R.string.title_bind_bank_card))
    }

    override fun initData() {
    }

    override fun retryGetData() {
    }

}
