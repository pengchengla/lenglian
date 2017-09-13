package com.example.administrator.lenglian.fragment.order.bean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Walletbean {

    /**
     * code : 200
     * msg : success
     * datas : {"balance":"100","balance_begin_time":"","balance_end_time":"","deposit":"20"}
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
         * balance : 100
         * balance_begin_time :
         * balance_end_time :
         * deposit : 20
         */

        private String balance;
        private String balance_begin_time;
        private String balance_end_time;
        private String deposit;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getBalance_begin_time() {
            return balance_begin_time;
        }

        public void setBalance_begin_time(String balance_begin_time) {
            this.balance_begin_time = balance_begin_time;
        }

        public String getBalance_end_time() {
            return balance_end_time;
        }

        public void setBalance_end_time(String balance_end_time) {
            this.balance_end_time = balance_end_time;
        }

        public String getDeposit() {
            return deposit;
        }

        public void setDeposit(String deposit) {
            this.deposit = deposit;
        }
    }
}
