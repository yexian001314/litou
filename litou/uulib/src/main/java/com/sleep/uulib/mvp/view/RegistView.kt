package com.sleep.uulib.mvp.view

import com.sleep.uulib.bean.CheckPhoneBean
import com.sleep.uulib.bean.RegisterInfo

/**
 * Created by SleepYM09 on 2017/11/13.
 *
 * blog: www.sleepym09.com
 */
interface RegistView {
    fun registSuccess(data: RegisterInfo)
    fun checkSuccess(data: CheckPhoneBean)
}