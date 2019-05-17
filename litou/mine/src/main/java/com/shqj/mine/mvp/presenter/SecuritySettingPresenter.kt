package com.shqj.mine.mvp.presenter

import android.app.Activity
import com.sleep.commonlib.base.BaseEvent
import com.sleep.commonlib.base.EventCode
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.UUApplication
import com.sleep.uulib.bean.LoginStepTwoBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.netWork.*
import com.sleep.uulib.util.EventBusUtil
import com.shqj.mine.mvp.view.SecuritySettingView
import org.simple.eventbus.EventBus
import java.util.*

/**
 * Created by SleepYM09 on 2017/12/8.
 *
 * blog: www.sleepym09.com
 */
class SecuritySettingPresenter(val view: SecuritySettingView,val statesLayoutManager: StatesLayoutManager) {
    fun logout(){
        AppRequest.INSTANCE.logout(TreeMap(), BaseObserver(object : OnLoadDataListener<Any>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                view.getDataFinish()
                NetCommonMethod.judgeError(errorCode,statesLayoutManager)
            }

            override fun onSuccess(data: Any) {
                EventBus.getDefault().post(BaseEvent<Any>(EventCode.LOGOUT))
                view.logoutSuccess()
            }
        }))
    }
    /**
     * 登录 效验密码
     */
    fun login(mobilePhone: String?, password: String?,context:Activity) {
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.MOBILE_PHONE, mobilePhone)
        treeMap.put(NetConstant.PASSWORD, password)
        AppRequest.INSTANCE.login(treeMap, ProgressObserver(context, false, object : OnLoadDataListener<LoginStepTwoBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                view.efficacyFailure()
            }

            override fun onSuccess(data: LoginStepTwoBean) {
                //更新用户数据
                UUApplication.user = data.userInfo
                EventBusUtil.postLoginEvent(data.userInfo.getMobilePhone()!!)
                view.efficacySuccess()
            }
        }))
    }
}