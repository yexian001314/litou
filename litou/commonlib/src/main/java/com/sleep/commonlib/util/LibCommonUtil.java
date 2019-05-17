package com.sleep.commonlib.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.widget.TextView;

import com.sleep.commonlib.widget.TimerCount;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者：alsoWell on 2017/5/12 18:26
 * 邮箱：1161882463@qq.com
 */
public class LibCommonUtil {


    /**
     * 防止控件的重复点击
     */
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 传入button返回验证码倒计时效果 记得调用 start
     *
     * @param mBtn
     * @return
     */
    public static TimerCount getTimerCount(TextView mBtn) {
        //rebuild 需要重构，修改为自定义颜色
        mBtn.setTextColor(Color.parseColor("#4c4c4c"));
        TimerCount mTimerCount = new TimerCount(60000, 1000, mBtn);
        return mTimerCount;
    }

    /**
     *  返回true 表示可以使用  返回false表示不可以使用
     * @return
     */
    public static boolean cameraIsCanUse() {
        boolean isCanUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
            Camera.Parameters mParameters = mCamera.getParameters(); //针对魅族手机
            mCamera.setParameters(mParameters);
        } catch (Exception e) {
            isCanUse = false;
        }

        if (mCamera != null) {
            try {
                mCamera.release();
            } catch (Exception e) {
                e.printStackTrace();
                return isCanUse;
            }
        }
        return isCanUse;
    }

    /**
     * AES加密
     * @param code
     * @return
     */
    public static String AESEncode(String code){
        byte[] bytes = EncryptUtils.encryptAES(code.getBytes(), EncodeUtils.base64Decode("euNirWrWgUnbTR/t2ITQ6OZSTejAKDT2"));
        return EncodeUtils.base64Encode2String(bytes);
    }

    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context
     * @param packageName
     *            ：应用包名
     * @return
     */
    public static boolean isAvilible(Context context, String packageName) {
        // 获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        // 用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        // 从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        // 判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

}
