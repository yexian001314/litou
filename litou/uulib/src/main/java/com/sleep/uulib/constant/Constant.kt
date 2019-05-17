package com.sleep.uulib.constant

/**
 * Created by SleepYM09 on 2017/10/18.
 *
 * blog: www.sleepym09.com
 */
object Constant {
    const val USER_INFO = "userInfo"
    const val PHONE_NUM = "phoneNum"
    const val COOKIE = "cookie"
    const val IS_SHOW_TOTAL_ASSETS = "is_show_total_assets"
    const val PUSH_URL = "push_url"
    const val IS_NEED_OPEN_PUSH_URL = "is_need_open_push_url"
    const val IS_TEST_SERVER = "is_test_server"
    const val SHARE_TITLE = "我为你准备了一份现金大礼"
    const val SHARE_LINK = "https://www.uumoney.cn/mobile-webapp/activity.html?key=%s"
    const val SHARE_DESC = "您的好友邀您来利投网一起赚钱了，赶快加入，躺赚收益"
    const val DEFAULT_MONEY_FORMAT = "###,###,###,##0.00"
    const val PSD_PATTERN = "(?!^\\d+\$)(?!^[a-zA-Z]+\$)(?!^[,./;'\\[\\]<>\\?:\"{}~`_#@!]+$).{6,12}"
    const val STR_DIGIT_LETTER = "^[A-Za-z0-9,./;'\\[\\]<>\\?:\"{}~`_#@!]*\$"
    const val CONFIRM_CORD_LETTER = "^[0-9]{4}\$"
    const val OLD_PSD_PARTTERN = "^.{6,12}\$"
    const val DEFAULT_WELFARE_DATE_FORMAT = "yyyy.MM.dd HH:mm:ss"
    const val EXPEND_LEVEL_0: Int = 0
    const val EXPEND_LEVEL_1: Int = 1
    //是否设置手势密码成功
    const val GES_PSD_HAS: String = "ges_psd_has"
    const val GES_PSD_ERROR_COUNT: String = "ges_psd_error_count"
    const val GES_PSD_LOGIN_PHONE_NUMBER: String = "ges_psd_login_phone_number"
    //手势密码
    const val GES_PASSWORD: String = "ges_password"
    //帮助中心是否已经缓存
    const val HAS_HELP_CENTER_MENU: String = "has_help_center_menu"
}