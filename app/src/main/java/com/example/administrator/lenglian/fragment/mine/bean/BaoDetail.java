package com.example.administrator.lenglian.fragment.mine.bean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class BaoDetail {


    /**
     * code : 200
     * success : success
     * datas : [{"pro_id":"1","contact_name":"1","contact_mobile":"1","repair_address":"1","order_id":"10","repair_num":"1","repair_status":"1","repair_note":"1","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"main_title":"啊啊啊啊啊","pro_price":"1","sub_title":"广泛的是第三方","pro_name":"fdsafdg"}]
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
         * pro_id : 1
         * contact_name : 1
         * contact_mobile : 1
         * repair_address : 1
         * order_id : 10
         * repair_num : 1
         * repair_status : 1
         * repair_note : 1
         * pro_pic : [{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}]
         * main_title : 啊啊啊啊啊
         * pro_price : 1
         * sub_title : 广泛的是第三方
         * pro_name : fdsafdg
         */

        private String pro_id;
        private String contact_name;
        private String contact_mobile;
        private String repair_address;
        private String order_id;
        private String repair_num;
        private String repair_status;
        private String repair_note;
        private String main_title;
        private String pro_price;
        private String sub_title;
        private String pro_name;
        private List<ProPicBean> pro_pic;

        public String getPro_id() {
            return pro_id;
        }

        public void setPro_id(String pro_id) {
            this.pro_id = pro_id;
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

        public String getRepair_address() {
            return repair_address;
        }

        public void setRepair_address(String repair_address) {
            this.repair_address = repair_address;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getRepair_num() {
            return repair_num;
        }

        public void setRepair_num(String repair_num) {
            this.repair_num = repair_num;
        }

        public String getRepair_status() {
            return repair_status;
        }

        public void setRepair_status(String repair_status) {
            this.repair_status = repair_status;
        }

        public String getRepair_note() {
            return repair_note;
        }

        public void setRepair_note(String repair_note) {
            this.repair_note = repair_note;
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
