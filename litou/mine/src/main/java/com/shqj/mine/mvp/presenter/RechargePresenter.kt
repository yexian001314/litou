package com.shqj.mine.mvp.presenter

import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.PrepaymentBean
import com.sleep.uulib.bean.RealRechargeResultBean
import com.sleep.uulib.bean.RechargeLimitBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.BaseObserver
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import com.shqj.mine.mvp.view.RechargeView
import java.util.*

/**
 * Created by SleepYM09 on 2017/12/8.
 *
 * blog: www.sleepym09.com
 */
class RechargePresenter(val mView: RechargeView, val statesLayoutManager: StatesLayoutManager){
    fun recharge(chargeMoney: String){
        val treeMap = TreeMap<String, Any>()
        treeMap.put(NetConstant.DEVICE_TYPE,1)
        treeMap.put(NetConstant.CHARGE_MONEY,chargeMoney)
        AppRequest.INSTANCE.prerecharge(treeMap, BaseObserver(object: OnLoadDataListener<PrepaymentBean>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                mView.getDataFinish()
                NetCommonMethod.judgeError(errorCode,statesLayoutManager)
            }

            override fun onSuccess(data: PrepaymentBean) {
                val map = TreeMap<String, Any>()
                map.put(NetConstant.DEVICE_TYPE,1)
                map.put(NetConstant.PAY_CODE,data.payCode)
                AppRequest.INSTANCE.realRecharge(map,data.payJumpUrl,realRechargeObserver)
            }
        }))
    }

    private val realRechargeObserver = BaseObserver(object: OnLoadDataListener<RealRechargeResultBean>{
        override fun onFailure(e: Throwable?, errorCode: Int) {
            mView.getDataFinish()
            NetCommonMethod.judgeError(errorCode,statesLayoutManager)
        }

        override fun onSuccess(data: RealRechargeResultBean) {
            mView.getRealRechargeResultSuccess(data)
        }

    })
    fun getRechargeLimitData(){
        AppRequest.INSTANCE.getRechargeLimitData(BaseObserver(object : OnLoadDataListener<RechargeLimitBean>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode,statesLayoutManager)

            }

            override fun onSuccess(data: RechargeLimitBean) {
                mView.loadData(data)
            }

        }))
    }

}