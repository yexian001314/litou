package com.sleep.uulib.widget

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.sleep.commonlib.util.SizeUtils

/**
 * Created by SleepYM09 on 2017/12/25.
 *
 * blog: www.sleepym09.com
 */
class GridLayoutItemDecoration(private val context: Context, private val dpValue: Float) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val childPosition = parent.getChildAdapterPosition(view)
        if (childPosition % 2 != 0) {
            outRect.left = SizeUtils.dp2px(context, dpValue)
        }

        if(childPosition >= 2){
            outRect.top = SizeUtils.dp2px(context, dpValue)
        }else{
            outRect.top = 0
        }
    }
}