package com.shqj.arrange.mvp.prestener

import com.sleep.commonlib.util.PhoneUtils
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.CreateOrderBean
import com.sleep.uulib.bean.InvestResultBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.BaseObserver
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import com.shqj.arrange.mvp.view.InvestView
import java.util.*

/**
 * Created by SleepYM09 on 2017/12/14.
 *
 * blog: www.sleepym09.com
 */
class InvestPresenter(private val view: InvestView, private val statesLayoutManager: StatesLayoutManager) {
    /**
     * 获取PayCode并进行支付
     */
    fun invest(orderId: String?, totalMoney: String, password: String?, welfraeId: Long? = 0, ticketId: Long? = 0) {
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.ORDER_ID, orderId)
        treeMap.put(NetConstant.TOTAL_MONEY, totalMoney)
        if (welfraeId != null && welfraeId != 0.toLong()) {
            treeMap.put(NetConstant.REDPACKETS_ID, welfraeId)
        }
        if (ticketId != null && ticketId != 0.toLong()) {
            treeMap.put(NetConstant.INTEREST_RATE_ID, ticketId)
        }
        treeMap.put(NetConstant.TRAN_PAW, password)
        treeMap.put(NetConstant.DEVICE_TYPE, 1)
        AppRequest.INSTANCE.creatInvestOrder(treeMap, BaseObserver(object : OnLoadDataListener<CreateOrderBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
                view.investFailure()
            }

            override fun onSuccess(data: CreateOrderBean) {
                if (data.orderCode.isEmpty()) {
                    view.investFailure()
                } else {
                    investStepTwo(data.payCode)
                }
            }
        }))
    }

    /**
     * 根据PayCode进行支付
     */
    private fun investStepTwo(payCode: String) {
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.PAY_CODE, payCode)
        treeMap.put(NetConstant.IMEI, PhoneUtils.getIMEI())
        AppRequest.INSTANCE.invest(treeMap, BaseObserver(object : OnLoadDataListener<InvestResultBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                view.investFailure()
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: InvestResultBean) {
                view.investSuccess(data)
            }

        }))
    }
}