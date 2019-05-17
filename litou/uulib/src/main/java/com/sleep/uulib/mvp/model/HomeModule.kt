package com.sleep.uulib.mvp.model

import com.sleep.uulib.bean.HomeAnnocementsBean
import com.sleep.uulib.bean.HomeBannerBean
import com.sleep.uulib.bean.HomeFinanceListBean
import com.sleep.uulib.netWork.AppRequest
import com.sleep.uulib.netWork.BaseObserver
import com.sleep.uulib.netWork.OnLoadDataListener
import java.util.*

/**
 * Created by SleepYM09 on 2017/10/25.
 *
 * blog: www.sleepym09.com
 */
class HomeModule {
    /**
     * 获取首页banner数据
     */
    fun getHomeBanner(mTreeMap: TreeMap<String, Any>,
                      onLoadDataListener: OnLoadDataListener<HomeBannerBean>
    ) {
        AppRequest.INSTANCE.getHomeBannerData(mTreeMap, BaseObserver(onLoadDataListener))

    }

    /**
     * 获取首页两条数据
     */
    fun getFinanceList(onLoadDataListener: OnLoadDataListener<HomeFinanceListBean>){
        val tree = TreeMap<String, Any>()
        AppRequest.INSTANCE.getHomeFinanceListData(tree,BaseObserver(onLoadDataListener))
    }

    /**
     * 获取首页公告数据
     */
    fun getAnnocements(tree: TreeMap<String, Any>,onLoadDataListener: OnLoadDataListener<HomeAnnocementsBean>){
        AppRequest.INSTANCE.getAnnocementsData(tree,BaseObserver(onLoadDataListener))
    }
}