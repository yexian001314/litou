package com.shqj.information.mvp.view

import com.sleep.uulib.bean.IntegralCommodityBean
import com.sleep.uulib.bean.IsSignedBean
import com.sleep.uulib.bean.SignBean

/**
 * Created by SleepYM09 on 2017/12/13.
 *
 * blog: www.sleepym09.com
 */
interface InformationView {
    fun queryUserIsSignSuccess(data: IsSignedBean)
    fun userSignSuccess(data: SignBean)
    fun getIntegralCommoditySuccess(data: IntegralCommodityBean)
}