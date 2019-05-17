package com.example.twitterlib.wigdet.dialog

import android.view.View
import android.widget.TextView
import com.sleep.commonlib.util.StringUtil
import com.sleep.uulib.R


/**
 * Created by Sleep on 2017/8/3.
 */
class CommonLogicSetter constructor(private val posListener: View.OnClickListener?, private val negListener: View.OnClickListener?) : CommonFragmentDialog.ILogicSetter {
    var title: String = "title"
    var describe: String = "describe"
    var pos: String = "pos"
    var neg: String? = "neg"

    fun setData(title: String, describe: String, pos: String, neg: String?) {
        this.title = title
        this.describe = describe
        this.pos = pos
        this.neg = neg
    }

    override fun setLogic(dialog: CommonFragmentDialog, rootView: View) {
        val tvTitle = rootView.findViewById<TextView>(R.id.common_dialog_title)
        if (!android.text.TextUtils.isEmpty(this.title)) tvTitle.text = title
        val tvDescribe = rootView.findViewById<TextView>(R.id.common_dialog_describe)
        if (!android.text.TextUtils.isEmpty(this.describe)) tvDescribe.text = describe
        val tvNegative = rootView.findViewById<TextView>(R.id.common_dialog_negative)
        if (!android.text.TextUtils.isEmpty(this.neg)) tvNegative.text = neg
        val tvPositive = rootView.findViewById<TextView>(R.id.common_dialog_positive)
        if (!android.text.TextUtils.isEmpty(this.pos)) tvPositive.text = pos
        val line = rootView.findViewById<View>(R.id.common_dialog_line2)
        if (StringUtil.isEmpty(neg)) {
            line.visibility = View.GONE
            tvNegative.visibility = View.GONE
        }
        tvNegative.setOnClickListener({view ->
            negListener?.onClick(view)
            dialog.dismiss()
        })
        tvPositive.setOnClickListener({view ->
            posListener?.onClick(view)
            dialog.dismiss()
        })

    }
}