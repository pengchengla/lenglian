package com.example.administrator.lenglian.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/12.
 */

public class GoodDetailBean {


    /**
     * code : 200
     * success : success
     * datas : {"pro_temperature":"10","pro_model":"","price_introduce":"","pro_list":"","cool_type":"15225","pro_machine":"","pro_in_size":"","pro_color":"","power_introduce":"","class_name":"1冰柜","control_type":"5123","sub_title":"广泛的是第三方","collect_id":"11","profile_pic":"/cfc/uploads/file1/20170912/59b78365b13b4.png","pro_type":"1","pro_deposit":"5000.00","pro_class":"1","pro_cold":"","express_money":"50.00","sale_price":"0.10","pro_out_size":"","pro_rent":"","pro_price":"2","pro_pic":[{"url":"/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"pro_id":"2","pro_volume":"","pro_weight":"","pro_brand":"","pro_power":"000","collect":1,"main_title":"啊啊啊啊啊","pro_name":"fdsafdg","pro_status":"1"}
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
         * pro_temperature : 10
         * pro_model :
         * price_introduce :
         * pro_list :
         * cool_type : 15225
         * pro_machine :
         * pro_in_size :
         * pro_color :
         * power_introduce :
         * class_name : 1冰柜
         * control_type : 5123
         * sub_title : 广泛的是第三方
         * collect_id : 11
         * profile_pic : /cfc/uploads/file1/20170912/59b78365b13b4.png
         * pro_type : 1
         * pro_deposit : 5000.00
         * pro_class : 1
         * pro_cold :
         * express_money : 50.00
         * sale_price : 0.10
         * pro_out_size :
         * pro_rent :
         * pro_price : 2
         * pro_pic : [{"url":"/cfc/uploads/file1/20170912/59b78365b13b4.png"}]
         * pro_id : 2
         * pro_volume :
         * pro_weight :
         * pro_brand :
         * pro_power : 000
         * collect : 1
         * main_title : 啊啊啊啊啊
         * pro_name : fdsafdg
         * pro_status : 1
         */
        private String pro_temperature;
        private String pro_model;
        private String price_introduce;
        private String pro_list;
        private String cool_type;
        private String pro_machine;
        private String pro_in_size;
        private String pro_color;
        private String power_introduce;
        private String class_name;
        private String control_type;
        private String sub_title;
        private String collect_id;
        private String profile_pic;
        private String pro_type;
        private String pro_deposit;
        private String pro_class;
        private String pro_cold;
        private String express_money;
        private String sale_price;
        private String pro_out_size;
        private String pro_rent;
        private String pro_price;
        private List<ProPicEntity> pro_pic;
        private String pro_id;
        private String pro_volume;
        private String pro_weight;
        private String pro_brand;
        private String pro_power;
        private int collect;
        private String main_title;
        private String pro_name;
        private String pro_status;

        public void setPro_temperature(String pro_temperature) {
            this.pro_temperature = pro_temperature;
        }

        public void setPro_model(String pro_model) {
            this.pro_model = pro_model;
        }

        public void setPrice_introduce(String price_introduce) {
            this.price_introduce = price_introduce;
        }

        public void setPro_list(String pro_list) {
            this.pro_list = pro_list;
        }

        public void setCool_type(String cool_type) {
            this.cool_type = cool_type;
        }

        public void setPro_machine(String pro_machine) {
            this.pro_machine = pro_machine;
        }

        public void setPro_in_size(String pro_in_size) {
            this.pro_in_size = pro_in_size;
        }

        public void setPro_color(String pro_color) {
            this.pro_color = pro_color;
        }

