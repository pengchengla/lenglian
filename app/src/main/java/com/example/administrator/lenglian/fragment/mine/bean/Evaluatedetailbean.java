package com.example.administrator.lenglian.fragment.mine.bean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Evaluatedetailbean {


    /**
     * code : 200
     * success : success
     * datas : {"pro_score":"1","content":"1","add_status":"1","add_content":"1","pic_id":"1","pic":[{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}]}
     */

    private int code;
    private String success;
    private DatasBean datas;

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

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * pro_score : 1
         * content : 1
         * add_status : 1
         * add_content : 1
         * pic_id : 1
         * pic : [{"url":"http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png"}]
         */

        private String pro_score;
        private String content;
        private String add_status;
        private String add_content;
        private String pic_id;
        private List<PicBean> pic;

        public String getPro_score() {
            return pro_score;
        }

        public void setPro_score(String pro_score) {
            this.pro_score = pro_score;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAdd_status() {
            return add_status;
        }

        public void setAdd_status(String add_status) {
            this.add_status = add_status;
        }

        public String getAdd_content() {
            return add_content;
        }

        public void setAdd_content(String add_content) {
            this.add_content = add_content;
        }

        public String getPic_id() {
            return pic_id;
        }

        public void setPic_id(String pic_id) {
            this.pic_id = pic_id;
        }

        public List<PicBean> getPic() {
            return pic;
        }

        public void setPic(List<PicBean> pic) {
            this.pic = pic;
        }

        public static class PicBean {
            /**
             * url : http://114.215.83.139/cfc/uploads/file1/20170912/59b78365b13b4.png
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
