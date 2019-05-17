package com.sleep.uulib.mvp.view

/**
 * Created by SleepYM09 on 2017/11/9.
 *
 * blog: www.sleepym09.com
 */
interface LoadListDataView<T> {
    /**
     * 第一页加载到数据加载数据
     * @param data 数据
     */
    fun loadData(data: T)

    /**
     * 第一页加载到数据，并且没有更多数据
     */
    fun loadDataAndNoMore(data: T)

    /**
     * 加载更多
     * @param data 数据
     */
    fun loadMoreData(data: T)

    /**
     * 没有数据
     */
    fun loadNoData()

    /**
     * 没有更多数据
     */
    fun loadNoMoreData(data: T)

    /**
     * 加载失败
     */
    fun loadDataFailure(e: Throwable?, errorCode: Int)
}