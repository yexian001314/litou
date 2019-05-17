package com.shqj.litouwang.mvp

import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.SplashBannerBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.BaseObserver
import com.sleep.uulib.netWork.OnLoadDataListener
import java.util.*

/**
 * Created by SleepYM09 on 2018/3/15.
 *
 * blog: www.sleepym09.com
 */
class SplashPresenter(val view: SplashView, val statesLayoutManager: StatesLayoutManager) {
    fun getSplashBannerUrl() {
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.STATES, 1)
        treeMap.put(NetConstant.PLATFORM, 3)
        AppRequest.INSTANCE.getSplashBannerUrl(treeMap, BaseObserver(object : OnLoadDataListener<SplashBannerBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                view.getStartBannerFailure()
//                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: SplashBannerBean) {
                view.getStartBannerSuccess(data)
            }

        }))
    }
}