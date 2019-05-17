package com.shqj.mine.fragment.invest


import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sleep.commonlib.util.LibCommonUtil
import com.sleep.commonlib.util.SpannableStringUtils
import com.sleep.commonlib.util.TimeUtils
import com.sleep.uulib.bean.InvestRecordBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ArouterParamConstant
import com.sleep.uulib.util.NumberFormatUtils
import com.sleep.uulib.util.NumberUtils
import com.sleep.uulib.uubase.LayoutManagerType
import com.sleep.uulib.uubase.UUBaseListFragment
import com.umeng.analytics.MobclickAgent
import com.shqj.mine.R
import com.shqj.mine.mvp.presenter.MyInvestPresenter
import kotlinx.android.synthetic.main.mine_fragment_participate.*


/**
 * 我的投资 -> 招标中
 */
class ParticipateFragment : UUBaseListFragment<InvestRecordBean.PageBean.DataBean, InvestRecordBean>() {

    private var REQUEST_PAGE = 0

    private lateinit var mPresenter: MyInvestPresenter

    override fun getLayoutResourse(): Int {
        return R.layout.mine_fragment_participate
    }

    override fun initItemLayout() {
        super.mRecyclerView = participate_recycler
        super.mSmartLayout = participate_refresh
        setListType(LayoutManagerType.LINEAR_LAYOUT_MANAGER, true)
        setLayoutResId(R.layout.item_my_invest)
    }

    override fun initData() {
        super.initData()
        mPresenter = MyInvestPresenter(this,mStateManager)
        mAdapter?.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            if(!LibCommonUtil.isFastDoubleClick()){
                MobclickAgent.onEvent(context, "wd_wdtz_zbz_lbdj")
                ARouter.getInstance()
                        .build(ArouterConstant.MINE_MY_INVEST_DETAIL)
                        .withParcelable(ArouterParamConstant.INVEST_DATA,mAdapter?.data!![position])
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

    override fun loadData(data: InvestRecordBean) {
        super.loadData(data)
        mAdapter?.setNewData(data.page.data)
    }

    override fun loadMoreData(data: InvestRecordBean) {
        super.loadMoreData(data)
        mAdapter?.addData(data.page.data)
    }

    override fun loadNoMoreData(data: InvestRecordBean) {
        super.loadNoMoreData(data)
        mAdapter?.addData(data.page.data)
    }

    override fun getCurrentRequestPage(): Int {
        return REQUEST_PAGE
    }

    override fun commonHolder(viewHolder: BaseViewHolder, data: InvestRecordBean.PageBean.DataBean) {
        //设置项目名称
        if (!TextUtils.isEmpty(data.subjectName)) {
            viewHolder.setText(R.id.tv_project_name, data.subjectName)
        }

        //设置ID
        if (!TextUtils.isEmpty(data.investOrderId)) {
            viewHolder.setText(R.id.tv_project_id, "NO." + data.id)
        }

        //设置预期年化收益率
        val earningRate = viewHolder.getView<TextView>(R.id.tv_earnings_rate)
        earningRate.text = SpannableStringUtils
                .getBuilder(NumberFormatUtils.getFormatNumber(NumberUtils.mul(data.lendRate, 100.00), 2))
                .setProportion(1f)
                .setForegroundColor(ContextCompat.getColor(context, R.color.color_4c4c4c))
                .append("%")
//                .setProportion(0.5f)
//                .setForegroundColor(ContextCompat.getColor(context, R.color.gray))
                .create()


        //设置投资
        val investAmount = viewHolder.getView<TextView>(R.id.tv_invest_amount)
        investAmount.text = SpannableStringUtils
                .getBuilder(NumberFormatUtils.getFormatNumber(data.investMoney,2))
                .setProportion(1f)
                .setForegroundColor(ContextCompat.getColor(context, R.color.color_ff721f))
//                .append("元")
//                .setProportion(0.5f)
//                .setForegroundColor(ContextCompat.getColor(context, R.color.gray))
                .create()

        //设置代收利息
        val interest = viewHolder.getView<TextView>(R.id.tv_interest)
        interest.text = SpannableStringUtils
                .getBuilder(NumberFormatUtils.getFormatNumber(data.everydayIncome, 2))
                .setProportion(1f)
                .setForegroundColor(ContextCompat.getColor(context, R.color.color_4c4c4c))
//                .append("元")
//                .setProportion(0.5f)
//                .setForegroundColor(ContextCompat.getColor(context, R.color.gray))
                .create()


        //设置到期天数
//        viewHolder.setText(R.id.tv_deadline, "正在招标中")
        //设置合同按钮不显示
//        viewHolder.setVisible(R.id.bt_contract, false)

        if(data.payType == 1){
            viewHolder.setText(R.id.tv_project_pay_type,"（一次性还本付息）")
            viewHolder.setText(R.id.tv_pay_date_title, "回款日期")
            viewHolder.setText(R.id.tv_pay_date, TimeUtils.milliseconds2String(data.expiredTime,"yyyy-MM-dd"))
            viewHolder.getView<View>(R.id.iv_user_invest_list).visibility = View.GONE
        }else if(data.payType == 2){
            viewHolder.setText(R.id.tv_project_pay_type,"（先息后本）")
            viewHolder.setText(R.id.tv_pay_date_title, "回款日期")
            viewHolder.setText(R.id.tv_pay_date, TimeUtils.milliseconds2String(data.expiredTime,"yyyy-MM-dd"))
            viewHolder.getView<View>(R.id.iv_user_invest_list).visibility = View.GONE
        }
    }

    private fun getData(){
        mPresenter.getMyInvestData(REQUEST_PAGE,1)
    }
}
