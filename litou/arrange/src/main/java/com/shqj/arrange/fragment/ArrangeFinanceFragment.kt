package com.shqj.arrange.fragment


import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseViewHolder
import com.shqj.arrange.R
import com.sleep.commonlib.util.LibCommonUtil
import com.sleep.uulib.bean.FinanceListBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ArouterParamConstant
import com.sleep.uulib.mvp.presenter.ArrangeFinancePresenter
import com.sleep.uulib.util.BidUtil
import com.sleep.uulib.util.NumberFormatUtils
import com.sleep.uulib.util.NumberUtils
import com.sleep.uulib.uubase.LayoutManagerType
import com.sleep.uulib.uubase.UUBaseListFragment
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.arrange_fragment_arrange_finance.*


/**
 * 投资tab页.
 */
@Route(path = ArouterConstant.APP_ARRANGE_FINACE_FRAGMENT)
class ArrangeFinanceFragment : UUBaseListFragment<FinanceListBean.ProjectsBean.DataBean, FinanceListBean>() {

    private val mPresenter: ArrangeFinancePresenter get() = ArrangeFinancePresenter(this, mStateManager)

    private var REQUEST_PAGE = 0
    override fun getLayoutResourse(): Int {
        return R.layout.arrange_fragment_arrange_finance
    }

    override fun shouldShowToolBar(): Boolean {
        return true
    }

    override fun initItemLayout() {
        super.mRecyclerView = arrange_finance_recycler
        super.mSmartLayout = arrange_finance_refresh
        setListType(LayoutManagerType.LINEAR_LAYOUT_MANAGER, true)
        setLayoutResId(R.layout.item_arrange_finance_fragment)
    }

    override fun initData() {
        super.initData()
        hideBackView()
        setTitle(getString(R.string.arrange_finance_title))
        mAdapter?.setOnItemClickListener { _, _, position ->
            if (!LibCommonUtil.isFastDoubleClick()) {
                MobclickAgent.onEvent(context, "tz_cplb_btn")
                ARouter.getInstance()
                        .build(ArouterConstant.ARRANGE_PROJECT_DETAIL)
                        .withString(ArouterParamConstant.ORDER_ID, mAdapter?.data!![position].orderId)
                        .withString(ArouterParamConstant.SUBJECT_NAME, mAdapter?.data!![position].subjectName)
                        .navigation()
            }

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

    override fun loadData(data: FinanceListBean) {
        super.loadData(data)
        mAdapter?.setNewData(data.projects.data)
    }

    override fun loadMoreData(data: FinanceListBean) {
        super.loadMoreData(data)
        mAdapter?.addData(data.projects.data)
    }

    override fun loadNoMoreData(data: FinanceListBean) {
        super.loadNoMoreData(data)
        mAdapter?.addData(data.projects.data)
    }

    override fun getCurrentRequestPage(): Int {
        return REQUEST_PAGE
    }

    override fun commonHolder(holder: BaseViewHolder, data: FinanceListBean.ProjectsBean.DataBean) {
        var baseRate = NumberFormatUtils.getFormatNumber(NumberUtils.mul(data.baseRate, 100.00), 1)
        var extraRate = ""
        var littleTipExtraRate = ""
        when {
            data.subjectType == 1 -> {
                //普通标 U系列加息放在活动加息里面
                extraRate = NumberFormatUtils.getFormatNumber(NumberUtils.mul(data.activityRate, 100.00), 1)
                if (data.activityRate != 0.0) {
                    littleTipExtraRate = NumberFormatUtils.getFormatNumberWithNoDigital(NumberUtils.mul(data.activityRate, 100.00))
                    holder.setVisible(R.id.tv_subject_type, true)
                    holder.setBackgroundRes(R.id.tv_subject_type,R.mipmap.coupon)
                } else {
                    holder.setVisible(R.id.tv_subject_type, false)

                }
            }
            data.subjectType == 2 -> {
                //活动标
                extraRate = NumberFormatUtils.getFormatNumber(NumberUtils.mul(data.activityRate, 100.00), 1)
                if (data.activityRate != 0.0) {
                    littleTipExtraRate = NumberFormatUtils.getFormatNumberWithNoDigital(NumberUtils.mul(data.activityRate, 100.00))
                    holder.setVisible(R.id.tv_subject_type, true)
                    holder.setBackgroundRes(R.id.tv_subject_type,R.mipmap.coupon)
                } else {
                    holder.setVisible(R.id.tv_subject_type, false)
                }
            }
            data.subjectType == 3 -> {
                //新手标
                holder.setVisible(R.id.tv_subject_type, true)
                //新手加息
                extraRate = NumberFormatUtils.getFormatNumber(NumberUtils.mul(data.noviceRate, 100.00), 1)
            }
        }
        //设置加息小标
        if (littleTipExtraRate.isNotEmpty()) {
            holder.setBackgroundRes(R.id.tv_subject_type, R.mipmap.coupon)
        }
        //设置加息
        BidUtil.setBidRateText(holder.getView(R.id.tv_earning_rate), baseRate, extraRate)
        //项目名
        holder.setText(R.id.tv_project_name, data.subjectName)
        val rate :Int =(data.currentMoney*100/data.totalMoney).toInt()
        holder.setProgress(R.id.pb_invest_progess, rate)
        holder.setText(R.id.tv_invest_rate,  rate.toString()+"%")
        //期限
        holder.setText(R.id.tv_invest_period, "期限" + data.investPeriod + "天")
        //按钮点击行为
        holder.itemView.setOnClickListener({
            MobclickAgent.onEvent(context, "tz_ljjr_btn")
            ARouter.getInstance()
                    .build(ArouterConstant.ARRANGE_PROJECT_DETAIL)
                    .withString(ArouterParamConstant.ORDER_ID, data.orderId)
                    .withString(ArouterParamConstant.SUBJECT_NAME, data.subjectName)
                    .navigation()
        })

        //设置还本付息还是分期还款
        if (data.payType == 1) {
            //一次性还本付息
            holder.setText(R.id.tv_pay_type, "一次性还本付息")
        } else if (data.payType == 2) {
            //分期还款
            holder.setText(R.id.tv_pay_type, "先息后本")
        }
    }

    private fun getData() {
        mPresenter.getFinanceManagerList(REQUEST_PAGE)
    }
}
