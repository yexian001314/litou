package com.sleep.uulib.mvp.view

/**
 * Created by SleepYM09 on 2018/1/8.
 *
 * blog: www.sleepym09.com
 */
interface CommonView{
    /**
     * 网络请求完成回调
     */
    fun refreshSuccess()

    /**
     * 网络请求失败
     */
    fun refreshFailure()
}