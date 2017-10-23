package com.example.administrator.lenglian.bean;

/**
 * Created by Administrator on 2017/9/8.
 */

public class RegisterBean {


    /**
     * code : 1
     * success : 注册成功
     * datas : {"user_id":"63"}
     */

    private int code;
    private String success;
    private String error;
    private DatasBean datas;

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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * user_id : 63
         */

        private String user_id;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
