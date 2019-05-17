package com.sleep.uulib.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.ViewConfiguration;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meituan.android.walle.WalleChannelReader;
import com.sleep.commonlib.util.LogUtil;
import com.sleep.uulib.bean.ChannelBean;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by SleepYM09 on 2017/12/14.
 * <p>
 * blog: www.sleepym09.com
 */

public class UUCommonUtil {
    /**
     * 返回友盟渠道对应的后台channelId，如果没找到默认返回0
     *
     * @param context
     * @return
     */
    public static int getChannelId(Context context) {
        Gson gson = new Gson();
        String assetsJson = AssetsUtil.getFromAssets(context, "channel.json");
        String finalJsonString = "";
        try {
            finalJsonString = URLDecoder.decode(assetsJson, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<ChannelBean> channels = gson.fromJson(finalJsonString, new TypeToken<List<ChannelBean>>() {
        }.getType());
        for (ChannelBean channel : channels) {
            if (channel.getChannelAppName().equals(WalleChannelReader.getChannel(context))) {
                LogUtil.d("channelName",WalleChannelReader.getChannel(context));
                return channel.getChannelId();
            }
        }
        return 0;
    }

    /**
     * 检查是否存在虚拟按键栏
     * @param context
     * @return
     */
    public static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // check override flag
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else { // fallback
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    /**
     * 判断虚拟按键栏是否重写
     * @return
     */
    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
            }
        }
        return sNavBarOverride;
    }

}
