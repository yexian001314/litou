package com.shqj.litouwang.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.twitterlib.wigdet.dialog.DialogFactory
import com.google.gson.Gson
import com.shqj.litouwang.R
import com.shqj.litouwang.adapter.HomeIndicatorAdapter
import com.sleep.commonlib.adapter.BaseFragmentAdapter
import com.sleep.commonlib.base.BaseEvent
import com.sleep.commonlib.util.SPUtil
import com.sleep.commonlib.util.ToastUtil
import com.sleep.uulib.UUApplication
import com.sleep.uulib.bean.AppUpdateBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ArouterParamConstant
import com.sleep.uulib.constant.Constant
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.netWork.HProgressDialogUtils
import com.sleep.uulib.util.UpdateAppHttpUtil
import com.sleep.uulib.uubase.HtmlUrlActivity
import com.sleep.uulib.uubase.UUBaseActivity
import com.vector.update_app.UpdateAppBean
import com.vector.update_app.UpdateAppManager
import com.vector.update_app.UpdateCallback
import com.vector.update_app.service.DownloadService
import kotlinx.android.synthetic.main.activity_main.*
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import org.simple.eventbus.Subscriber
import java.io.File


@Route(path = ArouterConstant.APP_MAIN_ACTIVITY)
class MainActivity : UUBaseActivity(), HomeIndicatorAdapter.OnItemClickListener {

    private var choosePosition: Int = 0
    private var updateApp: UpdateAppBean? = null

    private var updateAppManager: UpdateAppManager? = null

    private lateinit var mViewPagerAdapter: BaseFragmentAdapter

    private val mIndicatorTitles: List<String>
        get() {
            return listOf(resources.getString(R.string.main_indicator_home),
                    resources.getString(R.string.main_indicator_arrange_finance),
//                    resources.getString(R.string.main_indicator_market),
//                    resources.getString(R.string.main_indicator_information),
                    resources.getString(R.string.main_indicator_mine))
        }

    private val mIndicatorRes: List<Int>
        get() {
            return listOf(R.drawable.selector_tab_home,
                    R.drawable.selector_tab_arrange_finance,
//                    R.drawable.selector_tab_market,
//                    R.drawable.selector_tab_information,
//                    R.drawable.selector_tab_interact,
                    R.drawable.selector_tab_mine)
        }

    private val mFragments: List<Fragment>
        get() {
            return listOf(ARouter.getInstance().build(ArouterConstant.APP_HOME_FRAGMENT).navigation() as Fragment,
                    ARouter.getInstance().build(ArouterConstant.APP_ARRANGE_FINACE_FRAGMENT).navigation() as Fragment,
//                    ARouter.getInstance().build(ArouterConstant.APP_MARKET_FRAGMENT).navigation() as Fragment,
//                    ARouter.getInstance().build(ArouterConstant.APP_INFORMATION_FRAGMENT).navigation() as Fragment,
                    ARouter.getInstance().build(ArouterConstant.APP_MINE_FRAGMENT).navigation() as Fragment)
        }

    override fun shouldReceiveOtherLogin(): Boolean {
        if (main_viewpager.currentItem == 2) {
            return true
        }
        return false
    }

    override fun getLayoutResourse(): Int {
        return R.layout.activity_main
    }

    override fun shouldShowToolBar(): Boolean {
        return false
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        val targetPosition = intent?.getIntExtra(ArouterParamConstant.CHOOSE_POSITION, -1)
        if (targetPosition != -1) {
//            if (targetPosition == 2 || targetPosition == 3) {
//                //检查是否登录
//                if (UUApplication.user == null) {
//                    LoginActivity.launch()
//                    return
//                }
//            }
            main_viewpager.currentItem = targetPosition!!
        }

    }

