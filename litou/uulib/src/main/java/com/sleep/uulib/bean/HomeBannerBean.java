package com.sleep.uulib.bean;

import java.util.List;

/**
 * Created by SleepYM09 on 2017/10/31.
 * <p>
 * blog: www.sleepym09.com
 */

public class HomeBannerBean {
    /**
     * responseStatus : {"code":"00","message":"操作成功"}
     * success : true
     * isException : false
     * bannerList : [{"banner_id":42,"banner_name":"活动","pic_url":"http://uumoney.cn/backWeb/images/verify/index_banner_banner_06517936a07843f39b847214206600c3jpg.jpg","jump_url":"activity.html","status":1,"platform":1,"show_order":2,"create_time":1509001827000},{"banner_id":44,"banner_name":"活动","pic_url":"http://uumoney.cn/backWeb/images/verify/index_banner_banner_baa41360051c4820802d2e94a2a00fc2jpg.jpg","jump_url":"activity.html","status":1,"platform":1,"show_order":2,"create_time":1509067963000},{"banner_id":45,"banner_name":"你好","pic_url":"http://uumoney.cn/backWeb/images/verify/index_banner_banner_cba80de6f21f4f399f1a943c077af2f3jpg.jpg","jump_url":"activity.html","status":1,"platform":1,"show_order":4,"create_time":1509070482000}]
     */

    private ResponseStatusBean responseStatus;
    private boolean success;
    private boolean isException;
    private List<BannerListBean> bannerList;

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

    public List<BannerListBean> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerListBean> bannerList) {
        this.bannerList = bannerList;
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

    public static class BannerListBean {
        /**
         * banner_id : 42
         * banner_name : 活动
         * pic_url : http://uumoney.cn/backWeb/images/verify/index_banner_banner_06517936a07843f39b847214206600c3jpg.jpg
         * jump_url : activity.html
         * status : 1
         * platform : 1
         * show_order : 2
         * create_time : 1509001827000
         */

        private int banner_id;
        private String banner_name;
        private String pic_url;
        private String jump_url;
        private int status;
        private int platform;
        private int show_order;
        private long create_time;

        public int getBanner_id() {
            return banner_id;
        }

        public void setBanner_id(int banner_id) {
            this.banner_id = banner_id;
        }

        public String getBanner_name() {
            return banner_name;
        }

        public void setBanner_name(String banner_name) {
            this.banner_name = banner_name;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getJump_url() {
            return jump_url;
        }

        public void setJump_url(String jump_url) {
            this.jump_url = jump_url;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getPlatform() {
            return platform;
        }

        public void setPlatform(int platform) {
            this.platform = platform;
        }

        public int getShow_order() {
            return show_order;
        }

        public void setShow_order(int show_order) {
            this.show_order = show_order;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }
    }
}
