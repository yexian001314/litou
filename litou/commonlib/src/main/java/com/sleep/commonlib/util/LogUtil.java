package com.sleep.commonlib.util;


import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.jiongbull.jlog.Logger;
import com.jiongbull.jlog.constant.LogLevel;
import com.jiongbull.jlog.constant.LogSegment;
import com.jiongbull.jlog.util.TimeUtils;
import com.sleep.commonlib.BaseApplication;
import com.sleep.commonlib.BuildConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：alsoWell on 2017/5/3 16:44
 * 邮箱：1161882463@qq.com
 */
public class LogUtil {
    private static Logger sLogger;
    private static Context mContext;

    public static void initLog() {
        List<String> logLevels = new ArrayList<>();
        logLevels.add(LogLevel.ERROR);
        logLevels.add(LogLevel.INFO);
        logLevels.add(LogLevel.DEBUG);
        logLevels.add(LogLevel.WTF);
        sLogger = Logger.Builder.newBuilder(BaseApplication.sInstance, "FUCK_ANDROID")
                /* 下面的属性都是默认值，你可以根据需求决定是否修改它们. */
                .setDebug(BuildConfig.AW_DEBUG)
                .setWriteToFile(false)
                .setLogDir("FUCK_ANDROID")
                .setLogPrefix("yexmLog")
                .setLogSegment(LogSegment.TWELVE_HOURS)
                .setLogLevelsForFile(logLevels)
                .setZoneOffset(TimeUtils.ZoneOffset.P0800)
                .setTimeFormat("yyyy-MM-dd HH:mm:ss")
                .setPackagedLevel(1)
                .setStorage(null)
                .build();
    }

    /**
     * 打印info级别的log
     */
    public static void i(String tag, String msg) {

        if (BuildConfig.AW_DEBUG && AppUtils.isAppForeground(BaseApplication.sInstance) && sLogger != null) {
            if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.i(tag,msg);
            }else {
                sLogger.i(tag, msg);
            }
        }

    }

    public static void i(String msg) {

        if (BuildConfig.AW_DEBUG && AppUtils.isAppForeground(BaseApplication.sInstance) && sLogger != null) {
            if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.i("yxm",msg);
            }else {
                sLogger.i(msg);
            }
        }
    }

    /**
     * 打印error级别的log
     */
    public static void e(String tag, String msg) {

        if (BuildConfig.AW_DEBUG && AppUtils.isAppForeground(BaseApplication.sInstance) && sLogger != null) {
            if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.e(tag,msg);
            }else {
                sLogger.e(tag, msg);
            }
        }
    }

    /**
     * 抛出异常的错误log
     */
    public static void e(String msg) {

        if (BuildConfig.AW_DEBUG && AppUtils.isAppForeground(BaseApplication.sInstance) && sLogger != null) {
            if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.e("yxm",msg);
            }else {
                sLogger.e(msg);
            }
        }
    }

    /**
     * 打印string类json字符串
     *
     * @param json
     */
    public static void json(String json) {
        if (BuildConfig.AW_DEBUG && AppUtils.isAppForeground(BaseApplication.sInstance) && sLogger != null) {
            sLogger.json(json);
        }
    }

    public static void json(String tag, String json) {
        if (BuildConfig.AW_DEBUG && AppUtils.isAppForeground(BaseApplication.sInstance) && sLogger != null) {
            sLogger.json(tag, json);
        }
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.AW_DEBUG && AppUtils.isAppForeground(BaseApplication.sInstance) && sLogger != null) {
            sLogger.d(msg);
        }
    }

    /**
     * 打印AW_DEBUG类型字符
     *
     * @param msg
     */
    public static void d(String msg) {
        if (BuildConfig.AW_DEBUG && AppUtils.isAppForeground(BaseApplication.sInstance) && sLogger != null) {
            sLogger.d(msg);
        }
    }

    /**
     * warn类型
     *
     * @param msg
     */
    public static void w(String tag, String msg) {
        if (BuildConfig.AW_DEBUG && AppUtils.isAppForeground(BaseApplication.sInstance) && sLogger != null) {
            sLogger.w(tag, msg);
        }
    }

    public static void w(String msg) {
        if (BuildConfig.AW_DEBUG && AppUtils.isAppForeground(BaseApplication.sInstance) && sLogger != null) {
            sLogger.w(msg);
        }
    }
}
