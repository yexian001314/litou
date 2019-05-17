package com.shqj.mine.activity

import android.content.Intent
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sleep.commonlib.util.SpannableStringUtils
import com.sleep.commonlib.util.TimeUtils
import com.sleep.uulib.bean.ChooseWelfareBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ArouterParamConstant
import com.sleep.uulib.constant.Constant
import com.sleep.uulib.util.NumberFormatUtils
import com.sleep.uulib.uubase.LayoutManagerType
import com.sleep.uulib.uubase.UUBaseListActivity
import com.shqj.mine.R
import com.shqj.mine.mvp.presenter.ChooseMyWelfarePresenter
import kotlinx.android.synthetic.main.activity_choose_my_welfare.*

/**
 * 投资时选择福利
 *
 * 参数
 */
@Route(path = ArouterConstant.MINE_CHOOSE_WELFARE)
class ChooseMyWelfareActivity : UUBaseListActivity<ChooseWelfareBean.DatasBean, ChooseWelfareBean>() {

    private var REQUEST_PAGE = 0
    private lateinit var mPresenter: ChooseMyWelfarePresenter
    //传递过来的订单Id
    private var orderId = ""
    //传递过来的投资金额
    private var amount: Int = 0
    //传递过来的项目周期
    private var investperiod: Int = 0
    private var subjctType: Int = 0

    override fun getLayoutResourse(): Int {
        return R.layout.activity_choose_my_welfare
    }

    override fun initItemLayout() {
        setTitle(getString(R.string.title_my_welfare))
        super.mRecyclerView = choose_welfare_recycler
        super.mSmartLayout = choose_welfare_refresh
        setListType(LayoutManagerType.LINEAR_LAYOUT_MANAGER, true)
        setLayoutResId(R.layout.item_my_invest_envelopes)
    }

    override fun initData() {
        super.initData()
        mPresenter = ChooseMyWelfarePresenter(this, mStateManager)
        orderId = intent.getStringExtra(ArouterParamConstant.ORDER_ID)
        amount = intent.getIntExtra(ArouterParamConstant.AMOUNT, 0)
        investperiod = intent.getIntExtra(ArouterParamConstant.INVEST_PERIOD, 0)
        subjctType = intent.getIntExtra(ArouterParamConstant.SUBJECT_TYPE, 0)
        mAdapter?.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            val intent = Intent()
            if (position != mAdapter?.data?.size!! - 1) {
                intent.putExtra(ArouterParamConstant.RED_PACKET, mAdapter?.data?.get(position))
                setResult(RESULT_OK, intent)
                finish()
            } else {
                setResult(RESULT_CANCELED)
                finish()
            }
        }
    }

    override fun retryGetData() {
        mStateManager.showLoading()
        getData()
    }

    override fun onListRefresh() {
        getData()
    }

    override fun onListLoadMore() {
        getData()
    }

    override fun loadDataAndNoMore(data: ChooseWelfareBean) {
        val datas = filtrateData(data.datas)
        if (datas.isNotEmpty()) {
            datas.add(ChooseWelfareBean.DatasBean())
        } else {
        }
        mSmartLayout.finishRefresh(50, true)
        mAdapter?.setNewData(datas)
        mAdapter?.loadMoreEnd()
    }

    override fun getCurrentRequestPage(): Int {
        return REQUEST_PAGE
    }

    override fun commonHolder(viewHolder: BaseViewHolder, data: ChooseWelfareBean.DatasBean) {
        if (viewHolder.adapterPosition != mAdapter?.data?.size!! - 1) {
            //设置红包的背景
            viewHolder.getView<RelativeLayout>(R.id.rl_root_view).setBackgroundResource(R.mipmap.red_envelope_available)
            viewHolder.getView<View>(R.id.tv_not_choose_red_packet).visibility = View.INVISIBLE
            viewHolder.getView<View>(R.id.rl_bg).visibility = View.VISIBLE

            //  //  红包描述（类型） 之前 ：1->注册红包  2->邀请红包  3->活动红包  或者是加息券
            val type = viewHolder.getView<TextView>(R.id.tv_envelopes)
            type.setTextColor(ContextCompat.getColor(this, R.color.white))
            type.text = data.description

            //设置红包金额或者加息券的额度
            val frontAmount = "¥"
            val behindAmount = NumberFormatUtils.getFormatNumberWithNoDigital(data.packetsAmount)

            val envelopesAmount = viewHolder.getView<TextView>(R.id.tv_envelopes_amount)
            envelopesAmount.text = SpannableStringUtils
                    .getBuilder(frontAmount)
                    .setProportion(0.8f)
                    .append(behindAmount)
                    .setProportion(1.6f)
                    .setBold()
                    .create()

            //设置是状态
            viewHolder.setText(R.id.tv_envelopes_state, "去使用")
            val useLimit: StringBuilder
            //设置投资额度的使用条件
//            if (data.templeteId == 107 || data.templeteId == 108 || data.templeteId == 109) {
//                useLimit = StringBuilder("投资额=" + NumberFormatUtils.getFormatNumberWithNoDigital(data.useRule.toDouble()))
//            } else {
                useLimit = StringBuilder("投资额≥" + NumberFormatUtils.getFormatNumberWithNoDigital(data.useRule.toDouble()))
//            }

            if (data.useRuleInvestPeriod != 0) {
                //如果有适用标的周期（use_rule_invest_period）就拼接上去
                useLimit.append("," + data.useRuleInvestPeriod + "天标")
            }
            useLimit.append("可用")
            viewHolder.setText(R.id.tv_envelopes_use_condition, useLimit.toString())

            //设置有效期
            viewHolder.setText(R.id.tv_available_time, "有效期至" + TimeUtils.milliseconds2String(data.dueTime, Constant.DEFAULT_WELFARE_DATE_FORMAT))
        } else {
            //是最后一个,设置最后一个条目的背景
            viewHolder.getView<RelativeLayout>(R.id.rl_root_view).setBackgroundResource(R.mipmap.bg_not_choose_red_packet)
            viewHolder.getView<View>(R.id.tv_not_choose_red_packet).visibility = View.VISIBLE
            viewHolder.getView<View>(R.id.rl_bg).visibility = View.INVISIBLE
        }
    }

    private fun getData() {
        mPresenter.getCanUserWelfare(orderId, amount)
    }

    private fun filtrateData(data: List<ChooseWelfareBean.DatasBean>): MutableList<ChooseWelfareBean.DatasBean> {
        val result = data.filter {
//            if (it.templeteId == 107 || it.templeteId == 108 || it.templeteId == 109) {
//                //通用红包都
//                investperiod == it.useRuleInvestPeriod && //判断适用周期周期
//                        System.currentTimeMillis() < it.dueTime &&  //判断是否过期
//                        (getTransfromSubjectType() == it.investType || it.investType == 4) &&  //判断类型是否一致
//                        it.type == 1    //1 -> 红包 2 -> 加息券
//            } else {
                //通用红包都
                investperiod >= it.useRuleInvestPeriod && //判断适用周期周期
                        System.currentTimeMillis() < it.dueTime &&  //判断是否过期
                        (getTransfromSubjectType() == it.investType || it.investType == 4) &&  //判断类型是否一致
                        it.type == 1    //1 -> 红包 2 -> 加息券
//            }


        }.toMutableList()
        return result
    }

    private fun getTransfromSubjectType(): Int {
        return if (subjctType == 1) 3 else if (subjctType == 3) 1 else subjctType
    }

    override fun onBackPressed() {
        setResult(RESULT_CANCELED)
        super.onBackPressed()
    }
}
