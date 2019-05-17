package com.shqj.mine.activity

import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.sleep.commonlib.adapter.BaseFragmentAdapter
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.uubase.UUBaseActivity
import com.shqj.mine.R
import com.shqj.mine.adapter.HomeIndicatorAdapter
import com.shqj.mine.fragment.welfare.RedPacketFragment
import com.shqj.mine.fragment.welfare.TicketFragment
import kotlinx.android.synthetic.main.mine_activity_my_welfare.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator

@Route(path = ArouterConstant.MINE_MY_WELFARE)
class MyWelfareActivity : UUBaseActivity(), HomeIndicatorAdapter.OnItemClickListener {
    override fun onItemClickListener(position: Int) {
        choosePosition=position
        my_welfare_viewpager.currentItem=position
    }

    private var choosePosition:Int = 0
    private lateinit var mViewPagerAdapter: BaseFragmentAdapter

    private val mIndicatorTitles: List<String>
        get() {
            return listOf(resources.getString(R.string.my_welfare_red_packet),
                    resources.getString(R.string.my_welfare_ticket))
        }
    private val mResIcon: List<Int>
        get() {
            return listOf(
                    R.drawable.selector_mine_redbag,
                    R.drawable.selector_mine_ticket)

        }
    private val mFragments: List<Fragment>
        get() {
            return listOf(RedPacketFragment(),
                    TicketFragment())
        }

    override fun getLayoutResourse(): Int {
        return R.layout.mine_activity_my_welfare
    }

    override fun initView() {
        setTitle(getString(R.string.title_my_welfare))
        mViewPagerAdapter = BaseFragmentAdapter(mFragments, supportFragmentManager)
        my_welfare_viewpager.adapter = mViewPagerAdapter
        my_welfare_viewpager.offscreenPageLimit = mIndicatorTitles.size - 1

        //初始化indicator
        val commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = true
//        commonNavigator.adapter = MyWelfareIndicatorAdapter(mIndicatorTitles,my_welfare_viewpager, this)
        commonNavigator.adapter = HomeIndicatorAdapter(mResIcon,mIndicatorTitles, this)
        my_welfare_indicator.navigator = commonNavigator
        ViewPagerHelper.bind(my_welfare_indicator, my_welfare_viewpager)
        my_welfare_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                my_welfare_indicator.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                my_welfare_indicator.onPageSelected(position)
                choosePosition = position
            }

            override fun onPageScrollStateChanged(state: Int) {
                my_welfare_indicator.onPageScrollStateChanged(state)
            }
        })
    }

    override fun initData() {
    }

    override fun retryGetData() {
    }

}
