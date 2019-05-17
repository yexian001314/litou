package com.sleep.uulib.mvp.view

import com.sleep.uulib.bean.CheckPhoneBean

/**
 * Created by SleepYM09 on 2017/11/8.
 *
 * blog: www.sleepym09.com
 */
interface LoginStepOnView : BaseView {
    fun checkSuccess(data: CheckPhoneBean)
}