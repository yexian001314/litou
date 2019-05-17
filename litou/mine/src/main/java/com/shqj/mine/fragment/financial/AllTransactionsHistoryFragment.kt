package com.shqj.mine.fragment.financial


import com.chad.library.adapter.base.BaseViewHolder
import com.shqj.mine.R
import com.shqj.mine.mvp.presenter.FinancialDetailPresenter
import com.sleep.commonlib.util.TimeUtils
import com.sleep.uulib.bean.InvestDetailBean
import com.sleep.uulib.constant.Constant
import com.sleep.uulib.uubase.LayoutManagerType
import com.sleep.uulib.uubase.UUBaseListFragment
import kotlinx.android.synthetic.main.mine_fragment_all_transactions_history.*
import java.text.DecimalFormat


/**
 * 资金明细 -> 全部交易
 */
class AllTransactionsHistoryFragment : UUBaseListFragment<InvestDetailBean.ProjectsBean.DataBean, InvestDetailBean>() {

    private var REQUEST_PAGE = 0
    private lateinit var mPresenter: FinancialDetailPresenter

    override fun getLayoutResourse(): Int {
        return R.layout.mine_fragment_all_transactions_history
    }

    override fun initItemLayout() {
        super.mRecyclerView = all_transactions_recycler
        super.mSmartLayout = all_transactions_refresh
        setListType(LayoutManagerType.LINEAR_LAYOUT_MANAGER, true)
        setLayoutResId(R.layout.item_financial_details)
    }

    override fun initData() {
        super.initData()
        mPresenter = FinancialDetailPresenter(this, mStateManager)
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
        //设置投资类型
        val type = data.fundType
        var str_type = ""
        when (type) {
            1 -> str_type = "充值"
            2 -> str_type = "提现"
            3 -> str_type = "购买项目"
            4 -> str_type = "项目利息回款"
            5 -> str_type = "项目本金回款"
            6 -> str_type = "服务费"
            7 -> str_type = "保证金"
            8 -> str_type = "利息"
            10 -> str_type = "退还保证金"
            11 -> str_type = "报名费"
            12 -> str_type = "追加保证金"
            13 -> str_type = "提现失败"
            14 -> str_type = "活动返现"
            15 -> str_type = "充值(资金调整)"
            16 -> str_type = "项目分红"
            17 -> str_type = "借款结余"
            18 -> str_type = "红包投资利息"
            19 -> str_type = "借款"
            20 -> str_type = "还款"
            21 -> str_type = "投标"
            22 -> str_type = "退还利息"
            30 -> str_type = "借款取消返还服务费"
            32 -> str_type = "车辆入库管理费"
            33 -> str_type = "取消返还管理费"
            35 -> str_type = "放款"
            36 -> str_type = "添加债务"
            37 -> str_type = "减少债务"
            38 -> str_type = "追加利息"
            39 -> str_type = "提现手续费"
            40 -> str_type = "提现失败手续费返还"
            41 -> str_type = "推荐奖励收益"
            42 -> str_type = "红包抵扣"
            43 -> str_type = "红包返还"
            151 -> str_type = "利投平账操作"
            107 -> str_type = "提现手续费返还"
            115 -> str_type = "投资解冻转待收"
            else -> {
            }
        }
        viewHolder.setText(R.id.tv_type, str_type)
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

    private fun getData() {
        mPresenter.getFinancialDetailData(REQUEST_PAGE)
    }

}
