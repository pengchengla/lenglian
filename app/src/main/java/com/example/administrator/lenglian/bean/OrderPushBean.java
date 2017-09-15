package com.example.administrator.lenglian.bean;

/**
 * Created by Administrator on 2017/9/14.
 */

public class OrderPushBean {

    /**
     * code : 200
     * success : success
     * datas : {"order_id":"14"}
     */
    private int code;
    private String success;
    private String error;
    private DatasEntity datas;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

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
         * order_id : 14
         */
        private String order_id;

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_id() {
            return order_id;
        }
    }
}
