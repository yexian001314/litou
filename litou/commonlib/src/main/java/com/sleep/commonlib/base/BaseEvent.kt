package com.sleep.commonlib.base

/**
 * Created by SleepYM09 on 2017/10/31.
 *
 * blog: www.sleepym09.com
 */
data class BaseEvent<T>(val eventCode: EventCode,val eventData: T?){
    constructor(eventCode: EventCode):this(eventCode,null)
}