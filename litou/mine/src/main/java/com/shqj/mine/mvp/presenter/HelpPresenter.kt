package com.shqj.mine.mvp.presenter

import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.HelpBean
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.BaseObserver
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import com.shqj.mine.mvp.view.IHelpCenterView
import java.util.*

/**
 * Created by SleepYM09 on 2018/4/2.
 *
 * blog: www.sleepym09.com
 */
class HelpPresenter(private val view: IHelpCenterView<HelpBean>, private val statesLayoutManager: StatesLayoutManager) {
    fun requestHelpCenterMenuData() {
        val treeMap = TreeMap<String, Any?>()
        AppRequest.INSTANCE.getHelpCenterMenuData(treeMap, BaseObserver(object : OnLoadDataListener<HelpBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: HelpBean) {
                view.loadData(data)
            }
        }))

    }
}