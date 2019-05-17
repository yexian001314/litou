package com.shqj.mine.fragment.welfare


import android.support.v4.content.ContextCompat
import android.widget.RelativeLayout
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sleep.commonlib.util.SpannableStringUtils
import com.sleep.commonlib.util.TimeUtils
import com.sleep.uulib.bean.TicketBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ArouterParamConstant
import com.sleep.uulib.constant.Constant
import com.sleep.uulib.util.NumberFormatUtils
import com.sleep.uulib.util.NumberUtils
import com.sleep.uulib.uubase.LayoutManagerType
import com.sleep.uulib.uubase.UUBaseListFragment
import com.shqj.mine.R
import com.shqj.mine.mvp.presenter.TicketPresenter
import kotlinx.android.synthetic.main.fragment_red_packet.*


/**
 * 我的优惠（加息券）
 */
class TicketFragment : UUBaseListFragment<TicketBean.ProjectsBean.DataBean,TicketBean>() {

    private var REQUEST_PAGE = 0
    private lateinit var mPresenter: TicketPresenter

    override fun initItemLayout() {
        super.mRecyclerView = red_packet_recycler
        super.mSmartLayout = red_packet_refresh
        setListType(LayoutManagerType.LINEAR_LAYOUT_MANAGER, true)
        setLayoutResId(R.layout.item_my_invest_envelopes)
    }

    override fun getLayoutResourse(): Int {
        return R.layout.fragment_red_packet
    }

