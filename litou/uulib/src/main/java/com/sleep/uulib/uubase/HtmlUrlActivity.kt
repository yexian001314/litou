package com.sleep.uulib.uubase

import android.content.Intent
import android.webkit.JavascriptInterface
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.sleep.commonlib.base.BaseEvent
import com.sleep.commonlib.base.EventCode
import com.sleep.commonlib.base.UUBaseWebActivity
import com.sleep.commonlib.constant.LibConstant
import com.sleep.commonlib.util.LibCommonUtil
import com.sleep.commonlib.util.LogUtil
import com.sleep.commonlib.util.StringUtil
import com.sleep.commonlib.util.ToastUtil
import com.sleep.uulib.R
import com.sleep.uulib.UUApplication
import com.sleep.uulib.account.LoginActivity
import com.sleep.uulib.account.RegistActivity
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ArouterParamConstant
import com.sleep.uulib.constant.Constant
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.enums.MainChooseType
import com.umeng.socialize.ShareAction
import com.umeng.socialize.UMShareAPI
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.UMImage
import com.umeng.socialize.media.UMWeb
import org.simple.eventbus.Subscriber


@Route(path = ArouterConstant.UUBASE_HTML_ACTIVITY)
class HtmlUrlActivity : UUBaseWebActivity() {

    override fun retryGetData() {
    }

    companion object {
        private const val PARAMS_WEB_URL = "web_url"
        private const val PARAMS_NEED_SYNC_COOKIE = "needSyncCookie"
        private const val PARAMS_RIGHT_TEXT = "rightText"
        fun launch(url: String, needSyncCookie: Boolean = false, rightText: String = "") {
            ARouter.getInstance().build(ArouterConstant.UUBASE_HTML_ACTIVITY)
                    .withString(PARAMS_WEB_URL, url)
                    .withBoolean(PARAMS_NEED_SYNC_COOKIE, needSyncCookie)
                    .withString(PARAMS_RIGHT_TEXT, rightText)
                    .navigation()
        }
    }

    override fun initView() {
        super.mLottieAnimationView = findViewById(R.id.LottieAnimationView)
        super.mWebView = findViewById(R.id.web_view)
        super.needSyncCookie = intent.extras.getBoolean(PARAMS_NEED_SYNC_COOKIE, false)
        super.mUrl = intent.extras.getString(PARAMS_WEB_URL)
        super.mWebView.addJavascriptInterface(this, "nativeMethod")
        super.initView()
    }

    override fun getLayoutResourse(): Int {
        return R.layout.activity_html_url
    }

    override fun initData() {
        val rightText = intent.extras.getString(PARAMS_RIGHT_TEXT)
        if (!StringUtil.isEmpty(rightText)) {
            setRightText(rightText)
            getBarView(TitleView.RIGHT_TV)?.setOnClickListener({
                if ("兑换记录" == rightText) {
                    HtmlUrlActivity.launch(NetConstant.H5_INTEGRATION_MALL_RECORD, true)
                }
            })
        }
    }

    @JavascriptInterface
    fun shareWeChart(shareUrl: String) {
        if (!LibCommonUtil.isAvilible(this, LibConstant.WECHAT_PACKET_NAME)) {
            ToastUtil.showToast("请先安装微信")
            return
        }
        val web = UMWeb(shareUrl)
        //缩略图
        web.title = Constant.SHARE_TITLE
        //缩略图
        web.setThumb(UMImage(this, R.mipmap.icon_umeng_share))
        //描述
        web.description = Constant.SHARE_DESC
        ShareAction(this)
                .setPlatform(SHARE_MEDIA.WEIXIN)
                .withMedia(web)
                .setCallback(shareListener)
                .share()
    }

    @JavascriptInterface
    fun shareMonments(shareUrl: String) {
        ToastUtil.showToast(shareUrl)
        if (!LibCommonUtil.isAvilible(this, LibConstant.WECHAT_PACKET_NAME)) {
            ToastUtil.showToast("请先安装微信")
            return
        }
        val web = UMWeb(shareUrl)
        //缩略图
        web.title = Constant.SHARE_TITLE
        //缩略图
        web.setThumb(UMImage(this, R.mipmap.icon_umeng_share))
        //描述
        web.description = Constant.SHARE_DESC
        ShareAction(this)
                .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                .withMedia(web)
                .setCallback(shareListener)
                .share()
    }

