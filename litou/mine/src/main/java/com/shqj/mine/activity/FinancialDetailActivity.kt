package com.shqj.mine.activity

import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.sleep.commonlib.adapter.BaseFragmentAdapter
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.uubase.UUBaseActivity
import com.shqj.mine.R
import com.shqj.mine.adapter.MyInvestIndicatorAdapter
import com.shqj.mine.fragment.financial.AllTransactionsHistoryFragment
import com.shqj.mine.fragment.financial.InvestHistoryFragment
import com.shqj.mine.fragment.financial.RechargeHistoryFragment
import com.shqj.mine.fragment.financial.WithdrawHistoryFragment
import kotlinx.android.synthetic.main.mine_activity_financial_detail.*
import net.lucode.hackware.magicindicator.FragmentContainerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator

/**
 * 资金明细
 */
@Route(path = ArouterConstant.MINE_FINANCIAL_DETAIL)
class FinancialDetailActivity : UUBaseActivity(), ViewPager.OnPageChangeListener {

    private lateinit var mViewPagerAdapter: BaseFragmentAdapter
    private lateinit var mFragmentContainerHelper: FragmentContainerHelper

    private val mIndicatorTitles: List<String>
        get() {
            return listOf(resources.getString(R.string.all_transactions),
                    resources.getString(R.string.recharge_record),
                    resources.getString(R.string.withdraw_record),
                    resources.getString(R.string.invest_record))
        }

    private val mIndicatorRes: List<Int>
        get() {
            return listOf(R.drawable.selector_tab_all_record,
                    R.drawable.selector_tab_recharge_record,
                    R.drawable.selector_tab_withdraw_record,
                    R.drawable.selector_tab_invest_record)
        }

    private val mFragments: List<Fragment>
        get() {
            return listOf(AllTransactionsHistoryFragment(),
                    RechargeHistoryFragment(),
                    WithdrawHistoryFragment(),
                    InvestHistoryFragment())
        }

    override fun getLayoutResourse(): Int {
        return R.layout.mine_activity_financial_detail
    }

    override fun initView() {
        setTitle(getString(R.string.title_financial_detail))
        mViewPagerAdapter = BaseFragmentAdapter(mFragments, supportFragmentManager)
        financial_details_viewpager.adapter = mViewPagerAdapter
        financial_details_viewpager.offscreenPageLimit = mIndicatorRes.size - 1
        financial_details_viewpager.addOnPageChangeListener(this)

        //初始化indicator
        val commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = MyInvestIndicatorAdapter(mIndicatorTitles, financial_details_viewpager)
        financial_details_indicator.navigator = commonNavigator
        mFragmentContainerHelper = FragmentContainerHelper(financial_details_indicator)
    }

    override fun initData() {
    }

    override fun retryGetData() {
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        mFragmentContainerHelper.handlePageSelected(position, false)
    }

}
