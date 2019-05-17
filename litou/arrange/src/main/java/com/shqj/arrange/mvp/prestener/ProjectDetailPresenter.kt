package com.shqj.arrange.mvp.presenter

import com.shqj.arrange.mvp.view.ProjectDetailView
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.UUApplication
import com.sleep.uulib.bean.CheckInvestedBean
import com.sleep.uulib.bean.ProjectDetailBean
import com.sleep.uulib.bean.ProjectDetailLimit
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.BaseObserver
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import java.util.*

/**
 * Created by SleepYM09 on 2017/12/1.
 *
 * blog: www.sleepym09.com
 */
class ProjectDetailPresenter(private val mView: ProjectDetailView,
                             private val statesLayoutManager: StatesLayoutManager) {
    fun getProjectDetail(orderId: String){
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.ORDER_ID,orderId)
        AppRequest.INSTANCE.getProjectDetail(treeMap, BaseObserver(object: OnLoadDataListener<ProjectDetailBean>{
            override fun onSuccess(data: ProjectDetailBean) {
                statesLayoutManager.showContent()
                mView.getProjectDetailSuccess(data)
                if(UUApplication.user != null && data.financingOrder.orderStatus != 5){
                    //未登录和非招标中状态才需要区分用户是否投标
                    getCUrrentUserIsInvest(UUApplication.user?.getId()!!,data.financingOrder.orderId)
                }
                getProjectDetailLimit()
            }

            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode,statesLayoutManager)
            }
        }))
    }

    /**
     * 获取当前用户是否投资过当前标的
     */
    fun getCUrrentUserIsInvest(investerUid: Long,orderId: String){
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.INVESTER_UID,investerUid)
        treeMap.put(NetConstant.ORDER_ID,orderId)
        AppRequest.INSTANCE.checkInvested(treeMap, BaseObserver(object: OnLoadDataListener<CheckInvestedBean>{
            override fun onSuccess(data: CheckInvestedBean) {
                statesLayoutManager.showContent()
                mView.checkInvestedSuccess(data)
            }

            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode,statesLayoutManager)
            }
        }))
    }

    /**
     * 获取详情中的限额
     */
    fun getProjectDetailLimit(){
        AppRequest.INSTANCE.getProjectDetailLimitData(BaseObserver(object : OnLoadDataListener<ProjectDetailLimit>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode,statesLayoutManager)
            }

            override fun onSuccess(data: ProjectDetailLimit) {
                mView.getProjectDetailLimitSuccess(data)
            }

        }))
    }
}