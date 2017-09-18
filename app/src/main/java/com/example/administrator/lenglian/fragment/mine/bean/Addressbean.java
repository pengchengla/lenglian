package com.example.administrator.lenglian.fragment.mine.bean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Addressbean {

    /**
     * code : 200
     * success : success
     * datas : [{"express_id":"1","user_id":"76","receive_name":"","mobile":"","area_id":"0","address_detail":"","is_default":"2"},{"express_id":"2","user_id":"76","receive_name":"","mobile":"18310482720","area_id":"0","address_detail":"胡","is_default":"2"},{"express_id":"3","user_id":"76","receive_name":"","mobile":"18863894005","area_id":"0","address_detail":"胡","is_default":"2"},{"express_id":"4","user_id":"76","receive_name":"","mobile":"18310482730","area_id":"0","address_detail":"骨头","is_default":"1"},{"express_id":"5","user_id":"76","receive_name":"","mobile":"18863894005","area_id":"0","address_detail":"胡好","is_default":"2"}]
     */

    private int code;
    private String success;
    private List<DatasBean> datas;

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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * express_id : 1
         * user_id : 76
         * receive_name :
         * mobile :
         * area_id : 0
         * address_detail :
         * is_default : 2
         */

        private String express_id;
        private String user_id;
        private String receive_name;
        private String mobile;
        private String area_id;
        private String address_detail;
        private String is_default;

        public String getExpress_id() {
            return express_id;
        }

        public void setExpress_id(String express_id) {
            this.express_id = express_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getReceive_name() {
            return receive_name;
        }

        public void setReceive_name(String receive_name) {
            this.receive_name = receive_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getAddress_detail() {
            return address_detail;
        }

        public void setAddress_detail(String address_detail) {
            this.address_detail = address_detail;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }
    }
}
