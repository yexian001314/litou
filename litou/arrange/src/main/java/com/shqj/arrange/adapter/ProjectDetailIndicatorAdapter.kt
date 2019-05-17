package com.shqj.arrange.adapter

import android.content.Context
import android.graphics.Color
import android.support.v4.view.ViewPager
import com.sleep.commonlib.util.SizeUtils
import com.zhy.autolayout.utils.AutoUtils
import com.shqj.arrange.R
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView

/**
 * Created by SleepYM09 on 2017/12/18.
 *
 * blog: www.sleepym09.com
 */
class ProjectDetailIndicatorAdapter (private val mTitleDataList: List<String>?,
                                     private val mViewPager: ViewPager,private val mContext: Context): CommonNavigatorAdapter() {

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
        indicator.lineWidth = SizeUtils.dp2px(context,26f).toFloat()
        indicator.mode = LinePagerIndicator.MODE_EXACTLY
        return indicator
    }
}