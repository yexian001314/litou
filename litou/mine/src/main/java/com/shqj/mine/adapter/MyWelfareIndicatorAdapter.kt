package com.shqj.mine.adapter

import android.content.Context
import android.graphics.Color
import android.support.v4.view.ViewPager
import com.zhy.autolayout.utils.AutoUtils
import com.shqj.mine.R
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView

/**
 * Created by SleepYM09 on 2017/12/12.
 *
 * blog: www.sleepym09.com
 */
class MyWelfareIndicatorAdapter(private val mTitleDataList: List<String>?,
                                private val mViewPager: ViewPager, private val mContext: Context): CommonNavigatorAdapter() {

    override fun getCount(): Int {
        return mTitleDataList?.size ?: 0
    }

    override fun getTitleView(context: Context, index: Int): IPagerTitleView {
        val colorTransitionPagerTitleView = ColorTransitionPagerTitleView(context)
        colorTransitionPagerTitleView.normalColor = Color.BLACK
        colorTransitionPagerTitleView.textSize = 14f
        colorTransitionPagerTitleView.selectedColor = mContext.resources.getColor(R.color.colorAccent)
        colorTransitionPagerTitleView.text = mTitleDataList!![index]
        AutoUtils.auto(colorTransitionPagerTitleView)
        colorTransitionPagerTitleView.setOnClickListener({mViewPager.currentItem = index})
        return colorTransitionPagerTitleView
    }

    override fun getIndicator(context: Context): IPagerIndicator {
        val indicator = LinePagerIndicator(context)
        indicator.setColors(mContext.resources.getColor(R.color.colorAccent))
        indicator.mode = LinePagerIndicator.MODE_MATCH_EDGE
        return indicator
    }
}