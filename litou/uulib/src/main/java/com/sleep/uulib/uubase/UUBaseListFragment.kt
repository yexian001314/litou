package com.sleep.uulib.uubase

import android.support.annotation.LayoutRes
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseItemDraggableAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.sleep.uulib.R
import com.sleep.uulib.mvp.view.LoadListDataView
import com.sleep.uulib.view.MyLoadingMoreView
import java.util.*

/**
 * Created by SleepYM09 on 2017/11/9.
 *
 * blog: www.sleepym09.com
 */
abstract class UUBaseListFragment<T, K> : UUBaseFragment(), LoadListDataView<K>, BaseQuickAdapter.RequestLoadMoreListener {

    private lateinit var mListType: LayoutManagerType
    private var mIsVertical = true
    private var mSpanCount = 1
    protected lateinit var mSmartLayout: SmartRefreshLayout

    protected var mRecyclerView: RecyclerView? = null
    protected lateinit var mLayoutManager: RecyclerView.LayoutManager
    protected var mAdapter: BaseQuickAdapter<T, BaseViewHolder>? = null
    private var layoutResId = -1
    protected var mAdapterType = 0
    private var isAutoRefresh = true //是否自动刷新  默认是true

    override fun initView(mView: View) {
        initItemLayout()//
        if (-1 == this.layoutResId) {
            throw RuntimeException("layoutResId is null!")
        } else {
            if(!customAdapter()){
                if (mAdapterType == 0) {
                    this.mAdapter = ListAdapter(this.layoutResId, ArrayList())
                } else if (mAdapterType == 1) {
                    this.mAdapter = swipeAdapter(this.layoutResId, ArrayList())
                }
            }
        }

        this.chooseListType(this.mListType, this.mIsVertical)

        this.mRecyclerView!!.adapter = this.mAdapter
        mAdapter?.setLoadMoreView(MyLoadingMoreView(context))
        mAdapter?.emptyView = layoutInflater.inflate(R.layout.uulib_list_empty_view,null)
        mAdapter?.setOnLoadMoreListener(this, mRecyclerView)
        mAdapter?.disableLoadMoreIfNotFullPage(mRecyclerView)
    }

    override fun onFirstUserVisible() {
        //只在可以刷新的时候自动刷新
        if (mSmartLayout.isEnableRefresh) {
            mSmartLayout.autoRefresh(0)
        }
    }

    override fun initData() {
        mSmartLayout.setOnRefreshListener {
            onListRefresh()
        }
    }

    protected fun setListType(type: LayoutManagerType, isVertical: Boolean) {
        this.mListType = type
        this.mIsVertical = isVertical
    }

    protected fun setSpanCount(spanCount: Int) {
        if (spanCount > 0) {
            this.mSpanCount = spanCount
        }
    }

    /**
     * 设置item
     */
    open fun setLayoutResId(@LayoutRes layoutResId: Int) {
        this.layoutResId = layoutResId
    }

    /**
     * 设置是否自动刷新
     */
    open fun setAutoRefresh(isAuto: Boolean = true) {
        this.isAutoRefresh = isAuto
    }

    /**
     * 是否自定义adapter
     */
    open fun customAdapter(): Boolean{
        return false
    }

    /**
     * 是否开启上啦加载  和下拉刷新
     */
    protected fun openLoadMoreOrRefresh(loadMore: Boolean = true, refresh: Boolean = true) {
        this.mSmartLayout.isEnableRefresh = refresh //是否开启下拉刷新
        this.mSmartLayout.isEnableLoadmore = false //是否开启refreshlayout的上啦加载 因为有adapter 所以默认关闭
        this.mAdapter?.setEnableLoadMore(loadMore)
    }

    /**
     * 初始化item布局的方法     */
    protected abstract fun initItemLayout()


    private fun chooseListType(listType: LayoutManagerType, isVertical: Boolean) {
        when (listType) {
            LayoutManagerType.LINEAR_LAYOUT_MANAGER -> {
                val linearLayoutManager = LinearLayoutManager(context)
                linearLayoutManager.orientation = if (isVertical) 1 else 0
                this.mRecyclerView!!.layoutManager = linearLayoutManager
                this.mLayoutManager = linearLayoutManager
            }
            LayoutManagerType.GRID_LAYOUT_MANAGER -> {
                val gridLayoutManager = GridLayoutManager(context, this.mSpanCount)
                gridLayoutManager.orientation = if (isVertical) 1 else 0
                this.mRecyclerView!!.layoutManager = gridLayoutManager
                this.mLayoutManager = gridLayoutManager
            }
            LayoutManagerType.STAGGERED_GRID_LAYOUT_MANAGER -> {
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(this.mSpanCount, if (isVertical) 1 else 0)
                this.mRecyclerView!!.layoutManager = staggeredGridLayoutManager
                this.mLayoutManager = staggeredGridLayoutManager
            }
        }
    }

    protected abstract fun onListRefresh()

    protected abstract fun onListLoadMore()

    override fun loadData(data: K) {
        mSmartLayout.finishRefresh(50,true)//刷新完成并且 刷新成功
    }

    override fun loadMoreData(data: K) {
        mAdapter?.loadMoreComplete()
    }

    override fun loadDataAndNoMore(data: K) {
        loadData(data)
        mAdapter?.loadMoreEnd()
    }

    override fun loadNoData() {
        mSmartLayout.finishRefresh(50,true)
        mAdapter?.setNewData(null)
    }

    override fun loadNoMoreData(data: K) {
        mAdapter?.loadMoreComplete()
        mAdapter?.loadMoreEnd()
    }

    override fun loadDataFailure(e: Throwable?, errorCode: Int) {
        mSmartLayout.finishRefresh(50,false)//刷新失败
        mAdapter?.loadMoreEnd()
        if (getCurrentRequestPage() > 1) {
            mAdapter?.loadMoreFail() //当前自动加载更多失败
        }
    }

    /**
     * 子类返回一个记录当前请求页面的常量
     */
    abstract fun getCurrentRequestPage(): Int

    override fun onLoadMoreRequested() {
        onListLoadMore()
    }

    inner class ListAdapter(@LayoutRes layoutResId: Int, data: List<T>?) : BaseQuickAdapter<T, BaseViewHolder>(layoutResId, data) {

        override fun convert(helper: BaseViewHolder, item: T) {
            this@UUBaseListFragment.commonHolder(helper, item)
        }
    }

    inner class swipeAdapter(@LayoutRes layoutResId: Int, data: List<T>?) : BaseItemDraggableAdapter<T, BaseViewHolder>(layoutResId, data) {

        override fun convert(helper: BaseViewHolder, item: T) {
            this@UUBaseListFragment.commonHolder(helper, item)
        }
    }

    /**
     * 设置数据
     */
    protected abstract fun commonHolder(viewHolder: BaseViewHolder, data: T)

}