    @JavascriptInterface
    fun shareTencent(shareUrl: String) {
        LogUtil.e("shareUrl = $shareUrl")
        if (!LibCommonUtil.isAvilible(this, LibConstant.TENCENT_PACKET_NAME) && !LibCommonUtil.isAvilible(this, LibConstant.TENCENT_TIM_PACKET_NAME)) {
            ToastUtil.showToast("请先安装腾讯相关产品")
            return
        }
        val web = UMWeb(shareUrl)
        //缩略图
        web.title = Constant.SHARE_TITLE
        //缩略图
        web.setThumb(UMImage(this, R.mipmap.icon_umeng_share))
        //描述
        web.description = Constant.SHARE_DESC
        ShareAction(this)
                .setPlatform(SHARE_MEDIA.QQ)
                .withMedia(web)
                .setCallback(shareListener)
                .share()
    }

    @JavascriptInterface
    fun shareQZone(shareUrl: String) {
        if (!LibCommonUtil.isAvilible(this, LibConstant.TENCENT_PACKET_NAME) && !LibCommonUtil.isAvilible(this, LibConstant.TENCENT_TIM_PACKET_NAME)) {
            ToastUtil.showToast("请先安装腾讯相关产品")
            return
        }
        val web = UMWeb(shareUrl)
        //缩略图
        web.title = Constant.SHARE_TITLE
        //缩略图
        web.setThumb(UMImage(this, R.mipmap.icon_umeng_share))
        //描述
        web.description = Constant.SHARE_DESC
        ShareAction(this)
                .setPlatform(SHARE_MEDIA.QZONE)
                .withMedia(web)
                .setCallback(shareListener)
                .share()
    }

    @JavascriptInterface
    fun loginAndRegist() {
        if (UUApplication.user == null) {
            LoginActivity.launch()
        } else {
            ARouter.getInstance().build(ArouterConstant.APP_MAIN_ACTIVITY).withInt(ArouterParamConstant.CHOOSE_POSITION, 3).navigation()
        }
        finish()
    }

    @JavascriptInterface
    fun loginAndRefresh(){
        if (UUApplication.user == null) {
            LoginActivity.launch()
        } else {
            ARouter.getInstance().build(ArouterConstant.APP_MAIN_ACTIVITY).withInt(ArouterParamConstant.CHOOSE_POSITION, 3).navigation()
        }
    }
    @JavascriptInterface
    fun regist(){
        if (UUApplication.user == null) {
            RegistActivity.launch()
        } else {
            ARouter.getInstance().build(ArouterConstant.APP_MAIN_ACTIVITY).withInt(ArouterParamConstant.CHOOSE_POSITION, 3).navigation()
        }
    }
    @JavascriptInterface
    fun chooseTable(choosePosition: Int) {
        if(choosePosition  == MainChooseType.INFORMATION.ordinal || choosePosition  == MainChooseType.MINE.ordinal){
            if (UUApplication.user == null) {
                LoginActivity.launch()
            } else {
                ARouter.getInstance().build(ArouterConstant.APP_MAIN_ACTIVITY).withInt(ArouterParamConstant.CHOOSE_POSITION, choosePosition).navigation()
            }
        }else {
            ARouter.getInstance().build(ArouterConstant.APP_MAIN_ACTIVITY).withInt(ArouterParamConstant.CHOOSE_POSITION, choosePosition).navigation()
        }
        finish()
    }

    private val shareListener = object : UMShareListener {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        override fun onStart(platform: SHARE_MEDIA) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        override fun onResult(platform: SHARE_MEDIA) {}

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        override fun onError(platform: SHARE_MEDIA, t: Throwable) {}

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        override fun onCancel(platform: SHARE_MEDIA) {}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data)
    }

    @Subscriber
    fun receiveLogin(baseEvent: BaseEvent<Any>){
        if(baseEvent.eventCode == EventCode.LOGIN){
            super.reloadWeb()
        }
    }
}
