package com.shqj.arrange.adapter

import android.content.Context
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sleep.uulib.bean.ProjectDetailBean
import com.sleep.uulib.util.GlideHelper
import com.shqj.arrange.R

/**
 * Created by SleepYM09 on 2017/12/25.
 *
 * blog: www.sleepym09.com
 */
class RelateInformationAdapter(private val context: Context,
                               layoutRes: Int,
                               private val loanType: String): BaseQuickAdapter<ProjectDetailBean.ImageInfo,BaseViewHolder>(layoutRes) {
    override fun convert(helper: BaseViewHolder?, item: ProjectDetailBean.ImageInfo?) {
        if(loanType == "1"){
            Glide.with(context).applyDefaultRequestOptions(GlideHelper.getOptions(GlideHelper.ImageType.DETAILS_INFO))
                    .load(item?.uploadImage).into(helper?.getView(R.id.iv_img))
        }else{
            Glide.with(context).applyDefaultRequestOptions(GlideHelper.getOptions(GlideHelper.ImageType.DETAILS_INFO))
                    .load(item?.uploadImage).into(helper?.getView(R.id.iv_img))
        }
    }
}