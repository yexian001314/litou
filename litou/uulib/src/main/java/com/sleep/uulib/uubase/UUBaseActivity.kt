package com.sleep.uulib.uubase

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import com.example.twitterlib.wigdet.dialog.CommonFragmentDialog
import com.example.twitterlib.wigdet.dialog.DialogFactory
import com.sleep.commonlib.base.BaseActivity
import com.sleep.commonlib.base.BaseEvent
import com.sleep.commonlib.base.EventCode
import com.sleep.commonlib.util.AndroidWorkaround
import com.sleep.commonlib.util.BarUtils
import com.sleep.commonlib.util.StatusBarUtil
import com.sleep.commonlib.view.stateManager.StateLayoutManagerBuilder
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.R
import com.sleep.uulib.UUApplication
import com.sleep.uulib.account.LoginActivity
import com.sleep.uulib.util.UUCommonUtil
import com.sleep.uulib.widget.dialog.ProgressDialog
import com.umeng.analytics.MobclickAgent
import com.umeng.message.PushAgent
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_base_no_toolbar.*
import kotlinx.android.synthetic.main.base_title_bar.*
import org.simple.eventbus.Subscriber


/**
 * Created by SleepYM09 on 2017/10/18.
 *
 * blog: www.sleepym09.com
 */
abstract class UUBaseActivity : BaseActivity() {

    private var leftText = ""
    private var rightText = ""
//    internal var mViewhook = ViewHook()
    /**
     * ToolBar中间显示的title
     */
    private var title = ""

    /**
     * 当有两个或以上连续请求的时候，无法用progressObserver，需要自己手动控制dialog，则用此dialog
     */
    private var mDialog: ProgressDialog? = null

    /**
     * 页面状态管理器
     */
    lateinit var mStateManager: StatesLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        StatusBarUtil.setTransparent(this)
        // 锁定竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        if (shouldShowToolBar()) {
            setContentView(R.layout.activity_base)
            //设置toolBar
            ll_status_bar.visibility = View.VISIBLE
            val layoutParams = ll_status_bar.layoutParams
            layoutParams.height = BarUtils.getStatusBarHeight(this)
            ll_status_bar.layoutParams = layoutParams
        } else {
            setContentView(R.layout.activity_base_no_toolbar)
        }

        //推送打开率统计
        PushAgent.getInstance(this).onAppStart()

        //适配华为底部虚拟按键
        if(UUCommonUtil.hasNavBar(this)){
            window.decorView.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
        }

        AndroidWorkaround.assistActivity(window.decorView.findViewById(android.R.id.content))

        initPagerStateManager()

        initToolBar()

//        setToolBarColor(R.color.colorAccent)

        initView()
