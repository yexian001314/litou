package com.shqj.litouwang.umong

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.sleep.commonlib.util.SPUtil
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.Constant
import com.umeng.message.UmengNotifyClickActivity
import com.shqj.litouwang.R
import org.android.agoo.common.AgooConstants
import org.json.JSONObject


class MipushActivity : UmengNotifyClickActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mipush)
        SPUtil.getInstance().put(Constant.IS_NEED_OPEN_PUSH_URL, true)
        ARouter.getInstance().build(ArouterConstant.APP_SPLASH).navigation()
        Log.e("UMessage","start SplashActivity")
        finish()
    }

    override fun onMessage(intent: Intent) {
        super.onMessage(intent);  //此方法必须调用，否则无法统计打开数
        val body = intent.getStringExtra(AgooConstants.MESSAGE_BODY)
        val jsonObject = JSONObject(body)
        val extraBean = jsonObject.getJSONObject("extra")
        val url = extraBean.getString("custom")
        Log.e("UMessage","onMessage = $url")
        SPUtil.getInstance().put(Constant.PUSH_URL, url)
    }
}
