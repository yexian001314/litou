package com.sleep.home.fragment


import android.support.v4.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import cn.bingoogolapple.bgabanner.BGABanner
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.sleep.commonlib.util.LibCommonUtil
import com.sleep.commonlib.util.SpannableStringUtils
import com.sleep.commonlib.util.StringUtil
import com.sleep.home.R
import com.sleep.home.adapter.HomeBannerAdapter
import com.sleep.home.mvp.view.HomeView
import com.sleep.uulib.bean.HomeAnnocementsBean
import com.sleep.uulib.bean.HomeBannerBean
import com.sleep.uulib.bean.HomeFinanceListBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.ArouterParamConstant
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.presenter.HomePresenter
import com.sleep.uulib.util.BidUtil
import com.sleep.uulib.util.NumberFormatUtils
import com.sleep.uulib.util.NumberUtils
import com.sleep.uulib.uubase.HtmlUrlActivity
import com.sleep.uulib.uubase.UUBaseFragment
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.home_fragment_home.*


/**
 * A simple [Fragment] subclass.
 */
@Route(path = ArouterConstant.APP_HOME_FRAGMENT)
class HomeFragment : UUBaseFragment(), BGABanner.Delegate<ImageView, HomeBannerBean.BannerListBean>, HomeView, View.OnClickListener, OnRefreshListener {

    private lateinit var mBannerAdapter: HomeBannerAdapter

    private lateinit var mNewBidPorject: HomeFinanceListBean.ProjectsBean
    private lateinit var mActivityProject: HomeFinanceListBean.ProjectsBean
    private lateinit var mUProject: HomeFinanceListBean.ProjectsBean

    override fun getLayoutResourse(): Int {
        return R.layout.home_fragment_home
    }

    private lateinit var mPresenter: HomePresenter

    override fun onFirstUserVisible() {
        mStateManager.showLoading()
        getData()
    }

    override fun initView(view: View) {
        //初始化banner
        mBannerAdapter = HomeBannerAdapter(this.activity)
        home_banner.setAdapter(mBannerAdapter)
        home_banner.setDelegate(this)

        //springView初始化
        home_smart_refresh_layout.setOnRefreshListener(this)
        ll_green_hand_guide.setOnClickListener(this)
        ll_security.setOnClickListener(this)

        ll_u_series_zone.setOnClickListener(this)
        include_home_u_series_zone.setOnClickListener(this)
        ll_recommend_courteous.setOnClickListener(this)
        home_activity_zone.setOnClickListener(this)
        home_newbie_zone.setOnClickListener(this)
        tv_notice_more.setOnClickListener(this)
        ll_information_disclosure.setOnClickListener(this)
        ll_newbid_zone.setOnClickListener(this)
        ll_activity_zone.setOnClickListener(this)
    }

    override fun initData() {
        mPresenter = HomePresenter(this, mStateManager)
    }

    override fun retryGetData() {
        mStateManager.showLoading()
        getData()
    }

    override fun onRefresh(refreshlayout: RefreshLayout?) {
        getData()
    }

    private fun getData() {
        //获取首页数据
        mPresenter.getHomeData()
    }

    private fun setBannerData(homeBannerBean: HomeBannerBean) {
        if (homeBannerBean.bannerList != null) {
            home_banner.setData(homeBannerBean.bannerList, listOf())
        }
    }

