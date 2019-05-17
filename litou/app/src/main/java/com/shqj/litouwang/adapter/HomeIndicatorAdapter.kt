package com.shqj.litouwang.adapter

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.shqj.litouwang.R
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView


/**
 * Created by SleepYM09 on 2017/10/24.
 *
 * blog: www.sleepym09.com
 */
class HomeIndicatorAdapter(private var mIndicatorRes: List<Int>,
                           private var mTitles: List<String>,
                           private var listener: OnItemClickListener
) : CommonNavigatorAdapter() {
    override fun getCount(): Int {
        return mTitles.size
    }

    override fun getTitleView(context: Context, index: Int): IPagerTitleView {
        val commonPagerTitleView = CommonPagerTitleView(context)
        // load custom layout
        val customLayout = LayoutInflater.from(context).inflate(R.layout.app_item_home_indicator, null)
        val titleImg = customLayout.findViewById<ImageView>(R.id.title_img)
        val titleText = customLayout.findViewById<TextView>(R.id.title_text)
        titleImg.setImageResource(mIndicatorRes[index])
        titleText.text = mTitles[index]
        commonPagerTitleView.setContentView(customLayout)

        commonPagerTitleView.onPagerTitleChangeListener = object : CommonPagerTitleView.OnPagerTitleChangeListener {

            override fun onSelected(index: Int, totalCount: Int) {
                titleText.setTextColor(context.resources.getColor(R.color.colorAccent))
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

        commonPagerTitleView.setOnClickListener { listener.onItemClickListener(index) }

        return commonPagerTitleView
    }


    override fun getIndicator(p0: Context?): IPagerIndicator? {
        return null
    }

    /**
     * 首页因为三四条目需要判断是否登录，所以要在点击的时候就判断，不然会走OnResum()导致弹出重新登录对话框，
     * 这里通过listener将点击事件传递出去交给MainActivity自己处理
     */
    interface OnItemClickListener {
        fun onItemClickListener(position: Int)
    }
}