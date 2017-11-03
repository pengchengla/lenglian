package com.example.administrator.lenglian.fragment.mine.bean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Gengxinbean {


    /**
     * code : 200
     * success : success
     * datas : {"iOS":"1.0","Android":"1.0"}
     */

    private int code;
    private String success;
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

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * iOS : 1.0
         * Android : 1.0
         */

        private String iOS;
        private String Android;

        public String getIOS() {
            return iOS;
        }

        public void setIOS(String iOS) {
            this.iOS = iOS;
        }

        public String getAndroid() {
            return Android;
        }

        public void setAndroid(String Android) {
            this.Android = Android;
        }
    }
}
