package com.shqj.mine.mvp.view

import com.sleep.uulib.bean.RechargeLimitBean
import com.sleep.uulib.bean.WithdrawLimitBean
import com.sleep.uulib.mvp.view.BaseView

/**
 * Created by SleepYM09 on 2017/12/8.
 *
 * blog: www.sleepym09.com
 */
interface WithdrawView:BaseView {
    fun refreshFreeLimit(data: WithdrawLimitBean)
    fun  loadData(data: RechargeLimitBean)
}