        public void setPower_introduce(String power_introduce) {
            this.power_introduce = power_introduce;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public void setControl_type(String control_type) {
            this.control_type = control_type;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public void setCollect_id(String collect_id) {
            this.collect_id = collect_id;
        }

        public void setProfile_pic(String profile_pic) {
            this.profile_pic = profile_pic;
        }

        public void setPro_type(String pro_type) {
            this.pro_type = pro_type;
        }

        public void setPro_deposit(String pro_deposit) {
            this.pro_deposit = pro_deposit;
        }

        public void setPro_class(String pro_class) {
            this.pro_class = pro_class;
        }

        public void setPro_cold(String pro_cold) {
            this.pro_cold = pro_cold;
        }

        public void setExpress_money(String express_money) {
            this.express_money = express_money;
        }

        public void setSale_price(String sale_price) {
            this.sale_price = sale_price;
        }

        public void setPro_out_size(String pro_out_size) {
            this.pro_out_size = pro_out_size;
        }

        public void setPro_rent(String pro_rent) {
            this.pro_rent = pro_rent;
        }

        public void setPro_price(String pro_price) {
            this.pro_price = pro_price;
        }

        public void setPro_pic(List<ProPicEntity> pro_pic) {
            this.pro_pic = pro_pic;
        }

        public void setPro_id(String pro_id) {
            this.pro_id = pro_id;
        }

        public void setPro_volume(String pro_volume) {
            this.pro_volume = pro_volume;
        }

        public void setPro_weight(String pro_weight) {
            this.pro_weight = pro_weight;
        }

        public void setPro_brand(String pro_brand) {
            this.pro_brand = pro_brand;
        }

        public void setPro_power(String pro_power) {
            this.pro_power = pro_power;
        }

        public void setCollect(int collect) {
            this.collect = collect;
        }

        public void setMain_title(String main_title) {
            this.main_title = main_title;
        }

        public void setPro_name(String pro_name) {
            this.pro_name = pro_name;
        }

        public void setPro_status(String pro_status) {
            this.pro_status = pro_status;
        }

        public String getPro_temperature() {
            return pro_temperature;
        }

        public String getPro_model() {
            return pro_model;
        }

        public String getPrice_introduce() {
            return price_introduce;
        }

        public String getPro_list() {
            return pro_list;
        }

        public String getCool_type() {
            return cool_type;
        }

        public String getPro_machine() {
            return pro_machine;
        }

        public String getPro_in_size() {
            return pro_in_size;
        }

        public String getPro_color() {
            return pro_color;
        }

        public String getPower_introduce() {
            return power_introduce;
        }

        public String getClass_name() {
            return class_name;
        }

        public String getControl_type() {
            return control_type;
        }

        public String getSub_title() {
            return sub_title;
        }

        public String getCollect_id() {
            return collect_id;
        }

        public String getProfile_pic() {
            return profile_pic;
        }

        public String getPro_type() {
            return pro_type;
        }

        public String getPro_deposit() {
            return pro_deposit;
        }

        public String getPro_class() {
            return pro_class;
        }

        public String getPro_cold() {
            return pro_cold;
        }

        public String getExpress_money() {
            return express_money;
        }

        public String getSale_price() {
            return sale_price;
        }

        public String getPro_out_size() {
            return pro_out_size;
        }

        public String getPro_rent() {
            return pro_rent;
        }

        public String getPro_price() {
            return pro_price;
        }

        public List<ProPicEntity> getPro_pic() {
            return pro_pic;
        }

        public String getPro_id() {
            return pro_id;
        }

        public String getPro_volume() {
            return pro_volume;
        }

        public String getPro_weight() {
            return pro_weight;
        }

        public String getPro_brand() {
            return pro_brand;
        }

        public String getPro_power() {
            return pro_power;
        }

        public int getCollect() {
            return collect;
        }

        public String getMain_title() {
            return main_title;
        }

        public String getPro_name() {
            return pro_name;
        }

        public String getPro_status() {
            return pro_status;
        }

        public static class ProPicEntity {
            /**
             * url : /cfc/uploads/file1/20170912/59b78365b13b4.png
             */
            private String url;

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUrl() {
                return url;
            }
        }
    }
}
