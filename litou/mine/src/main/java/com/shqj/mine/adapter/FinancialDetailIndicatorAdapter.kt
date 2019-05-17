package com.shqj.mine.adapter

import android.content.Context
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.shqj.mine.R
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView

/**
 * Created by SleepYM09 on 2017/12/11.
 *
 * blog: www.sleepym09.com
 */
class FinancialDetailIndicatorAdapter(private var mIndicatorRes : List<Int>,
                                      private var mTitles : List<String>,
                                      private var mViewPager : ViewPager
) : CommonNavigatorAdapter() {
    override fun getCount(): Int {
        return mTitles.size
    }

    override fun getTitleView(context: Context, index: Int): IPagerTitleView {
        val commonPagerTitleView = CommonPagerTitleView(context)
        // load custom layout
        val customLayout = LayoutInflater.from(context).inflate(R.layout.financial_detail_item_indicator, null)
        val titleImg = customLayout.findViewById<ImageView>(R.id.title_img)
        val titleText = customLayout.findViewById<TextView>(R.id.title_text)
        titleImg.setImageResource(mIndicatorRes[index])
        titleText.text = mTitles[index]
        commonPagerTitleView.setContentView(customLayout)

        commonPagerTitleView.onPagerTitleChangeListener = object : CommonPagerTitleView.OnPagerTitleChangeListener {

            override fun onSelected(index: Int, totalCount: Int) {
                titleText.setTextColor(context.resources.getColor(R.color.black))
                titleImg.isSelected = true
            }

            override fun onDeselected(index: Int, totalCount: Int) {
                titleText.setTextColor(context.resources.getColor(R.color.color_b3b3b3))
                titleImg.isSelected = false
            }

            override fun onLeave(index: Int, totalCount: Int, leavePercent: Float, leftToRight: Boolean) {

            }

            override fun onEnter(index: Int, totalCount: Int, enterPercent: Float, leftToRight: Boolean) {

            }
        }

        commonPagerTitleView.setOnClickListener{mViewPager.currentItem = index}

        return commonPagerTitleView
    }


    override fun getIndicator(p0: Context?): IPagerIndicator? {
        return null
    }

}