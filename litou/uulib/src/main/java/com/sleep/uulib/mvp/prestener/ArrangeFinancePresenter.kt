package com.sleep.uulib.mvp.presenter

import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.FinanceListBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.view.LoadListDataView
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.BaseObserver
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import java.util.*

/**
 * Created by SleepYM09 on 2017/11/9.
 *
 * blog: www.sleepym09.com
 */
class ArrangeFinancePresenter(val loadListDataView: LoadListDataView<FinanceListBean>,
                              val statesLayoutManager: StatesLayoutManager) {

    /**
     * 获取理财界面数据
     */
    fun getFinanceManagerList(requestPage: Int,bidType: Int = 0) {
        val start = requestPage * NetConstant.PAGE_SIZE
        //start = 上一个结束的position
        val treeMap = TreeMap<String, Any>()
        treeMap.put(NetConstant.LIMIT,NetConstant.PAGE_SIZE)
        treeMap.put(NetConstant.STATRT,start)
        if(bidType != 0){
            treeMap.put(NetConstant.SUBJECT_TYPE,bidType)
        }
        AppRequest.INSTANCE.getFinanceManagerList(treeMap, BaseObserver(object : OnLoadDataListener<FinanceListBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                loadListDataView.loadDataFailure(e,errorCode)
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: FinanceListBean) {
                statesLayoutManager.showContent()
                if(data.financingTotalCount == 0){
                    loadListDataView.loadNoData()
                }else if(data.financingTotalCount <= start){
                    //已经全部加载完
                    loadListDataView.loadNoMoreData(data)
                }else if(start != 0 && data.financingTotalCount > start){
                    //加载到更多数据
                    loadListDataView.loadMoreData(data)
                }else {
                    loadListDataView.loadData(data)
                }
            }

        }))
    }
}