package com.shqj.mine.mvp.presenter

import android.content.Context
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.UUApplication
import com.sleep.uulib.bean.LoginStepTwoBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.view.LoginView
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import com.sleep.uulib.netWork.ProgressObserver
import com.sleep.uulib.util.EventBusUtil
import com.umeng.message.PushAgent
import java.util.*

/**
 * Created by SleepYM09 on 2017/11/10.
 *
 * blog: www.sleepym09.com
 */
open class LoginPresenter(private val context: Context, private val loginView: LoginView, private val statesLayoutManager: StatesLayoutManager) {
    /**
     * 登录
     */
    fun login(mobilePhone: String?, password: String?) {
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.MOBILE_PHONE, mobilePhone)
        treeMap.put(NetConstant.PASSWORD, password)
        if(PushAgent.getInstance(context).registrationId.isNotEmpty()){
            treeMap.put(NetConstant.DEVICE_ID, PushAgent.getInstance(context).registrationId)
            treeMap.put(NetConstant.DEVICE_TYPE, 1)
        }
        AppRequest.INSTANCE.login(treeMap, ProgressObserver(context, false, object : OnLoadDataListener<LoginStepTwoBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: LoginStepTwoBean) {
                //更新用户数据
                UUApplication.user = data.userInfo
                loginView.loginSuccess()
                EventBusUtil.postLoginEvent(data.userInfo.getMobilePhone()!!)
            }
        }))
    }
}