    override fun onResume() {
        super.onResume()
        val pushUrl = SPUtil.getInstance().getString(Constant.PUSH_URL)
        if (SPUtil.getInstance().getBoolean(Constant.IS_NEED_OPEN_PUSH_URL) && pushUrl.isNotEmpty()) {
            HtmlUrlActivity.launch(pushUrl, true)
            SPUtil.getInstance().put(Constant.IS_NEED_OPEN_PUSH_URL, false)
            if (!UUApplication.isGesTrueSuccess && SPUtil.getInstance().getBoolean(Constant.GES_PSD_HAS, false) && UUApplication.user != null && UUApplication.isPushMessage) {
                ARouter.getInstance().build(ArouterConstant.UUBASE_GESTRUE).navigation(this)
            }
            UUApplication.isPushMessage = false
        }
        if (UUApplication.isAppFirstRun) {
            UUApplication.isAppFirstRun = false
            if (UUApplication.user != null) {
                if (SPUtil.getInstance().getBoolean(Constant.GES_PSD_HAS, false)) {
                    Handler().postDelayed(Runnable {
                        ARouter.getInstance().build(ArouterConstant.UUBASE_GESTRUE).navigation(this)
                    }, 1000)
                    return
                }
            }
        }

    }

    override fun initView() {
        mViewPagerAdapter = BaseFragmentAdapter(mFragments, supportFragmentManager)
        main_viewpager.adapter = mViewPagerAdapter
        main_viewpager.offscreenPageLimit = mIndicatorRes.size - 1

        //初始化indicator
        val commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = HomeIndicatorAdapter(mIndicatorRes, mIndicatorTitles, this)
        indicator.navigator = commonNavigator
        main_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                indicator.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                indicator.onPageSelected(position)
                choosePosition = position
            }

