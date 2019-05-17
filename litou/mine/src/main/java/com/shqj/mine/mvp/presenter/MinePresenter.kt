package com.shqj.mine.mvp.presenter

import com.shqj.mine.mvp.view.IMineView
import com.sleep.commonlib.util.LogUtil
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.RedBagAndCouponBean
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.BaseObserver
import com.sleep.uulib.netWork.OnLoadDataListener
import java.util.*

/**
 * Created by SleepYM09 on 2018/4/2.
 *
 * blog: www.sleepym09.com
 */
class MinePresenter(private val view: IMineView<RedBagAndCouponBean>, private val statesLayoutManager: StatesLayoutManager) {
    fun requestRedBagAndCoupon() {
        val treeMap = TreeMap<String, Any?>()
        AppRequest.INSTANCE.getRedBagAndCoupon(treeMap, BaseObserver(object : OnLoadDataListener<RedBagAndCouponBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                LogUtil.e("yexml",e.toString())
//                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }
            override fun onSuccess(data: RedBagAndCouponBean) {
                view.loadRedBagAndConpon(data)
            }
        }))

    }
}