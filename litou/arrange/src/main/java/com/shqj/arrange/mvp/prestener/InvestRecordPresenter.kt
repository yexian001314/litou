package com.shqj.arrange.mvp.presenter

import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.InvestRecordListBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.view.LoadListDataView
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.BaseObserver
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import java.util.*

/**
 * Created by SleepYM09 on 2017/12/5.
 *
 * blog: www.sleepym09.com
 */
class InvestRecordPresenter(val loadListDataView: LoadListDataView<InvestRecordListBean>,
                            val statesLayoutManager: StatesLayoutManager) {
    fun getInvestRecord(requestPage: Int, orderId: String, orderStatus: IntArray) {
        val start = requestPage * NetConstant.PAGE_LARGE_SIZE
        //start = 上一个结束的position
        val treeMap = TreeMap<String, Any>()
        treeMap.put(NetConstant.LIMIT, NetConstant.PAGE_LARGE_SIZE)
        treeMap.put(NetConstant.STATRT, start)
        treeMap.put(NetConstant.ORDER_ID, orderId)
        treeMap.put(NetConstant.ORDER_STATUS_LIST, orderStatus)
        treeMap.put(NetConstant.IS_ASC, false)
        AppRequest.INSTANCE.getInvestRecord(treeMap, BaseObserver(object : OnLoadDataListener<InvestRecordListBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                loadListDataView.loadDataFailure(e, errorCode)
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: InvestRecordListBean) {
                statesLayoutManager.showContent()
                //data.page在没数据的时候就会为空，不论是第几页，所以得分开判断
                if (data.page != null && data.page.data != null) {
                    if (data.page.isHasNextPage) {
                        if (requestPage == 0) {
                            loadListDataView.loadData(data)
                        } else {
                            loadListDataView.loadMoreData(data)
                        }
                    } else {
                        if (requestPage == 0) {
                            loadListDataView.loadDataAndNoMore(data)
                        } else {
                            loadListDataView.loadNoMoreData(data)
                        }
                    }
                } else {
                    if (requestPage == 0) {
                        loadListDataView.loadNoData()
                    } else if (requestPage != 0) {
                        loadListDataView.loadNoMoreData(data)
                    }
                }
            }
        }))
    }
}
