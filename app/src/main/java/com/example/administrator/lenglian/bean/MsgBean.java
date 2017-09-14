package com.example.administrator.lenglian.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */

public class MsgBean {

    /**
     * code : 1
     * datas : [{"news":"1","send_time":"0000-00-00 00:00:00","news_type":"1","user_id":"1","is_del":"0","news_status":"1","news_id":"1"}]
     * success : success
     */
    private int code;
    private List<DatasEntity> datas;
    private String success;

    public void setCode(int code) {
        this.code = code;
    }

    public void setDatas(List<DatasEntity> datas) {
        this.datas = datas;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public List<DatasEntity> getDatas() {
        return datas;
    }

    public String getSuccess() {
        return success;
    }

    public static class DatasEntity {
        /**
         * news : 1
         * send_time : 0000-00-00 00:00:00
         * news_type : 1
         * user_id : 1
         * is_del : 0
         * news_status : 1
         * news_id : 1
         */
        private String news;
        private String send_time;
        private String news_type;
        private String user_id;
        private String is_del;
        private String news_status;
        private String news_id;

        public void setNews(String news) {
            this.news = news;
        }

        public void setSend_time(String send_time) {
            this.send_time = send_time;
        }

        public void setNews_type(String news_type) {
            this.news_type = news_type;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public void setNews_status(String news_status) {
            this.news_status = news_status;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getNews() {
            return news;
        }

        public String getSend_time() {
            return send_time;
        }

        public String getNews_type() {
            return news_type;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getIs_del() {
            return is_del;
        }

        public String getNews_status() {
            return news_status;
        }

        public String getNews_id() {
            return news_id;
        }
    }
}
