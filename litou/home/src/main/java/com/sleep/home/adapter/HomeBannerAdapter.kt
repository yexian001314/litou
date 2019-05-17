package com.sleep.home.adapter

import android.content.Context
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.sleep.uulib.bean.HomeBannerBean
import com.sleep.uulib.util.GlideHelper

/**
 * Created by SleepYM09 on 2017/10/25.
 *
 * blog: www.sleepym09.com
 */
class HomeBannerAdapter(val context: Context) : BGABanner.Adapter<ImageView, HomeBannerBean.BannerListBean> {

    override fun fillBannerItem(banner: BGABanner?, itemView: ImageView?, model: HomeBannerBean.BannerListBean, position: Int) {
        Glide.with(context)
                .applyDefaultRequestOptions(GlideHelper.getOptions(GlideHelper.ImageType.HOME_BANNER))
                .load(model.pic_url)
                .into(itemView)
    }
}