package com.shqj.information.adapbter

import android.content.Context
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sleep.uulib.bean.IntegralCommodityBean
import com.sleep.uulib.util.GlideHelper
import com.shqj.information.R

/**
 * Created by SleepYM09 on 2018/1/18.
 *
 * blog: www.sleepym09.com
 */
class InformationCommodityAdapter(private val context: Context,
                                  layoutRes: Int): BaseQuickAdapter<IntegralCommodityBean.ItemTypeEOListBean,BaseViewHolder>(layoutRes) {
    override fun convert(helper: BaseViewHolder, item: IntegralCommodityBean.ItemTypeEOListBean) {
        Glide.with(context).applyDefaultRequestOptions(GlideHelper.getOptions(GlideHelper.ImageType.DETAILS_INFO))
                .load(item.imageUrl).into(helper.getView(R.id.iv_commodity_pic))
        helper.setText(R.id.tv_commodity_name,item.itemName)
        helper.setText(R.id.tv_commodity_price,"${item.requireoints}积分")
    }
}