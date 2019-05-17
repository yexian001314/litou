package com.sleep.uulib

import android.app.Activity
import android.app.Notification
import android.content.Context
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.widget.RemoteViews
import com.alibaba.android.arouter.launcher.ARouter
import com.liulishuo.filedownloader.FileDownloader
import com.meituan.android.walle.WalleChannelReader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.sleep.commonlib.BaseApplication
import com.sleep.commonlib.BuildConfig
import com.sleep.commonlib.util.LogUtil
import com.sleep.commonlib.util.SPUtil
import com.sleep.uulib.bean.SystemMaintainBean
import com.sleep.uulib.bean.UserInfo
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.Constant
import com.sleep.uulib.enums.ErrorType
import com.sleep.uulib.util.ForegroundCallbacks
import com.tencent.bugly.crashreport.CrashReport
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.IUmengRegisterCallback
import com.umeng.message.PushAgent
import com.umeng.message.UmengMessageHandler
import com.umeng.message.UmengNotificationClickHandler
import com.umeng.message.entity.UMessage
import com.umeng.socialize.PlatformConfig
import com.umeng.socialize.UMShareAPI
import org.android.agoo.huawei.HuaWeiRegister
import org.android.agoo.mezu.MeizuRegister
import org.android.agoo.xiaomi.MiPushRegistar
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException


/**
 * Created by SleepYM09 on 2017/10/18.
 *
 * blog: www.sleepym09.com
 */
class UUApplication : BaseApplication() {

    init {
        //分享初始化
        PlatformConfig.setWeixin("wxa0f73f042a4d121f", "b5d844bf6c7981b780104192b0be6f64")
        PlatformConfig.setQQZone("1106861216", "suIHpRRw1ruVudqb")
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater { context, layout ->
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.background_color, R.color.color_5b5b5b)
            layout.setReboundDuration(400)
            //指定为经典Header
            val classicsHeader = ClassicsHeader(context)
            classicsHeader.setFinishDuration(500)
        }
    }

    companion object {
        var user: UserInfo? = null
        lateinit var sInstance: UUApplication
        var isAppFirstRun: Boolean = true;
        var isGesTrueSuccess: Boolean = false
        var isPushMessage: Boolean = false

        /**
         * 用于表示系统升级对话框是否显示过
         */
        var systemMaintainDialogIsShow = false

        /**
         * 当前请求是否由于系统更新产生错误，由[com.sleep.uulib.netWork.MyHttpLoggerIntercept]判断改变,
         * 用于 [com.sleep.uulib.netWork.BaseObserver]
         */
        var systemErrorType: ErrorType = ErrorType.NONE

        var systemMaintainTime: SystemMaintainBean? = null

        var umMessageJumpUrl: String = ""
        val TIMEOUT: Long = 5 * 60 * 1000L
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this

        initUM()

        //腾讯Bugly的初始化
        initBugly()

        //umeng分享初始化
        UMShareAPI.get(this)

        //初始化downLoader
        FileDownloader.setupOnApplicationOnCreate(this)

    }

    private fun initUM() {
        UMConfigure.setLogEnabled(true)
        //加密传输log
        UMConfigure.setEncryptEnabled(true)
        MobclickAgent.openActivityDurationTrack(false)
        //设置统计类型  普通统计场景类型
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL)
        UMConfigure.init(this, "5ad6a47eb27b0a058f000116", WalleChannelReader.getChannel(this), UMConfigure.DEVICE_TYPE_PHONE, null)

        //初始化推送
