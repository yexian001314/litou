package com.sleep.uulib.util

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import com.sleep.uulib.R


/**
 * Created by SleepYM09 on 2018/3/27.
 *
 * blog: www.sleepym09.com
 */
class AnimationUtils{
    companion object {
         fun shake(context: Context,view :View){
             val loadAnimation = AnimationUtils.loadAnimation(context, R.anim.slight_shake)
             view.startAnimation(loadAnimation)
        }
    }
}