package com.shqj.arrange.activity

import android.text.TextUtils
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseViewHolder
import com.sleep.commonlib.util.StringUtil
import com.sleep.commonlib.util.TimeUtils
import com.sleep.commonlib.util.ToastUtil
import com.sleep.uulib.bean.InvestRecordListBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ArouterParamConstant
import com.sleep.uulib.constant.Constant
import com.sleep.uulib.uubase.LayoutManagerType
import com.sleep.uulib.uubase.UUBaseListActivity
import com.shqj.arrange.R
import com.shqj.arrange.mvp.presenter.InvestRecordPresenter
import kotlinx.android.synthetic.main.arrange_activity_invest_record.*
import java.text.DecimalFormat

/**
 * @deprecated
 *
 * 投资记录(1.0.5版本之前使用)
 * params:
 *      orderId: 订单号
 *      orderStatus: 订单状态
 */
@Route(path = ArouterConstant.ARRANGE_INVEST_RECORD)
class InvestRecordActivity : UUBaseListActivity<InvestRecordListBean.PageBean.DataBean,InvestRecordListBean>() {

    private val mPresenter: InvestRecordPresenter get() = InvestRecordPresenter(this,mStateManager)

    private var REQUEST_PAGE = 0
    private lateinit var orderId:String
    private var orderStatus = 2

    override fun getLayoutResourse(): Int {
        return R.layout.arrange_activity_invest_record
    }

    override fun initItemLayout() {
        setTitle(getString(R.string.title_invest_record))
        super.mRecyclerView = invest_record_recycler
        super.mSmartLayout = invest_record_refresh
        setListType(LayoutManagerType.LINEAR_LAYOUT_MANAGER, true)
        setLayoutResId(R.layout.arrange_item_invest_record)
    }

    override fun initData() {
        intent?.getStringExtra(ArouterParamConstant.ORDER_ID)?.let { orderId = it }
        intent?.getIntExtra(ArouterParamConstant.ORDER_STATUS,2)?.let { orderStatus = it }
        if(StringUtil.isEmpty(orderId)){
            ToastUtil.showToast("OrderId is empty")
            finish()
        }
        super.initData()
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
        REQUEST_PAGE ++
        getData()
    }

    override fun loadData(data: InvestRecordListBean) {
        super.loadData(data)
        mAdapter?.setNewData(data.page.data)
    }

    override fun loadMoreData(data: InvestRecordListBean) {
        super.loadMoreData(data)
        mAdapter?.addData(data.page.data)
    }

    override fun loadNoMoreData(data: InvestRecordListBean) {
        super.loadNoMoreData(data)
        if(data.page != null) mAdapter?.addData(data.page.data)
    }

    override fun getCurrentRequestPage(): Int {
        return REQUEST_PAGE
    }

    override fun commonHolder(viewHolder: BaseViewHolder, item: InvestRecordListBean.PageBean.DataBean) {
        if (!TextUtils.isEmpty(item.phoneNumber)) {
            viewHolder.setText(R.id.tv_phoneNum, StringUtil.dimStrings(item.phoneNumber, 3, 4))
        }
        //投资金额
        val df = DecimalFormat(Constant.DEFAULT_MONEY_FORMAT)
        viewHolder.setText(R.id.tv_invest_amount, df.format(item.investMoney))
        //时间
        viewHolder.setText(R.id.tv_invest_time, TimeUtils.milliseconds2String(item.createTime))
    }

    private fun getData(){
        mPresenter.getInvestRecord(REQUEST_PAGE,orderId,mutableListOf(2,3,5).toIntArray())
    }
}
