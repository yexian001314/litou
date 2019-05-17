package com.shqj.mine.mvp.presenter

import android.content.Context
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.CheckPhoneBean
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import com.sleep.uulib.netWork.ProgressObserver
import java.util.*
import com.sleep.uulib.mvp.view.LoginStepOnView

/**
 * Created by SleepYM09 on 2017/11/8.
 *
 * blog: www.sleepym09.com
 */
class LogintStepOnePresenter(private val context: Context, val loginView: LoginStepOnView, val statesLayoutManager: StatesLayoutManager) {
    /**
     * 检测手机号是否存在
     */
    fun checkPhoneIsExist(treeMap: TreeMap<String, Any>) {
        AppRequest.INSTANCE.checkPhoneIsUsed(treeMap, ProgressObserver(context,false,object : OnLoadDataListener<CheckPhoneBean>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                loginView.getDataFinish()
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: CheckPhoneBean) {
                loginView.checkSuccess(data)
            }

        }))
    }
}