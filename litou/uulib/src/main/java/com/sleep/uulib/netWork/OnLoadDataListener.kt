package com.sleep.uulib.netWork


/**
 * Created by SleepYM09 on 2017/10/18.
 *
 * blog: www.sleepym09.com
 */
interface OnLoadDataListener<T> {
    fun onFailure(e: Throwable?, errorCode: Int)
    fun onSuccess(data: T)
}