package com.shqj.mine.mvp

import com.sleep.uulib.bean.QueryInvestByIdBean

/**
 * Created by SleepYM09 on 2018/2/28.
 *
 * blog: www.sleepym09.com
 */
interface QueryInvestListCallBack {
    fun queryInvestListByOrderIdSuccess(data: QueryInvestByIdBean)
}