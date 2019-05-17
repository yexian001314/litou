package com.sleep.uulib.widget.fragmentdialog

import android.view.View
import android.widget.TextView
import com.example.twitterlib.wigdet.dialog.CommonFragmentDialog
import com.sleep.uulib.R

/**
 * Created by SleepYM09 on 2018/1/22.
 *
 * blog: www.sleepym09.com
 */
class HelpCenterLogicSetter(private var mString: String = "", private val posListener: View.OnClickListener?) : CommonFragmentDialog.ILogicSetter {
    override fun setLogic(fragmentDialog: CommonFragmentDialog, rootView: View) {
        rootView.findViewById<TextView>(R.id.tv_number_phone).text = mString
        rootView.findViewById<View>(R.id.tv_confirm).setOnClickListener {
            posListener?.onClick(rootView)
            fragmentDialog.dismiss()
        }
        rootView.findViewById<View>(R.id.tv_cancel).setOnClickListener {
            fragmentDialog.dismiss()
        }
    }

}