package com.shqj.litouwang.activity

import android.Manifest
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.example.twitterlib.wigdet.dialog.DialogFactory
import com.shqj.litouwang.R
import com.shqj.litouwang.mvp.SplashPresenter
import com.shqj.litouwang.mvp.SplashView
import com.sleep.commonlib.view.CountDownView
import com.sleep.uulib.bean.QueryBankBean
import com.sleep.uulib.bean.QueryUserInfoBean
import com.sleep.uulib.bean.SplashBannerBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.model.CommonModule
import com.sleep.uulib.mvp.view.QueryUserInfoView
import com.sleep.uulib.util.GlideHelper
import com.sleep.uulib.uubase.HtmlUrlActivity
import com.sleep.uulib.uubase.UUBaseActivity
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.PermissionNo
import com.yanzhenjie.permission.PermissionYes
import kotlinx.android.synthetic.main.activity_splash.*


@Route(path = ArouterConstant.APP_SPLASH)
class SplashActivity : UUBaseActivity(), CountDownView.SecondCallBack, View.OnClickListener, QueryUserInfoView, SplashView {

    companion object {
        const val REQUEST_PERMISSION_PHONE_STATE_CODE = 300
    }

    private var presenter: SplashPresenter? = null

    override fun shouldShowToolBar(): Boolean {
        return false
    }

    override fun shouldReceiveOtherLogin(): Boolean {
        return false
    }

    override fun shouldReceiveSystemMaintain(): Boolean {
        return false
    }

    override fun getLayoutResourse(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        tv_skip.setSecondCallBack(this)
        tv_skip.setOnClickListener(this)
    }

    override fun initData() {
        presenter = SplashPresenter(this, mStateManager)
        requestPermission()

    }

    override fun retryGetData() {
        mStateManager.showContent()
    }

    private fun requestPermission() {
        AndPermission
                .with(this)
                .requestCode(REQUEST_PERMISSION_PHONE_STATE_CODE)
                .permission(Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .callback(this)
                .start()
    }

    override fun onClick(p0: View?) {
        ARouter.getInstance().build(ArouterConstant.APP_MAIN_ACTIVITY).navigation()
        tv_skip.cancel()
        finish()
    }

    override fun nextSecond(second: Int) {
        if (second !== 0) {
            tv_skip.text = ("跳过 " + second)
        } else {
            ARouter.getInstance().build(ArouterConstant.APP_MAIN_ACTIVITY).navigation()
            finish()
        }
    }

    @PermissionYes(REQUEST_PERMISSION_PHONE_STATE_CODE)
    fun requestPhoneStatePermissionSuccess(deniedPermissions: List<String>) {
        queryUserInfo()
    }

    @PermissionNo(REQUEST_PERMISSION_PHONE_STATE_CODE)
    fun requestPhoneStatePermissionFailure(deniedPermissions: List<String>) {
        if (AndPermission.hasPermission(this@SplashActivity, deniedPermissions)) {
            //此时实际已经有权限
            queryUserInfo()

        } else {
            // 第二种：用自定义的提示语。
            AndPermission.defaultSettingDialog(this@SplashActivity, 400)
                    .setTitle("权限申请失败")
                    .setMessage("您拒绝了我们必要的一些权限，已经没法愉快的玩耍了，请在设置中授权！")
                    .setPositiveButton("好，去设置")
                    .show()
        }
    }

    private fun queryUserInfo() {
        try {
            CommonModule.queryUserInfo(this)
        } catch (error: Error) {

        }
    }

    override fun queryUserInfoFailure(e: Throwable?, errorCode: Int) {
        presenter?.getSplashBannerUrl()
//        judgeSystemMaintainError(errorCode)
    }

    override fun queryUserInfoSuccess(data: QueryUserInfoBean) {
    }

    override fun queryBankFailure(e: Throwable?, errorCode: Int) {
        presenter?.getSplashBannerUrl()
//        judgeSystemMaintainError(errorCode)
    }

    override fun queryBankSuccess(data: QueryBankBean) {
        presenter?.getSplashBannerUrl()
    }

    override fun getStartBannerSuccess(data: SplashBannerBean) {
        if (data.bannerList != null && data.bannerList.size > 0) {
            Glide.with(this)
                    .applyDefaultRequestOptions(GlideHelper.getOptions(GlideHelper.ImageType.SPLASH))
                    .load(data.bannerList[0].pic_url)
                    .into(iv_splash_root)
            iv_splash_root.setOnClickListener(View.OnClickListener {
                HtmlUrlActivity.launch(data.bannerList[0].jump_url, false, data.bannerList[0].banner_name)
            })
        }
        tv_skip.visibility = View.VISIBLE
        tv_skip.start()
    }

    override fun getStartBannerFailure() {
        tv_skip.visibility = View.VISIBLE
        tv_skip.start()
    }

    fun judgeSystemMaintainError(errorCode: Int) {
        if (errorCode == NetConstant.NET_SYSTEM_MAINTAIN_ERROR) {
            DialogFactory.instance.getCommonDialog("提示", "后台服务正在升级，稍后即可恢复使用，\n敬请谅解~", "知道了", null, false,
                    View.OnClickListener {
                    }).show(supportFragmentManager, "server_maintain")
        } else {
            tv_skip.start()
        }
    }
}
