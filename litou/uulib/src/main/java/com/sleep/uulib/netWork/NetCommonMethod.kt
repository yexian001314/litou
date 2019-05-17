package com.sleep.uulib.netWork

import android.widget.TextView
import com.google.gson.JsonParseException
import com.sleep.commonlib.Execption.ApiException
import com.sleep.commonlib.util.LogUtil
import com.sleep.commonlib.util.ToastUtil
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.R
import com.sleep.uulib.UUApplication
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.enums.ErrorType
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * Created by SleepYM09 on 2017/10/20.
 *
 * blog: www.sleepym09.com
 */
object NetCommonMethod {


    fun <T> judgeError(listener: OnLoadDataListener<T>, e: Throwable?) {
        LogUtil.e("网络请求异常", e.toString())
        if (e is HttpException) {
            val mCode = e.code()
            if (mCode == 404) {
                ToastUtil.showToast("网络无响应")
                listener.onFailure(e, NetConstant.NET_CANT_USE_ERROR)
                return
            }else if(mCode == 301){
                ToastUtil.showToast("重定向失败")
                listener.onFailure(e, NetConstant.NET_CANT_USE_ERROR)
            }else if (mCode in 500..599){
//                ToastUtil.showToast("系统繁忙，请稍后再试")
                listener.onFailure(e, NetConstant.NET_SYSTEM_BUSY_ERROR)
            }
        } else if (e is UnknownHostException) {
            listener.onFailure(e, NetConstant.NET_CANT_USE_ERROR)
        } else if (e is ConnectException || e is SocketTimeoutException) {
            listener.onFailure(e, NetConstant.TIME_OUT_ERROR)
        } else if (e is JsonParseException || e is JSONException || e is ParseException) {
            if(UUApplication.systemErrorType == ErrorType.SYSTEM_MAINTAIN){
                listener.onFailure(e, NetConstant.NET_SYSTEM_MAINTAIN_ERROR)
            }else if(UUApplication.systemErrorType == ErrorType.SYSTEM_BUSY){
                listener.onFailure(e, NetConstant.NET_SYSTEM_BUSY_ERROR)
            }else{
                ToastUtil.showToast("解析失败")
                listener.onFailure(e, NetConstant.NET_SERVER_ERROR)
            }
        }else if (e is ApiException) {
            listener.onFailure(e, e.errorCode)
        } else {
            listener.onFailure(e!!, NetConstant.UNKNOW_ERROR)
        }
    }

    fun judgeError(errorCode: Int,stateManager: StatesLayoutManager){
        when(errorCode){

            NetConstant.NET_CANT_USE_ERROR,NetConstant.TIME_OUT_ERROR -> stateManager.showNetWorkError()
            NetConstant.UNKNOW_ERROR,NetConstant.NET_SERVER_ERROR -> stateManager.showError()
            NetConstant.NET_SERVER_FAILURE_DONT_ERROR  -> stateManager.showContent()
            NetConstant.NET_SYSTEM_MAINTAIN_ERROR -> {
                //系统维护中，设置时间段
                stateManager.showSystemMaintain()
                if(UUApplication.systemMaintainTime != null){
                    stateManager.systemMaintainView.findViewById<TextView>(R.id.tv_empty_title).text = "系统维护时间\n${UUApplication.systemMaintainTime?.time}"
                }
            }
            NetConstant.NET_SYSTEM_BUSY_ERROR -> stateManager.showSystemBusy()
        }
    }
}