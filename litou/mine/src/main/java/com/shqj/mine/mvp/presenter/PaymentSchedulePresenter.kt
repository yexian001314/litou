package com.shqj.mine.mvp.presenter

import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.PaymentScheduleBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.view.LoadListDataView
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.BaseObserver
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import java.util.*

/**
 * Created by SleepYM09 on 2017/12/12.
 *
 * blog: www.sleepym09.com
 */
class PaymentSchedulePresenter(private val view: LoadListDataView<PaymentScheduleBean>, private val statesLayoutManager: StatesLayoutManager) {

    /**
     * @param foundType 1-->当月回款  2-->三个月内  3-->三个月后
     */
    fun getPaymentSchedule(requestPage: Int, foundType: Int) {
        val start = requestPage * NetConstant.PAGE_SIZE
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.QUERY_TYPE, foundType)
        treeMap.put(NetConstant.LIMIT, NetConstant.PAGE_SIZE)
        treeMap.put(NetConstant.STATRT, start)
        AppRequest.INSTANCE.getPaymentSchedule(treeMap, BaseObserver(object : OnLoadDataListener<PaymentScheduleBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                view.loadDataFailure(e, errorCode)
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: PaymentScheduleBean) {
                statesLayoutManager.showContent()
                //data.page在没数据的时候就会为空，不论是第几页，所以得分开判断
                if (data.totalCount == 0) {
                    view.loadNoData()
                }
                if (data.totalCount <= NetConstant.PAGE_SIZE) {
                    view.loadDataAndNoMore(data)
                } else {
                    if (requestPage == 0) {
                        view.loadData(data)
                    } else {
                        if ((requestPage + 1) * NetConstant.PAGE_SIZE < data.totalCount) {
                            view.loadMoreData(data)
                        } else {
                            view.loadNoMoreData(data)
                        }
                    }
                }
            }

        }))
    }
}