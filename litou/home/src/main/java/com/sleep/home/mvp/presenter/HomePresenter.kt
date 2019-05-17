package com.sleep.uulib.mvp.presenter

import com.sleep.commonlib.view.stateManager.StatesLayoutManager
import com.sleep.home.mvp.view.HomeView
import com.sleep.uulib.bean.HomeAnnocementsBean
import com.sleep.uulib.bean.HomeBannerBean
import com.sleep.uulib.bean.HomeFinanceListBean
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.mvp.model.HomeModule
import com.sleep.uulib.netWork.NetCommonMethod
import com.sleep.uulib.netWork.OnLoadDataListener
import java.util.*

/**
 * Created by SleepYM09 on 2017/10/25.
 *
 * blog: www.sleepym09.com
 */
class HomePresenter(val homeView: HomeView, val statesLayoutManager: StatesLayoutManager) {
    private val mModule: HomeModule
        get() {
            return HomeModule()
        }

    /**
     * 获取首页banner数据
     */
    fun getHomeData() {
        val treeMap = TreeMap<String, Any>()
        treeMap.put(NetConstant.STATES, 1)
        treeMap.put(NetConstant.PLATFORM, 3)
        mModule.getHomeBanner(treeMap, object : OnLoadDataListener<HomeBannerBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: HomeBannerBean) {
                homeView.getHomeBannerSuccess(data)
                getFinanceList()
            }
        })
    }

    /**
     * 获取首页两种活动数据（从列表中分别筛选出新手标和活动标）
     */
    fun getFinanceList() {
        mModule.getFinanceList(object : OnLoadDataListener<HomeFinanceListBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: HomeFinanceListBean) {
                homeView.getHomeFinancingSuccess(data)
                getAnnocements()
            }
        })
    }

    /**
     * 获取首页公告数据
     */
    fun getAnnocements() {
        val treeMap = TreeMap<String, Any>()
        treeMap.put(NetConstant.STATRT, 0)
        treeMap.put(NetConstant.LIMIT, 3)
        treeMap.put(NetConstant.ANNOUNCEMENT_TYPE, 1)
        mModule.getAnnocements(treeMap, object : OnLoadDataListener<HomeAnnocementsBean> {
            override fun onFailure(e: Throwable?, errorCode: Int) {
                NetCommonMethod.judgeError(errorCode, statesLayoutManager)
            }

            override fun onSuccess(data: HomeAnnocementsBean) {
                homeView.getHOmeAnnocementSuccess(data)
                homeView.getDataFinish()
            }
        })
    }
}