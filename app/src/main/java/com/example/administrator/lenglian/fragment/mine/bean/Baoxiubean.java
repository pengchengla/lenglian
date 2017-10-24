package com.example.administrator.lenglian.fragment.mine.bean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Baoxiubean {


    /**
     * code : 200
     * success : success
     * datas : [{"repair_id":"1","pro_id":"1","order_id":"10","is_comment":"0","comment_id":"0","pro_pic":[{"url":"http://59.110.213.63/cfc/uploads/file1/20171018/59e6f70d5a22d.jpg"}],"main_title":"威王","pro_price":"1","sub_title":"点菜柜","pro_name":"fdsafdg","single_pic":"http://59.110.213.63/cfc/uploads/file1/20170912/59b78365b13b4.png","pro_deposit":"5000.00","express_money":"50.00"},{"repair_id":"2","pro_id":"1","order_id":"1","is_comment":"0","comment_id":"0","pro_pic":[{"url":"http://59.110.213.63/cfc/uploads/file1/20171018/59e6f70d5a22d.jpg"}],"main_title":"威王","pro_price":"1","sub_title":"点菜柜","pro_name":"fdsafdg","single_pic":"http://59.110.213.63/cfc/uploads/file1/20170912/59b78365b13b4.png","pro_deposit":"5000.00","express_money":"50.00"}]
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
         * repair_id : 1
         * pro_id : 1
         * order_id : 10
         * is_comment : 0
         * comment_id : 0
         * pro_pic : [{"url":"http://59.110.213.63/cfc/uploads/file1/20171018/59e6f70d5a22d.jpg"}]
         * main_title : 威王
         * pro_price : 1
         * sub_title : 点菜柜
         * pro_name : fdsafdg
         * single_pic : http://59.110.213.63/cfc/uploads/file1/20170912/59b78365b13b4.png
         * pro_deposit : 5000.00
         * express_money : 50.00
         */

        private String repair_id;
        private String pro_id;
        private String order_id;
        private String is_comment;
        private String comment_id;
        private String main_title;
        private String pro_price;
        private String sub_title;
        private String pro_name;
        private String single_pic;
        private String pro_deposit;
        private String express_money;
        private List<ProPicBean> pro_pic;

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

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(String is_comment) {
            this.is_comment = is_comment;
        }

        public String getComment_id() {
            return comment_id;
        }

        public void setComment_id(String comment_id) {
            this.comment_id = comment_id;
        }

        public String getMain_title() {
            return main_title;
        }

        public void setMain_title(String main_title) {
            this.main_title = main_title;
        }

        public String getPro_price() {
            return pro_price;
        }

        public void setPro_price(String pro_price) {
            this.pro_price = pro_price;
        }

        public String getSub_title() {
            return sub_title;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public String getPro_name() {
            return pro_name;
        }

        public void setPro_name(String pro_name) {
            this.pro_name = pro_name;
        }

        public String getSingle_pic() {
            return single_pic;
        }

        public void setSingle_pic(String single_pic) {
            this.single_pic = single_pic;
        }

        public String getPro_deposit() {
            return pro_deposit;
        }

        public void setPro_deposit(String pro_deposit) {
            this.pro_deposit = pro_deposit;
        }

        public String getExpress_money() {
            return express_money;
        }

        public void setExpress_money(String express_money) {
            this.express_money = express_money;
        }

        public List<ProPicBean> getPro_pic() {
            return pro_pic;
        }

        public void setPro_pic(List<ProPicBean> pro_pic) {
            this.pro_pic = pro_pic;
        }

        public static class ProPicBean {
            /**
             * url : http://59.110.213.63/cfc/uploads/file1/20171018/59e6f70d5a22d.jpg
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
