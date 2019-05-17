package com.shqj.mine.mvp.presenter

import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.RechargeLimitBean
import com.sleep.uulib.mvp.view.LoadListDataView
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.BaseObserver
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener

/**
 * Created by SleepYM09 on 2017/12/20.
 *
 * blog: www.sleepym09.com
 */
class RechargeLimitPresenter(private val view: LoadListDataView<RechargeLimitBean>,private val statesLayoutManager: StatesLayoutManager) {
    fun getRechargeLimitData(){
        AppRequest.INSTANCE.getRechargeLimitData(BaseObserver(object : OnLoadDataListener<RechargeLimitBean>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode,statesLayoutManager)
                view.loadDataFailure(e,errorCode)
            }

            override fun onSuccess(data: RechargeLimitBean) {
                view.loadDataAndNoMore(data)
            }

        }))
    }
}