package com.example.administrator.lenglian.blue;

/**
 * Created by Administrator on 2017/10/31.
 */
public class BlueTimeBean {


    /**
     * code : 200
     * success : success
     * datas : {"end_time":"1712011538","current_time":"1710311538"}
     */
    private int code;
    private String success;
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
         * end_time : 1712011538
         * current_time : 1710311538
         */
        private String end_time;
        private String current_time;

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public void setCurrent_time(String current_time) {
            this.current_time = current_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public String getCurrent_time() {
            return current_time;
        }
    }
}
