package com.sleep.uulib.netWork

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.sleep.commonlib.BaseApplication
import com.sleep.commonlib.BuildConfig
import com.sleep.commonlib.util.HttpsUtils
import com.sleep.uulib.netWork.multi_base_url.MultiBaseUrlInterceptor
import okhttp3.OkHttpClient
import java.io.IOException
import java.io.InputStream
import java.util.concurrent.TimeUnit


/**
 * 作者：alsoWell on 2017/7/24 09:20
 * 邮箱：1161882463@qq.com
 */
class OkHttpUtils {
    /**
     * 获取client
     * kotlin中的静态方法是使用
     *  companion object 修饰
     *  如果需要单元测试 那么需要在方法前面加上 jvmstatic
     */

    companion object {
        @JvmStatic
        fun getOkHttpClient(): OkHttpClient {
            //信任所有https
            var open: InputStream? = null
            try {
                //Https证书
//                open = BaseApplication.sInstance.assets.open("214300956310365.pem")
                open = BaseApplication.sInstance.assets.open("certificate.der")
                NetConfig.addCertificate(open)
            } catch (e: IOException) {
                e.printStackTrace()
            }

//            val certificates = mutableListOf<InputStream>()
//            val certificatesData = NetConfig.getCertificatesData()
//            if (certificatesData!=null&&!certificatesData.isEmpty()){
//                for (bytes in certificatesData) {
//                    certificates.add(ByteArrayInputStream(bytes))
//                }
//            }
            val sslParams = HttpsUtils.getSslSocketFactory(arrayOf(open), null, null)
            var mBuilder: OkHttpClient.Builder = OkHttpClient().newBuilder()
            mBuilder.connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                    .addNetworkInterceptor(StethoInterceptor())
            //cookie管理
            mBuilder.cookieJar(CookiesManager())
            mBuilder.addInterceptor(MyHttpLoggerIntercept())
            if(BuildConfig.AW_DEBUG){
                mBuilder.addInterceptor(MultiBaseUrlInterceptor.getInstance())
            }
            return mBuilder.build()

        }
    }

}