package com.sleep.uulib.mvp.view

import com.sleep.uulib.bean.QueryBankBean
import com.sleep.uulib.bean.QueryUserInfoBean

/**
 * Created by SleepYM09 on 2017/12/6.
 *
 * blog: www.sleepym09.com
 */
interface QueryUserInfoView {
    fun queryUserInfoFailure(e: Throwable?, errorCode: Int)
    fun queryUserInfoSuccess(data: QueryUserInfoBean)
    fun queryBankFailure(e: Throwable?, errorCode: Int)
    fun queryBankSuccess(data: QueryBankBean)
}