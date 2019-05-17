package com.shqj.mine.mvp.presenter

import android.content.Context
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.RechargeLimitBean
import com.sleep.uulib.bean.WithdrawBean
import com.sleep.uulib.bean.WithdrawLimitBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.netWork.*
import com.sleep.uulib.uubase.HtmlContentActivity
import com.shqj.mine.mvp.view.WithdrawView
import java.util.*

/**
 * Created by SleepYM09 on 2017/12/8.
 *
 * blog: www.sleepym09.com
 */
class WithdrawPresenter(val view: WithdrawView, val statesLayoutManager: StatesLayoutManager) {

    /**
     * @Deprecated
     *
     * 获取当前账户的免费提现额度
     */
    @Deprecated("新版本取消免费额度，采用免费次数")
    fun refreshFreeLimit(context: Context){
        AppRequest.INSTANCE.refreshFreeLimit(TreeMap(), ProgressObserver(context,false,object : OnLoadDataListener<WithdrawLimitBean>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode,statesLayoutManager)
            }

            override fun onSuccess(data: WithdrawLimitBean) {
                view.refreshFreeLimit(data)
            }

        }))
    }

    /**
     * 提现
     */
    fun withdraw(context: Context,withdrawMoney: String){
        val treeMap = TreeMap<String, Any>()
        treeMap.put(NetConstant.DEVICE_TYPE,1)
        treeMap.put(NetConstant.TOTAL_MONEY,withdrawMoney)
        AppRequest.INSTANCE.withdraw(treeMap,ProgressObserver(context,false,object: OnLoadDataListener<WithdrawBean>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode,statesLayoutManager)
            }

            override fun onSuccess(data: WithdrawBean) {
                HtmlContentActivity.launch(data.formData)
            }
        }))
    }
    fun getRechargeLimitData(){
        AppRequest.INSTANCE.getRechargeLimitData(BaseObserver(object : OnLoadDataListener<RechargeLimitBean>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode,statesLayoutManager)

            }

            override fun onSuccess(data: RechargeLimitBean) {
                view.loadData(data)
            }

        }))
    }
}