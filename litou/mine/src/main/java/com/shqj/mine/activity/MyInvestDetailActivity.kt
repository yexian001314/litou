package com.shqj.mine.activity

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.twitterlib.wigdet.dialog.CommonFragmentDialog
import com.example.twitterlib.wigdet.dialog.DialogFactory
import com.shqj.mine.R
import com.shqj.mine.mvp.view.IMyInvestDetailView
import com.sleep.commonlib.util.TimeUtils
import com.sleep.commonlib.util.ToastUtil
import com.sleep.uulib.bean.InvestRecordBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ArouterParamConstant
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.util.NumberFormatUtils
import com.sleep.uulib.util.NumberUtils
import com.sleep.uulib.uubase.HtmlUrlActivity
import com.sleep.uulib.uubase.UUBaseActivity
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.mine_activity_my_invest_detail.*

/**
 * 我的投资 -> 投资详情
 *
 * @param orderId
 * 请求数据的orderId
 */
@Route(path = ArouterConstant.MINE_MY_INVEST_DETAIL)
class MyInvestDetailActivity : UUBaseActivity(), View.OnClickListener, IMyInvestDetailView {

    override fun loadData() {
    }


    /**
     * 当前投资数据
     */
    var investData: InvestRecordBean.PageBean.DataBean? = null

    override fun getLayoutResourse(): Int {
        return R.layout.mine_activity_my_invest_detail
    }

    override fun initView() {
//        setToolBarColor(R.color.colorAccent)
        tv_project_detail.setOnClickListener(this)
        rl_loan_agreement.setOnClickListener(this)
        rl_repayment_date.setOnClickListener(this)
    }

    override fun initData() {
        investData = intent.getParcelableExtra(ArouterParamConstant.INVEST_DATA)
        if (investData == null) {
            ToastUtil.showToast("界面数据传递出错，请重试")
            finish()
        }
        setTitle(investData?.subjectName.toString())
        setViewData()
    }

    /**
     * 设置界面数据
     */
    private fun setViewData() {
        //投资金额
        tv_invest_amount.text = NumberFormatUtils.getNumberWithDigital(investData?.investMoney!!, 2)
        //预期年化
        expect_earnings_rate.text = "${NumberFormatUtils.getNumberWithDigital(NumberUtils.mul(investData?.lendRate!!, 100.toDouble()), 2)}%"
        //投标日期
        tv_invest_time.text = TimeUtils.milliseconds2String(investData?.createTime!!, "yyyy/MM/dd")
        //还款日期
        if (investData?.expiredTime != 0.toLong()) {
            tv_repayment_date.text = TimeUtils.milliseconds2String(investData?.expiredTime!!, "yyyy/MM/dd")
        } else if (investData?.endTime != 0.toLong()) {
            tv_repayment_date.text = TimeUtils.milliseconds2String(investData?.endTime!!, "yyyy/MM/dd")
        }
        //红包金额
        if (investData?.packetsAmount != null && investData?.packetsAmount != 0.toDouble()) {
            tv_red_package_used_amount.text = "${NumberFormatUtils.getNumberWithDigital(investData?.packetsAmount!!, 2)}元"
        } else {
            ll_invest_red_packet.visibility = View.GONE
        }
        //加息券额度
        if (investData?.interestRate != null && investData?.interestRate != 0.toDouble()) {
            tv_ticket_used_amount.text = "+${NumberFormatUtils.getFormatNumber(investData?.interestRate!! * 100, 1)} %"
        } else {
            ll_invest_ticket.visibility = View.GONE
        }
        //预计收益
        tv_earnings_rate.text = NumberFormatUtils.getNumberWithDigital(investData?.everydayIncome!!, 2)
        //设置还本付息还是分期还款
        if (investData?.payType == 1) {
            //一次性还本付息
            tv_repayment_type.text = "一次性还本付息"
        } else if (investData?.payType == 2) {
            //分期还款
            tv_repayment_type.text = "先息后本"
        }
        if (investData?.createTime!!.toLong() - 1525754792000 <= 0) {
            rl_loan_agreement.visibility = View.GONE
        }
    }

    override fun retryGetData() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
        //项目详情
            R.id.tv_project_detail -> {
                MobclickAgent.onEvent(this, "wd_tzls_xmxq")
                ARouter.getInstance().build(ArouterConstant.ARRANGE_PROJECT_DETAIL)
                        .withString(ArouterParamConstant.ORDER_ID, investData?.orderId)
                        .withString(ArouterParamConstant.SUBJECT_NAME, investData?.subjectName)
                        .navigation()
            }
        //借款协议
            R.id.rl_loan_agreement -> {
                MobclickAgent.onEvent(this, "wd_tzls_jkht")
                when (investData?.loanType) {
                    "1" -> {
                        //车贷借款协议
                        HtmlUrlActivity.launch(String.format(NetConstant.H5_LOANC_AGREEMENT, investData?.orderId + "&investOrderId=" + investData?.investOrderId), true)

                    }
                    "3" -> {
                        //企业贷借款协议
                        HtmlUrlActivity.launch(String.format(NetConstant.H5_LOAN_AGREEMENT, investData?.orderId + "&investOrderId=" + investData?.investOrderId), true)
                    }
                }

            }
        //还款日期
            R.id.rl_repayment_date -> {
                MobclickAgent.onEvent(this, "wd_wdtz_hkrq")
                //还款日期
                DialogFactory.instance.getBottomDialog(R.layout.dialog_my_invest_detail_repayment_date, object : CommonFragmentDialog.ILogicSetter {
                    override fun setLogic(fragmentDialog: CommonFragmentDialog, rootView: View) {
                        //投资时间
                        rootView.findViewById<TextView>(R.id.tv_invest_date).text = TimeUtils.milliseconds2String(investData?.createTime!!, "yyyy-MM-dd")
                        //余额到账时间
                        if (investData?.expiredTime != 0.toLong()) {
                            rootView.findViewById<TextView>(R.id.tv_interested_date).text = TimeUtils.milliseconds2String(investData?.expiredTime!!, "yyyy-MM-dd")
                        } else if (investData?.endTime != 0.toLong()) {
                            tv_repayment_date.text = TimeUtils.milliseconds2String(investData?.endTime!!, "yyyy/MM/dd")
                        }
                        //投资金额
                        rootView.findViewById<TextView>(R.id.tv_invest_amount).text = "投资${investData?.investMoney!!}元"

                        if (investData?.orderStatus == 5) {
                            rootView.findViewById<ImageView>(R.id.iv_interesting_date).setImageResource(R.mipmap.icon_my_invest_detail_interested)
                            rootView.findViewById<ImageView>(R.id.iv_interested_date).setImageResource(R.mipmap.icon_my_invest_detail_interested)
                        }
                        //回款期数
                        if (investData?.orderStatus != 2 && investData?.payType == 2) {
                            rootView.findViewById<TextView>(R.id.tv_interesting_period).text = "回款日期（${investData?.period}/${investData?.totalPeriod}）"
                            //计息时间
                            rootView.findViewById<TextView>(R.id.tv_interesting_date).text = TimeUtils.milliseconds2String(investData?.payTime!!, "yyyy-MM-dd")
                        } else {
                            rootView.findViewById<TextView>(R.id.tv_interesting_period).text = "计息"
                            //计息时间
                            rootView.findViewById<TextView>(R.id.tv_interesting_date).text = TimeUtils.milliseconds2String(investData?.createTime!!, "yyyy-MM-dd")
                        }
                    }
                }).show(supportFragmentManager, "repayment_dialog")
            }
        }
    }
}
