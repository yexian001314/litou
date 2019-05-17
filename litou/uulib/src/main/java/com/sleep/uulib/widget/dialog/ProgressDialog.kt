package com.sleep.uulib.widget.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import com.sleep.uulib.R



/**
 * Created by SleepYM09 on 2017/10/26.
 *
 * blog: www.sleepym09.com
 */
class ProgressDialog(val canCancel: Boolean,context: Context) : Dialog(context, R.style.progress_dialog) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_progress_dialog)
        setCancelable(canCancel)
        window.decorView.setPadding(0,0,0,0)
        window.decorView.setBackgroundColor(context.resources.getColor(R.color.color_00000000))
        window.setGravity(Gravity.CENTER)
        val lp = window.attributes
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = lp
    }
}