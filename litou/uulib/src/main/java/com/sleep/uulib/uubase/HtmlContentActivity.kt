package com.sleep.uulib.uubase

import android.webkit.JavascriptInterface
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.sleep.commonlib.base.BaseEvent
import com.sleep.commonlib.base.EventCode
import com.sleep.commonlib.base.UUBaseWebActivity
import com.sleep.uulib.R
import com.sleep.uulib.UUApplication
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ArouterParamConstant
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.item_webview_body.*
import org.simple.eventbus.EventBus


@Route(path = ArouterConstant.UUBASE_HTML_CONTENT_ACTIVITY)
class HtmlContentActivity : UUBaseWebActivity() {

    private var exitIsFinish = false

    override fun retryGetData() {
    }

    companion object {
        const val WEB_CONTENT = "web_content"
        const val WEB_EXIT_IS_FINISH = "web_exit_is_finish"
        fun launch(webContent: String,exitIsFinish: Boolean = false){
            ARouter.getInstance().build(ArouterConstant.UUBASE_HTML_CONTENT_ACTIVITY)
                    .withString(WEB_CONTENT, webContent)
                    .withBoolean(WEB_EXIT_IS_FINISH,exitIsFinish)
                    .navigation()
        }
    }

    override fun initView() {
        super.mLottieAnimationView = LottieAnimationView
        super.mWebView = web_view
        super.needSyncCookie = true
        super.mUrl = intent.extras.getString(WEB_CONTENT)
        super.mWebView.addJavascriptInterface(this, "nativeMethod")
        super.isLoadContent = true
        super.initView()
    }

    override fun getLayoutResourse(): Int {
        return R.layout.activity_html_content
    }

    override fun initData() {
        exitIsFinish = intent.extras.getBoolean(WEB_EXIT_IS_FINISH)
    }

    @JavascriptInterface
    fun chargeSuccess() {
        EventBus.getDefault().post(BaseEvent<Any>(EventCode.RECHARGE_SUCCESS))
        MobclickAgent.onEvent(this, "wd_cz_czcg")
        finish()
    }

    @JavascriptInterface
    fun chargeFailed() {
        finish()
    }

    @JavascriptInterface
    fun recharge() {
        EventBus.getDefault().post(BaseEvent<Any>(EventCode.CONTINUE_RECHARGE))
        finish()
    }

    @JavascriptInterface
    fun withdrawSuccess() {
        ARouter.getInstance().build(ArouterConstant.APP_MAIN_ACTIVITY).withInt(ArouterParamConstant.CHOOSE_POSITION, 4).navigation()
    }

    @JavascriptInterface
    fun continueWithdraw() {
        EventBus.getDefault().post(BaseEvent<Any>(EventCode.WITHDRAW_SUCCESS))
        finish()
    }

    @JavascriptInterface
    fun withdrawFailed() {
        finish()
    }

    @JavascriptInterface
    fun verifySuccess() {
        MobclickAgent.onEvent(this, "wd_smrz_ok")
        if(exitIsFinish){
            finish()
        }else{
            ARouter.getInstance().build(ArouterConstant.APP_MAIN_ACTIVITY).withInt(ArouterParamConstant.CHOOSE_POSITION, 1).navigation()
        }
    }

    @JavascriptInterface
    fun verifyFailed() {
        finish()
    }

    @JavascriptInterface
    fun gotoInvest() {
        UUApplication.user?.setIsIdentityUserInfo(true)
        ARouter.getInstance().build(ArouterConstant.APP_MAIN_ACTIVITY).withInt(ArouterParamConstant.CHOOSE_POSITION, 1).navigation()
    }
}
