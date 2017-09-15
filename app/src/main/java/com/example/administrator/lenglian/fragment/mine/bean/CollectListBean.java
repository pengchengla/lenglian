package com.example.administrator.lenglian.fragment.mine.bean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class CollectListBean {

    /**
     * code : 200
     * success : success
     * datas : [{"pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","collect_id":"11","user_id":"1","is_del":"0","main_title":"啊啊啊啊啊","pro_name":"fdsafdg"},{"pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","collect_id":"12","user_id":"1","is_del":"0","main_title":"啊啊啊啊啊","pro_name":"fdsafdg"},{"pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","collect_id":"13","user_id":"1","is_del":"0","main_title":"啊啊啊啊啊","pro_name":"fdsafdg"},{"pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","collect_id":"14","user_id":"1","is_del":"0","main_title":"啊啊啊啊啊","pro_name":"fdsafdg"},{"pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","collect_id":"15","user_id":"1","is_del":"0","main_title":"啊啊啊啊啊","pro_name":"fdsafdg"},{"pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","collect_id":"16","user_id":"1","is_del":"0","main_title":"啊啊啊啊啊","pro_name":"fdsafdg"},{"pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","collect_id":"26","user_id":"1","is_del":"0","main_title":"啊啊啊啊啊","pro_name":"fdsafdg"},{"pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","collect_id":"27","user_id":"1","is_del":"0","main_title":"啊啊啊啊啊","pro_name":"fdsafdg"},{"pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","collect_id":"28","user_id":"1","is_del":"0","main_title":"啊啊啊啊啊","pro_name":"fdsafdg"},{"pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","collect_id":"36","user_id":"1","is_del":"0","main_title":"啊啊啊啊啊","pro_name":"fdsafdg"},{"pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","collect_id":"37","user_id":"1","is_del":"0","main_title":"啊啊啊啊啊","pro_name":"fdsafdg"},{"pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","collect_id":"49","user_id":"1","is_del":"0","main_title":"啊啊啊啊啊","pro_name":"fdsafdg"}]
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
         * pro_price : 2
         * pro_pic : [{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}]
         * sub_title : 广泛的是第三方
         * pro_id : 2
         * collect_id : 11
         * user_id : 1
         * is_del : 0
         * main_title : 啊啊啊啊啊
         * pro_name : fdsafdg
         */
        private boolean isChecked;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        private String pro_price;
        private List<ProPicEntity> pro_pic;
        private String sub_title;
        private String pro_id;
        private String collect_id;
        private String user_id;
        private String is_del;
        private String main_title;
        private String pro_name;

        public void setPro_price(String pro_price) {
            this.pro_price = pro_price;
        }

        public void setPro_pic(List<ProPicEntity> pro_pic) {
            this.pro_pic = pro_pic;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public void setPro_id(String pro_id) {
            this.pro_id = pro_id;
        }

        public void setCollect_id(String collect_id) {
            this.collect_id = collect_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public void setMain_title(String main_title) {
            this.main_title = main_title;
        }

        public void setPro_name(String pro_name) {
            this.pro_name = pro_name;
        }

        public String getPro_price() {
            return pro_price;
        }

        public List<ProPicEntity> getPro_pic() {
            return pro_pic;
        }

        public String getSub_title() {
            return sub_title;
        }

        public String getPro_id() {
            return pro_id;
        }

        public String getCollect_id() {
            return collect_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getIs_del() {
            return is_del;
        }

        public String getMain_title() {
            return main_title;
        }

        public String getPro_name() {
            return pro_name;
        }

        public static class ProPicEntity {
            /**
             * url : http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png
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
