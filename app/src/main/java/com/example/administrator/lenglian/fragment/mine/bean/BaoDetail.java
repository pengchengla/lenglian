package com.example.administrator.lenglian.fragment.mine.bean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class BaoDetail {

    /**
     * code : 200
     * msg : success
     * datas : {"repair_id":"10","pro_id":"20","pro_pic":"url","contact_name":"Frank","repair_address":"aaa","contact_mobile":"18888888888","repair_note":"abbbsss"}
     */

    private int code;
    private String msg;
    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * repair_id : 10
         * pro_id : 20
         * pro_pic : url
         * contact_name : Frank
         * repair_address : aaa
         * contact_mobile : 18888888888
         * repair_note : abbbsss
         */

        private String repair_id;
        private String pro_id;
        private String pro_pic;
        private String contact_name;
        private String repair_address;
        private String contact_mobile;
        private String repair_note;

        public String getRepair_id() {
            return repair_id;
        }

        public void setRepair_id(String repair_id) {
            this.repair_id = repair_id;
        }

        public String getPro_id() {
            return pro_id;
        }

        public void setPro_id(String pro_id) {
            this.pro_id = pro_id;
        }

        public String getPro_pic() {
            return pro_pic;
        }

        public void setPro_pic(String pro_pic) {
            this.pro_pic = pro_pic;
        }

        public String getContact_name() {
            return contact_name;
        }

        public void setContact_name(String contact_name) {
            this.contact_name = contact_name;
        }

        public String getRepair_address() {
            return repair_address;
        }

        public void setRepair_address(String repair_address) {
            this.repair_address = repair_address;
        }

        public String getContact_mobile() {
            return contact_mobile;
        }

        public void setContact_mobile(String contact_mobile) {
            this.contact_mobile = contact_mobile;
        }

        public String getRepair_note() {
            return repair_note;
        }

        public void setRepair_note(String repair_note) {
            this.repair_note = repair_note;
        }
    }
}
