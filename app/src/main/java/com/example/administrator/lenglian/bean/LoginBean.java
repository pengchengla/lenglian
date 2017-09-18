package com.example.administrator.lenglian.bean;

/**
 * Created by Administrator on 2017/9/11.
 */

public class LoginBean
{
    /**
     * code : 1
     * success : 登录成功
     * datas : {"user_id":"71"}
     */
    private int code;
    private String success;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    private DatasEntity datas;

    public void setCode(int code) {
        this.code = code;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public void setDatas(DatasEntity datas) {
        this.datas = datas;
    }

    public int getCode() {
        return code;
    }

    public String getSuccess() {
        return success;
    }

    public DatasEntity getDatas() {
        return datas;
    }

    public static class DatasEntity {
        /**
         * user_id : 71
         */
        private String user_id;

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_id() {
            return user_id;
        }
    }
}
