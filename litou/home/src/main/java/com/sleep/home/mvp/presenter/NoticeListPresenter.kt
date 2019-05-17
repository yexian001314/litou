package com.sleep.home.mvp.presenter

import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.HomeAnnocementsBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.view.LoadListDataView
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.BaseObserver
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import java.util.*

/**
 * Created by SleepYM09 on 2018/1/2.
 *
 * blog: www.sleepym09.com
 */
class NoticeListPresenter(private val view: LoadListDataView<HomeAnnocementsBean>,
                          private val statesLayoutManager: StatesLayoutManager) {
    fun getAnnocements() {
        val treeMap = TreeMap<String, Any>()
        treeMap.put(NetConstant.ANNOUNCEMENT_TYPE, 1)
        AppRequest.INSTANCE.getAnnocementsData(treeMap, BaseObserver(object : OnLoadDataListener<HomeAnnocementsBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: HomeAnnocementsBean) {
                statesLayoutManager.showContent()
                view.loadDataAndNoMore(data)
            }
        }))
    }
}