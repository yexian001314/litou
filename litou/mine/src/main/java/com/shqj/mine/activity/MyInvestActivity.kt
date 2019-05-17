package com.shqj.mine.activity

import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.sleep.commonlib.adapter.BaseFragmentAdapter
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ArouterParamConstant
import com.sleep.uulib.uubase.UUBaseActivity
import com.umeng.analytics.MobclickAgent
import com.shqj.mine.R
import com.shqj.mine.adapter.MyInvestIndicatorAdapter
import com.shqj.mine.fragment.invest.InvestingFragment
import com.shqj.mine.fragment.invest.ParticipateFragment
import com.shqj.mine.fragment.invest.ReceivableFragment
import kotlinx.android.synthetic.main.mine_activity_my_invest.*
import net.lucode.hackware.magicindicator.FragmentContainerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator

/**
 * 我的投资
 */
@Route(path = ArouterConstant.MINE_MY_INVEST)
class MyInvestActivity : UUBaseActivity(), ViewPager.OnPageChangeListener {

    private lateinit var mViewPagerAdapter: BaseFragmentAdapter
    private lateinit var mFragmentContainerHelper: FragmentContainerHelper

    private var isBackFinishCurrent = true

    private val mIndicatorTitles: List<String>
        get() {
            return listOf(resources.getString(R.string.participate),
                    resources.getString(R.string.investment),
                    resources.getString(R.string.paid_back))
        }

    private val mFragments: List<Fragment>
        get() {
            return listOf(ParticipateFragment(),
                    InvestingFragment(),
                    ReceivableFragment())
        }

    override fun getLayoutResourse(): Int {
        return R.layout.mine_activity_my_invest
    }

    override fun initView() {
        setTitle(getString(R.string.title_my_invest))
        mViewPagerAdapter = BaseFragmentAdapter(mFragments, supportFragmentManager)
        my_invest_viewpager.adapter = mViewPagerAdapter
        my_invest_viewpager.offscreenPageLimit = mFragments.size - 1
        my_invest_viewpager.addOnPageChangeListener(this)

        //初始化indicator
        val commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = MyInvestIndicatorAdapter(mIndicatorTitles, my_invest_viewpager)
        my_invest_indicator.navigator = commonNavigator
        mFragmentContainerHelper = FragmentContainerHelper(my_invest_indicator)
    }

    override fun initData() {
        isBackFinishCurrent = intent.getBooleanExtra(ArouterParamConstant.IS_BACK_FINISH_CURRENT, true)
    }

    override fun retryGetData() {
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        mFragmentContainerHelper.handlePageSelected(position, false)
        when(position){
            0 ->{
                MobclickAgent.onEvent(this, "wd_wdtz_tab_zbz")
            }
            1 ->{
                MobclickAgent.onEvent(this, "wd_wdtz_tab_hkz")
            }
            2 ->{
                MobclickAgent.onEvent(this, "wd_wdtz_tab_yhk")
            }
        }
    }

    override fun onBackPressed() {
        if (isBackFinishCurrent) {
            super.onBackPressed()
        } else {
            //从投资完成也跳转过来的，点击返回启动mainActivity
            ARouter.getInstance().build(ArouterConstant.APP_MAIN_ACTIVITY).withInt(ArouterParamConstant.CHOOSE_POSITION, 1).navigation()
        }
    }
}
