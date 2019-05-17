package com.shqj.mine.mvp.view

import com.sleep.uulib.mvp.view.BaseView

/**
 * Created by SleepYM09 on 2017/12/8.
 *
 * blog: www.sleepym09.com
 */
interface SecuritySettingView: BaseView {
    fun logoutSuccess()
    fun efficacySuccess()
    fun efficacyFailure()
}