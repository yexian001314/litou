package com.sleep.home.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sleep.commonlib.util.TimeUtils
import com.sleep.home.R
import com.sleep.home.mvp.presenter.NoticeListPresenter
import com.sleep.uulib.bean.HomeAnnocementsBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.uubase.HtmlUrlActivity
import com.sleep.uulib.uubase.LayoutManagerType
import com.sleep.uulib.uubase.UUBaseListActivity
import kotlinx.android.synthetic.main.activity_notice_list.*

@Route(path = ArouterConstant.HOME_NOTICE_LIST)
class NoticeListActivity : UUBaseListActivity<HomeAnnocementsBean.AnnouncementEoListBean,HomeAnnocementsBean>() {

    private var REQUEST_PAGE = 0

    private lateinit var mPresenter: NoticeListPresenter

    override fun getLayoutResourse(): Int {
        return R.layout.activity_notice_list
    }

    override fun initItemLayout() {
        setTitle("公告列表")
        super.mRecyclerView = notice_list_recycler
        super.mSmartLayout = notice_list_refresh
        setListType(LayoutManagerType.LINEAR_LAYOUT_MANAGER, true)
        setLayoutResId(R.layout.item_notice_layout)
    }

    override fun initData() {
        super.initData()
        mPresenter = NoticeListPresenter(this, mStateManager)
        mAdapter?.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            HtmlUrlActivity.launch(NetConstant.H5_NOTICE.format(mAdapter?.data!![position].id.toString()))
//            HtmlContentActivity.launch(mAdapter?.data!![position].content,"详情内容")
        }
    }

    override fun retryGetData() {
        mStateManager.showLoading()
        getData()
    }

    override fun onListRefresh() {
        REQUEST_PAGE = 0
        getData()
    }

    override fun onListLoadMore() {
        REQUEST_PAGE++
        getData()
    }

    override fun loadDataAndNoMore(data: HomeAnnocementsBean) {
        mAdapter?.setNewData(data.announcementEoList)
        super.loadDataAndNoMore(data)
    }

    override fun getCurrentRequestPage(): Int {
        return REQUEST_PAGE
    }

    override fun commonHolder(viewHolder: BaseViewHolder, item: HomeAnnocementsBean.AnnouncementEoListBean) {
        viewHolder.setText(R.id.tv_notice_title,item.title)
        viewHolder.setText(R.id.tv_creat_time,TimeUtils.milliseconds2String(item.createTime,"yyyy-MM-dd"))
    }

    private fun getData() {
        mPresenter.getAnnocements()
    }
}
