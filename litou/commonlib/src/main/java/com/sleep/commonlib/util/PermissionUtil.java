package com.sleep.commonlib.util;

import android.Manifest;
import android.app.Activity;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

/**
 * 作者：alsoWell on 2017/5/23 15:29
 * 邮箱：1161882463@qq.com
 */
public class PermissionUtil {


    public static void requestPermissions(final Activity mActivity, int  requestCode, PermissionListener permissionListener, String... permissions) {
        AndPermission.with(mActivity)
                .requestCode(requestCode)
                .permission(Manifest.permission.READ_PHONE_STATE)
                .callback(permissionListener)
                .start();
    }

}
