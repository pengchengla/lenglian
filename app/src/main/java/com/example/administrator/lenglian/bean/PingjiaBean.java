package com.example.administrator.lenglian.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/12.
 */

public class PingjiaBean {


    /**
     * code : 200
     * success : success
     * datas : [{"add_content":"1","pic_id":"2","mobile":"15811337458","pro_score":"1","commit_time":"0000-00-00 00:00:00","pic":[{"url":"/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"add_time":"0000-00-00 00:00:00","content":"1"}]
     */
    private int code;
    private String success;
    private List<DatasEntity> datas;

    public void setCode(int code) {
        this.code = code;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public void setDatas(List<DatasEntity> datas) {
        this.datas = datas;
    }

    public int getCode() {
        return code;
    }

    public String getSuccess() {
        return success;
    }

    public List<DatasEntity> getDatas() {
        return datas;
    }

    public static class DatasEntity {
        /**
         * add_content : 1
         * pic_id : 2
         * mobile : 15811337458
         * pro_score : 1
         * commit_time : 0000-00-00 00:00:00
         * pic : [{"url":"/cfc/uploads/file1/20170912/59b78365b13b4.png"}]
         * add_time : 0000-00-00 00:00:00
         * content : 1
         */
        private String add_content;
        private String pic_id;
        private String mobile;
        private String pro_score;
        private String commit_time;
        private List<PicEntity> pic;
        private String add_time;
        private String content;

        public void setAdd_content(String add_content) {
            this.add_content = add_content;
        }

        public void setPic_id(String pic_id) {
            this.pic_id = pic_id;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setPro_score(String pro_score) {
            this.pro_score = pro_score;
        }

        public void setCommit_time(String commit_time) {
            this.commit_time = commit_time;
        }

        public void setPic(List<PicEntity> pic) {
            this.pic = pic;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAdd_content() {
            return add_content;
        }

        public String getPic_id() {
            return pic_id;
        }

        public String getMobile() {
            return mobile;
        }

        public String getPro_score() {
            return pro_score;
        }

        public String getCommit_time() {
            return commit_time;
        }

        public List<PicEntity> getPic() {
            return pic;
        }

        public String getAdd_time() {
            return add_time;
        }

        public String getContent() {
            return content;
        }

        public static class PicEntity {
            /**
             * url : /cfc/uploads/file1/20170912/59b78365b13b4.png
             */
            private String url;

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUrl() {
                return url;
            }
        }
    }
}
