package com.sleep.commonlib.base

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.alibaba.android.arouter.launcher.ARouter
import org.simple.eventbus.EventBus

/**
 * Created by LLL on 2017/10/16.
 */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * activity可见性判断
     */
    protected var isResume = false

    private var mNeedHandleEditSelf = false

    override fun onCreate(savedInstanceState: Bundle?) {
        beforeSetContent()
        ARouter.getInstance().inject(this)//注入arouter 可以自动赋值
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        isResume = true
    }

    override fun onPause() {
        super.onPause()
        isResume = false
    }

    /**
     * 获取Layout Id
     */
    abstract fun getLayoutResourse(): Int

    abstract fun initView()

    abstract fun initData()

    open fun beforeSetContent() {}

    /**
     * 点击空白处关闭软键盘逻辑
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v!!.windowToken)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    fun setNeedHandleEditSelf(needHandleEditSelf: Boolean) {
        mNeedHandleEditSelf = needHandleEditSelf
    }


    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏

     * @param v
     * *
     * @param event
     * *
     * @return
     */
    private fun isShouldHideKeyboard(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val l = intArrayOf(0, 0)
            v.getLocationInWindow(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + v.height
            val right = left + v.width
            if (event.x > left && event.x < right
                    && event.y > top && event.y < bottom) {
                // 点击EditText的事件，忽略它。
                return false
            } else {
                if (mNeedHandleEditSelf) {
                    return false
                }
                return true
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private fun hideKeyboard(token: IBinder?) {
        if (token != null) {
            val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}