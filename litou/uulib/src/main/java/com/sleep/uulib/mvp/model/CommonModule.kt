package com.sleep.uulib.mvp.model

import android.content.Context
import com.sleep.uulib.UUApplication
import com.sleep.uulib.bean.QueryBankBean
import com.sleep.uulib.bean.QueryUserInfoBean
import com.sleep.uulib.bean.VerificationCodeBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.view.CommonView
import com.sleep.uulib.mvp.view.QueryUserInfoView
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.BaseObserver
import com.sleep.uulib.netWork.OnLoadDataListener
import com.sleep.uulib.netWork.ProgressObserver
import java.util.*

/**
 * Created by SleepYM09 on 2017/12/6.
 * blog: www.sleepym09.com
 *
 * CommonModule中的失败情况不自动处理，调用者根据errorCode自行判断stateManager状态
 */
class CommonModule {
    companion object {
        /**
         * 查询用户信息
         */
        fun queryUserInfo(mView: QueryUserInfoView) {
            AppRequest.INSTANCE.queryUserInfo(TreeMap(), BaseObserver(object : OnLoadDataListener<QueryUserInfoBean> {
                override fun onFailure(e: Throwable?, errorCode: Int) {
                    mView.queryUserInfoFailure(e, errorCode)
                }

                override fun onSuccess(data: QueryUserInfoBean) {
                    UUApplication.user = data.userInfo
                    mView.queryUserInfoSuccess(data)
                    queryBank(mView)
                }
            }))
        }

        private fun queryBank(mView: QueryUserInfoView) {
            AppRequest.INSTANCE.queryBank(TreeMap(), BaseObserver(object : OnLoadDataListener<QueryBankBean> {
                override fun onFailure(e: Throwable?, errorCode: Int) {
                    mView.queryBankFailure(e, errorCode)
                }

                override fun onSuccess(data: QueryBankBean) {
                    if (data.bankCardEOs.size > 0) {
                        UUApplication.user?.setBankInfo(data.bankCardEOs[0])
                    }
                    mView.queryBankSuccess(data)
                }
            }))
        }

        public fun refreshUserInfo(mView: CommonView) {
            AppRequest.INSTANCE.queryUserInfo(TreeMap(), BaseObserver(object : OnLoadDataListener<QueryUserInfoBean> {
                override fun onFailure(e: Throwable?, errorCode: Int) {
                    mView.refreshFailure()
                }

                override fun onSuccess(data: QueryUserInfoBean) {
                    UUApplication.user = data.userInfo
                    mView.refreshSuccess()
                }
            }))
        }

        /**
         * 获取验证码
         */
        fun getVerifyCode(context: Context, phone: String, type: String, listener: OnLoadDataListener<VerificationCodeBean>, code: String = "") {
            val treeMap = TreeMap<String, Any?>()
            if (code.isNotEmpty()) {
                treeMap.put(NetConstant.CODE, code)
            }
            treeMap.put(NetConstant.MOBILE_PHONE, phone)
            treeMap.put(NetConstant.VERIFY_CODE_TYPE, type)
            AppRequest.INSTANCE.getVerifyCode(treeMap, ProgressObserver(context, false, listener))
        }
    }
}