package com.shqj.mine.mvp.presenter

import android.content.Context
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.bean.UpdataAddressBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import com.sleep.uulib.netWork.ProgressObserver
import com.shqj.mine.mvp.view.PersonalDataView
import java.util.*

/**
 * Created by SleepYM09 on 2017/12/13.
 *
 * blog: www.sleepym09.com
 */
class PersonalDataPersenter(private val view: PersonalDataView,private val statesLayoutManager: StatesLayoutManager) {
    fun saveAddress(context: Context,provinceId: String,cityId: String,CountyId: String,detailAddress: String){
        val treeMap = TreeMap<String, Any?>()
        treeMap.put(NetConstant.PROVINCE_ID,provinceId)
        treeMap.put(NetConstant.CITY_ID,cityId)
        treeMap.put(NetConstant.COUNTY_ID,CountyId)
        treeMap.put(NetConstant.STREET_ID,detailAddress)
        AppRequest.INSTANCE.saveAddress(treeMap,ProgressObserver(context,false,object: OnLoadDataListener<UpdataAddressBean>{
            override fun onFailure(e: Throwable?, errorCode: Int) {
                view.getDataFinish()
                NetCommonMethod.judgeError(errorCode,statesLayoutManager)
            }

            override fun onSuccess(data: UpdataAddressBean) {
                view.saveAddressSuccess(data)
            }

        }))

    }
}