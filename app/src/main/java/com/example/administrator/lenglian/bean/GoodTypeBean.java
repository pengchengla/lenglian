package com.example.administrator.lenglian.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */

public class GoodTypeBean {


    /**
     * code : 200
     * success : success
     * datas : [{"class_id":"1","class_name":"1冰柜"},{"class_id":"2","class_name":"2冰柜"},{"class_id":"3","class_name":"3冰柜"},{"class_id":"4","class_name":"4冰柜"},{"class_id":"5","class_name":"5冰柜"},{"class_id":"6","class_name":"6冰柜"},{"class_id":"7","class_name":"7冰柜"},{"class_id":"8","class_name":"8冰柜"},{"class_id":"9","class_name":"9冰柜"},{"class_id":"10","class_name":"10冰柜"},{"class_id":"11","class_name":"11冰柜"},{"class_id":"12","class_name":"12冰柜"}]
     */
    private int code;
    private String success;
    private List<DatasEntity> datas;

    public void setCode(int code) {
        this.code = code;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public void setDatas(List<DatasEntity> datas) {
        this.datas = datas;
    }

    public int getCode() {
        return code;
    }

    public String getSuccess() {
        return success;
    }

    public List<DatasEntity> getDatas() {
        return datas;
    }

    public static class DatasEntity {
        /**
         * class_id : 1
         * class_name : 1冰柜
         */
        private String class_id;
        private String class_name;
        private boolean isChecked;//加的

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public void setClass_id(String class_id) {
            this.class_id = class_id;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public String getClass_id() {
            return class_id;
        }

        public String getClass_name() {
            return class_name;
        }
    }
}
