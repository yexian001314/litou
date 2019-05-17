package com.shqj.information.fragment


import android.Manifest
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.shqj.information.R
import com.shqj.information.adapbter.InformationCommodityAdapter
import com.shqj.information.mvp.presenter.InformationPresenter
import com.shqj.information.mvp.view.InformationView
import com.sleep.commonlib.util.LibCommonUtil
import com.sleep.commonlib.util.LogUtil
import com.sleep.commonlib.util.ToastUtil
import com.sleep.uulib.UUApplication
import com.sleep.uulib.account.LoginActivity
import com.sleep.uulib.bean.*
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.model.CommonModule
import com.sleep.uulib.mvp.view.QueryUserInfoView
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.util.NumberFormatUtils
import com.sleep.uulib.uubase.HtmlUrlActivity
import com.sleep.uulib.uubase.UUBaseFragment
import com.umeng.analytics.MobclickAgent
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.PermissionListener
import kotlinx.android.synthetic.main.information_fragment_information.*


/**
 * 咨询界面（当前版本作为互动页）.
 */

@Route(path = ArouterConstant.APP_INFORMATION_FRAGMENT)
class InformationFragment : UUBaseFragment(), QueryUserInfoView, View.OnClickListener, InformationView, BaseQuickAdapter.OnItemClickListener {

    /**
     * 用户是否签到
     */
    private var isSigned: Boolean = false

    /**
     * 是否需要刷新用户信息
     */
    private var mIsNeedRefreshUserInfo = true

    private lateinit var mAdapter: InformationCommodityAdapter

    private lateinit var mPresenter: InformationPresenter

    private val REQUEST_CODE_SETTING = 300

    override fun shouldReceiveOtherLogin(): Boolean {
        return false
    }

    override fun getLayoutResourse(): Int {
        return R.layout.information_fragment_information
    }

    override fun onFirstUserVisible() {
        CommonModule.queryUserInfo(this)
    }

    override fun onUserVisible() {
        if (mIsNeedRefreshUserInfo) {
            CommonModule.queryUserInfo(this)
            mIsNeedRefreshUserInfo = false
        }

    }

    override fun initView(view: View) {
        ll_wonderful_activity.setOnClickListener(this)
        tv_sign.setOnClickListener(this)
        ll_contact_server.setOnClickListener(this)
        tv_integration_mall.setOnClickListener(this)
        ll_invest_friend.setOnClickListener(this)
        tv_integration_detail.setOnClickListener(this)
        tv_integration_explain.setOnClickListener(this)
        tv_available_points.setOnClickListener(this)

        val gridLayoutManager = GridLayoutManager(context, 3)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        information_recycler.layoutManager = gridLayoutManager
        information_recycler.isNestedScrollingEnabled = false
//        information_recycler.addItemDecoration(GridLayoutItemDecoration(context, 10f))
        mAdapter = InformationCommodityAdapter(context, R.layout.item_information_commodity)
        information_recycler.adapter = mAdapter
        mAdapter.onItemClickListener = this
    }

    override fun initData() {
        mPresenter = InformationPresenter(context, this, mStateManager)
        mPresenter.getIntegralCommodity()
    }

    override fun onResume() {
//        super.onResume就会调用OnUserVisible方法，所以状态变更需要放在super.onResume()之前
        mIsNeedRefreshUserInfo = true
        super.onResume()
        if (UUApplication.user != null) {
            tv_available_points.setTextSize(COMPLEX_UNIT_SP, 34f)
            tv_available_points.text = NumberFormatUtils.getFormatNumberWithNoDigital(UUApplication.user?.getTotalPoints()!!)
            if (isSigned) {
                changeSignBtnStatus(true)
            } else {
                changeSignBtnStatus(false)
            }
            mPresenter.getIntegralCommodity()
            mPresenter.queryUserIsSign()
        } else {
            tv_sign_days.text = "已连续签到: 0天"
            tv_available_points.text = "立即登录"
            tv_available_points.setTextSize(COMPLEX_UNIT_SP, 20f)
            changeSignBtnStatus(false)
        }
    }

    override fun retryGetData() {
        mStateManager.showContent()
        CommonModule.queryUserInfo(this)
    }

