package com.shqj.mine.mvp.presenter

import android.content.Context
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.SetTradingPwdBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import com.sleep.uulib.netWork.ProgressObserver
import com.shqj.mine.mvp.view.SetTradingPwdView
import java.util.*

/**
 * Created by SleepYM09 on 2017/12/11.
 *
 * blog: www.sleepym09.com
 */
class SetTradingPwdPresenter(private val context: Context,private val view: SetTradingPwdView,private val statesLayoutManager: StatesLayoutManager) {
    fun setTradingPwd(traderPwd: String){
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.TRADERS_PW,traderPwd)
        AppRequest.INSTANCE.setTradingPwd(treeMap,ProgressObserver(context,false,object : OnLoadDataListener<SetTradingPwdBean>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                view.getDataFinish()
                NetCommonMethod.judgeError(errorCode,statesLayoutManager)
            }

            override fun onSuccess(data: SetTradingPwdBean) {
                view.setTradingPwdSuccess(data)
            }

        }))
    }
}