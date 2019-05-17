package com.shqj.mine.mvp.presenter

import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.InvestDetailBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.view.LoadListDataView
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.BaseObserver
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import java.util.*

/**
 * Created by SleepYM09 on 2017/12/11.
 *
 * blog: www.sleepym09.com
 */
class FinancialDetailPresenter(private val view: LoadListDataView<InvestDetailBean>, private val statesLayoutManager: StatesLayoutManager) {
    fun getFinancialDetailData(requestPage: Int, foundType: Int = 0) {
        val start = requestPage * NetConstant.PAGE_SIZE
        val treeMap = TreeMap<String, Any?>()
        if (foundType != 0) {
            treeMap.put(NetConstant.FUND_TYPE, foundType)
        }
        treeMap.put(NetConstant.LIMIT, NetConstant.PAGE_SIZE)
        treeMap.put(NetConstant.STATRT, start)
        AppRequest.INSTANCE.getFinancialDetailData(treeMap, BaseObserver(object : OnLoadDataListener<InvestDetailBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                view.loadDataFailure(e, errorCode)
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: InvestDetailBean) {
                statesLayoutManager.showContent()
                //data.page在没数据的时候就会为空，不论是第几页，所以得分开判断
                if (data.projects != null && data.projects.data != null) {
                    if (data.projects.isHasNextPage) {
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
}