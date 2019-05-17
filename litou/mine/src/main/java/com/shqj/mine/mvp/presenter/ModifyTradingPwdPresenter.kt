package com.shqj.mine.mvp.presenter

import android.content.Context
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.UUApplication
import com.sleep.uulib.bean.ModifyTradingPwdBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import com.sleep.uulib.netWork.ProgressObserver
import com.shqj.mine.mvp.view.ModifyTradingPwdView
import java.util.*

/**
 * Created by SleepYM09 on 2017/12/11.
 *
 * blog: www.sleepym09.com
 */
class ModifyTradingPwdPresenter(private val context: Context,private val view: ModifyTradingPwdView,private val statesLayoutManager: StatesLayoutManager){
    fun modifyTradingPwd(newTradersPw: String,verifyCode: String){
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.MOBILE_PHONE,UUApplication.user?.getMobilePhone())
        treeMap.put(NetConstant.NEW_TRADERS_PW,newTradersPw)
        treeMap.put(NetConstant.VERIFY_CODE,verifyCode)
        AppRequest.INSTANCE.modifyTradingPwd(treeMap,ProgressObserver(context,false,object : OnLoadDataListener<ModifyTradingPwdBean>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                view.getDataFinish()
                NetCommonMethod.judgeError(errorCode,statesLayoutManager)
            }

            override fun onSuccess(data: ModifyTradingPwdBean) {
                view.modifySuccess(data)
            }

        }))
    }
}