    override fun onClick(view: View) {
        if (LibCommonUtil.isFastDoubleClick()) {
            return
        }
        when (view.id) {
            R.id.ll_security -> {
                //安全保障
                MobclickAgent.onEvent(context, "sy_aqbz")
                HtmlUrlActivity.launch(NetConstant.HOME_SECURITY)
            }
            R.id.ll_green_hand_guide -> {
                //新手指引
                MobclickAgent.onEvent(context, "sy_xszy")
                HtmlUrlActivity.launch(NetConstant.HOME_GREEN_HAND_GUIDE)
            }
            R.id.ll_recommend_courteous -> {
                //我的邀请
                MobclickAgent.onEvent(context, "hd_wdyq")
                HtmlUrlActivity.launch(NetConstant.H5_MY_INVITE, true)
            }
            R.id.ll_information_disclosure -> {
                //信息披露
                MobclickAgent.onEvent(context, "sy_dbxxpl")
                HtmlUrlActivity.launch(NetConstant.HOME_INFORMATION_SHOW)
            }
//            R.id.tv_security_safeguard -> {
//                //底部安全保障
//                MobclickAgent.onEvent(context, "sy_dbaqbz")
//                HtmlUrlActivity.launch(NetConstant.HOME_SECURITY)
//            }
//            R.id.tv_information_disclosure -> {
//                //底部信息披露
//                MobclickAgent.onEvent(context, "sy_dbxxpl")
//                HtmlUrlActivity.launch(NetConstant.HOME_INFORMATION_SHOW)
//            }
            R.id.ll_newbid_zone -> {
                //新手专区
                MobclickAgent.onEvent(context, "sy_more_btn")
                ARouter.getInstance().build(ArouterConstant.HOME_BID_PREFECTURE).withInt(ArouterParamConstant.BID_TYPE, 3).navigation()
            }
            R.id.ll_activity_zone -> {
                //活动专区
                MobclickAgent.onEvent(context, "sy_more_btn")
                ARouter.getInstance().build(ArouterConstant.HOME_BID_PREFECTURE).withInt(ArouterParamConstant.BID_TYPE, 2).navigation()
            }
            R.id.ll_u_series_zone -> {
                //U系列专区
                MobclickAgent.onEvent(context, "sy_more_btn")
                ARouter.getInstance().build(ArouterConstant.HOME_BID_PREFECTURE).withInt(ArouterParamConstant.BID_TYPE, 1).navigation()
            }
            R.id.home_newbie_zone -> {
                //新手标
                MobclickAgent.onEvent(context, "sy_cplb_btn")
                launchNewBid()
            }
            R.id.home_activity_zone -> {
                //活动标
                MobclickAgent.onEvent(context, "sy_cplb_btn")
                launchActivityBid()
            }
            R.id.include_home_u_series_zone -> {
                //u系列标
                MobclickAgent.onEvent(context, "sy_cplb_btn")
                launchUBid()
            }

            R.id.tv_notice_more -> {
                //更多公告
                ARouter.getInstance().build(ArouterConstant.HOME_NOTICE_LIST).navigation()
            }
        }
    }

    override fun onBannerItemClick(banner: BGABanner?, itemView: ImageView, model: HomeBannerBean.BannerListBean, position: Int) {
        if (!StringUtil.isEmpty(model.jump_url)) {
            HtmlUrlActivity.launch(model.jump_url, true)
            MobclickAgent.onEvent(context, "sy_banner")
        }
    }

    override fun getHomeBannerSuccess(homeBannerBean: HomeBannerBean) {
        setBannerData(homeBannerBean)
    }

    override fun getHOmeAnnocementSuccess(data: HomeAnnocementsBean) {
        val noticeData = data.announcementEoList.map { it.title }
        tv_notice.start(noticeData)
        tv_notice.setOnClickListener({
            HtmlUrlActivity.launch(NetConstant.H5_NOTICE.format(data.announcementEoList[tv_notice.index].id.toString()))
        })
    }

    override fun getHomeFinancingSuccess(data: HomeFinanceListBean) {
        if (data.newProjects != null && data.newProjects.size > 0) {
            //设置新手标
            mNewBidPorject = data.newProjects[0]
            ll_newbid_item.visibility = View.VISIBLE
            setBidZoneSameData(home_newbie_zone, mNewBidPorject)
            //新手标计算总的利率
            val baseRate = NumberFormatUtils.getFormatNumber(NumberUtils.mul(mNewBidPorject.baseRate, 100.00), 1)
            var extraRate = NumberFormatUtils.getFormatNumber(NumberUtils.mul(mNewBidPorject.noviceRate, 100.00), 1)
            BidUtil.setBidRateText(home_newbie_zone.findViewById(R.id.tv_earning_rate), baseRate, extraRate)
            home_newbie_zone.findViewById<View>(R.id.tv_subject_type).visibility = View.VISIBLE
            home_newbie_zone.findViewById<View>(R.id.tv_bid_status_and_btn).setOnClickListener {
                //跳转新手标
                MobclickAgent.onEvent(context, "sy_ljjr_btn")
                launchNewBid()
            }
        }
        if (data.activityProjects != null && data.activityProjects.size > 0) {
            //设置活动标
            mActivityProject = data.activityProjects[0]
            ll_activity_item.visibility = View.VISIBLE
            setBidZoneSameData(home_activity_zone, mActivityProject)
            //基础利率
            val baseRate = NumberFormatUtils.getFormatNumber(NumberUtils.mul(mActivityProject.baseRate, 100.00), 1)
            //拼接后面的浮动收益率
            val extraRate = NumberFormatUtils.getFormatNumber(NumberUtils.mul(mActivityProject.activityRate, 100.00), 1)
            //活动加息
            BidUtil.setBidRateText(home_activity_zone.findViewById(R.id.tv_earning_rate), baseRate, extraRate)
            //加息小标
            if (mActivityProject.activityRate == 0.0) {
                home_activity_zone.findViewById<View>(R.id.tv_subject_type).visibility = View.GONE
            } else {
//                val littleTypeRate = NumberFormatUtils.getFormatNumberWithNoDigital(NumberUtils.mul(mActivityProject.activityRate, 100.00))
                home_activity_zone.findViewById<TextView>(R.id.tv_subject_type).setBackgroundResource(R.mipmap.coupon)
            }
            home_activity_zone.findViewById<View>(R.id.tv_bid_status_and_btn).setOnClickListener {
                //跳转活动手标
                MobclickAgent.onEvent(context, "sy_ljjr_btn")
                launchActivityBid()
            }
        }
        if (data.getuProjects() != null && data.getuProjects().size > 0) {
            mUProject = data.getuProjects()[0]
            ll_u_series_item.visibility = View.VISIBLE
            setBidZoneSameData(include_home_u_series_zone, mUProject)
            //普通标加息
            val baseRate = NumberFormatUtils.getFormatNumber(NumberUtils.mul(mUProject.baseRate, 100.00), 1)
            //拼接后面的浮动收益率
            val extraRate = NumberFormatUtils.getFormatNumber(NumberUtils.mul(mUProject.activityRate, 100.00), 1)
            BidUtil.setBidRateText(include_home_u_series_zone.findViewById(R.id.tv_earning_rate), baseRate, extraRate)
            //加息小标
            if (mUProject.activityRate == 0.0) {
                include_home_u_series_zone.findViewById<View>(R.id.tv_subject_type).visibility = View.GONE
            } else {
                val littleTypeRate = NumberFormatUtils.getFormatNumberWithNoDigital(NumberUtils.mul(mUProject.activityRate, 100.00))
//                include_home_u_series_zone.findViewById<TextView>(R.id.tv_subject_type).text = "加息"
            }
            include_home_u_series_zone.findViewById<View>(R.id.tv_bid_status_and_btn).setOnClickListener {
                //跳转u系列手标
                MobclickAgent.onEvent(context, "sy_ljjr_btn")
                launchUBid()
            }
        }
    }

