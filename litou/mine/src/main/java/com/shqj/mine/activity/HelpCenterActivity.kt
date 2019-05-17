package com.shqj.mine.activity

import android.support.v4.app.Fragment
import android.text.TextUtils
import com.alibaba.android.arouter.facade.annotation.Route
import com.sleep.commonlib.util.SPUtil
import com.sleep.uulib.bean.HelpBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.Constant
import com.sleep.uulib.uubase.UUBaseActivity
import com.shqj.mine.R
import com.shqj.mine.adapter.HelpCenterAdapter
import com.shqj.mine.adapter.ProjectDetailIndicatorAdapter
import com.shqj.mine.fragment.helpcenter.HelpCenterFragment
import com.shqj.mine.mvp.presenter.HelpPresenter
import com.shqj.mine.mvp.view.IHelpCenterView
import kotlinx.android.synthetic.main.activity_help_center.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator

@Route(path = ArouterConstant.MINE_HELPCENTER)
class HelpCenterActivity : UUBaseActivity(), IHelpCenterView<HelpBean> {
    override fun loadData(data: HelpBean) {
        for (item in data.helpMenuEOS) {
            mIndicatorTitles.add(item.title)
        }
        loadView()
    }

    override fun getDataFinish() {
    }

    private lateinit var mPresenter: HelpPresenter
    private lateinit var mViewPagerAdapter: HelpCenterAdapter
    private lateinit var mFragments: MutableList<Fragment>
    private lateinit var mIndicatorTitles: MutableList<String>

    override fun initView() {
        mPresenter = HelpPresenter(this, mStateManager)
        mIndicatorTitles=mutableListOf()
        if (TextUtils.isEmpty(SPUtil.getInstance().getString(Constant.HAS_HELP_CENTER_MENU))) {
            getData()
        } else {
            loadView()
        }
    }

    fun loadView() {
        mFragments = mutableListOf()
        for (item in mIndicatorTitles) {
            mFragments.add(HelpCenterFragment())
        }
        mViewPagerAdapter = HelpCenterAdapter(mFragments, supportFragmentManager)
        project_detail_viewpager.adapter = mViewPagerAdapter
        project_detail_viewpager.offscreenPageLimit = mIndicatorTitles.size - 1

        //初始化indicator
        val commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = ProjectDetailIndicatorAdapter(mIndicatorTitles, project_detail_viewpager, this)
        project_detail_indicator.navigator = commonNavigator
        ViewPagerHelper.bind(project_detail_indicator, project_detail_viewpager)
    }

    override fun initData() {
        setTitle("帮助中心")
        getData()
    }

    fun getData() {
        mPresenter.requestHelpCenterMenuData()
    }

    override fun retryGetData() {

    }

    override fun getLayoutResourse(): Int {
        return R.layout.activity_help_center
    }

}
