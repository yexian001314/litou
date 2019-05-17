package com.shqj.information

import android.text.TextUtils
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sleep.commonlib.util.StringUtil
import com.sleep.uulib.bean.HomeBannerBean
import com.sleep.uulib.constant.ArouterConstant
import com.sleep.uulib.util.GlideHelper
import com.sleep.uulib.uubase.HtmlUrlActivity
import com.sleep.uulib.uubase.LayoutManagerType
import com.sleep.uulib.uubase.UUBaseListActivity
import com.shqj.information.mvp.presenter.WonderfulPresenter
import kotlinx.android.synthetic.main.information_activity_wonderful.*

@Route(path = ArouterConstant.INFORMATION_WONDERFUL)
class WonderfulActivity : UUBaseListActivity<HomeBannerBean.BannerListBean, HomeBannerBean>(), BaseQuickAdapter.OnItemClickListener {

    private var REQUEST_PAGE = 0
    private lateinit var mPresenter: WonderfulPresenter

    override fun getLayoutResourse(): Int {
        return R.layout.information_activity_wonderful
    }

    override fun initItemLayout() {
        setTitle(getString(R.string.title_winderful))
        super.mRecyclerView = wonderful_recycler
        super.mSmartLayout = wonderful_refresh
        setListType(LayoutManagerType.LINEAR_LAYOUT_MANAGER, true)
        setLayoutResId(R.layout.item_wonderful)
    }

    override fun initData() {
        super.initData()
        mPresenter = WonderfulPresenter(this,mStateManager)
        mAdapter?.onItemClickListener = this
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

    override fun loadData(data: HomeBannerBean) {
        super.loadData(data)
        mAdapter?.setNewData(data.bannerList)
    }

    override fun loadMoreData(data: HomeBannerBean) {
        super.loadMoreData(data)
        mAdapter?.addData(data.bannerList)
    }

    override fun loadNoMoreData(data: HomeBannerBean) {
        super.loadNoMoreData(data)
        mAdapter?.addData(data.bannerList)
    }

    override fun getCurrentRequestPage(): Int {
        return REQUEST_PAGE
    }

    override fun commonHolder(viewHolder: BaseViewHolder, data: HomeBannerBean.BannerListBean) {
        Glide.with(this)
                .applyDefaultRequestOptions(GlideHelper.getOptions(GlideHelper.ImageType.HOME_BANNER))
                .load(data.pic_url)
                .into(viewHolder.getView(R.id.img))
        if (!TextUtils.isEmpty(data.banner_name)) {
            viewHolder.setText(R.id.tv_title, data.banner_name)
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        if(!StringUtil.isEmpty(mAdapter?.data?.get(position)?.jump_url)){
            HtmlUrlActivity.launch(mAdapter?.data?.get(position)?.jump_url!!,true)
        }
    }

    private fun getData(){
        mPresenter.getWonderfulData()
    }
}
