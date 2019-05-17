package com.shqj.arrange.activity

import android.support.v4.app.Fragment
import android.view.View
import android.widget.RelativeLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.twitterlib.wigdet.dialog.DialogFactory
import com.shqj.arrange.R
import com.shqj.arrange.adapter.ProjectDetailIndicatorAdapter
import com.shqj.arrange.adapter.ProjectDetailPagerAdapter
import com.shqj.arrange.fragment.InvestRecordFragment
import com.shqj.arrange.fragment.ProductIntroductionFragment
import com.shqj.arrange.fragment.RelateInformationFragment
import com.shqj.arrange.mvp.presenter.ProjectDetailPresenter
import com.shqj.arrange.mvp.view.ProjectDetailView
import com.sleep.commonlib.base.BaseEvent
import com.sleep.commonlib.base.EventCode
import com.sleep.commonlib.util.SizeUtils
import com.sleep.commonlib.util.SpannableStringUtils
import com.sleep.commonlib.util.StringUtil
import com.sleep.uulib.UUApplication
import com.sleep.uulib.account.LoginActivity
import com.sleep.uulib.bean.CheckInvestedBean
import com.sleep.uulib.bean.ProjectDetailBean
import com.sleep.uulib.bean.ProjectDetailLimit
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ArouterParamConstant
import com.sleep.uulib.util.NumberFormatUtils
import com.sleep.uulib.util.NumberUtils
import com.sleep.uulib.uubase.UUBaseActivity
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.arrange_activity_porject_detail.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import org.simple.eventbus.Subscriber
import java.text.SimpleDateFormat
import java.util.*

/**
 * 项目详情
 * params :
 *      orderId 订单号
 */
@Route(path = ArouterConstant.ARRANGE_PROJECT_DETAIL)
class ProjectDetailActivity : UUBaseActivity(), ProjectDetailView, View.OnClickListener {

    private val mPresenter: ProjectDetailPresenter get() = ProjectDetailPresenter(this, mStateManager)

    private lateinit var mProjectDetail: ProjectDetailBean

    private lateinit var mViewPagerAdapter: ProjectDetailPagerAdapter

    private val mIndicatorTitles: List<String>
        get() {
            return listOf(resources.getString(R.string.product_introduction),
                    resources.getString(R.string.relate_information),
                    resources.getString(R.string.invest_record))
        }

    private lateinit var mFragments: List<Fragment>

    /**
     * 订单号
     */
    private lateinit var orderId: String
    /**
     * 标的类型
     */
    private lateinit var subjectName: String
    /**
     * 借款类型 1 -> 车贷  2 -> 房贷 3 -> 企业贷
     */
    private lateinit var loanType: String

    /**
     * 投资记录tab
     */
    private lateinit var investRecordFragment: InvestRecordFragment

    /**
     * 项目介绍tab
     */
    private lateinit var introductionFragment: ProductIntroductionFragment

    /**
     * 相关资料tab
     */
    private lateinit var informationFragment: RelateInformationFragment

    override fun getLayoutResourse(): Int {
        return R.layout.arrange_activity_porject_detail
    }

    override fun initView() {
//        setToolBarColor(R.color.colorAccent)
        intent?.getStringExtra(ArouterParamConstant.ORDER_ID)?.let { orderId = it }
        intent?.getStringExtra(ArouterParamConstant.SUBJECT_NAME)?.let { subjectName = it }
        setTitle(subjectName)

        //三个fragment数据
        investRecordFragment = InvestRecordFragment()
        introductionFragment = ProductIntroductionFragment()
        informationFragment = RelateInformationFragment()
        mFragments = listOf(introductionFragment,
                informationFragment,
                investRecordFragment)

        //设置三个table页面
        mViewPagerAdapter = ProjectDetailPagerAdapter(mFragments, supportFragmentManager)
        project_detail_viewpager.adapter = mViewPagerAdapter
        project_detail_viewpager.offscreenPageLimit = mIndicatorTitles.size - 1

        //初始化indicator
        val commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = ProjectDetailIndicatorAdapter(mIndicatorTitles, project_detail_viewpager, this)
        project_detail_indicator.navigator = commonNavigator
        ViewPagerHelper.bind(project_detail_indicator, project_detail_viewpager)

        bt_right_invest.setOnClickListener(this)
        iv_pay_type_question.setOnClickListener(this)
        tv_pay_type_question.setOnClickListener(this)

    }

    override fun initData() {
        mPresenter.getProjectDetail(orderId)
    }

