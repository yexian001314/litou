package com.sleep.uulib.bean;

/**
 * Created by SleepYM09 on 2017/12/15.
 * <p>
 * blog: www.sleepym09.com
 */

public class AppUpdateBean {
    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * appVersion : {"id":3,"appChannelId":1004,"appDownUrl":"ewqeqw45","version":"3.2.2","recommendVersion":"3.2.1","forcedVersion":"3.2.0","issueTime":1501869744000,"isForcedUpdating":true,"isRecommendUpdating":false,"lastUpdateTime":1501971132000,"isDelete":false,"updateLog":"无测试","targetSize":"20M"}
     * updateType : 01
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private AppVersionBean appVersion;
    private String updateType;

    public ResponseStatusBean getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatusBean responseStatus) {
        this.responseStatus = responseStatus;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isIsException() {
        return isException;
    }

    public void setIsException(boolean isException) {
        this.isException = isException;
    }

    public AppVersionBean getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(AppVersionBean appVersion) {
        this.appVersion = appVersion;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public static class ResponseStatusBean {
        /**
         * code : 00
         * message : 操作成功
         */

        private String code;
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class AppVersionBean {
        /**
         * id : 3
         * appChannelId : 1004
         * appDownUrl : ewqeqw45
         * version : 3.2.2
         * recommendVersion : 3.2.1
         * forcedVersion : 3.2.0
         * issueTime : 1501869744000
         * isForcedUpdating : true
         * isRecommendUpdating : false
         * lastUpdateTime : 1501971132000
         * isDelete : false
         * updateLog : 无测试
         * targetSize : 20M
         */

        private int id;
        private int appChannelId;
        private String appDownUrl;
        private String version;
        private String recommendVersion;
        private String forcedVersion;
        private long issueTime;
        private boolean isForcedUpdating;
        private boolean isRecommendUpdating;
        private long lastUpdateTime;
        private boolean isDelete;
        private String updateLog;
        private String targetSize;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAppChannelId() {
            return appChannelId;
        }

        public void setAppChannelId(int appChannelId) {
            this.appChannelId = appChannelId;
        }

        public String getAppDownUrl() {
            return appDownUrl;
        }

        public void setAppDownUrl(String appDownUrl) {
            this.appDownUrl = appDownUrl;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getRecommendVersion() {
            return recommendVersion;
        }

        public void setRecommendVersion(String recommendVersion) {
            this.recommendVersion = recommendVersion;
        }

        public String getForcedVersion() {
            return forcedVersion;
        }

        public void setForcedVersion(String forcedVersion) {
            this.forcedVersion = forcedVersion;
        }

        public long getIssueTime() {
            return issueTime;
        }

        public void setIssueTime(long issueTime) {
            this.issueTime = issueTime;
        }

        public boolean isIsForcedUpdating() {
            return isForcedUpdating;
        }

        public void setIsForcedUpdating(boolean isForcedUpdating) {
            this.isForcedUpdating = isForcedUpdating;
        }

        public boolean isIsRecommendUpdating() {
            return isRecommendUpdating;
        }

        public void setIsRecommendUpdating(boolean isRecommendUpdating) {
            this.isRecommendUpdating = isRecommendUpdating;
        }

        public long getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(long lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }

        public boolean isIsDelete() {
            return isDelete;
        }

        public void setIsDelete(boolean isDelete) {
            this.isDelete = isDelete;
        }

        public String getUpdateLog() {
            return updateLog;
        }

        public void setUpdateLog(String updateLog) {
            this.updateLog = updateLog;
        }

        public String getTargetSize() {
            return targetSize;
        }

        public void setTargetSize(String targetSize) {
            this.targetSize = targetSize;
        }
    }
}
