package com.example.administrator.lenglian.fragment.order.bean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Xiangqingbean {


    /**
     * code : 200
     * success : success
     * datas : [{"order_id":"23","order_num":"aaabbb","order_price":"","order_status":"1","vendor_id":"4","user_id":"71","exp_time":"0000-00-00 00:00:00","order_time":"0000-00-00 00:00:00","ok_time":"0000-00-00 00:00:00","express_id":"13","receive_time":"0000-00-00 00:00:00","note":"","pro_id":"1","duration":"1","start_time":"0000-00-00 00:00:00","end_time":"0000-00-00 00:00:00","is_comment":"0","receive_name":"哈哈","mobile":"18863894005","area_id":"北京","address_detail":"海淀桥头","is_default":"2","is_del":"1","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"main_title":"啊啊啊啊啊","pro_price":"1","sub_title":"广泛的是第三方","pro_name":"fdsafdg","single_pic":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png","pro_deposit":"5000.00","express_money":"50.00","expired_time":0}]
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
         * order_id : 23
         * order_num : aaabbb
         * order_price :
         * order_status : 1
         * vendor_id : 4
         * user_id : 71
         * exp_time : 0000-00-00 00:00:00
         * order_time : 0000-00-00 00:00:00
         * ok_time : 0000-00-00 00:00:00
         * express_id : 13
         * receive_time : 0000-00-00 00:00:00
         * note :
         * pro_id : 1
         * duration : 1
         * start_time : 0000-00-00 00:00:00
         * end_time : 0000-00-00 00:00:00
         * is_comment : 0
         * receive_name : 哈哈
         * mobile : 18863894005
         * area_id : 北京
         * address_detail : 海淀桥头
         * is_default : 2
         * is_del : 1
         * pro_pic : [{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}]
         * main_title : 啊啊啊啊啊
         * pro_price : 1
         * sub_title : 广泛的是第三方
         * pro_name : fdsafdg
         * single_pic : http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png
         * pro_deposit : 5000.00
         * express_money : 50.00
         * expired_time : 0
         */

        private String order_id;
        private String order_num;
        private String order_price;
        private String order_status;
        private String vendor_id;
        private String user_id;
        private String exp_time;
        private String order_time;
        private String ok_time;
        private String express_id;
        private String receive_time;
        private String note;
        private String pro_id;
        private String duration;
        private String start_time;
        private String end_time;
        private String is_comment;
        private String receive_name;
        private String mobile;
        private String area_id;
        private String address_detail;
        private String is_default;
        private String is_del;
        private String main_title;
        private String pro_price;
        private String sub_title;
        private String pro_name;
        private String single_pic;
        private String pro_deposit;
        private String express_money;
        private int expired_time;
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

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getVendor_id() {
            return vendor_id;
        }

        public void setVendor_id(String vendor_id) {
            this.vendor_id = vendor_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getExp_time() {
            return exp_time;
        }

        public void setExp_time(String exp_time) {
            this.exp_time = exp_time;
        }

        public String getOrder_time() {
            return order_time;
        }

        public void setOrder_time(String order_time) {
            this.order_time = order_time;
        }

        public String getOk_time() {
            return ok_time;
        }

        public void setOk_time(String ok_time) {
            this.ok_time = ok_time;
        }

        public String getExpress_id() {
            return express_id;
        }

        public void setExpress_id(String express_id) {
            this.express_id = express_id;
        }

        public String getReceive_time() {
            return receive_time;
        }

        public void setReceive_time(String receive_time) {
            this.receive_time = receive_time;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getPro_id() {
            return pro_id;
        }

        public void setPro_id(String pro_id) {
            this.pro_id = pro_id;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(String is_comment) {
            this.is_comment = is_comment;
        }

        public String getReceive_name() {
            return receive_name;
        }

        public void setReceive_name(String receive_name) {
            this.receive_name = receive_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getAddress_detail() {
            return address_detail;
        }

        public void setAddress_detail(String address_detail) {
            this.address_detail = address_detail;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
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

        public String getSingle_pic() {
            return single_pic;
        }

        public void setSingle_pic(String single_pic) {
            this.single_pic = single_pic;
        }

        public String getPro_deposit() {
            return pro_deposit;
        }

        public void setPro_deposit(String pro_deposit) {
            this.pro_deposit = pro_deposit;
        }

        public String getExpress_money() {
            return express_money;
        }

        public void setExpress_money(String express_money) {
            this.express_money = express_money;
        }

        public int getExpired_time() {
            return expired_time;
        }

        public void setExpired_time(int expired_time) {
            this.expired_time = expired_time;
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
