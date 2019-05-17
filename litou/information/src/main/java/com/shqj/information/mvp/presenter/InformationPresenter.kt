package com.shqj.information.mvp.presenter

import android.content.Context
import android.util.Log
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.IntegralCommodityBean
import com.sleep.uulib.bean.IsSignedBean
import com.sleep.uulib.bean.SignBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.netWork.*
import com.shqj.information.mvp.view.InformationView
import java.util.*

/**
 * Created by SleepYM09 on 2017/12/13.
 *
 * blog: www.sleepym09.com
 */
class InformationPresenter(private val context: Context, private val view: InformationView, private val statesLayoutManager: StatesLayoutManager) {
    /**
     * 检查用户是否签到
     */
    fun queryUserIsSign() {
        AppRequest.INSTANCE.queryUserIsSign(TreeMap(), BaseObserver(object : OnLoadDataListener<IsSignedBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: IsSignedBean) {
                view.queryUserIsSignSuccess(data)
                getIntegralCommodity()
            }
        }))
    }

    /**
     * 签到
     */
    fun userSign() {
        AppRequest.INSTANCE.userSign(TreeMap(), ProgressObserver(context, false, object : OnLoadDataListener<SignBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: SignBean) {
                view.userSignSuccess(data)
            }

        }))
    }

    /**
     * 获取积分商品
     */
    fun getIntegralCommodity() {
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.STR_PAGE_SIZE, "6")
        treeMap.put(NetConstant.STATRT, 0)
        AppRequest.INSTANCE.getIntegralCommodity(treeMap, BaseObserver(object : OnLoadDataListener<IntegralCommodityBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)

            }

            override fun onSuccess(data: IntegralCommodityBean) {
                Log.e("ssss",data.toString())
                view.getIntegralCommoditySuccess(data)
            }

        }))
    }
}