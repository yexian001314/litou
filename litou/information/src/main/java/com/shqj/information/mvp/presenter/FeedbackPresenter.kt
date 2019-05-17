package com.shqj.information.mvp.presenter

import android.content.Context
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.UUApplication
import com.sleep.uulib.bean.AdviceFeedbackBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import com.sleep.uulib.netWork.ProgressObserver
import com.shqj.information.mvp.view.FeedbackView
import java.util.*

/**
 * Created by SleepYM09 on 2017/12/13.
 *
 * blog: www.sleepym09.com
 */
class FeedbackPresenter(private val context: Context, private val view: FeedbackView, private val statesLayoutManager: StatesLayoutManager) {
    fun submitFeedback(content: String){
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.MOBILE_PHONE,UUApplication.user?.getMobilePhone())
        treeMap.put(NetConstant.CONTENT,content)
        treeMap.put(NetConstant.FEEDBACK_TYPE,2)
        AppRequest.INSTANCE.submitFeedback(treeMap,ProgressObserver(context,false,object : OnLoadDataListener<AdviceFeedbackBean>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode,statesLayoutManager)
            }

            override fun onSuccess(data: AdviceFeedbackBean) {
                view.submitFeedbackSuccess(data)
            }

        }))
    }
}