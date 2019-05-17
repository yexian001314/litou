package com.sleep.uulib.widget.fragmentdialog

import android.view.View
import com.example.twitterlib.wigdet.dialog.CommonFragmentDialog
import com.sleep.uulib.R

/**
 * Created by SleepYM09 on 2018/1/22.
 *
 * blog: www.sleepym09.com
 */
class PhoneCallLogicSetter(private var mString: String = "", private val posListener: View.OnClickListener?) : CommonFragmentDialog.ILogicSetter {
    fun setData() {
    }
    override fun setLogic(fragmentDialog: CommonFragmentDialog, rootView: View) {
        rootView.findViewById<View>(R.id.tv_confirm).setOnClickListener {
            posListener?.onClick(rootView)
            fragmentDialog.dismiss()
        }
        rootView.findViewById<View>(R.id.tv_cancel).setOnClickListener {
            fragmentDialog.dismiss()
        }
    }

}