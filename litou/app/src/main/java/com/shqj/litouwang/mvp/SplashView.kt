package com.shqj.litouwang.mvp

import com.sleep.uulib.bean.SplashBannerBean

/**
 * Created by SleepYM09 on 2018/3/15.
 *
 * blog: www.sleepym09.com
 */
interface SplashView {
    fun getStartBannerSuccess(data: SplashBannerBean)
    fun getStartBannerFailure()
}