    override fun onClick(v: View?) {
        if (LibCommonUtil.isFastDoubleClick()) {
            return
        }
        when (v?.id) {
            R.id.ll_wonderful_activity -> {
                //精彩活动
                MobclickAgent.onEvent(context, "hd_jfzx_jchd")
                ARouter.getInstance().build(ArouterConstant.INFORMATION_WONDERFUL).navigation()
            }
            R.id.tv_available_points -> {
                if (UUApplication.user == null) {
                    LoginActivity.launch()
                }
            }
            R.id.ll_contact_server -> {
                //消息中心
                HtmlUrlActivity.launch(NetConstant.H5_MESSAGE_CENTER, true)

            }
//            R.id.rl_advice_feedback -> {
//                //意见反馈
//                MobclickAgent.onEvent(context, "hd_feedback")
//                ARouter.getInstance().build(ArouterConstant.INFORMATION_FEEDBACK).navigation()
//            }
            R.id.tv_integration_mall -> {
                //积分商城
                MobclickAgent.onEvent(context, "hd_jfzx_gd")
                HtmlUrlActivity.launch(NetConstant.H5_INTEGRATION_MALL, true, "兑换记录")
            }
            R.id.ll_invest_friend -> {
                //我的邀请
                MobclickAgent.onEvent(context, "hd_wdyq")
                HtmlUrlActivity.launch(NetConstant.H5_MY_INVITE, true)
            }
            R.id.tv_sign -> {
                if (UUApplication.user == null) {
                    LoginActivity.launch()
                    return
                }
                //签到
                MobclickAgent.onEvent(context, "hd_jfzx_ljqd")
                if (isSigned) {
                    return
                }
                mPresenter.userSign()
            }
            R.id.tv_integration_detail -> {
                //积分明细
                MobclickAgent.onEvent(context, "hd_jfzx_jfmx")
                HtmlUrlActivity.launch(NetConstant.H5_INTEGRAL_DETAIL, true)
            }
            R.id.tv_integration_explain -> {
                //如何获取积分
                MobclickAgent.onEvent(context, "hd_jfzx_hdjf")
                HtmlUrlActivity.launch(NetConstant.H5_INTEGRAL_EXPLAIN)
            }
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        val itemData = mAdapter.data[position]
        HtmlUrlActivity.launch(NetConstant.H5_PRODUCTS_EXCHANGE.format(itemData.id, itemData.itemType), true)
    }

    override fun queryUserIsSignSuccess(data: IsSignedBean) {
        isSigned = data.isResult
        setIsUserSignInfo()
        if (isSigned) {
            changeSignBtnStatus(true)
        } else {
            changeSignBtnStatus(false)
        }
    }

    override fun userSignSuccess(data: SignBean) {
        ToastUtil.showToast("已签到")
        isSigned = true
        setSignInfo(data)
    }

    override fun queryUserInfoFailure(e: Throwable?, errorCode: Int) {
        NetCommonMethod.judgeError(errorCode, mStateManager)
    }

    override fun queryUserInfoSuccess(data: QueryUserInfoBean) {
    }

    override fun queryBankFailure(e: Throwable?, errorCode: Int) {
        NetCommonMethod.judgeError(errorCode, mStateManager)
    }

    override fun queryBankSuccess(data: QueryBankBean) {

    }

    override fun getIntegralCommoditySuccess(data: IntegralCommodityBean) {
        mAdapter.setNewData(data.itemTypeEOList)
    }

    /**
     * 查询用户签到信息后设置数据
     */
    private fun setIsUserSignInfo() {
        //已经登录后再做数据请求
        if (UUApplication.user == null) {
            return
        }
        if (isSigned) {
            //已签到
            //设置已连续签到天数
            tv_sign_days.text = "已连续签到: " + UUApplication.user?.getSignCount() + "天"
            //设置可用积分
            tv_available_points.text = NumberFormatUtils.getFormatNumberWithNoDigital(UUApplication.user?.getTotalPoints()!!)
            //
            changeSignBtnStatus(true)
        } else {
            //未签到
            //设置已连续签到天数
            tv_sign_days.text = "已连续签到: " + UUApplication.user?.getSignCount() + "天"
            //设置可用积分
            tv_available_points.text = NumberFormatUtils.getFormatNumberWithNoDigital(UUApplication.user?.getTotalPoints()!!)
            changeSignBtnStatus(false)
        }

    }

    /**
     * 签到后变更数据
     */
    private fun setSignInfo(data: SignBean) {
        //隐藏未签到显示已经签到
        changeSignBtnStatus(true)
        //设置连续签到
        tv_sign_days.text = "已连续签到: " + data.signCount + "天"
//        设置可用积分
        tv_available_points.text = NumberFormatUtils.getFormatNumberWithNoDigital(data.totalPoints.toDouble())
        //设置积分
        UUApplication.user?.setSignCount(data.totalPoints)
        //设置连续签到天数
        UUApplication.user?.setTotalPoints(data.signCount.toDouble())
        //设置已经签到
        UUApplication.user?.setSign(true)
    }

    /**
     * 设置按钮文字以及对应图片
     */
    private fun changeSignBtnStatus(isSigned: Boolean) {
        if (isSigned) {
            tv_sign.text = "已签到"
        } else {
            tv_sign.text = "立即签到"
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
                    AndPermission.rationaleDialog(context, rationale).show()
                })
                .start()
    }

    /**
     * 跳转QQ客服
     */
    private fun contackQQServer() {
        if (LibCommonUtil.isAvilible(context, "com.tencent.mobileqq") || LibCommonUtil.isAvilible(context, "com.tencent.tim")) {
            val urlQQ = "http://wpa.b.qq.com/cgi/wpa.php?ln=2&uin=4006688956"
            HtmlUrlActivity.launch(urlQQ)
        } else {
            ToastUtil.showToast("请先安装腾讯QQ或TIM")
        }
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
            if (AndPermission.hasAlwaysDeniedPermission(context, deniedPermissions)) {
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(activity, REQUEST_CODE_SETTING).show()
            }
        }
    }
}
