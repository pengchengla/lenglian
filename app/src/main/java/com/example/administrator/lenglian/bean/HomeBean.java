package com.example.administrator.lenglian.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */

public class HomeBean {


    /**
     * code : 200
     * datas : {"sale":[{"note":"1","pro_price":"3","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"3","sale_price":"0.60","main_title":"啊啊啊啊啊"},{"note":"123","pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","sale_price":"0.10","main_title":"啊啊啊啊啊"},{"note":"123","pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","sale_price":"0.10","main_title":"啊啊啊啊啊"}],"banner":[{"banner_address":"http://www.baidu.com","url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"},{"banner_address":"http://www.baidu.com","url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"},{"banner_address":"http://www.baidu.com","url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"best":[{"note":"1","pro_price":"1","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"1","main_title":"啊啊啊啊啊"},{"note":"222","pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","main_title":"啊啊啊啊啊"},{"note":"222","pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","main_title":"啊啊啊啊啊"},{"note":"222","pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","main_title":"啊啊啊啊啊"}],"pro_class":[{"class_id":"1","class_name":"1冰柜","url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"},{"class_id":"2","class_name":"2冰柜","url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"},{"class_id":"3","class_name":"3冰柜","url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"},{"class_id":"4","class_name":"4冰柜","url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"recommend":[{"note":"1213","pro_price":"1","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"1","main_title":"啊啊啊啊啊"},{"note":"1213","pro_price":"1","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"1","main_title":"啊啊啊啊啊"},{"note":"1213","pro_price":"1","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"1","main_title":"啊啊啊啊啊"},{"note":"1213","pro_price":"1","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"1","main_title":"啊啊啊啊啊"}]}
     * success : success
     */
    private int code;
    private DatasEntity datas;
    private String success;

    public void setCode(int code) {
        this.code = code;
    }

    public void setDatas(DatasEntity datas) {
        this.datas = datas;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public DatasEntity getDatas() {
        return datas;
    }

    public String getSuccess() {
        return success;
    }

    public static class DatasEntity {
        /**
         * sale : [{"note":"1","pro_price":"3","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"3","sale_price":"0.60","main_title":"啊啊啊啊啊"},{"note":"123","pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","sale_price":"0.10","main_title":"啊啊啊啊啊"},{"note":"123","pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","sale_price":"0.10","main_title":"啊啊啊啊啊"}]
         * banner : [{"banner_address":"http://www.baidu.com","url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"},{"banner_address":"http://www.baidu.com","url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"},{"banner_address":"http://www.baidu.com","url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}]
         * best : [{"note":"1","pro_price":"1","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"1","main_title":"啊啊啊啊啊"},{"note":"222","pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","main_title":"啊啊啊啊啊"},{"note":"222","pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","main_title":"啊啊啊啊啊"},{"note":"222","pro_price":"2","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"2","main_title":"啊啊啊啊啊"}]
         * pro_class : [{"class_id":"1","class_name":"1冰柜","url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"},{"class_id":"2","class_name":"2冰柜","url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"},{"class_id":"3","class_name":"3冰柜","url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"},{"class_id":"4","class_name":"4冰柜","url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}]
         * recommend : [{"note":"1213","pro_price":"1","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"1","main_title":"啊啊啊啊啊"},{"note":"1213","pro_price":"1","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"1","main_title":"啊啊啊啊啊"},{"note":"1213","pro_price":"1","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"1","main_title":"啊啊啊啊啊"},{"note":"1213","pro_price":"1","pro_pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}],"sub_title":"广泛的是第三方","pro_id":"1","main_title":"啊啊啊啊啊"}]
         */
        private List<SaleEntity> sale;
        private List<BannerEntity> banner;
        private List<BestEntity> best;
        private List<ProClassEntity> pro_class;
        private List<RecommendEntity> recommend;

        public void setSale(List<SaleEntity> sale) {
            this.sale = sale;
        }

        public void setBanner(List<BannerEntity> banner) {
            this.banner = banner;
        }

        public void setBest(List<BestEntity> best) {
            this.best = best;
        }

        public void setPro_class(List<ProClassEntity> pro_class) {
            this.pro_class = pro_class;
        }

        public void setRecommend(List<RecommendEntity> recommend) {
            this.recommend = recommend;
        }

        public List<SaleEntity> getSale() {
            return sale;
        }

        public List<BannerEntity> getBanner() {
            return banner;
        }

        public List<BestEntity> getBest() {
            return best;
        }

        public List<ProClassEntity> getPro_class() {
            return pro_class;
        }

        public List<RecommendEntity> getRecommend() {
            return recommend;
        }

        public static class SaleEntity {
            /**
             * note : 1
             * pro_price : 3
             * pro_pic : [{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}]
             * sub_title : 广泛的是第三方
             * pro_id : 3
             * sale_price : 0.60
             * main_title : 啊啊啊啊啊
             */
            private String note;
            private String pro_price;
            private List<ProPicEntity> pro_pic;
            private String sub_title;
            private String pro_id;
            private String sale_price;
            private String main_title;

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

            public void setSale_price(String sale_price) {
                this.sale_price = sale_price;
            }

            public void setMain_title(String main_title) {
                this.main_title = main_title;
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

            public String getSale_price() {
                return sale_price;
            }

            public String getMain_title() {
                return main_title;
            }

            public static class ProPicEntity {
                /**
                 * url : http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png
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

        public static class BannerEntity {
            /**
             * banner_address : http://www.baidu.com
             * url : http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png
             */
            private String banner_address;
            private String url;

            public void setBanner_address(String banner_address) {
                this.banner_address = banner_address;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getBanner_address() {
                return banner_address;
            }

            public String getUrl() {
                return url;
            }
        }

        public static class BestEntity {
            /**
             * note : 1
             * pro_price : 1
             * pro_pic : [{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}]
             * sub_title : 广泛的是第三方
             * pro_id : 1
             * main_title : 啊啊啊啊啊
             */
            private String note;
            private String pro_price;
            private List<ProPicEntity> pro_pic;
            private String sub_title;
            private String pro_id;
            private String main_title;

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

            public static class ProPicEntity {
                /**
                 * url : http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png
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

        public static class ProClassEntity {
            /**
             * class_id : 1
             * class_name : 1冰柜
             * url : http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png
             */
            private String class_id;
            private String class_name;
            private String url;

            public void setClass_id(String class_id) {
                this.class_id = class_id;
            }

            public void setClass_name(String class_name) {
                this.class_name = class_name;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getClass_id() {
                return class_id;
            }

            public String getClass_name() {
                return class_name;
            }

            public String getUrl() {
                return url;
            }
        }

        public static class RecommendEntity {
            /**
             * note : 1213
             * pro_price : 1
             * pro_pic : [{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}]
             * sub_title : 广泛的是第三方
             * pro_id : 1
             * main_title : 啊啊啊啊啊
             */
            private String note;
            private String pro_price;
            private List<ProPicEntity> pro_pic;
            private String sub_title;
            private String pro_id;
            private String main_title;

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

            public static class ProPicEntity {
                /**
                 * url : http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png
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
}
