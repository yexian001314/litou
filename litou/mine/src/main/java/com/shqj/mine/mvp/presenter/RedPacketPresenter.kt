package com.shqj.mine.mvp.presenter

import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.UUApplication
import com.sleep.uulib.bean.RedEnvelopeBean
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
class RedPacketPresenter(private val view: LoadListDataView<RedEnvelopeBean>, private val statesLayoutManager: StatesLayoutManager) {
    fun getRedPacketData(requestPage: Int) {
        val start = requestPage * NetConstant.PAGE_SIZE
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.PACKETS_USER_ID, UUApplication.user?.getId())
        treeMap.put(NetConstant.LIMIT, NetConstant.PAGE_SIZE)
        treeMap.put(NetConstant.STATRT, start)
        AppRequest.INSTANCE.getRedPacketData(treeMap, BaseObserver(object : OnLoadDataListener<RedEnvelopeBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                view.loadDataFailure(e, errorCode)
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: RedEnvelopeBean) {
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