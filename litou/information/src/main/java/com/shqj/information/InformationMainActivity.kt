package com.shqj.information

import com.shqj.information.fragment.InformationFragment
import com.sleep.uulib.uubase.UUBaseActivity

class InformationMainActivity : UUBaseActivity() {

    override fun retryGetData() {
    }

    override fun getLayoutResourse(): Int {
        return R.layout.information_activity_information_main
    }

    override fun initView() {
        hideBackView()
        setTitle("咨询")
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.content, InformationFragment())
        transaction.commit()
    }

    override fun initData() {
    }
}