    override fun retryGetData() {
        mStateManager.showContent()
        changeEmptyView(true)
        mPresenter.getProjectDetail(orderId)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_right_invest -> {
                if (UUApplication.user == null) {
//            DialogFactory.instance.getCommonDialog("提示", "请先登录", "确定", "取消", false,
//                    View.OnClickListener {
//                        LoginActivity.launch()
//                    }, View.OnClickListener {
//                finish()
//            }).show(supportFragmentManager, "login")
                    LoginActivity.launch()
                    return
                }
                if (!UUApplication.user?.isIsIdentityUserInfo()!!) {
                    ARouter.getInstance().build(ArouterConstant.UUBASE_IDENTIFICATION).withBoolean(ArouterParamConstant.EXIT_IS_FINISH, true).navigation()
                    return
                }
                MobclickAgent.onEvent(this, "sy_cpxq_ljtz_btn")
                ARouter.getInstance().build(ArouterConstant.ARRANGE_INVEST)
                        .withParcelable(ArouterParamConstant.PROJECT_DETAILS, mProjectDetail.financingOrder)
                        .withString(ArouterParamConstant.SUBJECT_NAME, mProjectDetail.secondHandCarInfo.subjectName)
                        .navigation()
            }
            //还款方式
            R.id.iv_pay_type_question,R.id.tv_pay_type_question ->{
                MobclickAgent.onEvent(this, "tz_xmxq_hkfs")
                DialogFactory.instance.getPaytypeQestionDialog().show(supportFragmentManager,"pay_type_dialog")
            }
        }
    }

    override fun getProjectDetailSuccess(data: ProjectDetailBean) {
        changeEmptyView(false)
        mStateManager.showContent()
        if (data.financingOrder == null) {
            return
        }
        mProjectDetail = data
        val financingOrder = data.financingOrder

        //年化收益率
        //拼接后面的浮动收益率
        var baseRate = NumberFormatUtils.getFormatNumber(NumberUtils.mul(financingOrder.baseRate, 100.00), 1)
        var extraRate = ""
        when {
            financingOrder.subjectType == 1 -> {
                //普通标 U系列加息放在活动加息里面
                if (financingOrder.activityRate != 0.0) {
                    extraRate = NumberFormatUtils.getFormatNumber(NumberUtils.mul(financingOrder.activityRate, 100.00), 1)
                }
            }
            financingOrder.subjectType == 2 -> {
                //活动标
                if (financingOrder.activityRate != 0.0) {
                    extraRate = NumberFormatUtils.getFormatNumber(NumberUtils.mul(financingOrder.activityRate, 100.00), 1)
                }
            }
            financingOrder.subjectType == 3 -> {
                //新手加息
                if (financingOrder.noviceRate != 0.0) {
                    extraRate = NumberFormatUtils.getFormatNumber(NumberUtils.mul(financingOrder.noviceRate, 100.00), 1)
                }
            }
        }

        //预期年化收益，有附加利息才显示加号后面内容
        tv_earnings_rate.text = if (StringUtil.isEmpty(extraRate)) {
            SpannableStringUtils
                    .getBuilder(baseRate)
                    .setProportion(1.2f)
                    .append("%")
                    .setProportion(0.8f)
                    .create()!!
        } else {
            SpannableStringUtils
                    .getBuilder(baseRate)
                    .setProportion(1.2f)
                    .append("%+$extraRate%")
                    .setProportion(0.8f)
                    .create()
        }

        //借款总额
        tv_value_total_amount_of_borrowing.text = ("项目总额（元）：" + NumberFormatUtils.getFormatNumberWithNoDigital(financingOrder.totalMoney))

        //投资总人数
        tv_total_invest_people.text = ("已投人数（位）：" + financingOrder.totalInvester)

        //最高可投标签是否显示
        cfv_limit.visibility = if (financingOrder.subjectType == 3) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }

        //发标时间(用startTime字段)
        val startBidTime = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(financingOrder.startTime))
        tv_value_on_the_time.text = startBidTime

        //借款期限
        tv_project_deadline.text = financingOrder.investPeriod.toString()

        //剩余可投
        tv_remaining_can_be_cast.text = NumberFormatUtils.getFormatNumberWithNoDigital(NumberUtils.sub(financingOrder.totalMoney, financingOrder.currentMoney))

        //设置进度条
        progressbar.max = NumberFormatUtils.getFormatNumberWithNoDigital(financingOrder.totalMoney).toInt()
