package com.sleep.home.mvp.view

import com.sleep.uulib.bean.HomeAnnocementsBean
import com.sleep.uulib.bean.HomeBannerBean
import com.sleep.uulib.bean.HomeFinanceListBean
import com.sleep.uulib.mvp.view.BaseView

/**
 * Created by SleepYM09 on 2017/10/25.
 *
 * blog: www.sleepym09.com
 */
interface HomeView : BaseView {
    fun getHomeBannerSuccess(homeBannerBean: HomeBannerBean)
    /**
     * 设置新手标数据
     */
    fun getHomeFinancingSuccess(data: HomeFinanceListBean)

    fun getHOmeAnnocementSuccess(data: HomeAnnocementsBean)
}