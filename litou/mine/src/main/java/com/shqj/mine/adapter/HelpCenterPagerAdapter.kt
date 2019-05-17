package com.shqj.mine.adapter

import android.animation.ValueAnimator
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sleep.uulib.bean.HelpCenterBean
import com.shqj.mine.R

/**
 * Created by SleepYM09 on 2018/3/29.
 *
 * blog: www.sleepym09.com
 */
class HelpCenterPagerAdapter(data: List<HelpCenterBean.HelpMenuListEOSBean>) : BaseQuickAdapter<HelpCenterBean.HelpMenuListEOSBean, BaseViewHolder>(R.layout.item_help_center, data) {

    override fun convert(helper: BaseViewHolder?, item: HelpCenterBean.HelpMenuListEOSBean?) {
        if (data?.indexOf(item) == data.size - 1) {
            helper?.getView<View>(R.id.view_line)?.visibility = View.GONE
        }
        val tv_content_title = helper?.getView<TextView>(R.id.tv_content_title)
        val ll_contain = helper?.getView<LinearLayout>(R.id.ll_contain)
        val tv_content = helper?.getView<TextView>(R.id.tv_content)
        val ll_content = helper?.getView<LinearLayout>(R.id.ll_content)
        val iv_arrow = helper?.getView<ImageView>(R.id.iv_arrow)
        tv_content_title?.text = item?.title
        tv_content?.text =item?.content
        ll_contain?.setOnClickListener(View.OnClickListener {
            if (ll_contain?.isSelected) {
                ll_contain.post(Runnable {
                    ll_contain?.isSelected = false
                    iv_arrow?.setImageResource(R.mipmap.icon_down_arrow)
                    val animator = ValueAnimator.ofInt(ll_content!!.height, 0)
                    animator.setDuration(300)

                    animator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                        override fun onAnimationUpdate(animation: ValueAnimator?) {
                            val animatedValue: Int = animation?.getAnimatedValue() as Int
                            val layoutParams = ll_content?.layoutParams
                            layoutParams?.height = animatedValue
                            ll_content?.layoutParams = layoutParams
                            if (animatedValue == 0) {
                                ll_content?.visibility = View.GONE
                            }
                        }
                    })
                    animator.start()
                })


            } else {
                ll_contain.post(Runnable {
                    ll_contain?.isSelected = true
                    iv_arrow?.setImageResource(R.mipmap.icon_up_arrow)
                    val height = unDisplayViewSize(ll_content!!)[1]
                    val animator: ValueAnimator = ValueAnimator.ofInt(0, height)
                    animator.setDuration(300)
                    animator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                        override fun onAnimationUpdate(animation: ValueAnimator?) {
                            val animatedValue: Int = animation?.getAnimatedValue() as Int
                            val layoutParams = ll_content.layoutParams
                            layoutParams.height = animatedValue
                            ll_content.layoutParams = layoutParams
                            ll_content?.visibility = View.VISIBLE
                        }
                    })
                    animator.start()
                })
            }
        })
    }

    fun unDisplayViewSize(view: View): IntArray {
        val size = IntArray(2)
        val width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED)
        val height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED)
        view.measure(width, height)
        size[0] = view.measuredWidth
        size[1] = view.measuredHeight
        return size
    }
}
