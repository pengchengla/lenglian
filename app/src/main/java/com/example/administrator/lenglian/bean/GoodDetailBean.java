package com.example.administrator.lenglian.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/12.
 */

public class GoodDetailBean {

    /**
     * code : 200
     * success : success
     * datas : {"repair_fee":"","express_fee":"","pro_temperature":"0～10℃或≤-18℃","recommend":[{"note":"1","pro_price":"130","pro_pic":[{"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f00b958603b.jpg"}],"sub_title":"超市组合岛柜","pro_id":"31","single_pic":"http://59.110.213.63/cfc/uploads/file1/20171025/59f00b958603b.jpg","pro_deposit":"3400.00","express_money":"120.00","main_title":"CFC超市组合岛柜","pro_name":"超市组合岛柜"},{"note":"哈哈哈","pro_price":"110","pro_pic":[{"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f02a824f633.jpg"}],"sub_title":"多媒体展示柜","pro_id":"41","single_pic":"http://59.110.213.63/cfc/uploads/file1/20171025/59f02a824f633.jpg","pro_deposit":"3000.00","express_money":"120.00","main_title":"CFC多媒体展示柜系列","pro_name":"多媒体展示柜"}],"pro_model":"SR/SF-1086","price_introduce":"租费","pro_list":"说明书保修卡x1 整机x1","cool_type":"直冷","pro_machine":"QD110H*2","pro_in_size":"2304*804*610mm","pro_color":"白的","power_introduce":"一级能效","class_name":"深底前透明推拉门岛柜","control_type":"机械控温","tech":[{"value":"白的","key":"商品颜色"},{"value":"机械控温","key":"控温类型"},{"value":"5.35km.h/24h","key":"能耗参数"},{"value":"CFC冷链","key":"品牌"},{"value":"SR/SF-1086","key":"型号"},{"value":"0～10℃或≤-18℃","key":"温度范围"},{"value":"2304*804*610mm","key":"内部尺寸"},{"value":"2500*1010*845mm","key":"外部尺寸"},{"value":"964升 ","key":"容积"},{"value":"155","key":"体积"},{"value":"QD110H*2","key":"压缩机"},{"value":"R134a/215+195","key":"制冷剂"}],"brokerage":"","sub_title":"深底前透明推拉门岛柜","introduce":[{"value":"说明书保修卡x1 整机x1","key":"包装清单"},{"value":"年租","key":"租赁说明"},{"value":"租费","key":"价格介绍"},{"value":"一级能效","key":"能耗说明"}],"profile_pic":[{"width":750,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f0250ae5bd0.jpg","height":808},{"width":750,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f025113ca13.jpg","height":2500},{"width":750,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f025123420a.jpg","height":1799},{"width":750,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f0252ca37a8.jpg","height":1680},{"width":750,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f0252f14df4.jpg","height":808},{"width":750,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f025337f3eb.jpg","height":2500},{"width":750,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f02534c9a6b.jpg","height":1799}],"pro_type":"0","pro_deposit":"4800.00","single_pic":"http://59.110.213.63/cfc/uploads/file1/20171025/59f0253c73846.jpg","pro_class":"47","control_class":"非智能","pro_cold":"R134a/215+195","express_money":"120.00","sale_price":"0","pro_out_size":"2500*1010*845mm","pro_rent":"年租","pro_price":"220","pro_pic":[{"width":800,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f0253c73846.jpg","height":800},{"width":800,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f0254067392.jpg","height":800},{"width":800,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f0254568415.jpg","height":800},{"width":800,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f02549693db.jpg","height":800},{"width":800,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f0254dcd3d7.jpg","height":800}],"pro_id":"35","pro_volume":"964升 ","pro_weight":"155","pro_brand":"CFC冷链","pro_power":"5.35km.h/24h","collect":0,"main_title":"CFC深底前透明推拉门岛柜系列","pro_name":"深底前透明推拉门岛柜","pro_status":"1"}
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
         * repair_fee :
         * express_fee :
         * pro_temperature : 0～10℃或≤-18℃
         * recommend : [{"note":"1","pro_price":"130","pro_pic":[{"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f00b958603b.jpg"}],"sub_title":"超市组合岛柜","pro_id":"31","single_pic":"http://59.110.213.63/cfc/uploads/file1/20171025/59f00b958603b.jpg","pro_deposit":"3400.00","express_money":"120.00","main_title":"CFC超市组合岛柜","pro_name":"超市组合岛柜"},{"note":"哈哈哈","pro_price":"110","pro_pic":[{"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f02a824f633.jpg"}],"sub_title":"多媒体展示柜","pro_id":"41","single_pic":"http://59.110.213.63/cfc/uploads/file1/20171025/59f02a824f633.jpg","pro_deposit":"3000.00","express_money":"120.00","main_title":"CFC多媒体展示柜系列","pro_name":"多媒体展示柜"}]
         * pro_model : SR/SF-1086
         * price_introduce : 租费
         * pro_list : 说明书保修卡x1 整机x1
         * cool_type : 直冷
         * pro_machine : QD110H*2
         * pro_in_size : 2304*804*610mm
         * pro_color : 白的
         * power_introduce : 一级能效
         * class_name : 深底前透明推拉门岛柜
         * control_type : 机械控温
         * tech : [{"value":"白的","key":"商品颜色"},{"value":"机械控温","key":"控温类型"},{"value":"5.35km.h/24h","key":"能耗参数"},{"value":"CFC冷链","key":"品牌"},{"value":"SR/SF-1086","key":"型号"},{"value":"0～10℃或≤-18℃","key":"温度范围"},{"value":"2304*804*610mm","key":"内部尺寸"},{"value":"2500*1010*845mm","key":"外部尺寸"},{"value":"964升 ","key":"容积"},{"value":"155","key":"体积"},{"value":"QD110H*2","key":"压缩机"},{"value":"R134a/215+195","key":"制冷剂"}]
         * brokerage :
         * sub_title : 深底前透明推拉门岛柜
         * introduce : [{"value":"说明书保修卡x1 整机x1","key":"包装清单"},{"value":"年租","key":"租赁说明"},{"value":"租费","key":"价格介绍"},{"value":"一级能效","key":"能耗说明"}]
         * profile_pic : [{"width":750,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f0250ae5bd0.jpg","height":808},{"width":750,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f025113ca13.jpg","height":2500},{"width":750,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f025123420a.jpg","height":1799},{"width":750,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f0252ca37a8.jpg","height":1680},{"width":750,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f0252f14df4.jpg","height":808},{"width":750,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f025337f3eb.jpg","height":2500},{"width":750,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f02534c9a6b.jpg","height":1799}]
         * pro_type : 0
         * pro_deposit : 4800.00
         * single_pic : http://59.110.213.63/cfc/uploads/file1/20171025/59f0253c73846.jpg
         * pro_class : 47
         * control_class : 非智能
         * pro_cold : R134a/215+195
         * express_money : 120.00
         * sale_price : 0
         * pro_out_size : 2500*1010*845mm
         * pro_rent : 年租
         * pro_price : 220
         * pro_pic : [{"width":800,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f0253c73846.jpg","height":800},{"width":800,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f0254067392.jpg","height":800},{"width":800,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f0254568415.jpg","height":800},{"width":800,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f02549693db.jpg","height":800},{"width":800,"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f0254dcd3d7.jpg","height":800}]
         * pro_id : 35
         * pro_volume : 964升
         * pro_weight : 155
         * pro_brand : CFC冷链
         * pro_power : 5.35km.h/24h
         * collect : 0
         * main_title : CFC深底前透明推拉门岛柜系列
         * pro_name : 深底前透明推拉门岛柜
         * pro_status : 1
         */
        private String shortest;

