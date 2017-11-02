package com.example.administrator.lenglian.blue;

/**
 * Created by Administrator on 2017/10/31.
 */
public class BlueTimeBean {


    /**
     * code : 200
     * success : success
     * datas : {"end_time":"1712021811","return_end_time":"2017-12-02 18:11","return_current_time":"2017-11-02 18:11","current_time":"1711021811","mac":""}
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
         * end_time : 1712021811
         * return_end_time : 2017-12-02 18:11
         * return_current_time : 2017-11-02 18:11
         * current_time : 1711021811
         * mac :
         */
        private String end_time;
        private String return_end_time;
        private String return_current_time;
        private String current_time;
        private String mac;

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public void setReturn_end_time(String return_end_time) {
            this.return_end_time = return_end_time;
        }

        public void setReturn_current_time(String return_current_time) {
            this.return_current_time = return_current_time;
        }

        public void setCurrent_time(String current_time) {
            this.current_time = current_time;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public String getEnd_time() {
            return end_time;
        }

        public String getReturn_end_time() {
            return return_end_time;
        }

        public String getReturn_current_time() {
            return return_current_time;
        }

        public String getCurrent_time() {
            return current_time;
        }

        public String getMac() {
            return mac;
        }
    }
}
