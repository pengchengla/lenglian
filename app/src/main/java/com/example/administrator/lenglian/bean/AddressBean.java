package com.example.administrator.lenglian.bean;


import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class AddressBean {


    /**
     * code : 200
     * success : success
     * datas : [{"address_detail":"老六","express_id":"98","user_id":"79","receive_name":"弄","mobile":"15811337458","is_del":"0","area_id":"北京市北京市东城区","is_default":"1"}]
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
         * address_detail : 老六
         * express_id : 98
         * user_id : 79
         * receive_name : 弄
         * mobile : 15811337458
         * is_del : 0
         * area_id : 北京市北京市东城区
         * is_default : 1
         */
        private String address_detail;
        private String express_id;
        private String user_id;
        private String receive_name;
        private String mobile;
        private String is_del;
        private String area_id;
        private String is_default;

        public void setAddress_detail(String address_detail) {
            this.address_detail = address_detail;
        }

        public void setExpress_id(String express_id) {
            this.express_id = express_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setReceive_name(String receive_name) {
            this.receive_name = receive_name;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getAddress_detail() {
            return address_detail;
        }

        public String getExpress_id() {
            return express_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getReceive_name() {
            return receive_name;
        }

        public String getMobile() {
            return mobile;
        }

        public String getIs_del() {
            return is_del;
        }

        public String getArea_id() {
            return area_id;
        }

        public String getIs_default() {
            return is_default;
        }
    }
}
