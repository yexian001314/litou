package com.sleep.uulib.util

import android.widget.TextView
import com.sleep.commonlib.util.SpannableStringUtils

/**
 * Created by SleepYM09 on 2017/12/17.
 *
 * blog: www.sleepym09.com
 */
class BidUtil {
    companion object {
        fun setBidRateText(targetView: TextView, baseRate: String, extraRate: String) {
            val extraRateText = if ("0.0" == extraRate || extraRate.isEmpty()) {
                "%"
            } else {
                "% + $extraRate%"
            }
            targetView.text = SpannableStringUtils
                    .getBuilder(baseRate)
                    .append(extraRateText)
                    .setProportion(0.5f)
                    .create()
        }
    }
}