package com.sleep.uulib.util

import com.bumptech.glide.request.RequestOptions
import com.sleep.uulib.R
import com.sleep.uulib.widget.CircleTransform

/**
 * Created by SleepYM09 on 2017/10/24.
 *
 * blog: www.sleepym09.com
 */
object GlideHelper {
    fun getOptions(imageType: ImageType): RequestOptions {
        val options = RequestOptions()
        //TODO 待设定初始化
        when (imageType) {
            ImageType.HEAD -> options.placeholder(R.mipmap.user_default)
            ImageType.CIRCLE -> {
                options.transform(CircleTransform())
                options.placeholder(R.mipmap.app_launcher)
            }
            ImageType.HOME_BANNER -> options.placeholder(R.mipmap.home_banner_placeholder)

            ImageType.DETAIL_BANNER -> options.placeholder(R.mipmap.ph_project_detail_about)
            ImageType.DETAILS_INFO -> options.placeholder(R.mipmap.ph_project_detail_about)
            ImageType.SPLASH -> options.placeholder(R.mipmap.bg_splash)

        }
        return options
    }

    enum class ImageType {
        HEAD,
        HOME_BANNER,
        DETAIL_BANNER,
        CIRCLE,
        NON,
        DETAILS_INFO,
        SPLASH
    }
}