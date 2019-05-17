package com.shqj.mine.activity

import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.sleep.commonlib.adapter.BaseFragmentAdapter
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.uubase.UUBaseActivity
import com.shqj.mine.R
import com.shqj.mine.adapter.PaymentScheduleIndicatorAdapter
import com.shqj.mine.fragment.schedule.AfterThreeMonthFragment
import com.shqj.mine.fragment.schedule.CurrentMonthFragment
import com.shqj.mine.fragment.schedule.InThreeMonthFragment
import kotlinx.android.synthetic.main.mine_activity_payment_schedule.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator

/**
 * 回款日程
 */
@Route(path = ArouterConstant.MINE_PAYMENT_SCHEDULE)
class PaymentScheduleActivity : UUBaseActivity() {

    private lateinit var mViewPagerAdapter: BaseFragmentAdapter

    private val mIndicatorTitles: List<String>
        get() {
            return listOf(resources.getString(R.string.schedule_in_one_month),
                    resources.getString(R.string.schedule_in_three_month),
                    resources.getString(R.string.schedule_after_three_month))
        }

    private val mFragments: List<Fragment>
        get() {
            return listOf(CurrentMonthFragment(),
                    InThreeMonthFragment(),
                    AfterThreeMonthFragment())
        }

    override fun getLayoutResourse(): Int {
        return R.layout.mine_activity_payment_schedule
    }

    override fun initView() {
        setTitle(getString(R.string.title_payment_schedule))
        mViewPagerAdapter = BaseFragmentAdapter(mFragments, supportFragmentManager)
        schedule_viewpager.adapter = mViewPagerAdapter
        schedule_viewpager.offscreenPageLimit = mIndicatorTitles.size - 1

        //初始化indicator
        val commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = PaymentScheduleIndicatorAdapter(mIndicatorTitles, schedule_viewpager, this)
        schedule_indicator.navigator = commonNavigator
        ViewPagerHelper.bind(schedule_indicator, schedule_viewpager)
    }

    override fun initData() {
    }

    override fun retryGetData() {
    }

}
