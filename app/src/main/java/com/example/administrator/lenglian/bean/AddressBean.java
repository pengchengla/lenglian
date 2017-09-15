package com.example.administrator.lenglian.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class AddressBean {

    /**
     * msg : success
     * code : 200
     * datas : [{"address_detail":"详细地址","express_id":"28","recieve_name":"aaa","address":"bbb","mobile":"18888888888","is_default":"1"}]
     */
    private String msg;
    private int code;
    private List<DatasEntity> datas;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setDatas(List<DatasEntity> datas) {
        this.datas = datas;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public List<DatasEntity> getDatas() {
        return datas;
    }

    public static class DatasEntity {
        /**
         * address_detail : 详细地址
         * express_id : 28
         * recieve_name : aaa
         * address : bbb
         * mobile : 18888888888
         * is_default : 1
         */
        private String address_detail;
        private String express_id;
        private String recieve_name;
        private String address;
        private String mobile;
        private String is_default;

        public void setAddress_detail(String address_detail) {
            this.address_detail = address_detail;
        }

        public void setExpress_id(String express_id) {
            this.express_id = express_id;
        }

        public void setRecieve_name(String recieve_name) {
            this.recieve_name = recieve_name;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
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

        public String getRecieve_name() {
            return recieve_name;
        }

        public String getAddress() {
            return address;
        }

        public String getMobile() {
            return mobile;
        }

        public String getIs_default() {
            return is_default;
        }
    }
}
