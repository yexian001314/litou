package com.sleep.commonlib.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.simple.eventbus.EventBus



/**
 * Created by LLL on 2017/10/16.
 */
abstract class BaseFragment : Fragment() {
    protected var mIsPrepared = false
    private var mIsFirstVisible = true
    private var mIsFirstInvisible = true
    private var mIsFirstResume = true
    lateinit var mRootView: View

    /**
     * 当前fragment是否课件
     */
    protected var mIsResume = false

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        EventBus.getDefault().register(this)
        mRootView = getRootView(inflater,container)
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        childInit()
        initPrepare()
    }

    @Synchronized fun initPrepare() {
        if (mIsPrepared) {
            onFirstUserVisible()
            mIsResume = true
        } else {
            mIsPrepared = true
        }
    }

    /**
     * 此方法在FragmentPagerAdapter中手动调用，因此在非viewpager中不要使用该生命周期，否则会出现无法调用的情况
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            if (mIsFirstVisible) {
                mIsFirstVisible = false
                initPrepare()
            } else {
                onUserVisible()
                mIsResume = true
            }
        } else {
            if (mIsFirstInvisible) {
                mIsFirstInvisible = false
                onFirstUserInvisible()
                mIsResume = false
            } else {
                onUserInvisible()
                mIsResume = false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (mIsFirstResume) {
            mIsFirstResume = false
            return
        }
        if (userVisibleHint) {
            onUserVisible()
            mIsResume = true
        }

    }

    override fun onPause() {
        super.onPause()
        if (userVisibleHint) {
            onUserInvisible()
            mIsResume = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    abstract fun initView(view: View)

    abstract fun initData()

    abstract fun childInit()

    /**
     * 第一次对用户可见时会调用该方法
     */
    abstract fun onFirstUserVisible()

    /**
     * 获取根布局文件
     */
    abstract fun getRootView(inflater : LayoutInflater?,container: ViewGroup?):View

    /**
     * 对用户可见时会调用该方法，除了第一次
     */
    open fun onUserVisible() {}

    /**
     * 第一次对用户不可见时会调用该方法
     */
    open fun onFirstUserInvisible() {}

    /**
     * 对用户不可见时会调用该方法，除了第一次
     */
    open fun onUserInvisible() {}
}