        public String getShortest() {
            return shortest;
        }

        public void setShortest(String shortest) {
            this.shortest = shortest;
        }

        private String repair_fee;
        private String express_fee;
        private String pro_temperature;
        private List<RecommendEntity> recommend;
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
        private List<TechEntity> tech;
        private String brokerage;
        private String sub_title;
        private List<IntroduceEntity> introduce;
        private List<ProfilePicEntity> profile_pic;
        private String pro_type;
        private String pro_deposit;
        private String single_pic;
        private String pro_class;
        private String control_class;
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

        public void setRepair_fee(String repair_fee) {
            this.repair_fee = repair_fee;
        }

        public void setExpress_fee(String express_fee) {
            this.express_fee = express_fee;
        }

        public void setPro_temperature(String pro_temperature) {
            this.pro_temperature = pro_temperature;
        }

        public void setRecommend(List<RecommendEntity> recommend) {
            this.recommend = recommend;
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

        public void setTech(List<TechEntity> tech) {
            this.tech = tech;
        }

        public void setBrokerage(String brokerage) {
            this.brokerage = brokerage;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public void setIntroduce(List<IntroduceEntity> introduce) {
            this.introduce = introduce;
        }

        public void setProfile_pic(List<ProfilePicEntity> profile_pic) {
            this.profile_pic = profile_pic;
        }

        public void setPro_type(String pro_type) {
            this.pro_type = pro_type;
        }

        public void setPro_deposit(String pro_deposit) {
            this.pro_deposit = pro_deposit;
        }

        public void setSingle_pic(String single_pic) {
            this.single_pic = single_pic;
        }

        public void setPro_class(String pro_class) {
            this.pro_class = pro_class;
        }

        public void setControl_class(String control_class) {
            this.control_class = control_class;
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

        public String getRepair_fee() {
            return repair_fee;
        }

        public String getExpress_fee() {
            return express_fee;
        }

        public String getPro_temperature() {
            return pro_temperature;
        }

        public List<RecommendEntity> getRecommend() {
            return recommend;
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

        public List<TechEntity> getTech() {
            return tech;
        }

        public String getBrokerage() {
            return brokerage;
        }

        public String getSub_title() {
            return sub_title;
        }

        public List<IntroduceEntity> getIntroduce() {
            return introduce;
        }

        public List<ProfilePicEntity> getProfile_pic() {
            return profile_pic;
        }

        public String getPro_type() {
            return pro_type;
        }

        public String getPro_deposit() {
            return pro_deposit;
        }

        public String getSingle_pic() {
            return single_pic;
        }

        public String getPro_class() {
            return pro_class;
        }

        public String getControl_class() {
            return control_class;
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

        public static class RecommendEntity {
            /**
             * note : 1
             * pro_price : 130
             * pro_pic : [{"url":"http://59.110.213.63/cfc/uploads/file1/20171025/59f00b958603b.jpg"}]
             * sub_title : 超市组合岛柜
             * pro_id : 31
             * single_pic : http://59.110.213.63/cfc/uploads/file1/20171025/59f00b958603b.jpg
             * pro_deposit : 3400.00
             * express_money : 120.00
             * main_title : CFC超市组合岛柜
             * pro_name : 超市组合岛柜
             */
            private String note;
            private String pro_price;
            private List<ProPicEntity> pro_pic;
            private String sub_title;
            private String pro_id;
            private String single_pic;
            private String pro_deposit;
            private String express_money;
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

            public void setSingle_pic(String single_pic) {
                this.single_pic = single_pic;
            }

            public void setPro_deposit(String pro_deposit) {
                this.pro_deposit = pro_deposit;
            }

            public void setExpress_money(String express_money) {
                this.express_money = express_money;
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

            public String getSingle_pic() {
                return single_pic;
            }

            public String getPro_deposit() {
                return pro_deposit;
            }

            public String getExpress_money() {
                return express_money;
            }

            public String getMain_title() {
                return main_title;
            }

            public String getPro_name() {
                return pro_name;
            }

            public static class ProPicEntity {
                /**
                 * url : http://59.110.213.63/cfc/uploads/file1/20171025/59f00b958603b.jpg
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

        public static class TechEntity {
            /**
             * value : 白的
             * key : 商品颜色
             */
            private String value;
            private String key;

            public void setValue(String value) {
                this.value = value;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public String getKey() {
                return key;
            }
        }

        public static class IntroduceEntity {
            /**
             * value : 说明书保修卡x1 整机x1
             * key : 包装清单
             */
            private String value;
            private String key;

            public void setValue(String value) {
                this.value = value;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public String getKey() {
                return key;
            }
        }

        public static class ProfilePicEntity {
            /**
             * width : 750
             * url : http://59.110.213.63/cfc/uploads/file1/20171025/59f0250ae5bd0.jpg
             * height : 808
             */
            private int width;
            private String url;
            private int height;

            public void setWidth(int width) {
                this.width = width;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getWidth() {
                return width;
            }

            public String getUrl() {
                return url;
            }

            public int getHeight() {
                return height;
            }
        }

        public static class ProPicEntity {
            /**
             * width : 800
             * url : http://59.110.213.63/cfc/uploads/file1/20171025/59f0253c73846.jpg
             * height : 800
             */
            private int width;
            private String url;
            private int height;

            public void setWidth(int width) {
                this.width = width;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getWidth() {
                return width;
            }

            public String getUrl() {
                return url;
            }

            public int getHeight() {
                return height;
            }
        }
    }
}
