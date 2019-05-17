package com.sleep.commonlib.base

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.webkit.*
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView
import com.sleep.commonlib.BaseApplication
import com.sleep.commonlib.util.LogUtil
import com.sleep.commonlib.util.StringUtil
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.netWork.CookiesManager
import com.sleep.uulib.netWork.OkHttpUtils
import com.sleep.uulib.netWork.multi_base_url.InvalidUrlException
import com.sleep.uulib.netWork.multi_base_url.MultiBaseUrlInterceptor
import com.sleep.uulib.uubase.UUBaseActivity
import okhttp3.HttpUrl


abstract class UUBaseWebActivity : UUBaseActivity() {

    protected lateinit var mWebView: WebView
    protected lateinit var mUrl: String
    protected lateinit var mLottieAnimationView: LottieAnimationView
    /**
     * 是否需要同步cookie
     */
    protected var needSyncCookie = false

    /**
     * 加载的是否是H5代码
     */
    protected var isLoadContent = false

    override fun beforeSetContent() {
        super.beforeSetContent()
        requestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    override fun onLeftViewClick(iv_left: ImageView) {
        finish()
    }

    override fun onPause() {
        super.onPause()
        mWebView.onPause()
        mWebView.pauseTimers()
    }

    override fun onResume() {
        super.onResume()
        mWebView.onResume()
        mWebView.resumeTimers()
    }

    override fun initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        if (StringUtil.isEmpty(mUrl)) {
            mUrl = NetConstant.BASE_URL
        }
        if(!isLoadContent){
            val globalDomain = MultiBaseUrlInterceptor.getInstance().globalDomain
            if (MultiBaseUrlInterceptor.getInstance().isRun && globalDomain != null && globalDomain.host().isNotEmpty()) {
                val parseUrl = HttpUrl.parse(mUrl)
                if (parseUrl == null) {
                    throw InvalidUrlException(mUrl)
                } else {
                    val newUrl = parseUrl.newBuilder().scheme(MultiBaseUrlInterceptor.getInstance().globalDomain.scheme())
                            .host(MultiBaseUrlInterceptor.getInstance().globalDomain.host())
                            .port(MultiBaseUrlInterceptor.getInstance().globalDomain.port())
                            .build()
                    mUrl = newUrl.toString()
                }
            }
        }
        LogUtil.i("BaseWebViewActivity", mUrl)
        initSetting()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initSetting() {
        val webSettings = mWebView.settings
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT

        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.allowFileAccess = true

        //在Android 5.0上 Webview 默认不允许加载 Http 与 Https 混合内容
        //但是到了5.0以上,就是不允许，实际情况下很我们很难确定所有的网页都是https的,所以就需要这一步的操作
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // chromium, enable hardware acceleration
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        } else {
            // older android version, disable hardware acceleration
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        }

        // 设置支持javascript脚本
        webSettings.javaScriptEnabled = true
        //是否开启本地DOM存储  鉴于它的安全特性（任何人都能读取到它，尽管有相应的限制，将敏感数据存储在这里依然不是明智之举），Android 默认是关闭该功能的。
        webSettings.domStorageEnabled = true
        // 设置可以支持缩放
        webSettings.setSupportZoom(true)
        // 设置出现缩放工具 是否使用WebView内置的缩放组件，由浮动在窗口上的缩放控制和手势缩放控制组成，默认false
        webSettings.builtInZoomControls = true
        //隐藏缩放工具
        webSettings.displayZoomControls = false
        // 扩大比例的缩放
        webSettings.useWideViewPort = true
        //自适应屏幕
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        webSettings.loadWithOverviewMode = true
        //支持多窗口
        webSettings.setSupportMultipleWindows(true)
        webSettings.databaseEnabled = true
        mWebView.isSaveEnabled = true
        mWebView.keepScreenOn = true

        //设置此方法可在WebView中打开链接，反之用浏览器打开
        mWebView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                if (!mWebView.settings.loadsImagesAutomatically) {
                    mWebView.settings.loadsImagesAutomatically = true
                }
                mLottieAnimationView.visibility = View.GONE
                super.onPageFinished(view, url)
            }

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                mLottieAnimationView.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)

            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                Log.e("yexm",url)
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url)
                    return false
                }
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
                return true
            }
        }

        // 设置setWebChromeClient对象
        mWebView.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)
                if (!StringUtil.isEmpty(title) && "about:blank" != title) {
                    if ("wpa.b.qq.com/cgi/wpa.php?ln=2&uin=4006688956" == title) {
                        setTitle("跳转中...")
                    } else {
                        setTitle(title)
                    }
                }
            }

            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                mLottieAnimationView.progress = newProgress.toFloat()
            }
        }

        getAndSyncCookies()

        if (!isLoadContent) {
            mWebView.loadUrl(mUrl)
        } else {
            mWebView.loadDataWithBaseURL(null, mUrl, "text/html", "utf-8", null)
        }
    }

    private fun syncCookie(url: String, cookie: String) {
        val cookieManager = CookieManager.getInstance()
        cookieManager.setCookie(url, cookie)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(BaseApplication.sInstance).sync()
        }
//        LogUtil.e("cookies","set cookie is ${cookieManager.getCookie(url)}")
    }

    override fun onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    fun reloadWeb() {
        getAndSyncCookies()
        mWebView.reload()
    }

    /**
     * 获取并同步Cookies
     */
    private fun getAndSyncCookies() {
        if (needSyncCookie) {
            val cookieJar = OkHttpUtils.getOkHttpClient().cookieJar() as CookiesManager
            val cookies = cookieJar.cookieStore.get(HttpUrl.parse(NetConstant.BASE_URL + NetConstant.USER_LOGIN))
            for (cookie in cookies) {
                if (isLoadContent) {
                    //加载内容的时候，WebView 会将 content前面一部分UrlEncode之后当做host_key存储，导致和域名当做host_key存储出现cookie不一致的情况
                    //webView会自动维护cookie，并保存在data/data/package_name/app_WebView/Cookies.db
                    //可以通过CookieManager.sync()方法手动更新该数据库内容
                    syncCookie(NetConstant.BASE_WEB_URL, cookie.toString())
                } else {
                    syncCookie(mUrl, cookie.toString())
                }
            }
        }
    }
}
