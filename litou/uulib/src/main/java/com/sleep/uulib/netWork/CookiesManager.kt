package com.sleep.uulib.netWork

import com.sleep.commonlib.BaseApplication
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * Created by SleepYM09 on 2017/11/3.
 *
 * blog: www.sleepym09.com
 */
class CookiesManager : CookieJar {
    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>?) {
        if (cookies != null && cookies.size > 0) {
            cookies.forEach { item ->
                run {
                    cookieStore.add(url, item)
                }
            }
        }
    }

    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
        return cookieStore.get(url)
    }

    val cookieStore: PersistentCookieStore get() = PersistentCookieStore(BaseApplication.sInstance.applicationContext)
}