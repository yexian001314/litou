package com.shqj.mine.mvp.presenter

import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.ChooseWelfareBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.view.LoadListDataView
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.BaseObserver
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import java.util.*

/**
 * Created by SleepYM09 on 2017/12/14.
 *
 * blog: www.sleepym09.com
 */
class ChooseMyWelfarePresenter(private val view: LoadListDataView<ChooseWelfareBean>, private val statesLayoutManager: StatesLayoutManager) {

    /**
     *
     */
    fun getCanUserWelfare(orderId: String,totalMoney:Int){
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.STATES,1)
        treeMap.put(NetConstant.ORDER_ID,orderId)
        treeMap.put(NetConstant.TOTAL_MONEY,totalMoney)
        AppRequest.INSTANCE.getCanUserWelfare(treeMap, BaseObserver(object: OnLoadDataListener<ChooseWelfareBean>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode,statesLayoutManager)
            }

            override fun onSuccess(data: ChooseWelfareBean) {
                view.loadDataAndNoMore(data)
            }
        }))
    }

    fun getCanUserRedPacket(orderId: String,totalMoney:Int){
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.STATES,1)
        treeMap.put(NetConstant.ORDER_ID,orderId)
        treeMap.put(NetConstant.TOTAL_MONEY,totalMoney)
        AppRequest.INSTANCE.getCanUserWelfare(treeMap, BaseObserver(object: OnLoadDataListener<ChooseWelfareBean>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode,statesLayoutManager)
            }

            override fun onSuccess(data: ChooseWelfareBean) {
                view.loadDataAndNoMore(data)
            }
        }))
    }
}