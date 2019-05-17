package com.shqj.mine.fragment.invest


import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.sleep.commonlib.util.LibCommonUtil
import com.sleep.uulib.bean.InvestRecordBean
import com.sleep.uulib.bean.QueryInvestByIdBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ArouterParamConstant
import com.sleep.uulib.uubase.LayoutManagerType
import com.sleep.uulib.uubase.UUBaseListFragment
import com.umeng.analytics.MobclickAgent
import com.shqj.mine.R
import com.shqj.mine.adapter.MyinvestExpendAdapter
import com.shqj.mine.mvp.QueryInvestListCallBack
import com.shqj.mine.mvp.presenter.MyInvestPresenter
import kotlinx.android.synthetic.main.mine_fragment_investing.*


/**
 * 我的投资 -> 还款中
 */
class InvestingFragment : UUBaseListFragment<MultiItemEntity, InvestRecordBean>() {

    private var REQUEST_PAGE = 0
    private lateinit var mPresenter: MyInvestPresenter

    private var needExpandPosition: Int= 0

    override fun getLayoutResourse(): Int {
        return R.layout.mine_fragment_investing
    }

    override fun initItemLayout() {
        super.mRecyclerView = investing_recycler
        super.mSmartLayout = investing_refresh
        setListType(LayoutManagerType.LINEAR_LAYOUT_MANAGER, true)
        setLayoutResId(R.layout.item_my_invest)
    }

    override fun initData() {
        super.initData()
        mPresenter = MyInvestPresenter(this,mStateManager)
        mAdapter?.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            if(!LibCommonUtil.isFastDoubleClick()){
                if(mAdapter?.data!![position] is InvestRecordBean.PageBean.DataBean){
                    MobclickAgent.onEvent(context, "wd_wdtz_hkz_lbdj")
                    ARouter.getInstance()
                            .build(ArouterConstant.MINE_MY_INVEST_DETAIL)
                            .withParcelable(ArouterParamConstant.INVEST_DATA,mAdapter?.data!![position] as InvestRecordBean.PageBean.DataBean)
                            .navigation()
                }
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

    override fun loadData(data: InvestRecordBean) {
        super.loadData(data)
        mAdapter?.setNewData(data.page.data as List<InvestRecordBean.PageBean.DataBean>)
    }

    override fun loadMoreData(data: InvestRecordBean) {
        super.loadMoreData(data)
        mAdapter?.addData(data.page.data)
    }

    override fun loadNoMoreData(data: InvestRecordBean) {
        super.loadNoMoreData(data)
        if(data.page != null){
            mAdapter?.addData(data.page.data)
        }
    }

    override fun getCurrentRequestPage(): Int {
        return REQUEST_PAGE
    }

    override fun customAdapter(): Boolean {
        super.mAdapter = MyinvestExpendAdapter(this.context,null,object : MyinvestExpendAdapter.ExpandListener{
            override fun expanded(position: Int) {
                MobclickAgent.onEvent(context, "wd_wdtz_hkz_jtdj")
                needExpandPosition = position
                val data = mAdapter?.data!![needExpandPosition] as InvestRecordBean.PageBean.DataBean
                mPresenter.queryInvestListByOrderId(context, data.orderId, data.investOrderId, object : QueryInvestListCallBack {
                    override fun queryInvestListByOrderIdSuccess(data: QueryInvestByIdBean) {
                        val newData = mAdapter?.data!!
                        (newData[needExpandPosition] as InvestRecordBean.PageBean.DataBean).removeSubItem(0)
                        (newData[needExpandPosition] as InvestRecordBean.PageBean.DataBean).addSubItem(data)
//                        mAdapter?.setNewData(newData)
                        (mAdapter as MyinvestExpendAdapter).expand(needExpandPosition)
                        (mAdapter as MyinvestExpendAdapter).collapsePrevExpendItem(needExpandPosition)
                    }
                })
            }

        },false)
        return true
    }

    override fun commonHolder(viewHolder: BaseViewHolder, data: MultiItemEntity) {
    }

    private fun getData(){
        mPresenter.getMyInvestData(REQUEST_PAGE,2)
    }
}