    /**
     * 设置两个标区相同的数据
     *
     * @param zone
     */
    private fun setBidZoneSameData(zone: View, data: HomeFinanceListBean.ProjectsBean?) {
        if (data == null) {
            return
        }
        zone.findViewById<TextView>(R.id.tv_remain_money).text = SpannableStringUtils.getBuilder("")
                .append(NumberFormatUtils.getFormatNumberWithNoDigital(NumberUtils.sub(data.totalMoney, data.currentMoney)))
                .setForegroundColor(resources.getColor(R.color.colorAccent))
                .create()
        (zone.findViewById<View>(R.id.tv_project_name) as TextView).text = data.subjectName//项目名
        (zone.findViewById<View>(R.id.tv_invest_period) as TextView).text = ("期限" + data.investPeriod + "天")//期限
        //设置标状态
        val status = zone.findViewById<TextView>(R.id.tv_bid_status_and_btn)
        if (data.orderStatus == 5) {
            //募集中
            status.setBackgroundResource(R.mipmap.new_input)
            status.text = getString(R.string.invest_now)
        } else if (data.orderStatus == 6) {
            //募集完成
            status.setBackgroundResource(R.drawable.shape_gray_corner)
            status.text = getString(R.string.bid_full)
        } else if (data.orderStatus == 7) {
            //还款中
            status.setBackgroundResource(R.drawable.shape_gray_corner)
            status.text = getString(R.string.paymenting)
        } else if (data.orderStatus == 9 || data.orderStatus == 11) {
            //已还款
            status.setBackgroundResource(R.drawable.shape_gray_corner)
            status.text = getString(R.string.has_bean_payment)
        }

        //设置还本付息还是分期还款
        if (data.payType == 1) {
            //一次性还本付息
            zone.findViewById<TextView>(R.id.tv_pay_type).text = " | 一次性还本付息"
        } else if (data.payType == 2) {
            //分期还款
            zone.findViewById<TextView>(R.id.tv_pay_type).text = " | 先息后本"
        }
    }

    private fun launchUBid() {
        ARouter.getInstance().build(ArouterConstant.ARRANGE_PROJECT_DETAIL)
                .withString(ArouterParamConstant.ORDER_ID, mUProject.orderId)
                .withString(ArouterParamConstant.SUBJECT_NAME, mUProject.subjectName)
                .navigation()
    }

    private fun launchActivityBid() {
        ARouter.getInstance().build(ArouterConstant.ARRANGE_PROJECT_DETAIL)
                .withString(ArouterParamConstant.ORDER_ID, mActivityProject.orderId)
                .withString(ArouterParamConstant.SUBJECT_NAME, mActivityProject.subjectName)
                .navigation()
    }

    private fun launchNewBid() {
        ARouter.getInstance().build(ArouterConstant.ARRANGE_PROJECT_DETAIL)
                .withString(ArouterParamConstant.ORDER_ID, mNewBidPorject.orderId)
                .withString(ArouterParamConstant.SUBJECT_NAME, mNewBidPorject.subjectName)
                .navigation()
    }

    override fun getDataFinish() {
        mStateManager.showContent()
        home_smart_refresh_layout.finishRefresh()
    }
}
