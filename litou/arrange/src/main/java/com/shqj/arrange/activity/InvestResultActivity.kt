package com.shqj.arrange.activity

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.shqj.arrange.R
import com.sleep.commonlib.util.SizeUtils
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ArouterParamConstant
import com.sleep.uulib.util.NumberFormatUtils
import com.sleep.uulib.util.NumberUtils
import com.sleep.uulib.uubase.UUBaseActivity
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.arragne_activity_invest_result.*

/**
 * params :
 *  IS_SUCCESS:成功失败标识
 *  BID_NAME:标的名字
 *  PRICE:价格
 *
 */
@Route(path = ArouterConstant.ARRANGE_INVEST_RESULT)
class InvestResultActivity : UUBaseActivity(), View.OnClickListener {

    private var mIsSuccess: Boolean = false
    private var mBidName: String? = null
    private var mPrice: String? = null

    override fun getLayoutResourse(): Int {
        return R.layout.arragne_activity_invest_result
    }

    override fun initView() {
        setTitle(getString(R.string.title_invest_result))
        mIsSuccess = intent.getBooleanExtra(ArouterParamConstant.IS_SUCCESS, false)
        mBidName = intent.getStringExtra(ArouterParamConstant.BID_NAME)
        mPrice = intent.getStringExtra(ArouterParamConstant.PRICE)
        val params = iv_status_icon.layoutParams as ViewGroup.MarginLayoutParams
        if (mIsSuccess) {
            //购买成功
            iv_status_icon.setImageResource(R.mipmap.icon_result_success)
            tv_status.text = getString(R.string.string_invest_success)
            params.topMargin = SizeUtils.dp2px(this, 90f)
            ll_integral.visibility = View.GONE
            tv_integral.text = NumberFormatUtils.getFormatNumberWithNoDigital(NumberUtils.mul(java.lang.Double.parseDouble(mPrice), 0.1)) + "积分"
            ll_bid_name.visibility = View.VISIBLE
            tv_bid_name.text = mBidName
            ll_price.visibility = View.VISIBLE
            tv_price.text = "${mPrice}元"
            bt_btn1.text = "继续投资"
        } else {
            //购买失败
            iv_status_icon.setImageResource(R.mipmap.icon_result_failure)
            tv_status.text = getString(R.string.string_invest_failed)
            params.topMargin = SizeUtils.dp2px(this, 110f)
            ll_integral.visibility = View.GONE
            ll_bid_name.visibility = View.INVISIBLE
            ll_price.visibility = View.INVISIBLE
            bt_btn1.text = "重新投资"
        }
        iv_status_icon.layoutParams = params
        bt_btn1.setOnClickListener(this)
        bt_btn2.setOnClickListener(this)
    }

    override fun initData() {

    }

    override fun retryGetData() {
        mStateManager.showContent()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_btn1 -> {
                if (mIsSuccess) {
                    //继续投资
                    MobclickAgent.onEvent(this, "sy_tzcg_ljtz_btn")
                    ARouter.getInstance().build(ArouterConstant.APP_MAIN_ACTIVITY).withInt(ArouterParamConstant.CHOOSE_POSITION, 1).navigation()
                } else {
                    //点击重新投资，回到项目详情页
                    setResult(Activity.RESULT_CANCELED)
                    finish()
                }
            }
            R.id.bt_btn2 -> {
                //投资记录
                MobclickAgent.onEvent(this, "sy_tzcg_jl_btn")
                ARouter.getInstance().build(ArouterConstant.MINE_MY_INVEST).withBoolean(ArouterParamConstant.IS_BACK_FINISH_CURRENT, false).navigation()
            }
        }
    }

}
