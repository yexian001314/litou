package com.sleep.uulib.widget.fragmentdialog

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.example.twitterlib.wigdet.dialog.CommonFragmentDialog
import com.sleep.commonlib.util.ToastUtil
import com.sleep.uulib.R
import com.sleep.uulib.constant.NetConstant
import com.sleep.uulib.util.GlideHelper
import com.sleep.uulib.widget.OkHttpUrlLoader
import java.io.InputStream

/**
 * Created by SleepYM09 on 2017/12/22.
 *
 * blog: www.sleepym09.com
 */
class IdentifyDialogLogicSetter(private val listener: ConfirmClickListener) : CommonFragmentDialog.ILogicSetter {
    override fun setLogic(fragmentDialog: CommonFragmentDialog, rootView: View) {
        val etIdentifyCode = rootView.findViewById<EditText>(R.id.et_identify_code)
        val ivCodePlaceHolder = rootView.findViewById<ImageView>(R.id.iv_code_place_holder)
        loadImage(fragmentDialog, ivCodePlaceHolder)
        rootView.findViewById<View>(R.id.common_dialog_positive).setOnClickListener({
            val confirmCode = etIdentifyCode.text.toString()
            if (confirmCode.length == 4) {
                listener.onConfirmClick(confirmCode)
                fragmentDialog.dismiss()
            } else {
                ToastUtil.showToast("请输入4位校验码")
            }
        })
        rootView.findViewById<View>(R.id.common_dialog_negative).setOnClickListener({
            fragmentDialog.dismiss()
        })

        ivCodePlaceHolder.setOnClickListener({
            loadImage(fragmentDialog, ivCodePlaceHolder)
        })
    }

    /**
     * 加载图片
     */
    private fun loadImage(fragmentDialog: CommonFragmentDialog, ivCodePlaceHolder: ImageView?) {
        val diskCacheStrategy = GlideHelper.getOptions(GlideHelper.ImageType.HOME_BANNER)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
        val glide = Glide.with(fragmentDialog.context)
        glide.clear(ivCodePlaceHolder)
        Glide.get(fragmentDialog.context).registry.replace(GlideUrl::class.java,InputStream::class.java, OkHttpUrlLoader.factory)
        glide.applyDefaultRequestOptions(diskCacheStrategy)
                .load(NetConstant.IDENTIFY_CODE_URL)
                .into(ivCodePlaceHolder)
    }

    interface ConfirmClickListener {
        fun onConfirmClick(identifyStr: String)
    }
}