//        initUMPush()
    }

    private fun initUMPush() {
        val mPushAgent = PushAgent.getInstance(this)

        //注册推送服务，每次调用register方法都会回调该接口
        /**
         * 自定义行为的回调处理，参考文档：高级功能-通知的展示及提醒-自定义通知打开动作
         * UmengNotificationClickHandler是在BroadcastReceiver中被调用，故
         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
         * */
        mPushAgent.notificationClickHandler = object : UmengNotificationClickHandler() {
            override fun launchApp(p0: Context?, msg: UMessage) {
                Log.e("UMessage", "notificationClickHandler = ${msg.extra}")
                SPUtil.getInstance().put(Constant.PUSH_URL, msg.extra["custom"])
                SPUtil.getInstance().put(Constant.IS_NEED_OPEN_PUSH_URL, true)
                super.launchApp(p0, msg)
            }
        }
//        mPushAgent.messageHandler = object : UmengMessageHandler(){
//
//            override fun getNotification(p0: Context?, msg: UMessage?): Notification {
//                Log.e("UMessage","UMessage = $msg")
//                return super.getNotification(p0, msg)
//            }
//
//            override fun dealWithCustomMessage(p0: Context?, p1: UMessage?) {
//                Log.e("UMessage","p1 = ${p1?.custom}")
//                super.dealWithCustomMessage(p0, p1)
//            }
//        }
        val messageHandler = object : UmengMessageHandler() {
            override fun getNotification(context: Context?, msg: UMessage): Notification {
                Log.e("UMessage", "getNotification = ${msg.extra}")
//                SPUtil.getInstance().put(Constant.PUSH_URL,msg.extra["custom"])
//                SPUtil.getInstance().put(Constant.IS_NEED_OPEN_PUSH_URL,true)
                val builder = Notification.Builder(context)
                val myNotificationView = RemoteViews(context?.packageName,
                        R.layout.notification_view)
                myNotificationView.setTextViewText(R.id.notification_title, msg.title)
//                myNotificationView.setTextViewText(R.id.notification_title, "notification")
                myNotificationView.setTextViewText(R.id.notification_text, msg.text)
//                myNotificationView.setImageViewBitmap(R.id.notification_large_icon1,
//                        getLargeIcon(context, msg))
//                        myNotificationView.setImageViewResource(R.id.notification_small_icon,
//                                getSmallIconId(context, msg))
                builder.setContent(myNotificationView)
                        .setSmallIcon(getSmallIconId(context, msg))
                        .setTicker(msg.ticker)
                        .setAutoCancel(true)

                return builder.notification
            }
        }
        Log.e("yexmId", mPushAgent.getRegistrationId())
        mPushAgent.messageHandler = messageHandler
        //当通知栏已经有两条通知，此时若第三条通知到达，则会把第一条通知隐藏
//        mPushAgent.displayNotificationNumber = 2
        mPushAgent.register(object : IUmengRegisterCallback {

            override fun onSuccess(deviceToken: String) {
                //注册成功会返回device token
                LogUtil.e("umeng", "deviceToken = " + deviceToken)
            }

            override fun onFailure(s: String, s1: String) {
                LogUtil.e("umeng", "s = $s  s1 = $s1")
            }
        })
        //appId appKey
        MiPushRegistar.register(this, "2882303761517628608", "5101762881608")
        HuaWeiRegister.register(this)
        MeizuRegister.register(this, "112135", "a51f343c6abe47d68dab58361e093370")
    }

    private var mCurrentTime: Long = 0
    private var mPreviousTime: Long = 0
    private fun initBugly() {
        mCurrentTime = System.currentTimeMillis()
        mPreviousTime = System.currentTimeMillis()
        //获取当前包名
        val packageName = this.packageName
        // 获取当前进程名
        val processName = getProcessName(android.os.Process.myPid())
        // 设置是否为上报进程
        val strategy = CrashReport.UserStrategy(this)
        strategy.appChannel = WalleChannelReader.getChannel(this)
        strategy.isUploadProcess = processName == null || processName == packageName
        // 初始化Bugly
        //发布时设置false
        CrashReport.setIsDevelopmentDevice(this, BuildConfig.AW_DEBUG)
//        CrashReport.initCrashReport(applicationContext, "9ce424a86d", BuildConfig.AW_DEBUG, strategy)
        CrashReport.initCrashReport(applicationContext, "9ce424a86d", false, strategy)
        ForegroundCallbacks.init(this)
        ForegroundCallbacks.get().addListener(object : ForegroundCallbacks.Listener {
            override fun onBecameForeground(activity: Activity) {
                mCurrentTime = System.currentTimeMillis()
                Handler().postDelayed(Runnable {
                    if (SPUtil.getInstance().getBoolean(Constant.GES_PSD_HAS, false) && UUApplication.user != null && mCurrentTime - TIMEOUT >= mPreviousTime && !isAppFirstRun) {
                        if (isPushMessage) {
                            isGesTrueSuccess = false
                            return@Runnable
                        }
                        ARouter.getInstance().build(ArouterConstant.UUBASE_GESTRUE).navigation(activity)
                    }
                }, 30)

            }

            override fun onBecameBackground() {
                mPreviousTime = System.currentTimeMillis()
            }
        })
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private fun getProcessName(pid: Int): String? {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(FileReader("/proc/$pid/cmdline"))
            var processName = reader.readLine()
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim { it <= ' ' }
            }
            return processName
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        } finally {
            try {
                if (reader != null) {
                    reader.close()
                }
            } catch (exception: IOException) {
                exception.printStackTrace()
            }
        }
        return null
    }


    fun systemExit() {
        MobclickAgent.onKillProcess(this)
        exit()
    }
}