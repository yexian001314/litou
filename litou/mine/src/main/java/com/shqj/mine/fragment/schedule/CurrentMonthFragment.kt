package com.shqj.mine.fragment.schedule


import android.text.TextUtils
import com.chad.library.adapter.base.BaseViewHolder
import com.sleep.commonlib.util.TimeUtils
import com.sleep.uulib.bean.PaymentScheduleBean
import com.sleep.uulib.util.NumberFormatUtils
import com.sleep.uulib.uubase.LayoutManagerType
import com.sleep.uulib.uubase.UUBaseListFragment
import com.shqj.mine.R
import com.shqj.mine.mvp.presenter.PaymentSchedulePresenter
import kotlinx.android.synthetic.main.mine_fragment_current_month.*


/**
 * 回款计划 -> 本月回款
 */
class CurrentMonthFragment : UUBaseListFragment<PaymentScheduleBean.ReturnFundVOSBean, PaymentScheduleBean>() {

    private var REQUEST_PAGE = 0

    private lateinit var mPresenter: PaymentSchedulePresenter

    override fun getLayoutResourse(): Int {
        return R.layout.mine_fragment_current_month
    }

    override fun initItemLayout() {
        super.mRecyclerView = current_month_recycler
        super.mSmartLayout = current_month_refresh
        setListType(LayoutManagerType.LINEAR_LAYOUT_MANAGER, true)
        setLayoutResId(R.layout.item_payment_schedule)
    }

    override fun initData() {
        super.initData()
        mPresenter = PaymentSchedulePresenter(this,mStateManager)
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

    override fun loadData(data: PaymentScheduleBean) {
        super.loadData(data)
        mAdapter?.setNewData(data.returnFundVOS)
    }

    override fun loadMoreData(data: PaymentScheduleBean) {
        super.loadMoreData(data)
        mAdapter?.addData(data.returnFundVOS)
    }

    override fun loadNoMoreData(data: PaymentScheduleBean) {
        super.loadNoMoreData(data)
        mAdapter?.addData(data.returnFundVOS)
    }

    override fun getCurrentRequestPage(): Int {
        return REQUEST_PAGE
    }

    override fun commonHolder(viewHolder: BaseViewHolder, data: PaymentScheduleBean.ReturnFundVOSBean) {
        //项目名称
        if (!TextUtils.isEmpty(data.subjectName)) {
            viewHolder.setText(R.id.tv_project_name, data.subjectName)
        }
        //待回总额
        val strBackTotalMoney = NumberFormatUtils.getFormatNumber(data.money, 2)
        viewHolder.setText(R.id.tv_back_total, strBackTotalMoney)
        //待回日期
        viewHolder.setText(R.id.tv_date, TimeUtils.milliseconds2String(data.time))
        //当前/总期
        viewHolder.setText(R.id.tv_current_total, "${data.period}/${data.totalPeriod}")
    }

    private fun getData(){
        mPresenter.getPaymentSchedule(REQUEST_PAGE,1)
    }
}

