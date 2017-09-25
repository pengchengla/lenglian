package com.example.administrator.lenglian.fragment.mine.bean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Returntailbean {

    /**
     * code : 200
     * success : success
     * datas : [{"return_id":"13","pro_id":"1","order_num":"aaabbb","commit_time":"2017-09-20 16:36:43","ok_time":"0000-00-00 00:00:00","contact_name":"安静","contact_mobile":"18310482720","return_address":"规矩是外包","return_status":"1","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"main_title":"啊啊啊啊啊","pro_price":"1","sub_title":"广泛的是第三方","pro_name":"fdsafdg"}]
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
         * return_id : 13
         * pro_id : 1
         * order_num : aaabbb
         * commit_time : 2017-09-20 16:36:43
         * ok_time : 0000-00-00 00:00:00
         * contact_name : 安静
         * contact_mobile : 18310482720
         * return_address : 规矩是外包
         * return_status : 1
         * pro_pic : [{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}]
         * main_title : 啊啊啊啊啊
         * pro_price : 1
         * sub_title : 广泛的是第三方
         * pro_name : fdsafdg
         */

        private String return_id;
        private String pro_id;
        private String order_num;
        private String commit_time;
        private String ok_time;
        private String contact_name;
        private String contact_mobile;
        private String return_address;
        private String return_status;
        private String main_title;
        private String pro_price;
        private String sub_title;
        private String pro_name;
        private List<ProPicBean> pro_pic;

        public String getReturn_id() {
            return return_id;
        }

        public void setReturn_id(String return_id) {
            this.return_id = return_id;
        }

        public String getPro_id() {
            return pro_id;
        }

        public void setPro_id(String pro_id) {
            this.pro_id = pro_id;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getCommit_time() {
            return commit_time;
        }

        public void setCommit_time(String commit_time) {
            this.commit_time = commit_time;
        }

        public String getOk_time() {
            return ok_time;
        }

        public void setOk_time(String ok_time) {
            this.ok_time = ok_time;
        }

        public String getContact_name() {
            return contact_name;
        }

        public void setContact_name(String contact_name) {
            this.contact_name = contact_name;
        }

        public String getContact_mobile() {
            return contact_mobile;
        }

        public void setContact_mobile(String contact_mobile) {
            this.contact_mobile = contact_mobile;
        }

        public String getReturn_address() {
            return return_address;
        }

        public void setReturn_address(String return_address) {
            this.return_address = return_address;
        }

        public String getReturn_status() {
            return return_status;
        }

        public void setReturn_status(String return_status) {
            this.return_status = return_status;
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
