package com.example.twitterlib.wigdet.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.*
import com.sleep.uulib.R
import com.zhy.autolayout.utils.AutoUtils


/**
 * Created by Sleep on 2017/7/18.
 */
class CommonFragmentDialog : DialogFragment() {
    private var layoutResource = 0  //自定义资源
    private var cancelEnable: Boolean = false   //是否可取消
    private var mLogicSetter: ILogicSetter? = null
    private var animStyleResource = 0
    private var gravity = Gravity.CENTER
    private var isMatchParent = false //是否铺满屏幕

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = inflater.inflate(if (layoutResource == 0) R.layout.view_common_dialog else layoutResource, container)
        AutoUtils.auto(view)
        val window = dialog.window
        window.setBackgroundDrawableResource(android.R.color.transparent)
        if (isMatchParent) window.decorView.setPadding(0, 0, 0, 0) //设置铺满屏幕
        val wlp = window.attributes
        wlp.gravity = gravity
        if (isMatchParent) wlp.width = WindowManager.LayoutParams.MATCH_PARENT
        window.attributes = wlp
        return view
    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        isCancelable = cancelEnable
        this@CommonFragmentDialog.dialog.window.setWindowAnimations(if (animStyleResource == 0) R.style.dialog_common_anim else animStyleResource)
        if (mLogicSetter != null) {
            mLogicSetter!!.setLogic(this, view)
        }
    }

    override fun show(manager: FragmentManager, tag: String) {
        val fragmentTransaction = manager.beginTransaction()
        fragmentTransaction.add(this, tag)
        fragmentTransaction.commitAllowingStateLoss()
    }

    class DialogBuilder {

        private var layoutResource = 0  //自定义资源
        private var cancelEnable: Boolean = false   //是否可取消
        private var mLogicSetter: ILogicSetter? = null  //自定义逻辑接口
        private var animStyleResource = 0   //自定义入场出场动画Style
        private var gravity: Int = 0    //dialog出现位子
        private var isMatchParent = false //是否填充屏幕

        fun setLayoutResouce(layoutResource: Int): DialogBuilder {
            this.layoutResource = layoutResource
            return this
        }

        fun setCancelEnable(cancelEnable: Boolean): DialogBuilder {
            this.cancelEnable = cancelEnable
            return this
        }

        fun setLogicSetter(mLogicSetter: ILogicSetter): DialogBuilder {
            this.mLogicSetter = mLogicSetter
            return this
        }

        fun setAnimStyleResource(animStyleResource: Int): DialogBuilder {
            this.animStyleResource = animStyleResource
            return this
        }

        fun setGravity(gravity: Int): DialogBuilder {
            this.gravity = gravity
            return this
        }

        fun setIsMatchParent(isMatchParent: Boolean): DialogBuilder {
            this.isMatchParent = isMatchParent
            return this
        }

        private fun applySetting(fragmentDialog: CommonFragmentDialog) {
            fragmentDialog.cancelEnable = this.cancelEnable
            fragmentDialog.mLogicSetter = this.mLogicSetter
            fragmentDialog.animStyleResource = this.animStyleResource
            fragmentDialog.gravity = this.gravity
            fragmentDialog.isMatchParent = this.isMatchParent
            fragmentDialog.layoutResource = this.layoutResource
        }

        fun build(): CommonFragmentDialog {
            val commonDialog = CommonFragmentDialog()
            applySetting(commonDialog)
            return commonDialog
        }
    }

    interface ILogicSetter {
        open fun setLogic(fragmentDialog: CommonFragmentDialog, rootView: View)
    }
}