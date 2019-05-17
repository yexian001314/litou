package com.shqj.arrange.fragment


import android.support.v4.app.Fragment
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.uubase.UUBaseFragment
import com.shqj.arrange.R


/**
 * A simple [Fragment] subclass.
 */
@Route(path = ArouterConstant.APP_MARKET_FRAGMENT)
class MarketFragment : UUBaseFragment() {

    override fun getLayoutResourse(): Int {
        return R.layout.fragment_market
    }

    override fun onFirstUserVisible() {

    }

    override fun initView(view: View) {
    }

    override fun initData() {
    }

    override fun retryGetData() {
    }

}
