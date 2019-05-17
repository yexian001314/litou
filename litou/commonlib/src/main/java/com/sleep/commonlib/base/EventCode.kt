package com.sleep.commonlib.base

/**
 * Created by SleepYM09 on 2017/10/31.
 *
 * blog: www.sleepym09.com
 */
enum class EventCode {
    //他端登录
    OTHER_LOGIN,
    //登出
    LOGOUT,
    //登录
    LOGIN,
    //投资成功
    INVEST_SUCCESS,
    INVEST_FAILED,
    //更换头像
    CHANGE_HEAD_PORTRAIT,
    //继续充值
    CONTINUE_RECHARGE,
    //充值成功
    RECHARGE_SUCCESS,
    //提现成功
    WITHDRAW_SUCCESS,
    //系统维护
    SYSTEM_MAINTAIN
}