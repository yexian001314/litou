package com.sleep.uulib.widget

import android.view.View
import com.chad.library.adapter.base.BaseViewHolder
import com.zhy.autolayout.utils.AutoUtils

/**
 * Created by SleepYM09 on 2017/11/9.
 *
 * blog: www.sleepym09.com
 */
class AutoViewHolder(view: View) : BaseViewHolder(view) {
    init {
        AutoUtils.auto(view)
    }
}