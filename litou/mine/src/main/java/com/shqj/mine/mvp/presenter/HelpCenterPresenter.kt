package com.shqj.mine.mvp.presenter

import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.HelpCenterBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.BaseObserver
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import com.shqj.mine.mvp.view.IHelpCenterView
import java.util.*

/**
 * Created by SleepYM09 on 2018/3/30.
 *
 * blog: www.sleepym09.com
 */
class HelpCenterPresenter(private val view: IHelpCenterView<HelpCenterBean>, private val statesLayoutManager: StatesLayoutManager) {

    fun requestData() {
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.ID, 67)
        AppRequest.INSTANCE.getHelpCenterItemData(treeMap, BaseObserver(object : OnLoadDataListener<HelpCenterBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: HelpCenterBean) {
                view.loadData(data)
            }
        }))
    }

}