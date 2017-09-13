package com.example.administrator.lenglian.fragment.order.bean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Dingdanbean {

    /**
     * code : 200
     * msg : success
     * datas : [{"order_id":"10","pro_id":"20","order_status":"1","pro_pic":"url","price":"2000","main_title":"aaa"}]
     */

    private int code;
    private String msg;
    private List<DatasBean> datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * order_id : 10
         * pro_id : 20
         * order_status : 1
         * pro_pic : url
         * price : 2000
         * main_title : aaa
         */

        private String order_id;
        private String pro_id;
        private String order_status;
        private String pro_pic;
        private String price;
        private String main_title;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getPro_id() {
            return pro_id;
        }

        public void setPro_id(String pro_id) {
            this.pro_id = pro_id;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getPro_pic() {
            return pro_pic;
        }

        public void setPro_pic(String pro_pic) {
            this.pro_pic = pro_pic;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMain_title() {
            return main_title;
        }

        public void setMain_title(String main_title) {
            this.main_title = main_title;
        }
    }
}
