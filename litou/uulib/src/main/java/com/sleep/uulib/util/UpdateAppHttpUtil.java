package com.sleep.uulib.util;

import android.support.annotation.NonNull;

import com.google.gson.JsonObject;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.sleep.commonlib.Execption.NoNetWorkException;
import com.sleep.commonlib.util.DeviceUtils;
import com.sleep.commonlib.util.LogUtil;
import com.sleep.commonlib.util.PhoneUtils;
import com.sleep.uulib.UUApplication;
import com.sleep.uulib.constant.NetConstant;
import com.sleep.uulib.netWork.AppRequest;
import com.sleep.uulib.netWork.BaseObserver;
import com.sleep.uulib.netWork.OnLoadDataListener;
import com.vector.update_app.HttpManager;
import com.vector.update_app.utils.AppUpdateUtils;

import org.jetbrains.annotations.Nullable;
import org.json.JSONException;

import java.io.File;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.TreeMap;

/**
 * <pre>
 *     author : Chen
 *     time   : 2017/08/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class UpdateAppHttpUtil implements HttpManager {


    /**
     *
     * 异步get
     *
     * @param url           get请求地址
     * @param params        get参数
     * @param callBack      回调
     */

    @Override
    public void asyncGet(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {
    }


    /**
     * 异步post
     *
     * @param url           post请求地址
     * @param params        post请求参数
     * @param callBack      回调
     */
    @Override
    public void asyncPost(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("version", AppUpdateUtils.getVersionName(UUApplication.sInstance));
        treeMap.put("appType", 0);
        treeMap.put("deviceModel", DeviceUtils.getModel());
        treeMap.put(NetConstant.IMEI, PhoneUtils.getIMEI());
        treeMap.put("mobileVersion", String.valueOf(DeviceUtils.getSDKVersion()));
        treeMap.put("os", "0");
        AppRequest.Companion.getINSTANCE().postWithJson(treeMap, url, new BaseObserver<>(new OnLoadDataListener<JsonObject>() {
            @Override
            public void onFailure(@Nullable Throwable e, int errorCode) {
                callBack.onError(validateError(e));
            }

            @Override
            public void onSuccess(JsonObject data) {
                callBack.onResponse(data.toString());
            }
        }));
    }

    /**
     * 下载
     *
     * @param url      下载地址
     * @param path     文件保存路径
     * @param fileName 文件名称
     * @param callback 回调
     */
    @Override
    public void download(@NonNull String url, @NonNull final String path, @NonNull String fileName, @NonNull final FileCallback callback) {
        FileDownloader.getImpl().create(url)
                .setPath(path)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        callback.onBefore();
                    }

                    @Override
                    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        float progress = (float) soFarBytes/(float) totalBytes;
                        LogUtil.e("progress", " progress = " + progress);
                        callback.onProgress(progress,totalBytes);
                    }

                    @Override
                    protected void blockComplete(BaseDownloadTask task) {
                    }

                    @Override
                    protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes, final int soFarBytes) {
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        callback.onResponse(new File(path));
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        callback.onError(validateError(e));
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                    }
                }).start();
    }




    private String validateError(Throwable error) {

        if (error != null) {
            if (error instanceof NoNetWorkException) {
                return "无网络，请联网重试";
            } else if (error instanceof SocketTimeoutException) {
                return "网络连接超时，请稍候重试";
            } else if (error instanceof JSONException) {
                return "json转化异常";
            } else if (error instanceof ConnectException) {
                return "服务器网络异常或宕机，请稍候重试";
            }
        }

        return "未知异常，请稍候重试";
    }
}
