package com.example.administrator.lenglian.fragment.order.bean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Walletbean {


    /**
     * code : 200
     * msg : success
     * datas : {"balance":"余额","start_time":"开始时间","end_time":"结束时间","pro_deposit":"押金"}
     */

    private int code;
    private String msg;
    private DatasBean datas;

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

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * balance : 余额
         * start_time : 开始时间
         * end_time : 结束时间
         * pro_deposit : 押金
         */

        private String balance;
        private String start_time;
        private String end_time;
        private String pro_deposit;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
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

        public String getPro_deposit() {
            return pro_deposit;
        }

        public void setPro_deposit(String pro_deposit) {
            this.pro_deposit = pro_deposit;
        }
    }
}
