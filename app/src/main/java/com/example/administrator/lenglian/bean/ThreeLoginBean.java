package com.example.administrator.lenglian.bean;

/**
 * Created by Administrator on 2017/10/20.
 */
public class ThreeLoginBean {

    /**
     * msg : 成功
     * code : 200
     * data : {"user_id":"1","mobile":"13366669999"}
     */
    private String msg;
    private int code;
    private DataEntity data;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * user_id : 1
         * mobile : 13366669999
         */
        private String user_id;
        private String mobile;

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getMobile() {
            return mobile;
        }
    }
}
