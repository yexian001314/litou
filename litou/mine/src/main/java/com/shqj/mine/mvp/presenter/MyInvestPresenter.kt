package com.shqj.mine.mvp.presenter

import android.content.Context
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.InvestRecordBean
import com.sleep.uulib.bean.QueryInvestByIdBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.view.LoadListDataView
import com.sleep.uulib.netWork.*
import com.shqj.mine.mvp.QueryInvestListCallBack
import java.util.*

/**
 * Created by SleepYM09 on 2017/12/11.
 *
 * blog: www.sleepym09.com
 */
class MyInvestPresenter(private val view: LoadListDataView<InvestRecordBean>, private val statesLayoutManager: StatesLayoutManager) {

    fun getMyInvestData(requestPage: Int, foundType: Int) {
        val start = requestPage * NetConstant.PAGE_SIZE
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.KEY_QUERY_INVENST_TYPE, foundType)
        treeMap.put(NetConstant.LIMIT, NetConstant.PAGE_SIZE)
        treeMap.put(NetConstant.STATRT, start)
        AppRequest.INSTANCE.getMyInvestData(treeMap, BaseObserver(object : OnLoadDataListener<InvestRecordBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                view.loadDataFailure(e, errorCode)
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: InvestRecordBean) {
                statesLayoutManager.showContent()
                //data.page在没数据的时候就会为空，不论是第几页，所以得分开判断
                if (data.page != null && data.page.data != null) {
                    if (data.page.isHasNextPage) {
                        if (requestPage == 0) {
                            view.loadData(data)
                        } else {
                            view.loadMoreData(data)
                        }
                    } else {
                        if (requestPage == 0) {
                            view.loadDataAndNoMore(data)
                        } else {
                            view.loadNoMoreData(data)
                        }
                    }
                } else {
                    if (requestPage == 0) {
                        view.loadNoData()
                    } else if (requestPage != 0) {
                        view.loadNoMoreData(data)
                    }
                }
            }

        }))
    }

    fun queryInvestListByOrderId(context: Context,orderId: String,investOrderid : String,callBack: QueryInvestListCallBack){
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.ORDER_ID,orderId)
        treeMap.put(NetConstant.INVEST_ORDER_ID,investOrderid)
        AppRequest.INSTANCE.queryInvestListByOrderId(treeMap,ProgressObserver(context,false,object : OnLoadDataListener<QueryInvestByIdBean>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                view.loadDataFailure(e, errorCode)
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: QueryInvestByIdBean) {
                callBack.queryInvestListByOrderIdSuccess(data)
            }

        }))
    }
}