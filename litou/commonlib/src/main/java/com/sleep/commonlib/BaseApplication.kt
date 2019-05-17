package com.sleep.commonlib

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.facebook.stetho.Stetho
import com.sleep.commonlib.util.LogUtil
import com.sleep.commonlib.util.SPUtil
import com.sleep.commonlib.util.ToastUtil
import com.sleep.commonlib.util.Utils

/**
 * Created by LLL on 2017/9/28.
 */
open class BaseApplication : MultiDexApplication(), Application.ActivityLifecycleCallbacks {

    companion object {
        lateinit var sInstance: BaseApplication
    }

    private val activities = mutableListOf<Activity>()

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
        MultiDex.install(context)
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        registerActivityLifecycleCallbacks(this)
        intiTool()
    }

    private fun intiTool() {
        initArouter()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            LogUtil.initLog()
        ToastUtil.init(this)
        SPUtil.init(this)
        Utils.init(this)
    }

    private fun initArouter() {
        if (BuildConfig.AW_DEBUG) {
            //打开log
//            ARouter.openLog()
            ARouter.openDebug()
            //调试初始化
            Stetho.initializeWithDefaults(this)
        }
        ARouter.init(this)
    }

    /**
     * 退出系统
     */
    protected fun exit() {
        finishAllActivity()
        System.exit(0)
    }

    fun finishAllActivity() {
        for (activity in activities) {
            activity.finish()
        }
        activities.clear()
    }

    override fun onActivityPaused(p0: Activity?) {
    }

    override fun onActivityResumed(p0: Activity?) {

    }

    override fun onActivityStarted(p0: Activity?) {

    }

    override fun onActivityDestroyed(p0: Activity?) {
        activities.remove(p0)
    }

    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
    }

    override fun onActivityStopped(p0: Activity?) {
    }

    override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
        if (p0 != null) {
            activities.add(p0)
        }
    }
}