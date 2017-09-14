package com.example.administrator.lenglian.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */

public class ChangXiaoBean
{

    /**
     * code : 200
     * success : success
     * datas : [{"note":"1","pro_price":"1","pro_pic":[{"url":"1"}],"sub_title":"广泛的是第三方","pro_id":"1","main_title":"啊啊啊啊啊","pro_name":"fdsafdg"},{"note":"222","pro_price":"2","pro_pic":[{"url":"1"}],"sub_title":"广泛的是第三方","pro_id":"2","main_title":"啊啊啊啊啊","pro_name":"fdsafdg"}]
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
         * note : 1
         * pro_price : 1
         * pro_pic : [{"url":"1"}]
         * sub_title : 广泛的是第三方
         * pro_id : 1
         * main_title : 啊啊啊啊啊
         * pro_name : fdsafdg
         */
        private String note;
        private String pro_price;
        private List<ProPicEntity> pro_pic;
        private String sub_title;
        private String pro_id;
        private String main_title;
        private String pro_name;

        public void setNote(String note) {
            this.note = note;
        }

        public void setPro_price(String pro_price) {
            this.pro_price = pro_price;
        }

        public void setPro_pic(List<ProPicEntity> pro_pic) {
            this.pro_pic = pro_pic;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public void setPro_id(String pro_id) {
            this.pro_id = pro_id;
        }

        public void setMain_title(String main_title) {
            this.main_title = main_title;
        }

        public void setPro_name(String pro_name) {
            this.pro_name = pro_name;
        }

        public String getNote() {
            return note;
        }

        public String getPro_price() {
            return pro_price;
        }

        public List<ProPicEntity> getPro_pic() {
            return pro_pic;
        }

        public String getSub_title() {
            return sub_title;
        }

        public String getPro_id() {
            return pro_id;
        }

        public String getMain_title() {
            return main_title;
        }

        public String getPro_name() {
            return pro_name;
        }

        public static class ProPicEntity {
            /**
             * url : 1
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
