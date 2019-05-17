package com.sleep.uulib.widget.fragmentdialog

import android.view.View
import com.example.twitterlib.wigdet.dialog.CommonFragmentDialog
import com.sleep.uulib.R

/**
 * Created by Sleep on 2017/7/18.
 */
class PayTypeQuestionDialogLogicSetter: CommonFragmentDialog.ILogicSetter {

    override fun setLogic(fragmentDialog: CommonFragmentDialog, rootView: View) {
        rootView.findViewById<View>(R.id.tv_confirm).setOnClickListener {
            fragmentDialog.dismiss()
        }
    }
}