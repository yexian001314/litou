package com.shqj.arrange.adapter

import android.support.annotation.LayoutRes
import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sleep.commonlib.util.StringUtil
import com.sleep.commonlib.util.TimeUtils
import com.sleep.uulib.bean.InvestRecordListBean
import com.sleep.uulib.constant.Constant
import com.shqj.arrange.R
import java.text.DecimalFormat

/**
 * Created by SleepYM09 on 2017/12/18.
 *
 * blog: www.sleepym09.com
 */
class InvestRecordAdapter (@LayoutRes layoutResId: Int, data: List<InvestRecordListBean.PageBean.DataBean>) : BaseQuickAdapter<InvestRecordListBean.PageBean.DataBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(viewHolder: BaseViewHolder, item: InvestRecordListBean.PageBean.DataBean) {
        if (!TextUtils.isEmpty(item.phoneNumber)) {
            viewHolder.setText(R.id.tv_phoneNum, StringUtil.dimStrings(item.phoneNumber, 3, 4))
        }
        //投资金额
        val df = DecimalFormat(Constant.DEFAULT_MONEY_FORMAT)
        viewHolder.setText(R.id.tv_invest_amount, df.format(item.investMoney))
        //时间
        viewHolder.setText(R.id.tv_invest_time, TimeUtils.milliseconds2String(item.createTime))
    }
}