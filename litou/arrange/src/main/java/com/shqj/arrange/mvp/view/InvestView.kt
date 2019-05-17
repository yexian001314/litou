package com.shqj.arrange.mvp.view

import com.sleep.uulib.bean.InvestResultBean

/**
 * Created by SleepYM09 on 2017/12/14.
 *
 * blog: www.sleepym09.com
 */
interface InvestView {
    fun investSuccess(data: InvestResultBean)
    fun investFailure()
}