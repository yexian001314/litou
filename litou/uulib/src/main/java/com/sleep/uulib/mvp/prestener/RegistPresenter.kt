package com.shqj.mine.mvp.presenter

import android.content.Context
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.UUApplication
import com.sleep.uulib.bean.CheckPhoneBean
import com.sleep.uulib.bean.RegisterInfo
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.view.RegistView
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import com.sleep.uulib.netWork.ProgressObserver
import com.sleep.uulib.util.EventBusUtil
import java.util.*

/**
 * Created by SleepYM09 on 2017/11/13.
 *
 * blog: www.sleepym09.com
 */
class RegistPresenter(private val context: Context,private val view: RegistView,private val statesLayoutManager: StatesLayoutManager) {

    fun regist(treeMap: TreeMap<String,Any?>){
        AppRequest.INSTANCE.regist(treeMap,ProgressObserver(context,false,object : OnLoadDataListener<RegisterInfo>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode,statesLayoutManager)
            }

            override fun onSuccess(data: RegisterInfo) {
                UUApplication.user = data.userInfo
                EventBusUtil.postLoginEvent(data.userInfo.getMobilePhone()!!)
                view.registSuccess(data)
            }

        }))
    }

    /**
     * 检测手机号是否存在
     */
    fun checkPhoneIsExist(phoneNum: String) {
        val treeMap = TreeMap<String, Any>()
        treeMap.put(NetConstant.MOBILE_PHONE, phoneNum)
        AppRequest.INSTANCE.checkPhoneIsUsed(treeMap, ProgressObserver(context,false,object : OnLoadDataListener<CheckPhoneBean>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: CheckPhoneBean) {
                view.checkSuccess(data)
            }

        }))
    }
}