package com.example.administrator.lenglian.bean;

/**
 * Created by Administrator on 2017/9/8.
 */

public class RegisterBean {

    /**
     * msg : success
     * code : 200
     * datas : {"user_id":110}
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
         * user_id : 110
         */
        private int user_id;

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getUser_id() {
            return user_id;
        }
    }
}
