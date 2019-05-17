package com.shqj.mine.mvp.presenter

import com.google.gson.JsonObject
import com.shqj.mine.mvp.view.IMyInvestDetailView
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.BaseObserver
import com.sleep.uulib.netWork.OnLoadDataListener
import java.util.*

/**
 * Created by SleepYM09 on 2018/4/28.
 *
 * blog: www.sleepym09.com
 */
class MyInvestFragmentPresenter(private val mView: IMyInvestDetailView, private val statesLayoutManager: StatesLayoutManager) {
    //判断是否是老用户
    fun getInvestType() {
        val treeMap = TreeMap<String ,Any>()
        AppRequest.INSTANCE.postWithJson(treeMap,"", BaseObserver(object :OnLoadDataListener<JsonObject>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
            }

            override fun onSuccess(data: JsonObject) {
            }
        }))
    }
}