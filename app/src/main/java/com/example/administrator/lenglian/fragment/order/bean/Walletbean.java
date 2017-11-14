package com.example.administrator.lenglian.fragment.order.bean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Walletbean {


    /**
     * code : 200
     * success : success
     * datas : [{"pro_id":"44","start_time":"2017-11-14 09:41:10","end_time":"2018-02-14 09:41:10","duration":"3","order_price":"1615.00","pro_deposit":"1405.00","pro_price":"55","pro_name":"豪华立式展示柜 LC-260","order_num":"201711140939341930","single_pic":"http://59.110.213.63/cfc/uploads/file1/20171025/59f02be0b15b9.jpg","balance":164.58},{"pro_id":"40","start_time":"2017-11-14 10:13:29","end_time":"2018-01-14 10:13:29","duration":"2","order_price":"2675.00","pro_deposit":"2500.00","pro_price":"70","pro_name":"多媒体展示柜 LC-308","order_num":"201711141011345403","single_pic":"http://59.110.213.63/cfc/uploads/file1/20171025/59f0298a7b75b.jpg","balance":139.51}]
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
         * pro_id : 44
         * start_time : 2017-11-14 09:41:10
         * end_time : 2018-02-14 09:41:10
         * duration : 3
         * order_price : 1615.00
         * pro_deposit : 1405.00
         * pro_price : 55
         * pro_name : 豪华立式展示柜 LC-260
         * order_num : 201711140939341930
         * single_pic : http://59.110.213.63/cfc/uploads/file1/20171025/59f02be0b15b9.jpg
         * balance : 164.58
         */

        private String pro_id;
        private String start_time;
        private String end_time;
        private String duration;
        private String order_price;
        private String pro_deposit;
        private String pro_price;
        private String pro_name;
        private String order_num;
        private String single_pic;
        private double balance;

        public String getPro_id() {
            return pro_id;
        }

        public void setPro_id(String pro_id) {
            this.pro_id = pro_id;
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

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public String getPro_deposit() {
            return pro_deposit;
        }

        public void setPro_deposit(String pro_deposit) {
            this.pro_deposit = pro_deposit;
        }

        public String getPro_price() {
            return pro_price;
        }

        public void setPro_price(String pro_price) {
            this.pro_price = pro_price;
        }

        public String getPro_name() {
            return pro_name;
        }

        public void setPro_name(String pro_name) {
            this.pro_name = pro_name;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getSingle_pic() {
            return single_pic;
        }

        public void setSingle_pic(String single_pic) {
            this.single_pic = single_pic;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }
}
