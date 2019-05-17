package com.shqj.arrange.fragment


import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.sleep.uulib.bean.InvestRecordListBean
import com.sleep.uulib.mvp.view.LoadListDataView
import com.sleep.uulib.uubase.UUBaseFragment
import com.sleep.uulib.view.MyLoadingMoreView
import com.shqj.arrange.R
import com.shqj.arrange.adapter.InvestRecordAdapter
import com.shqj.arrange.mvp.presenter.InvestRecordPresenter
import kotlinx.android.synthetic.main.arrange_fragment_invest_record.*


/**
 * 投资详情中投资记录界面.
 */
class InvestRecordFragment : UUBaseFragment(), LoadListDataView<InvestRecordListBean>, BaseQuickAdapter.RequestLoadMoreListener {

    private lateinit var mPresenter: InvestRecordPresenter
    protected lateinit var mAdapter: InvestRecordAdapter

    private var REQUEST_PAGE = 0
    private lateinit var orderId:String
    private var orderStatus = 2

    override fun getLayoutResourse(): Int {
        return R.layout.arrange_fragment_invest_record
    }

    override fun initView(view: View) {
        mAdapter = InvestRecordAdapter(R.layout.arrange_item_invest_record, mutableListOf())
        mAdapter.setLoadMoreView(MyLoadingMoreView(context))
        mAdapter.emptyView = layoutInflater.inflate(R.layout.uulib_list_tab_empty_view,null)
        mAdapter.setOnLoadMoreListener(this, invest_record_recycler)
        mAdapter.disableLoadMoreIfNotFullPage(invest_record_recycler)
        invest_record_recycler.layoutManager = LinearLayoutManager(context)
        invest_record_recycler.adapter = mAdapter
        invest_record_recycler.isNestedScrollingEnabled = false
        invest_record_recycler.viewTreeObserver.addOnGlobalLayoutListener {
            mAdapter.emptyView.requestLayout()
        }
    }

    override fun initData() {
        REQUEST_PAGE = 0
        mPresenter = InvestRecordPresenter(this,mStateManager)
    }



    override fun onFirstUserVisible() {
    }

    override fun retryGetData() {
        mStateManager.showLoading()
        getData()
    }

    override fun onLoadMoreRequested() {
        REQUEST_PAGE ++
        getData()
    }

    override fun loadData(data: InvestRecordListBean) {
        mAdapter.setNewData(data.page.data)
    }

    override fun loadDataAndNoMore(data: InvestRecordListBean) {
        mAdapter.setNewData(data.page.data)
        mAdapter.loadMoreComplete()
        mAdapter.loadMoreEnd()
    }

    override fun loadMoreData(data: InvestRecordListBean) {
        mAdapter.addData(data.page.data)
        mAdapter.loadMoreComplete()
    }

    override fun loadNoData() {
        mAdapter.setNewData(null)
    }

    override fun loadNoMoreData(data: InvestRecordListBean) {
        mAdapter.addData(data.page.data)
        mAdapter.loadMoreComplete()
        mAdapter.loadMoreEnd()
    }

    override fun loadDataFailure(e: Throwable?, errorCode: Int) {

    }

    /**
     * 返回
     */
    private fun getRealOrderStatue(): Int {

        if (orderStatus < 7) {
            return 2
        } else if (orderStatus in 7 until  8 || orderStatus == 13) {
            return 3
        } else if (orderStatus in 9..11) {
            return 5
        }
        return 2
    }

    private fun getData(){
        mPresenter.getInvestRecord(REQUEST_PAGE,orderId, mutableListOf(2,3,5).toIntArray())
    }

    fun setOrderData(orderId: String,orderStatus: Int){
        this.orderId = orderId
        this.orderStatus = orderStatus
        getData()
    }
}