            override fun onPageScrollStateChanged(state: Int) {
                indicator.onPageScrollStateChanged(state)
            }
        })
    }

    override fun initData() {
        checkNewVersion()
    }

    override fun retryGetData() {
    }

    override fun onItemClickListener(position: Int) {
        this.choosePosition = position
//        if (position == 2 || position == 3) {
//            //检查是否登录
//            if (UUApplication.user == null) {
//                LoginActivity.launch()
//                return
//            }
//        }
        main_viewpager.currentItem = position
    }

    private fun checkNewVersion() {
        val path = Environment.getExternalStorageDirectory().absolutePath
        UpdateAppManager.Builder()
                //必须设置,当前的Activity
                .setActivity(this)
                .setHttpManager(UpdateAppHttpUtil())
                .setPost(true)
                //更新地址
                .setUpdateUrl(NetConstant.APP_VERSION_INFO)
                .setTargetPath(path)
                .build()
                //检测新版本
                .checkNewApp(object : UpdateCallback() {


                    /**
                     * 解析json,自定义协议
                     *
                     * @param json  服务器返回的json
                     * @return      UpdateAppBean
                     */

                    override fun parseJson(json: String?): UpdateAppBean {

                        val updateAppBean = UpdateAppBean()

                        val appUpdateBean = Gson().fromJson(json, AppUpdateBean::class.java)

                        //（必须）是否更新Yes,No
                        if (appUpdateBean.updateType == "00") {
                            updateAppBean.update = "No"
                            updateAppBean.isConstraint = false
                        } else if (appUpdateBean.updateType == "01" || appUpdateBean.updateType == "02") {
                            updateAppBean.update = "Yes"
                            if (appUpdateBean.updateType == "01") {
                                updateAppBean.isConstraint = true
                            }
                        }

                        //(必须）新版本号
                        updateAppBean.newVersion = appUpdateBean.appVersion.version

                        //（必须）下载地址
                        updateAppBean.apkFileUrl = appUpdateBean.appVersion.appDownUrl

                        //（必须）更新内容
                        updateAppBean.updateLog = appUpdateBean.appVersion.updateLog

                        //大小，不设置不显示大小，可以不设置
                        updateAppBean.targetSize = appUpdateBean.appVersion.targetSize

                        return updateAppBean
                    }


                    /**
                     * 有新版本
                     *
                     * @param updateApp        新版本信息
                     * @param updateAppManager app更新管理器
                     */
                    override fun hasNewApp(updateApp: UpdateAppBean?, updateAppManager: UpdateAppManager) {
                        this@MainActivity.updateApp = updateApp
                        this@MainActivity.updateAppManager = updateAppManager
                        checkInstallPermission()
                    }


                    override fun onAfter() {
                        //                        CProgressDialogUtils.cancelProgressDialog(HomeActivity.this);
                    }


                    override fun noNewApp() {
                        //                        Toast.makeText(HomeActivity.this, "没有新版本", Toast.LENGTH_SHORT).show();
                    }


                    override fun onBefore() {
                        //                        CProgressDialogUtils.showProgressDialog(HomeActivity.this);
                    }
                })
    }

    /**
     * 检查应用安装权限
     */
    private fun checkInstallPermission() {
        var haveInstallPermission: Boolean
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //先获取是否有安装未知来源应用的权限
            haveInstallPermission = packageManager.canRequestPackageInstalls()
            if (!haveInstallPermission) {//没有权限
                DialogFactory.instance.getCommonDialog("提示", "版本更新需要打开未知来源权限\n请去设置中开启权限", "确定", "取消", true, View.OnClickListener {
                    startInstallPermissionSettingActivity()
                }).show(supportFragmentManager, "update_dialog")
            } else {
                showInstallApp()
            }
        } else {
            showInstallApp()
        }
    }

    /**
     * 显示安装apk的对话框
     */
    private fun showInstallApp() {
        if (updateApp!!.isConstraint) {
            //自定义对话框
            //强制更新，
            showDiyDialog()
        } else {
            updateAppManager?.showDialogFragment()
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun startInstallPermissionSettingActivity() {
        //注意这个是8.0新API
        val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES)
        startActivityForResult(intent, 10000)
    }

    /**
     * 自定义对话框
     * @param updateApp
     * @param updateAppManager
     */
    private fun showDiyDialog() {

        val targetSize = updateApp?.targetSize
        val updateLog = updateApp?.updateLog

        var msg = ""

        if (!TextUtils.isEmpty(targetSize)) {
            msg = "新版本大小：" + targetSize + "\n\n"
        }

        if (!TextUtils.isEmpty(updateLog)) {
            msg += updateLog
        }

        AlertDialog.Builder(this)
                .setTitle(String.format("您需要下载安装最新版%s，才能正常使用", updateApp?.newVersion))
                .setMessage(msg)
                .setPositiveButton("升级") { dialog, which ->
                    //显示下载进度
                    updateAppManager?.download(object : DownloadService.DownloadCallback {
                        override fun onStart() {
                            HProgressDialogUtils.showHorizontalProgressDialog(this@MainActivity, "下载进度", false)
                        }
                        /**
                         * 进度
                         *
                         * @param progress  进度 0.00 -1.00 ，总大小
                         * @param totalSize 总大小 单位B
                         */
                        /**
                         * 进度
                         *
                         * @param progress  进度 0.00 -1.00 ，总大小
                         * @param totalSize 总大小 单位B
                         */
                        override fun onProgress(progress: Float, totalSize: Long) {
                            HProgressDialogUtils.setProgress(Math.round(progress * 100))
                        }
                        /**
                         *
                         * @param total 总大小 单位B
                         */
                        /**
                         *
                         * @param total 总大小 单位B
                         */
                        override fun setMax(total: Long) {

                        }

                        override fun onFinish(file: File): Boolean {
                            HProgressDialogUtils.cancel()
                            return true
                        }

                        override fun onError(msg: String) {
                            ToastUtil.showToast(msg)
                            HProgressDialogUtils.cancel()

                        }
                    })

                    dialog.dismiss()
                }
                .setCancelable(false)
                .create()
                .show()
    }

    /**
     * 复写返回键，避免调用父类关闭当前activity
     */
    override fun onBackPressed() {}

    private var firstTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                ToastUtil.showToast("再按一次退出程序")
                firstTime = System.currentTimeMillis()
            } else {
                UUApplication.sInstance.systemExit()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10000) {
            if (resultCode == Activity.RESULT_OK) {
                showInstallApp()
            } else {
                checkInstallPermission()
            }
        }
    }

    @Subscriber
    private fun receiveLogin(baseEvent: BaseEvent<Any>) {
//        if (baseEvent.eventCode == EventCode.LOGIN) {
        main_viewpager.currentItem = choosePosition
//        }
    }
}
