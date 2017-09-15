package com.example.administrator.lenglian.bean;

/**
 * Created by Administrator on 2017/9/14.
 */

public class EditCollectBean {

    /**
     * code : 200
     * success : success
     * datas : {"collect_id":"49"}
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
         * collect_id : 49
         */
        private String collect_id;

        public void setCollect_id(String collect_id) {
            this.collect_id = collect_id;
        }

        public String getCollect_id() {
            return collect_id;
        }
    }
}
