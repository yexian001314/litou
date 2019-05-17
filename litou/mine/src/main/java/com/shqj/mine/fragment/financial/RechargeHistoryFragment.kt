package com.shqj.mine.fragment.financial


import com.chad.library.adapter.base.BaseViewHolder
import com.sleep.commonlib.util.TimeUtils
import com.sleep.uulib.bean.InvestDetailBean
import com.sleep.uulib.constant.Constant
import com.sleep.uulib.uubase.LayoutManagerType
import com.sleep.uulib.uubase.UUBaseListFragment
import com.shqj.mine.R
import com.shqj.mine.mvp.presenter.FinancialDetailPresenter
import kotlinx.android.synthetic.main.mine_fragment_recharge_history.*
import java.text.DecimalFormat


/**
 * 资金明细 -> 充值记录
 */
class RechargeHistoryFragment : UUBaseListFragment<InvestDetailBean.ProjectsBean.DataBean, InvestDetailBean>() {

    private var REQUEST_PAGE = 0
    private lateinit var mPresenter: FinancialDetailPresenter

    override fun getLayoutResourse(): Int {
        return R.layout.mine_fragment_recharge_history
    }

    override fun initItemLayout() {
        super.mRecyclerView = recharge_history_recycler
        super.mSmartLayout = recharge_history_refresh
        setListType(LayoutManagerType.LINEAR_LAYOUT_MANAGER, true)
        setLayoutResId(R.layout.item_financial_details)
    }

    override fun initData() {
        super.initData()
        mPresenter = FinancialDetailPresenter(this,mStateManager)
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

    override fun loadData(data: InvestDetailBean) {
        super.loadData(data)
        mAdapter?.setNewData(data.projects.data)
    }

    override fun loadMoreData(data: InvestDetailBean) {
        super.loadMoreData(data)
        mAdapter?.addData(data.projects.data)
    }

    override fun loadNoMoreData(data: InvestDetailBean) {
        super.loadNoMoreData(data)
        mAdapter?.addData(data.projects.data)
    }

    override fun getCurrentRequestPage(): Int {
        return REQUEST_PAGE
    }

    override fun commonHolder(viewHolder: BaseViewHolder, data: InvestDetailBean.ProjectsBean.DataBean) {
        //类型
        viewHolder.setText(R.id.tv_type, getString(R.string.recharge))
        //创建时间
        viewHolder.setText(R.id.tv_time, TimeUtils.milliseconds2String(data.createTime))
        //金额数
        val df = DecimalFormat(Constant.DEFAULT_MONEY_FORMAT)
        val strTotalMoney = df.format(data.totalMoney)
        viewHolder.setText(R.id.tv_amount, if (data.totalMoney > 0) {
            "+$strTotalMoney"
        } else {
            strTotalMoney
        })
        //去掉最后一个条目的分割线
        viewHolder.setVisible(R.id.line, mAdapter?.data?.size !== viewHolder.layoutPosition)
    }

    private fun getData(){
        mPresenter.getFinancialDetailData(REQUEST_PAGE,1)
    }

}
