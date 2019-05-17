package com.sleep.home.activity

import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseViewHolder
import com.sleep.commonlib.util.LibCommonUtil
import com.sleep.home.R
import com.sleep.uulib.bean.FinanceListBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ArouterParamConstant
import com.sleep.uulib.mvp.presenter.ArrangeFinancePresenter
import com.sleep.uulib.util.BidUtil
import com.sleep.uulib.util.NumberFormatUtils
import com.sleep.uulib.util.NumberUtils
import com.sleep.uulib.uubase.LayoutManagerType
import com.sleep.uulib.uubase.UUBaseListActivity
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.home_activity_bid_prefecture.*

@Route(path = ArouterConstant.HOME_BID_PREFECTURE)
class BidPrefectureActivity : UUBaseListActivity<FinanceListBean.ProjectsBean.DataBean, FinanceListBean>() {

    private val mPresenter: ArrangeFinancePresenter get() = ArrangeFinancePresenter(this, mStateManager)

    private var REQUEST_PAGE = 0

    /**
     * 专区类型
     */
    private var bidType = -1

    override fun getLayoutResourse(): Int {
        return R.layout.home_activity_bid_prefecture
    }

    override fun initData() {
        super.initData()
        intent?.getIntExtra(ArouterParamConstant.BID_TYPE,-1)?.let { bidType = it }
        if(bidType == -1) finish()

        when(bidType){
            1 -> setTitle(getString(R.string.title_u_series_prefecture))
            2 -> setTitle(getString(R.string.title_activity_bid_prefecture))
            3 -> setTitle(getString(R.string.title_new_bid_prefecture))
        }
        mAdapter?.setOnItemClickListener { _, _, position ->
            if(!LibCommonUtil.isFastDoubleClick()){
                MobclickAgent.onEvent(this, "bdzq_lbdj_btn")
                ARouter.getInstance()
                        .build(ArouterConstant.ARRANGE_PROJECT_DETAIL)
                        .withString(ArouterParamConstant.ORDER_ID, mAdapter?.data!![position].orderId)
                        .withString(ArouterParamConstant.SUBJECT_NAME, mAdapter?.data!![position].subjectName)
                        .navigation()
            }
        }
    }

    override fun initItemLayout() {
        super.mRecyclerView = bid_prefecture_recycler
        super.mSmartLayout = bid_prefecture_refresh
        setListType(LayoutManagerType.LINEAR_LAYOUT_MANAGER, true)
        setLayoutResId(R.layout.item_arrange_finance)
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

    override fun commonHolder(viewHolder: BaseViewHolder, item: FinanceListBean.ProjectsBean.DataBean) {
        var baseRate = NumberFormatUtils.getFormatNumber(NumberUtils.mul(item.baseRate, 100.00), 1)
        var extraRate = ""
        var littleTipExtraRate = ""
        when {
            item.subjectType == 1 -> {
                //普通标 U系列加息放在活动加息里面
                extraRate = NumberFormatUtils.getFormatNumber(NumberUtils.mul(item.activityRate, 100.00), 1)
                if(item.activityRate != 0.0){
                    littleTipExtraRate = NumberFormatUtils.getFormatNumberWithNoDigital(NumberUtils.mul(item.activityRate, 100.00))
                    viewHolder.setVisible(R.id.tv_subject_type,true)
                }else{
                    viewHolder.setVisible(R.id.tv_subject_type,false)
                }
            }
            item.subjectType == 2 -> {
                //活动标
                extraRate = NumberFormatUtils.getFormatNumber(NumberUtils.mul(item.activityRate, 100.00), 1)
                if(item.activityRate != 0.0){
                    littleTipExtraRate = NumberFormatUtils.getFormatNumberWithNoDigital(NumberUtils.mul(item.activityRate, 100.00))
                    viewHolder.setVisible(R.id.tv_subject_type,true)
                }else {
                    viewHolder.setVisible(R.id.tv_subject_type,false)
                }
            }
            item.subjectType == 3 -> {
                //新手标
//                viewHolder.setText(R.id.tv_subject_type,"新手专享")
                viewHolder.setVisible(R.id.tv_subject_type,true)
                //新手加息
                extraRate = NumberFormatUtils.getFormatNumber(NumberUtils.mul(item.noviceRate, 100.00), 1)
            }
        }
        //设置加息小标
        if(littleTipExtraRate.isNotEmpty()){
//            viewHolder.setText(R.id.tv_subject_type,"加息$littleTipExtraRate%")
            viewHolder.setBackgroundRes(R.id.tv_subject_type,R.mipmap.coupon)
        }

        //设置加息
        BidUtil.setBidRateText(viewHolder.getView(R.id.tv_earning_rate),baseRate,extraRate)
        //项目名
        viewHolder.setText(R.id.tv_project_name, item.subjectName)
//        剩余可投
//        viewHolder.getView<TextView>(R.id.tv_remain_money).text = SpannableStringUtils.getBuilder("剩余可投")
//                .append(NumberFormatUtils.getFormatNumberWithNoDigital(NumberUtils.sub(item.totalMoney,item.currentMoney)))
//                .setForegroundColor(resources.getColor(R.color.colorAccent))
//                .append("元")
//                .create()
        viewHolder.getView<TextView>(R.id.tv_remain_money).text=NumberFormatUtils.getFormatNumberWithNoDigital(NumberUtils.sub(item.totalMoney,item.currentMoney))
//        期限
        viewHolder.setText(R.id.tv_invest_period, "期限" + item.investPeriod + "天")
        //设置标状态
        val status = viewHolder.getView<TextView>(R.id.tv_bid_status_and_btn)
        if (item.orderStatus == 5) {
            //募集中
            status.setBackgroundResource(R.drawable.shape_accent_corner)
            status.text = getString(R.string.invest_now)
        } else if (item.orderStatus == 6) {
            //募集完成
            status.setBackgroundResource(R.drawable.shape_gray_corner)
            status.text = getString(R.string.bid_full)
        } else if (item.orderStatus == 7) {
            //还款中
            status.setBackgroundResource(R.drawable.shape_gray_corner)
            status.text = getString(R.string.paymenting)
        } else if (item.orderStatus == 9 || item.orderStatus == 11) {
            //已还款
            status.setBackgroundResource(R.drawable.shape_gray_corner)
            status.text = getString(R.string.has_bean_payment)
        }
        status.setOnClickListener({
            if(!LibCommonUtil.isFastDoubleClick()){
                MobclickAgent.onEvent(BidPrefectureActivity@this, "bdzq_ljjr_btn")
                ARouter.getInstance()
                        .build(ArouterConstant.ARRANGE_PROJECT_DETAIL)
                        .withString(ArouterParamConstant.ORDER_ID, item.orderId)
                        .withString(ArouterParamConstant.SUBJECT_NAME, item.subjectName)
                        .navigation()
            }
        })

        //设置还本付息还是分期还款
        if(item.payType == 1){
            //一次性还本付息
            viewHolder.setText(R.id.tv_pay_type,"| 一次性还本付息")
        }else if(item.payType == 2){
            //分期还款
            viewHolder.setText(R.id.tv_pay_type,"| 先息后本")
        }
    }

    private fun getData() {
        mPresenter.getFinanceManagerList(REQUEST_PAGE,bidType)
    }

}
