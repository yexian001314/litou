package com.shqj.mine.activity

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.twitterlib.wigdet.dialog.CommonFragmentDialog
import com.example.twitterlib.wigdet.dialog.DialogFactory
import com.shqj.mine.R
import com.shqj.mine.fragment.MineFragment
import com.shqj.mine.mvp.presenter.SecuritySettingPresenter
import com.shqj.mine.mvp.view.SecuritySettingView
import com.sleep.commonlib.util.*
import com.sleep.uulib.UUApplication
import com.sleep.uulib.account.ForgetLoginPwdActivity
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ArouterParamConstant
import com.sleep.uulib.constant.Constant
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.util.GlideCacheUtil
import com.sleep.uulib.uubase.HtmlUrlActivity
import com.sleep.uulib.uubase.UUBaseActivity
import com.umeng.analytics.MobclickAgent
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.PermissionListener
import kotlinx.android.synthetic.main.mine_activity_security_setting.*

/**
 * 安全设置
 */
@Route(path = ArouterConstant.MINE_SECURITY_SETTING)
class SecuritySettingActivity : UUBaseActivity(), View.OnClickListener, SecuritySettingView {
    override fun efficacyFailure() {
        ToastUtil.showToast("密码输入错误,请重新输入")
    }

    override fun efficacySuccess() {
        SPUtil.getInstance().put(Constant.GES_PSD_HAS, false)
        ARouter.getInstance().build(ArouterConstant.UUBASE_GESTRUE).navigation(this)
    }

    private lateinit var mPresenter: SecuritySettingPresenter

    override fun getLayoutResourse(): Int {
        return R.layout.mine_activity_security_setting
    }

    override fun initView() {
        setTitle(getString(R.string.title_security_setting))
        tv_version.text = String.format("当前版本: V%s", AppUtils.getAppVersionName(this))

        if (UUApplication.user != null && UUApplication.user?.isIsTradersPwBinded()!!) {
            //已经设置过支付密码
            tv_trading_pwd.text = "修改"
        } else if (UUApplication.user != null && !UUApplication.user?.isIsTradersPwBinded()!!) {
            //还未设置支付密码
            tv_trading_pwd.text = "设置"
        }

        tv_login_out.setOnClickListener(this)
        ll_modify_login_pwd.setOnClickListener(this)
        ll_trading_pwd.setOnClickListener(this)
        ll_about_us.setOnClickListener(this)
        ll_modify_gestrue_pwd.setOnClickListener(this)
        ll_clear_cache.setOnClickListener(this)
        ll_customer_service_telephone.setOnClickListener(this)
    }

    override fun initData() {
        mPresenter = SecuritySettingPresenter(this, mStateManager)
    }

    override fun retryGetData() {
        mStateManager.showContent()
    }

    override fun getDataFinish() {

    }

