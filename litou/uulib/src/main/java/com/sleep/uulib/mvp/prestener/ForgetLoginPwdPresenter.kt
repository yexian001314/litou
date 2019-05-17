package com.shqj.mine.mvp.presenter

import android.content.Context
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.FindPasswordBean
import com.sleep.uulib.bean.VerificationCodeBean
import com.sleep.uulib.mvp.view.ForgetLoginPwdView
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import com.sleep.uulib.netWork.ProgressObserver
import java.util.*

/**
 * Created by SleepYM09 on 2017/11/15.
 *
 * blog: www.sleepym09.com
 */
class ForgetLoginPwdPresenter(private val context: Context,
                              private val forgetLoginPwdView: ForgetLoginPwdView,
                              private val statesLayoutManager: StatesLayoutManager) {
    /**
     * 获取验证码
     */
    fun getVerifyCode(treeMap: TreeMap<String, Any?>) {
        AppRequest.INSTANCE.getVerifyCode(treeMap, ProgressObserver(context, false, object : OnLoadDataListener<VerificationCodeBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: VerificationCodeBean) {
                //更新用户数据
                forgetLoginPwdView.getVerifyCodeSuccess()
            }
        }))
    }

    /**
     * 找回密码
     */
    fun findPassword(treeMap: TreeMap<String, Any?>) {
        AppRequest.INSTANCE.findPassword(treeMap, ProgressObserver(context, false, object : OnLoadDataListener<FindPasswordBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: FindPasswordBean) {
                //更新用户数据
//                UUApplication.user = data.userInfo
                forgetLoginPwdView.findPasswordSuccess()
            }
        }))
    }
}