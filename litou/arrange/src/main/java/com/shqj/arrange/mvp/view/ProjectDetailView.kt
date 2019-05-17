package com.shqj.arrange.mvp.view

import com.sleep.uulib.bean.CheckInvestedBean
import com.sleep.uulib.bean.ProjectDetailBean
import com.sleep.uulib.bean.ProjectDetailLimit

/**
 * Created by SleepYM09 on 2017/12/1.
 *
 * blog: www.sleepym09.com
 */
interface ProjectDetailView {
    fun getProjectDetailSuccess(data: ProjectDetailBean)
    fun getProjectDetailLimitSuccess(data: ProjectDetailLimit)
    fun checkInvestedSuccess(data: CheckInvestedBean)
}