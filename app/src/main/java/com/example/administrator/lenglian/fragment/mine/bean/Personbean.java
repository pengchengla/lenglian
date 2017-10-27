package com.example.administrator.lenglian.fragment.mine.bean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Personbean {


    /**
     * code : 200
     * success : success
     * datas : [{"head":"http://59.110.213.63/cfc/uploads/file1/20171019/59e846bcf170f.jpg","nick_name":"胡","sign":"股份返回韩国发生大","user_name":"看看","sex":"2017","birth":"2017年10月30日","mobile":"18310482720"}]
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
         * head : http://59.110.213.63/cfc/uploads/file1/20171019/59e846bcf170f.jpg
         * nick_name : 胡
         * sign : 股份返回韩国发生大
         * user_name : 看看
         * sex : 2017
         * birth : 2017年10月30日
         * mobile : 18310482720
         */

        private String head;
        private String nick_name;
        private String sign;
        private String user_name;
        private String sex;
        private String birth;
        private String mobile;

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
