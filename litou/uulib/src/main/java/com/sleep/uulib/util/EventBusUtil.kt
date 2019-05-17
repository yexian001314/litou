package com.sleep.uulib.util

import com.sleep.commonlib.base.BaseEvent
import com.sleep.commonlib.base.EventCode
import com.sleep.commonlib.util.SPUtil
import com.sleep.uulib.constant.Constant
import org.simple.eventbus.EventBus

/**
 * Created by SleepYM09 on 2017/12/27.
 *
 * blog: www.sleepym09.com
 */
class EventBusUtil{
    companion object {
        /**
         * 发送登录成功的通知，并且将手机号存到sp中
         */
        fun postLoginEvent(phoneNum: String){
            SPUtil.getInstance().put(Constant.PHONE_NUM,phoneNum)
            EventBus.getDefault().post(BaseEvent<Any>(EventCode.LOGIN))
        }
    }
}