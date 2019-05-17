package com.shqj.arrange.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity



/**
 * Created by SleepYM09 on 2018/2/26.
 *
 * blog: www.sleepym09.com
 */
class ReceivableAdapter(data:List<MultiItemEntity>): BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {

    init {

    }

    override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {
    }
}