//        val progressValue = Integer.parseInt(NumberFormatUtils.getNumberWithDigital(NumberUtils.mul(NumberUtils.div(financingOrder.currentMoney, financingOrder.totalMoney), 100.00),1))
        val toInt = NumberFormatUtils.getFormatNumberWithNoDigital(financingOrder.currentMoney).toInt()
        progressbar.setTragetProgress(toInt)

        //设置投资按钮样式以及和否点击状态
        val orderStatus = data.financingOrder.orderStatus
        if (orderStatus==2){

        }
        if (orderStatus == 5) {
            bt_right_invest.setBackgroundResource(R.mipmap.top_bg)
            bt_right_invest.isClickable = true
            bt_right_invest.setText(R.string.invest_now)
        } else if (orderStatus == 6) {
            bt_right_invest.setBackgroundColor(resources.getColor(R.color.color_a9a9a9))
            bt_right_invest.isClickable = false
            bt_right_invest.setText(R.string.bid_full)
        } else if (orderStatus == 7) {
            bt_right_invest.setBackgroundColor(resources.getColor(R.color.color_a9a9a9))
            bt_right_invest.isClickable = false
            bt_right_invest.setText(R.string.paymenting)
        } else if (orderStatus == 9 || data.financingOrder.orderStatus == 11) {
            bt_right_invest.setBackgroundColor(resources.getColor(R.color.color_a9a9a9))
            bt_right_invest.isClickable = false
            bt_right_invest.setText(R.string.has_bean_payment)
        }

        //当标的可投的时候只有登录能看到，其他状态的时候，只有投资且登录才能看。
        if (UUApplication.user != null) {
            if (orderStatus == 5) {
                informationFragment.setCurrentUserIsInvest(RelateInformationFragment.ProjectDetail.CONTENT)
            } else {
                informationFragment.setCurrentUserIsInvest(RelateInformationFragment.ProjectDetail.NOT_INVEST)
            }
        } else {
            informationFragment.setCurrentUserIsInvest(RelateInformationFragment.ProjectDetail.NOT_LOGIN)
        }

        //计算起息日期
        tv_start_interest.text = data.interestMethod

        //设置还本付息还是分期还款
        if(financingOrder.payType == 1){
            //一次性还本付息
            tv_pay_type.text = "一次性还本付息"
            val layoutParams = ll_text_container.layoutParams as RelativeLayout.LayoutParams
            layoutParams.rightMargin = SizeUtils.dp2px(this,12f)
            ll_text_container.layoutParams = layoutParams
        }else if(financingOrder.payType == 2){
            //分期还款
            tv_pay_type.text = "先息后本"
            val layoutParams = ll_text_container.layoutParams as RelativeLayout.LayoutParams
            layoutParams.rightMargin = SizeUtils.dp2px(this,28f)
            ll_text_container.layoutParams = layoutParams
        }

        //设置类产品介绍和相关资料tab页数据
        val secondHandCarInfo = data.secondHandCarInfo
        loanType = secondHandCarInfo.loanType
        introductionFragment.setData(mProjectDetail.financingOrder?.orderId!!, loanType, secondHandCarInfo.projectNote, secondHandCarInfo.reskConrol, secondHandCarInfo.loanUse, secondHandCarInfo.repaymentSource)
        informationFragment.setData(loanType, secondHandCarInfo.carImage)
        investRecordFragment.setOrderData(mProjectDetail.financingOrder?.orderId!!, mProjectDetail.financingOrder?.orderStatus!!)
    }

    override fun checkInvestedSuccess(data: CheckInvestedBean) {
        if (data.isHasInvested) {
            informationFragment.setCurrentUserIsInvest(RelateInformationFragment.ProjectDetail.CONTENT)
        }
    }

    override fun getProjectDetailLimitSuccess(data: ProjectDetailLimit) {
        cfv_limit.setText("最高可投${NumberFormatUtils.formatNumberWithTenThousand(data.totalMoney)}")
    }

    /**
     * 控制显示低保真空界面
     */
    private fun changeEmptyView(isSHowEmptyView: Boolean) {
        project_detail_content.visibility = if (!isSHowEmptyView) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
        project_detail_empty_view.visibility = if (isSHowEmptyView) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    @Subscriber
    private fun onReceiveUserLogin(event: BaseEvent<Any>) {
        if (event.eventCode == EventCode.LOGIN) {
            mPresenter.getProjectDetail(orderId)
        }
    }
}
