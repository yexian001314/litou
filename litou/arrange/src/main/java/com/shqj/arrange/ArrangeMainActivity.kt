package com.shqj.arrange

import com.shqj.arrange.fragment.ArrangeFinanceFragment
import com.sleep.uulib.uubase.UUBaseActivity

class ArrangeMainActivity : UUBaseActivity() {

    private lateinit var arrangeFinanceFragment: ArrangeFinanceFragment

    override fun getLayoutResourse(): Int {
        return R.layout.arrange_activity_arrange_main
    }

    override fun shouldShowToolBar(): Boolean {
        return false
    }

    override fun initView() {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        arrangeFinanceFragment = ArrangeFinanceFragment()
        transaction.replace(R.id.content, arrangeFinanceFragment)
        transaction.commit()
    }

    override fun initData() {
    }

    override fun retryGetData() {
    }
}