    override fun logoutSuccess() {
        UUApplication.user = null
//        BaseApplication.sInstance.finishAllActivity()
        // 单个测试的时候用intent跳转到moduleMainActivity里面去，正式测试的时候需要换回去
//        startActivity(Intent(this, MineActivityMainActivity::class.java))
        ARouter.getInstance().build(ArouterConstant.APP_MAIN_ACTIVITY).withInt(ArouterParamConstant.CHOOSE_POSITION, 0).navigation()
        ToastUtil.showToast(getString(R.string.has_bean_logout))
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_modify_login_pwd -> {
                //修改登录密码
                MobclickAgent.onEvent(this, "wd_aqsz_pwxg")
                ForgetLoginPwdActivity.launch(UUApplication.user?.getMobilePhone()!!)
            }
            R.id.ll_customer_service_telephone -> {
                //联系客服
                DialogFactory.instance.getBottomDialog(R.layout.layout_bottom_dialog_contact_service, object : CommonFragmentDialog.ILogicSetter {
                    override fun setLogic(fragmentDialog: CommonFragmentDialog, rootView: View) {
                        //QQ客服
                        rootView.findViewById<TextView>(R.id.tv_contact_qq_server).setOnClickListener({
                            contackQQServer()
                            fragmentDialog.dismiss()
                        })

                        //电话客服
                        rootView.findViewById<View>(R.id.tv_call_phone).setOnClickListener({
                            fragmentDialog.dismiss()
                            DialogFactory.instance.getPhoneCallDialog(View.OnClickListener {
                                call()
                            }).show(supportFragmentManager, "contact_server_dialog")
                        })

                        //取消
                        rootView.findViewById<View>(R.id.tv_cancel).setOnClickListener({
                            fragmentDialog.dismiss()
                        })
                    }

                }).show(supportFragmentManager, "contact_service")
            }
            R.id.ll_modify_gestrue_pwd -> {
                //修改手势密码
                DialogFactory.instance.getIdentifyComfirm(UUApplication.user!!.getMobilePhone()!!.replaceRange(3, 7, "****"),
                        View.OnClickListener { v ->
                            mPresenter.login(UUApplication.user?.getMobilePhone(), v.findViewById<EditText>(R.id.et_psd).text.toString(), this@SecuritySettingActivity)
                        }).show(supportFragmentManager, "modify_gestrue")
            }
            R.id.ll_trading_pwd -> {
                //修改/设置支付密码 1->若支付密码未设置,跳转支付密码设置页   2->已经设置,跳转支付密码修改页面
                MobclickAgent.onEvent(this, "wd_aqsz_zfpwxg")
                if (UUApplication.user?.isIsTradersPwBinded()!!) {
                    ARouter.getInstance().build(ArouterConstant.MINE_MODIFY_TRADING_PWD).navigation()
                } else {
                    ARouter.getInstance().build(ArouterConstant.MINE_SET_TRADING_PWD).navigation()
                }
            }
            R.id.ll_about_us -> {
                //关于我们
                HtmlUrlActivity.launch(NetConstant.ABOUT_US)
            }
            R.id.tv_login_out -> {
                //退出登录
                MobclickAgent.onEvent(this, "wd_aqsz_tc_btn")
                DialogFactory.instance.getCommonDialog("提示", "确定退出登录？", "确定", "取消", true,
                        View.OnClickListener {
                            MobclickAgent.onEvent(this, "wd_aqsz_qrtc_btn")
                            mPresenter.logout()
                            SPUtil.getInstance().put(Constant.GES_PSD_HAS, false)
                        }, View.OnClickListener {
                    MobclickAgent.onEvent(this, "wd_aqsz_tcqx_btn")
                }).show(supportFragmentManager, "logout")
            }
            R.id.ll_clear_cache -> {
                //清除缓存
                val cacheSize = GlideCacheUtil.getInstance().getCacheSize(this@SecuritySettingActivity)
                Log.e("yexmCache", cacheSize)

                DialogFactory.instance.getCommonDialog("清除缓存", "缓存大小:" + cacheSize, "确定", "取消", true,
                        View.OnClickListener {
                            GlideCacheUtil.getInstance().clearImageAllCache(this@SecuritySettingActivity)
                            this@SecuritySettingActivity.deleteDatabase("webview.db")
                            this@SecuritySettingActivity.deleteDatabase("webviewCache.db")

                        }, View.OnClickListener {
                }).show(supportFragmentManager, "logout")
            }
        }
    }
    /**
     * 跳转QQ客服
     */
    private fun contackQQServer() {
        if (LibCommonUtil.isAvilible(this, "com.tencent.mobileqq") || LibCommonUtil.isAvilible(this, "com.tencent.tim")) {
            val urlQQ = "http://wpa.b.qq.com/cgi/wpa.php?ln=2&uin=4006688956"
            HtmlUrlActivity.launch(urlQQ)
        } else {
            ToastUtil.showToast("请先安装腾讯QQ或TIM")
        }
    }
    /**
     * 联系客服，打电话
     */
    private fun call() {
        AndPermission.with(this)
                .requestCode(200)
                .permission(Manifest.permission.CALL_PHONE)
                .callback(permissionListener)
                // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框；
                // 这样避免用户勾选不再提示，导致以后无法申请权限。
                // 你也可以不设置。
                .rationale({ requestCode, rationale ->
                    // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                    AndPermission.rationaleDialog(this@SecuritySettingActivity, rationale).show()
                })
                .start()
    }
    private val permissionListener = object : PermissionListener {
        override fun onSucceed(requestCode: Int, grantPermissions: List<String>) {

            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getString(R.string.CALL_PHONE)))
            try {
                startActivity(intent)
            } catch (e: SecurityException) {
                LogUtil.e(e.toString())
            }
        }

        override fun onFailed(requestCode: Int, deniedPermissions: List<String>) {

            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(this@SecuritySettingActivity, deniedPermissions)) {
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(this@SecuritySettingActivity, MineFragment.REQUEST_CODE_SETTING).show()
            }
        }
    }

}
