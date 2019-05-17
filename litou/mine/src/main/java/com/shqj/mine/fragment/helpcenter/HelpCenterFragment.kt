package com.shqj.mine.fragment.helpcenter

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.sleep.uulib.bean.HelpCenterBean
import com.sleep.uulib.uubase.UUBaseFragment
import com.shqj.mine.R
import com.shqj.mine.adapter.HelpCenterPagerAdapter
import com.shqj.mine.mvp.presenter.HelpCenterPresenter
import com.shqj.mine.mvp.view.IHelpCenterView
import kotlinx.android.synthetic.main.frag_help_center.*

/**
 * 帮助中心
 */
class HelpCenterFragment() : UUBaseFragment(), IHelpCenterView<HelpCenterBean> {
    override fun getDataFinish() {

    }

    override fun loadData(data: HelpCenterBean) {
        if (data.helpMenuListEOS != null) {
            mData.addAll(data.helpMenuListEOS)
            mAdapter = HelpCenterPagerAdapter(mData)
            rv_contain.adapter = mAdapter
        }
    }

    private val mPresenter: HelpCenterPresenter get() = HelpCenterPresenter(this, mStateManager)
    private lateinit var mAdapter: HelpCenterPagerAdapter
    private lateinit var mData: MutableList<HelpCenterBean.HelpMenuListEOSBean>
    override fun initView(view: View) {
        mData = mutableListOf()
        rv_contain.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun initData() {

        mPresenter.requestData()
    }

    override fun onFirstUserVisible() {

    }

    override fun retryGetData() {

    }


    override fun getLayoutResourse(): Int {
        return R.layout.frag_help_center
    }
}