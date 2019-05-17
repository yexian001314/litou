package com.sleep.uulib.netWork

import android.util.Log
import com.google.gson.Gson
import com.sleep.commonlib.base.BaseEvent
import com.sleep.commonlib.base.EventCode
import com.sleep.commonlib.util.LogUtil
import com.sleep.commonlib.util.ToastUtil
import com.sleep.uulib.UUApplication
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.enums.ErrorType
import com.sleep.uulib.netWork.NetCommonMethod.judgeError
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import org.json.JSONObject
import org.simple.eventbus.EventBus


/**
 * Created by SleepYM09 on 2017/10/18.
 *
 * blog: www.sleepym09.com
 */
open class BaseObserver<T>(var listener: OnLoadDataListener<T>) : Observer<T> {

    override fun onError(e: Throwable?) {
        LogUtil.e("yexml",e.toString())
        judgeError(listener, e)
    }


    override fun onSubscribe(d: Disposable?) {

    }

    override fun onComplete() {
    }

    /**
     * 本应用内，服务端报错和被挤下线逻辑相同，在onsuccess中根据data是否为空判断，并进行相应逻辑。
     */
    override fun onNext(value: T) {
        try {
            val gson = Gson()
            val originalJson = gson.toJson(value)
            val jsonObject = JSONObject(originalJson)
            val strResponseStatusBean = jsonObject.getJSONObject("responseStatus")
            val resultCode = strResponseStatusBean.getString("code")
            Log.e("yexml",resultCode.toString()+originalJson.toString())
            LogUtil.e("yexml",resultCode.toString()+originalJson.toString())
            when (resultCode) {
                NetConstant.RESULT_NOT_LOGIN -> {
                    //用户在他端登录
                    EventBus.getDefault().post(BaseEvent<Any>(EventCode.OTHER_LOGIN, null))
                    //清空存储的用户信息
                    UUApplication.user = null
                    listener.onFailure(null, NetConstant.NET_OTHER_LOGIN)
                }
                NetConstant.RESULT_SUCCESS -> listener.onSuccess(value)
                NetConstant.RESULT_ERROR_01, NetConstant.RESULT_ERROR_X00 -> {
                    listener.onFailure(null, NetConstant.NET_SERVER_ERROR)
                    ToastUtil.showToast(strResponseStatusBean.getString("message"))
                }
                else -> {
                    listener.onFailure(null, NetConstant.NET_SERVER_FAILURE_DONT_ERROR)
                    ToastUtil.showToast(strResponseStatusBean.getString("message"))
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            if (UUApplication.systemErrorType == ErrorType.SYSTEM_MAINTAIN) {
                listener.onFailure(e, NetConstant.NET_SYSTEM_MAINTAIN_ERROR)
            } else if (UUApplication.systemErrorType == ErrorType.SYSTEM_BUSY) {
                listener.onFailure(e, NetConstant.NET_SYSTEM_BUSY_ERROR)
            } else {
                listener.onFailure(null, NetConstant.UNKNOW_ERROR)
            }
        }
    }
}