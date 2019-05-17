package com.sleep.uulib.uubase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.twitterlib.wigdet.dialog.CommonFragmentDialog
import com.example.twitterlib.wigdet.dialog.DialogFactory
import com.sleep.commonlib.base.BaseEvent
import com.sleep.commonlib.base.BaseFragment
import com.sleep.commonlib.base.EventCode
import com.sleep.commonlib.util.BarUtils
import com.sleep.commonlib.view.stateManager.StateLayoutManagerBuilder
import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.uulib.R
import com.sleep.uulib.account.LoginActivity
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.base_title_bar.*
import org.simple.eventbus.Subscriber

/**
 * Created by SleepYM09 on 2017/10/18.
 *
 * blog: www.sleepym09.com
 */
abstract class UUBaseFragment : BaseFragment() {

    private var leftText = ""
    private var rightText = ""

    private val ivLeft: ImageView
        get() {
            return mRootView.findViewById(R.id.iv_left)
        }

    private val ivRight: ImageView
        get() {
            return mRootView.findViewById(R.id.iv_right)
        }
    private val tvLeft: TextView
        get() {
            return mRootView.findViewById(R.id.tv_left)
        }

    private val tvRight: TextView
        get() {
            return mRootView.findViewById(R.id.tv_right)
        }

    private val tvTitle: TextView
        get() {
            return mRootView.findViewById(R.id.tv_title)
        }

    /**
     * ToolBar中间显示的title
     */
    private var title = ""

    /**
     * 页面状态管理器
     */
    lateinit var mStateManager: StatesLayoutManager

    override fun childInit() {
        initPagerStateManager()
        initToolBar()
//        setToolBarColor(R.color.colorAccent)
        initView(mRootView)
        initData()
    }

    override fun onResume() {
        hideDialog()
        super.onResume()
    }

    private fun initPagerStateManager() {
        val builder = StateLayoutManagerBuilder(this.activity)
                .setContentView(LayoutInflater.from(this.activity).inflate(getLayoutResourse(), null))
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
            mRootView.findViewById<ViewGroup>(R.id.ll_root).addView(mStateManager.rootLayout, 2)
        } else {
            mRootView.findViewById<ViewGroup>(R.id.ll_root_no_toolbar).addView(mStateManager.rootLayout, 0)
        }
    }

    private fun initToolBar() {
        if (!shouldShowToolBar()) return
        //设置toolbar属性
        ivLeft.setOnClickListener { onLeftViewClick(ivLeft) }
        tvLeft.setOnClickListener { onLeftTextClick(tvLeft) }
        ivRight.setOnClickListener { onRightViewClick(ivRight) }
        tvRight.setOnClickListener { onRightTextClick(tvRight) }
    }

    override fun getRootView(inflater: LayoutInflater?, container: ViewGroup?): View {
        if (shouldShowToolBar()) {
            mRootView = inflater!!.inflate(R.layout.activity_base, container, false)
            //设置toolBar
            val statusBar = mRootView.findViewById<LinearLayout>(R.id.ll_status_bar)
            statusBar.visibility = View.VISIBLE
            val layoutParams = statusBar.layoutParams
            layoutParams.height = BarUtils.getStatusBarHeight(context)
            statusBar.layoutParams = layoutParams
        } else {
            mRootView = inflater!!.inflate(R.layout.activity_base_no_toolbar, container, false)
        }
        return mRootView
    }

    abstract fun getLayoutResourse(): Int

    open fun onLeftViewClick(iv_left: ImageView) {
        activity.onBackPressed()
    }

    open fun onRightViewClick(iv_right: ImageView) {}
    open fun onLeftTextClick(tv_left: TextView) {}
    open fun onRightTextClick(tv_right: TextView) {}

    /**
     * 设置StatusBar颜色
     */
    fun setToolBarColor(colorRes: Int) {
        if (shouldShowToolBar()) {
            ll_status_bar.setBackgroundResource(colorRes)
            rl_base_title_bar.setBackgroundResource(colorRes)
        }
    }

    /**
     * 默认不需要显示toolbar，子类需要的时候重写即可
     */
    open fun shouldShowToolBar(): Boolean {
        return false
    }

    fun hideBackView() {
        iv_left.visibility = View.GONE
    }

    /**
     * 默认显示左边视图（即返回按钮）
     */
    fun setLeftView(src: Int) {
        ivLeft.visibility = View.VISIBLE
        ivLeft.setImageResource(src)
    }

    /**
     * 默认不显示左边文字
     */
    fun setleftText(leftText: String) {
        this.leftText = leftText
        tvLeft.visibility = View.VISIBLE
        tvLeft.text = leftText
    }

    /**
     * 默认不显示右边视图
     */
    fun setRightView(src: Int) {
        ivRight.visibility = View.VISIBLE
        ivRight.setImageResource(src)
    }

    /**
     * 默认不显示右边文字
     */
    fun setRightText(rightText: String) {
        this.rightText = rightText
        tvRight.visibility = View.VISIBLE
        tvRight.text = rightText
    }

    /**
     * 网络出错或者服务器出错点击重试回调
     */
    abstract fun retryGetData()

    fun setTitle(title: String) {
        this.title = title
        tvTitle.visibility = View.VISIBLE
        tvTitle.text = title
    }

    fun getBarView(viewName: UUBaseActivity.TitleView): View? {
        return when (viewName) {
            UUBaseActivity.TitleView.LEFT_TV -> tvLeft
            UUBaseActivity.TitleView.RIGHT_TV -> tvRight
            UUBaseActivity.TitleView.LEFT_IV -> tvLeft
            UUBaseActivity.TitleView.RIGHT_IV -> ivRight
            UUBaseActivity.TitleView.TITLE_TV -> tvTitle
        }
    }

    fun showDialog() {
        val uuBaseActivity = activity as UUBaseActivity
        uuBaseActivity.showDialog()
    }

    fun hideDialog() {
        val uuBaseActivity = activity as UUBaseActivity
        uuBaseActivity.hideDialog()
    }

    /**
     * 该activity是否需要接收用户离线通知 , fragment中默认不接收 ，activity中默认接收
     */
    open fun shouldReceiveOtherLogin(): Boolean {
        return false
    }

    private var commonDialog: CommonFragmentDialog? = null

    /**
     * 收到被挤下线的通知，需要重新登录
     */
    @Subscriber
    fun receiveOtherLogin(baseEvent: BaseEvent<Any>) {
        if (baseEvent.eventCode == EventCode.OTHER_LOGIN && shouldReceiveOtherLogin()) {
            //判断是否重复显示
            if ((commonDialog == null || !commonDialog?.dialog?.isShowing!!) && mIsResume) {
                commonDialog = DialogFactory.instance.getCommonDialog("提示", "请重新登录", "确定", null, false,
                        View.OnClickListener { LoginActivity.launch() })
                commonDialog?.show(fragmentManager, "outerLogin")
            }
        }
    }

}