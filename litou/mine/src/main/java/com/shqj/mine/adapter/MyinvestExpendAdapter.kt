package com.shqj.mine.adapter

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.shqj.mine.R
import com.sleep.commonlib.util.SpannableStringUtils
import com.sleep.commonlib.util.TimeUtils
import com.sleep.uulib.bean.InvestRecordBean
import com.sleep.uulib.bean.QueryInvestByIdBean
import com.sleep.uulib.constant.Constant
import com.sleep.uulib.util.NumberFormatUtils
import com.sleep.uulib.util.NumberUtils

/**
 * Created by SleepYM09 on 2018/2/28.
 *
 * blog: www.sleepym09.com
 */
class MyinvestExpendAdapter(val context: Context,
                            data: MutableList<MultiItemEntity>?,
                            private val listener: ExpandListener,
                            private val isPayed: Boolean
) : BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {

    init {
        addItemType(Constant.EXPEND_LEVEL_0, R.layout.item_my_invest)
        addItemType(Constant.EXPEND_LEVEL_1, R.layout.item_my_invest_sub)
    }

    var prevExpendPosition = -1

    override fun convert(helper: BaseViewHolder, item: MultiItemEntity) {
        when (helper.itemViewType) {
            Constant.EXPEND_LEVEL_0 -> {
                val data = item as InvestRecordBean.PageBean.DataBean
                if (isPayed) {
                    helper.setText(R.id.tv_rate_title, "已收利息")
                } else {
                    helper.setText(R.id.tv_rate_title, "预期收益")
                }
                //设置车型
                if (!TextUtils.isEmpty(data.subjectName)) {
                    helper.setText(R.id.tv_project_name, data.subjectName)
                }

                //设置ID
                if (!TextUtils.isEmpty(data.investOrderId)) {
                    helper.setText(R.id.tv_project_id, "NO." + data.id)
                }


                //设置预期年化收益率
                val earningRate = helper.getView<TextView>(R.id.tv_earnings_rate)
                earningRate.text = SpannableStringUtils
                        .getBuilder(NumberFormatUtils.getFormatNumber(NumberUtils.mul(data.lendRate, 100.00), 2))
                        .setProportion(1f)
                        .setForegroundColor(ContextCompat.getColor(context, R.color.color_4c4c4c))
                        .append("%")
                        .create()

                //设置投资
                val investAmount = helper.getView<TextView>(R.id.tv_invest_amount)
                investAmount.text = SpannableStringUtils
                        .getBuilder(NumberFormatUtils.getFormatNumber(data.investMoney, 2))
                        .setProportion(1f)
                        .setForegroundColor(ContextCompat.getColor(context, R.color.color_ff721f))
                        .create()

                //设置代收利息
                val interest = helper.getView<TextView>(R.id.tv_interest)
                interest.text = SpannableStringUtils
                        .getBuilder(NumberFormatUtils.getFormatNumber(data.everydayIncome, 2))
                        .setProportion(1f)
                        .setForegroundColor(ContextCompat.getColor(context, R.color.color_4c4c4c))
//                        .append("元")
                        .create()

                if (data.payType == 1) {
                    helper.setText(R.id.tv_project_pay_type, "（一次性还本付息）")
                    helper.setText(R.id.tv_pay_date_title, "回款日期")
                    helper.setText(R.id.tv_pay_date, TimeUtils.milliseconds2String(data.expiredTime, "yyyy-MM-dd"))
                    helper.getView<View>(R.id.iv_user_invest_list).visibility = View.GONE
                } else if (data.payType == 2) {
                    helper.setText(R.id.tv_project_pay_type, "（先息后本）")
                    helper.setText(R.id.tv_pay_date_title, "期数")
                    helper.setText(R.id.tv_pay_date, "${data.period}/${data.totalPeriod}")
                    val investListIcon = helper.getView<ImageView>(R.id.iv_user_invest_list)
                    investListIcon.visibility = View.VISIBLE
                    investListIcon.setOnClickListener {
                        if (!data.isExpanded) {
                            if (data.totalPeriod!=0)
                            listener.expanded(helper.adapterPosition)
                        } else {
                            if (helper.adapterPosition == prevExpendPosition) {
                                prevExpendPosition = -1
                            }
                            collapse(helper.adapterPosition)
                        }
                    }
                    if (!data.isExpanded) {
                        investListIcon.setImageResource(R.mipmap.icon_down_arrow)
                    } else {
                        investListIcon.setImageResource(R.mipmap.icon_up_arrow)
                    }
                }
            }

            Constant.EXPEND_LEVEL_1 -> {
                val data = item as QueryInvestByIdBean
                val recyclerView = helper.getView<RecyclerView>(R.id.rc_sub)
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = ItemRecyclerAdapter(context, R.layout.item_my_invest_sub_layout, data.interestVOS)
                if (isPayed) {
                    helper.getView<TextView>(R.id.tv_invest_amount_title).text = "已收本息"
                } else {
                    helper.getView<TextView>(R.id.tv_invest_amount_title).text = "应收本息"
                }
            }
        }
    }

    fun collapsePrevExpendItem(needExpandPosition: Int) {
//        ToastUtil.showToast("$prevExpendPosition   $needExpandPosition")
        if (prevExpendPosition != -1) {
            //纠正adapterPosition 以关闭正确的position  collapse()会忽略subItem但是adapterPosition不会，所以需要手动纠正
            if (needExpandPosition < prevExpendPosition) {
                collapse(prevExpendPosition + 1)
            } else {
                collapse(prevExpendPosition)
            }
        }
        //记录prevExpendPosition
        if (prevExpendPosition != -1 && needExpandPosition > prevExpendPosition) {
            prevExpendPosition = needExpandPosition - 1
        } else {
            prevExpendPosition = needExpandPosition
        }
    }

    override fun setNewData(data: MutableList<MultiItemEntity>?) {
        prevExpendPosition = -1
        super.setNewData(data)
    }

    interface ExpandListener {
        fun expanded(position: Int)
    }

    private inner class ItemRecyclerAdapter(val context: Context, @LayoutRes layoutResId: Int,
                                            data: List<QueryInvestByIdBean.InterestVOSBean>) : BaseQuickAdapter<QueryInvestByIdBean.InterestVOSBean, BaseViewHolder>(layoutResId, data) {
        override fun convert(helper: BaseViewHolder, item: QueryInvestByIdBean.InterestVOSBean) {
            //期数
            helper.getView<TextView>(R.id.tv_pay_date).text = "${item.itemId}/${item.totalPeriod}"
            //已收本息
            helper.getView<TextView>(R.id.tv_received_money).text = if (helper.adapterPosition == data.size - 1) {
                //加息券利息+利息
                val interest = NumberUtils.add(item.interest, item.extraInterest)
                //本+息
                NumberFormatUtils.getNumberWithDigital(NumberUtils.add(item.principal, interest), 2)
            } else {
                NumberFormatUtils.getNumberWithDigital(item.interest, 2)
            }
            //本金
            helper.getView<TextView>(R.id.tv_principal).text = if (helper.adapterPosition == data.size - 1) {
                NumberFormatUtils.getFormatNumberWithNoDigital(item.principal)
            } else {
                "0"
            }
            //利息
            //加息券利息+利息
            val interest = NumberUtils.add(item.interest, item.extraInterest)
            helper.getView<TextView>(R.id.tv_interest).text = NumberFormatUtils.getFormatNumber(interest, 2)
            //汇款日期
            helper.getView<TextView>(R.id.tv_payment_date).text = TimeUtils.milliseconds2String(item.payTime, "yy/MM/dd")

            if (item.state == 1) {
                helper.getView<TextView>(R.id.tv_pay_date).setTextColor(context.resources.getColor(R.color.color_969696))
                helper.getView<TextView>(R.id.tv_received_money).setTextColor(context.resources.getColor(R.color.color_969696))
                helper.getView<TextView>(R.id.tv_principal).setTextColor(context.resources.getColor(R.color.color_969696))
                helper.getView<TextView>(R.id.tv_interest).setTextColor(context.resources.getColor(R.color.color_969696))
                helper.getView<TextView>(R.id.tv_payment_date).setTextColor(context.resources.getColor(R.color.color_969696))
            } else {
                helper.getView<TextView>(R.id.tv_pay_date).setTextColor(context.resources.getColor(R.color.color_4c4c4c))
                helper.getView<TextView>(R.id.tv_received_money).setTextColor(context.resources.getColor(R.color.color_4c4c4c))
                helper.getView<TextView>(R.id.tv_principal).setTextColor(context.resources.getColor(R.color.color_4c4c4c))
                helper.getView<TextView>(R.id.tv_interest).setTextColor(context.resources.getColor(R.color.color_4c4c4c))
                helper.getView<TextView>(R.id.tv_payment_date).setTextColor(context.resources.getColor(R.color.color_4c4c4c))
            }
        }

    }
}