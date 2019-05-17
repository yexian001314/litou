package com.sleep.uulib.netWork;

import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;
import com.sleep.commonlib.base.BaseEvent;
import com.sleep.commonlib.base.EventCode;
import com.sleep.commonlib.util.LogUtil;
import com.sleep.commonlib.util.StringUtil;
import com.sleep.commonlib.util.TimeUtils;
import com.sleep.commonlib.util.Utils;
import com.sleep.uulib.UUApplication;
import com.sleep.uulib.bean.SystemMaintainBean;
import com.sleep.uulib.constant.Constant;
import com.sleep.uulib.constant.NetConstant;
import com.sleep.uulib.enums.ErrorType;

import org.simple.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * 作者：alsoWell on 2017/5/12 14:46
 * 邮箱：1161882463@qq.com
 */
public class MyHttpLoggerIntercept implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();
        Connection connection = chain.connection();
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        //请求发起的时间
        long t1 = System.nanoTime();
        if (Utils.isApkDebugable(UUApplication.sInstance) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.e("yexmlInterface", String.format("发送请求 %s on %s%n%s",
                    request.url(), TimeUtils.milliseconds2String(System.currentTimeMillis(), Constant.DEFAULT_WELFARE_DATE_FORMAT),
                    request.headers()));
            Log.e("yexmlInterface 请求体:", bodyToString(request));
        } else {
            LogUtil.e("yexmlInterface", String.format("发送请求 %s on %s%n%s",
                    request.url(), TimeUtils.milliseconds2String(System.currentTimeMillis(), Constant.DEFAULT_WELFARE_DATE_FORMAT),
                    request.headers()));
            LogUtil.json("yexmlInterface 请求体:", bodyToString(request));
        }
        Response response = chain.proceed(request);
        //收到响应的时间
        long t2 = System.nanoTime();

        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(1024 * 1024);

        LogUtil.json("fuckandroid", String.format("接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
                response.request().url(),
                responseBody.string(),
                (t2 - t1) / 1e6d,
                response.headers()));

        String systemErrorTag = response.header(NetConstant.SYSTEM_ERROR_TAG);
        if (!StringUtil.isEmpty(systemErrorTag)) {
            //服务器升级或者繁忙
            SystemMaintainBean systemMaintainBean = new Gson().fromJson(systemErrorTag, SystemMaintainBean.class);
            LogUtil.e("serverError", "receive server error code is " + systemErrorTag);
            if (NetConstant.SYSTEM_MAINTAIN_TAG.equals(systemMaintainBean.getVal())) {
                //服务器维护升级
                UUApplication.Companion.setSystemMaintainTime(systemMaintainBean);
                UUApplication.Companion.setSystemErrorType(ErrorType.SYSTEM_MAINTAIN);
                //服务器停服弹窗
                if (!UUApplication.Companion.getSystemMaintainDialogIsShow()) {
                    EventBus.getDefault().post(new BaseEvent(EventCode.SYSTEM_MAINTAIN));
                }
            } else if (NetConstant.SYSTEM_BUSY_TAG.equals(systemMaintainBean.getVal())) {
                //服务器繁忙
                UUApplication.Companion.setSystemErrorType(ErrorType.SYSTEM_BUSY);
            }
        } else {
            //普通请求
            UUApplication.Companion.setSystemErrorType(ErrorType.NONE);
        }
        return response;
    }

    private static String bodyToString(final Request request) {

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            if (copy.body() == null) {
                return "get------------";
            }
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
