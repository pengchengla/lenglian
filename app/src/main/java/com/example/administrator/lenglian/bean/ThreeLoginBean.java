package com.example.administrator.lenglian.bean;

/**
 * Created by Administrator on 2017/10/20.
 */
public class ThreeLoginBean {

    /**
     * msg : 登录成功
     * code : 200
     * datas : {"qq":"","user_name":"","sex":"1","user_address":"","mobile":"15811337458","sign":"","birth":"2010-10-1","head":"14","address_detail":"","password":"$2y$10$i5Kc8c2aQKLdD7yMnbN7ou.ZUCJk04.LPR7Zjhl.r58VQ2JUSnu4m","weixin":"orAEv0v42_LS0jS0xclpE_omduus","balance":"0.00","weibo":"","user_id":"79","last_time":"2017-10-26 00:00:00","nick_name":"张三","reg_time":"2017-09-18 16:20:34","is_del":"1","deposit":"0.00","total_money":"0.00","balance_end_time":"0000-00-00 00:00:00","age":"0","balance_begin_time":"0000-00-00 00:00:00"}
     */
    private String msg;
    private int code;
    private DatasEntity datas;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setDatas(DatasEntity datas) {
        this.datas = datas;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public DatasEntity getDatas() {
        return datas;
    }

    public static class DatasEntity {
        /**
         * qq :
         * user_name :
         * sex : 1
         * user_address :
         * mobile : 15811337458
         * sign :
         * birth : 2010-10-1
         * head : 14
         * address_detail :
         * password : $2y$10$i5Kc8c2aQKLdD7yMnbN7ou.ZUCJk04.LPR7Zjhl.r58VQ2JUSnu4m
         * weixin : orAEv0v42_LS0jS0xclpE_omduus
         * balance : 0.00
         * weibo :
         * user_id : 79
         * last_time : 2017-10-26 00:00:00
         * nick_name : 张三
         * reg_time : 2017-09-18 16:20:34
         * is_del : 1
         * deposit : 0.00
         * total_money : 0.00
         * balance_end_time : 0000-00-00 00:00:00
         * age : 0
         * balance_begin_time : 0000-00-00 00:00:00
         */
        private String qq;
        private String user_name;
        private String sex;
        private String user_address;
        private String mobile;
        private String sign;
        private String birth;
        private String head;
        private String address_detail;
        private String password;
        private String weixin;
        private String balance;
        private String weibo;
        private String user_id;
        private String last_time;
        private String nick_name;
        private String reg_time;
        private String is_del;
        private String deposit;
        private String total_money;
        private String balance_end_time;
        private String age;
        private String balance_begin_time;

        public void setQq(String qq) {
            this.qq = qq;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setUser_address(String user_address) {
            this.user_address = user_address;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public void setAddress_detail(String address_detail) {
            this.address_detail = address_detail;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public void setWeibo(String weibo) {
            this.weibo = weibo;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setLast_time(String last_time) {
            this.last_time = last_time;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public void setReg_time(String reg_time) {
            this.reg_time = reg_time;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public void setDeposit(String deposit) {
            this.deposit = deposit;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public void setBalance_end_time(String balance_end_time) {
            this.balance_end_time = balance_end_time;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public void setBalance_begin_time(String balance_begin_time) {
            this.balance_begin_time = balance_begin_time;
        }

        public String getQq() {
            return qq;
        }

        public String getUser_name() {
            return user_name;
        }

        public String getSex() {
            return sex;
        }

        public String getUser_address() {
            return user_address;
        }

        public String getMobile() {
            return mobile;
        }

        public String getSign() {
            return sign;
        }

        public String getBirth() {
            return birth;
        }

        public String getHead() {
            return head;
        }

        public String getAddress_detail() {
            return address_detail;
        }

        public String getPassword() {
            return password;
        }

        public String getWeixin() {
            return weixin;
        }

        public String getBalance() {
            return balance;
        }

        public String getWeibo() {
            return weibo;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getLast_time() {
            return last_time;
        }

        public String getNick_name() {
            return nick_name;
        }

        public String getReg_time() {
            return reg_time;
        }

        public String getIs_del() {
            return is_del;
        }

        public String getDeposit() {
            return deposit;
        }

        public String getTotal_money() {
            return total_money;
        }

        public String getBalance_end_time() {
            return balance_end_time;
        }

        public String getAge() {
            return age;
        }

        public String getBalance_begin_time() {
            return balance_begin_time;
        }
    }
}
