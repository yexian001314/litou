package com.shqj.mine.mvp.view

import com.sleep.uulib.bean.ModifyTradingPwdBean
import com.sleep.uulib.mvp.view.BaseView

/**
 * Created by SleepYM09 on 2017/12/11.
 *
 * blog: www.sleepym09.com
 */
interface ModifyTradingPwdView: BaseView {
    fun modifySuccess(data: ModifyTradingPwdBean)
}