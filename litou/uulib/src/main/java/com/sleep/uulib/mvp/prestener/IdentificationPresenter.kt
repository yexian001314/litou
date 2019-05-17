package com.sleep.uulib.mvp.prestener

import android.content.Context
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.CertificationBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.view.IdentificationView
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import com.sleep.uulib.netWork.ProgressObserver
import java.util.*

/**
 * Created by SleepYM09 on 2017/12/15.
 *
 * blog: www.sleepym09.com
 */
class IdentificationPresenter(private val context: Context,private val view: IdentificationView,private val statesLayoutManager: StatesLayoutManager) {
    fun getIdentificationData(relaName: String,certNo: String){
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.REAL_NAME,relaName)
        treeMap.put(NetConstant.CERTNO,certNo)
        treeMap.put(NetConstant.DEVICE_TYPE,1)
        AppRequest.INSTANCE.getIdentificationData(treeMap, ProgressObserver(context,false,object : OnLoadDataListener<CertificationBean>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode,statesLayoutManager)
            }

            override fun onSuccess(data: CertificationBean) {
                view.getIdentificationData(data)
            }

        }))
    }
}