package com.sleep.uulib.netWork;


import com.sleep.commonlib.util.LogUtil;
import com.sleep.commonlib.util.SPUtil;
import com.sleep.uulib.constant.Constant;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 作者：SleepY on 2017/5/16 19:29
 * 邮箱：1138984416@qq.com
 */
public class ReceivedCookiesInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
        //这里获取请求返回的cookie
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            final StringBuffer cookieBuffer = new StringBuffer();
            String[] split = originalResponse.headers("Set-Cookie").get(0).split(";");
            cookieBuffer.append(split[0]).append(";");
            SPUtil.getInstance().put(Constant.COOKIE,cookieBuffer.toString());
            LogUtil.d(cookieBuffer.toString());
        }

        return originalResponse;
    }
}