//        window.decorView.postDelayed({ mViewhook.hookInit(window.decorView.rootView) }, 500)
        initData()
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }

    override fun onResume() {
        hideDialog()
        super.onResume()
        MobclickAgent.onResume(this)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (shouldClickRequestFocus()) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
            window.decorView.findViewById<View>(android.R.id.content).requestFocus()
        }
        return super.onTouchEvent(event)
    }

    private fun initPagerStateManager() {
        val builder = StateLayoutManagerBuilder(this)
                .setContentView(LayoutInflater.from(this).inflate(getLayoutResourse(), null))
                .setLoadingLayoutResId(R.layout.item_state_progress_loading_view)
                .setContentEmptyLayoutResId(R.layout.item_state_empty_view)
                .setContentErrorResId(R.layout.item_state_error_view)
                .setNetWorkErrorLayoutResId(R.layout.item_state_network_error_view)
                .setSystemMaintainResId(R.layout.item_state_system_maintain_view)
                .setSystemBusyResId(R.layout.item_state_system_busy_view)
                .setRetryViewId(R.id.retry_view)
                .setOnRetryListener { retryGetData() }
        mStateManager = builder.create()
        if (shouldShowToolBar()) {
            ll_root.addView(mStateManager.rootLayout, 2)
        } else {
            ll_root_no_toolbar.addView(mStateManager.rootLayout, 0)
        }
    }

    private fun initToolBar() {
        if (!shouldShowToolBar()) return
        //设置toolbar属性
        iv_left.setOnClickListener { onLeftViewClick(iv_left) }
        tv_left.setOnClickListener { onLeftTextClick(tv_left) }
        iv_right.setOnClickListener { onRightViewClick(iv_right) }
        tv_right.setOnClickListener { onRightTextClick(tv_right) }
    }

    open fun onLeftViewClick(iv_left: ImageView) {
        onBackPressed()
    }

    open fun onRightViewClick(iv_right: ImageView) {}
    open fun onLeftTextClick(tv_left: TextView) {}
    open fun onRightTextClick(tv_right: TextView) {}

    /**
     * 默认需要显示toolbar，子类不需要的时候重写即可
     */
    open fun shouldShowToolBar(): Boolean {
        return true
    }

    open fun shouldClickRequestFocus(): Boolean {
        return true
    }

    /**
     * 该activity是否需要接收用户离线通知
     */
    open fun shouldReceiveOtherLogin(): Boolean {
        return true
    }

    /**
     * 该activity是否需要接收系统维护通知
     */
    open fun shouldReceiveSystemMaintain(): Boolean {
        return true
    }

    /**
     * 设置StatusBar颜色
     */
    fun setToolBarColor(colorRes: Int) {
        if (shouldShowToolBar()) {
            ll_status_bar.setBackgroundResource(colorRes)
            rl_base_title_bar.setBackgroundResource(colorRes)
        }
    }

    fun hideBackView() {
        iv_left.visibility = View.GONE
    }

    /**
     * 默认显示左边视图（即返回按钮）
     */
    fun setLeftView(src: Int) {
        iv_left.visibility = View.VISIBLE
        iv_left.setImageResource(src)
    }

    /**
     * 默认不显示左边文字
     */
    fun setleftText(leftText: String) {
        this.leftText = leftText
        tv_left.visibility = View.VISIBLE
        tv_left.text = leftText
    }

    /**
     * 默认不显示右边视图
     */
    fun setRightView(src: Int) {
        iv_right.visibility = View.VISIBLE
        iv_right.setImageResource(src)
    }

    /**
     * 默认不显示右边文字
     */
    fun setRightText(rightText: String) {
        this.rightText = rightText
        tv_right.visibility = View.VISIBLE
        tv_right.text = rightText
    }

    /**
     * 网络出错或者服务器出错点击重试回调
     */
    abstract fun retryGetData()

    fun setTitle(title: String) {
        this.title = title
        tv_title.visibility = View.VISIBLE
        tv_title.text = title
    }

    fun getBarView(viewName: TitleView): View? {
        return when (viewName) {
            TitleView.LEFT_TV -> tv_left
            TitleView.RIGHT_TV -> tv_right
            TitleView.LEFT_IV -> iv_left
            TitleView.RIGHT_IV -> iv_right
            TitleView.TITLE_TV -> tv_title
        }
    }

    fun showDialog() {
        if (mDialog == null) {
            mDialog = ProgressDialog(false, this)
        }
        if (!mDialog!!.isShowing) {
            mDialog!!.show()
        }
    }

    fun hideDialog() {
        if (mDialog == null) return
        if (mDialog!!.isShowing) {
            mDialog!!.dismiss()
            mDialog = null
        }
    }

    protected var commonDialog: CommonFragmentDialog? = null

    /**
     * 收到被挤下线的通知，需要重新登录
     */
    @Subscriber
   protected open fun receiveOtherLogin(baseEvent: BaseEvent<Any>) {
        if (baseEvent.eventCode == EventCode.OTHER_LOGIN && shouldReceiveOtherLogin()) {
            //判断是否重复显示
            if ((commonDialog == null || !commonDialog?.dialog?.isShowing!!) && isResume) {
                commonDialog = DialogFactory.instance.getCommonDialog("提示", "请重新登录", "确定", null, false,
                        View.OnClickListener {
                            LoginActivity.launch()
                        })
                commonDialog?.show(supportFragmentManager, "outer_login")
            }
        }
    }

    /**
     * 收到系统在维护的通知
     */
    @Subscriber
    fun receiveServerMaintain(baseEvent: BaseEvent<Any>) {
        if (baseEvent.eventCode == EventCode.SYSTEM_MAINTAIN) {
            val shouldReceiveSystemMaintain = shouldReceiveSystemMaintain()
            if (shouldReceiveSystemMaintain && !UUApplication.systemMaintainDialogIsShow ) {
                UUApplication.systemMaintainDialogIsShow = true
                DialogFactory.instance.getCommonDialog("提示", "服务正在升级，稍后即可恢复使用，\n敬请谅解~", "知道了", null, false,
                        View.OnClickListener {
                        }).show(supportFragmentManager, "server_maintain")
            }
        }
    }

    enum class TitleView {
        LEFT_TV,
        RIGHT_TV,
        LEFT_IV,
        RIGHT_IV,
        TITLE_TV
    }
}