package com.sleep.uulib.netWork;


import com.sleep.commonlib.util.SPUtil;
import com.sleep.commonlib.util.StringUtil;
import com.sleep.uulib.constant.Constant;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：SleepY on 2017/5/16 19:29
 * 邮箱：1138984416@qq.com
 */
public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();
        String cookie = SPUtil.getInstance().getString(Constant.COOKIE, "");
        if(!StringUtil.isEmpty(cookie)){
            builder.addHeader("Cookie", cookie);
       }
        return chain.proceed(builder.build());
    }
}