    override fun initData() {
        super.initData()
        mPresenter = TicketPresenter(this, mStateManager)
        mAdapter?.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            ARouter.getInstance().build(ArouterConstant.APP_MAIN_ACTIVITY).withInt(ArouterParamConstant.CHOOSE_POSITION, 1).navigation()
        }
    }

    override fun retryGetData() {
        mStateManager.showLoading()
        getData()
    }

    override fun onListRefresh() {
        REQUEST_PAGE = 0
        getData()
    }

    override fun onListLoadMore() {
        REQUEST_PAGE++
        getData()
    }

    override fun loadData(data: TicketBean) {
        super.loadData(data)
        mAdapter?.setNewData(sortData(data.projects.data))
    }

    override fun loadMoreData(data: TicketBean) {
        super.loadMoreData(data)
        val list = mutableListOf<TicketBean.ProjectsBean.DataBean>()
        list.addAll(mAdapter?.data!!)
        list.addAll(data.projects.data)
        mAdapter?.setNewData(sortData(list))
    }

    override fun loadNoMoreData(data: TicketBean) {
        val list = mutableListOf<TicketBean.ProjectsBean.DataBean>()
        list.addAll(mAdapter?.data!!)
        list.addAll(data.projects.data)
        mAdapter?.setNewData(sortData(list))
        super.loadNoMoreData(data)
    }

    override fun getCurrentRequestPage(): Int {
        return REQUEST_PAGE
    }

    override fun commonHolder(viewHolder: BaseViewHolder, data: TicketBean.ProjectsBean.DataBean) {
        //设置红包的背景以及设置标题字段
        val relativeLayout = viewHolder.getView<RelativeLayout>(R.id.rl_root_view)
        val type = viewHolder.getView<TextView>(R.id.tv_envelopes)
        if (data.rateStatus == 1) {
            relativeLayout.setBackgroundResource(R.mipmap.ticket_envelope_available)
            type.setTextColor(ContextCompat.getColor(context, R.color.black))
        } else {
            relativeLayout.setBackgroundResource(R.mipmap.bg_not_choose_red_packet)
            type.setTextColor(ContextCompat.getColor(context, R.color.black))
        }

        //  红包描述（类型）
        type.text = data.description

        //设置红包金额  ¥20
        val envelopesAmount = viewHolder.getView<TextView>(R.id.tv_envelopes_amount)
        envelopesAmount.text = SpannableStringUtils
                .getBuilder("+")
                .setProportion(0.8f)
                .setForegroundColor(
                        if (data.rateStatus == 1)
                            ContextCompat.getColor(context, R.color.color_e99818)
                        else
                            ContextCompat.getColor(context, R.color.black))
                .append(NumberFormatUtils.getFormatNumber(NumberUtils.mul(data.lendRate, 100.00), 1)+"%")
                .setProportion(1.6f)
                .setBold()
                .setForegroundColor(
                        if (data.rateStatus == 1)
                            ContextCompat.getColor(context, R.color.color_e99818)
                        else
                            ContextCompat.getColor(context, R.color.black))
                .create()


        //设置是否失效的显示
        val envelopeState = viewHolder.getView<TextView>(R.id.tv_envelopes_state)
        var statue = ""
        if (data.rateStatus == 1) {
            statue = "未使用"
        } else if (data.rateStatus == 2) {
            statue = "使用中"
        } else if (data.rateStatus == 3) {
            statue = "已使用"
        } else if (data.rateStatus == 4) {
            statue = "已过期"
        }
        envelopeState.text = SpannableStringUtils
                .getBuilder(statue)
                .setForegroundColor(
                        if (data.rateStatus == 1)
                            ContextCompat.getColor(context, R.color.color_e99818)
                        else
                            ContextCompat.getColor(context, R.color.color_676767))
                .create()


        //设置投资额度的使用条件
        val useRule = viewHolder.getView<TextView>(R.id.tv_envelopes_use_condition)
        val useLimit = StringBuilder(NumberFormatUtils.getFormatNumberWithNoDigital(data.useRule.toDouble()))
        if (data.useRuleInvestPeriod != 0) {
            //如果有适用标的周期（use_rule_invest_period）就拼接上去
            useLimit.append("," + data.useRuleInvestPeriod + "天标")
        }
        useLimit.append("可用")
        useRule.text = SpannableStringUtils
                .getBuilder("投资额≥")
                .setForegroundColor(
                        if (data.rateStatus == 1)
                            ContextCompat.getColor(context, R.color.black)
                        else
                            ContextCompat.getColor(context, R.color.black))
                .append(useLimit)
                .setForegroundColor(
                        if (data.rateStatus == 1)
                            ContextCompat.getColor(context, R.color.black)
                        else
                            ContextCompat.getColor(context, R.color.black))
                .create()

        //设置有效期
        val dueTime = viewHolder.getView<TextView>(R.id.tv_available_time)
        dueTime.text = SpannableStringUtils
                .getBuilder("有效期至" + TimeUtils.milliseconds2String(data.dueTime, Constant.DEFAULT_WELFARE_DATE_FORMAT))
                .setForegroundColor(
                        if (data.rateStatus == 1)
                            ContextCompat.getColor(context, R.color.color_676767)
                        else
                            ContextCompat.getColor(context, R.color.color_676767))
                .create()

        //设置使用余多少天的标
        val timeLimit = viewHolder.getView<TextView>(R.id.tv_time_limit)
        timeLimit.text = SpannableStringUtils
                .getBuilder("适用于" + data.useRuleInvestPeriod + "天以上的标")
                .setForegroundColor(
                        if (data.rateStatus == 1)
                            ContextCompat.getColor(context, R.color.black)
                        else
                            ContextCompat.getColor(context, R.color.black))
                .create()
    }

    private fun getData() {
        mPresenter.getTicketData(REQUEST_PAGE)
    }

    /**
     * 排序过期红包
     * @param datas
     * @return
     */
    private fun sortData(datas: List<TicketBean.ProjectsBean.DataBean>): List<TicketBean.ProjectsBean.DataBean> {
        //未过期
        val resultData = datas
                .filter { System.currentTimeMillis() <= it.dueTime }
                .toMutableList()
        for (data in datas) {
            if (System.currentTimeMillis() > data.dueTime) {
                //已过期
                if (data.rateStatus == 1) {
                    data.rateStatus = 4
                }
                resultData.add(resultData.size, data)
            }
        }
        return resultData
    }

}
