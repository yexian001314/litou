package com.shqj.arrange.fragment


import android.view.View
import android.widget.TextView
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.uubase.HtmlUrlActivity
import com.sleep.uulib.uubase.UUBaseFragment
import com.shqj.arrange.R
import kotlinx.android.synthetic.main.arrange_fragment_product_introduction.*


/**
 * 详情中产品介绍页.
 */
class ProductIntroductionFragment : UUBaseFragment() {

    override fun initView(view: View) {

    }

    override fun onFirstUserVisible() {
    }

    override fun getLayoutResourse(): Int {
        return R.layout.arrange_fragment_product_introduction
    }

    override fun initData() {
    }

    override fun retryGetData() {
    }

    /**
     * 设置数据
     * @param
     * loanType:借款类型 1 -> 车贷  2 -> 房贷 3 -> 企业贷
     * projectNote: 产品介绍
     * reskConrol: 产品保障
     * loanUse: 资金用途
     * repaymentSource: 还款来源
     */
    fun setData(orderId: String, loanType: String, projectNote: String?, reskConrol: String?, loanUse: String? = "", repaymentSource: String? = "") {
        mRootView.findViewById<TextView>(R.id.tv_product_introduction).text = projectNote
        mRootView.findViewById<TextView>(R.id.tv_product_safeguard).text = reskConrol
        when (loanType) {
            "1" -> {
                //车贷
                mRootView.findViewById<View>(R.id.ll_use_of_funds).visibility = View.GONE
                mRootView.findViewById<View>(R.id.ll_source_of_repayment).visibility = View.GONE
            }
            "3" -> {
                //企业贷、有资金用途和还款来源
                mRootView.findViewById<View>(R.id.ll_use_of_funds).visibility = View.VISIBLE
                mRootView.findViewById<View>(R.id.ll_source_of_repayment).visibility = View.VISIBLE
                mRootView.findViewById<TextView>(R.id.tv_use_of_funds).text = loanUse
                mRootView.findViewById<TextView>(R.id.tv_source_of_repayment).text = repaymentSource
            }
        }
        ll_loan_agreement.setOnClickListener({
            when (loanType) {
                "1" -> {
                    //车贷借款协议
                    HtmlUrlActivity.launch(String.format(NetConstant.H5_LOANC_AGREEMENT, orderId), true)
                }
                "3" -> {
                    //企业贷借款协议
                    HtmlUrlActivity.launch(String.format(NetConstant.H5_LOAN_AGREEMENT, orderId), true)
                }
            }
        })
    }

}
