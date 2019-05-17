package com.shqj.information.mvp.presenter

import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.HomeBannerBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.view.LoadListDataView
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.BaseObserver
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import java.util.*

/**
 * Created by SleepYM09 on 2017/12/13.
 *
 * blog: www.sleepym09.com
 */
class WonderfulPresenter(private val view: LoadListDataView<HomeBannerBean>, private val statesLayoutManager: StatesLayoutManager) {
    fun getWonderfulData(){
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.STATES,1)
        treeMap.put(NetConstant.PLATFORM,3)
        AppRequest.INSTANCE.getWonderfulData(treeMap, BaseObserver(object: OnLoadDataListener<HomeBannerBean>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode,statesLayoutManager)
            }

            override fun onSuccess(data: HomeBannerBean) {
                view.loadDataAndNoMore(data)
            }

        }))
    }
}