package com.example.administrator.lenglian.fragment.order.bean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Zhifubean {

    /**
     * code : 200
     * success : success
     * datas : [{"order_id":"11","order_num":"aaabbb","order_status":"1","pro_id":"1","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"main_title":"啊啊啊啊啊","pro_price":"1","sub_title":"广泛的是第三方","pro_name":"fdsafdg"}]
     */

    private int code;
    private String success;
    private List<DatasBean> datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * order_id : 11
         * order_num : aaabbb
         * order_status : 1
         * pro_id : 1
         * pro_pic : [{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}]
         * main_title : 啊啊啊啊啊
         * pro_price : 1
         * sub_title : 广泛的是第三方
         * pro_name : fdsafdg
         */

        private String order_id;
        private String order_num;
        private String order_status;
        private String pro_id;
        private String main_title;
        private String pro_price;
        private String sub_title;
        private String pro_name;
        private List<ProPicBean> pro_pic;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getPro_id() {
            return pro_id;
        }

        public void setPro_id(String pro_id) {
            this.pro_id = pro_id;
        }

        public String getMain_title() {
            return main_title;
        }

        public void setMain_title(String main_title) {
            this.main_title = main_title;
        }

        public String getPro_price() {
            return pro_price;
        }

        public void setPro_price(String pro_price) {
            this.pro_price = pro_price;
        }

        public String getSub_title() {
            return sub_title;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public String getPro_name() {
            return pro_name;
        }

        public void setPro_name(String pro_name) {
            this.pro_name = pro_name;
        }

        public List<ProPicBean> getPro_pic() {
            return pro_pic;
        }

        public void setPro_pic(List<ProPicBean> pro_pic) {
            this.pro_pic = pro_pic;
        }

        public static class ProPicBean {
            /**
             * url : http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
