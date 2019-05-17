package com.shqj.information.mvp.view

import com.sleep.uulib.bean.AdviceFeedbackBean

/**
 * Created by SleepYM09 on 2017/12/13.
 *
 * blog: www.sleepym09.com
 */
interface FeedbackView {
    fun submitFeedbackSuccess(data: AdviceFeedbackBean)
}